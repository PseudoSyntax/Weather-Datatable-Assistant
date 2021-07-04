
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
import org.jfree.data.xy.XYSeries;  
import org.jfree.data.xy.XYSeriesCollection;  
  
public class Test2 extends JFrame 
{  
  //private static final long serialVersionUID = 6294689542092367723L; //Have to declare a static final
  
  public Test2(String title) {  
    super(title);  
  
    // Create dataset  
    XYDataset dataset = createDataset();  
  
    // Create chart  
    JFreeChart chart = ChartFactory.createTimeSeriesChart(   
        "Date vs. Temperature Across Various Cities",   
        "Date", "Temperature", dataset);  
  
      
    //Changes background color  
    XYPlot plot = (XYPlot)chart.getPlot();  
    plot.setBackgroundPaint(new Color(255,228,196));  
      
     
    // Create Panel  
    ChartPanel panel = new ChartPanel(chart);  
    setContentPane(panel);  
  }  
  
  private XYDataset createDataset() {  
	 TimeSeriesCollection dataset = new TimeSeriesCollection();  
    
    String path = "";
	String line = "";
	Scanner scan = new Scanner(System.in);
	
	ArrayList<String> city = new ArrayList<String>();
	ArrayList<String> date = new ArrayList<String>();
	ArrayList<String> temperature = new ArrayList<String>();
	
	try
	{
		//XYSeries series1 = new XYSeries("City 1");
		BufferedReader br = new BufferedReader(new FileReader("something.csv"));
		
		line = br.readLine();
		String[] info = line.split(",");
		
		while((line = br.readLine()) != null)
		{		
			System.out.println(line);
			info = line.split(",");
			
			city.add(info[0]);
			date.add(info[1]);
			temperature.add(info[2]);
			
//			series1.add(null, city.add(info[0]));
//			series1.add(null, date.add(info[1]));
//			series1.add(null, temperature.add(info[2]));
			System.out.println(line);
			System.out.println("City: " + info[0] + " Date: " + info[1] + " Temperature: " + info[2]);
			TimeSeries series1 = new TimeSeries("Series2");  
			series1.add(new Day(1, 1, 2017), Integer.parseInt(info[2]));
			series1.add(new Day(1, 2, 2017), Integer.parseInt(info[2]));
			series1.add(new Day(1, 3, 2017), Integer.parseInt(info[2]));
			
			
			dataset.addSeries(series1);
			return dataset;
		}
	}
	catch(FileNotFoundException e)
	{
		e.printStackTrace();
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}   
    return null;  
  }  
  
  public static void main(String[] args) 
  {  
	  
	  
	  SwingUtilities.invokeLater(() -> {  
      Test2 example = new Test2("Scatter Chart");  
      example.setSize(800, 400);  
      example.setLocationRelativeTo(null);  
      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
      example.setVisible(true);  
    });  
  }  
}  