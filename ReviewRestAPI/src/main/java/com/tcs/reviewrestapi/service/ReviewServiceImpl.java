package com.tcs.reviewrestapi.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.reviewrestapi.model.Review;
import com.tcs.reviewrestapi.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Override
	public Review addReview(Review review) {
		// TODO Auto-generated method stub
		Review review2 = null;
		try
		{
			review2 = reviewRepository.save(review);
			return review2;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String updateReview(Review review) {
		// TODO Auto-generated method stub
		Review review2 = null;
		try
		{
			review2 = reviewRepository.save(review);
			return "success";
		}catch(Exception e)
		{
			e.printStackTrace();
			return "fail";
		}
	}

	@Override
	public void deleteReview(int id) {
		// TODO Auto-generated method stub
		reviewRepository.deleteById(id);
	}

	@Override
	public Optional<Review> findById(int id) {
		// TODO Auto-generated method stub
		return reviewRepository.findById(id);
	}

	@Override
	public Optional<List<Review>> getReviews() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(reviewRepository.findAll());
	}

	@Override
	public Optional<Review> findByProductId(int id) {
		// TODO Auto-generated method stub
		return reviewRepository.findByProductId(id);
	}

	@Override
	public void registerReview(Review review) {
		// TODO Auto-generated method stub
		Review review2 = reviewRepository.save(review);
	}

}