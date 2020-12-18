package com.tcs.pricerestapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.pricerestapi.model.Price;
import com.tcs.pricerestapi.repository.PriceServiceRepository;



@Service
public class PriceServiceImpl implements PriceService{
	
	@Autowired
	PriceServiceRepository priceServicerepository;
	
	public Optional<Price> getPriceById(long priceId) {
		// TODO Auto-generated method stub
		return priceServicerepository.findById(priceId);
	}

	
	public Price createPrice(Price price) {
		Price price_value = null;
		 try {
			 price_value= priceServicerepository.save(price);
			return price_value;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String updatePrice(Price price) {
		Price price_update = null;
		try {
			price_update = priceServicerepository.save(price);
			return "success to update";
		} catch (Exception e) {
			e.printStackTrace();
			return "failed to update";
		}
	}
	
	public void deletePrice(long priceId) {
		priceServicerepository.deleteById(priceId);
	}


	@Override
	public void deleteProductById(long productId) {
		// TODO Auto-generated method stub
		priceServicerepository.deleteByProductId(productId);
	}


	@Override
	public Optional<Price> getByProductId(long productId) {
		// TODO Auto-generated method stub
		return priceServicerepository.findByProductId(productId);
	}
}
