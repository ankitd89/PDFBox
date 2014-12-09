package model;

import java.util.ArrayList;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Users {
	@Id
	@NotEmpty
	private String email;
	@NotEmpty
	private String accessToken;
	private ArrayList<Bill> bills=new ArrayList<Bill>();
	public ArrayList<Bill> getBills() {
		return bills;
	}
	public void setBills(ArrayList<Bill> bills) {
		this.bills = bills;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
