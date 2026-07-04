package pis;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.sql.*;
public class Login  extends JFrame implements ActionListener
{ 
    JLabel D1 =new JLabel("User Name: "); 
    JTextField t1 =new JTextField(30);
    JLabel D2 =new JLabel("Password: ");
    JTextField t2 =new JTextField(30);
    JButton b1=new JButton("Sing In");
    JButton b2=new JButton("Log In");


    public Login(){
        JPanel p1=new JPanel();
        p1.setLayout(new GridLayout(4,2,6,6));
        p1.add(D1);
        p1.add(t1);
        p1.add(D2);
        p1.add(t2);
        p1.add(b1);
        p1.add(b2);
        add(p1);
        b1.addActionListener(this);
        b2.addActionListener(this);
        }
    
    public void actionPerformed(ActionEvent ae){
        String UN = t1.getText(); 
        String Pass = t2.getText();

    if(ae.getSource()==b1)
    {
        
        try{
           String url ="jdbc:mysql://localhost:3306/pms";
           Connection conn = DriverManager.getConnection(url,"root","");
           Statement st = conn.createStatement();
           int a = st.executeUpdate("insert into login(Username,Password)Values('"+UN+"','"+Pass+"')");
          
           if(a==1) 
            JOptionPane.showMessageDialog(this,"Inserted Succefully"); 
            st.close(); 
            conn.close(); 
        } 
         
        catch(SQLException e){ 
            JOptionPane.showMessageDialog(null, e);
            
        } 
        
        }
         if(ae.getSource()==b2)
         {
           if(checkU(UN,Pass)){
               dispose();
               Home m = new Home();
               m.setSize(400,300);
               m.setVisible(true);
               
           }else 
               JOptionPane.showMessageDialog(null, "User Name or Password is incorrect");
         }
    
    }
 public static boolean checkU(String un,String pass){
          boolean st = false;
        try{
            String url ="jdbc:mysql://localhost:3306/pms";
            Connection conn = DriverManager.getConnection(url,"root","");
            PreparedStatement ps = conn.prepareStatement("select * from login where Username=? and Password=?");
            ps.setString(1,un);ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            st=rs.next();
            }
        catch(SQLException e){ 
            e.printStackTrace();
        }
        return st;
 }
}