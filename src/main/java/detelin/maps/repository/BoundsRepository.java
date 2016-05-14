package detelin.maps.repository;

import detelin.maps.entity.Bounds;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by dzlatev on 5/4/16.
 */
@Repository
public interface BoundsRepository extends CrudRepository<Bounds, Long> {

    Bounds findByMinLatAndMinLonAndMaxLatAndMaxLon(BigDecimal minLat, BigDecimal minLon, BigDecimal maxLat, BigDecimal maxLon);

}
