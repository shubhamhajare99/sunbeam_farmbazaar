package com.farmbazaar.dto;

import lombok.*;


@NoArgsConstructor
@Getter
@Setter
public class UserDetailsDTO {

	private int id;
    private String email;
    private String role;
}
