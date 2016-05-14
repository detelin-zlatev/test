package detelin.maps.repository;

import detelin.maps.entity.Bounds;
import detelin.maps.entity.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by dzlatev on 5/4/16.
 */
@Repository
public interface NodeRepository extends CrudRepository<Node, Long> {

}
