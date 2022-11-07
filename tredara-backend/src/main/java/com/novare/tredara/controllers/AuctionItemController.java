package com.novare.tredara.controllers;

import com.novare.tredara.exceptions.TredaraException;
import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.models.ECategory;
import com.novare.tredara.payload.AuctionItemDTO;
import com.novare.tredara.repositories.AuctionItemRepository;
import com.novare.tredara.services.AuctionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        List<AuctionItem> contents = itemRepository
                .findActiveItemsByCategory(ECategory.valueOf(category.toUpperCase()));
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
    public ResponseEntity<AuctionItem> create(@RequestBody AuctionItemDTO contentRequest) throws TredaraException {
        AuctionItem items = AuctionItemDTO.createAuctionItemModel(contentRequest);
        itemRepository.save(items);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/update")
    public ResponseEntity<AuctionItem> update(@RequestBody AuctionItemDTO contentRequest) throws TredaraException {
        AuctionItem items = AuctionItemDTO.createAuctionItemModel(contentRequest);
        itemRepository.save(items);
        return ResponseEntity.ok(items);
    }
}

