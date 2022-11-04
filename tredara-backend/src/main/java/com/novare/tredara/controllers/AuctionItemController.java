package com.novare.tredara.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.models.ECategory;
import com.novare.tredara.payload.AuctionItemDTO;
import com.novare.tredara.repositories.AuctionItemRepository;
import com.novare.tredara.services.AuctionItemService;

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
    public ResponseEntity<List<AuctionItem>> getByStatus(@PathVariable(value = "status") Integer status) {
        List<AuctionItem> contents = itemRepository.findAll().stream().filter(items -> items.getStatus() == status)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contents);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuctionItemDTO>> getAuctionItemByFreeText(@RequestParam("freeText") String freeText) {
        List<AuctionItem> contents =auctionItemService.getAuctionItemByFreeText(freeText);
        List<AuctionItemDTO> auctionItemDTOs = new ArrayList<>();
        contents.stream().forEach(item -> {
            auctionItemDTOs.add(AuctionItemDTO.buildResponse(item));
        });
        return ResponseEntity.ok(auctionItemDTOs);
    }

    @GetMapping("/getbycategory/{category}")
    public ResponseEntity<List<AuctionItem>> getMobiles(@PathVariable(value = "category") String category) {
        List<AuctionItem> contents = itemRepository
                .findActiveItemsByCategory(ECategory.valueOf(category.toUpperCase()));
        return ResponseEntity.ok(contents);
    }
}
