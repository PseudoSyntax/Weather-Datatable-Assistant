/**
 * <>
 * CSE 360: Group Assignment
 */
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
import java.net.URL;
import javax.swing.JFileChooser;


public class Main {
    public static void main(String[] args) throws InterruptedException, MalformedURLException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
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
        JPanel aboutPanel = new JPanel();
        JPanel loadDataPanel = new JPanel();
        JPanel addDataPanel = new JPanel();
        JPanel saveDataPanel = new JPanel();


        //===============================================HOME PANEL===============================================
        //JLabel Welcome = new JLabel("\n Welcome " );
        //homePanel.add(Welcome);
        tabs.addTab("Home", homePanel);

        String data[][]={ {"Phoenix","7/2","100","F"},
                {"Test","7/3","39","C"},
                {"Random","5/6","70","F"},
                {"Random2","5/9","49","C"} };
        String column[]={"City Name","Date","Temperature","Type"};
        JTable jt=new JTable(data,column);
        jt.setBounds(35,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        homePanel.add(sp);








        //===============================================ABOUT PANEL===============================================

        tabs.addTab("About", aboutPanel);
        //===============================================LOAD PANEL===============================================

        tabs.addTab("Load Data", loadDataPanel);







        //===============================================ADD PANEL===============================================

        tabs.addTab("Add Data", addDataPanel);

        JTable jt_add =new JTable(data,column);
        jt.setBounds(30,40,200,300);
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

        //String cityInput = cityText.getText();
        //String dateInput = dateText.getText();
        //String tempInput = tempText.getText();
        //String degreeInput = degreeText.getText();



        addRowBtn.addActionListener(new ActionListener() {
            int count=1;//helps array grow with each btn click
            int tail = 0;
            int i,j;

            public void actionPerformed(ActionEvent e) {
            System.out.println("length of row "+data.length);
                System.out.println("length of col "+data[0].length);
                String cityInput = cityText.getText();
                String dateInput = dateText.getText();
                String tempInput = tempText.getText();
                String degreeInput = degreeText.getText();

                int n = data.length;//row size
                int m = data[0].length;//col size

                String[][] data2 = new String[count+data.length][4]; //new array declared
                    for ( i=0; i < data.length;i++ ) {
                        for ( j=0; j < data[0].length;j++) { //we used num[0] because we need the length of the rows not the columns
                            data2[i][j] = data[i][j];
                            System.out.println(data2[i][j]); //Copies array
                        }
                    }
                for ( i = data.length; i < data.length+count;i++ ) {
                    for (j = 0; j < 4; j++) {
                        while(tail!=count) {
                            data2[i][0] = cityInput;
                            data2[i][1] = dateInput;
                            data2[i][2] = tempInput;
                            data2[i][3] = degreeInput;
                            tail++;
                        }

                        System.out.println(data2[i][j]); //Copies array

                    }
                }
                //data[data2.length][4] = data2[i][j];//update data array size with data2 array size
                count++;//updates data length
                System.out.println("count var: "+count);
            }

        });



        //===============================================SAVE PANEL===============================================

        tabs.addTab("Save Data", saveDataPanel);

        JTable table = new JTable(data,column);
        JScrollPane sp3=new JScrollPane(table);

        JTextField userInput = new JTextField(8);
        saveDataPanel.add(userInput);
        JLabel label1 = new JLabel(".csv");
        saveDataPanel.add(label1);

        JButton b1 = new JButton();
        saveDataPanel.add(b1);
        b1.setSize(400,400);
        b1.setText("Save data to .csv file");
        b1.setVisible(true);

        saveDataPanel.add(sp3);


        b1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String fileName = userInput.getText();
                //*************************************ADDED*************************************
                //Not final just code I wil/ implement into the existing buffer reader later
                /*
                JFrame fileExp = new JFrame();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to save");

                int userSelect = fileChooser.showSaveDialog(fileExp);

                if (userSelect == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    JFrame locationFrame = new JFrame();
                    JOptionPane.showMessageDialog(locationFrame,"File saved to: " + fileToSave.getAbsolutePath(),"File Location", JOptionPane.INFORMATION_MESSAGE);
                }
                */
                //*************************************ADDED*************************************
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName +".csv")))) {
                    StringJoiner joiner = new StringJoiner("\n");

                    System.out.println(joiner.toString());
                    bw.write(joiner.toString());
                    for (int row = 0; row < table.getRowCount(); row++) {
                        joiner = new StringJoiner("\n");
                        for (int col = 0; col < table.getColumnCount(); col++) {
                            Object obj = table.getValueAt(row, col);
                            String value = obj == null ? "null" : obj.toString();
                            joiner.add(value);
                        }
                        System.out.println(joiner.toString());
                        bw.write(joiner.toString());
                        bw.newLine();
                    }

                } catch (IOException exp) {
                    exp.printStackTrace();
                }
                JFrame localFile = new JFrame();
                JOptionPane.showMessageDialog(localFile,"File saved to Project folder!","File Location", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //Add JPanel for tables
        JPanel allTables = new JPanel(new GridLayout(6, 1));
        allTables.add(new JLabel("Table Layout"));

        //===============================================PLOT PANEL===============================================
        JScrollPane plotDataPanel = new JScrollPane(allTables);
        tabs.addTab("Plot Data", plotDataPanel);
        



        //baseFrame layout
        tabs.setBorder(BorderFactory.createTitledBorder("Menu"));
        baseFrame.add(tabs, BorderLayout.CENTER);
        baseFrame.setVisible(true);
        baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//stop when closed


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

*/
    }

}//end of Main
