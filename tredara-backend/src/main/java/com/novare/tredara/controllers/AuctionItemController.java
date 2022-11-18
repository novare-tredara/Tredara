package com.novare.tredara.controllers;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.novare.tredara.dtos.AuctionItemDTO;
import com.novare.tredara.dtos.BiddingHistoryDTO;
import com.novare.tredara.exceptions.TredaraException;
import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.models.BiddingHistory;
import com.novare.tredara.models.ECategory;
import com.novare.tredara.models.EStatus;
import com.novare.tredara.models.User;
import com.novare.tredara.repositories.AuctionItemRepository;
import com.novare.tredara.repositories.UserRepository;
import com.novare.tredara.services.AuctionItemService;
import com.novare.tredara.utils.DateUtil;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auctionitems")
@Validated
public class AuctionItemController {

	@Autowired
	private final static Logger LOGGER = LoggerFactory.getLogger(AuctionItemController.class);

	@Autowired
	private AuctionItemRepository itemRepository;
	@Autowired
	private AuctionItemService auctionItemService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public ResponseEntity<List<AuctionItemDTO>> getAllItems() {
		LOGGER.info("Start: Fetching all the auction items, [api/auctionitems]");
		List<AuctionItem> auctionItems = itemRepository.findAll();
		List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();
		auctionItems.stream().forEach(item -> {
			auctionItemDTOs.add(AuctionItemDTO.buildResponse(item));
		});
		LOGGER.info("End: Found the auction items, [api/auctionitems] Total:{}", auctionItemDTOs.size());
		return ResponseEntity.ok(auctionItemDTOs);
	}

	@GetMapping("/getbystatus/{status}")
	public ResponseEntity<List<AuctionItemDTO>> getByStatus(@PathVariable(value = "status") Integer status) {
		LOGGER.info("Start: Fetching all the auction items by status, [/api/auctionitems/getbystatus/{}]", status);
		List<AuctionItem> contents = itemRepository.findAll().stream().filter(items -> items.getStatus() == status)
				.collect(Collectors.toList());
		List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();
		contents.stream().forEach(item -> {
			auctionItemDTOs.add(AuctionItemDTO.buildResponse(item));
		});
		LOGGER.info("End: Found the auction items by status, [/api/auctionitems/getbystatus/{}] Total:{}", status,
				auctionItemDTOs.size());
		return ResponseEntity.ok(auctionItemDTOs);
	}

	@GetMapping("/getbiddings/{auctionId}")
	public ResponseEntity<List<BiddingHistoryDTO>> getAllBiddingsByAuction(
			@PathVariable(value = "auctionId") Integer auctionId) {
		LOGGER.info("Start: Fetching all the Bidding histories by auciton ID, [/api/auctionitems/getbiddings/{}]",
				auctionId);
		List<BiddingHistory> biddiList = itemRepository.findBiddingsByAuctionItem(auctionId);
		List<BiddingHistoryDTO> biddingHistoryDTOs = new ArrayList<>();
		biddiList.stream().forEach(item -> {
			biddingHistoryDTOs.add(BiddingHistoryDTO.buildDTO(item));
		});
		LOGGER.info("End: Found the Bidding histories by auciton ID, [/api/auctionitems/getbiddings/{}] Total:{}",
				auctionId, biddingHistoryDTOs.size());
		return ResponseEntity.ok(biddingHistoryDTOs);
	}

	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AuctionItemDTO>> getAuctionItemByFreeText(@RequestParam("freeText") String freeText) {
		LOGGER.info("Start: Searching the auction items by title, [/api/auctionitems/search/{}]", freeText);
		List<AuctionItem> contents = itemRepository.findByTitleContainsAndStatus(freeText, 1);
		List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();
		contents.stream().forEach(item -> {
			auctionItemDTOs.add(AuctionItemDTO.buildResponse(item));
		});
		LOGGER.info("End: Found the auction items by title, [/api/auctionitems/search/{}] Total:{}", freeText,
				auctionItemDTOs.size());
		return ResponseEntity.ok(auctionItemDTOs);
	}

	@GetMapping("/details/{id}")
	public ResponseEntity<AuctionItemDTO> getItemDetailsById(@PathVariable(value = "id") Integer itemId)
			throws TredaraException {
		LOGGER.info("Start: Get the auction items by id, [/api/auctionitems/details/{}]", itemId);
		AuctionItem item = itemRepository.findById(itemId)
				.orElseThrow(() -> new TredaraException(HttpStatus.NOT_FOUND, "Item not found on :: " + itemId));
		AuctionItemDTO itemDTO = AuctionItemDTO.buildResponse(item);
		LOGGER.info("End: Found the auction items by id, [/api/auctionitems/details/{}]", itemId);
		return ResponseEntity.ok(itemDTO);
	}

	@GetMapping("/getbycategory/{category}")
	public ResponseEntity<List<AuctionItemDTO>> getItemsByCategory(@PathVariable(value = "category") String category) {
		LOGGER.info("Start: Get the auction items by category, [/api/auctionitems/getbycategory/{}]", category);
		List<AuctionItem> items = itemRepository.findActiveItemsByCategory(ECategory.valueOf(category.toUpperCase()));
		List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();
		items.stream().forEach(item -> {
			auctionItemDTOs.add(AuctionItemDTO.buildResponse(item));
		});
		LOGGER.info("End: Found the auction items by category, [/api/auctionitems/getbycategory/{}] Total:{}", category,
				auctionItemDTOs.size());
		return ResponseEntity.ok(auctionItemDTOs);
	}

