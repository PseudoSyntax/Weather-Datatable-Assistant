// CSE 360: Group Assignment
// Group Members: Imaan Ahmed, May Lister, Cera Monson, Italo Pennella
 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.*;
import java.net.MalformedURLException;
import java.util.StringJoiner;
import java.util.Vector;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JFrame;  
import javax.swing.SwingUtilities;  
import javax.swing.WindowConstants;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;  

import java.net.URL;

public class PlotData extends JFrame 
{
	
	//===============================================PLOT DATA PANEL===============================================
	
	public PlotData(String title) 
	{  
	    super(title);  
	  
	    // Create dataset  
	    XYDataset dataset = createDataset();  
	  
	    // Create chart  
	    JFreeChart chart = ChartFactory.createTimeSeriesChart(   
	        "Temperature vs. Date Across Various Cities",   
	        "Date", "Temperature", dataset);  
	  
	      
	    //Changes background color  
	    XYPlot plot = (XYPlot)chart.getPlot();  
	    plot.setBackgroundPaint(Color.LIGHT_GRAY);  
	      
	     
	    // Create Panel  
	    ChartPanel panel = new ChartPanel(chart);  
	    setContentPane(panel);  
	} 
	
	static String data [][] = {};
	static String column[]={"City Name","Date","Temperature","Type"};
	
