package com.jacekg.homefinances.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserErrorResponse {
	
	private int status;
	private String message;
	private long timestamp;
}
