package chat;
import java.awt.*;
import movie.*;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

class ChatRoomDisplay extends JFrame implements ActionListener, KeyListener,
                                                ListSelectionListener, ChangeListener
{
  private ClientThread cr_thread;
  private String idTo;
  private boolean isSelected;
  public boolean isAdmin;

  private JLabel roomer;
  public JList roomerInfo;
  private JButton coerceOut, sendWord, sendFile, quitRoom;
  private Font font;
  private JViewport view;
  private JScrollPane jsp3;
  public JTextArea messages;
  public JTextField message;
  
  public ChatRoomDisplay(ClientThread thread){
	
	  
	super(thread.getCt_logonID());
    cr_thread = thread;
    isSelected = false;
    isAdmin = false;
    font = new Font("SanSerif", Font.PLAIN, 12);
 /*   roomer.setBackground(Color.black);
    roomer.setForeground(Color.white);
*/
    Container c = getContentPane();
    c.setLayout(null);
    c.setBackground(Color.black);
    c.setForeground(Color.white);

    JPanel p = new JPanel();
    p.setBackground(Color.black);
    p.setForeground(Color.white);
    p.setLayout(null);
    p.setBounds(425, 10, 140, 175);
    p.setBorder(new TitledBorder(
      new EtchedBorder(EtchedBorder.LOWERED), "������"));
    

    roomerInfo = new JList();
    roomerInfo.setFont(font);
    roomerInfo.setBackground(Color.black);
    roomerInfo.setForeground(Color.white);
    JScrollPane jsp2 = new JScrollPane(roomerInfo);
    roomerInfo.addListSelectionListener(this);
    jsp2.setBounds(15, 25, 110, 135);
    p.add(jsp2);
    jsp2.setBackground(Color.black);
    jsp2.setForeground(Color.white);
    
    c.add(p);

    p = new JPanel();
    p.setBackground(Color.black);
    p.setForeground(Color.white);
    p.setLayout(null);
    p.setBounds(10, 10, 410, 340);
    p.setBorder(new TitledBorder(
      new EtchedBorder(EtchedBorder.LOWERED), "ä��â"));

    view = new JViewport();
    messages = new JTextArea();
    messages.setFont(font);
    messages.setEditable(false);
    view.setBackground(Color.black);
    view.setForeground(Color.white);
    view.add(messages);
    view.addChangeListener(this);
    jsp3 = new JScrollPane(view);
    jsp3.setBackground(Color.black);
    jsp3.setForeground(Color.white);
    jsp3.setBounds(15, 25, 380, 270);
    p.add(jsp3);
   /* message.setBackground(Color.black);
    message.setForeground(Color.white);*/
    jsp3.setBackground(Color.black);
    jsp3.setForeground(Color.white);

    message = new JTextField();
    message.setFont(font);
    message.addKeyListener(this);
    message.setBounds(15, 305, 380, 20);
    message.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
    p.add(message);

    c.add(p);

    coerceOut = new JButton("�� �� �� ��");
    coerceOut.setBackground(Color.black);
    coerceOut.setForeground(Color.white);
    coerceOut.setFont(font);
    coerceOut.addActionListener(this);
    coerceOut.setBounds(445, 195, 100, 30);
    coerceOut.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    c.add(coerceOut);

    sendWord = new JButton("�Ӹ�������");
    sendWord.setBackground(Color.black);
    sendWord.setForeground(Color.white);
    sendWord.setFont(font);
    sendWord.addActionListener(this);
    sendWord.setBounds(445, 235, 100, 30);
    sendWord.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    c.add(sendWord);

    sendFile = new JButton("�� �� �� ��");
    sendFile.setBackground(Color.black);
    sendFile.setForeground(Color.white);
    sendFile.setFont(font);
    sendFile.addActionListener(this);
    sendFile.setBounds(445, 275, 100, 30);
    sendFile.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    c.add(sendFile);

    quitRoom = new JButton("�� �� �� ��");
    quitRoom.setBackground(Color.black);
    quitRoom.setForeground(Color.white);
    quitRoom.setFont(font);
    quitRoom.addActionListener(this);
    quitRoom.setBounds(445, 315, 100, 30);
    quitRoom.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
    c.add(quitRoom);

    Dimension dim = getToolkit().getScreenSize();
    setSize(580, 400);
    setLocation(dim.width/2 - getWidth()/2,
                dim.height/2 - getHeight()/2);
    show();

    addWindowListener(
      new WindowAdapter() {
        public void windowActivated(WindowEvent e) {
          message.requestFocusInWindow();
        }
      }
    );

    addWindowListener(
      new WindowAdapter(){
        public void windowClosing(WindowEvent e){
          cr_thread.requestQuitRoom();
        }
      }
    );
  }

  public void resetComponents(){
    messages.setText("");
    message.setText("");
    message.requestFocusInWindow();
  }

  public void keyPressed(KeyEvent ke){
    if (ke.getKeyChar() == KeyEvent.VK_ENTER){
      String words = message.getText();
      String data;
      String idTo;
      if(words.startsWith("/w")){
        StringTokenizer st = new StringTokenizer(words, " ");
        String command = st.nextToken();
        idTo = st.nextToken();
        data = st.nextToken();
        cr_thread.requestSendWordTo(data, idTo);
        message.setText("");
      } else {
        cr_thread.requestSendWord(words);
        message.requestFocusInWindow();
      }
    }
  }

  public void valueChanged(ListSelectionEvent e){
    isSelected = true;
    idTo = String.valueOf(((JList)e.getSource()).getSelectedValue());
  }

  public void actionPerformed(ActionEvent ae){
    if (ae.getSource() == coerceOut) {
      if (!isAdmin) {
        JOptionPane.showMessageDialog(this, "����� ������ �ƴմϴ�.",
                        "��������", JOptionPane.ERROR_MESSAGE);
      } else if (!isSelected) {
        JOptionPane.showMessageDialog(this, "�������� ID�� �����ϼ���.",
                        "��������", JOptionPane.ERROR_MESSAGE);
      } else {
        cr_thread.requestCoerceOut(idTo);
        isSelected = false;
      }
    } else if (ae.getSource() == quitRoom) {
      cr_thread.requestQuitRoom();
    } else if (ae.getSource() == sendWord) {
      String idTo, data;
      if ((idTo = JOptionPane.showInputDialog("���̵� �Է��ϼ���.")) != null){
        if ((data = JOptionPane.showInputDialog("�޼����� �Է��ϼ���.")) != null) {
          cr_thread.requestSendWordTo(data, idTo);
        }
      }
    } else if (ae.getSource() == sendFile) {
      String idTo;
      if ((idTo = JOptionPane.showInputDialog("���� ���̵� �Է��ϼ���.")) != null){
        cr_thread.requestSendFile(idTo);
      }
    }
  }
  
  public void stateChanged(ChangeEvent e){
    jsp3.getVerticalScrollBar().setValue((jsp3.getVerticalScrollBar().getValue() + 20));    	
  }
  public void keyReleased(KeyEvent e){}
  public void keyTyped(KeyEvent e){}
}
