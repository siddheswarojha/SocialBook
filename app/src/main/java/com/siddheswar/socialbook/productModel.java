package com.siddheswar.socialbook;



public class productModel {
    String data;
    int like;
    public productModel() {

    }

    public productModel(String data, int like) {
        this.data = data;
        this.like = like;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
    public void setLike(int like) {
        this.like = like;
    }


    public int getLike() {
        return like;
    }






}
