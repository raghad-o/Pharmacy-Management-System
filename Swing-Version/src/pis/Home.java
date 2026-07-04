package pis;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
public class Home extends JFrame implements ActionListener{
   

  JLabel ml = new JLabel("Medicine Name: ");
  JLabel idl = new JLabel("Medicine ID: ");
  JLabel pl = new JLabel("Prescription Type:");
  
  JTextField name = new JTextField(20);
  JTextField mid = new JTextField(10);
  JTextField pres = new JTextField(10);
  
  JButton view = new JButton("View");
  JButton add = new JButton("Add");
  JButton update = new JButton("Update");
  JButton del = new JButton("Delete");
  
  public Home(){
      
      
      JPanel p1 = new JPanel();
      p1.setLayout(new GridLayout(6,2,6,6));
      
      p1.add(ml);
      p1.add(name);
      
      p1.add(idl);
      p1.add(mid);
      
      p1.add(pl);
      p1.add(pres);
      
      p1.add(view);
      p1.add(add);
      p1.add(update);
      p1.add(del);
      
      add(p1);
      view.addActionListener(this);
      add.addActionListener(this);
      update.addActionListener(this);
      del.addActionListener(this);
}
  public void actionPerformed(ActionEvent ae)
  {
      String n = name.getText();
      String id = mid.getText();
      String p = pres.getText();
      int a = 0;
      try{
      String url = "jdbc:mysql://localhost:3306/pms";
      Connection conn = DriverManager.getConnection(url,"root","");
      Statement st = conn.createStatement();
     
      if(ae.getSource()==view)
      {
         ResultSet rs = st.executeQuery("SELECT * FROM medicine"); 
         while(rs.next()){
             System.out.println(rs.getString(1)+ " | "+ rs.getString(2) + " | " + rs.getString(3));
         }
      }
      if(ae.getSource()==add){
          a = st.executeUpdate("insert into medicine(name,id,PrescriptionType) values('"+n+"','"+id+"','"+p+"')");
      }
      if(ae.getSource()==update){
          a = st.executeUpdate("update medicine set name = '"+n+"',PrescriptionType = '"+p+"' where id = '"+id+"' ");
      }
      if(ae.getSource()==del){
          a = st.executeUpdate("delete from medicine where id = '"+id+"' ");
      }
      if(a==1){
          JOptionPane.showMessageDialog(this, "Done Succefully");
          st.close();
          conn.close();
      } 
      
      }catch(SQLException e){
          JOptionPane.showMessageDialog(null, e);
      }
  }
}