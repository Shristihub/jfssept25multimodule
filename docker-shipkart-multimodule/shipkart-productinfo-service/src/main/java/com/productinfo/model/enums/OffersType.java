package com.productinfo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OffersType {
	CB("CASH BACK"),
	NCE("NO COST EMI"),
	BO("BANK OFFER"),
	PO("PARTNER OFFER");
	
	private String offerName;
//	public String getOfferName() {
//		return offerName;
//	}
}
