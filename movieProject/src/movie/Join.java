package movie;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Join extends JDialog implements ActionListener{
	public JLabel title;
	public Frame f;
	public JTextField tf1,tf2;
	public JButton b1,b2;
	public JDialog d;
	public Image img;
	public JPasswordField pw;
	public Panel p1,p2,p3,p4,p5,p6;
	public GridLayout g1,g2;
	public Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15;
	public MemberDialog MemberDialog;
	public Login Login;
	public Join() {
		title = new JLabel("영화 예매 시스템");
		f = new Frame();
		f.setTitle("영화 예매 사이트");
		d = new JDialog(this);         
		
		b1 = new JButton("회원가입");
		b2 = new JButton("로그인");
		pw = new JPasswordField();
		p1 = new Panel();
		p2 = new Panel();
		p3 = new Panel();
		p4 = new Panel();
		p5 = new Panel();
		p6 = new Panel();
		l1 = new Label("");
		l2 = new Label("");
		l3 = new Label("");
		l4 = new Label("");
		l5 = new Label("");
		l6 = new Label("");
		l7 = new Label("");
		l8 = new Label("");
		l9 = new Label("");
		l10 = new Label("");
		l11 = new Label("");
		l12 = new Label("");
		l13 = new Label("");
		l14 = new Label("");
		l15 = new Label("");
		g1 = new GridLayout(2,4);
		p1.setBackground(Color.BLACK);
		p2.setBackground(Color.BLACK);
		b1.setBackground(Color.black);
		b2.setBackground(Color.black);
		b1.setForeground(Color.white);
		b2.setForeground(Color.white);
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		Toolkit tool=Toolkit.getDefaultToolkit();
		img=tool.getImage("C:\\Users\\odae\\work\\movieProject\\src\\movie\\Movie.jpg");				
		p1.setLayout(new BorderLayout());
		p1.add(view);
		
		p2.setLayout(g1);
		p2.add(l1);
		p2.add(b1);
		p2.add(b2);
		p2.add(l6);
		p2.add(l2);
		p2.add(l3);
		p2.add(l4);
		p2.add(l5);
		
		f.setLayout(new BorderLayout());
		f.add(p6,"North");
		f.add(p1,"Center");
		f.add(p2,"South");
		f.setSize(400,400);
		f.setLocation(800, 300);
		f.setVisible(true);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);;
			}
		});
	}
		Canvas view = new Canvas() {
			public void paint(Graphics g) {
		g.drawImage(img,0,0,this);
	}
};

		public static void main(String[] args) {
			new Join();
		}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == b1) {			//회원가입
			new MemberDialog();
		}
		else if(obj == b2) {
			f.dispose();
			new Login();//로그인

		}
	}
}
