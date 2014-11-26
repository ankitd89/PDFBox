import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import javax.servlet.http.HttpServletResponse;
import model.Account;
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
	DropboxUtility dropbox=new DropboxUtility();
	ItextUtility utility = new ItextUtility();
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfigJava.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
    
 
    @RequestMapping(value="/dropbox/upload",method=RequestMethod.POST)
    	 public @ResponseBody String handleFileUpload(MultipartHttpServletRequest request, HttpServletResponse response){
    	Iterator<String> itr =  request.getFileNames();
    	 
         MultipartFile file = request.getFile(itr.next());       
    	if (!file.isEmpty()) {
    	            try {
    	            	
    	                byte[] bytes = file.getBytes();
    	                BufferedOutputStream stream =
    	                        new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
    	                stream.write(bytes);
    	                stream.close();
    	                System.out.println(file.getOriginalFilename());
    	                dropbox.uploadFile(file.getOriginalFilename());
    	                return "upload success!!";
    	            } catch (Exception e) {
    	                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
    	            }
    	        } else {
    	            return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
    	        }
    }
    
    @RequestMapping(value="/dropbox/upload1",method=RequestMethod.POST)
	 public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
	            @RequestParam("file") MultipartFile file){
	        if (!file.isEmpty()) {
	            try {
	            	
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
	                stream.write(bytes);
	                stream.close();
	                System.out.println(file.getOriginalFilename());
	                dropbox.uploadFile(file.getOriginalFilename());
	                return "You successfully uploaded receipt to dropbox !";
	            } catch (Exception e) {
	                return "You failed to upload " + name + " => " + e.getMessage();
	            }
	        } else {
	            return "You failed to upload " + name + " because the file was empty.";
	        }
}
    @RequestMapping(value="/dropbox/download",method=RequestMethod.GET)
    public String RESTDownload() {
    	String metadata="";
       try{
    	metadata=dropbox.downloadFile();
       }catch(Exception e){}
    	return metadata;
    }
    @RequestMapping(value="/dropbox/Pdftotext",method=RequestMethod.POST)
    public String RestPdfToText() {
    	utility.convertPdfToText("", ""); //TODO: Pass correct parameters after serialization and deserialization
    	utility.buildMetaDataForFile(""); //TODO: Pass actual src
    	return "Pdf converted to Text";
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
			Users addUser = new Users(acc.getEmail(), access_token);
			mongoOperation.save(addUser);
		}
		catch (DbxException e) {
			e.printStackTrace();
		}
	}
	
	
}