package com.tcs.pricerestapi.service;

import java.util.Optional;

import com.tcs.pricerestapi.model.Price;



public interface PriceService {
	public Optional<Price> getPriceById(long priceId);
	public Price createPrice(Price price);
	public String updatePrice(Price price);
	public void deletePrice(long priceId);
	public Optional<Price> getByProductId(long productId);
	public void deleteProductById(long productId);
}
