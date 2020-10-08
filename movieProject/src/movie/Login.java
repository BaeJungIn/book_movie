package movie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class Login  extends JDialog implements ActionListener{
   public JPanel pa1, pa2;
   public JLabel lb1, lb2, lb3, lb4, lb5, lb6, lb7;
   public JTextField id_jt;
   public JPasswordField pw_jt;
   public JButton b1, b2;
   String pw;
   public static String id;
   public Login(){ 
      pa1 = new JPanel(new FlowLayout());
      pa1.setBackground(Color.black);
      lb1 = new JLabel("아 이 디 ");
      lb2 = new JLabel("비밀번호");
      lb1.setForeground(Color.white);
      lb2.setForeground(Color.white);
      lb3 = new JLabel();
      lb4 = new JLabel();
      lb5 = new JLabel();
      lb6 = new JLabel(); 
      lb7 = new JLabel();
      id_jt = new JTextField();
      pw_jt = new JPasswordField();
      b1 = new JButton("확 인");
      b2 = new JButton("취 소");
      b1.setForeground(Color.white);
      b2.setForeground(Color.white);
      b1.setBackground(Color.black);
      b2.setBackground(Color.black);
      setTitle("로그인");
      
      id_jt.setPreferredSize(new Dimension(100,25));
      pw_jt.setPreferredSize(new Dimension(100,25));
      
   
      pa1.add(lb1);
      pa1.add(id_jt);
      pa1.add(lb2);
      pa1.add(pw_jt);   
      pa1.add(lb6);
      pa1.add(b1);
      pa1.add(b2);
      
      
      add(pa1);
      
      b1.addActionListener(this);
      b2.addActionListener(this);
      
      setLocation(800,300);
      setSize(200,150);
      setVisible(true);
   }
   public void actionPerformed(ActionEvent e) {
         Object obj = e.getSource();
         DBConnection db = DBConnection.getInstance();
         Connection conn = db.getConnection();
         PreparedStatement pstmt = null;
         
         if(obj == b1) {
            try {
                  Statement stmt = null;
                  ResultSet result = null; 
                  
                  id = id_jt.getText();
                  pw = pw_jt.getText();
                  
                  String sql = "select userid,pwd from team2_member where userid='"+id+"' and pwd='"+pw+"'";
             
                  stmt = conn.createStatement();
                  result = stmt.executeQuery(sql);
                  
                  if(result.next()) {
//                  if(result.getString("userid").equals(id) && result.getString("pwd").equals(pw)) {
                        new Main();
                        setVisible(false);
                  }else {
                     JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인해주세요!");
                  }
                  
                  
               } catch (SQLException ex) {
                  ex.printStackTrace();
               }

         }
         if(obj == b2) {   
            dispose();
         }
         
         
      }
}