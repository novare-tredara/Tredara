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
	@NotEmpty(message = "*Please provide a valid Item Original price")
	private Long originalPrice;

	@Column(name = "SOLD_PRICE")
	private Long soldPrice;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(foreignKey = @ForeignKey(name = "ITEM_CATEGORY_ID"), name = "CATEGORY_ID", referencedColumnName = "ID")
	private Category category;

	@OneToMany(mappedBy = "auctionItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<BiddingHistory> histories;

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(foreignKey = @ForeignKey(name = "ITEM_USER_ID"), name = "USER_ID", referencedColumnName = "ID")
	private User createdBy;
}
