import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DProject extends JFrame 
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton customers, employees, back1, back2, addCust,
		cont, member, yesMem, noMem, backCus, admin,
		backEmp, addAirP, backAdmin, goAdmin, conNewAirP,
		report, ShowFlights;
	Container contents;
	Panel start, CustPan, EmpPan, NewCustPan, IDPan,
		memberPan, gratzPan, addAirport, adminPan, addAirPPan,
		reportPan, ShowFPan;
	JLabel Fname, askID, memberLab, gratzLab, askAPID,
		askLoc;
	JTextField getID, APIDField, locField;
	JCheckBox inter;
	
	int curCusID, APID;
	String IDString, memberQ, toParse, locString, sqlQuery,
		international;
	ResultSet set;
	
	Connection connection;
	Statement statement;
	
	public DProject() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		String connectionURL= 
				"jdbc:mysql://localhost:3306/Regal_Air?autoReconnect=true&useSSL=false";
		connection = DriverManager.getConnection(connectionURL, "root", "root");
		statement = connection.createStatement();
		
		ButtonHandler bh = new ButtonHandler();
		contents = getContentPane();
		contents.setLayout(new FlowLayout());
		
		customers = new JButton("Costomers");
		
		back1 = new JButton("back");
		back2 = new JButton("Back");
		addCust = new JButton("New Customer");
		member = new JButton("Become member");
		backCus = new JButton("Back");
		backCus.addActionListener(bh);
		
		Fname = new JLabel("First name");
		
		CustPan = new Panel();
		NewCustPan = new Panel();
		IDPan = new Panel();
		memberPan = new Panel();
		
		cont = new JButton("Continue");
		askID = new JLabel("Enter your customer ID");
		getID = new JTextField();
		getID.setColumns(10);
		IDPan.add(askID);
		IDPan.add(getID);
		IDPan.add(cont);
		cont.addActionListener(bh);
		
		yesMem = new JButton("Yes");
		noMem = new JButton("No");
		memberLab = new JLabel("Become Member?");
		memberPan.add(memberLab);
		memberPan.add(yesMem);
		memberPan.add(noMem);
		yesMem.addActionListener(bh);
		noMem.addActionListener(bh);
		
		gratzPan = new Panel();
		gratzLab = new JLabel("You are now a member congratulations!");
		gratzPan.add(gratzLab);
		gratzPan.add(backCus);
		
		start = new Panel();
		employees = new JButton("Employees");
		start.add(customers);
		start.add(employees);
		customers.addActionListener(bh);
		employees.addActionListener(bh);
		
		CustPan.add(back1);
		CustPan.add(member);
		member.addActionListener(bh);
		back1.addActionListener(bh);
		
		EmpPan = new Panel();
		goAdmin = new JButton("Admin");
		report = new JButton("Reporting");
		EmpPan.add(goAdmin);
		EmpPan.add(report);
		EmpPan.add(back2);
		NewCustPan.add(Fname);
		back2.addActionListener(bh);
		goAdmin.addActionListener(bh);
		report.addActionListener(bh);
		
		contents.add(start);
		
		adminPan = new Panel();
		backEmp = new JButton("Back");
		backEmp.addActionListener(bh);
		addAirP = new JButton("Add AirPort");
		addAirP.addActionListener(bh);
		adminPan.add(addAirP);
		adminPan.add(backEmp);
		
		addAirPPan = new Panel(new GridLayout(2, 2));
		askAPID = new JLabel("Airport ID");
		askLoc = new JLabel("Location");
		APIDField = new JTextField();
		APIDField.setColumns(10);
		locField = new JTextField();
		locField.setColumns(10);
		conNewAirP = new JButton("Confirm");
		conNewAirP.addActionListener(bh);
		backAdmin = new JButton("Back");
		backAdmin.addActionListener(bh);
		inter = new JCheckBox("International?");
		inter.addActionListener(bh);
		addAirPPan.add(askAPID);
		addAirPPan.add(APIDField);
		addAirPPan.add(askLoc);
		addAirPPan.add(locField);
		addAirPPan.add(inter);
		addAirPPan.add(conNewAirP);
		addAirPPan.add(backAdmin);
		
		reportPan = new Panel();
		ShowFlights = new JButton("Show Flights");
		ShowFlights.addActionListener(bh);
		reportPan.add(ShowFlights);
		reportPan.add(backEmp);
		
		ShowFPan = new Panel();
		
		
		setSize(500, 500);
		setVisible(true);
	}
	
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent a) 
		{
			if(a.getSource() == customers)
			{
				contents.remove(start);
				contents.add(IDPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == employees)
			{
				contents.remove(start);
				contents.add(EmpPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == back1)
			{
				contents.removeAll();
				contents.add(start);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == back2)
			{
				contents.remove(EmpPan);
				contents.add(start);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == addCust)
			{
				contents.remove(EmpPan);
				contents.add(NewCustPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == cont)
			{
				IDString = getID.getText();
				curCusID = Integer.parseInt(IDString);
				contents.removeAll();
				contents.add(CustPan); 
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == member)
			{
				contents.removeAll();
				contents.add(memberPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == noMem)
			{
				contents.removeAll();
				contents.add(CustPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == yesMem)
			{
				memberQ = "update customers set member = true where C_ID = " + curCusID;
				try {
					statement.executeUpdate(memberQ);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				contents.removeAll();
				contents.add(gratzPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == backCus)
			{
				contents.removeAll();
				contents.add(CustPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == backEmp)
			{
				contents.removeAll();
				contents.add(EmpPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == addAirP)
			{
				contents.removeAll();
				contents.add(addAirPPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == backAdmin)
			{
				contents.removeAll();
				contents.add(adminPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == goAdmin)
			{
				contents.removeAll();
				contents.add(adminPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == conNewAirP)
			{
				toParse = APIDField.getText();
				APID = Integer.parseInt(toParse);
				locString = locField.getText();
				if(inter.isSelected())
				{
					international = "true";
				}
				else
				{
					international = "false";
				}
				
				sqlQuery = "insert into Airport(AirP_ID, Location, International) values(" + APID + ", '" +  locString  + "', " + international + ")"; 
				try {
					statement.execute(sqlQuery);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				contents.removeAll();
				contents.add(adminPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == report)
			{
				contents.removeAll();
				contents.add(reportPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == ShowFlights)
			{
				contents.removeAll();
				contents.add(ShowFPan);
				setVisible(false);
				setVisible(true);
			}
			
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{	
		DProject SP = new DProject();
		SP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
