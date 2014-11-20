import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.SortedMap;
import model.*;

//iText imports
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import com.jramoyo.io.*;

import config.MongoConfigJava;

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
    		this.buildBillAmount();
    		this.buildBillProducts();
    		//this.displayBill();
    		
    		//this.displayBillLines();
    		MongoConfigJava mongo = new MongoConfigJava();
    		mongo.saveBill(bill);
    		reader.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    }
	
	private void displayBillLines()
    {
    	for(int j = 0; j<=lines.size();j++)
			System.out.println("Line "+j+":"+lines.get(j));
    }
    
    private void buildShopIdForBill()
    {
    	bill.setShopId(StringFormatter.clearShopID(lines.get(5)));  
    }
    
    private void buildBillDateAndTime() 
    {
    	bill.setBillDate(StringFormatter.clearBillDate(lines.get(lines.size())));
		bill.setBillTime(StringFormatter.clearBillTime(lines.get(lines.size())));
	}
    
    private void buildBillRef() 
    {
    	bill.setBillRef(StringFormatter.clearBillRef(lines.get(lines.size()-9)));
	}
    
    private void buildBillProducts() 
    {
    	int lastProductIndex = lines.size()-22;
		int firstProductIndex = 8;

		while(lastProductIndex != firstProductIndex)
		{
			products.add(lines.get(lastProductIndex));
			lastProductIndex--;
		}
	
		//add first product
		products.add(lines.get(8));
		ArrayList<Product> clearedProducts = StringFormatter.clearProductsArray(this.products);
		
		bill.setProducts(clearedProducts);
	}

	private void buildBillAmount() 
    {
    	//There can be many items between line 8th(inclusive) and 18th(inclusive)
		//Get the total bill, lines.size()-18 is the line from bottom that will give us the total amount
    	for(int i = lines.size();;i--)
			if(i == lines.size()-18)
			{
				bill.setTotalBillAmount(StringFormatter.clearTotalAmount(lines.get(lines.size()-18)));
				break;
			}
	}

	private void displayBill()
    {
    	System.out.println("Bill total is :" + this.bill.getTotalBillAmount());
    	System.out.println("Bill shop id is :" + this.bill.getShopId());	    	
    	System.out.println("Bill date is: "+ this.bill.getBillDate());
    	System.out.println("Bill date is: "+ this.bill.getBillTime());
    	System.out.println("Bill Ref is: "+ this.bill.getBillRef());
    	
		for(int i = 0; i<=this.bill.getProducts().size();i++)
		{
			Product p = bill.getProducts().get(i);
			System.out.println(":"+p.getProductQuantity()+":"+p.getProductName()+":"+p.getProductPrice());	
			}
    }
}
