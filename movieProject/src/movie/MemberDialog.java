package movie;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MemberDialog extends JDialog implements ActionListener{
	public JDialog d;
	public JButton btnMember1,btnExit2,btnOver3;
	public Panel p1,p2,p3,p4;
	public Label l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17,l18,l19,
				l20,l21,l22,l23,l24,l25,l26,l27;
	public TextField t1,t2,t3,t4,t5;
	public Choice cho;
	public JFrame jf;
	public JPanel jp;
	public Frame f;
	public Font font;
	
	public MemberDialog() {
		d = new JDialog(this);
		f = new Frame();
		setTitle("회원가입");
		btnMember1 = new JButton("회원가입");
		btnExit2 = new JButton("취소");
		btnOver3 = new JButton("중복확인");
		p1 = new Panel();
		p2 = new Panel();
		p3 = new Panel();
		p4 = new Panel();
		btnMember1.addActionListener(this);
		btnExit2.addActionListener(this);
		btnOver3.addActionListener(this);
		l1 = new Label("");
		l2 = new Label("이름");
		l3 = new Label("");
		l4 = new Label("아이디");
		l5 = new Label("");
		l6 = new Label("비밀번호");
		l7 = new Label("");
		l8 = new Label("E-mail");
		l9 = new Label("");
		l10 = new Label("");
		l11 = new Label("");
		l12 = new Label("");
		l13 = new Label("");
		l14 = new Label("");
		l15 = new Label("");
		l16 = new Label("");
		l17 = new Label("");
		l18 = new Label("");
		l19 = new Label("");
		l20 = new Label("");
		l21 = new Label("선호장르");
		l22 = new Label("");
		l23 = new Label("");
		l24 = new Label("");
		l25 = new Label("");
		l26 = new Label("");
		l27 = new Label("");
		t1 = new TextField();
		t2 = new TextField();
		t3 = new TextField();
		t4 = new TextField();
		t5 = new TextField();
		cho = new Choice();
		cho.add("로맨스");
		cho.add("액션");
		cho.add("공포");
		cho.add("애니메이션");
		cho.add("SF");
		cho.add("모험");
		cho.add("드라마");
		
		p1.setBackground(Color.black);
		p2.setBackground(Color.black);
		p3.setBackground(Color.black);
		p4.setBackground(Color.black);
		l1.setBackground(Color.black);
		
		btnMember1.setBackground(Color.black);
		btnExit2.setBackground(Color.black);
		btnOver3.setBackground(Color.black);
		
		btnMember1.setForeground(Color.white);
		btnExit2.setForeground(Color.white);
		btnOver3.setForeground(Color.white);
		
		font = new Font("맑은 고딕", Font.BOLD, 23);
	    l2.setFont(font);
	    l2.setForeground(Color.white);
		
	    font = new Font("맑은 고딕", Font.BOLD, 23);
	    l4.setFont(font);
	    l4.setForeground(Color.white);
		
	    font = new Font("맑은 고딕", Font.BOLD, 23);
	    l6.setFont(font);
	    l6.setForeground(Color.white);
		
	    font = new Font("맑은 고딕", Font.BOLD, 23);
	    l8.setFont(font);
	    l8.setForeground(Color.white);
		
	    font = new Font("맑은 고딕", Font.BOLD, 23);
	    l15.setFont(font);
	    l15.setForeground(Color.white);
		
	    font = new Font("맑은 고딕", Font.BOLD, 23);
	    l21.setFont(font);
	    l21.setForeground(Color.white);
		
		
		p1.setLayout(new GridLayout(9,1));
		p1.add(l2);
		p1.add(l3);
		p1.add(l4);
		p1.add(l5);
		p1.add(l6);
		p1.add(l7);
		p1.add(l8);
		p1.add(l20);
		p1.add(l21);
		
		p2.setLayout(new GridLayout(9,1));
		p2.add(t1);
		p2.add(l9);
		p2.add(t2);
		p2.add(l10);
		p2.add(t3);
		p2.add(l11);
		p2.add(t4);
		p2.add(l12);
		p2.add(cho);  //장르선택
		
		
		p3.setLayout(new GridLayout(9,1));
		p3.add(l13);
		p3.add(l14); 
		p3.add(btnOver3); //중복확인버튼
		p3.add(l15);
		p3.add(l22);
		p3.add(l16);
		
		p4.setLayout(new GridLayout(2,5));
		p4.add(l17);
		p4.add(l23);
		p4.add(l24);
		p4.add(l25);
		p4.add(l26);
		p4.add(l27);
		p4.add(btnMember1); //회원가입 버튼
		p4.add(l18);
		p4.add(btnExit2);   //취소버튼
		p4.add(l19);
		
		setLayout(new BorderLayout());
		add(l1,"North");
		add(p1,"West");
		add(p2,"Center");
		add(p3,"East");
		add(p4,"South");
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);;
				
			}
		});
		setSize(450,300);
		setVisible(true);
	}
	
	public void insertData() {
	      DBConnection db = DBConnection.getInstance();
	      Connection conn = db.getConnection();
	      PreparedStatement pstmt = null;
	      String sql = "insert into team2_member values(?,?,?,?,?,?)";
	      int result = -1;
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1,  t1.getText()); // 이름
	         pstmt.setString(2,  t2.getText()); // 아이디
	         pstmt.setString(3,  t3.getText()); // 비밀번호
	         pstmt.setString(4,  t4.getText()); // E-mail
	         pstmt.setString(5,  cho.getSelectedItem()); // 선호장르선택
	         pstmt.setString(6,  null);
	         result = pstmt.executeUpdate();
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }finally {
	         try {
	            if(pstmt != null) pstmt.close();
	         }catch(SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      System.out.println(result == 0 ? "실패" : "성공");
//	      return result;
	}
	
	public boolean idCheck() {
	    DBConnection db = DBConnection.getInstance();
	    Connection conn = db.getConnection();
	    
		boolean contains = false;
		String id = t2.getText();
		String sql = "select * from team2_member where userid='" + id +"'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql); //입력한 id 값을 갖는 회원이 있는지를 db에서 검색해서 결과를 가져옴
			if(rs.next() == true) {
				contains = true;
				System.out.println("아이디 중복이 일어남");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return contains;
	}

	
	public static void main(String args[]) {
		new MemberDialog();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnExit2)
			this.setVisible(false);
		
		if(e.getSource() ==btnMember1) {
			insertData();
			setVisible(false);
		}
		
		if(e.getSource()==btnOver3) {
			boolean contains = idCheck();
			if(contains == true) { //중복이 있는 경우
				new Overlap();
				btnMember1.setEnabled(true);
			}else { //중복이 없는 경우
				new Overlapnot();
				btnMember1.setEnabled(true);
			}
		}
	}
}


	