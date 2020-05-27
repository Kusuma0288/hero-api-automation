package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddProductsToListRequest {
    private String articleId;
    private int quantity;
    private boolean checked;
    private long timestamp;
    private long lastsynced;
    private ApigeeListDetailsResponse apigeeListDetailsResponse;
    

    @Override
    public String toString() {
        return "ApigeeListRequest{" +
                "articleId=" + articleId +
                ", quantity='" + quantity + '\'' +
                ", lastsynced=" + lastsynced +
                ", checked=" + checked +
                ", timestamp=" + timestamp +
                '}';
    }

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
