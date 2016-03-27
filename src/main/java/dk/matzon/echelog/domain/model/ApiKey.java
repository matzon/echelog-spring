package dk.matzon.echelog.domain.model;

public class ApiKey {
    private long id;
    private String key;
    private boolean valid;

    private String user;
    private String password;

    public ApiKey(long _id, String _key, boolean _valid, String _user, String _password) {
        id = _id;
        key = _key;
        valid = _valid;
        user = _user;
        password = _password;
    }

    public long getId() {
        return id;
    }

    public void setId(long _id) {
        id = _id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String _key) {
        key = _key;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean _valid) {
        valid = _valid;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String _user) {
        user = _user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password) {
        password = _password;
    }

    @Override
    public boolean equals(Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;

        ApiKey apiKey = (ApiKey) _o;

        if (id != apiKey.id) return false;
        if (valid != apiKey.valid) return false;
        if (key != null ? !key.equals(apiKey.key) : apiKey.key != null) return false;
        if (user != null ? !user.equals(apiKey.user) : apiKey.user != null) return false;
        return password != null ? password.equals(apiKey.password) : apiKey.password == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (valid ? 1 : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApiKey{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", valid=" + valid +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}