import com.dropbox.core.*;

import java.util.Locale;
import java.io.*;

public class DropboxUtility {
	final String APP_KEY = "4f242qr19c5vyy8";
	final String APP_SECRET = "x0gtlkorpr2kqc2";
	String accessToken = "hJXl5ZHWosAAAAAAAAAYA8-wNLV6XQ4dmId2MI_JmQIzUPDQ9fNpGoYnHsOTORcf";
	DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
	DbxClient client;

	public void login() {
		DbxRequestConfig config = new DbxRequestConfig("cmpe_273", Locale
				.getDefault().toString());
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		@SuppressWarnings("unused")
		String authorizeUrl = webAuth.start();
		client = new DbxClient(config, accessToken);
	}
	
	 public void uploadFile(String file) throws Exception
	 {

	    login();
	    FileInputStream inputStream =null;
	    try 
	    {
	       File inputFile = new File(file);
	       inputStream= new FileInputStream(inputFile);
	       
	       DbxEntry.File uploadedFile = client.uploadFile("/"+file,
	       DbxWriteMode.add(), inputFile.length(), inputStream);
	       System.out.println("Uploaded: " + uploadedFile.toString());
	    } 
	    finally 
	    {
	                inputStream.close();
	    }
	}

	//Download file from dropbox
	 public String downloadFile() throws Exception{

	        login();
	        DbxEntry.File downloadedFile;
	        FileOutputStream outputStream = new FileOutputStream("dropbox.pdf");
	        try 
	        {
	        	downloadedFile= client.getFile("/dropbox.pdf", null,
	                outputStream);
	            System.out.println("Metadata: " + downloadedFile.toString());
	        } finally {
	            outputStream.close();
	        }
	        return downloadedFile.toString();
	    }
	 
	 //List the files from dropbox
	 public String listFiles() throws Exception{

	        login();
	        DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
	        System.out.println("Files in the root path:");
	        String files="";
	        for (DbxEntry child : listing.children) {
	            System.out.println("	" + child.name + ": " + child.toString());
	            files+=child.name+"\n";
	        }
	        return files;
	        
	    }
}
