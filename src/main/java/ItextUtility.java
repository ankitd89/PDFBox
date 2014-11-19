import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.SortedMap;



import model.Bill;


//iText imports
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.jramoyo.io.*;



public class ItextUtility 
{
	ArrayList<String> products;
	SortedMap<Integer, String> lines;
	Bill bill;
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
    		products = new ArrayList<String>();
    		bill = new Bill();
    		//TODO: use src parameter
    		File file = new File("output.txt");
    		IndexedFileReader reader = new IndexedFileReader(file);
    		int totalLines = reader.getLineCount();
    		lines =  reader.readLines(1, totalLines);
    		this.buildShopIdForBill();
    		this.buildBillDateAndTime();
    		this.buildBillRef();
    		//this.displayBillLines();
    		reader.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
	
    @SuppressWarnings("unused")
	private void displayBillLines()
    {
    	for(int j = 0; j<=lines.size();j++)
			System.out.println("Line "+j+":"+lines.get(j));
    }
    
    private void buildShopIdForBill()
    {
    	bill.setShopId(StringFormatter.clearShopID(lines.get(5)));  
    	System.out.println("Shop ID retrived from receipt : "+bill.getShopId());
    }
    
    private void buildBillDateAndTime() 
    {
    	bill.setBillDate(StringFormatter.clearBillDate(lines.get(lines.size())));
		bill.setBillTime(StringFormatter.clearBillTime(lines.get(lines.size())));
		System.out.println("Bill date retrived from receipt : "+bill.getBillDate());
		System.out.println("Bill time retrived from receipt : "+bill.getBillTime());
	}
    
    private void buildBillRef() 
    {
    	bill.setBillRef(StringFormatter.clearBillRef(lines.get(lines.size()-9)));
    	System.out.println("Reference number retrived from receipt : "+bill.getBillRef());
	}

}
