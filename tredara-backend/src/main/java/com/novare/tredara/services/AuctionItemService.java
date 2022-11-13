package com.novare.tredara.services;

import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.repositories.AuctionItemRepository;
import com.novare.tredara.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AuctionItemService {
    public final AuctionItemRepository auctionItemRepository;
    @Autowired
    private EmailSenderService senderService;

    public AuctionItemService(AuctionItemRepository auctionItemRepository) {
        this.auctionItemRepository = auctionItemRepository;
    }

    public List<AuctionItem> setItemStatus() {
        List<AuctionItem> contentsList = auctionItemRepository.findAll();
        contentsList.stream().forEach(item -> {
            if ((item.getEndDate().compareTo(DateUtil.toDateYYMMDD(DateUtil.toStringYYMMDD(LocalDateTime.now()))) < 0)
                    && (item.getStatus() == 1)) {
                item.setStatus(2);
                List<Integer> ids = new ArrayList<>();
                item.getHistories().forEach(value -> ids.add(value.getId()));
                Collections.sort(ids);
                var user = item.getHistories().stream().filter(val -> val.getId() == ids.get(ids.size() - 1)).findFirst();
                String mail = user.get().getBidder().getEmail();
                String body = "Hello " + user.get().getBidder().getFullName() + ",\n\nYou won the bid for " + item.getTitle() + "\n\nRegards\nTredara";
                senderService.sendSimpleEmail(mail, "Congratulations from Tredara", body);
                auctionItemRepository.save(item);
            }
        });
        return contentsList;
    }
}
