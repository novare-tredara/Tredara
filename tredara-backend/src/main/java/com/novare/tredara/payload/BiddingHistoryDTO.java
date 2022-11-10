package com.novare.tredara.payload;

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

	@JsonProperty("bidding_price")
	private Long biddingPrice;

	@JsonProperty("create_on")
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

	@Override
	public int compareTo(BiddingHistoryDTO o) {
		return getId().compareTo(o.getId());
	}

	public static BiddingHistoryDTO buildResponse(BiddingHistory biddingHistory) {
		BiddingHistoryDTO historyDTO = new BiddingHistoryDTO();
		historyDTO.setId(biddingHistory.getId());
		historyDTO.setCreatedOn(DateUtil.toStringYYMMDD(biddingHistory.getCreatedOn()));
		historyDTO.setBiddingPrice(biddingHistory.getBiddingPrice());
		historyDTO.setBidder(biddingHistory.getBidder().getFullName());
		return historyDTO;
	}

	public static BiddingHistory createAuctionItemModel(BiddingHistoryDTO builder) {
		BiddingHistory history = new BiddingHistory();
		history.setId(builder.getId());
		history.setCreatedOn(DateUtil.toDateYYMMDD(builder.getCreatedOn()));
		history.setBiddingPrice(builder.getBiddingPrice());
		return history;
	}
}
