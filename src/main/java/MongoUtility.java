import java.util.ArrayList;
import java.util.List;

import model.Bill;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;

import config.MongoConfigJava;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

public class MongoUtility{
	private String BILLS_COLLECTION = "bills";

	public void saveBill(Bill b)
	{
		ApplicationContext ctx =  new AnnotationConfigApplicationContext(MongoConfigJava.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		try{
			//MongoOperations operation = mongoTemplate();
			mongoOperation.save(b, BILLS_COLLECTION);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Bill> getBillsForAmountWithCondition(double amount, String condition)
	{
		List<Bill> bills = new ArrayList<Bill>();
		
		ApplicationContext ctx =  new AnnotationConfigApplicationContext(MongoConfigJava.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		try
		{
			//MongoOperations operation = mongoTemplate();
//			@SuppressWarnings("deprecation") 
//			Mongo mongo = new Mongo("localhost", 27017);
//			DB db = mongo.getDB("PDFBox");
		 
			  // get a single collection
//			  DBCollection collection = db.getCollection("bills");
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
		return bills;
	}
	
	public int getNumberOfBillsForDate(String date){
		List<Bill> bills = new ArrayList<Bill>();		
		ApplicationContext ctx =  new AnnotationConfigApplicationContext(MongoConfigJava.class);
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		try{
		Bill bill =  (Bill) mongoOperation.findOne(new Query(Criteria.where("billDate").is(date)), Bill.class, "bills");
		bills.add(bill);
		}catch(Exception e){
			e.printStackTrace();
		}
		int count=bills.size();
		return count;
	}
	
	public double getEarningsForDate(String date){
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