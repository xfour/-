package com.xfour.bean;

public class Property {
	
	private int id;
	private String name;//属性名称
	private Category category;//该属性所属的分类
	
	public Property() {
		
	}

	public Property(String name, Category category, int id) {
		this.name = name;
		this.category = category;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Property [name=" + name + ", category=" + category + ", id=" + id + "]";
	}
	
}
