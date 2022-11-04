package com.novare.tredara.services;

import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.models.ECategory;
import com.novare.tredara.repositories.AuctionItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AuctionItemService {
    public final AuctionItemRepository auctionItemRepository;

    public AuctionItemService(AuctionItemRepository auctionItemRepository) {
        this.auctionItemRepository = auctionItemRepository;
    }

    public List<AuctionItem> getItems() {
        return auctionItemRepository.findByStatus(1);
    }

    public List<AuctionItem> getMobiles() {
        return getItems(ECategory.MOBILES);
    }

    public List<AuctionItem> getAccessories() {
        return getItems(ECategory.ACCESSORIES);
    }

    public List<AuctionItem> getClothes() {
        return getItems(ECategory.CLOTHES);
    }

    public List<AuctionItem> getBooks() {
        return getItems(ECategory.BOOKS);
    }

    public List<AuctionItem> getBeauty() {
        return getItems(ECategory.BEAUTYCARE);
    }

    private List<AuctionItem> getItems(ECategory category) {
        List<AuctionItem> AllContents = getItems();
        int contentId;
        if (Objects.equals(category, ECategory.MOBILES)) {
            contentId = 1;
        } else if (Objects.equals(category, ECategory.ACCESSORIES)) {
            contentId = 2;
        } else if (Objects.equals(category, ECategory.CLOTHES)) {
            contentId = 3;
        } else if (Objects.equals(category, ECategory.BOOKS)) {
            contentId = 4;
        } else contentId = 5;

        return AllContents.stream()
                .filter(items -> items.getCategory().getId() == contentId)
                .collect(Collectors.toList());
    }

    public List<AuctionItem> getAuctionItemByFreeText(String freeText) {
        List<AuctionItem> item =auctionItemRepository.findByTitleContainsAndStatus(freeText,1);
        //item.add((AuctionItem) auctionItemRepository.findByDescriptionContainsAndStatus(freeText,1));
        return  item;

    }
}
