package movie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Point extends JFrame implements ActionListener{
	JButton charge, exit;
	JLabel la, la2, title;
	JTextField jt;
	JTextArea ta;
	JPanel jp1,jp2,jp3;
	Font font;
	String point;
	DBConnection db = DBConnection.getInstance();
	Connection conn = db.getConnection();
	Statement stmt = null;
	ResultSet rs = null;
	int result = -1;
	public Point() {
		// TODO Auto-generated constructor stub
		setTitle("포인트 충전");
		charge = new JButton("충전");
		exit = new JButton("닫기");
		la = new JLabel("금액 입력");
		la2 = new JLabel("보유 포인트");
		jt = new JTextField();
		jp1 = new JPanel();
		jp2 = new JPanel(new GridLayout(2,2));
		jp3 = new JPanel();
		ta = new JTextArea();
		title = new JLabel("포인트 충전");
		font = new Font("맑은 고딕", Font.BOLD, 25);
		title.setFont(font);
		title.setForeground(Color.white);
		jp1.setBackground(Color.black);
		jp1.add(title, "center");
		
		la.setForeground(Color.white);
		la2.setForeground(Color.white);
		jp2.setBackground(Color.black);

		jp2.add(la);
		jp2.add(jt);
		jp2.add(la2);
		jp2.add(ta);
		ta.setEditable(false);
		
		charge.setBackground(Color.BLACK);
		charge.setForeground(Color.white);
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.white);
		jp3.setBackground(Color.black);
		jp3.add(charge);
		jp3.add(exit);
		add(jp1,"North");
		add(jp2,"Center");
		add(jp3,"South");
		setSize(200, 200);
		setLocation(800, 300);
		setVisible(true);
		
		charge.addActionListener(this);
		exit.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == charge) {
			try {
				String mypoint = "select userpoint from team2_member where userid = '"+Login.id+"'";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(mypoint);
				while(rs.next()) {
					point = rs.getString("userpoint");
					if(point == null) {
						point = "0";
					}
					
				}
				int mypo = Integer.parseInt(point)+Integer.parseInt(jt.getText());
				ta.setText(mypo+"");
				String sql = "update team2_member set userpoint ='"+mypo+"'where userid = '"+Login.id+"'";
				stmt = conn.createStatement();
				result = stmt.executeUpdate(sql);
				
			} catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}else if(e.getSource() == exit) {
			setVisible(false);
		}
		
	}
}
