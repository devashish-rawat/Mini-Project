import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.*;
import java.awt.Image;
import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


public class popular_baby_names extends JFrame
{
    JPanel contentPane,contentPane2,BarGraph,introPane;
    JComboBox comboBox1,comboBox2,comboBox3,comboBox4,comboBox5;
    JButton buttonSubmit,buttonSubmit2,buttonNext,buttonPrev,buttonContinue,buttonBrowse,goIntro;
    JLabel labelYear,labelCheckFor,labelShow,labelCheckFor2,labelEnterName,labelFrom,label,lblNewLabel;
    String infoCombo1,infoCombo2,infoCombo3,infoCombo4,infoCombo5,Name,Path;
    JTextArea Output,Output2;
    JScrollPane scrollBar,scrollBar2;
    JTextField textField;
    JFileChooser choose;
    
	public static void main(String[] args)
	{
		new popular_baby_names();
	}
	
	public popular_baby_names()
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
		this.setSize(715,737);					
		this.setLocationRelativeTo(null);	
		this.setResizable(false);         
		this.setTitle("Popular Baby Names");
		getContentPane().setLayout(new CardLayout(0, 0));
		
		contentPane = new JPanel();
		getContentPane().add(contentPane, "1");
		contentPane.setVisible(false);
		
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
		buttonSubmit.setToolTipText("Click !! to View Output");
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
		
		buttonNext = new JButton("Check Popularity of a Particular Name");
		buttonNext.setToolTipText("Search Using a Particular Name");
		buttonNext.setBounds(403, 658, 249, 25);
		contentPane.add(buttonNext);
		buttonNext.addActionListener(lForButton);
		
		goIntro = new JButton("Back");
		goIntro.setBounds(36, 658, 84, 25);
		goIntro.addActionListener(lForButton);
		contentPane.add(goIntro);
		
		
		contentPane2 = new JPanel();
		getContentPane().add(contentPane2, "2");
		contentPane2.setLayout(null);
		contentPane2.setVisible(false);
		
		buttonPrev = new JButton("Search Names Using Birth Year");
		buttonPrev.setToolTipText("Search Using Birth Year");
		buttonPrev.setBounds(420, 658, 232, 25);
		buttonPrev.addActionListener(lForButton);
		contentPane2.add(buttonPrev);
		
		comboBox4 = new JComboBox();
		comboBox4.addItem("Male");
		comboBox4.addItem("Female");
		comboBox4.setBounds(110, 50, 75, 22);
		contentPane2.add(comboBox4);
		
		textField = new JTextField();
		textField.setToolTipText("Enter First Name !!\r\n");
		textField.setBounds(293, 50, 102, 22);
		contentPane2.add(textField);
		textField.setColumns(10);
		
		buttonSubmit2 = new JButton("Submit");
		buttonSubmit2.setToolTipText("Click !! to View Output");
		buttonSubmit2.setBounds(555, 49, 97, 25);
		buttonSubmit2.addActionListener(lForButton);
		contentPane2.add(buttonSubmit2);
		
		
		labelEnterName = new JLabel("Enter Name :");
		labelEnterName.setBounds(210, 28, 84, 66);
		contentPane2.add(labelEnterName);
		
		labelCheckFor2 = new JLabel("Check For :");
		labelCheckFor2.setBounds(36, 53, 84, 16);
		contentPane2.add(labelCheckFor2);
	
		Output2 = new JTextArea(200,50);
		Output2.setFont(new Font("Monospaced", Font.PLAIN, 15));
		
		scrollBar2 = new JScrollPane(Output2,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollBar2.setBounds(36, 492, 616, 142);
		contentPane2.add(scrollBar2);
		
		labelFrom = new JLabel("From :");
		labelFrom.setBounds(407, 41, 56, 41);
		contentPane2.add(labelFrom);
		
		comboBox5 = new JComboBox(Year);
		comboBox5.setBounds(455, 50, 67, 22);
		contentPane2.add(comboBox5);
		
		BarGraph = new JPanel();	
		BarGraph.setBackground(Color.WHITE);
		BarGraph.setBounds(36, 82, 616, 392);
		contentPane2.add(BarGraph);
		BarGraph.setLayout(new BoxLayout(BarGraph, BoxLayout.X_AXIS));
		
		
		introPane = new JPanel();
		getContentPane().add(introPane, "4");
		introPane.setLayout(null);
		introPane.setVisible(true);
		
		JLabel lblSelectTheFolder = new JLabel("Select the Folder Containing the csv Files.");
		lblSelectTheFolder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelectTheFolder.setBounds(374, 425, 286, 25);
		introPane.add(lblSelectTheFolder);
		
		buttonBrowse = new JButton("Browse");
		buttonBrowse.setBounds(374, 454, 97, 25);
		buttonBrowse.addActionListener(lForButton);
		introPane.add(buttonBrowse);
		
		buttonContinue = new JButton("Next");
		buttonContinue.setBounds(554, 654, 97, 25);
		buttonContinue.addActionListener(lForButton);
		introPane.add(buttonContinue);
		buttonContinue.setEnabled(false);
		
		label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label.setBounds(374, 492, 335, 25);
		introPane.add(label);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 366, 712);
		Image img = new ImageIcon(this.getClass().getResource("/baby.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		
		introPane.add(lblNewLabel);
		
		JLabel lblPopularBabyNames = new JLabel("Popular Baby Names :");
		lblPopularBabyNames.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPopularBabyNames.setBounds(374, 83, 219, 37);
		introPane.add(lblPopularBabyNames);
		
		JLabel lblChecksForNames = new JLabel("-> Checks for Names by Birth Year.");
		lblChecksForNames.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChecksForNames.setBounds(374, 146, 286, 16);
		introPane.add(lblChecksForNames);
		
		JLabel lblChecksPopularity = new JLabel("-> Checks Popularity of a Particular Name.");
		lblChecksPopularity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChecksPopularity.setBounds(374, 189, 286, 16);
		introPane.add(lblChecksPopularity);
	    
		this.setVisible(true);
	
	}
	
	private class ListenForButton implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==buttonContinue)
			{
				introPane.setVisible(false);
				contentPane.setVisible(true);
				contentPane2.setVisible(false);
			}
			
