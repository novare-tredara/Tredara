package com.novare.tredara.controllers;

import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.services.AuctionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/api")
public class AuctionItemController {
    public final AuctionItemService auctionItemService;

    @Autowired
    public AuctionItemController(AuctionItemService auctionItemService) {
        this.auctionItemService = auctionItemService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<AuctionItem>> listAllItems() {
        List<AuctionItem> contents = auctionItemService.getItems();
        return ResponseEntity.ok(contents);
    }

    @GetMapping("/items/mobiles")
    public ResponseEntity<List<AuctionItem>> getMobiles() {
        List<AuctionItem> contents = auctionItemService.getMobiles();
        return ResponseEntity.ok(contents);
    }

    @GetMapping("/items/accessories")
    public ResponseEntity<List<AuctionItem>> getAccessories() {
        List<AuctionItem> contents = auctionItemService.getAccessories();
        return ResponseEntity.ok(contents);
    }

    @GetMapping("/items/clothes")
    public ResponseEntity<List<AuctionItem>> getClothes() {
        List<AuctionItem> contents = auctionItemService.getClothes();
        return ResponseEntity.ok(contents);
    }

    @GetMapping("/items/books")
    public ResponseEntity<List<AuctionItem>> getBooks() {
        List<AuctionItem> contents = auctionItemService.getBooks();
        return ResponseEntity.ok(contents);
    }

    @GetMapping("/items/beautycare")
    public ResponseEntity<List<AuctionItem>> getBeauty() {
        List<AuctionItem> contents = auctionItemService.getBeauty();
        return ResponseEntity.ok(contents);
    }

    @GetMapping(value= "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AuctionItem>> getAuctionItemByFreeText(@RequestParam("freeText") String freeText) {
        return ResponseEntity.ok(auctionItemService.getAuctionItemByFreeText(freeText));
    }
}
