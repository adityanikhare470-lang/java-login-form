import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class LoginForm extends JFrame implements ActionListener{
    JLabel l1,l2;
    JTextField t1;
    JPasswordField t2;
    JButton b1;
    Connection con;
    PreparedStatement ps;
    LoginForm(){
        l1=new JLabel("Username");
        l2=new JLabel("Password");
        t1=new JTextField();
        t2=new JPasswordField();
        b1=new JButton("Login");
        l1.setBounds(50,50,100,30);
        l2.setBounds(50,100,100,30);
        t1.setBounds(150,50,150,30);
        t2.setBounds(150,100,150,30);
        b1.setBounds(120,150,100,30);
        add(l1);
        add(l2);
        add(t1);
        add(t2);
        add(b1);
        b1.addActionListener(this);
        setSize(350,250);
        setLayout(null);
        setVisible(true);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb","root","Aditya@2004");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void actionPerformed(ActionEvent e){
        try{
            String user=t1.getText();
            String pass=new String(t2.getPassword());
            ps=con.prepareStatement("select*from users where username=? and password=?");
            ps.setString(1,user);
            ps.setString(2,pass);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(this,"Login Successful");
            }
            else{
                JOptionPane.showMessageDialog(this,"Invalid Username or Password");
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void main(String[] args){
        new LoginForm();
    }
}