			if(e.getSource()==buttonBrowse)
			{
				choose=new JFileChooser();
				choose.setDialogTitle("Select Folder");
				choose.setCurrentDirectory(new java.io.File("."));
				choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				choose.setAcceptAllFileFilterUsed(false);
				
			    if (choose.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			    {
			    Path = choose.getSelectedFile().toString();
			    }
			    
			    if(Path.contains("Baby Names 1944-2013"))
			    {
			    	
			    	label.setText("Path : " + Path);
			    	buttonContinue.setEnabled(true);
			    }
			    else
			    {
			    	label.setText("Files Not Found !! Try Again !!");
			    }
			    
			    Path = Path + "\\";
			}
			
			if(e.getSource()==goIntro)
			{
				introPane.setVisible(true);
				contentPane.setVisible(false);
				contentPane2.setVisible(false);
				
			}
			
			
			if(e.getSource()==buttonSubmit)
			{
				Output.setText("");										
				scrollBar.getVerticalScrollBar().setValue(0);	     
				
				infoCombo1=comboBox1.getSelectedItem().toString();
				infoCombo2=comboBox2.getSelectedItem().toString();
				infoCombo3=comboBox3.getSelectedItem().toString();
				
				//System.out.println(infoCombo1);
				//System.out.println(infoCombo2);
				//System.out.println(infoCombo3);
				
				comboReturn(infoCombo1,infoCombo2,infoCombo3);
			}
			
			if(e.getSource()==buttonNext)
			{
				contentPane.setVisible(false);
				contentPane2.setVisible(true);
				introPane.setVisible(false);
			}
			
			if(e.getSource()==buttonPrev)
			{
				contentPane.setVisible(true);
				contentPane2.setVisible(false);
				introPane.setVisible(false);
			}
			
			if(e.getSource()==buttonSubmit2)
			{
				Output2.setText("");								
				scrollBar2.getVerticalScrollBar().setValue(0);	   
				
				infoCombo4=comboBox4.getSelectedItem().toString();
				//System.out.println(infoCombo4);
				infoCombo5=comboBox5.getSelectedItem().toString();
				//System.out.println(infoCombo5);
				
				Name=textField.getText();
				//System.out.println(Name);
				
				comboReturn2(infoCombo4,Name,infoCombo5);
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
		
		
		File file = new File (Path + fileName);
		
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
				if(values[1].equals("\""))
				{
					no_of_births = extractInt (values[2]);
				}
				else
				{
					no_of_births = extractInt (values[1]);
				}
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
		//String clean ; 
		
		String []values1={""};
		String []values2={""};
		String clean1="",clean2="";
		
		//System.out.println(fileName1);
		//System.out.println(fileName2);
		
		File file1 = new File (Path + fileName1);
		File file2 = new File (Path + fileName2);
		
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
	
	public void comboReturn2(String infoCombo4,String Name,String infoCombo5)
	{
		int year,no_of_births,flag,i;
		String fileName,choice1,clean,nof;
		
		year=extractInt(infoCombo5);
		
		choice1=infoCombo4.toLowerCase();
		Name=Name.toUpperCase();
		//System.out.println(Name);
		
		
		DefaultCategoryDataset data = new DefaultCategoryDataset();				//Bar Chart
		
		JFreeChart jchart = ChartFactory.createBarChart("","Year","No of Births",data);
		CategoryPlot plot = jchart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.black);

		Output2.append("YEAR" + "\t\t" + "NUMBER OF BIRTHS\n");
		for(i=year;i<=2013;i++)
		{
			flag=0;
			
			fileName=choice1 + "_cy" + i + "_top.csv";
			//System.out.println(fileName);
			
			File file3 = new File (Path + fileName);
			
			try
			{
				Scanner inputStream3 = new Scanner (file3);
				
				inputStream3.next(); //to remove Column Headers
				inputStream3.next();
				
				while(inputStream3.hasNext())
				{
					String datas = inputStream3.next();
					String []values = datas.split(",");
					if(values[1].equals("\""))
					{
						no_of_births = extractInt (values[2]);
					}
					else
					{
						no_of_births = extractInt (values[1]);
					}
					clean = removeQuotes (values[0]);
			
					if(clean.equals(Name))
					{
						nof=Integer.toString(no_of_births);
						Output2.append(i + "\t\t");
						Output2.append(nof + "\n");
						flag=1;
						data.setValue(no_of_births,"No of Births",Integer.toString(i));	
						
						break;
					}

				}			
				inputStream3.close();
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			
			if(flag==0)
			{
				Output2.append(i + "\t\t");
				Output2.append("0"+"\n");
				data.setValue(0,"No of Births",Integer.toString(i));	
			}
		}
		
		ChartFrame chartFrm = new ChartFrame("Student",jchart,true);
	
		ChartPanel chartPanel = new ChartPanel(jchart);
		BarGraph.removeAll();
		BarGraph.add(chartPanel);
		BarGraph.updateUI();
	}
	
	public int extractInt (String str)
	{
		int intValue;
		
		str=str.replaceAll("[^0-9]", "");	
		
		intValue = Integer.parseInt(str);
		
		return (intValue);	
	}
	
	public String removeQuotes (String str)
	{
		String clean;
		
		clean=str.replaceAll("\"", "");			
	
		return (clean);	
	}
}