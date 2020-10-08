package movie;

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
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

//장르 추천 영화
public class MyContents extends JFrame{
	JButton jb;
	List list;
	JPanel p, p2;
	Font font;
	JLabel label;
	DBConnection db = DBConnection.getInstance();
	Connection conn = db.getConnection();
	Statement stmt = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	String con;
	public MyContents() {
		setTitle("장르추천");
		// TODO Auto-generated constructor stub
		jb = new JButton("닫기");
		list = new List();
		p = new JPanel();
		p2 = new JPanel();
		label = new JLabel("장르 추천");
		font = new Font("맑은 고딕", Font.BOLD, 25);
		p2.add(label,"Center");
		label.setFont(font);
		label.setForeground(Color.white);
		p2.setBackground(Color.BLACK);
		add(p2,"North");
		p.setBackground(Color.black);
		jb.setBackground(Color.BLACK);
		jb.setForeground(Color.white);
		p.add(jb, "South");
		add(list,"Center");
		add(p,"East");
		setSize(300, 400);
		setLocation(800, 300);
		setVisible(true);
		
		try {
			String mycon = "select contents from team2_member where userid = '"+Login.id+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(mycon);
			while(rs.next()) {
				con = rs.getString("contents");
			}
			String sql = "select distinct moviename from team2_movielist where contents = '"+con+"'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			int cols = rsmd.getColumnCount();
			while(rs.next()) {
				for(int i=1; i<=cols; i++) {
					list.add(rs.getString(i));
				}
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
	}
}
