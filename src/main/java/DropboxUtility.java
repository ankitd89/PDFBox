import com.dropbox.core.*;
import java.util.Locale;

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
		String authorizeUrl = webAuth.start();
		client = new DbxClient(config, accessToken);
	}

}
