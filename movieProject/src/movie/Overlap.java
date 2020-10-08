package movie;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;

public class Overlap extends JDialog implements ActionListener{
	public Panel p;
	public Frame f;
	public JButton btn1;
	public JDialog d;
	
	public Overlap() {
		d = new JDialog(this);
		f = new Frame();
		btn1 = new JButton("아이디 중복!");
		p = new Panel();
		
		p.setLayout(new GridLayout());
		p.add(btn1);
		
		btn1.addActionListener(this);
		
		setLayout(new BorderLayout());
		add(p,"Center");
	

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setSize(150,150);
		setVisible(true);

	}
		public static void main(String[] args) {
			new Overlap();
		}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1)
			this.setVisible(false);	

			}
}
