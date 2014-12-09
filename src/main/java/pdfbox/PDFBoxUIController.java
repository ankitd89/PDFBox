package pdfbox;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PDFBoxUIController {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String firstPage() {
		return "login";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String homePage( @RequestParam(value = "email", required = false) String email) throws Exception {
//		String url = "http://localhost:8080/restHome";
//		
//		URL obj = new URL(url);
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
// 		con.setRequestMethod("GET");
// 		int responseCode = con.getResponseCode();
// 		BufferedReader in = new BufferedReader(
//		        new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
//		in.close();		 
//		return response.toString();
		return "home";
	}
}
