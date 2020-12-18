package com.tcs.stockrestapi.controller;

import java.util.HashMap;
import java.util.List;

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

import com.tcs.stockrestapi.exception.ResourceNotFoundException;
import com.tcs.stockrestapi.model.Stock;
import com.tcs.stockrestapi.service.StockService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stock")
public class StockController {
	
	
	@Autowired
	StockService stockService;
	
	@GetMapping("/getallstocks")
	public List<Stock> getStocks(){
		return stockService.getStocks().get();
	}
	
	
	@GetMapping("/id/{id}")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Stock> getProductById(@PathVariable("id") int stockId) 
			throws ResourceNotFoundException {
		
		Stock stock = stockService.getStockById(stockId).orElseThrow(()-> 
		new ResourceNotFoundException("Stock not found"));
		
		return ResponseEntity.ok().body(stock);
	}
	
	
	@PostMapping("/create")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createStock(@RequestBody Stock stock, 
			UriComponentsBuilder uriComponentsBuilder,HttpServletRequest request) {		
		Stock stock2 = stockService.createStock(stock);
		UriComponents uriComponents = uriComponentsBuilder
				.path(request.getRequestURI()+"/{id}")
				.buildAndExpand(stock2.getStockId());
		
		return ResponseEntity.created(uriComponents.toUri()).body(stock2);
		
	}
	
	
	@DeleteMapping("/delete/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public HashMap<String, Boolean> deleteStockById(@PathVariable int id) 
			throws ResourceNotFoundException {
		Stock stock = stockService.getStockById(id).orElseThrow(()-> 
		new ResourceNotFoundException("Stock not found"));
		
		stockService.deleteStock(id);
		HashMap<String, Boolean> hashMap = new HashMap<>();
		hashMap.put("deleted", Boolean.TRUE);
		
		return hashMap;
	}
	
	@DeleteMapping("/delete/productid/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public HashMap<String, Boolean> deleteStockByProductId(@PathVariable int id) 
			throws ResourceNotFoundException {
		Stock stock = stockService.getByProductId(id).orElseThrow(()-> 
		new ResourceNotFoundException("Stock not found"));
		
		stockService.deleteStock(stock.getStockId());
		HashMap<String, Boolean> hashMap = new HashMap<>();
		hashMap.put("deleted", Boolean.TRUE);
		
		return hashMap;
	}
	
	
	
	@PutMapping("/update/{id}")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Stock> updateStock(@PathVariable("id") Integer id,
			@Valid @RequestBody Stock stock) throws ResourceNotFoundException{
	
		Stock stock2 = stockService.getStockById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Stock not found"));
		stock.setStockId(id);
		Stock stock3 = stockService.createStock (stock);
		
		return ResponseEntity.ok(stock3);
	}
	
}
