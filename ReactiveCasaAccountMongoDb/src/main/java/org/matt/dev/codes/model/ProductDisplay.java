package org.matt.dev.codes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDisplay {
	private String productImage;
    private String containerBackgroundColor;
    private String containerBorderColor;
    private String quickActionBackgroundColor;

}
