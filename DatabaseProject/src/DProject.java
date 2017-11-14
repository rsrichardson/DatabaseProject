import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DProject extends JFrame 
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton next;
	
	
	public DProject()
	{
		Container contents = getContentPane();
		contents.setLayout(new FlowLayout());
		next = new JButton("next");
		
		contents.add(next);
		
		ButtonHandler bh = new ButtonHandler();
		next.addActionListener(bh);
		
		setSize(500, 500);
		setVisible(true);
	}
	
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent a) 
		{
			if(a.getSource() == next)
			{
				Second S = new Second();
				S.setVisible(true);
			}
		}
		
	}
	
	public static void main(String[] args) 
	{
		DProject SP = new DProject();
		SP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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