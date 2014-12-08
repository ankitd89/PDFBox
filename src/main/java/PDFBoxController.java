import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Users;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.dropbox.core.DbxException;
import config.MongoConfigJava;

@RestController
public class PDFBoxController {
	DropboxUtility dropbox=new DropboxUtility();
	ItextUtility utility = new ItextUtility();
	MongoUtility mongo= new MongoUtility();
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfigJava.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
    static String currentUser;
 
    @RequestMapping(value="/dropbox/upload",method=RequestMethod.POST)
    	 public @ResponseBody String handleFileUpload(MultipartHttpServletRequest request, HttpServletResponse response){
    	Iterator<String> itr =  request.getFileNames();
    	 
         MultipartFile file = request.getFile(itr.next());       
    	if (!file.isEmpty()) {
    	            try {
    	            	
    	                byte[] bytes = file.getBytes();
    	                BufferedOutputStream stream =
    	                        new BufferedOutputStream(new FileOutputStream(new File("receipt.pdf")));
    	                stream.write(bytes);
    	                stream.close();
    	                utility.convertPdfToText();
    	            	Bill bill=new Bill();
    	            	bill=utility.buildMetaDataForFile();
    	            	mongo.saveBill(currentUser, bill);	                    
    	                dropbox.uploadFile(bill.getBillRef()+".pdf");
    	                return "upload success!!";
    	                //TODO:refresh file list here
    	            } catch (Exception e) {
    	                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
    	            }
    	        } else {
    	            return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
    	        }
    }
    

    @RequestMapping(value="/dropbox/download/{file}",method=RequestMethod.GET)
    public String RESTDownload(@PathVariable ("file") String fileName) {
    	String metadata="";
       try{
    	metadata=dropbox.downloadFile(fileName);
    	System.out.println("in restdownload");
       }catch(Exception e){}
    	return metadata;
    }
    
    @RequestMapping(value="/dropbox/files",method=RequestMethod.GET)
    public String RESTListFiles() 
    {
    	String files="";
       try{
    	files=dropbox.listFiles();
       }catch(Exception e){}
    	return files;
    }
    
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value="/signin/{email}", method=RequestMethod.GET)
	public String signup(@PathVariable("email") String email) {
		email+=".com";
		currentUser=email;
		//TODO: use mongoUtility class for mongodb operations
		Query searchUserQuery = new Query(Criteria.where("_id").is(email));
		 Users savedUser = mongoOperation.findOne(searchUserQuery, Users.class);
		 if(savedUser != null)
		 {
			 DropboxUtility.accessToken = savedUser.getAccessToken();
			 return "success" ;
		 }
		 else
			 return "failure";
	}
	
	//This function will get email from Dropbox account api and save both email and access token in MongoDB
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value="/accesstokens", method=RequestMethod.POST)
	public void saveAccessTokens(@RequestParam("access_token") String access_token) {
		 DropboxUtility.accessToken = access_token;
		 try {
			Account acc = dropbox.getAccountInfo();
			//TODO: use mongoUtility class for mongodb operations
			currentUser=acc.getEmail();
			Users addUser = new Users(acc.getEmail(), access_token);
			mongoOperation.save(addUser);
		}
		catch (DbxException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getBillCount/{date}", method=RequestMethod.GET)
	public List<String> getBillCount(@PathVariable("date") String date) {
		return mongo.getNumberOfBillsForDate(date);
	}
	
	@RequestMapping(value="/getTotalEarnings/{date}", method=RequestMethod.GET)
	public double getTotalEarnings(@PathVariable("date") String date) {
		double amount= mongo.getEarningsForDate(date);
		return amount;
	}
	
	@RequestMapping(value="/getEarningsForMode/{mode}", method=RequestMethod.GET)
	public double getModeEarnings(@PathVariable("mode") String mode) {
		double amount= mongo.getEarningsForPaymentType(mode);
		return amount;
	}
}