package com.xfour.bean;

import java.util.Date;
import java.util.List;

public class Product {

	private int id;
	private String name;//产品名称
	private String subTitle;//产品小标题
	private float orignalPrice;//原价格
	private float discountPrice;//折扣价格
	private int stock;//库存数量
	private Date createDate;//创建时间
	
	private Category category;//产品所属分类
	private List<ProductImage> productSingleImages;//产品图片,一般是五个
	private List<ProductImage> productDetailImages;//产品详情图片
	private int firstProductImage;//作为产品默认图片,用于保存默认图片id
	
	//此处没有写成与review的一对多关系，而是通过其他方法解决
	private int reviewCount;//评论数量 
	private int saleCount;//售卖数量
	
	private int rvid;//评论id
	
	public Product() {
		
	}

	public Product(int id, String name, String subTitle, float orignalPrice, float discountPrice, int stock,
			Date createDate, Category category, List<ProductImage> productSingleImages,
			List<ProductImage> productDetailImages, int firstProductImage, int reviewCount, int saleCount, int rvid) {
		this.id = id;
		this.name = name;
		this.subTitle = subTitle;
		this.orignalPrice = orignalPrice;
		this.discountPrice = discountPrice;
		this.stock = stock;
		this.createDate = createDate;
		this.category = category;
		this.productSingleImages = productSingleImages;
		this.productDetailImages = productDetailImages;
		this.firstProductImage = firstProductImage;
		this.reviewCount = reviewCount;
		this.saleCount = saleCount;
		this.rvid = rvid;
	}

	public int getRvid() {
		return rvid;
	}

	public void setRvid(int rvid) {
		this.rvid = rvid;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public float getOrignalPrice() {
		return orignalPrice;
	}

	public float getDiscountPrice() {
		return discountPrice;
	}

	public int getStock() {
		return stock;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Category getCategory() {
		return category;
	}

	public List<ProductImage> getProductSingleImages() {
		return productSingleImages;
	}

	public List<ProductImage> getProductDetailImages() {
		return productDetailImages;
	}

	public int getFirstProductImage() {
		return firstProductImage;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public int getSaleCount() {
		return saleCount;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public void setOrignalPrice(float orignalPrice) {
		this.orignalPrice = orignalPrice;
	}

	public void setDiscountPrice(float discountPrice) {
		this.discountPrice = discountPrice;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setProductSingleImages(List<ProductImage> productSingleImages) {
		this.productSingleImages = productSingleImages;
	}

	public void setProductDetailImages(List<ProductImage> productDetailImages) {
		this.productDetailImages = productDetailImages;
	}

	public void setFirstProductImage(int firstProductImage) {
		this.firstProductImage = firstProductImage;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", subTitle=" + subTitle + ", orignalPrice=" + orignalPrice
				+ ", discountPrice=" + discountPrice + ", stock=" + stock + ", createDate=" + createDate + ", category="
				+ category + ", productSingleImages=" + productSingleImages + ", productDetailImages="
				+ productDetailImages + ", firstProductImage=" + firstProductImage + ", reviewCount=" + reviewCount
				+ ", saleCount=" + saleCount + ", rvid=" + rvid + "]";
	}
	
}
