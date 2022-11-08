package com.novare.tredara.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.novare.tredara.exceptions.TredaraException;
import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.models.ECategory;
import com.novare.tredara.payload.AuctionItemDTO;
import com.novare.tredara.repositories.AuctionItemRepository;
import com.novare.tredara.services.AuctionItemService;
import com.novare.tredara.utils.DateUtil;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/api/auctionitems")
public class AuctionItemController {

	@Autowired
	private AuctionItemRepository itemRepository;

	@Autowired
	private AuctionItemService auctionItemService;

	@GetMapping("/")
	public ResponseEntity<List<AuctionItemDTO>> getAllItems() {
		List<AuctionItem> auctionItems = itemRepository.findAll();
		List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();
		auctionItems.stream().forEach(item -> {
			auctionItemDTOs.add(AuctionItemDTO.buildResponse(item));
		});
		return ResponseEntity.ok(auctionItemDTOs);
	}

	@GetMapping("/getbystatus/{status}")
	public ResponseEntity<List<AuctionItemDTO>> getByStatus(@PathVariable(value = "status") Integer status) {
		List<AuctionItem> contents = itemRepository.findAll().stream().filter(items -> items.getStatus() == status)
				.collect(Collectors.toList());
		List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();
		contents.stream().forEach(item -> {
			auctionItemDTOs.add(AuctionItemDTO.buildResponse(item));
		});
		return ResponseEntity.ok(auctionItemDTOs);
	}

	@GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AuctionItemDTO>> getAuctionItemByFreeText(@RequestParam("freeText") String freeText) {
		List<AuctionItem> contents = auctionItemService.getAuctionItemByFreeText(freeText);
		List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();
		contents.stream().forEach(item -> {
			auctionItemDTOs.add(AuctionItemDTO.buildResponse(item));
		});
		return ResponseEntity.ok(auctionItemDTOs);
	}

	@GetMapping("/getbycategory/{category}")
	public ResponseEntity<List<AuctionItemDTO>> getItemsByCategory(@PathVariable(value = "category") String category) {
		List<AuctionItem> items = itemRepository
				.findActiveItemsByCategory(ECategory.valueOf(category.toUpperCase()));
		List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();
		items.stream().forEach(item -> {
			auctionItemDTOs.add(AuctionItemDTO.buildResponse(item));
		});
		return ResponseEntity.ok(auctionItemDTOs);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteItem(@PathVariable(value = "id") Integer itemId)
			throws TredaraException {
		AuctionItem item = itemRepository.findById(itemId)
				.orElseThrow(() -> new TredaraException(HttpStatus.NOT_FOUND, "Item not found on :: " + itemId));
		itemRepository.delete(item);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/create")
	public ResponseEntity<AuctionItemDTO> create(@RequestBody AuctionItemDTO contentRequest) throws TredaraException {
		if(contentRequest.getStartDate()==null) {
			contentRequest.setStartDate(DateUtil.toStringYYMMDD(LocalDateTime.now()));
		}
		AuctionItem items = AuctionItemDTO.createAuctionItemModel(contentRequest);
		itemRepository.save(items);
		return ResponseEntity.ok().body(contentRequest);
	}

	@PutMapping("/update")
	public ResponseEntity<AuctionItemDTO> update(@RequestBody AuctionItemDTO contentRequest) throws TredaraException {
		AuctionItem items = AuctionItemDTO.createAuctionItemModel(contentRequest);
		itemRepository.save(items);
		return ResponseEntity.ok().body(contentRequest);
	}

	@GetMapping("/getitemsbyuser/{user}")
	public ResponseEntity<List<AuctionItemDTO>>getItemsByUser(@PathVariable(value = "user") String user)
			throws TredaraException {
		List<AuctionItem> items = itemRepository.findByUser(user);
		List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();
		
		items.stream().forEach(item -> {
			auctionItemDTOs.add(AuctionItemDTO.buildResponse(item));
		});
		return ResponseEntity.ok(auctionItemDTOs);
	}

}
