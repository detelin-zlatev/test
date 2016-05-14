package detelin.maps.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by dzlatev on 4/8/16.
 */
@Entity
public class Node {

    private long id;

    private long extId;

    private BigDecimal lat;

    private BigDecimal lon;

    private Set<Way> ways;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getExtId() {
        return extId;
    }

    public void setExtId(long extId) {
        this.extId = extId;
    }

    @Column(precision=10, scale=8)
    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    @Column(precision=10, scale=8)
    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    @ManyToMany(mappedBy = "nodes")
    public Set<Way> getWays() {
        return ways;
    }

    public void setWays(Set<Way> ways) {
        this.ways = ways;
    }
}
