package pdfbox;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Bill;
import model.Users;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.dropbox.core.DbxException;
import config.MongoConfigJava;

@RestController
public class PDFBoxController {
	
	MongoUtility mongo= new MongoUtility();
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfigJava.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
 
    @RequestMapping(value="/dropbox/{email}/upload",method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@PathVariable("email") String email, MultipartHttpServletRequest request, HttpServletResponse response){
    	Iterator<String> itr =  request.getFileNames();
        MultipartFile file = request.getFile(itr.next());       
    	if (!file.isEmpty()) {
			try 
			{
					ItextUtility utility = new ItextUtility();
					byte[] bytes = file.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File("receipt.pdf")));
					stream.write(bytes);
					stream.close();
					utility.convertPdfToText();
					Bill bill = new Bill();
					bill = utility.buildMetaDataForFile();
					mongo.saveBill(email, bill);
					DropboxUtility dropboxUtility = createDropBoxUtility(email);
					dropboxUtility.uploadFile(bill.getBillRef() + ".pdf");
					return "upload success!!";
			} 
			catch (Exception e) {
					return "You failed to upload " + file.getOriginalFilename()
							+ " => " + e.getMessage();
				}
		} 
    	else {
			return "You failed to upload " + file.getOriginalFilename()
					+ " because the file was empty.";
		}
    }
    
    @RequestMapping(value="/dropbox/{email}/download/{file}",method=RequestMethod.GET)
    public String RESTDownload(@PathVariable ("file") String fileName, @PathVariable("email") String email) {
    	String metadata="";
       try{
    	   DropboxUtility dropboxUtility = createDropBoxUtility(email);
    	   metadata=dropboxUtility.downloadFile(fileName);
       }catch(Exception e){}
    	return metadata;
    }
    
    @RequestMapping(value="/dropbox/{email}/delete/{filename}",method=RequestMethod.POST)
    public String RESTDelete(@PathVariable("email") String email, @PathVariable("filename") String filename) 
    {
       try{
    	   	Query searchUserQuery = new Query(Criteria.where("_id").is(email));
	   	    Users savedUser = mongoOperation.findOne(searchUserQuery, Users.class);
	   		DropboxUtility dropboxUtility = new DropboxUtility();
	   	 	dropboxUtility.setAccessToken(savedUser.getAccessToken());
	   	 	dropboxUtility.login();
	   	 	dropboxUtility.deleteFile(filename + ".pdf");
		   	int increment = 0;
		   	int listSize = savedUser.getBills().size();
	   	 	while(increment<listSize) {
   	 	        Bill bill = savedUser.getBills().get(increment);
   	 	        if (bill.getBillRef().equals(filename)) {
   	 	          savedUser.getBills().remove(increment);
   	 	          break;
   	 	        }
   	 	        increment = increment+1;
	   	 	}
	   	 	mongoOperation.save(savedUser);
   	 		return "success";
	   	 	
       }
       catch(Exception e){
    	   return "failure";
       }
    	
    }
    
    @RequestMapping(value="/dropbox/{email}/files",method=RequestMethod.GET)
    public String RESTListFiles(@PathVariable("email") String email) 
    {
    	
    	String files="";
       try{
    	   DropboxUtility dropboxUtility = createDropBoxUtility(email);
    	   files=dropboxUtility.listFiles();
       }catch(Exception e){}
    	return files;
    }
    
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/signin/{email:.+}", method=RequestMethod.GET)
	public String signup(@PathVariable("email") String email) {
		Query searchUserQuery = new Query(Criteria.where("_id").is(email));
		Users savedUser = mongoOperation.findOne(searchUserQuery, Users.class);
		 if(savedUser != null)
			 return "success" ;
		 else
			 return "failure";
	}
	
	//This function will get email from Dropbox account api and save both email and access token in MongoDB
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value="/accesstokens", method=RequestMethod.POST)
	public String saveAccessTokens(@RequestParam("access_token") String access_token) {
		
		 String email="";
		 try {
			 	DropboxUtility dropboxUtility = new DropboxUtility();
			 	dropboxUtility.setAccessToken(access_token);
			 	dropboxUtility.login();
			 	Users addUser = new Users();
			 	addUser.setAccessToken(access_token);
			 	Account acc = dropboxUtility.getAccountInfo();
			 	email = acc.getEmail();
			 	addUser.setEmail(email);
			 	mongoOperation.save(addUser);
		}
		catch (DbxException e) {
			e.printStackTrace();
		}
		return email;
	}
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	@ResponseBody
	public String logout(){
//		currentUser="";
//		DropboxUtility.accessToken=null;
		return "success";
	}

//	@RequestMapping(value="/restHome", method=RequestMethod.GET)
//	@ResponseBody
//	public String checkUser(){
//		System.out.println(currentUser + DropboxUtility.accessToken);
//		if((currentUser==null || currentUser.equals(""))&&(DropboxUtility.accessToken==null || DropboxUtility.accessToken.equals("")))
//			return "login";
//		else
//			return "home";
//	}
	
	@RequestMapping(value ="/dropbox/{email}/getBillsForDate/{date}", method=RequestMethod.GET)
	@ResponseBody
	public String getBillsForDate(@PathVariable("date") String date, @PathVariable("email") String email){
		return mongo.getNumberOfBillsForDate(date, email);
	}
	
	@RequestMapping(value ="/getEarningsForDate/{date}", method=RequestMethod.GET)
	@ResponseBody
	public double getEarningsForDate(@PathVariable("date") String date){
		return mongo.getEarningsForDate(date);
	}
	
	@RequestMapping(value="dropbox/{email}/getEarningsUponPaymentType/{type}", method= RequestMethod.GET)
	@ResponseBody
	public String getEarningOnType(@PathVariable("email")String email, @PathVariable("type") String type){
		return mongo.getEarningsForPaymentType(type,email);	
	}
	
	@RequestMapping(value ="/dropbox/{email}/getBillsOnCondition/{condition}/{amt:.+}", method=RequestMethod.GET)
	@ResponseBody
	public String getEarningsForDate(@PathVariable("email") String email, @PathVariable("condition") String cnd, @PathVariable("amt") double amount){
		return mongo.getBillsForAmountWithCondition(amount, cnd, email);
	}
	
	@RequestMapping(value="dropbox/{email}/getMetaDataFroBill/{billRef}", method = RequestMethod.GET)
	public String getMetaDataForClickedBill(@PathVariable("email") String email, @PathVariable("billRef") String ref)
	{
		System.out.println("billRef:"+ref);
		return mongo.getMetaDataForBill(ref, email);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value= "/health", method=RequestMethod.GET)
	public String getHealth()
	{
		return "health";
	}
	
	public DropboxUtility createDropBoxUtility(String email)
	{
		Query searchUserQuery = new Query(Criteria.where("_id").is(email));
	    Users savedUser = mongoOperation.findOne(searchUserQuery, Users.class);
		DropboxUtility dropboxUtility = new DropboxUtility();
	 	dropboxUtility.setAccessToken(savedUser.getAccessToken());
	 	dropboxUtility.login();
	 	return dropboxUtility;
	}
}