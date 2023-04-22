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
import static javaodev.BankacıDuzen.txtad;
import static javaodev.BankacıDuzen.txtmusterııd;
import static javaodev.BankacıDuzen.txtsifre;
import static javaodev.BankacıDuzen.txtsoyad;
import static javaodev.BankacıDuzen.txttc;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static javaodev.BankacıDuzen.jTable3;
import static javaodev.MusteriHesapAçma.jTable4;
import static javaodev.MusteriHesapAçma.txttc1;
import static javaodev.MusteriHesapAçma.txtad1;
import static javaodev.MusteriHesapAçma.txtsoyad1;
import static javaodev.MusteriHesapAçma.txtsifre1;

/**
 *
 * @author Sedat CANDİR
 */
public class Bankacı extends Ortak implements Toplu {
    @Override
    public void giris(String kullanıcıad,String sifre)
    {
       String sqlquery="SELECT * FROM Personel WHERE tcno='"+kullanıcıad+"'AND sifre='"+sifre+"'";
       try{
       banka conn=new banka();
       Connection con=conn.baglan();
       PreparedStatement pst=con.prepareStatement(sqlquery);
       ResultSet rs=pst.executeQuery();
       if(!rs.next()){
           JOptionPane.showMessageDialog(null, "T.C. veya Şifre Hatalı");
           new BankacıGiris().show();

       }else{
           JOptionPane.showMessageDialog(null, "Giriş Başarılı");
           new BankacıEkranı().show();
       }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);

       }
    }
                        
    public void tablo_update()
    {
        int c;
        String sqlquery="SELECT * FROM Musteri";
        
        try{
            banka conn=new banka();
            Connection con=conn.baglan();
            PreparedStatement pst =con.prepareStatement(sqlquery);
            ResultSet rs=pst.executeQuery();
            ResultSetMetaData rss=rs.getMetaData();
            c=rss.getColumnCount();
            DefaultTableModel df=(DefaultTableModel)jTable3.getModel();
            df.setRowCount(0);
            while(rs.next())
            {
                Vector v2= new Vector();
                
                for(int a=1;a<=c;a++)
                {
                    v2.add(rs.getString("musteriıd"));
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
public void musteriekleme(String personel,String ad,String soyad,String tcno,String sifre)
{
        String sqlquery= "INSERT INTO Musteri VALUES('"+personel+"','"+ad+"','"+soyad+"','"+tcno+"','"+sifre+"' )";
        

        try{
            if(txtmusterııd.getText().equals("")){
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
            txtmusterııd.setText("");
            txtad.setText("");
            txtsoyad.setText("");
            txttc.setText("");
            txtsifre.setText("");
            txtmusterııd.requestFocus();

            con.close();
        }
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
}

 public void musteridüzen(String id1)  

{
        String sqlquery = "UPDATE Musteri SET musteriıd='"+txtmusterııd.getText()+"',ad='"+txtad.getText()+"',soyad='"+txtsoyad.getText()+"',tcno='"+txttc.getText()+"',sifre='"+txtsifre.getText()+"'WHERE musteriıd='"+id1+"'";
        
        try {
        banka conn=new banka();
        Connection con=conn.baglan();
        Statement stm=con.createStatement();
        stm.executeUpdate(sqlquery);
        JOptionPane.showMessageDialog(null, "Kayıt Düzenlendi");
        tablo_update();
            txtmusterııd.setText("");
            txtad.setText("");
            txtsoyad.setText("");
            txttc.setText("");
            txtsifre.setText("");
            txtmusterııd.requestFocus();
        con.close();
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
}
 public void msuterisil(String id1)  
 {
         String sqlquery = "DELETE FROM Musteri WHERE musteriıd='"+id1+"'";
        
        try {
        banka conn=new banka();
        Connection con=conn.baglan();
        Statement stm=con.createStatement();
        stm.executeUpdate(sqlquery);
        JOptionPane.showMessageDialog(null, "Kayıt Silindi");
        tablo_update();
            txtmusterııd.setText("");
            txtad.setText("");
            txtsoyad.setText("");
            txttc.setText("");
            txtsifre.setText("");
            txtmusterııd.requestFocus();
        con.close();
        
        
       
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
 }
 public void tablo_update1()
    {
        int c;  
        String sqlquery= "SELECT * FROM MusterıHesap";
    try{
        banka conn=new banka();
        Connection con=conn.baglan();
        Statement stm=con.createStatement();
        PreparedStatement pst = con.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rss= rs.getMetaData();
        c = rss.getColumnCount();
        DefaultTableModel df = (DefaultTableModel)jTable4.getModel();
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
                v2.add(rs.getString("musteribakiye"));
            }
            df.addRow(v2);
        }
        
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
 public void ekleme(String tcduzen,String adduzen,String soyadduzen,String sifreduzen)
 {
     String sqlquery= "INSERT INTO MusterıHesap(musteritc,musteriad,musterisoyad,musterisifre,musteribakiye) VALUES('"+tcduzen+"','"+adduzen+"','"+soyadduzen+"','"+sifreduzen+"','"+0+"')";
        

        try{
            if(txttc1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen T.C. No Girin");
            }if(txtad1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen Ad Girin");
             }if(txtsoyad1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen Soyad Girin");
            }else if(txtsifre1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Lütfen Şifre Girin");
        }else{
            banka conn=new banka();
        Connection con=conn.baglan();
        Statement stm=con.createStatement();
            stm.executeUpdate(sqlquery);
            JOptionPane.showMessageDialog(null, "Kayıt Edildi");
            txttc1.setText("");
            txtad1.setText("");
            txtsoyad1.setText("");
            txtsifre1.setText("");
            txttc1.requestFocus();
            tablo_update1();
            con.close();
        }
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
 }
  public void sil(String id)
  {
      String sqlquery = "DELETE FROM MusterıHesap WHERE musterihesap='"+id+"'";

        try {
           banka conn=new banka();
            Connection con=conn.baglan();
            Statement stm=con.createStatement();
            stm.executeUpdate(sqlquery);
            JOptionPane.showMessageDialog(null, "Kayıt Silindi");
            tablo_update1();
            txttc1.setText("");
            txtad1.setText("");
            txtsoyad1.setText("");
            txtsifre1.setText("");
            txttc1.requestFocus();
            con.close();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
  }
    @Override
  public void parayatırma(String username,String miktar1)
  {
      int intmiktar1 = Integer.parseInt(miktar1);
        String sqlquery = "SELECT * FROM MusterıHesap WHERE musterihesap='"+username+"'";
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
            String sqlquery2 = "UPDATE MusterıHesap SET musteribakiye='"+toplambakiye+"'WHERE musterihesap='"+username+"'";
            PreparedStatement pst1 = con.prepareStatement(sqlquery2);
            pst1.execute();
            
            JOptionPane.showMessageDialog(null, "Para Yatırıldı");
            
         
        }
        con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
  }
    @Override
    public void paraçekme(String username,String miktar1)
    {
        int intmiktar1 = Integer.parseInt(miktar1);
        String sqlquery = "SELECT * FROM MusterıHesap WHERE musterihesap='"+username+"'";
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
            String sqlquery2 = "UPDATE MusterıHesap SET musteribakiye='"+toplambakiye+"'WHERE musterihesap='"+username+"'";
            PreparedStatement pst1 = con.prepareStatement(sqlquery2);
            pst1.execute();
            
            JOptionPane.showMessageDialog(null, "Para Çekildi");
            
         
        }
        con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }


}
