package detelin.maps.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.Set;

/**
 * Created by dzlatev on 4/11/16.
 */
@XStreamAlias("osm")
public class MapDto {

    @XStreamImplicit(itemFieldName="node")
    private Set<NodeDto> nodes;

    @XStreamImplicit(itemFieldName="way")
    private Set<WayDto> ways;

    private BoundsDto bounds;

    public Set<NodeDto> getNodes() {
        return nodes;
    }

    public void setNodes(Set<NodeDto> nodes) {
        this.nodes = nodes;
    }

    public Set<WayDto> getWays() {
        return ways;
    }

    public void setWays(Set<WayDto> ways) {
        this.ways = ways;
    }

    public BoundsDto getBounds() {
        return bounds;
    }

    public void setBounds(BoundsDto bounds) {
        this.bounds = bounds;
    }

    @Override
    public String toString() {
        return "MapDto{" +
                "nodes=" + nodes +
                ", ways=" + ways +
                ", bounds=" + bounds +
                '}';
    }
}

