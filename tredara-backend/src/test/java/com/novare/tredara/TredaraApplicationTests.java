package com.novare.tredara;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.models.ECategory;
import com.novare.tredara.repositories.AuctionItemRepository;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = TredaraApplication.class)
class TredaraApplicationTests {
	@Autowired
	private AuctionItemRepository itemRepository;

	@Test
	public void testFindActiveItemsByCategory() {
		List<AuctionItem> findActiveItemsByCategory = itemRepository.findActiveItemsByCategory(ECategory.MOBILES);
		System.out.println("TredaraApplicationTests.findActiveItemsByCategory()");
		for (AuctionItem auctionItem : findActiveItemsByCategory) {
			System.out.println(auctionItem.getDescription());
		}
	}

	@Test
	public void testFindInActiveItemsByCategory() {
		List<AuctionItem> findInActiveItemsByCategory = itemRepository.findInActiveItemsByCategory(ECategory.MOBILES);
		System.out.println("TredaraApplicationTests.findInActiveItemsByCategory()");
		for (AuctionItem auctionItem : findInActiveItemsByCategory) {
			System.out.println(auctionItem.getDescription());
		}
	}

	@Test
	public void testFindItemsByCategory() {
		List<AuctionItem> findActiveItemsByCategory = itemRepository.findAllItemsByCategory(ECategory.MOBILES);
		System.out.println("TredaraApplicationTests.findAllItemsByCategory()");
		for (AuctionItem auctionItem : findActiveItemsByCategory) {
			System.out.println(auctionItem.getDescription());
		}
	}

}
