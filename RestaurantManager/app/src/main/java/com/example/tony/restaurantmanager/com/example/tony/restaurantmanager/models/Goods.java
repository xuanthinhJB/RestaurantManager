package com.example.tony.restaurantmanager.com.example.tony.restaurantmanager.models;

import android.graphics.Bitmap;

/**
 * Created by phanx on 02/01/2018.
 */

public class Goods {
    private byte[] goodsImage;
    private String goodsName;
    private Float goodsQuantity;
    private String goodsUnit;
    private long goodsPrice;
    private String goodsReceiptedDate;
    private String goodsExpiredDate;

    public Goods(byte[] goodsImage, String goodsName, Float goodsQuantity, String goodsUnit, long goodsPrice, String goodsReceiptedDate, String goodsExpiredDate) {
        this.goodsImage = goodsImage;
        this.goodsName = goodsName;
        this.goodsQuantity = goodsQuantity;
        this.goodsUnit = goodsUnit;
        this.goodsPrice = goodsPrice;
        this.goodsReceiptedDate = goodsReceiptedDate;
        this.goodsExpiredDate = goodsExpiredDate;
    }

    public Goods() {
    }

    public byte[] getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(byte[] goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Float getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(Float goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public long getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(long goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsReceiptedDate() {
        return goodsReceiptedDate;
    }

    public void setGoodsReceiptedDate(String goodsReceiptedDate) {
        this.goodsReceiptedDate = goodsReceiptedDate;
    }

    public String getGoodsExpiredDate() {
        return goodsExpiredDate;
    }

    public void setGoodsExpiredDate(String goodsExpiredDate) {
        this.goodsExpiredDate = goodsExpiredDate;
    }
}
