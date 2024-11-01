/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplikasi.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ThinkPad
 */
public class MahasiswaManager {
    Connection conn = null;
    Statement st = null; 
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/db_si_51";
    
    public MahasiswaManager(){
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, "root", "");
            st = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[]args){
        new MahasiswaManager();
    }
public List getMahasiswa(){
    ResultSet rs = null;
    List Mahasiswa = new ArrayList();
        try{
           rs = st.executeQuery("Select * From tabel_mahasiswa");
           while (rs.next()) {
              Mahasiswa m = new Mahasiswa();
              m.setNoBP(rs.getString("NoBP"));
              m.setNama(rs.getString("Nama"));
              m.setTempatLahir(rs.getString("TempatLahir"));
              m.setTanggalLahir(rs.getString("TanggalLahir"));
              m.setAlamat(rs.getString("Alamat"));
              m.setTelp(rs.getString("Telp"));
              m.setAsalSekolah(rs.getString("AsalSekolah"));
              Mahasiswa.add(m);
            }
        }  catch (Exception e) {
             e.printStackTrace();
    }
    return Mahasiswa;    
}
public int Insert(Mahasiswa m){
    int result = 0;
    try {
        result = st.executeUpdate("insert into tabel_mahasiswa value('"+ m.getNoBP() +"'  ,'"+ m.getNama() +"'  ,'"+ m.getTempatLahir() + "','" + m.getTanggalLahir() + "','" + m.getAlamat() + "','" + m.getTelp() + "','" + m.getAsalSekolah() + "')"); 
      } catch (SQLException e) {
            e.printStackTrace();
      }
      return result;
    
}
public int Delete(Mahasiswa m){
    int result = 0;
    try {
        result = st.executeUpdate("delete from tabel_mahasiswa where NoBP='" + m.getNoBP() +"'");
      } catch (SQLException e) {
          e.printStackTrace();
      }
      return result;
}
public int Update(Mahasiswa m){
    int result = 0;
    try {
        result = st.executeUpdate("Update tabel_mahasiswa set "
+ "NoBP='" + m.getNoBP() + "', " + "Nama='" + m.getNama() + "', " +
"TempatLahir='" + m.getTempatLahir() + "', " + "TanggalLahir='" + m.getTanggalLahir() + "', " +
"Alamat='" + m.getAlamat() + "', " + "Telp='" + m.getTelp() + "', " +
"AsalSekolah='" + m.getAsalSekolah() + "' " + "WHERE NoBP='" + m.getNoBP() + "'"
);
      } catch (SQLException e) {
      }
      return result;
  }
   public void closeConnection(){
       try {
           conn.close();
           st.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
