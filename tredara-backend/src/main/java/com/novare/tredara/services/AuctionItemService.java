package com.novare.tredara.services;

import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.repositories.AuctionItemRepository;
import com.novare.tredara.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuctionItemService {
    public final AuctionItemRepository auctionItemRepository;

    public AuctionItemService(AuctionItemRepository auctionItemRepository) {
        this.auctionItemRepository = auctionItemRepository;
    }
    public List<AuctionItem> setItemStatus() {
        List<AuctionItem> contentsList = auctionItemRepository.findAll();
        contentsList.stream().forEach(item -> {
            if (item.getEndDate().compareTo(DateUtil.toDateYYMMDD(DateUtil.toStringYYMMDD(LocalDateTime.now()))) < 0) {
                item.setStatus(2);
                auctionItemRepository.save(item);
            }
        });
        return contentsList;
    }
}
