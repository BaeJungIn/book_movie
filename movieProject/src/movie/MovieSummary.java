package movie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MovieSummary extends JFrame {
   JPanel p1, p2, p3, p4;
   JLabel jlMovieName, jlGrade, jlOpen, jlGenre, jlSummary, jlTime;
   JLabel jlGrade2, jlOpen2, jlGenre2, jlTime2;
   JLabel lbImg;
   JTextArea taSummary;
   JButton btnReser;
   ImageIcon ic;
   DBConnection db = DBConnection.getInstance();
   Connection conn = db.getConnection();
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   MovieInfo mi;

   public MovieSummary(String data) {
      
      setTitle("영화정보확인");
      setSize(700, 500);
   
      
      p1 = new JPanel();
      p1.setBounds(20, 30, 250, 350);
      p2 = new JPanel(new GridLayout(5,1));
      p2.setBounds(270, 30, 380, 350);
      p3 = new JPanel();
      p3.setBounds(250, 390, 100, 100);
      p4 = new JPanel();

      p1.setBackground(Color.black);
      p2.setBackground(Color.black);
      p3.setBackground(Color.black);
      p4.setBackground(Color.black);

      jlMovieName = new JLabel();
      jlMovieName.setForeground(Color.white);
      
      
      if(data.equals("군함도")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\gunhamdo.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("슈퍼배드3")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\superbaed.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("덩케르크")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\dunkrik.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("스파이더맨:홈커밍")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\spiderman.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("\"극장판 짱구는 못말려 : 습격!! 외계인 덩덩이\"")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\zzanggu.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("플립")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\flip.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("47미터")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\47m.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("카3 : 새로운 도전")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\car3.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("옥자")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\okja.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("내 사랑")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\mylove.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("\"오즈 : 신기한 마법가루\"")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\oz.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("송 투 송")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\songtosong.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("예수는 역사다")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\jesus.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("프란츠")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\prantz.jpg");
         lbImg = new JLabel(ic);
      }else if(data.equals("위시 어폰")){
         ic = new ImageIcon("C:\\Users\\한채리\\workspace\\team2\\src\\movieReservation\\wish.jpg");
         lbImg = new JLabel(ic);
      }
      
      
      lbImg.setBounds(50, 20, 100, 100);
      

      jlMovieName.setFont(new Font("맑은고딕", Font.BOLD, 12));
      jlMovieName.setForeground(Color.white);
      
      jlGrade = new JLabel("     등급 : ");
      jlGrade.setForeground(Color.white);
      jlGrade.setFont(new Font("맑은고딕", Font.PLAIN, 16));

      jlGrade2 = new JLabel();
      jlGrade2.setForeground(Color.white);
      jlGrade2.setFont(new Font("돋움", Font.PLAIN, 16));

      jlOpen = new JLabel("    개봉일 : ");
      jlOpen.setForeground(Color.white);
      jlOpen.setFont(new Font("돋움", Font.PLAIN, 16));

      jlOpen2 = new JLabel();
      jlOpen2.setForeground(Color.white);
      jlOpen2.setFont(new Font("돋움", Font.PLAIN, 16));

      jlGenre = new JLabel("    장르 : ");
      jlGenre.setForeground(Color.white);
      jlGenre.setFont(new Font("돋움", Font.PLAIN, 16));

      jlGenre2 = new JLabel();
      jlGenre2.setForeground(Color.white);
      jlGenre2.setFont(new Font("돋움", Font.PLAIN, 16));

      jlSummary = new JLabel("    요약 : ");
      jlSummary.setBounds(60, 40, 100, 100);
      jlSummary.setForeground(Color.white);
      jlSummary.setFont(new Font("돋움", Font.PLAIN, 16));

      taSummary = new JTextArea();
      JScrollPane scrollPane = new JScrollPane(taSummary, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      taSummary.setBackground(Color.black);
      taSummary.setForeground(Color.white);
      taSummary.setFont(new Font("돋움", Font.PLAIN, 14));
      
      jlTime = new JLabel("    상영시간 : ");
      jlTime.setForeground(Color.white);
      jlTime.setFont(new Font("돋움", Font.PLAIN, 16));

      jlTime2 = new JLabel();
      jlTime2.setForeground(Color.white);
      jlTime2.setFont(new Font("돋움", Font.PLAIN, 16));

      btnReser = new JButton("예매하기");
      btnReser.setBounds(350, 400, 10, 10);
      btnReser.setBackground(Color.black);
      btnReser.setForeground(Color.WHITE);
      btnReser.setPreferredSize(new Dimension(100, 40));
      
      p1.add(lbImg);
      p1.add(jlMovieName);

      p2.add(jlGrade);
      p2.add(jlGrade2);
      p2.add(jlOpen);
      p2.add(jlOpen2);
      p2.add(jlGenre);
      p2.add(jlGenre2);
      p2.add(jlSummary);
      p2.add(scrollPane
    		  );
      p2.add(jlTime);
      p2.add(jlTime2);

      p3.add(btnReser);

      add(p1);
      add(p2);
      add(p3);
      add(p4);
      setLocation(600, 300);
      setVisible(true);

      try {
         String sql = "select "
               + "moviename, movielevel, openday, "
               + "contents, story, movietime "
               + "from team2_movielist "
               + "where moviename = ? ";

         pstmt = conn.prepareStatement(sql);

         pstmt.setString(1, data);

         rs = pstmt.executeQuery();
         
         
         if (rs.next()) {
            
            
            String name = rs.getString(1);
            String grade = rs.getString(2);
            String open = rs.getString(3);
            String genre = rs.getString(4);
            String summary = rs.getString(5);
            String movietime = rs.getString(6);
            
            
            
            jlMovieName.setText(name);
            jlGrade2.setText(grade);
            jlOpen2.setText(open);
            jlGenre2.setText(genre);
            taSummary.setText(summary);
            jlTime2.setText(movietime);
            
            
            
 
            
         } 

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();

         } catch (SQLException ee) {
            ee.printStackTrace();
         }
      }
   

      btnReser.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            new MovieReservation(data);
            dispose(); 
         }
      });

   }

}