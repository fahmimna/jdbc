/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugas4;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/**
 *
 * @author HP
 */
public class Login extends JFrame {
    JLabel luname, lpassword;
    JTextField tfuname;
    JPasswordField tfpassword;
    JButton bLogin;
    JPanel panelForm, panelTombol;
    String DBurl = "jdbc:mysql://localhost/tugasjdbc";
    String DBusername = "root";
    String DBpassword = "";
    Connection koneksi;
    Statement statement;
    
    public Login(){
        setTitle("Login");
        setSize(400,150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        
        luname = new JLabel("USERNAME");
        lpassword = new JLabel("PASSWORD");
        tfuname = new JTextField(50);
        tfpassword = new JPasswordField(50);
        bLogin = new JButton("Regist");
        panelForm = new JPanel(new GridLayout(3, 2));
        panelTombol = new JPanel(new FlowLayout());
        
            setLayout(new BorderLayout());
            add(panelForm, BorderLayout.CENTER);
            panelForm.add(luname);
            panelForm.add(tfuname);
            panelForm.add(lpassword);
            panelForm.add(tfpassword);
            add(panelTombol, BorderLayout.SOUTH);
            panelTombol.add(bLogin);
            
            bLogin.addActionListener(new ActionListener(){
            
                @Override
            public void actionPerformed(ActionEvent e) {
                masukkanData();
            }
         });
    } 
    
    public void masukkanData(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(DBurl,DBusername,DBpassword);
            statement = koneksi.createStatement();
            statement.executeUpdate("SELECT * FROM `users` WHERE `username` = "+tfuname);
            statement.executeUpdate("SELECT * FROM `users` WHERE `password` = "+tfpassword);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan !", "Hasil", JOptionPane.INFORMATION_MESSAGE);
            statement.close();
            koneksi.close();   
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan !", "Hasil", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Data Tidak Ditemukan !", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
    }
}