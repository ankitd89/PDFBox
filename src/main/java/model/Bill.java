package model;
import java.util.ArrayList;


/*
 * @abstract: This is bill bean class
 * 
 */
public class Bill
{
	String shopId;
	String totalBillAmount;
	ArrayList<Product> products;
	String billDate;
	String billTime;
	String billRef;
	
	public String getBillRef() {
		return billRef;
	}
	public void setBillRef(String billRef) {
		this.billRef = billRef;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getTotalBillAmount() {
		return totalBillAmount;
	}
	public void setTotalBillAmount(String totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getBillTime() {
		return billTime;
	}
	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}
}