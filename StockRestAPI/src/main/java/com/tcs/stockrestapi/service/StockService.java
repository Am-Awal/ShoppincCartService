package com.tcs.stockrestapi.service;

import java.util.List;
import java.util.Optional;

import com.tcs.stockrestapi.model.Stock;



public interface StockService {
	
	public Stock createStock(Stock stock);
	public Optional<Stock> getStockById(int id);
	public void deleteStock(int id);
	public Optional<List<Stock>> getStocks();
	public Optional<List<Stock>> getStockByLocation(String loaction);
	public Optional<Stock> getByProductId(int id);
}
