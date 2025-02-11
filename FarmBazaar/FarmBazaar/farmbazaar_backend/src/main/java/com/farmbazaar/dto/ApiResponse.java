package com.farmbazaar.dto;

import java.time.LocalDateTime;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {
	private LocalDateTime timeStamp;
	private String message;

	public ApiResponse(String message) {
		super();
		this.message = message;
		this.timeStamp=LocalDateTime.now();
	}
}
