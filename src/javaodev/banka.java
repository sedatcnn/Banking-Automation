package javaodev;


import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import javax.swing.UIManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

public class banka { 
    public Connection baglan(){
         Connection c=null;
        try {           
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            c=DriverManager.getConnection("jdbc:sqlserver://localhost\\DESKTOP-S6RUC8B:1433;databaseName=Uygulama;encrypt=true;trustServerCertificate=true", "sedat", "15253545");
            String sqlquery="";
            PreparedStatement pst= c.prepareStatement(sqlquery);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
           return c;
    }

    
public static void main(String[] args) {
        // TODO code application logic here
        FlatLightLaf.setup();
        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
        }catch (Exception e){
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Giriş().setVisible(true);
            }
        });
    }
}
