/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaodev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static javaodev.MudurDuzenleme.jTable1;
import static javaodev.MudurDuzenleme.txtad;
import static javaodev.MudurDuzenleme.txtpersonelıd;
import static javaodev.MudurDuzenleme.txtsifre;
import static javaodev.MudurDuzenleme.txtsoyad;
import static javaodev.MudurDuzenleme.txttc;
import static javaodev.MudurKredi.jTable2;
import static javaodev.MudurKredi.txthesap;

/**
 *
 * @author Sedat CANDİR
 */
public class Mudur extends Ortak implements Toplu {
@Override
public void giris(String kullanıcıad, String sifre)
    {
       String sqlquery="SELECT * FROM Mudur WHERE tcno='"+kullanıcıad+"'AND sifre='"+sifre+"'";
       try{
       banka conn=new banka();
       Connection con=conn.baglan();
       PreparedStatement pst=con.prepareStatement(sqlquery);
       ResultSet rs=pst.executeQuery();
       if(!rs.next()){
           JOptionPane.showMessageDialog(null, "T.C. veya Şifre Hatalı");
           new MüdürGiris().show();
       }else{
           JOptionPane.showMessageDialog(null, "Giriş Başarılı");
           new MudurEkranı().show();

          
       }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
    }   
public void tablo_update()
    {
        int c;
        String sqlquery="SELECT * FROM Personel";
        
        try{
            banka conn=new banka();
            Connection con=conn.baglan();
            Statement stm=con.createStatement();
            PreparedStatement pst =con.prepareStatement(sqlquery);
            ResultSet rs=pst.executeQuery();
            ResultSetMetaData rss=rs.getMetaData();
            c=rss.getColumnCount();
            DefaultTableModel df=(DefaultTableModel)jTable1.getModel();
            df.setRowCount(0);
            while(rs.next())
            {
                Vector v2= new Vector();
                
                for(int a=1;a<=c;a++)
                {
                    v2.add(rs.getString("personelıd"));
                    v2.add(rs.getString("ad"));
                    v2.add(rs.getString("soyad"));
                    v2.add(rs.getString("tcno"));
                    v2.add(rs.getString("sifre"));
                    
                }
                df.addRow(v2);
                
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
public void ekleme(String personel,String ad,String soyad,String tcno,String sifre)
{
    String sqlquery= "INSERT INTO Personel VALUES('"+personel+"','"+ad+"','"+soyad+"','"+tcno+"','"+sifre+"' )";
        

        try{
            if(txtpersonelıd.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen ID Girin");
        }else if(txtad.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen Ad Girin");
        }else if(txtsoyad.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen Soyad Girin");
        }else if(txttc.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen T.C. Girin");
        }else if(txtsifre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen Şifre Girin");
        }else{
            banka conn=new banka();
            Connection con=conn.baglan();
            Statement stm=con.createStatement();
            stm.executeUpdate(sqlquery);
            JOptionPane.showMessageDialog(null, "Kayıt Edildi");
            tablo_update();
            txtpersonelıd.setText("");
            txtad.setText("");
            txtsoyad.setText("");
            txttc.setText("");
            txtsifre.setText("");
            txtpersonelıd.requestFocus();

            con.close();
        }
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
}

 public void düzen(String id1)  

{
   String sqlquery = "UPDATE Personel SET personelıd='"+txtpersonelıd.getText()+"',ad='"+txtad.getText()+"',soyad='"+txtsoyad.getText()+"',tcno='"+txttc.getText()+"',sifre='"+txtsifre.getText()+"'WHERE personelıd='"+id1+"'";
        
        try {
        banka conn=new banka();
        Connection con=conn.baglan();
        Statement stm=con.createStatement();
        stm.executeUpdate(sqlquery);
        JOptionPane.showMessageDialog(null, "Kayıt Düzenlendi");
        tablo_update();
            txtpersonelıd.setText("");
            txtad.setText("");
            txtsoyad.setText("");
            txttc.setText("");
            txtsifre.setText("");
            txtpersonelıd.requestFocus();
        con.close();
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
}
 public void sil(String  id)  
 {
     String sqlquery = "DELETE FROM Personel WHERE personelıd='"+id+"'";
        
        try {
        banka conn=new banka();
        Connection con=conn.baglan();
        Statement stm=con.createStatement();
        stm.executeUpdate(sqlquery);
        JOptionPane.showMessageDialog(null, "Kayıt Silindi");
        tablo_update();
            txtpersonelıd.setText("");
            txtad.setText("");
            txtsoyad.setText("");
            txttc.setText("");
            txtsifre.setText("");
            txtpersonelıd.requestFocus();
        con.close();
        
        
       
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
 }
 
    public void tablo_update1()
    {
        int c;  
        String sqlquery= "SELECT * FROM MusterıHesap WHERE musterikredidurumu = 1";
    try{
        banka conn=new banka();
        Connection con=conn.baglan();
        Statement stm=con.createStatement();
        PreparedStatement pst = con.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rss= rs.getMetaData();
        c = rss.getColumnCount();
        DefaultTableModel df = (DefaultTableModel)jTable2.getModel();
        df.setRowCount(0);
        while(rs.next())
        {
            Vector v2 = new Vector();
            
            for(int a=1;a<=c; a++)
            {
                v2.add(rs.getString("musterihesap"));
                v2.add(rs.getString("musteritc"));
                v2.add(rs.getString("musteriad"));
                v2.add(rs.getString("musterisoyad"));
                v2.add(rs.getString("musterisifre"));
                v2.add(rs.getString("musterikredidurumu"));
                v2.add(rs.getString("musterikredisonuc"));
            }
            df.addRow(v2);
        }
        
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
public void kredikabul(String username,String a)  
{
        String sqlquery = "SELECT * FROM MusterıHesap WHERE musterihesap='"+username+"'";
        try{
            banka conn=new banka();
            Connection con=conn.baglan();
            PreparedStatement pst = con.prepareStatement(sqlquery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "Hesap No Hatalı");
            }else{
                String sqlquery2 = "UPDATE MusterıHesap SET musterikredisonuc='"+a+"'WHERE musterihesap='"+username+"'";
                PreparedStatement pst1 = con.prepareStatement(sqlquery2);
                pst1.execute();

                JOptionPane.showMessageDialog(null, "Kabul Edildi");
                tablo_update1();

            }
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
       tablo_update1();
}
public void kredired(String username,String a)  
{
        String sqlquery = "SELECT * FROM MusterıHesap WHERE musterihesap='"+username+"'";
        try{
            banka conn=new banka();
            Connection con=conn.baglan();
            PreparedStatement pst = con.prepareStatement(sqlquery);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "Hesap No Hatalı");
            }else{
                String sqlquery2 = "UPDATE MusterıHesap SET musterikredisonuc='"+a+"'WHERE musterihesap='"+username+"'";
                PreparedStatement pst1 = con.prepareStatement(sqlquery2);
                pst1.execute();

                JOptionPane.showMessageDialog(null, "Rededildi");

            }
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
       tablo_update1();
}


}
