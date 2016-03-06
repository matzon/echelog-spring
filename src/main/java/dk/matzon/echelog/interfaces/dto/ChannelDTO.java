package dk.matzon.echelog.interfaces.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for channels
 * @author Brian Matzon <brian@matzon.dk>
 */
public class ChannelDTO implements Serializable {

    private String network;
    private String name;
    private String description;
    private String url;
    private boolean archived;

    public ChannelDTO(String network, String name, String description, String url, boolean _archived) {
        this.network = network;
        this.name = name;
        this.description = description;
        this.url = url;
        this.archived = _archived;
    }

    public String getNetwork() {
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
