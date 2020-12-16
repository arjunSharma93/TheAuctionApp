package model;

public class Product {
	
	private String productName;
	private String productDescription;
	private String minimumBid;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	public String getMinimumBid() {
		return minimumBid;
	}
	public void setMinimumBid(String minimumBid) {
		this.minimumBid = minimumBid;
	}
	
	@Override
	public String toString() {
		return("name= "+getProductName()+" description= "+getProductDescription()+" minimumBid= "+getMinimumBid());
	}
	
}
