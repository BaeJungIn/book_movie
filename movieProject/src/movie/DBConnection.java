package movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//DB ���� Ŭ����, ������ �����̲��� �����ǰ� ���������Ƕ� �����Ҷ����� �ٲ������
public class DBConnection {
   public static DBConnection instance = new DBConnection();
   Connection conn = null;
   public DBConnection() {
      String user = "root";
      String pw = "oracle";
      
      try { //�ѹ��� ����ǰ� ���̻� ������� ����
         Class.forName("com.mysql.jdbc.Driver");
         System.out.println("Driver successful");
         conn = DriverManager.getConnection("jdbc:mysql://59.29.224.45:3306/test", user, pw);
         System.out.println("Connection successful");
         //DB Connection
      } catch (SQLException e) {
         e.printStackTrace();
      }catch(ClassNotFoundException ex) {
         ex.printStackTrace();
   }
   }
   
   public static DBConnection getInstance() {
      if(instance == null) {
         instance = new DBConnection();         
      }
      return instance;
   }
   public Connection getConnection() {
      return conn;
   }
}