	private static XYDataset createDataset() {
		TimeSeriesCollection dataset = new TimeSeriesCollection(); 
		
		TimeSeries series1 = new TimeSeries("Cities");
		
		try
		{
			for (int i = 0; i < data.length; i++)
			{

				TimeSeries series2 = new TimeSeries("Phoenix");
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("M/d/yyyy");
				String date = simpleDateFormat.format(new Date());
				Date d = simpleDateFormat.parse(data[i][1]);
				series1.add(new Day(d), Double.parseDouble(data[i][2]));

			}
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
	    dataset.addSeries(series1);
	    return dataset;
	  }  
	
	
	public static void main(String[] args) throws InterruptedException, MalformedURLException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException 
	{
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");  //Theme for the Windows, feel free to change it back to windows

        JProgressBar progressBar; //Progress bar variable

        /*URL FOR SPLASHSCREEN*/
        String splashURL = "https://th.bing.com/th/id/R3d8064758e54ec662e076b6ca54aa90e?rik=tZBabuOiEIiJdg&riu=http%3a%2f%2f38.media.tumblr.com%2f439a4606156abed8c2d04944d4efd12f%2ftumblr_n6kholb70q1sd011wo1_1280.gif&ehk=nahe56kJGROkVr9BULvIZzIWyumeW1pbVTYTXEGRoXw%3d&risl=&pid=ImgRaw";
        String splashURL1 = "https://th.bing.com/th/id/R.38c10d9ad1341f6413b28834ee8a5c69?rik=zdZb85N2fijuZA&riu=http%3a%2f%2ffreefrontend.com%2fassets%2fimg%2fcss-loaders%2filluminati-rainbow-loading.gif&ehk=XppS1B5%2f1AVwp4YXYb0ssOEKaCFYfaYvBLCno3d7it8%3d&risl=&pid=ImgRaw";
        String splashURL2 = "https://drive.google.com/file/d/1yRiN0QkHIoiQf9NBKiqXZtE0o3hPTLLC/view?usp=sharing";

        /******SPLASH SCREEN******/
        JWindow splashScreen = new JWindow();
        splashScreen.getContentPane().add(new JLabel("", new ImageIcon(new URL(splashURL1)), SwingConstants.CENTER));
        splashScreen.setBounds(290, 160, 400, 300);
        splashScreen.setVisible(true);
        splashScreen.setSize(390, 310);
        splashScreen.setLayout(null);
        splashScreen.setLocationRelativeTo(null);  //Sets in middle of user screen

        progressBar = new JProgressBar();
        //progressBar.setStringPainted(true);  //This will show the percentage but is forced disabled with nimbus theme
        //progressBar.setForeground(Color.ORANGE);  //Color is forced default orange when using nimbus theme
        progressBar.setMaximum(100);
        progressBar.setMinimum(0);


        splashScreen.add(progressBar);
        progressBar.setBounds(-10, 300, 404, 14); //Bounds of Progress Bar
        progressBar.setPreferredSize(new Dimension(310, 30)); //Size of Progress BAr
        
//        SwingUtilities.invokeLater(() -> {  
//            PlotData example = new PlotData("Time Series Chart");  
//            example.setSize(1000, 500);  
//            example.setLocationRelativeTo(null);  
//            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
//            example.setVisible(true);  
//          });  

        //Runnable inner class allows thread to move along bar
        Thread thread = new Thread()
        {
            public void run()
            {
                int i = 0;
                while (i <= 100) //Percentage by which bar goes up
                {
                    progressBar.setValue(i);
                    try {
                        sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        };
        thread.start();
        Thread.sleep(100);  //Display Screen for a bit
        splashScreen.setVisible(false); //Removes splashscreen load
        splashScreen.dispose();  //Deletes frame
        /******SPLASH SCREEN******/


        /***** Create JFrame *****/
        JFrame baseFrame = new JFrame();  //Create baseFrame of Application
        baseFrame.setSize(700, 700);
        baseFrame.setTitle("CSE 360");
        baseFrame.setLocationRelativeTo(null); //sets location to center of user screen
        baseFrame.setLayout(new GridLayout(1,1));

        /***** Add tabs to the frame *****/
        //baseFrame.add(menu, BorderLayout.EAST);
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.LEFT);

        JPanel homePanel = new JPanel();
        JPanel homePanel2 = new JPanel();
        JPanel aboutPanel = new JPanel();
        JPanel loadDataPanel = new JPanel();
        JPanel addDataPanel = new JPanel();
        JPanel saveDataPanel = new JPanel();
        JPanel plotDataPanel = new JPanel();


        //===============================================HOME PANEL===============================================
        //JLabel Welcome = new JLabel("\n Welcome " );
        //homePanel.add(Welcome);
        tabs.addTab("Home", homePanel);
        JTable tempor = new JTable();
        JScrollPane jsp = createTables(tempor);
        homePanel.add(jsp);

        //===============================================ABOUT PANEL===============================================

        //panel and color
        JPanel pain = new JPanel();
        pain.setBackground(Color.WHITE);
        
        //creating text
        JLabel group = new JLabel("\t\tTeam #28\n\n");
        group.setFont(new Font("Times New Roman", Font.BOLD,18));
        JLabel name1 = new JLabel("\t\t\tImaan Ahmed\n");
        name1.setFont(new Font("Times New Roman", Font.PLAIN,15));
        JLabel name2 = new JLabel("\t\t\tMay Lister\n");
        name2.setFont(new Font("Times New Roman", Font.PLAIN,15));
        JLabel name3 = new JLabel("\t\t\tCera Monson\n");
        name3.setFont(new Font("Times New Roman", Font.PLAIN,15));
        JLabel name4 = new JLabel("\t\t\tItalo Pennella");
        name4.setFont(new Font("Times New Roman", Font.PLAIN,15));
        
        //setting layout and adding text
        BoxLayout Box = new BoxLayout(pain, BoxLayout.Y_AXIS);
        pain.setLayout(Box);
        
        pain.add(group);
        pain.add(name1);
        pain.add(name2);
        pain.add(name3);
        pain.add(name4);
        
        //adding panel to tabs
        tabs.addTab("About", pain);
        
        //===============================================LOAD PANEL===============================================

        //add load tab
        tabs.addTab("Load Data", loadDataPanel);
        
        //setting up text field for user to enter filename
        JLabel selectFile = new JLabel("Select File");
        loadDataPanel.add(selectFile);
        JTextField fileInput = new JTextField(10);
        loadDataPanel.add(fileInput);
        
        //select file button
        JButton select = new JButton();
        select.setSize(400,100);
        select.setText("Select File");
        select.setVisible(true);
        loadDataPanel.add(select);
        
        //result of pushing button
        select.addActionListener(new ActionListener()
        	{
        		public void actionPerformed(ActionEvent e)
        		{
        			String file = fileInput.getText();
        			try
        			{
        				//file reader
        				FileReader fr = new FileReader(file);
        				BufferedReader br = new BufferedReader(fr);
        				String line = br.readLine();
        				line = br.readLine();
        				
        				//counting amount of data for new array
        				int count = 0;
        				
        				while(line != null)
        				{
        					line = br.readLine();
        					count++;
        				}
        				
        				//second file reader to
        				FileReader fr2 = new FileReader(file);
        				BufferedReader br2 = new BufferedReader(fr2);
        				String line2 = br2.readLine();
        				
        				line2 = br2.readLine();						//to skip over column names
        				String[][] data2 = new String[count][4];	//creating new array
        				int i = 0;
        				
        				//read and add data to new array
        				while (line2 != null)
        				{
        					String[] words = line2.split(",", 4);
        					data2[i][0] = words[0];
        					data2[i][1] = words[1];
        					data2[i][2] = words[2];
        					data2[i][3] = words[3];
        					
        					i++;
        					line2 = br2.readLine();
        				}
        				
        				//replace data
        				changer(data2);
        				
        				//close readers
        				br.close();
        				br2.close();
        				
        			}
        			catch (IOException e2)
        			{
        				System.out.println("IO Exception");
        			}
        			
        			//update home panel with new data
        			homePanel.removeAll();
        			homePanel.add(createTables(tempor));
        		}
        	});


        //===============================================ADD PANEL===============================================

       
        tabs.addTab("Add Data", addDataPanel);
        
        JTable jt_add =new JTable(data,column);
        //jt.setBounds(30,40,200,300);
        JScrollPane sp2=new JScrollPane(jt_add);
        addDataPanel.add(sp2);


        JLabel cityName = new JLabel("City");
        JLabel date = new JLabel("Date");
        JLabel tempBtn = new JLabel("Temperature");
        JLabel degreeType = new JLabel("Degree Type(C/F)");


        JTextField cityText = new JTextField("City",10);
        JTextField dateText = new JTextField("Date",10);
        JTextField tempText = new JTextField("Temperature",10);
        JTextField degreeText = new JTextField("Degree Type",10);

        addDataPanel.add(cityName);
        addDataPanel.add(cityText);

        addDataPanel.add(date);
        addDataPanel.add(dateText);

        addDataPanel.add(tempBtn);
        addDataPanel.add(tempText);

        addDataPanel.add(degreeType);
        addDataPanel.add(degreeText);



        JButton addRowBtn = new JButton("Submit");
        addDataPanel.add(addRowBtn);

        String cityInput = cityText.getText();
        //String dateInput = dateText.getText();
        //String tempInput = tempText.getText();
        //String degreeInput = degreeText.getText();

        //final int[] count = {1};
        final int[] tail = {0};
        addRowBtn.addActionListener(new ActionListener() {
            //int count=1;//helps array grow with each btn click

            int i,j;
            int count = 1;
            String temp = cityText.getText();
            public void actionPerformed(ActionEvent e) {
                if(0 > 1){

                    String temp2 = "cityInput";
                }
                System.out.print(temp);

                System.out.println("length of row "+data.length);
                System.out.println("length of col "+data[0].length);
                String cityInput = cityText.getText();
                String dateInput = dateText.getText();
                String tempInput = tempText.getText();
                String degreeInput = degreeText.getText();

                int n = data.length;//row size
                int m = data[0].length;//col size
                int index=0;



                String[][] data2 = new String[count +data.length][4]; //new array declared
                for ( i = 0; i < data.length;i++ ) {
                    for ( j=0; j < data[0].length;j++) { //we used num[0] because we need the length of the rows not the columns
                        data2[i][j] = data[i][j];
                        System.out.println(data2[i][j]); //Copies array
                    }
                }
                for (i = data.length; i < data.length+ count; i++ ) {//growing
                    for (j = 0; j < 4; j++) {
                        //while(tail[0]!=count[0]) {
                            //if(data2[i+1][3] == null) {

                                if(i-2 == count) {//prints new statement in correct place on array
                                    data2[i][0] = cityInput + "yeehaw";
                                    data2[i][1] = dateInput;
                                    data2[i][2] = tempInput;
                                    data2[i][3] = degreeInput;
                                    System.out.print("#" + i +"Count:"+count+"      ");
                                }
                               // if(i==4){
                                //    data2[i][0] = cityInput+"yeehaw2";
                                //}
                                //data2[i][1] = dateInput;
                                //data2[i][2] = tempInput;
                                //data2[i][3] = degreeInput;
                            //}
                            //tail[0]++;
                        //}
                        System.out.println(data2[i][j]); //Copies array
                        //System.out.println(tail[0]);
                        
                    }
                    
                }
                //data[data2.length][4] = data2[i][j];//update data array size with data2 array size

                /*
                for ( i = 0; i < data2.length+count;i++ ) {
                    for ( j=0; j < data2[0].length;j++) { //we used num[0] because we need the length of the rows not the columns
                        data[i][j] = data2[i][j];
                        System.out.println("Updating the data...\n"+data[i][j]); //Copies array
                    }
                }*/
                count++;//updates data length
//                addDataPanel.add(createTables(tempor));
                System.out.println("count:"+count);

                System.out.println("#####: "+ i);
                //createTables(data2);
            }

        });

        //update home panel with new data
//		addDataPanel.removeAll();
//		addDataPanel.add(createTables(tempor));



        //===============================================SAVE PANEL===============================================

        //add tab
        tabs.addTab("Save Data", saveDataPanel);

        //add text field for name of save file
        JTextField userInput = new JTextField(8);
        saveDataPanel.add(userInput);
        JLabel label1 = new JLabel(".csv");
        saveDataPanel.add(label1);

        //add button to activate save
        JButton b1 = new JButton();
        saveDataPanel.add(b1);
        b1.setSize(400,400);
        b1.setText("Save data");
        b1.setVisible(true);

        //action listener for button
        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String fileName = userInput.getText();

                //writing data to new file
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName +".csv")))) 
                {
                    StringJoiner joiner = new StringJoiner(",");

                    //writing column names to file
                    for (int col = 0; col < column.length; col++) 
                    {
                        joiner.add(column[col]);
                    }

                    bw.write(joiner.toString());
                    bw.newLine();

                    //writing data to file
                    for(int r = 0; r<data.length; r++)
                    {
                    	joiner = new StringJoiner(",");
                    	for (int c = 0; c<data[r].length; c++)
                    	{
                    		Object obj = data[r][c];
                    		String value = obj == null ? "null" : obj.toString();
                    		joiner.add(value);
                    	}
                    	bw.write(joiner.toString());
                        bw.newLine();
                    }
                    
                } 
                catch (IOException exp) 
                {
                    exp.printStackTrace();
                }  
            }
        });

        //Add JPanel for tables
        tabs.addTab("Plot Data", plotDataPanel);

        //baseFrame layout
        tabs.setBorder(BorderFactory.createTitledBorder("Menu"));
        baseFrame.add(tabs, BorderLayout.CENTER);
        baseFrame.setVisible(true);
        baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stop when closed
        
      //===============================================PLOT DATA PANEL===============================================
        JButton plot = new JButton();
        plot.setSize(400,400);
        plot.setText("Plot data");
        plot.setVisible(true);
        plotDataPanel.add(plot);

        //action listener for button
        plot.addActionListener(new ActionListener()
        {
        	
            public void actionPerformed(ActionEvent e)
            { 
            	
            	XYDataset dataset = createDataset();  
            	
            	 SwingUtilities.invokeLater(() -> {  
       		      PlotData example = new PlotData("Time Series Chart");  
       		      example.setSize(1000, 500);  
       		      example.setLocationRelativeTo(null);  
       		      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
       		      example.setVisible(true);  
       		    });  
            }
            
        });
        
        
        /****TERMS OF USE FRAME****/
        /*
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.add(new JLabel("Terms of Use"));
        frame.setSize(400,450);
        JLabel README = new JLabel("You agree to give us A+");
        JButton AgreeBtn = new JButton("I AGREE");
        JButton DisagreeBtn = new JButton("I DISAGREE FOR NOW");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        TextArea Terms = new TextArea(20,50);
        Terms.append("Text goes here");
        Terms.setEditable(false);
        JScrollPane scroll = new JScrollPane(Terms);
        Terms.setBackground(Color.LIGHT_GRAY);
        frame.add(scroll);
        frame.add(AgreeBtn);
        frame.add(DisagreeBtn);
        frame.add(README);
        frame.setVisible(true);
        //JOptionPane.showMessageDialog(frame,"Warning.","Warning Box", JOptionPane.WARNING_MESSAGE);
        //custom title, custom icon
*/
	}
	
	
	public static void changer(String[][] temps)
	{
		data = temps;
	}
	
	public static JScrollPane createTables(JTable jazz)
	{
		jazz = new JTable(data,column);
        JScrollPane sp=new JScrollPane(jazz);
        return sp;
	}

    
}//end of Main
