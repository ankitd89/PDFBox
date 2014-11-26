package config;

import java.util.ArrayList;
import java.util.List;

import model.Bill;

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
		//return new SimpleMongoDbFactory(new MongoClient(), "PDFBox");
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
	
	public List<Bill> getBillsForAmountWithCondition(String amount, String condition)
	{
		List<Bill> bills = new ArrayList<Bill>();
		try
		{
			MongoOperations operation = mongoTemplate();
			@SuppressWarnings("deprecation") 
			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("PDFBox");
		 
			  // get a single collection
			  DBCollection collection = db.getCollection("bills");
			  switch(condition)
				{
					case "<": {
						
					}
					break;
				
					case ">": {

					}
					break;
					
					case "<=": {

					}
					break;
					
					case ">=": {

					}
					break;
					
					case "=": {
						Bill bill =  (Bill) operation.findOne(new Query(Criteria.where("totalBillAmount").is(amount)), Bill.class, "bills");
						bills.add(bill);
					}
					break;
					
					default: {

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