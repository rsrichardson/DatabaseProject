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
		cont, member, yes, no;
	Container contents;
	Panel start, CustPan, EmpPan, NewCustPan, IDPan,
		memberPan;
	JLabel Fname, askID, memberLab;
	JTextField getID;
	int curCus;
	String IDString, memberQ;
	
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
		employees = new JButton("Employees");
		back1 = new JButton("back");
		back2 = new JButton("Back");
		addCust = new JButton("New Customer");
		member = new JButton("Become member");
		
		Fname = new JLabel("First name");
		
		start = new Panel();
		CustPan = new Panel();
		EmpPan = new Panel();
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
		
		yes = new JButton("Yes");
		no = new JButton("No");
		memberLab = new JLabel("Become Member?");
		memberPan.add(memberLab);
		memberPan.add(yes);
		memberPan.add(no);
		yes.addActionListener(bh);
		no.addActionListener(bh);
		
		start.add(customers);
		start.add(employees);
		
		CustPan.add(back1);
		CustPan.add(member);
		member.addActionListener(bh);
		
		
		EmpPan.add(back2);
		EmpPan.add(addCust);
		NewCustPan.add(Fname);
		
		contents.add(start);
		
		
		customers.addActionListener(bh);
		back1.addActionListener(bh);
		employees.addActionListener(bh);
		back2.addActionListener(bh);
		addCust.addActionListener(bh);
		
		
		
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
				contents.remove(CustPan);
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
				curCus = Integer.parseInt(IDString);
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
			else if(a.getSource() == no)
			{
				contents.removeAll();
				contents.add(CustPan);
				setVisible(false);
				setVisible(true);
			}
			else if(a.getSource() == yes)
			{
				memberQ = "update customers set member = true where C_ID = " + curCus;
				try {
					statement.executeUpdate(memberQ);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{	
		DProject SP = new DProject();
		SP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
