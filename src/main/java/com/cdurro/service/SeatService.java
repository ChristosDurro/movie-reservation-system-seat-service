package com.cdurro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cdurro.dto.SeatAvailabilityDTO;
import com.cdurro.model.Seat;
import com.cdurro.repository.SeatRepository;

@Service
public class SeatService {
	
	@Autowired
	SeatRepository repo;
	
	public ResponseEntity<List<Seat>> getSeats() {
		
		List<Seat> seats = repo.findAll();
		
		return ResponseEntity.ok(seats);
	}
	
	public ResponseEntity<Seat> getSeat(Long id) {
		
		return ResponseEntity.ok(repo.findById(id).orElse(null));
	}

	public ResponseEntity<List<Seat>> getSeatsByScheduleId(Long scheduleId) {
		
		List<Seat> seats = repo.findAllByScheduleId(scheduleId);
		
		return ResponseEntity.ok(seats);
	}
	
	public ResponseEntity<Seat> createSeat(Seat seat) {
		
		Seat seatSaved = repo.save(seat);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(seatSaved);
	}
	
	public ResponseEntity<List<Seat>> createSeats(List<Seat> seats) {
		
		List<Seat> seatsSaved = repo.saveAll(seats);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(seatsSaved);
	}

	public ResponseEntity<Seat> updateSeat(Seat seat) {
		
		Seat existingSeat = repo.findById(seat.getId()).orElse(null);
		
		if (existingSeat != null) {
			existingSeat.setAvailable(seat.isAvailable());
			existingSeat.setScheduleId(seat.getScheduleId());
			existingSeat.setSeatColumn(seat.getSeatColumn());
			existingSeat.setSeatRow(seat.getSeatRow());

			return ResponseEntity.ok(repo.save(existingSeat));
		}
		return ResponseEntity.ok(null);
	}

	public ResponseEntity<List<Seat>> updateSeats(@RequestBody SeatAvailabilityDTO body) {
		
		List<Seat> seatsToUpdate = repo.findAllById(body.getSeatIdsToUpdate());
		
		if (seatsToUpdate.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		for (Seat seat : seatsToUpdate) {
			seat.setAvailable(body.getAvailability());
		}
		
		List<Seat> updatedSeats = repo.saveAll(seatsToUpdate);
		
		return ResponseEntity.ok(updatedSeats);
	}

	public ResponseEntity<List<Seat>> getSeatsByIds(List<Long> scheduleIds) {
		
		List<Seat> seats = repo.findAllById(scheduleIds);
		
		return ResponseEntity.ok(seats);
	}

	public List<Seat> resetSeats(Long scheduleId) {
		
		List<Seat> seats = repo.findAllByScheduleId(scheduleId); 
		
		
		for (Seat seat : seats) {
			seat.setAvailable(true);
		}
		
		repo.saveAll(seats);
				
		return seats;
	}

	public Seat resetSeat(Long id) {
		
		Seat seat = repo.findById(id).orElse(null);
		
		if (seat != null) {
			seat.setAvailable(true);
			repo.save(seat);
		}
		
		return seat;
	}
}
