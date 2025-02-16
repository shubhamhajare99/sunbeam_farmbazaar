package com.farmbazaar.pojos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer id;
	
    private String email;
    private String password;

    @Column(name = "fname")
    private String firstName;

    @Column(name = "lname")
    private String lastName;

    private String mobileNo;
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT true")
    private boolean isActive;


}