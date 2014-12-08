package model;
import java.util.ArrayList;
import org.springframework.data.annotation.Id;

/*
 * @abstract: This is bill bean class
 * 
 */
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bills")
public class Bill
{
	@Id
	private String billRef;
	private String shopId;
	private double totalBillAmount;
	private ArrayList<Product> products;
	private String billDate;
	private String billTime;
	private String paymentMode;
	private String billName;
	
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
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
	public double getTotalBillAmount() {
		return totalBillAmount;
	}
	public void setTotalBillAmount(double totalBillAmount) {
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