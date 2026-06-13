package com.example.myapp.dto;

public class ReviewRequest {
    private Integer cakeId;
    private String comment;
    private Integer rating;

    public Integer getCakeId() { return cakeId; }
    public void setCakeId(Integer cakeId) { this.cakeId = cakeId; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
}
