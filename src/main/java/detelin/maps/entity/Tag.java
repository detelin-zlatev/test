package detelin.maps.entity;

import javax.persistence.*;

/**
 * Created by dzlatev on 4/8/16.
 */
@Entity
public class Tag {

    private long id;

    private String k;

    private String v;

    private Way way;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    @ManyToOne
    public Way getWay() {
        return way;
    }

    public void setWay(Way way) {
        this.way = way;
    }
}
