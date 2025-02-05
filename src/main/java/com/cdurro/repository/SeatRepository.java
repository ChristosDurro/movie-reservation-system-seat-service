package com.cdurro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdurro.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
	public List<Seat> findAllByScheduleId(Long scheduleId);
}
