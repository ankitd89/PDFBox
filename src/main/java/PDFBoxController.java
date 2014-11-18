import org.springframework.web.bind.annotation.*;

@RestController
public class PDFBoxController {

    DropboxUtility dropbox=new DropboxUtility();
    ItextUtility example = new ItextUtility();
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
    	ItextUtility utility = new ItextUtility();
    	utility.convertPdfToText("", ""); //TODO: Pass correct parameters after serialization and deserialization
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
}