package com.pradeep.reviewms.Review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServImplementation implements ReviewService{

    @Autowired
    ReviewRepo reviewRepo;

    public ReviewServImplementation(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    public ReviewServImplementation() {
    }

    @Override
    public List<Review> getAllReview(Long companyId) {

        List<Review> reviews = reviewRepo.findByCompanyId(companyId);
        return reviews;


    }

    @Override
    public List<Review> getTotalReview() {
        return reviewRepo.findAll();
    }

    @Override
    public boolean createReview(Long companyId, Review review) {

        if(companyId != null && review != null){
            review.setCompanyId(companyId);
            reviewRepo.save(review);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateReview(Review review, Long reviewId) {

        Review reviewOld = reviewRepo.findById(reviewId).orElse(null);

       try{
           if(reviewOld != null){
               //set company to review and then save it
               reviewOld.setRating(review.getRating());
               reviewOld.setDescription(review.getDescription());
               reviewOld.setTitle(review.getTitle());
               reviewOld.setCompanyId(review.getCompanyId());
               reviewRepo.save(reviewOld);
               return true;
           }
       }catch(Exception e){
           return false;
       }

        return false;
    }

    @Override
    public Review getReview(Long reviewId) {

        Review review = reviewRepo.findById(reviewId).orElse(null);

       if(review != null){
           return review;
       }
        return null;
    }

    @Override
    public boolean deleteReviewByCompanyIdByReviewId(Long reviewId) {

        //find all review of a company
        Review review = reviewRepo.findById(reviewId).orElse(null);

        if(review != null){
          reviewRepo.deleteById(reviewId);
          return true;
       }
        return false;
    }


}
