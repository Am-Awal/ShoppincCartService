package com.tcs.productcatrestapi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tcs.productcatrestapi.model.Product;
import com.tcs.productcatrestapi.repository.ProductRepository;






@Service
public class ProductServiceImpl implements ProductService {
// applying singleton 
	// task for u
	
	
	@Autowired
	ProductRepository productRepository;
	
	private String PRICE_URL = "http://localhost:9080/api/price";
	private String REVIEW_URL ="http://localhost:9070/api/review";
	private String STOCK_URL = "http://localhost:9060/api/stock";
	
	@Autowired RestTemplate restTemplate;
	
	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub			    	
		
		Product product2 = null;
		try {
			product2 = productRepository.save(product);
			
			
			int prodId = product2.getProductId();
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			JSONObject priceJSONObject = new JSONObject();
			priceJSONObject.put("productId", prodId);
			priceJSONObject.put("price_value", 0.0f);
			
			JSONObject stockJSONObject = new JSONObject();
			stockJSONObject.put("productId", prodId);
			stockJSONObject.put("quantity", 0);
			stockJSONObject.put("location", "NA");
			
			HttpEntity<String> priceRequest = 
				      new HttpEntity<String>(priceJSONObject.toString(), headers);
			HttpEntity<String> stockRequest = 
				      new HttpEntity<String>(stockJSONObject.toString(), headers);
			
			String priceResultAsJsonStr = 
				      restTemplate.postForObject(PRICE_URL+"/create", priceRequest, String.class);
			String stockResultAsJsonStr = 
				      restTemplate.postForObject(STOCK_URL+"/create", stockRequest, String.class);
			
			
			return product2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<Product> getProductById(int id) {
		// TODO Auto-generated method stub
		
		
		return productRepository.findById(id);
	}

	@Override
	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
		
		String idStr = Integer.toString(id);
	     
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", idStr);
	     
	    restTemplate.delete(PRICE_URL+"/delete/productid/{id}",  params );
	    restTemplate.delete(STOCK_URL+"/delete/productid/{id}",  params );
	}

	@Override
	public Optional<List<Product>> getProducts() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(productRepository.findAll());
	}

	@Override
	public Optional<List<Product>> getProductsByCategory(String catName) {
		
		// TODO Auto-generated method stub
		return Optional.ofNullable(productRepository.findByCategory(catName));
		//return null;
	}

}
