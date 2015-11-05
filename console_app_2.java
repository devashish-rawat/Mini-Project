import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class console_app_2 
{
	static Scanner userInput = new Scanner (System.in);
	
	public static void main(String[] args)
	{
		int i=1;
		
		String year , gender ,fileName ;
		
		System.out.println("Enter male / female");
		gender=userInput.nextLine();
		
		System.out.println("Enter year");
		year=userInput.nextLine();
		
		fileName = gender + "_cy" + year + "_top" + ".csv";
		
		File file = new File (fileName);
		
		try
		{
			Scanner inputStream = new Scanner (file);
			
			inputStream.next();	 //to remove Column Headers
			inputStream.next();
			
			while(i<=10)
			{
				String data = inputStream.next();
				String []values = data.split(",");
				System.out.println(values[0]);
				i++;
			}
			inputStream.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}

}
