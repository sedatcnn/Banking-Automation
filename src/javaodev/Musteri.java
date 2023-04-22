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
import static javaodev.MusteriHesabı.jTable5;
import static javaodev.MusteriKredi.jTable6;
import static javaodev.MusteriKredi.txthesap2;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static javaodev.MusteriKredi.txttc2;
import static javaodev.MusteriHesabı.txttc8;

/**
 *
 * @author Sedat CANDİR
 */
public class Musteri extends Ortak implements Toplu {
    @Override
    public void giris (String kullanıcıad,String sifre)
    {
       String sqlquery="SELECT * FROM Musteri WHERE tcno='"+kullanıcıad+"'AND sifre='"+sifre+"'";
       try{
       banka conn=new banka();
       Connection con=conn.baglan();
       PreparedStatement pst=con.prepareStatement(sqlquery);
       ResultSet rs=pst.executeQuery();
       if(!rs.next()){
           JOptionPane.showMessageDialog(null, "T.C. veya Şifre Hatalı");
           new MusteriGiris().show();

       }else{
           JOptionPane.showMessageDialog(null, "Giriş Başarılı");
           new MüsteriEkranı().show();

       }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
    }
    public void tablo_update()
    {
        int c;  
        String sqlquery= "SELECT * FROM MusterıHesap WHERE musteritc='"+txttc8.getText()+"'";
        try{
       banka conn=new banka();
        Connection con=conn.baglan();
        Statement stm=con.createStatement();
        PreparedStatement pst = con.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rss= rs.getMetaData();
        c = rss.getColumnCount();
        DefaultTableModel df = (DefaultTableModel)jTable5.getModel();
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
    public void tablo_update1()
    {
        int c;  
        
        String sqlquery= "SELECT * FROM MusterıHesap WHERE musteritc='"+txttc2.getText()+"'";
        try{
         banka conn=new banka();
        Connection con=conn.baglan();
        Statement stm=con.createStatement();
        PreparedStatement pst = con.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rss= rs.getMetaData();
        c = rss.getColumnCount();
        DefaultTableModel df = (DefaultTableModel)jTable6.getModel();
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
                v2.add(rs.getString("musterikredisonuc"));
                
            }
            df.addRow(v2);
        }
        
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    public void musterikredi(String username)
    {
        String sqlquery = "SELECT * FROM MusterıHesap WHERE musterihesap='"+username+"' ";
              
        try{
         banka conn=new banka();
        Connection con=conn.baglan();
        PreparedStatement pst = con.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            String sqlquery2="UPDATE MusterıHesap SET musterikredidurumu = (case musterikredidurumu "
                    + "                                                      when 0 then 1"
                    + "                                                          end)WHERE musterihesap='"+txthesap2.getText()+"' ";
            PreparedStatement pst1 = con.prepareStatement(sqlquery2);
            pst1.execute();
            JOptionPane.showMessageDialog(null, "Başvuru Yapıldı");
            tablo_update1();
        }else
            JOptionPane.showMessageDialog(null, "Hesap No Hatalı");
        {
            
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    public void basvurusonuc(String username1)
    {
        String sqlquery = "SELECT * FROM MusterıHesap WHERE musteritc='"+username1+"'";
        
        try{
        banka conn=new banka();
        Connection con=conn.baglan();
        PreparedStatement pst = con.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            tablo_update1();
        
        }else {
            JOptionPane.showMessageDialog(null, "T.C. Hatalı");
        }
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void hesapgoster(String username)
    {
          String sqlquery = "SELECT * FROM MusterıHesap WHERE musteritc='"+username+"'";
        
        try{
        banka conn=new banka();
        Connection con=conn.baglan();
        PreparedStatement pst = con.prepareStatement(sqlquery);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            tablo_update();
        
        }else {
            JOptionPane.showMessageDialog(null, "T.C Kimlik Numarası Hatalı");
        }
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
