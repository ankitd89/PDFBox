import model.Bill;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class MongoController{
	MongoUtility mongo= new MongoUtility();
	
	@RequestMapping(value="/getEarningsUponPaymentType/{type}", method= RequestMethod.GET)
	@ResponseBody
	public double getEarningOnType(@PathVariable("type") String type){
		System.out.println("In rest getEarningOnType");
		return mongo.getEarningsForPaymentType(type);	
	}
	
	@RequestMapping(value ="/getEarningsForDate/{date}", method=RequestMethod.GET)
	@ResponseBody
	public double getEarningsForDate(@PathVariable("date") String date){
		System.out.println("In rest getEarning for date");
		return mongo.getEarningsForDate(date);
	}
	
	@RequestMapping(value ="/getBillsForDate/{date}", method=RequestMethod.GET)
	@ResponseBody
	public int getBillsForDate(@PathVariable("date") String date){
		return mongo.getNumberOfBillsForDate(date);
	}
	
	@RequestMapping(value ="/getBillsOnCondition/{condition}/{amt}", method=RequestMethod.GET)
	@ResponseBody
	public List<Bill> getEarningsForDate(@PathVariable("condition") String cnd, @PathVariable("amt") double amount){
		System.out.println("In rest getBillsOnCondition");
		return mongo.getBillsForAmountWithCondition(amount, cnd);
	}
}