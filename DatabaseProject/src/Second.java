import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Second extends DProject {

	public Second()
	{
		Container contents = getContentPane();
		contents.setLayout(new FlowLayout());
		//JButton next = new JButton("next");
		JLabel L2nd = new JLabel();
			
		contents.add(L2nd);		
		
		setSize(500, 500);
		setVisible(true);	}
	}
