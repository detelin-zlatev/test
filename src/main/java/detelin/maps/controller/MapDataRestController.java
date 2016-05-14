package detelin.maps.controller;

import detelin.maps.dto.MapDto;
import detelin.maps.service.MapDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by dzlatev on 4/11/16.
 */
@RestController
@RequestMapping(value = "/mapData", produces = MediaType.APPLICATION_JSON_VALUE)
public class MapDataRestController {

    @Autowired
    private MapDataLoader mapDataLoader;

    @RequestMapping(value = "/loadByArea/minLat/{minLat}/minLon/{minLon}/maxLat/{maxLat}/maxLon/{maxLon}", method = RequestMethod.GET)
    public MapDto loadMapDataByArea(@PathVariable final BigDecimal minLat,
                                    @PathVariable final BigDecimal minLon,
                                    @PathVariable final BigDecimal maxLat,
                                    @PathVariable final BigDecimal maxLon) {
        return this.mapDataLoader.loadMapDataByArea(minLat, minLon, maxLat, maxLon);
    }
}
