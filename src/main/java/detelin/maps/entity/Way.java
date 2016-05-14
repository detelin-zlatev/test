package detelin.maps.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dzlatev on 4/8/16.
 */
@Entity
public class Way {

    private long id;

    private long extId;

    private Set<Node> nodes;

    private Set<Tag> tags;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "ext_id")
    public long getExtId() {
        return extId;
    }

    public void setExtId(long extId) {
        this.extId = extId;
    }

    @ManyToMany
    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    @OneToMany(mappedBy = "way")
    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set
                                <Tag> tags) {
        this.tags = tags;
    }
}
