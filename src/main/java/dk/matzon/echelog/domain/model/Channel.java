package dk.matzon.echelog.domain.model;

import se.citerus.dddsample.domain.shared.Entity;

import java.util.Objects;

/**
 * POJO representing a channel
 *
 * @author Brian Matzon <brian@matzon.dk>
 */
public class Channel implements Entity<Channel> {

    private long id;
    private String name;
    private String description;
    private String url;
    private boolean archived;

    public Channel() {
    }

    public Channel(long _id, String _name, String _description, String _url, boolean _archived) {
        id = _id;
        name = _name;
        description = _description;
        url = _url;
        archived = _archived;
    }

    public long getId() {
        return id;
    }

    public void setId(long _id) {
        id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String _description) {
        description = _description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String _url) {
        url = _url;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean _archived) {
        archived = _archived;
    }

    @Override
    public boolean sameIdentityAs(Channel _channel) {
        return id == _channel.id;
    }

    @Override
    public boolean equals(Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        Channel channel = (Channel) _o;
        return id == channel.id &&
                archived == channel.archived &&
                Objects.equals(name, channel.name) &&
                Objects.equals(description, channel.description) &&
                Objects.equals(url, channel.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, url, archived);
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", archived=" + archived +
                '}';
    }
}
