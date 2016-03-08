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

    private long id;
    private String name;
    private String description;
    private String url;
    private boolean archived;

    public ChannelDTO() {
    }

    public ChannelDTO(long _id, String _name, String _description, String _url, boolean _archived) {
        id = _id;
        name = _name;
        description = _description;
        url = _url;
        archived = _archived;
    }

    public long getId() {
        return id;
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
    public boolean equals(Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        ChannelDTO that = (ChannelDTO) _o;
        return id == that.id &&
                archived == that.archived &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, url, archived);
    }

    @Override
    public String toString() {
        return "ChannelDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", archived=" + archived +
                '}';
    }
}
