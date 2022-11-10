package com.novare.tredara.controllers;

import com.novare.tredara.exceptions.TredaraException;
import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.models.BiddingHistory;
import com.novare.tredara.models.ECategory;
import com.novare.tredara.models.User;
import com.novare.tredara.payload.AuctionItemDTO;
import com.novare.tredara.payload.BiddingHistoryDTO;
import com.novare.tredara.repositories.AuctionItemRepository;
import com.novare.tredara.repositories.BiddingHistoryRepository;
import com.novare.tredara.repositories.UserRepository;
import com.novare.tredara.services.AuctionItemService;
import com.novare.tredara.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/api/auctionitems")
public class AuctionItemController {

    @Autowired
    private AuctionItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuctionItemService auctionItemService;
    @Autowired
    private BiddingHistoryRepository biddingHistoryRepository;

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

    @GetMapping("/details/{id}")
    public ResponseEntity<AuctionItemDTO> getItemDetailsById(@PathVariable(value = "id") Integer itemId)
            throws TredaraException {
        AuctionItem item = itemRepository.findById(itemId)
                .orElseThrow(() -> new TredaraException(HttpStatus.NOT_FOUND, "Item not found on :: " + itemId));
        AuctionItemDTO itemDTO = AuctionItemDTO.buildResponse(item);
        return ResponseEntity.ok(itemDTO);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<AuctionItemDTO> updatePrice(@PathVariable(value = "id") Integer itemId, @RequestBody AuctionItemDTO itemRequest)
            throws TredaraException {
        AuctionItem item = itemRepository.findById(itemId)
                .orElseThrow(() -> new TredaraException(HttpStatus.NOT_FOUND, "Item not found on :: " + itemId));
        AuctionItem items = AuctionItemDTO.createAuctionItemModel(itemRequest);
        items.setCreatedBy(item.getCreatedBy());
        itemRepository.save(items);
        User user = userRepository.findByEmail(itemRequest.getUser()).orElseThrow(
                () -> new TredaraException(HttpStatus.NOT_FOUND, "User not found on :: " + itemRequest.getUser()));
        BiddingHistoryDTO historyDTO = new BiddingHistoryDTO();
        historyDTO.setBiddingPrice(items.getOriginalPrice());
        historyDTO.setCreatedOn(itemRequest.getStartDate());
        BiddingHistory history = BiddingHistoryDTO.createAuctionItemModel(historyDTO);
        history.setAuctionItem(items);
        history.setBidder(user);
        biddingHistoryRepository.save(history);
        return ResponseEntity.ok(itemRequest);
    }

    @GetMapping("/getbycategory/{category}")
    public ResponseEntity<List<AuctionItemDTO>> getItemsByCategory(@PathVariable(value = "category") String category) {
        List<AuctionItem> items = itemRepository.findActiveItemsByCategory(ECategory.valueOf(category.toUpperCase()));
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
    public ResponseEntity<AuctionItem> create(@RequestBody AuctionItemDTO itemRequest) throws TredaraException {
        if (itemRequest.getStartDate() == null) {
            itemRequest.setStartDate(DateUtil.toStringYYMMDD(LocalDateTime.now()));
        }
        AuctionItem items = AuctionItemDTO.createAuctionItemModel(itemRequest);
        User user = userRepository.findByEmail(itemRequest.getUser()).orElseThrow(
                () -> new TredaraException(HttpStatus.NOT_FOUND, "User not found on :: " + itemRequest.getUser()));
        items.setCreatedBy(user);
        itemRepository.save(items);
        BiddingHistoryDTO historyDTO = new BiddingHistoryDTO();
        historyDTO.setBiddingPrice(items.getOriginalPrice());
        historyDTO.setCreatedOn(itemRequest.getStartDate());
        BiddingHistory history = BiddingHistoryDTO.createAuctionItemModel(historyDTO);
        history.setAuctionItem(items);
        history.setBidder(user);
        biddingHistoryRepository.save(history);
        return ResponseEntity.ok(items);
    }

    @PutMapping("/update")
    public ResponseEntity<AuctionItemDTO> update(@RequestBody AuctionItemDTO itemRequest) throws TredaraException {
        AuctionItem items = AuctionItemDTO.createAuctionItemModel(itemRequest);
        User user = userRepository.findByEmail(itemRequest.getUser()).orElseThrow(
                () -> new TredaraException(HttpStatus.NOT_FOUND, "User not found on :: " + itemRequest.getUser()));
        items.setCreatedBy(user);
        itemRepository.save(items);
        return ResponseEntity.ok().body(itemRequest);
    }

    @GetMapping("/getitemsbyuser/{user}")
    public ResponseEntity<List<AuctionItemDTO>> getItemsByUser(@PathVariable(value = "user") String user)
            throws TredaraException {
        List<AuctionItem> items = itemRepository.findByUser(user);
        List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();

        items.stream().forEach(item -> {
            auctionItemDTOs.add(AuctionItemDTO.buildResponse(item));
        });
        return ResponseEntity.ok(auctionItemDTOs);
    }

}
