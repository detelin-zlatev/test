package detelin.maps.dto;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import detelin.maps.entity.Way;

/**
 * Created by dzlatev on 4/11/16.
 */
public class TagDto {

    @XStreamAsAttribute
    private String k;

    @XStreamAsAttribute
    private String v;

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

    @Override
    public String toString() {
        return "TagDto{" +
                "k='" + k + '\'' +
                ", v='" + v + '\'' +
                '}';
    }
}
