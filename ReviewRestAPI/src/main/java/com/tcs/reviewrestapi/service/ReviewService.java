package com.tcs.reviewrestapi.service;

import java.util.List;
import java.util.Optional;

import com.tcs.reviewrestapi.model.Review;

public interface ReviewService {
	public Review addReview(Review review);
	public String updateReview(Review review);
	public void deleteReview(int id);
	public Optional<Review> findById(int id);
	public Optional<java.util.List<Review>> getReviews();
	public Optional<Review> findByProductId(int id);
	
	public void registerReview(Review review);
}