package pdfbox;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;
import model.Account;
import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxRequestUtil;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;
import com.dropbox.core.http.HttpRequestor;

public class DropboxUtility {
	final String APP_KEY = "4f242qr19c5vyy8";
	final String APP_SECRET = "x0gtlkorpr2kqc2";
	private String accessToken;
	DbxAppInfo appInfo;
	DbxClient client;
	DbxRequestConfig config;
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public void login() {
		 config = new DbxRequestConfig("cmpe_273", Locale
				.getDefault().toString());
		 appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
		DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);
		@SuppressWarnings("unused")
		String authorizeUrl = webAuth.start();
		client = new DbxClient(config, accessToken);
	}
	
	 public void uploadFile(String file) throws Exception
	 {
	    FileInputStream inputStream =null;
	    try 
	    {
	       File inputFile = new File("receipt.pdf");
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
	 @SuppressWarnings("finally")
	public String downloadFile(String file) throws Exception{

	        login();
	        DbxEntry.File downloadedFile = null;
	        FileOutputStream outputStream = null;
	        try 
	        {
	        	System.out.println("In downloadFile.. file is:" +file);
	        	File outputFile = new File(file);
	        	outputStream = new FileOutputStream(outputFile);
	        	downloadedFile= client.getFile("/" +file, null,outputStream);
	        	String a = outputFile.getAbsolutePath(); 
	        	System.out.println(a+ "  PAth");
	        	System.out.println("Downloaded File is:" +downloadedFile.name);
	            System.out.println("Metadata: " + downloadedFile.toString());
	            
	        }catch(Exception e){System.out.println("Error" +e);}
	        
	        finally {
	        	outputStream.close();
	        	return downloadedFile.toString();
	        }
	        
	    }
	 
	 //List the files from dropbox
	 public String listFiles() throws Exception{

	        login();
	        DbxEntry.WithChildren listing = client.getMetadataWithChildren("/");
	        System.out.println("Files in the root path:");
	        String files="";
	        for (DbxEntry child : listing.children) {
	            files+=child.name+"\n";
	        }
	        return files;
	        
	    }
	 
	 //gets account info
	 public Account getAccountInfo()throws DbxException
	 {
        String host = "api.dropbox.com";
        String apiPath = "1/account/info";
        return DbxRequestUtil.doGet(config, accessToken, host, apiPath, null, null, new DbxRequestUtil.ResponseHandler<Account>(){
            @Override
            public Account handle(HttpRequestor.Response response) throws DbxException
            {
            	if (response.statusCode != 200) throw new DbxException.BadResponse("unexpected response code: " + response.statusCode); 
            	return DbxRequestUtil.readJsonFromResponse(Account.Reader, response.body);
            }
        });
        
	  }
	 
	 public void deleteFile(String path) throws DbxException
	 {
		 	client.delete("/"+path);
	 }
	 
}
