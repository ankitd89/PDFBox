

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.io.RandomAccessSourceFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PRTokeniser;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.parser.ContentByteUtils;
import com.itextpdf.text.pdf.parser.PdfContentStreamProcessor;
import com.itextpdf.text.pdf.parser.RenderListener;

public class ItextUtility {


	   
	    /**
	     * Parses the PDF using PRTokeniser
	     * @param src  the path to the original PDF file
	     * @param dest the path to the resulting text file
	     * @throws IOException
	     */
	    public void parsePdf(String src, String dest) throws IOException {
	        PdfReader reader = new PdfReader(src);
	        // we can inspect the syntax of the imported page
	        byte[] streamBytes = reader.getPageContent(1);
	        PRTokeniser tokenizer = new PRTokeniser(new RandomAccessFileOrArray(new RandomAccessSourceFactory().createSource(streamBytes)));
	        PrintWriter out = new PrintWriter(new FileOutputStream(dest));
	        while (tokenizer.nextToken()) {
	            if (tokenizer.getTokenType() == PRTokeniser.TokenType.STRING) {
	                out.println(tokenizer.getStringValue());
	            }
	        }
	        out.flush();
	        out.close();
	        reader.close();
	    }

}
