import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class console_app_1 {
	public static void main(String[] args)
	{
		int i=1;
		
		String fileName = "male_cy1996_top.csv";
		
		File file = new File (fileName);
		
		try
		{
			Scanner inputStream = new Scanner (file);
			
			inputStream.next(); //to remove Column Headers
			inputStream.next();
			
			while(i<=10)
			{
				String data = inputStream.next();
				System.out.println(data);
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
