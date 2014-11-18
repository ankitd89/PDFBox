

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.jramoyo.io.*;

import java.util.ArrayList;
import java.util.SortedMap;

public class ItextUtility 
{
	SortedMap<Integer, String> lines;

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
	
	public void buildMetaDataForFile(String src)
    {
    	try{
    	
    		
    		//TODO: use src parameter
    		File file = new File("output.txt");
    		IndexedFileReader reader = new IndexedFileReader(file);
    		int totalLines = reader.getLineCount();
    		lines =  reader.readLines(1, totalLines);
    		
    		this.displayBillLines();
    		reader.close();
    	}
    	catch(Exception e)
    	{}
    }
	
    private void displayBillLines()
    {
    	for(int j = 0; j<=lines.size();j++)
			System.out.println("Line "+j+":"+lines.get(j));
    }

}
