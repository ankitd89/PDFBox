
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
	
	
	
}