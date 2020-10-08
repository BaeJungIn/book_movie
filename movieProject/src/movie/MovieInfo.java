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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//상영중 영화 목록
public class MovieInfo extends JFrame {

   JButton jb;
   List list;
   JPanel p, p2;
   JLabel label;
   Font font;
   DBConnection db = DBConnection.getInstance();
   Connection conn = db.getConnection();
   Statement stmt = null;
   ResultSet rs = null;
   ResultSetMetaData rsmd = null;

   ArrayList<String> data;

   public MovieInfo() {
      data = new ArrayList<String>();
      setTitle("상영중 영화");
      // TODO Auto-generated constructor stub
      jb = new JButton("예매하기");
      list = new List();
      p = new JPanel();
      p2 = new JPanel();
      p.add(jb);

      label = new JLabel("상영중 영화");
      font = new Font("맑은 고딕", Font.BOLD, 25);
      p2.add(label, "Center");
      label.setFont(font);
      label.setForeground(Color.white);
      p2.setBackground(Color.BLACK);
      add(p2, "North");
      add(list, "Center");
      add(p, "East");
      p.setBackground(Color.black);
      jb.setBackground(Color.BLACK);
      jb.setForeground(Color.white);
      setSize(600, 600);
      setLocation(700, 300);
      setVisible(true);

      try {
         String sql = "select distinct moviename from team2_movielist";
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         rsmd = rs.getMetaData();
         int cols = rsmd.getColumnCount();
         while (rs.next()) {
            for (int i = 1; i <= cols; i++) {
               String item = rs.getString(i);
               list.add(item);
               data.add(item);
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
            // 예매하기 버튼 눌렀을 때

            new MovieSummary(list.getSelectedItem());
            dispose();
         }
      });
    
      
   }
   public static void main(String args[]) {
		new MovieInfo();
	}
 
}