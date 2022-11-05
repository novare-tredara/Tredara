package com.novare.tredara.payload;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.models.Category;
import com.novare.tredara.models.ECategory;
import com.novare.tredara.utils.ImageUtils;

public class AuctionItemDTO implements Comparable<AuctionItemDTO> {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("title")
	private String title;

	@JsonProperty("description")
	private String description;

	@JsonProperty("image")
	private String image;

	@JsonProperty("original_price")
	private Long originalPrice;

	@JsonProperty("sold_price")
	private Long soldPrice;

	@JsonProperty("start_date")
	private Date startDate;

	@JsonProperty("end_date")
	private Date endDate;

	@JsonProperty("status")
	private int status;

	@JsonProperty("category")
	private String category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Long originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Long getSoldPrice() {
		return soldPrice;
	}

	public void setSoldPrice(Long soldPrice) {
		this.soldPrice = soldPrice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int compareTo(AuctionItemDTO o) {
		return getId().compareTo(o.getId());
	}

	public static AuctionItemDTO buildResponse(AuctionItem auctionItem) {
		AuctionItemDTO itemDTO = new AuctionItemDTO();
		itemDTO.setId(auctionItem.getId());
		itemDTO.setDescription(auctionItem.getDescription());
		itemDTO.setEndDate(auctionItem.getEndDate());
		itemDTO.setStartDate(auctionItem.getStartDate());
		// itemDTO.setEndDate(DateUtil.toString(auctionItem.getEndDate()));
		// itemDTO.setStartDate(DateUtil.toString(auctionItem.getStartDate()));
		itemDTO.setImage(ImageUtils.toBase64(auctionItem.getImage()));
		itemDTO.setOriginalPrice(auctionItem.getOriginalPrice());
		itemDTO.setSoldPrice(auctionItem.getSoldPrice());
		itemDTO.setStatus(auctionItem.getStatus());
		itemDTO.setTitle(auctionItem.getTitle());
		return itemDTO;

	}

	public static AuctionItem createAuctionItemModel(AuctionItemDTO builder) {
		AuctionItem item = new AuctionItem();
		item.setId(builder.getId());
		item.setTitle(builder.getTitle());
		item.setDescription(builder.getDescription());
		item.setImage(ImageUtils.toImageFile(builder.getImage(), builder.getCategory().toLowerCase()));
		item.setOriginalPrice(builder.getOriginalPrice());
		item.setSoldPrice(builder.getSoldPrice());
		item.setStartDate(builder.getStartDate());
		item.setEndDate(builder.getEndDate());
		item.setStatus(builder.getStatus());
		return item;

	}
}
