package com.novare.tredara.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "auction_item")
public class AuctionItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "TITLE")
	@NotEmpty(message = "*Please provide a valid Item Title")
	private String title;

	@Column(name = "DESCRIPTION")
	@NotEmpty(message = "*Please provide a valid Item Description")
	private String description;

	@Column(name = "IMAGE")
	@NotEmpty(message = "*Please provide a valid Item image path")
	private String image;

	@Column(name = "ORIGINAL_PRICE")
	@NotNull(message = "*Please provide a valid Item Original price")
	private Long originalPrice;

	@Column(name = "SOLD_PRICE")
	private Long soldPrice;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "STATUS")
	private int status;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(foreignKey = @ForeignKey(name = "ITEM_CATEGORY_ID"), name = "CATEGORY_ID", referencedColumnName = "ID")
	private Category category;

	@OneToMany(mappedBy = "auctionItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BiddingHistory> histories;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(foreignKey = @ForeignKey(name = "ITEM_USER_ID"), name = "USER_ID", referencedColumnName = "ID")
	private User createdBy;

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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<BiddingHistory> getHistories() {
		return histories;
	}

	public void setHistories(Set<BiddingHistory> histories) {
		this.histories = histories;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
}
