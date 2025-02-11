package com.farmbazaar.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class CartItemRequest {

	private int productId;
    private int quantity;
}
