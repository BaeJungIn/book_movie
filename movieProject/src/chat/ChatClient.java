package chat;
import javax.swing.*;
import movie.*;




public class ChatClient extends Login{
	
  public ChatClient(String logonID)  {
	  String id = getLogonID();
	     
	     String args[] = {id};
	       try{
	           ClientThread thread = new ClientThread();
	           thread.start();
	           thread.requestLogon(id);
	           
	           //로그인창 꺼지게
	           setVisible(false);

	       }catch(Exception e){
	         System.out.println(e);
	       }
	}

public static String getLogonID(){
	 String logonID = id;
	    
	    try{
	      while(logonID.equals("")){
	        logonID = JOptionPane.showInputDialog("로그온 ID를 입력하세요.");
	      }
	    }catch(NullPointerException e){
	      System.exit(0);
	    }
	    return logonID;
  }

  public static void main(String args[]){
	  if(args.length > 0 ) {
	         new ChatClient(args[0]);
	      } else {
	    	  //
	         new ChatClient("59.29.224.35");
	      }
	  String id = getLogonID();
    try{
      if (args.length == 0){
        ClientThread thread = new ClientThread();
        thread.start();
        thread.requestLogon(id);
      } else if (args.length == 1){
        ClientThread thread = new ClientThread(args[0]);
        thread.start();
        thread.requestLogon(id);
      } 
    }catch(Exception e){
      System.out.println(e);
    }
  }
}

