package dk.matzon.echelog.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for a Channel. A Channel DTO always have a weak NetworkDTO parent.
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ChannelDTO implements Serializable {

    private NetworkDTO network;
    private String name;
    private String description;
    private String url;
    private boolean archived;

    public ChannelDTO(NetworkDTO _network, String _name, String _description, String _url, boolean _archived) {
        this.network = new NetworkDTO(_network.getName());
        this.name = _name;
        this.description = _description;
        this.url = _url;
        this.archived = _archived;
    }

    public NetworkDTO getNetwork() {
        return network;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public boolean isArchived() {
        return archived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChannelDTO that = (ChannelDTO) o;
        return archived == that.archived &&
                Objects.equals(network, that.network) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(network, name, description, url, archived);
    }

    @Override
    public String toString() {
        return "ChannelDTO{" +
                "network='" + network + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", archived=" + archived +
                '}';
    }
}
