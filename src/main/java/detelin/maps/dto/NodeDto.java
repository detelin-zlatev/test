package detelin.maps.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by dzlatev on 4/11/16.
 */
public class NodeDto {

    @XStreamAsAttribute
    private long id;

    @XStreamAsAttribute
    private BigDecimal lat;

    @XStreamAsAttribute
    private BigDecimal lon;

    @XStreamAsAttribute
    @XStreamAlias("ref")
    private BigDecimal ndRefId;


    private Set<WayDto> ways;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public Set<WayDto> getWays() {
        return ways;
    }

    public void setWays(Set<WayDto> ways) {
        this.ways = ways;
    }

    public BigDecimal getNdRefId() {
        return ndRefId;
    }

    public void setNdRefId(BigDecimal ndRefId) {
        this.ndRefId = ndRefId;
    }

    @Override
    public String toString() {
        return "NodeDto{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", ndRefId=" + ndRefId +
                '}';
    }
}
