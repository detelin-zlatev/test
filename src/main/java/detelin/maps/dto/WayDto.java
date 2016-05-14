package detelin.maps.dto;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.Set;

/**
 * Created by dzlatev on 4/11/16.
 */
public class WayDto {

    @XStreamAsAttribute
    private long id;

    @XStreamImplicit(itemFieldName="tag")
    private Set<TagDto> tags;

    @XStreamImplicit(itemFieldName="nd")
    private Set<NodeDto> nodes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<TagDto> getTags() {
        return tags;
    }

    public void setTags(Set<TagDto> tags) {
        this.tags = tags;
    }

    public Set<NodeDto> getNodes() {
        return nodes;
    }

    public void setNodes(Set<NodeDto> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "WayDto{" +
                "id=" + id +
                ", tags=" + tags +
                ", nodes=" + nodes +
                '}';
    }
}
