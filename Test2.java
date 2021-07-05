
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

  
public class Test2 extends JFrame 
{  
  //private static final long serialVersionUID = 6294689542092367723L; //Have to declare a static final
  
  public Test2(String title) {  
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
  
  
  private XYDataset createDataset() {  
	 TimeSeriesCollection dataset = new TimeSeriesCollection();  
    
	 String path = "";
	 String line = "";
	 Scanner scan = new Scanner(System.in);
	 DateFormat format = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
	
	
	 ArrayList<String> city = new ArrayList<String>();
	 ArrayList<String> date = new ArrayList<String>();
	 ArrayList<String> temperature = new ArrayList<String>();
	
	 try
	 {
		//XYSeries series1 = new XYSeries("City 1");
		FileReader filepath = new FileReader("something.csv");
		BufferedReader br = new BufferedReader(filepath);
		
		
		line = br.readLine();
//		String[] info = line.split(",");
		
//		while((line = br.readLine()) != null)
		for (int i = 0; i < 10; i++)
		{		
			line = br.readLine();
			String[] info = line.split(",");
//			info = line.split(",");
			
			city.add(info[0]);
			date.add(info[1]);
			temperature.add(info[2]);
			
//			try {
//				Date d = format.parse(info[2]);
//				System.out.println("City: " + info[0] + "\nDate: " + info[1] + "\nTemperature: " + info[2]);
//				TimeSeries series1 = new TimeSeries(info[0]);  
//				series1.add(new Day(d), Double.parseDouble(info[2]));
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
			System.out.println("City: " + info[0] + "\nDate: " + info[1] + "\nTemperature: " + info[2] + "\n");
			TimeSeries series1 = new TimeSeries(info[0]);  
//			series1.add(new Day(d), Double.parseDouble(info[2]));
//			series1.add(new Day(7, 3, 2021), Double.parseDouble(info[2]));
//			series1.add(new Day(5, 6, 2021), Double.parseDouble(info[2]));
			
			Date d = format.parse(info[1]);
			series1.add(new Day(d), Double.parseDouble(info[2]));
			dataset.addSeries(series1);
		}
	 }
	 catch(ParseException e) 
	 {
		 e.printStackTrace();
	 }
	 catch(FileNotFoundException e)
	 {
		e.printStackTrace();
	 }
	 catch(IOException e)
	 {
		e.printStackTrace();
	 }   
	 return dataset;  
  }  
  
  public static void main(String[] args) 
  {  
	  SwingUtilities.invokeLater(() -> {  
      Test2 example = new Test2("Time Series Chart");  
      example.setSize(1000, 500);  
      example.setLocationRelativeTo(null);  
      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
      example.setVisible(true);  
    });  
  }  
}  
