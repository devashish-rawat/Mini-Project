import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class console_app_3
{
static Scanner userInput = new Scanner (System.in);
	
	public static void main(String[] args)
	{
		
		String fileName;
		int top_no;
		
		fileName = generateFileName();
		
		top_no=askTop();
		
		fileRead(fileName,top_no);
		
	}
	
	public static void fileRead(String fileName , int top)
	{
		int i=1 , no_of_births ;
		String clean;
	
		File file = new File (fileName);
		
		try
		{
			Scanner inputStream = new Scanner (file);
			
			inputStream.next(); //to remove Column Headers
			inputStream.next();
			
			while(i<=top && inputStream.hasNext())
			{
				String data = inputStream.next();
				String []values = data.split(",");
				
				clean=removeQuotes(values[0]);
				System.out.println(clean);
				
				no_of_births = extractInt(values[1]);
				
				System.out.println("No of Births -> " + no_of_births + "\n");
				
				i++;
			}
			inputStream.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static int askTop()
	{
		int top=1;
		
		System.out.println("Enter no. of \"Top\" Names to be Displayed");
		
		if(userInput.hasNextInt())
		{
			top=userInput.nextInt();
		}
		else
		{
			System.out.println("Invalid Input");
			System.exit(0);
		}
		
		return(top);
		
		
	}
	
	public static String generateFileName()   
	{
		String fileName,gender;
		int year;
		
		System.out.println("Enter male / female");
		gender=userInput.nextLine();
		
		gender=gender.toLowerCase();
		
		if(!(gender.equals("male") || gender.equals("female")))
		{
			System.out.println("Invalid Input");
			System.exit(0);
		}
		
		System.out.println("Enter year");
		year=userInput.nextInt();
		
		if(year > 2013 || year < 1944)
		{
			System.out.println("Invalid Input");
			System.out.println("Enter year b/w 1944 & 2013");
			System.exit(0);
			
		}
		
		fileName = gender + "_cy" + year + "_top" + ".csv";
		
		return (fileName);
	}
	
	public static int  extractInt (String str)
	{
		int intValue;
		
		str=str.replaceAll("[^0-9]", ""); 
		
		intValue = Integer.parseInt(str);
		
		return (intValue);	
	}
	
	public static String  removeQuotes (String str)
	{
		String clean;
		
		clean=str.replaceAll("\"", ""); 
		
		return (clean);	
	}
}
