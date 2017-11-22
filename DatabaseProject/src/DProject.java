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
	JButton customers, employees, back1, back2, addCust;
	Container contents;
	Panel start, CustPan, EmpPan, NewCustPan;
	JLabel Fname;
	
	public DProject()
	{
		contents = getContentPane();
		contents.setLayout(new FlowLayout());
		
		customers = new JButton("Costomers");
		employees = new JButton("Employees");
		back1 = new JButton("back");
		back2 = new JButton("Back");
		addCust = new JButton("New Customer");
		
		Fname = new JLabel("First name");
		
		start = new Panel();
		CustPan = new Panel();
		EmpPan = new Panel();
		NewCustPan = new Panel();
		
		start.add(customers);
		start.add(employees);
		CustPan.add(back1);
		EmpPan.add(back2);
		EmpPan.add(addCust);
		NewCustPan.add(Fname);
		
		contents.add(start);
		
		ButtonHandler bh = new ButtonHandler();
		
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
				contents.add(CustPan);
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
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		DProject SP = new DProject();
		SP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Class.forName("com.mysql.jdbc.Driver");
		String connectionURL= 
				"jdbc:mysql://localhost:3306/Regal_Air?autoReconnect=true&useSSL=false";
		Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
		Statement statement = connection.createStatement();
	}

}

/*
public class Second extends JFrame
{
	
	public void Second()
	{
		Container contents = getContentPane();
		contents.setLayout(new FlowLayout());
		//JButton next = new JButton("next");
		JLabel L2nd = new JLabel();
		
		contents.add(L2nd);
		
		setSize(500, 500);
		setVisible(true);
	}
}
*/