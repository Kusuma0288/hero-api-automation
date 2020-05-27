package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ApigeeProductsInList {
    private long id;
    private long articleId;
    private double quantity;
    private long timestamp;
    private boolean checked;
    private String description;

    @Override
    public String toString() {
        return "ApigeeProductsInList{" +
                "id=" + id +
                ", articleId='" + articleId + '\'' +
                ", quantity=" + quantity +
                ", timestamp=" + timestamp +
                ", checked=" + checked +
                ", description=" + description +
                '}';
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    }
