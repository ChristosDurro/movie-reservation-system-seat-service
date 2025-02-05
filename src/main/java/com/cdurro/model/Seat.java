package com.cdurro.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Seat {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private int seatRow;
	private int seatColumn;
	private boolean available;
	private Long scheduleId;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getSeatRow() {
		return seatRow;
	}
	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}
	public int getSeatColumn() {
		return seatColumn;
	}
	public void setSeatColumn(int seatColumn) {
		this.seatColumn = seatColumn;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	
	@Override
	public String toString() {
		return "Seat [id=" + id + ", row=" + seatRow + ", column=" + seatColumn + ", isAvailable=" + available
				+ ", scheduleId=" + scheduleId + "]";
	}
}
