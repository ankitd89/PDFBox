import model.Users;
import org.bson.types.ObjectId;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import config.MongoConfigJava;

@RestController
public class PDFBoxController {
	DropboxUtility dropbox=new DropboxUtility();
	ItextUtility utility = new ItextUtility();
	ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfigJava.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
    
 
    @RequestMapping(value="/dropbox/upload",method=RequestMethod.POST)
    public String RESTUpload() {
       try{
    	dropbox.uploadFile();
       }catch(Exception e){}
    	return "check your dropbox!!";
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
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public Users hello(@RequestBody Users user) {
	    user.setUser_id("u-" + new ObjectId());
	    mongoOperation.save(user);
	    return user;
	}

    
}