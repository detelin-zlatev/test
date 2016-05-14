package detelin.maps.service;

import detelin.maps.dto.MapDto;

import java.math.BigDecimal;

/**
 * Created by dzlatev on 4/11/16.
 */
public interface MapDataLoader {

    MapDto loadMapDataByArea(BigDecimal minLat, BigDecimal minLon, BigDecimal maxLat, BigDecimal maxLon);
}
