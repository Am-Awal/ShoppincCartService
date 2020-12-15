package com.tcs.stockrestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.stockrestapi.model.Stock;
import com.tcs.stockrestapi.repository.StockRepository;



@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;
	
	public Stock createStock(Stock stock) {
		// TODO Auto-generated method stub
		Stock stock2 = null;
		try {
			stock2 = stockRepository.save(stock);
			return stock2;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}

	public Optional<Stock> getStockById(int id) {
		// TODO Auto-generated method stub
		return stockRepository.findById(id);
	}

	public void deleteStock(int id) {
		// TODO Auto-generated method stub
		stockRepository.deleteById(id);

	}

	public Optional<List<Stock>> getStocks() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(stockRepository.findAll());
	}

	public Optional<List<Stock>> getStockByLocation(String loactionName) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(stockRepository.findByLocation(loactionName));
	}

	@Override
	public Optional<Stock> getByProductId(int id) {
		// TODO Auto-generated method stub
		return stockRepository.getByProductId(id);
	}

}
