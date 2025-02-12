package com.cdurro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdurro.dto.SeatAvailabilityDTO;
import com.cdurro.model.Seat;
import com.cdurro.service.SeatService;

@RestController
public class SeatController {
	
	@Autowired
	SeatService seatService;
	
	@GetMapping("/seats")
	public ResponseEntity<List<Seat>> getAllSeats() {
		
		return seatService.getSeats();
	}
	
	@GetMapping("/seats/{id}")
	public ResponseEntity<Seat> getSeatById(@PathVariable Long id) {
		
		return seatService.getSeat(id);
	}
	
	@GetMapping("/seats/schedule/{scheduleId}")
	public ResponseEntity<List<Seat>> getSeatsByScheduleId(@PathVariable Long scheduleId) {
		
		return seatService.getSeatsByScheduleId(scheduleId);
	}
	
	@PostMapping("/seats/multiple")
	public ResponseEntity<List<Seat>> getMultipleSeats(@RequestBody List<Long> scheduleIds) {
		
		return seatService.getSeatsByIds(scheduleIds);
	}
	
	@PostMapping("/seats/create")
	public ResponseEntity<Seat> createSeat(@RequestBody Seat seat) {
		
		return seatService.createSeat(seat);
	}
	
	@PostMapping("/seats/multiple/create")
	public ResponseEntity<List<Seat>> createSeats(@RequestBody Long seatId) {
		
		List<Seat> multiSeats = new ArrayList<>(100);
		
		for (int row = 0; row < 10; row++) {
		    for (int column = 0; column < 10; column++) {
		        Seat seat = new Seat();
		        
		        seat.setSeatRow(row);
		        seat.setSeatColumn(column);
		        seat.setAvailable(true);
		        seat.setScheduleId(seatId);
		        
		        multiSeats.add(seat);
		    }
		}
		
		return seatService.createSeats(multiSeats);
	}
	
	@PutMapping("/seats/update")
	public ResponseEntity<Seat> updateSeat(@RequestBody Seat seat) {

		return seatService.updateSeat(seat);
	}
	
	@PutMapping("/seats/update/multiple")
	public ResponseEntity<List<Seat>> updateMultipleSeats(@RequestBody SeatAvailabilityDTO body) {
		
		return seatService.updateSeats(body);
	}
	
	@PutMapping("/seats/availability/reset/{id}")
	public Seat resetSeatAvailability(@PathVariable Long id) {
		
		return seatService.resetSeat(id);
	}
	
	@PutMapping("/seats/availability/reset")
	public List<Seat> resetSeatsAvailability(@RequestBody Long scheduleId) {
		
		return seatService.resetSeats(scheduleId);
	}
}
