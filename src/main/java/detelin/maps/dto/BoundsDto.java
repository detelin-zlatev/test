package detelin.maps.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.math.BigDecimal;

/**
 * Created by dzlatev on 4/8/16.
 */
@XStreamAlias("bounds")
public class BoundsDto {

    private long id;

    @XStreamAsAttribute
    @XStreamAlias("minlat")
    private BigDecimal minLat;

    @XStreamAsAttribute
    @XStreamAlias("minlon")
    private BigDecimal minLon;

    @XStreamAsAttribute
    @XStreamAlias("maxlat")
    private BigDecimal maxLat;

    @XStreamAsAttribute
    @XStreamAlias("maxlon")
    private BigDecimal maxLon;

    public BoundsDto(BigDecimal minLat, BigDecimal minLon, BigDecimal maxLat, BigDecimal maxLon) {
        this.minLat = minLat;
        this.minLon = minLon;
        this.maxLat = maxLat;
        this.maxLon = maxLon;
    }

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

    @Override
    public String toString() {
        return "BoundsDto{" +
                "minLat=" + minLat +
                ", minLon=" + minLon +
                ", maxLat=" + maxLat +
                ", maxLon=" + maxLon +
                '}';
    }
}
