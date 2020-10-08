package movie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.BoldAction;


public class MovieCheck extends JDialog {
   JPanel p1, p2;
   static List list;
   JButton moviecancel, reverse;
   JLabel label;
   Font font;

   DBConnection db = DBConnection.getInstance();
   Connection conn = db.getConnection();
   PreparedStatement pstmt = null;
   Statement stmt = null;
   ResultSet rs = null;
   ResultSetMetaData rsmd = null;
   String result;
   String point;
   String moviepo;
   int movieco;
   int count, count2;
   String thmoviecode, theater, seatinfo2;
   String moviecode;
   
   public MovieCheck() {
      p1 = new JPanel();
      p2 = new JPanel();
      label = new JLabel("예매 내역");
     
      list = new List();
      moviecancel = new JButton("예매취소");
      reverse = new JButton("메인으로");
      moviecancel.setBackground(Color.black);
      moviecancel.setForeground(Color.WHITE);
      reverse.setBackground(Color.black);
      reverse.setForeground(Color.WHITE);
      
      p1.setBackground(Color.black);
      label.setForeground(Color.WHITE);
      font = new Font("맑은 고딕", Font.BOLD, 25);
      label.setFont(font);
      
      p1.add(label, "Center");
      add(list, "Center");
      p2.add(moviecancel);
      p2.add(reverse);
      p2.setBackground(Color.black);
      
      
      add(p1, "North");
      add(p2, "South");
      setSize(600,600);
      setLocation(800, 300);
      setVisible(true);
      
       
       
       try {
             String sql = "select bookcode, moviename, time from team2_book where userid='"+Login.id+"'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            int dd = cols/3;
            list.clear();
            
            while(rs.next()) {
               String checklist = "";
               for(int i=1; i<=cols; i++) {      
                  checklist += rs.getString(i)+",";
                  if(i%3==0) {
                  list.add(checklist);
                  i+=3;
                  }
               }
            }
         } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
         }
       
          reverse.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               new Main();
               setVisible(false);
               
            }
         });
         moviecancel.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               
               try {
                  String sqldel = "select bookcode from team2_book where userid='"+Login.id+"'";
                  stmt = conn.createStatement();
                  rs = stmt.executeQuery(sqldel);
                  result = list.getSelectedItem();
                  StringTokenizer st = new StringTokenizer(result, ",");
                  String token = st.nextToken();
                  System.out.println(token);
                  while(rs.next()) {
                     if(rs.getString("bookcode").equals(token)) {
                        String sql3 = "select moviepoint,count, moviecode from team2_book where bookcode='"+token+"'";
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sql3);
                        while(rs.next()) {
                           moviepo = rs.getString("moviepoint");
                           count = rs.getInt("count");
                           moviecode = rs.getString("moviecode");
                        }
                        
                        try {
                        String sqlseat = "select moviecode, theater, seatinfo from team2_book where bookcode='"+token+"'";
                        stmt = conn.createStatement();
                        rs = stmt.executeQuery(sqlseat);
                            while(rs.next()) {
                               System.out.println("-3");
                               thmoviecode = rs.getString("moviecode");
                               theater = rs.getString("theater");
                               seatinfo2= rs.getString("seatinfo");
                            }
                            StringTokenizer st2 = new StringTokenizer(seatinfo2, " ");
                            try {
                                while(st2.hasMoreTokens()) {
                                	System.out.println("-2");
                                    String sqlseatinfo = "update team2_movietheater set seatstate ='1' where moviecode ='"+moviecode+"' and theater='"+theater+"' and seatinfo='"+st2.nextToken()+"'";
                                    stmt = conn.createStatement();
                                    stmt.executeUpdate(sqlseatinfo);
                                }
                               
                        } catch (SQLException e2) {
                           // TODO: handle exception
                        }
                            
                           
                     } catch (SQLException e2) {
                        e2.printStackTrace();
                     }
                        
                        String sql2 = "delete from team2_book where bookcode='"+token+"'";
                        stmt = conn.createStatement();
                        int result = stmt.executeUpdate(sql2);
                        System.out.println(result);
                        try {
                            String sqlpoint = "select count from team2_movielist where moviecode = '"+moviecode+"'";
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(sqlpoint);
                            while(rs.next()) {
                               count2 = rs.getInt("count");
                               System.out.println(count);
                            }
                               int mypo2 = count2+count;
                               String sqlpoint3 = "update team2_movielist set count ='"+mypo2+"'where moviecode = '"+moviecode+"'";
                               stmt = conn.createStatement();
                               stmt.executeUpdate(sqlpoint3);
                               
/*                               int index = list.getSelectedIndex();
                               list.remove(index);*/
                               
                              
                        
                     } catch (SQLException e2) {
                        e2.printStackTrace();
                     }
                        try {
                               String sqlpoint = "select userpoint from team2_member where userid = '"+Login.id+"'";
                               stmt = conn.createStatement();
                               rs = stmt.executeQuery(sqlpoint);
                               while(rs.next()) {
                                  point = rs.getString("userpoint");
                                  System.out.println(point);
                               }
                                  int mypo = Integer.parseInt(point)+Integer.parseInt(moviepo);
                                  System.out.println(moviepo);
                                  String sqlpoint2 = "update team2_member set userpoint ='"+mypo+"'where userid = '"+Login.id+"'";
                                  stmt = conn.createStatement();
                                  stmt.executeUpdate(sqlpoint2);
                                  
                                  int index = list.getSelectedIndex();
                                  list.remove(index);
                                  
                           
                        } catch (SQLException e2) {
                           e2.printStackTrace();
                        }
                     }
                  }
                  
                  
               } catch (SQLException e1) {
                  e1.printStackTrace();
               }   
               
               
                   
            }
         });
         
         
      }

   
}