package com.novare.tredara.controllers;

import com.novare.tredara.models.BiddingHistory;
import com.novare.tredara.payload.BiddingHistoryDTO;
import com.novare.tredara.repositories.BiddingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/api/auctionitems")
public class BiddingHistoryController {

    @Autowired
    private BiddingHistoryRepository repository;

    @GetMapping("/getbiddings/{auctionid}")
    public ResponseEntity<List<BiddingHistoryDTO>> getHistory(@PathVariable(value = "auctionid") Integer auctionid) {
        List<BiddingHistory> history = repository.findAll().stream().filter(items -> items.getAuctionItem().getId() == auctionid)
                .collect(Collectors.toList());
        List<BiddingHistoryDTO> historyDTOS = new ArrayList<>();
        history.stream().forEach(item -> {
            historyDTOS.add(BiddingHistoryDTO.buildResponse(item));
        });
        return ResponseEntity.ok(historyDTOS);
    }
}
