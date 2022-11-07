package com.novare.tredara.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.novare.tredara.models.BiddingHistory;

public interface BiddingHistoryRepository  extends JpaRepository<BiddingHistory, Integer> {

}
