package com.novare.tredara.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.models.ECategory;

public interface AuctionItemRepository extends JpaRepository<AuctionItem, Integer> {
	List<AuctionItem> findByStatus(int status);

	@Query("FROM AuctionItem WHERE category.category=:category")
	List<AuctionItem> findAllItemsByCategory(@Param("category") ECategory category);

	@Query("FROM AuctionItem WHERE category.category=:category and status=1")
	List<AuctionItem> findActiveItemsByCategory(@Param("category") ECategory category);

	@Query("FROM AuctionItem WHERE category.category=:category and status=0")
	List<AuctionItem> findInActiveItemsByCategory(ECategory category);

	List<AuctionItem> findByTitleContainsAndStatus(String freeText, int status);

	List<AuctionItem> findByDescriptionContainsAndStatus(String freeText, int status);
	
	@Query("FROM AuctionItem WHERE createdBy.email=:email")
	List<AuctionItem> findByUser(@Param("email") String email) ;

}
