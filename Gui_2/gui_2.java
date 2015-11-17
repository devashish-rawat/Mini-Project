import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;


public class gui_2 extends JFrame
{
    JPanel contentPane;
    JComboBox comboBox1,comboBox2,comboBox3;
    JButton buttonSubmit;
    JLabel labelYear,labelCheckFor,labelShow;
    String infoCombo1,infoCombo2,infoCombo3;
    JTextArea Output;
    JScrollPane scrollBar;
   
	public static void main(String[] args)
	{
		new gui_2();
	}
	
	public gui_2()
	{
		String[] Choice1 = {"Male","Female","Both"};
		String[] Year = {"1944","1945","1946","1947","1948","1949","1950","1951","1952","1953","1954",
						 "1955","1956","1957","1958","1959","1960","1961","1962","1963","1964","1965","1966",
						 "1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977",
						 "1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989",
				         "1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000",
				         "2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011",
				         "2012","2013"};
		String[] Display = {"Top 5","Top 10","Top 20","Top 50","Top 100","All"};
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,700);					 //Dimensions of the Frame
		this.setLocationRelativeTo(null);		//Location of the Frame on the Screen
		this.setResizable(false);              //Frame Cannot be Resized
		this.setTitle("Top 10 Baby Names");
		contentPane = new JPanel();
		
		contentPane.setLayout(null);
		
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
		
		comboBox3 = new JComboBox(Display);
		comboBox3.setLocation(449, 50);
		comboBox3.setSize(75, 22);
	
		contentPane.add(comboBox3);
		
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
		
		labelShow = new JLabel("Show :");
		labelShow.setBounds(403, 44, 59, 35);
		contentPane.add(labelShow);
		
		getContentPane().add(contentPane);
		this.setVisible(true);
	}
	
	private class ListenForButton implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==buttonSubmit)
			{
				Output.setText("");			//Clears TextField For Next Output
				
				infoCombo1=comboBox1.getSelectedItem().toString();
				infoCombo2=comboBox2.getSelectedItem().toString();
				infoCombo3=comboBox3.getSelectedItem().toString();
				
				comboReturn(infoCombo1,infoCombo2,infoCombo3);
			}
		}
		
	}
	
	public void comboReturn(String combo1 , String combo2 ,String combo3)
	{
		String choice1="", year="", fileName="" , fileName1="" , fileName2="";
		int show;
		
		choice1 = combo1.toLowerCase();
		year = combo2;
		
		if(combo3 == "All")
		{
			show = Integer.MAX_VALUE;		//Assigns 2147483647 to show
			//System.out.println(show);
		}
		else
		{
			show = extractInt(combo3);
		}
		if(combo1 == "Both")
		{
			fileName1 = "male_cy" + year + "_top.csv" ;
			fileName2 = "female_cy" + year + "_top.csv";
			
			outputFunc2 (fileName1 , fileName2 , year , show);	
		}
		else
		{
	
		fileName = choice1 + "_cy" + year + "_top" + ".csv";
		//System.out.println(fileName);
		outputFunc1(fileName , year ,show);
		
		}
		
	}
	
	public void outputFunc1 (String fileName , String year , int show)
	{
		
		int no_of_births , rank=1 ;
		int i=1;
		String clean ; 
		
		
		File file = new File (fileName);
		
		try
		{
			Scanner inputStream = new Scanner (file);
			
			inputStream.next(); //to remove Column Headers
			inputStream.next();
			
			Output.append("YEAR  " + year + "\n\n");
			
			do
			{
				String data = inputStream.next();
				String []values = data.split(",");
				clean = removeQuotes (values[0]);
				no_of_births = extractInt (values[1]);
				rank = extractInt (values[2]);
				//System.out.println(rank);
				if(rank > show)
					break;
				
				Output.append(i + ". " + clean + "\n");
				Output.append("No. of Births -> " + no_of_births + "\t" +"Rank -> "+ rank + "\n\n" );
				i++;
				
				
				
			}while(rank <= show && inputStream.hasNext());
			inputStream.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void outputFunc2 (String fileName1 , String fileName2 , String year , int show )
	{
		int no_of_births1=0 ,no_of_births2=0 ;
		int i=1,flag=0;
		
		String []values1={""};
		String []values2={""};
		String clean1="",clean2="";
		
		//System.out.println(fileName1);
		//System.out.println(fileName2);
		
		File file1 = new File (fileName1);
		File file2 = new File (fileName2);
		
		try
		{
			Scanner inputStream1 = new Scanner (file1);
			Scanner inputStream2 = new Scanner (file2);
			
			inputStream1.next(); //to remove Column Headers
			inputStream1.next();
			
			inputStream2.next(); //to remove Column Headers
			inputStream2.next();
			
			Output.append("YEAR  " + year + "\n\n");
			
			do
			{
				if(flag!=2)
				{
					String data1 = inputStream1.next();
					values1 = data1.split(",");
					no_of_births1 = extractInt (values1[1]);
					clean1 = removeQuotes (values1[0]);
				}
				if(flag!=1)
				{
					String data2 = inputStream2.next();
					values2 = data2.split(",");
					no_of_births2 = extractInt (values2[1]);
					clean2 = removeQuotes (values2[0]);
				}
			
			
				if(no_of_births1 >= no_of_births2)
				{
					Output.append(i + ". " + clean1 + "  (Male)" + "\n");
					Output.append("No. of Births -> " + no_of_births1 + "\t" +"Rank -> "+ i + "\n\n" );
					flag=1;
				}
				else
				{
					Output.append(i + ". " + clean2 + "  (Female)" + "\n");
					Output.append("No. of Births -> " + no_of_births2 + "\t" +"Rank -> "+ i + "\n\n" );
					flag=2;
				}
			
			i++;
			
			}while(i<=show && inputStream1.hasNext() && inputStream2.hasNext());
			
			if(i<=show)
			{
				Output.append(i + ". " + clean2 + "  (Female)" + "\n");
				Output.append("No. of Births -> " + no_of_births2 + "\t" +"Rank -> "+ i + "\n\n" );
				i++;
			}
			
			if(i<=show)
			{
				do
				{
					String data2 = inputStream2.next();
					values2 = data2.split(",");
					no_of_births2 = extractInt (values2[1]);
					clean2 = removeQuotes (values2[0]);
				
					Output.append(i + ". " + clean2 + "  (Female)" + "\n");
					Output.append("No. of Births -> " + no_of_births2 + "\t" +"Rank -> "+ i + "\n\n" );
				
					i++;
				
				}while(i<=show && inputStream2.hasNext());
			}
			
			
			inputStream1.close();
			inputStream2.close();
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
