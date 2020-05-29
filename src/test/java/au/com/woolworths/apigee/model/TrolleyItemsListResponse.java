package au.com.woolworths.apigee.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Arrays;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrolleyItemsListResponse {
	private String name;
	private String article;
	private String description;
	private Object images;
	private String href;
	private int size;
	private String measure;
	private Object is;
	private String instorelocation;
	private boolean allowsubstitution;
	private String comment;
	private InStorePrice instoreprice;
	private Promotions promotions;
	private Object footer;
	private int maxquantitylimit;
	private int defaultquantity;
	private int minquantitylimit;
	private int incrementalquantity;
	private int itemquantityintrolley;
	private int totalitemprice;
	private String supplementaryInfoTextAll;
	private boolean isrestricted;
	private boolean isdeliverypass;
	private boolean isavailable;
	private boolean ispmrestriction;
	private boolean isforcollection;
	private boolean isfordelivery;
	private Object supplementaryinfo;
	private String updated;
	private Object disclaimer;


	@Override
	public String toString() {
		return "TrolleyItems{" +
				"name=" + name +
				", article=" + article +
				", description=" + description +
				", images=" + images +
				", href=" + href +
				", size=" + size +
				", measure=" + measure +
				", is=" + is +
				", instorelocation=" + instorelocation +
				", allowsubstitution=" + allowsubstitution +
				", comment=" + comment +
				", instoreprice=" + instoreprice +
				", promotions=" + promotions +
				", footer=" + footer +
				", maxquantitylimit=" + maxquantitylimit +
				", defaultquantity=" + defaultquantity +
				", minquantitylimit=" + minquantitylimit +
				", incrementalquantity=" + incrementalquantity +
				", totalitemprice=" + totalitemprice +
				", supplementaryInfoTextAll=" + supplementaryInfoTextAll +
				", isrestricted=" + isrestricted +
				", isdeliverypass=" + isdeliverypass +
				", isavailable=" + isavailable +
				", ispmrestriction=" + ispmrestriction +
				", isforcollection=" + isforcollection +
				", isfordelivery=" + isfordelivery +
				", supplementaryinfo=" + supplementaryinfo +
				", updated=" + updated +
				", disclaimer=" + disclaimer +
				'}';
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getArticle() {
		return article;
	}


	public void setArticle(String article) {
		this.article = article;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Object getImages() {
		return images;
	}


	public void setImages(Object images) {
		this.images = images;
	}


	public String getHref() {
		return href;
	}


	public void setHref(String href) {
		this.href = href;
	}


	public int getSize() {
		return size;
	}


	public String getMeasure() {
		return measure;
	}


	public void setMeasure(String measure) {
		this.measure = measure;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public Object getIs() {
		return is;
	}


	public void setIs(Object is) {
		this.is = is;
	}


	public String getInstorelocation() {
		return instorelocation;
	}


	public void setInstorelocation(String instorelocation) {
		this.instorelocation = instorelocation;
	}


	public boolean isAllowsubstitution() {
		return allowsubstitution;
	}


	public void setAllowsubstitution(boolean allowsubstitution) {
		this.allowsubstitution = allowsubstitution;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public InStorePrice getInstoreprice() {
		return instoreprice;
	}


	public void setInstoreprice(InStorePrice instoreprice) {
		this.instoreprice = instoreprice;
	}

	public Promotions getPromotions() {
		return promotions;
	}


	public void setPromotions(Promotions promotions) {
		this.promotions = promotions;
	}

	public Object getFooter() {
		return footer;
	}


	public void setFooter(Object footer) {
		this.footer = footer;
	}


	public int getMaxquantitylimit() {
		return maxquantitylimit;
	}


	public void setMaxquantitylimit(int maxquantitylimit) {
		this.maxquantitylimit = maxquantitylimit;
	}


	public int getDefaultquantity() {
		return defaultquantity;
	}


	public void setDefaultquantity(int defaultquantity) {
		this.defaultquantity = defaultquantity;
	}


	public int getMinquantitylimit() {
		return minquantitylimit;
	}


	public void setMinquantitylimit(int minquantitylimit) {
		this.minquantitylimit = minquantitylimit;
	}


	public int getIncrementalquantity() {
		return incrementalquantity;
	}


	public void setIncrementalquantity(int incrementalquantity) {
		this.incrementalquantity = incrementalquantity;
	}


	public int getItemquantityintrolley() {
		return itemquantityintrolley;
	}


	public void setItemquantityintrolley(int itemquantityintrolley) {
		this.itemquantityintrolley = itemquantityintrolley;
	}


	public int getTotalitemprice() {
		return totalitemprice;
	}


	public void setTotalitemprice(int totalitemprice) {
		this.totalitemprice = totalitemprice;
	}


	public String getSupplementaryInfoTextAll() {
		return supplementaryInfoTextAll;
	}


	public void setSupplementaryInfoTextAll(String supplementaryInfoTextAll) {
		this.supplementaryInfoTextAll = supplementaryInfoTextAll;
	}


	public boolean isIsrestricted() {
		return isrestricted;
	}


	public void setIsrestricted(boolean isrestricted) {
		this.isrestricted = isrestricted;
	}


	public boolean isIsdeliverypass() {
		return isdeliverypass;
	}


	public void setIsdeliverypass(boolean isdeliverypass) {
		this.isdeliverypass = isdeliverypass;
	}


	public boolean isIsavailable() {
		return isavailable;
	}


	public void setIsavailable(boolean isavailable) {
		this.isavailable = isavailable;
	}


	public boolean isIspmrestriction() {
		return ispmrestriction;
	}


	public void setIspmrestriction(boolean ispmrestriction) {
		this.ispmrestriction = ispmrestriction;
	}


	public boolean isIsforcollection() {
		return isforcollection;
	}


	public void setIsforcollection(boolean isforcollection) {
		this.isforcollection = isforcollection;
	}


	public boolean isIsfordelivery() {
		return isfordelivery;
	}


	public void setIsfordelivery(boolean isfordelivery) {
		this.isfordelivery = isfordelivery;
	}


	public Object getSupplementaryinfo() {
		return supplementaryinfo;
	}


	public void setSupplementaryinfo(Object supplementaryinfo) {
		this.supplementaryinfo = supplementaryinfo;
	}


	public String getUpdated() {
		return updated;
	}


	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public Object getDisclaimer() {
		return disclaimer;
	}


	public void setDisclaimer(Object disclaimer) {
		this.disclaimer = disclaimer;
	}


}
