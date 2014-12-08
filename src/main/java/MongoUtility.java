import java.util.ArrayList;
import java.util.List;

import model.Bill;
import model.Users;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;

import config.MongoConfigJava;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

public class MongoUtility{
	private String BILLS_COLLECTION = "users";
	
	public void saveBill(String currentUser, Bill b)
	{
		ApplicationContext ctx =  new AnnotationConfigApplicationContext(MongoConfigJava.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		List<Users> bills = new ArrayList<Users>();
		try{
			Query q = new Query();
			q.addCriteria(Criteria.where("_id").is(currentUser));
			System.out.println("current user : " + currentUser);
			Users user = mongoOperation.findOne(q, Users.class);
			user.getBills().add(b);
			mongoOperation.save(user, BILLS_COLLECTION);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<String> getBillsForAmountWithCondition(double amount, String condition)
	{
		List<String> billNames = new ArrayList<String>();
		List<Bill> bills = new ArrayList<Bill>();
		
		ApplicationContext ctx =  new AnnotationConfigApplicationContext(MongoConfigJava.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		try
		{
			  switch(condition)
				{
					case "<": {
						Query q = new Query();
						q.addCriteria(Criteria.where("totalBillAmount").lt(amount));
						bills = mongoOperation.find(q, Bill.class);
					}
					break;
				
					case ">": {
						Query q = new Query();
						q.addCriteria(Criteria.where("totalBillAmount").gt(amount));
						bills = mongoOperation.find(q, Bill.class);
					}
					break;
					
					case "<=": {
						Query q = new Query();
						q.addCriteria(Criteria.where("totalBillAmount").lte(amount));
						bills = mongoOperation.find(q, Bill.class);
					}
					break;
					
					case ">=": {
						Query q = new Query();
						q.addCriteria(Criteria.where("totalBillAmount").gte(amount));
						bills = mongoOperation.find(q, Bill.class);
					}
					break;
					
					case "=": {
						Bill bill =  (Bill) mongoOperation.findOne(new Query(Criteria.where("totalBillAmount").is(amount)), Bill.class, "bills");
						bills.add(bill);
					}
					break;
					
					default: {
						System.out.println("Invalid operator, only <,>,<=,>=,= are allowed");
					}
					break;
				}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		for(int i = 0; i < bills.size(); i++)
			billNames.add(bills.get(i).getBillRef());
		
		return billNames;
	}
	
	public List<String> getNumberOfBillsForDate(String date)
	{
		List<String> billNames = new ArrayList<String>();
		List<Bill> bills = new ArrayList<Bill>();	
		
		ApplicationContext ctx =  new AnnotationConfigApplicationContext(MongoConfigJava.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		
		try
		{
			Query q = new Query();
			q.addCriteria(Criteria.where("billDate").is(date));
			bills = mongoOperation.find(q, Bill.class);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		for(int i = 0; i < bills.size(); i++)
			billNames.add(bills.get(i).getBillRef());
		
		return billNames;
	}
	
	public double getEarningsForDate(String date)
	{
		List<String> billNames = new ArrayList<String>();
		List<Bill> bills = new ArrayList<Bill>();	
		
		ApplicationContext ctx =  new AnnotationConfigApplicationContext(MongoConfigJava.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		double amount=0;
		try{
		Bill bill =  (Bill) mongoOperation.findOne(new Query(Criteria.where("billDate").is(date)), Bill.class, "bills");
		amount+= bill.getTotalBillAmount();
		}catch(Exception e){
			e.printStackTrace();
		}
		return amount;
	}
	
	public double getEarningsForPaymentType(String type){
		List<Bill> bills = new ArrayList<Bill>();		
		ApplicationContext ctx =  new AnnotationConfigApplicationContext(MongoConfigJava.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		double amount=0;
		try{
		Bill bill =  (Bill) mongoOperation.findOne(new Query(Criteria.where("paymentMode").is(type)), Bill.class, "bills");
		amount+= bill.getTotalBillAmount();
		}catch(Exception e){
			e.printStackTrace();
		}
		return amount;
	}
}