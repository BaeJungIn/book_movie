package movie;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import chat.ChatClient;
import chat.ChatServer;

public class Main extends JDialog implements ActionListener {
	public JDialog d;
	public JButton btnexit1, btnyemae2, btnyemaeok3, btnpoint4, btngood5, btnchatting6;
	public Panel p1, p2;
	public Label l1, l2, l3;
	public Frame f;
	public JFrame jf;
	public Font font;
	public Image img;
	
	public Main() {
		d = new JDialog();
		btnexit1 = new JButton("닫기");
		btnyemae2 = new JButton("예매");
		btnyemaeok3 = new JButton("예매 확인");
		btnpoint4 = new JButton("포인트 충전");
		btngood5 = new JButton("장르 추천");
		btnchatting6 = new JButton("채팅");
		p1 = new Panel();
		p2 = new Panel();
		l1 = new Label();
		l2 = new Label();
		l3 = new Label();
		f = new Frame();
		setTitle("메인 화면");
		font = new Font("맑은 고딕", Font.BOLD, 25);

		p1.setBackground(Color.BLACK);
		p2.setBackground(Color.BLACK);
		l1.setBackground(Color.BLACK);
		l2.setBackground(Color.BLACK);
		l3.setBackground(Color.BLACK);
		
		btnexit1.setBackground(Color.black);
		btnyemae2.setBackground(Color.black);
		btnyemaeok3.setBackground(Color.black);
		btnpoint4.setBackground(Color.black);
		btngood5.setBackground(Color.black);
		btnchatting6.setBackground(Color.black);
		
		btnexit1.setForeground(Color.white);
		btnyemae2.setForeground(Color.white);
		btnyemaeok3.setForeground(Color.white);
		btnpoint4.setForeground(Color.white);
		btngood5.setForeground(Color.white);
		btnchatting6.setForeground(Color.white);

		btnexit1.addActionListener(this);
		btnyemae2.addActionListener(this);
		btnyemaeok3.addActionListener(this);
		btnpoint4.addActionListener(this);
		btngood5.addActionListener(this);
		btnchatting6.addActionListener(this);

		p1.setLayout(new GridLayout(2, 5));
		
		Toolkit tool=Toolkit.getDefaultToolkit();
		img=tool.getImage("C:/Users/Bae/Desktop/정인학교/영화예매/movieProject/src/movie/MainPhoto3.jpg");				
		p1.setLayout(new BorderLayout());
		p1.add(view);

		p2.setLayout(new GridLayout(2, 3));
		p2.add(btnyemae2); // 예매버튼
		p2.add(btnpoint4); // 포인트 충전버튼
		p2.add(btnyemaeok3); // 예매확인버튼
		p2.add(btngood5); // 장르추천버튼
		p2.add(btnchatting6); //채팅
		p2.add(btnexit1); // 닫기

		setLayout(new BorderLayout());
		add(p1, "Center");
		add(p2, "South");
		add(l1, "North");
		add(l2, "East");
		add(l3, "West");
		setLocation(800, 300);

		setSize(400, 300);
		setVisible(true);
	}

	Canvas view = new Canvas() {
		public void paint(Graphics g) {
	g.drawImage(img,0,0,this);
}
};
	public static void main(String args[]) {
		new Main();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnexit1) {
			System.exit(0);
		} else if (e.getSource() == btnyemae2) {
			new MovieInfo();
		} else if (e.getSource() == btnyemaeok3) {
			new MovieCheck();
		} else if (e.getSource() == btnpoint4) {
			new Point();
		} else if (e.getSource() == btngood5) {
			new MyContents();
		} else if (e.getSource() == btnchatting6) {
			
			new Thread(new Runnable() {
				public void run() {
					new ChatServer(2777);
				}
			}).start();
			new ChatClient(paramString());
			setVisible(false);
		}

	}
}
