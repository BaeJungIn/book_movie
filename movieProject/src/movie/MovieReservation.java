package movie;

import java.awt.Color;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MovieReservation extends JFrame {
   JPanel p1, p2, p3;
   JLabel lbMovie, lbMovie2, lbDate, lbTime, lbNum, lbPay;
   JComboBox cbTime, cbDate, cbNum, cbPay;
   JButton btnReser, btnBack, seat;
   DBConnection db = DBConnection.getInstance();
   Connection conn = db.getConnection();
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   int result = -1;
   MovieSummary ms;
   MovieInfo mi;
   String name, mvday, mvtime, pay, coday, cotime, theater, seatinfo;
   static int seatnum, code;
   static int copay;
   String point;
   Statement stmt = null;
   static String bookcode;
   int num;
   int count;
      String theater2;
      String seatinfo2;
      String moviecode;

   public MovieReservation(String data) {
      ms = new MovieSummary(data);
      ms.dispose();
      setTitle("½Ã°£¼±ÅÃ");
      GridLayout grid = new GridLayout(6, 2);
      grid.setVgap(40);
      p1 = new JPanel(grid);
      p2 = new JPanel(new FlowLayout());
      p3 = new JPanel();
      p1.setBounds(0, 20, 400, 400);
      p1.setBackground(Color.black);
      p2.setBounds(30, 418, 400, 100);

      lbMovie = new JLabel("      ¿µÈ­ : ");
      lbMovie.setForeground(Color.white);
      lbMovie.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN, 16));

      lbMovie2 = new JLabel();
      lbMovie2.setForeground(Color.white);
      lbMovie2.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN, 16));
      lbTime = new JLabel("      ³¯Â¥");
      lbTime.setForeground(Color.white);
      lbTime.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN, 16));

      cbTime = new JComboBox();
      cbTime.setForeground(Color.black);
      cbTime.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN, 16));

      lbTime = new JLabel("      ½Ã°£");
      lbTime.setForeground(Color.white);
      lbTime.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN, 16));

      cbTime = new JComboBox();
      cbTime.setForeground(Color.black);
      cbTime.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN, 16));

      lbDate = new JLabel("      ³¯Â¥ ");
      lbDate.setForeground(Color.white);
      lbDate.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN, 16));

      cbDate = new JComboBox();
      cbDate.setForeground(Color.black);
      cbDate.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN, 16));

      lbNum = new JLabel("      ÀÎ¿ø¼ö");
      lbNum.setForeground(Color.white);
      lbNum.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN, 16));

      cbNum = new JComboBox();
      cbNum.setForeground(Color.black);
      cbNum.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN, 16));
      

      lbPay = new JLabel("      ¿ä±Ý");
      lbPay.setForeground(Color.white);
      lbPay.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN, 16));

      cbPay = new JComboBox();
      cbPay.setForeground(Color.black);
      cbPay.setFont(new Font("¸¼Àº°íµñ", Font.PLAIN, 16));

      seat = new JButton("ÁÂ¼® ¼±ÅÃ");
      seat.setBackground(Color.black);
      seat.setForeground(Color.WHITE);
      
      btnReser = new JButton("¿¹¸ÅÇÏ±â");
      btnReser.setBackground(Color.BLACK);
      btnReser.setForeground(Color.WHITE);
      
      btnBack = new JButton("ÀÌÀü");
      btnBack.setBackground(Color.black);
      btnBack.setForeground(Color.white);

      p1.add(lbMovie);
      p1.add(lbMovie2);

      p1.add(lbDate);
      p1.add(cbDate);

      p1.add(lbTime);
      p1.add(cbTime);
      cbTime.disable();

      p1.add(lbNum);
      p1.add(cbNum);
      cbNum.disable();

      p1.add(lbPay);
      p1.add(cbPay);
      cbPay.disable();

      p2.add(seat);
      p2.add(btnReser);
      p2.add(btnBack);
      
      

      add(p1);
      add(p2);
      add(p3);
      
      p2.setBackground(Color.black);
      p3.setBackground(Color.black);
      setSize(450, 520);
      setLocation(800, 300);
      setVisible(true);

      try {
         String sql = "select distinct day from team2_movielist where moviename = ?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, data);
         rs = pstmt.executeQuery();

         name = data;
         lbMovie2.setText(name);
         while (rs.next()) {
            mvday = rs.getString("day");
            cbDate.addItem(mvday);
         }

         cbDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // TODO Auto-generated method stub

               Object ob = e.getSource();
               if (ob == cbDate) {
                  coday = (String) cbDate.getSelectedItem();
                  cbTime.enable();
                  cbTime.removeAllItems();
                  try {
                     String sql = "select distinct time from team2_movielist where moviename = ? and day = ?";
                     pstmt = conn.prepareStatement(sql);
                     pstmt.setString(1, data);
                     pstmt.setString(2, coday);
                     rs = pstmt.executeQuery();

                     name = data;
                     while (rs.next()) {
                        mvtime = rs.getString("time");
                        cbTime.addItem(mvtime);
                     }
                     cbTime.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                           // TODO Auto-generated method stub
                           Object ob = e.getSource();
                           if (ob == cbTime) {
                              cotime = (String) cbTime.getSelectedItem();
                              cbNum.enable();
                              cbNum.removeAllItems();
                              try {
                                 String sql = "select count from team2_movielist where moviename = ? and day = ? and time = ?";
                                 pstmt = conn.prepareStatement(sql);
                                 pstmt.setString(1, data);
                                 pstmt.setString(2, coday);
                                 pstmt.setString(3, cotime);
                                 rs = pstmt.executeQuery();
                                 
                                 while (rs.next()) {
                                    num = rs.getInt("count");
                                 }
                                 for (int i = 1; i <= num; i++) {
                                    cbNum.addItem(i);
                                 }
                              } catch (SQLException e2) {
                                 e2.printStackTrace();
                              }
                              cbNum.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                    // TODO Auto-generated method
                                    // stub
                                    Object ob = e.getSource();
                                    if (ob == cbNum) {
                                       try {
                                          String sql = "select distinct moviepoint from team2_movielist where moviename = ? and day = ? and time = ?";
                                          pstmt = conn.prepareStatement(sql);
                                          pstmt.setString(1, data);
                                          pstmt.setString(2, coday);
                                          pstmt.setString(3, cotime);
                                          rs = pstmt.executeQuery();

                                          name = data;
                                          while (rs.next()) {
                                             pay = rs.getString("moviepoint");
                                             seatnum = (int) cbNum.getSelectedItem();
                                             cbPay.removeAllItems();
                                             cbPay.addItem(Integer.parseInt(pay) * seatnum); 
                                             copay = Integer.parseInt(pay) * seatnum;
                                          }
                                       } catch (Exception e4) {
                                          // TODO: handle
                                          // exception
                                          e4.printStackTrace();
                                       }
                                       cbPay.enable();
                                    }
                                 }
                              });
                           }
                        }
                     });
                  } catch (Exception e3) {
                     // TODO: handle exception
                     e3.printStackTrace();
                  }

               }
            }
         });
      } catch (Exception e2) {
         // TODO: handle exception
         e2.printStackTrace();
      }
      
      
      
      seat.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            try {
               System.out.println("2");
               String sqlgo = "select moviecode, theater from team2_movielist where moviename=? and day=? and time=?";
               pstmt = conn.prepareStatement(sqlgo);
               pstmt.setString(1, name);
               pstmt.setString(2, coday);
               pstmt.setString(3, cotime);
               rs = pstmt.executeQuery();

               System.out.println("3");

               while (rs.next()) {
                  code = rs.getInt(1);
                  theater = rs.getString(2);
                  System.out.println("4");
               }
               }catch (SQLException ex) {
                  ex.printStackTrace();
               }
            new MovieSeat(data);
            

         }

      });
      
      
      

      btnReser.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("1");
            try {
               System.out.println("2");
               String sqlgo = "select moviecode, theater from team2_movielist where moviename=? and day=? and time=?";
               pstmt = conn.prepareStatement(sqlgo);
               pstmt.setString(1, data);
               pstmt.setString(2, coday);
               pstmt.setString(3, cotime);
               rs = pstmt.executeQuery();

               System.out.println("3");

               while (rs.next()) {
                  code = rs.getInt(1);
                  theater = rs.getString(2);
                  System.out.println("4");
               }
               try {
                  String sqlpoint = "select userpoint from team2_member where userid=?";
                  pstmt = conn.prepareStatement(sqlpoint);
                  pstmt.setString(1, Login.id);
                  System.out.println(Login.id);
                  rs = pstmt.executeQuery();
                  while (rs.next()) {
                     point = rs.getString(1);
                     System.out.println(point);
                     if(point == null)
                        point = "0";
                  }
                  if (Integer.parseInt(point) >= copay) {
                     try {
                        System.out.println("5");
                        String sqlinto = "insert into team2_book values(?,?,?,?,?,?,?,?,?,?)";
                        bookcode = Login.id + code;
                        pstmt = conn.prepareStatement(sqlinto);
                        pstmt.setString(1, bookcode);
                        pstmt.setString(2, Login.id);
                        pstmt.setInt(3, code); // moviecode

                        pstmt.setString(4, data); // ¿µÈ­ ÀÌ¸§
                        pstmt.setString(5, coday); // ³¯Â¥
                        pstmt.setString(6, cotime); // ½Ã°£
                        pstmt.setString(7, theater); // theater
                        pstmt.setString(8, null); // seatinfo
                        pstmt.setInt(9, copay); // moviepoint
                        pstmt.setInt(10, seatnum);
                        result = pstmt.executeUpdate();
                        System.out.println("6");
                        
                        
                        
                     } catch (Exception e4) {
                        // TODO: handle exception
                        e4.printStackTrace();
                     }
                     int mypo = Integer.parseInt(point) - copay;
                     String sqlpoint2 = "update team2_member set userpoint ='" + mypo + "'where userid = '"
                           + Login.id + "'";
                     stmt = conn.createStatement();
                     result = stmt.executeUpdate(sqlpoint2);
                     
                     
                     //////////////////////////////////////////////////////////
                     try {
                     	System.out.println("¹«ºñ¸®½ºÆ®");
                     	String sqlseatnum = "select count from team2_movielist where moviecode = '"+MovieReservation.code+"'";
                     	stmt = conn.createStatement();
                     	rs = stmt.executeQuery(sqlseatnum);
                     	while(rs.next()) {
                     		count = rs.getInt("count");
                     		System.out.println(count);
                     		/*if(count == null) {
                              count = "0";
                           }*/
                     	}
                     	int totalcount = count-MovieReservation.seatnum;
                     	
                     	String sql = "update team2_movielist set count ='"+totalcount+"'where moviecode = '"+MovieReservation.code+"'";
                     	stmt = conn.createStatement();
                     	stmt.executeUpdate(sql);
                     } catch (SQLException e2) {
                     	e2.printStackTrace();
                     }
                     
                     try {
                     	System.out.println("ºÏ");
                     	String sqlreser = "update team2_book set seatinfo ='"+MovieSeat.seatinfo+"'where bookcode = '"+MovieReservation.bookcode+"'";
                     	stmt = conn.createStatement();
                     	stmt.executeUpdate(sqlreser);
                     	try {
                     		MovieReservation.bookcode += MovieSeat.seatinfo; 
                     		String sqlbookcode = "update team2_book set bookcode ='"+MovieReservation.bookcode+"'where userid = '"
                     				+Login.id+"' and moviecode ='"+MovieReservation.code+"' and seatinfo='"+MovieSeat.seatinfo+"'";
                     		stmt = conn.createStatement();
                     		stmt.executeUpdate(sqlbookcode);
                     	} catch (SQLException e2) {
                     		e2.printStackTrace();
                     	}
                     } catch (SQLException ee) {
                     	ee.printStackTrace();
                     }
                     
                     try {
                     	String sqlseat = "select moviecode, theater, seatinfo from team2_book where bookcode='"+MovieReservation.bookcode+"'";
                     	System.out.println(MovieReservation.bookcode);
                     	stmt = conn.createStatement();
                     	rs = stmt.executeQuery(sqlseat);
                     	while(rs.next()) {
                     		System.out.println("-3");
                     		moviecode = rs.getString("moviecode");
                     		theater = rs.getString("theater");
                     		seatinfo2= rs.getString("seatinfo");
                     	}
                     	StringTokenizer st = new StringTokenizer(seatinfo2, " ");
                     	try {
                     		while(st.hasMoreTokens()) {
                     			System.out.println("-2");
                     			String sql3 = "update team2_movietheater set seatstate ='0' where moviecode ='"+moviecode+"' and theater='"+theater+"' and seatinfo='"+st.nextToken()+"'";
                     			stmt = conn.createStatement();
                     			stmt.executeUpdate(sql3);
                     		}
                     		
                     	} catch (SQLException e2) {
                     		// TODO: handle exception
                     	}
                     	
                     } catch (SQLException e2) {
                     	e2.printStackTrace();
                     }
                     System.out.println("7");
                     ////////////////////////////////////////////////////

                     
                     dispose();
                     
                  } else if (Integer.parseInt(point) < copay) {
                     JOptionPane.showMessageDialog(null, "Æ÷ÀÎÆ®°¡ ºÎÁ·ÇÕ´Ï´Ù. ÃæÀüÇØÁÖ¼¼¿ä!");
                  }

               } catch (SQLException e2) {
                  e2.printStackTrace();
               }

            } catch (SQLException e2) {
               // TODO: handle exception
            }
                        
            

         }

      });
      
      btnBack.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            new MovieInfo();
            dispose();
         }
      });
   }
}