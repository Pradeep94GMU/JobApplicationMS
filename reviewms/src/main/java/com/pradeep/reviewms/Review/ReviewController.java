package com.pradeep.reviewms.Review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping //receiving via req param
    public ResponseEntity<List<Review>> getReviewByCompanyId(@RequestParam Long companyId){
        List<Review> reviewList = reviewService.getAllReview(companyId);
        if(reviewList!= null){
            return new ResponseEntity<>(reviewList, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review){
        boolean createdReview = reviewService.createReview(companyId, review);

        if(createdReview){
            return new ResponseEntity<>("New Review is Added", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@RequestBody Review review, @PathVariable Long reviewId){
        boolean updatedReview = reviewService.updateReview(review, reviewId);
        if(updatedReview){
            return new ResponseEntity<>("Review is updated..", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){

        Review review = reviewService.getReview(reviewId);
        if(review != null){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isDeleted = reviewService.deleteReviewByCompanyIdByReviewId(reviewId);

        if(isDeleted){
            return new ResponseEntity<>("Review has been deleted", HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Review>> totalReviews(){
        List<Review> totalReview = reviewService.getTotalReview();

        if(totalReview!= null){
            return new ResponseEntity<>(totalReview, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
