import java.util.ArrayList;
import model.*;

/*
 * @abstract: This class will provide string formating capabilities to the project
 * 
 */
class StringFormatter
{
	public static String clearShopID(String id)
	{
		String temp;
		temp = id.replaceAll("[^.#a-zA-Z0-9]+", ""); //This will give output as Shop#1234
		String[] filteredString = temp.split("#"); 
		temp = filteredString[1];
		return temp;
	}
	
	public static String clearBillDate(String str)
	{
		String clearString = "";
		clearString = str.replaceAll("[^:#/a-zA-Z0-9]+","");
		clearString = clearString.substring(15,25);
		return clearString;
	}
	
	public static String clearBillTime(String str)
	{
		String clearString = "";
		clearString = str.replaceAll("[^:#/a-zA-Z0-9]+","");
		clearString = clearString.substring(25,32);
		return clearString;
	}
	
	public static String clearBillRef(String str)
	{
		String clearString = "";
		clearString = str.replaceAll("[^0-9]+","");
		return clearString;
	}
	
	//TODO: This method requires optimization.
	public static ArrayList<Product> clearProductsArray(ArrayList<String> products)
	{
		ArrayList<Product> clearedProducts = new ArrayList<Product>();
		//Filter products
		for(int i = 0; i<products.size();i++)
		{
			Product p = new Product();
			//Get quantity
			String forQuantity = products.get(i);
			forQuantity=forQuantity.replaceAll("[^0-9]+", ""); 
			String[] splittedProductArray = forQuantity.split("");
			p.setProductQuantity(splittedProductArray[0]);

			//Get Name
			String forName = products.get(i);
			forName = forName.replaceAll("[^a-zA-Z]+", "");
			String finalProductName="";
			String[] splittedArrayForName = forName.split("");
			for(int j = 0; j< splittedArrayForName.length;j++)
			{
				if (j==splittedArrayForName.length-1)
					continue;
				else
					finalProductName += splittedArrayForName[j];
			}
			p.setProductName(finalProductName);

			//Get Price
			String forPrice = products.get(i);
			forPrice = forPrice.replaceAll("[^.0-9]+", "");
			String finalForPrice = "";
			String[] splittedArrayForPrice = forPrice.split("");
			for(int j = 0; j< splittedArrayForPrice.length;j++)
				if(j==0)
					continue;
				else
					finalForPrice += splittedArrayForPrice[j];

			p.setProductPrice(finalForPrice);
			clearedProducts.add(p);
		}

		return clearedProducts;
	}
	
	public static String clearTotalAmount(String amt)
	{
		String temp;
		temp = amt.replaceAll("[^.0-9]+", "");
		return temp;
	}
	
	
	
}