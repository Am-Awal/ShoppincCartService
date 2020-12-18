package com.tcs.reviewrestapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.tcs.reviewrestapi.exception.ResourceNotFoundException;
import com.tcs.reviewrestapi.model.Review;
import com.tcs.reviewrestapi.service.ReviewService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/review")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;

	@GetMapping("/getreviews")
	public List<Review> getReviews()
	{
		return reviewService.getReviews().get();
	}
	
/*	@GetMapping("/{id}")
	public ResponseEntity<Review> getReviewById(@PathVariable("id") int id) throws ResourceNotFoundException
	{
		Review review = reviewService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Review not found"));
		return ResponseEntity.ok().body(review);
	}*/
	
	@GetMapping("/reviewid/{id}")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Review> getReviewByProductId(@PathVariable("id") int id) throws ResourceNotFoundException
	{
		Review review = reviewService.findByProductId(id).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
		return ResponseEntity.ok().body(review);
	}
	
	@DeleteMapping("/delete/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public Map<String, Boolean> deleteReviewById(@PathVariable int id) throws ResourceNotFoundException
	{
		Review review = reviewService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Review not found"));
		reviewService.deleteReview(id);
		HashMap<String, Boolean> hashMap = new HashMap<>();
		hashMap.put("deleted", Boolean.TRUE);
		return hashMap;
	}
	
	@PostMapping("/create")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createReview(@RequestBody Review review,UriComponentsBuilder uriComponentsBuilder,HttpServletRequest request)
	{
		Review review2 = reviewService.addReview(review);
		UriComponents uriComponents = uriComponentsBuilder
				.path(request.getRequestURI()+"/{id}")
				.buildAndExpand(review2.getId());
		return ResponseEntity.created(uriComponents.toUri()).body(review2);
	}
	
	@PutMapping("/{id}")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Review> updateReview(@PathVariable("id") Integer id,
			@Valid @RequestBody Review review ) throws ResourceNotFoundException {
		Review review2 = reviewService.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Review not found"));
		review.setId(id);
		Review review3 =reviewService.addReview(review);		
		return ResponseEntity.ok(review3);
	}
}