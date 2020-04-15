package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApigeeListRequest {
    private long id;
    private String title;
    private String text;
    private long lastsynced;
    private boolean checked;
    private long timestamp;

    @Override
    public String toString() {
        return "ApigeeListRequest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", lastsynced=" + lastsynced +
                ", checked=" + checked +
                ", timestamp=" + timestamp +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getLastsynced() {
        return lastsynced;
    }

    public void setLastsynced(long lastsynced) {
        this.lastsynced = lastsynced;
    }

    public boolean isChecked() {
        return checked;
    }

    @JsonProperty(value="checked")
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
