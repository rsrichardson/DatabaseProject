import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DProject extends JFrame 
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton next, SB;
	Container contents;
	Panel panel1, panel2;
	
	public DProject()
	{
		contents = getContentPane();
		contents.setLayout(new FlowLayout());
		next = new JButton("next");
		SB = new JButton("here");
		
		panel1 = new Panel();
		panel2 = new Panel();
		
		panel1.add(next);
		contents.add(panel1);
		
		panel2.add(SB);
		
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
				contents.remove(panel1);
				contents.add(panel2);
				setVisible(false);
				setVisible(true);
				//hell
				
			}
		}
		
	}
	
	public static void main(String[] args) 
	{
		DProject SP = new DProject();
		SP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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