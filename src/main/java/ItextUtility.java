

import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class ItextUtility {

	public void convertPdfToText(String src, String dest)
    {
    	try
    	{
    		//TODO: use src and dest parameters when finished testing
    		PdfReader reader = new PdfReader("receipt-1.pdf");
    		PdfReaderContentParser parser = new PdfReaderContentParser(reader);
    		PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"));
    		TextExtractionStrategy strategy;
    		for (int i = 1; i <= reader.getNumberOfPages(); i++) 
    		{
    			strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            	out.println(strategy.getResultantText());
    		}
    		out.flush();
    		out.close();
    		reader.close();
    	}
    	catch(Exception e)
      	{e.printStackTrace();}
    }

}
