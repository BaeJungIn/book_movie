package movie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MovieSeat extends JFrame implements ActionListener {
   JPanel p1, p2, p3, p4, p5;
   JLabel lb;
   JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btnReser;
   DBConnection db = DBConnection.getInstance();
   Connection conn = db.getConnection();
   ResultSet rs = null;
   PreparedStatement pstmt = null;
   Statement stmt = null;
   static String seatinfo;
   int cnt = 0;
   String moviecode;
   String theater;
   String seatinfo2;
   String thseatinfo;
   String seatinfo3;
   int theater2;
   boolean a1= true,a2= true,a3= true,a4= true,a5= true,
		   b1= true,b2= true,b3= true,b4= true,b5 = true;
   
   public MovieSeat(String data) {
      MovieReservation mr = new MovieReservation(data);
      mr.setVisible(false);
      setTitle("¡¬ºÆº±≈√");
      setSize(600, 600);

      p1 = new JPanel(new BorderLayout());
      p1.setBounds(0, 0, 590, 150);

      p2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 30));
      p2.setBounds(0, 150, 590, 150);

      p3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 30));
      p3.setBounds(0, 300, 590, 150);

      p4 = new JPanel(new FlowLayout());
      p4.setBounds(0, 450, 590, 150);

      p5 = new JPanel();

      p1.setBackground(Color.black);
      p2.setBackground(Color.black);
      p3.setBackground(Color.black);
      p4.setBackground(Color.black);

      lb = new JLabel("                   ¡¬ºÆ¿ª º±≈√«ÿ¡÷ººø‰.");
      lb.setFont(new Font("∏º¿∫∞ÌµÒ", Font.PLAIN, 30));
      lb.setForeground(Color.white);

      btn1 = new JButton("a1");
      btn1.setPreferredSize(new Dimension(80, 80));

      btn2 = new JButton("a2");
      btn2.setPreferredSize(new Dimension(80, 80));

      btn3 = new JButton("a3");
      btn3.setPreferredSize(new Dimension(80, 80));

      btn4 = new JButton("a4");
      btn4.setPreferredSize(new Dimension(80, 80));

      btn5 = new JButton("a5");
      btn5.setPreferredSize(new Dimension(80, 80));

      btn6 = new JButton("b1");
      btn6.setPreferredSize(new Dimension(80, 80));

      btn7 = new JButton("b2");
      btn7.setPreferredSize(new Dimension(80, 80));

      btn8 = new JButton("b3");
      btn8.setPreferredSize(new Dimension(80, 80));

      btn9 = new JButton("b4");
      btn9.setPreferredSize(new Dimension(80, 80));

      btn10 = new JButton("b5");
      btn10.setPreferredSize(new Dimension(80, 80));

      btnReser = new JButton("øπæ‡«œ±‚");
      
      
      try {
            System.out.println("dd");
            String sqlseat = "select seatinfo, seatstate from team2_movietheater where seatstate=0 and moviecode ='"+MovieReservation.code+"'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlseat);
            System.out.println(MovieReservation.code);
            while(rs.next()) {
               theater2 = rs.getInt("seatstate");
               seatinfo3= rs.getString("seatinfo");
               System.out.print(theater2);
               System.out.println(seatinfo3);
               if(btn1.getText().equals(seatinfo3)) {
                  a1=false;
               }
               if(btn2.getText().equals(seatinfo3)) {
                  a2=false;
               }
               if(btn3.getText().equals(seatinfo3)) {
                  a3=false;
               }
               if(btn4.getText().equals(seatinfo3)) {
                  a4=false;
               }
               if(btn5.getText().equals(seatinfo3)) {
                  a5=false;
               }
               if(btn6.getText().equals(seatinfo3)) {
                  b1=false;
               }
               if(btn7.getText().equals(seatinfo3)) {
                  b2=false;
               }
               if(btn8.getText().equals(seatinfo3)) {
                  b3=false;
               }
               if(btn9.getText().equals(seatinfo3)) {
                  b4=false;
               }
               if(btn10.getText().equals(seatinfo3)) {
                  b5=false;
               }
            }
            
         } catch (SQLException e2) {
            e2.printStackTrace();
         }
         btn1.setEnabled(a1);
         btn2.setEnabled(a2);
         btn3.setEnabled(a3);
         btn4.setEnabled(a4);
         btn5.setEnabled(a5);
         btn6.setEnabled(b1);
         btn7.setEnabled(b2);
         btn8.setEnabled(b3);
         btn9.setEnabled(b4);
         btn10.setEnabled(b5);
      
      
      
      
      btn1.addActionListener(this);
      btn2.addActionListener(this);
      btn3.addActionListener(this);
      btn4.addActionListener(this);
      btn5.addActionListener(this);
      btn6.addActionListener(this);
      btn7.addActionListener(this);
      btn8.addActionListener(this);
      btn9.addActionListener(this);
      btn10.addActionListener(this);
      btnReser.addActionListener(this);

      p1.add(lb);
      p2.add(btn1);
      p2.add(btn2);
      p2.add(btn3);
      p2.add(btn4);
      p2.add(btn5);

      p3.add(btn6);
      p3.add(btn7);
      p3.add(btn8);
      p3.add(btn9);
      p3.add(btn10);

      p4.add(btnReser);

      add(p1);
      add(p2);
      add(p3);
      add(p4);
      add(p5);
      
      setLocation(1200, 300);
      seatinfo = "";
      setVisible(true);

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      Object ob = e.getSource();
      if(cnt < MovieReservation.seatnum) {
          if (ob == btn1) {
             cnt += 1;
             btn1.setEnabled(false);
             seatinfo += " a1";
          }
          if (ob == btn2) {
             cnt += 1;
             btn2.setEnabled(false);
             seatinfo += " a2";
          }
          if (ob == btn3) {
             cnt += 1;
             btn3.setEnabled(false);
             seatinfo += " a3";
          }
          if (ob == btn4) {
             cnt += 1;
             btn4.setEnabled(false);
             seatinfo += " a4";
          }
          if (ob == btn5) {
             cnt += 1;
             btn5.setEnabled(false);
             seatinfo += " a5";
          }
          if (ob == btn6) {
             cnt += 1;
             btn6.setEnabled(false);
             seatinfo += " b1";
          }
          if (ob == btn7) {
             cnt += 1;
             btn7.setEnabled(false);
             seatinfo += " b2";
          }
          if (ob == btn8) {
             cnt += 1;
             btn8.setEnabled(false);
             seatinfo += " b3";
          }
          if (ob == btn9) {
             cnt += 1;
             btn9.setEnabled(false);
             seatinfo += " b4";
          }
          if (ob == btn10) {
             cnt += 1;
             btn10.setEnabled(false);
             seatinfo += " b5";
          }
       }
      if (ob == btnReser) {
    	  dispose();
         setVisible(false);
      }
      System.out.println(seatinfo);   
      } 
      
   }
