package com.novare.tredara.payload;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.utils.DateUtil;
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
	private String startDate;

	@JsonProperty("end_date")
	private String endDate;

	@JsonProperty("status")
	private int status;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String  startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public int compareTo(AuctionItemDTO o) {
		return getId().compareTo(o.getId());
	}

	public static AuctionItemDTO buildResponse(AuctionItem auctionItem) {
		AuctionItemDTO itemDTO = new AuctionItemDTO();
		itemDTO.setId(auctionItem.getId());
		itemDTO.setDescription(auctionItem.getDescription());
		itemDTO.setEndDate(DateUtil.toString(auctionItem.getEndDate()));
		itemDTO.setStartDate(DateUtil.toString(auctionItem.getStartDate()));
		itemDTO.setImage(ImageUtils.toBase64(auctionItem.getImage()));
		itemDTO.setOriginalPrice(auctionItem.getOriginalPrice());
		itemDTO.setSoldPrice(auctionItem.getSoldPrice());
		itemDTO.setStatus(auctionItem.getStatus());
		itemDTO.setTitle(auctionItem.getTitle());
		return itemDTO;

	}
}
