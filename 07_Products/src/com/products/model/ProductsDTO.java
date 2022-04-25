package com.products.model;

public class ProductsDTO {
	private int pnum;
	private String category_fk;
	private String product_name;
	private String ep_code_fk;
	private int input_price;
	private int output_price;
	private int trans_cost;
	private int mileage;
	private String company;
	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getCategory_fk() {
		return category_fk;
	}
	public void setCategory_fk(String category_fk) {
		this.category_fk = category_fk;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getEp_code_fk() {
		return ep_code_fk;
	}
	public void setEp_code_fk(String ep_code_fk) {
		this.ep_code_fk = ep_code_fk;
	}
	public int getInput_price() {
		return input_price;
	}
	public void setInput_price(int input_price) {
		this.input_price = input_price;
	}
	public int getOutput_price() {
		return output_price;
	}
	public void setOutput_price(int output_price) {
		this.output_price = output_price;
	}
	public int getTrans_cost() {
		return trans_cost;
	}
	public void setTrans_cost(int trans_cost) {
		this.trans_cost = trans_cost;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
}
