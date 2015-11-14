import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class gui_1 extends JFrame
{
        JPanel contentPane;
        JComboBox comboBox1,comboBox2;
        JButton buttonSubmit;
        JLabel labelYear,labelCheckFor;
        String infoCombo1,infoCombo2;
        JTextArea Output;
        JScrollPane scrollBar;
   
	public static void main(String[] args)
	{
		new gui_1();
	}
	
	public gui_1()
	{
		String[] Choice1 = {"Male","Female"};
		String[] Year = {"1944","1945","1946","1947","1948","1949","1950","1951","1952","1953","1954",
			 "1955","1956","1957","1958","1959","1960","1961","1962","1963","1964","1965","1966",
			 "1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977",
			 "1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989",
		         "1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000",
			 "2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011",
			 "2012","2013"};
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,700);					 //Dimensions of the Frame
		this.setLocationRelativeTo(null);		//Location of the Frame on the Screen
		this.setResizable(false);              //Frame Cannot be Resized
		this.setTitle("Top 10 Baby Names");
		contentPane = new JPanel();
		
		contentPane.setLayout(null);				//Absolute Layout
		
		labelCheckFor = new JLabel("Check For :");
		labelCheckFor.setBounds(36, 53, 84, 16);
		contentPane.add(labelCheckFor);
		
		comboBox1 = new JComboBox(Choice1);			
		comboBox1.setBounds(110, 50, 75, 22);
		contentPane.add(comboBox1);
		
		labelYear = new JLabel("Year :");
		labelYear.setBounds(245, 53, 49, 16);
		contentPane.add(labelYear);
		
		comboBox2 = new JComboBox(Year);
		comboBox2.setBounds(287, 50, 75, 22);
		contentPane.add(comboBox2);
		
		buttonSubmit = new JButton("Submit");
		buttonSubmit.setBounds(555, 49, 97, 25);
		contentPane.add(buttonSubmit);
		ListenForButton lForButton = new ListenForButton();
		buttonSubmit.addActionListener(lForButton);
		
		Output = new JTextArea(200,50);
		Output.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		scrollBar = new JScrollPane(Output,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollBar.setBounds(36, 131, 616, 501);
		contentPane.add(scrollBar);
		
		getContentPane().add(contentPane);
		this.setVisible(true);
	}
	
	private class ListenForButton implements ActionListener 		//Tracks Events for Buttons
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==buttonSubmit)
			{
				Output.setText("");			 //Clears TextField For Next Output
				
				infoCombo1=comboBox1.getSelectedItem().toString();
				infoCombo2=comboBox2.getSelectedItem().toString();
				//System.out.println(infoCombo1);
				//System.out.println(infoCombo2);
				
				comboReturn(infoCombo1,infoCombo2);
			}
		}	
	}
	
	public void comboReturn(String combo1 , String combo2)
	{
		String choice1="", year="", fileName="", clean;
		
		choice1 = combo1.toLowerCase();
		year = combo2;
	
		fileName = choice1 + "_cy" + year + "_top" + ".csv";
		//System.out.println(fileName);
		
		int no_of_births;
		int i=1;
		
		File file = new File (fileName);
		
		try
		{
			Scanner inputStream = new Scanner (file);
			
			inputStream.next(); 	//to remove Column Headers
			inputStream.next();
			
			Output.append("YEAR  " + year + "\n\n");
			
			while(i<=10 && inputStream.hasNext())
			{
				String data = inputStream.next();
				String []values = data.split(",");
				clean = removeQuotes (values[0]);
				no_of_births = extractInt (values[1]);
				
				Output.append(i + ". " + clean + "\n");
				Output.append("No. of Births -> " + no_of_births  + "\n\n");
				i++;
			}
			inputStream.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static int extractInt (String str)
	{
		int intValue;
		
		str=str.replaceAll("[^0-9]", "");		//Extracts Integer values from String
		//System.out.println(str);
		
		intValue = Integer.parseInt(str);
		
		return (intValue);	
	}
	
	public static String removeQuotes (String str)
	{
		String clean;
		
		clean=str.replaceAll("\"", "");			//Removes " " from String 
		//System.out.println(clean);
		
		return (clean);	
	}
}

