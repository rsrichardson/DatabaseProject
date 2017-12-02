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
	 * By Ryan Richardson
	 * November 19, 2017
	 * Rochester MI
	 */
	private static final long serialVersionUID = 1L;
	JButton customers, employees, back1, back2, addCust,
		cont, member, yesMem, noMem, backCus, admin,
		backEmp, addAirP, backAdmin, goAdmin, conNewAirP,
		report, ShowFlights, DisFlight, backFReport, backFDisF,
		canConB, backCan, backLisF, canFB;
	Container contents;
	Panel start, CustPan, EmpPan, NewCustPan, IDPan,
		memberPan, gratzPan, addAirport, adminPan, addAirPPan,
		reportPan, ShowFPan, disFlightsPan, cancelPan;
	JLabel Fname, askID, memberLab, gratzLab, askAPID,
		askLoc, enterFLab, disFlightsLab, cancelLab;
	JTextField getID, APIDField, locField, flightField,
		canField;
	JCheckBox inter;
	JTable disFTab;
	
	String[] FColName = {"Flight", "From", "To"};
	Object[][] fliData = new Object[20][3];
	int curCusID, APID, airNum, canTickID, numAvl,
		FLID;
	String IDString, memberQ, toParse, locString, sqlQuery,
		international, disFlightSt, sqlQ, classDel;
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
		
		addCust = new JButton("New Customer");
		backCus = new JButton("Back");
		backCus.addActionListener(bh);
		
		
		
		// Panel for customer ID input
		IDPan = new Panel();
		cont = new JButton("Continue");
		askID = new JLabel("Enter your customer ID");
		getID = new JTextField();
		getID.setColumns(10);
		IDPan.add(askID);
		IDPan.add(getID);
		IDPan.add(cont);
		cont.addActionListener(bh);
		
		//Panel to become a member
		memberPan = new Panel();
		yesMem = new JButton("Yes");
		noMem = new JButton("No");
		memberLab = new JLabel("Become Member?");
		memberPan.add(memberLab);
		memberPan.add(yesMem);
		memberPan.add(noMem);
		yesMem.addActionListener(bh);
		noMem.addActionListener(bh);
		
		//Panel to display "congratulations" message
		gratzPan = new Panel();
		gratzLab = new JLabel("You are now a member congratulations!");
		gratzPan.add(gratzLab);
		gratzPan.add(backCus);
		
		//Panel to choose costomer/Employee
		start = new Panel();
		employees = new JButton("Employees");
		customers = new JButton("Costomers");
		start.add(customers);
		start.add(employees);
		customers.addActionListener(bh);
		employees.addActionListener(bh);
		
		//Customer Menu
		CustPan = new Panel();
		member = new JButton("Become member");
		back1 = new JButton("back");
		canFB = new JButton("Cancel Flight");
		CustPan.add(member);
		CustPan.add(canFB);
		CustPan.add(back1);
		member.addActionListener(bh);
		canFB.addActionListener(bh);
		back1.addActionListener(bh);
		
		//Panel for employee menu
		EmpPan = new Panel();
		Fname = new JLabel("First name");
		back2 = new JButton("Back");
		goAdmin = new JButton("Admin");
		report = new JButton("Reporting");
		EmpPan.add(goAdmin);
		EmpPan.add(report);
		EmpPan.add(back2);
		//NewCustPan.add(Fname);
		back2.addActionListener(bh);
		goAdmin.addActionListener(bh);
		report.addActionListener(bh);
		
		//adds start to contents to start gui
		contents.add(start);
		
		adminPan = new Panel();
		backEmp = new JButton("Back");
		backEmp.addActionListener(bh);
		addAirP = new JButton("Add AirPort");
		addAirP.addActionListener(bh);
		adminPan.add(addAirP);
		adminPan.add(backEmp);
		
		//panel to create new Airport
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
		
		//Panel for Reporting Menu
		reportPan = new Panel();
		ShowFlights = new JButton("Show Flights");
		backFReport = new JButton("Back");
		backFReport.addActionListener(bh);
		ShowFlights.addActionListener(bh);
		reportPan.add(ShowFlights);
		reportPan.add(backFReport);
		
		//Panel to show Flights 
		ShowFPan = new Panel();
		enterFLab = new JLabel("Enter Airport Number");
		flightField = new JTextField();
		flightField.setColumns(10);
		DisFlight = new JButton("Enter");
		DisFlight.addActionListener(bh);
		ShowFPan.add(enterFLab);
		ShowFPan.add(flightField);
		ShowFPan.add(DisFlight);
		
		//Panel to displays flights
		//disFlightsPan = new Panel();
		//disFlightsLab = new JLabel(disFlightSt);
		backFDisF = new JButton("Back");
		backFDisF.addActionListener(bh);
		//disFlightsPan.add(disFTab);
		//disFlightsPan.add(backFDisF);
		
		//panel to cancel tickets
		cancelPan = new Panel();
		cancelLab = new JLabel("Enter Ticket ID");
		canField = new JTextField();
		canField.setColumns(10);
		canConB = new JButton("Cancel");
		backCan = new JButton("Back");
		canConB.addActionListener(bh);
		backCan.addActionListener(bh);
		cancelPan.add(cancelLab);
		cancelPan.add(canField);
		cancelPan.add(canConB);
		cancelPan.add(backCan);
		
		
		
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
			else if(a.getSource() == DisFlight)
			{
				toParse = flightField.getText();
				airNum = Integer.parseInt(toParse);
				sqlQuery = "select F_ID, F.location As 'From' , T.location As 'TO' "
						+ "from Flights Join airport As F on From_A = F.AirP_ID Join airport as T on To_A = T.AirP_ID "
						+ "where from_A = " + airNum;
				try {
					set = statement.executeQuery(sqlQuery);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int i = 0;
				try {
					while(set.next())
					{
						fliData[i][0] = set.getInt(1);
						fliData[i][1] = set.getString(2);
						fliData[i][2] = set.getString(3);
						i++;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				disFTab = new JTable(fliData ,FColName);
				contents.removeAll();
				contents.add(disFTab);
				contents.add(backFDisF);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == backFReport)
			{
				contents.removeAll();
				contents.add(EmpPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == backFDisF)
			{
				contents.removeAll();
				contents.add(reportPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == canFB)
			{
				contents.removeAll();
				contents.add(cancelPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == canConB)
			{
				toParse = canField.getText();
				canTickID = Integer.parseInt(toParse);
				sqlQ = "select class, F_ID From tickets where T_ID = " + canTickID;
				try {
					set = statement.executeQuery(sqlQ);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			try {
				classDel = set.getString(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				FLID = set.getInt(2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(classDel == "Economy")
			{
				sqlQ = "update Flights set Economy_Avl = Economy_Avl + 1 where F_ID = " + FLID;
			}
			else if(classDel == "Bussiness")
			{
				sqlQ = "update Flights set Bussiness_Avl = Bussiness_Avl + 1 where F_ID = " + FLID;
			}
			else if(classDel == "First")
			{
				sqlQ = "update Flights set First_Avl = First_Avl + 1 where F_ID = " + FLID;
			}
			else
			{
				sqlQ = "update Flights set Family_Avl = Family_Avl + 1 where F_ID = " + FLID;
			}
			try {
				statement.execute(sqlQ);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sqlQ = "delete from tickets where T_ID = " + canTickID;
			try {
				statement.execute(sqlQ);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			contents.removeAll();
			contents.add(CustPan);
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
