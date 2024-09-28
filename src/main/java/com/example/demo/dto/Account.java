package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Account {
    
	private String username;
	private String password;
	private String role;
	private boolean enabled;
	private LocalDate invalidDate;

	public boolean isExpired() {
		boolean isExpired = false;

		LocalDate currentDate = LocalDate.now();
		
		if(currentDate.isAfter(invalidDate)) {
			isExpired = true;
		}

		return isExpired;
	}


}
