package com.xfour.bean;

public class PropertyValue {

	private int id;
	private String value;//属性值
	private Property property;//与property的多对一关系
	private Product product;//与product的多对一关系
	
	public PropertyValue() {
		
	}

	public PropertyValue(int id, String value, Property property, Product product) {
		this.id = id;
		this.value = value;
		this.property = property;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public Property getProperty() {
		return property;
	}

	public Product getProduct() {
		return product;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "PropertyValue [id=" + id + ", value=" + value + ", property=" + property + ", product=" + product + "]";
	}
	
}
