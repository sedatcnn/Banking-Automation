/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaodev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static javaodev.BankacıGiris.txttc;
import javax.swing.JOptionPane;

/**
 *
 * @author Sedat CANDİR
 */
public class Ortak implements Toplu {
    @Override
    public void giris(String kullanıcıad, String sifre)
    {
      
       String sqlquery="";
       try{
       banka conn=new banka();
       Connection con=conn.baglan();
       PreparedStatement pst=con.prepareStatement(sqlquery);
       ResultSet rs=pst.executeQuery();
       if(!rs.next()){
           JOptionPane.showMessageDialog(null, "T.C. veya Şifre Hatalı");
       }else{
           JOptionPane.showMessageDialog(null, "Giriş Başarılı");
          
       }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
    }
    public void paraçekme (String username,String miktar1)
    {
        int intmiktar1 = Integer.parseInt(miktar1);
        String sqlquery = "";
        try{
            banka conn=new banka();
            Connection con=conn.baglan();
            PreparedStatement pst = con.prepareStatement(sqlquery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "Hesap No Hatalı");
            }else{
                String bakiye = rs.getString("musteribakiye");
                int toplambakiye = Integer.parseInt(bakiye)- intmiktar1 ;
                if(intmiktar1 > Integer.parseInt(bakiye)){
                    JOptionPane.showMessageDialog(null, "Hesaptaki Miktar Yetersiz");
                }
                String sqlquery2 = "";
                PreparedStatement pst1 = con.prepareStatement(sqlquery2);
                pst1.execute();

                JOptionPane.showMessageDialog(null, "Para Çekildi");

            }
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }
    public void parayatırma(String username,String miktar1)
    {
        int intmiktar1 = Integer.parseInt(miktar1);
        String sqlquery = "";
        try{
            banka conn=new banka();
       Connection con=conn.baglan();
            PreparedStatement pst = con.prepareStatement(sqlquery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "Hesap No Hatalı");
            }else{
                String bakiye = rs.getString("musteribakiye");
                int toplambakiye = Integer.parseInt(bakiye)+ intmiktar1 ;
                String sqlquery2 = "";
                PreparedStatement pst1 = con.prepareStatement(sqlquery2);
                pst1.execute();

                JOptionPane.showMessageDialog(null, "Para Yatırıldı");

            }
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
