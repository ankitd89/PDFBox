import org.springframework.web.bind.annotation.*;

@RestController
public class Auth {

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
    	 /** The resulting PDF. */
	     String PDF = "revenuereport.pdf";
	    /** A possible resulting after parsing the PDF. */
	     String TEXT1 = "result1.txt";
	    /** A possible resulting after parsing the PDF. */
	     String TEXT2 = "result2.txt";
	    try{
    	example.parsePdf(PDF, TEXT1);
        example.extractText(PDF, TEXT2);
	    }catch(Exception e){}
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