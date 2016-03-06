package dk.matzon.echelog.domain.model;

import se.citerus.dddsample.domain.shared.Entity;

import java.util.Objects;

/**
 * POJO representing a channel
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
public class Channel implements Entity<Channel> {

    private Network network;
    private String name;
    private String description;
    private String url;
    private boolean archived;

    public Channel() {
    }

    public Channel(Network network, String name, String description, String url, boolean _archived) {
        this.network = network;
        this.name = name;
        this.description = description;
        this.url = url;
        this.archived = _archived;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return archived == channel.archived &&
                Objects.equals(network, channel.network) &&
                Objects.equals(name, channel.name) &&
                Objects.equals(description, channel.description) &&
                Objects.equals(url, channel.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(network, name, description, url, archived);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    @Override
    public boolean sameIdentityAs(Channel _channel) {
        return network.equals(_channel.network) && name.equals(_channel.name);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", archived=" + archived +
                '}';
    }
}
