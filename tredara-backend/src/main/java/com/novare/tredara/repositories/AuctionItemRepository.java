package com.novare.tredara.repositories;

import com.novare.tredara.models.AuctionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionItemRepository extends JpaRepository<AuctionItem,Integer> {
    List<AuctionItem> findByStatus(int status);
}
