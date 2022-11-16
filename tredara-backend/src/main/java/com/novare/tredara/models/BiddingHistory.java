package com.novare.tredara.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bidding_history")
public class BiddingHistory implements Comparable<BiddingHistory>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(foreignKey = @ForeignKey(name = "BID_AUCTION_ID"), name = "AUCTION_ID", referencedColumnName = "ID")
	private AuctionItem auctionItem;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(foreignKey = @ForeignKey(name = "ITEM_BIDDER_ID"), name = "USER_ID", referencedColumnName = "ID")
	private User bidder;
	
	@Column(name = "BIDDING_PRICE")
	private Long biddingPrice;
	
	@Column(name = "CREATED_ON")
	private Date createdOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AuctionItem getAuctionItem() {
		return auctionItem;
	}

	public void setAuctionItem(AuctionItem auctionItem) {
		this.auctionItem = auctionItem;
	}

	public User getBidder() {
		return bidder;
	}

	public void setBidder(User bidder) {
		this.bidder = bidder;
	}

	public Long getBiddingPrice() {
		return biddingPrice;
	}

	public void setBiddingPrice(Long biddingPrice) {
		this.biddingPrice = biddingPrice;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public int compareTo(BiddingHistory o) {
		return o.getBiddingPrice().compareTo(getBiddingPrice());
	}
}
