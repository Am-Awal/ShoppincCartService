package com.tcs.pricerestapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.pricerestapi.model.Price;



@Repository
public interface PriceServiceRepository extends JpaRepository<Price, Long> {

	void deleteByProductId(long productId);

	Optional<Price> findByProductId(long productId);

}
