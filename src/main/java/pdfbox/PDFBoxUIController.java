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
	public String homePage() throws Exception {
			return "home";
	}
}
