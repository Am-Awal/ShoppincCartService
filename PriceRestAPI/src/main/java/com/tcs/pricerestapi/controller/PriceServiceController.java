package com.tcs.pricerestapi.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.tcs.pricerestapi.exception.ResourceNotFoundException;
import com.tcs.pricerestapi.model.Price;
import com.tcs.pricerestapi.service.PriceService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/price")
public class PriceServiceController {
	
	@Autowired
	PriceService prices;
	
	@GetMapping("/id/{id}")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Price> getByProductId(@PathVariable("id") long productId) 
			throws ResourceNotFoundException {
		
		Price price = prices.getProductById(productId).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
		return ResponseEntity.ok().body(price);
	}
	
	
	@PostMapping("/create")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createPrice(@RequestBody Price price, 
			UriComponentsBuilder uriComponentsBuilder,HttpServletRequest request) {		
		Price price2 = prices.createPrice(price);
		UriComponents uriComponents = uriComponentsBuilder
				.path(request.getRequestURI()+"/{id}")
				.buildAndExpand(price2.getPriceId());
		return ResponseEntity.created(uriComponents.toUri()).body(price2);
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public HashMap<String, Boolean> deletePriceById(@PathVariable("id") long id) 
			throws ResourceNotFoundException {
		Price price3 = prices.getPriceById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Price not found"));
		
		prices.deletePrice(id);
		HashMap<String, Boolean> hashMap = new HashMap<>();
		hashMap.put("deleted", Boolean.TRUE);
		
		return hashMap;
	}
	
	@DeleteMapping("/delete/productid/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public HashMap<String, Boolean> deletePriceByProductId(@PathVariable("id") long id) 
			throws ResourceNotFoundException {
		Price price3 = prices.getProductById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Price not found"));
		
		prices.deleteProductById(id);
		HashMap<String, Boolean> hashMap = new HashMap<>();
		hashMap.put("deleted", Boolean.TRUE);
		
		return hashMap;
	}
	
	
	@PutMapping("/update/{id}")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Price> updatePrice(@PathVariable("id") long id,
			@Valid @RequestBody Price price) throws ResourceNotFoundException{
	
		Price price2 = prices.getPriceById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Price not found"));
		price.setPriceId(id);
		Price price3 = prices.createPrice(price);
		
		return ResponseEntity.ok(price3);
	}
}
