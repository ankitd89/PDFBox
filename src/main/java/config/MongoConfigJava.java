package config;

import model.Bill;
import model.Product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.MongoClient;
 
@Configuration
public class MongoConfigJava {
 
	private String BILLS_COLLECTION = "bills";
	
	public @Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient(), "PDFBox");
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
			
		}
	}
	
}