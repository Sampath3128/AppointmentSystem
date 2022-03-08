package com.team7.appointmentsystem.models.BusinessDetails;

public class OtherBusinesses {
    public long businessId;
    public String businessName;
    public String address;
    public String image;
    public int price;
    public int ratings;
    public String status;

    public OtherBusinesses() {
    }



    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public OtherBusinesses(long businessId, String businessName, String address, String image, int price, int ratings, String status) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.address = address;
        this.image = image;
        this.price = price;
        this.ratings = ratings;
        this.status = status;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
