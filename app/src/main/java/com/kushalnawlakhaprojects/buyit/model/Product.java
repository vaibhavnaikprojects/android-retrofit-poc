package com.kushalnawlakhaprojects.buyit.model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

/**
 * Created by vanaik on 2/7/2017.
 */
public class Product implements Serializable{
    private long productId;
    private String productName;
    private String brandName;
    private String thumbnailImageUrl;
    private String productUrl;
    private String originalPrice;
    private String price;
    private String percentOff;
    private long styleId;
    private long colorId;
    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }
    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }
    public String getProductUrl() {
        return productUrl;
    }
    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
    public String getOriginalPrice() {
        return originalPrice;
    }
    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getPercentOff() {
        return percentOff;
    }
    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }
    public long getStyleId() {
        return styleId;
    }
    public void setStyleId(long styleId) {
        this.styleId = styleId;
    }

    public long getColorId() {
        return colorId;
    }

    public void setColorId(long colorId) {
        this.colorId = colorId;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}
