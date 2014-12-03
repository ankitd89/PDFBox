package config;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
=======
import model.Bill;

>>>>>>> 053ae9761e9a4a7a17a054e846e6e161fe2b1d67
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
 
@Configuration
public class MongoConfigJava {
 
	private String BILLS_COLLECTION = "bills";
	
	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(new MongoClientURI("mongodb://Pdfbox:Pdfbox@ds051640.mongolab.com:51640/pdfbox")), "pdfbox");
	}
 
	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
 
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
 
		return mongoTemplate;
 
	} 
	
	public void saveBill(Bill b)
	{
		try{
			MongoOperations operation = mongoTemplate();
			operation.save(b, BILLS_COLLECTION);
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
			MongoOperations operation = mongoTemplate();
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
						Bill bill =  (Bill) operation.findOne(new Query(Criteria.where("totalBillAmount").is(amount)), Bill.class, "bills");
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
	
}