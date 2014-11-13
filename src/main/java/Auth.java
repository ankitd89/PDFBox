import org.springframework.web.bind.annotation.*;

@RestController
public class Auth {

    DropboxUtility dropbox=new DropboxUtility();
    
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
}