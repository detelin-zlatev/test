package detelin.maps.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by dzlatev on 4/8/16.
 */
@Entity
public class Bounds {

    private long id;

    private BigDecimal minLat;

    private BigDecimal minLon;

    private BigDecimal maxLat;

    private BigDecimal maxLon;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getMinLat() {
        return minLat;
    }

    public void setMinLat(BigDecimal minLat) {
        this.minLat = minLat;
    }

    public BigDecimal getMinLon() {
        return minLon;
    }

    public void setMinLon(BigDecimal minLon) {
        this.minLon = minLon;
    }

    public BigDecimal getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(BigDecimal maxLat) {
        this.maxLat = maxLat;
    }

    public BigDecimal getMaxLon() {
        return maxLon;
    }

    public void setMaxLon(BigDecimal maxLon) {
        this.maxLon = maxLon;
    }
}
