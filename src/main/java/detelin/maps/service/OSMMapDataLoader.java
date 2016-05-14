package detelin.maps.service;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import detelin.maps.dto.BoundsDto;
import detelin.maps.dto.MapDto;
import detelin.maps.dto.NodeDto;
import detelin.maps.dto.WayDto;
import detelin.maps.entity.Bounds;
import detelin.maps.entity.Node;
import detelin.maps.repository.BoundsRepository;
import detelin.maps.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dzlatev on 4/11/16.
 */
@Service
public class OSMMapDataLoader implements MapDataLoader {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BoundsRepository boundsRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public MapDto loadMapDataByArea(BigDecimal minLat, BigDecimal minLon, BigDecimal maxLat, BigDecimal maxLon) {
        final BoundsDto area = new BoundsDto(minLat, minLon, maxLat, maxLon);
        ensureSectors(area);
        return new MapDto();
    }

    private MapDto downloadSectorMapData(final BoundsDto sector) {
        final String sectorURL = "http://api.openstreetmap.org/api/0.6/map?bbox="
                + sector.getMinLat() + "," + sector.getMinLon() + "," + sector.getMaxLat() + "," + sector.getMaxLon();
        System.out.println("Downloading sector map data: " + sectorURL);

        String rawMapData = this.restTemplate.getForObject(sectorURL, String.class);

        XStream xStream = new XStream(new StaxDriver());
        xStream.processAnnotations(new Class[] {MapDto.class, WayDto.class, NodeDto.class, BoundsDto.class});
        xStream.omitField(NodeDto.class, "tag");
        xStream.omitField(MapDto.class, "relation");
        final MapDto sectorMapDto = (MapDto)xStream.fromXML(rawMapData);

        System.out.println("Downloaded sector map data: " + sectorURL + sectorMapDto);

        return sectorMapDto;
    }

    private void ensureSectors(final BoundsDto area) {
        final List<BoundsDto> areaSectors = getAreaSectors(area);

        final List<BoundsDto> sectorsToDownload = new ArrayList<>();

        for(final BoundsDto sector : areaSectors) {
            final Bounds bounds = this.boundsRepository.findByMinLatAndMinLonAndMaxLatAndMaxLon(
                    area.getMinLat(), area.getMinLon(), area.getMaxLat(), area.getMaxLon());

            if (bounds == null) {
                sectorsToDownload.add(sector);
            }
        }

        final List<MapDto> downloadedSectors = sectorsToDownload.parallelStream().map(b -> downloadSectorMapData(b)).collect(Collectors.toList());

        System.out.println("Persisting sectors...");
        this.persistSectors(downloadedSectors);
        System.out.println(downloadedSectors.size() + " persisted");
    }


    private void persistSectors(final List<MapDto> sectorsToPersist) {
        for (final MapDto sector : sectorsToPersist) {
            final Map<Long, NodeDto> nodesMap = new HashMap<>();

            if (sector.getNodes() != null) {
                for (final NodeDto node : sector.getNodes()) {
                    nodesMap.put(node.getId(), node);

                    final Node nodeToPersist = new Node();
                    nodeToPersist.setExtId(node.getId());
                    nodeToPersist.setLat(node.getLat());
                    nodeToPersist.setLon(node.getLon());

                    this.nodeRepository.save(nodeToPersist);
                }
            }

            final Bounds bounds = new Bounds();
            bounds.setMaxLat(sector.getBounds().getMaxLat());
            bounds.setMaxLon(sector.getBounds().getMaxLon());
            bounds.setMinLat(sector.getBounds().getMinLat());
            bounds.setMinLon(sector.getBounds().getMinLon());
            this.boundsRepository.save(bounds);
        }
    }

    private List<BoundsDto> getAreaSectors(final BoundsDto area) {
        if (area.getMinLat().compareTo(area.getMaxLat()) >= 0) {
            throw new IllegalArgumentException("minLat:" + area.getMinLat()+  " should be less than maxLat: " + area.getMaxLat());
        }

        if (area.getMinLon().compareTo(area.getMaxLon()) >= 0) {
            throw new IllegalArgumentException("minLon:" + area.getMinLon() +  " should be less than maxLon: " + area.getMaxLon());
        }

        final int STEP = 50;
        final BigDecimal INT_MULTIPLIER = new BigDecimal(100000);

        final int minLatInt = area.getMinLat().multiply(INT_MULTIPLIER).intValue();
        final int maxLatInt = area.getMaxLat().multiply(INT_MULTIPLIER).intValue();
        final int minLonInt = area.getMinLon().multiply(INT_MULTIPLIER).intValue();
        final int maxLonInt = area.getMaxLon().multiply(INT_MULTIPLIER).intValue();

        final BoundsDto areaExtBounds = new BoundsDto(
                new BigDecimal(minLatInt - (minLatInt % STEP)),
                new BigDecimal(minLonInt - (minLonInt % STEP)),
                new BigDecimal(maxLatInt + ((maxLatInt % STEP == 0) ? 0 : (STEP - (maxLatInt % STEP)))),
                new BigDecimal(maxLonInt + ((maxLonInt % STEP == 0) ? 0 : (STEP - (maxLonInt % STEP)))));

        System.out.println("Surrounding bounds: " + areaExtBounds);

        final List<BoundsDto> sectors = new ArrayList<>();

        for (int lat = areaExtBounds.getMinLat().intValue(); lat < areaExtBounds.getMaxLat().intValue(); lat = lat + STEP) {
            for (int lon = areaExtBounds.getMinLon().intValue(); lon < areaExtBounds.getMaxLon().intValue(); lon = lon + STEP) {
                sectors.add(new BoundsDto(new BigDecimal(lat).divide(INT_MULTIPLIER),
                        new BigDecimal(lon).divide(INT_MULTIPLIER),
                        new BigDecimal(lat + STEP).divide(INT_MULTIPLIER),
                        new BigDecimal(lon + STEP).divide(INT_MULTIPLIER)));
            }
        }

        return Collections.unmodifiableList(sectors);
    }
}
