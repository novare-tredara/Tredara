package com.novare.tredara.payload;

import java.text.ParseException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.novare.tredara.models.BiddingHistory;
import com.novare.tredara.utils.DateUtil;

public class BiddingHistoryDTO implements Comparable<BiddingHistoryDTO> {

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("auction_item")
	private Integer auctionItem;

	@JsonProperty("bidder")
	private String bidder;

	@JsonProperty("bidder_email")
	private String bidderEmail;

	@JsonProperty("bidding_price")
	private Long biddingPrice;

	@JsonProperty("created_on")
	private String createdOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuctionItem() {
		return auctionItem;
	}

	public void setAuctionItem(Integer auctionItem) {
		this.auctionItem = auctionItem;
	}

	public String getBidder() {
		return bidder;
	}

	public void setBidder(String bidder) {
		this.bidder = bidder;
	}

	public Long getBiddingPrice() {
		return biddingPrice;
	}

	public void setBiddingPrice(Long biddingPrice) {
		this.biddingPrice = biddingPrice;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getBidderEmail() {
		return bidderEmail;
	}

	public void setBidderEmail(String bidderEmail) {
		this.bidderEmail = bidderEmail;
	}

	@Override
	public int hashCode() {
		return createdOn.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BiddingHistoryDTO) {
			BiddingHistoryDTO dto = (BiddingHistoryDTO) obj;
			return this.createdOn.compareTo(dto.getCreatedOn()) == 0;
		}
		return super.equals(obj);
	}

	@Override
	public int compareTo(BiddingHistoryDTO compare) {
		return this.getCreatedOn().compareTo(compare.getCreatedOn());
	}

	public static BiddingHistoryDTO buildDTO(BiddingHistory history) {
		BiddingHistoryDTO dto = new BiddingHistoryDTO();
		dto.setAuctionItem(history.getAuctionItem().getId());
		dto.setBidder(history.getBidder().getFullName());
		dto.setBidderEmail(history.getBidder().getEmail());
		dto.setBiddingPrice(history.getBiddingPrice());
		dto.setCreatedOn(DateUtil.toString(history.getCreatedOn()));
		dto.setId(history.getId());
		return dto;
	}

	public static BiddingHistory buildModel(BiddingHistoryDTO dto) throws ParseException {
		BiddingHistory history = new BiddingHistory();
		history.setBiddingPrice(dto.getBiddingPrice());
		history.setCreatedOn(DateUtil.toDate(dto.getCreatedOn()));
		return history;
	}
}
