import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class PDFBoxUIController {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String firstPage() {
		return "login";
	}
}