	@GetMapping("/getitemsbyuser/{user}")
	public ResponseEntity<List<AuctionItemDTO>> getItemsByUser(@PathVariable(value = "user") String user)
			throws TredaraException {
		LOGGER.info("Start: Get the auction items by user, [/api/auctionitems/getitemsbyuser/{}]", user);
		List<AuctionItem> items = itemRepository.findByUser(user);
		List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();

		items.stream().forEach(item -> {
			auctionItemDTOs.add(AuctionItemDTO.buildResponse(item));
		});
		LOGGER.info("End: Found the auction items by user, [/api/auctionitems/getitemsbyuser/{}] Total:{}", user,
				auctionItemDTOs.size());
		return ResponseEntity.ok(auctionItemDTOs);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteItem(@PathVariable(value = "id") Integer itemId)
			throws TredaraException {
		LOGGER.info("Start: Deleting the auction item by ID, [/api/auctionitems/delete/{}]", itemId);
		AuctionItem item = itemRepository.findById(itemId)
				.orElseThrow(() -> new TredaraException(HttpStatus.NOT_FOUND, "Item not found on :: " + itemId));
		itemRepository.delete(item);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		LOGGER.info("End: Deleted the auction item, [/api/auctionitems/delete/{}] Deleted:{}", itemId,
				response.toString());
		return ResponseEntity.ok(response);
	}

	@PostMapping("/create")
	public ResponseEntity<AuctionItem> create(@RequestBody AuctionItemDTO itemRequest) throws TredaraException {
		LOGGER.info("Start: Creating the auction item , [/api/auctionitems/create/{}]", itemRequest.getTitle());
		if (itemRequest.getStartDate() == null) {
			itemRequest.setStartDate(DateUtil.toStringYYMMDD(LocalDateTime.now()));
		}

		AuctionItem items = AuctionItemDTO.createAuctionItemModel(itemRequest);
		User user = userRepository.findByEmail(itemRequest.getUserEmail()).orElseThrow(
				() -> new TredaraException(HttpStatus.NOT_FOUND, "User not found on :: " + itemRequest.getUser()));
		items.setCreatedBy(user);
		itemRepository.save(items);
		LOGGER.info("End: Successfully Created the auction item , [/api/auctionitems/create/{}]",
				itemRequest.getTitle());
		return ResponseEntity.ok(items);
	}

	@PutMapping("/update")
	public ResponseEntity<AuctionItemDTO> update(@RequestBody AuctionItemDTO itemRequest) throws TredaraException {
		LOGGER.info("Start: Updating the auction item , [/api/auctionitems/update/{}]", itemRequest.getTitle());
		AuctionItem items = AuctionItemDTO.createAuctionItemModel(itemRequest);
		User user = userRepository.findByEmail(itemRequest.getUserEmail()).orElseThrow(
				() -> new TredaraException(HttpStatus.NOT_FOUND, "User not found on :: " + itemRequest.getUser()));
		items.setCreatedBy(user);
		itemRepository.save(items);
		LOGGER.info("End: Successfully Updated the auction item , [/api/auctionitems/update/{}]",
				itemRequest.getTitle());
		return ResponseEntity.ok().body(itemRequest);
	}

	@PutMapping("/endbidding")
	public ResponseEntity<AuctionItemDTO> endBidding(@RequestBody AuctionItemDTO itemRequest) throws TredaraException {
		LOGGER.info("Start: Ending the bidding for the auction item , [/api/auctionitems/endbidding/{}]",
				itemRequest.getTitle());
		AuctionItem item = AuctionItemDTO.createAuctionItemModel(itemRequest);
		User user = userRepository.findByEmail(itemRequest.getUserEmail()).orElseThrow(
				() -> new TredaraException(HttpStatus.NOT_FOUND, "User not found on :: " + itemRequest.getUser()));
		item.setCreatedBy(user);
		List<BiddingHistory> biddingHistories = itemRepository.findBiddingsByAuctionItem(item.getId());
		item.setHistories(biddingHistories.stream().collect(Collectors.toSet()));
		auctionItemService.updateAuction(item);
		itemRequest.setStatus(EStatus.INACTIVE.getStatus());
		LOGGER.info("End: Closed the bidding process for the auction item , [/api/auctionitems/endbidding/{}]",
				itemRequest.getTitle());
		return ResponseEntity.ok().body(itemRequest);
	}

	@PutMapping("/updatebidding")
	public ResponseEntity<BiddingHistoryDTO> updateHistory(@RequestBody BiddingHistoryDTO itemRequest)
			throws TredaraException {
		try {
			LOGGER.info(
					"Start: Updating the bidding history for the auction item , [/api/auctionitems/updatebidding/{}]",
					itemRequest.getBidderEmail());
			BiddingHistory bidding = BiddingHistoryDTO.buildModel(itemRequest);

			User user = userRepository.findByEmail(itemRequest.getBidderEmail())
					.orElseThrow(() -> new TredaraException(HttpStatus.NOT_FOUND,
							"User not found on :: " + itemRequest.getBidderEmail()));
			bidding.setBidder(user);

			AuctionItem item = itemRepository.findById(itemRequest.getAuctionItem())
					.orElseThrow(() -> new TredaraException(HttpStatus.NOT_FOUND,
							"Auction Item not found on :: " + itemRequest.getAuctionItem()));

			bidding.setAuctionItem(item);
			item.setOriginalPrice(itemRequest.getBiddingPrice());
			item.getHistories().add(bidding);

			itemRepository.save(item);
			LOGGER.info(
					"End: Updated the bidding history for the auction item , [/api/auctionitems/updatebidding/{}] Price:{}",
					itemRequest.getBidderEmail(), itemRequest.getBiddingPrice());

		} catch (ParseException e) {
			throw new TredaraException(e.getMessage(), HttpStatus.BAD_REQUEST,
					"Parsing failes :: " + itemRequest.getAuctionItem());
		}
		return ResponseEntity.ok().body(itemRequest);
	}

}
