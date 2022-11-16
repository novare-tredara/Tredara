package com.novare.tredara.services;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.spring.annotations.Recurring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novare.tredara.models.AuctionItem;
import com.novare.tredara.models.BiddingHistory;
import com.novare.tredara.models.EStatus;
import com.novare.tredara.models.User;
import com.novare.tredara.repositories.AuctionItemRepository;
import com.novare.tredara.utils.DateUtil;

@Service
public class AuctionItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuctionItemService.class);

	@Autowired
	private AuctionItemRepository auctionItemRepository;
	@Autowired
	private EmailSenderService senderService;

	@Autowired
	private JobScheduler jobScheduler;

	@PostConstruct
	private void startJobBackground() {
		jobScheduler.enqueue(() -> endBidding());
	}

	@Recurring(id="endbidding-recurring-job", cron = "*/1 * * * *")
	@Job(name = "End bid and update the acution item status")
	public void endBidding() {
		Date startDate = DateUtil.toDate(LocalDateTime.now().minusMinutes(10));
		Date endDate = DateUtil.toDate(LocalDateTime.now().plusMinutes(10));
		List<AuctionItem> auctionItems = auctionItemRepository.getActiveItemsBetweenDate(startDate, endDate);
		for (AuctionItem auctionItem : auctionItems) {
			Date now = DateUtil.toDate(LocalDateTime.now());
			if (auctionItem.getEndDate().compareTo(now) <= 0) {
				LOGGER.info("******* STARTED ENDING BID ****************");
				LOGGER.info("Bidding is ending: {} Created By {} " , auctionItem.getTitle(),
						auctionItem.getCreatedBy().getFullName());
				updateAuction(auctionItem);
				LOGGER.info("******* DONE ******************************");

			}
		}
	}

	public void updateAuction(AuctionItem item) {
		Set<BiddingHistory> histories = item.getHistories();
		if (histories != null && !histories.isEmpty()) {
			List<BiddingHistory> biddingList = histories.stream().collect(Collectors.toList());
			Collections.sort(biddingList);
			BiddingHistory highestBidder = biddingList.get(0);
			LOGGER.info("Winner of the Items {0}", highestBidder.getBidder().getFullName());
			item.setSoldPrice(highestBidder.getBiddingPrice());
			sendEmailToBidder(highestBidder);
			LOGGER.info("Email Notification sent @{0} ", highestBidder.getBidder().getEmail());
		}
		item.setStatus(EStatus.INACTIVE.getStatus());
		auctionItemRepository.save(item);
	}

	private void sendEmailToBidder(BiddingHistory highestBidder) {
		User user = highestBidder.getBidder();
		String mail = user.getEmail();
		String body = "Hello " + user.getFullName() + ",\n\nYou won the bid for "
				+ highestBidder.getAuctionItem().getTitle() + "\n\nRegards\nTredara";
		senderService.sendSimpleEmail(mail, "Congratulations from Tredara", body);
	}

}
