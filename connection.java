package obs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class connection {

    static int flag;
    static Connection connect = null;

    static void baglan() {
        try {
            connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432", "{database_username}", "{database_password}");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void kontenjanEkle(String kText) throws SQLException {
        int kontenjan = Integer.parseInt(kText);
        String kontenjanQuery = "insert into ogretmen_tablo (kontenjan) values(?)";
        PreparedStatement pStmt = connect.prepareStatement(kontenjanQuery);
        pStmt.setInt(4, kontenjan);
        pStmt.executeQuery();
        connect.close();
    }

    static void ekleCikar(String no, String ad, String soyad, String agno) throws SQLException {
        if (flag == 0) { //ekleme - çıkarma işlemi belirleme koşulu ----------------------------------------------0
            if (no.charAt(0) == '1') { //öğrenci - öğretmen tablosu erişim belirleme ------------------------------0
                String addQuery = "insert into ogretmen_tablo (sicilno, ogretmen_ad, ogretmen_soyad) values (?,?,?)";
                String dersEklemeQuery = "insert into ogretmen_ders_tablo (ogretmen_no, verdigiders) values(?,?)";
                PreparedStatement pStmt = connect.prepareStatement(addQuery);
                PreparedStatement preStmt = connect.prepareStatement(dersEklemeQuery);
                pStmt.setInt(1, Integer.parseInt(no)); //1 değeri, ? işaretine atanacak değerin sıra numarasını belirtir.
                preStmt.setInt(1, Integer.parseInt(no));
                pStmt.setString(2, ad);
                preStmt.setInt(2, Integer.parseInt(agno));
                pStmt.setString(3, soyad);
                int sonuc = pStmt.executeUpdate();
                preStmt.executeUpdate();

                if (sonuc > 0) {
                    JOptionPane.showMessageDialog(null, "Kayıt eklendi.");
                } else {
                    JOptionPane.showMessageDialog(null, "Kayıt bulunamadı.");
                }
            } else if (no.charAt(0) == '2') { // --------------------------------------------------------------------0
                String addQuery = "insert into ogrenci_tablo (ogrenci_numara, ogrenci_ad, ogrenci_soyad, not_ort, talep_say) values (?,?,?,?,?)";
                PreparedStatement pStmt = connect.prepareStatement(addQuery);
                pStmt.setInt(1, Integer.parseInt(no)); //1 değeri, ? işaretine atanacak değerin sıra numarasını belirtir.
                pStmt.setString(2, ad);
                pStmt.setString(3, soyad);
                pStmt.setDouble(4, Double.parseDouble(agno));;
                pStmt.setInt(5, 0);
                int sonuc = pStmt.executeUpdate();

                if (sonuc > 0) { //kaydın silindiğini veya bulunamadığını belirten koşul
                } else {
                    JOptionPane.showMessageDialog(null, "Kayıt bulunamadı.");
                }
            } else { // -------------------------------------------------------------------------------------------------0
                JOptionPane.showMessageDialog(null, "Hatalı ID girildi lütfen kontrol ediniz !", "ID Hatası", JOptionPane.ERROR_MESSAGE);
            }
        } else if (flag == 1) {
            if (no.charAt(0) == '1') { //öğrenci - öğretmen tablosu erişim belirleme ------------------------------1
                String rmQuery = "delete from ogretmen_tablo where sicilno = ?";
                PreparedStatement pStmt = connect.prepareStatement(rmQuery);
                pStmt.setInt(1, Integer.parseInt(no)); //1 değeri, ? işaretine atanacak değerin sıra numarasını belirtir.
                int sonuc = pStmt.executeUpdate();

                if (sonuc > 0) {
                    JOptionPane.showMessageDialog(null, "Kayıt silindi.");
                } else {
                    JOptionPane.showMessageDialog(null, "Kayıt bulunamadı.");
                }
            } else if (no.charAt(0) == '2') { // --------------------------------------------------------------------1
                String rmQuery = "delete from ogrenci_tablo where ogrenci_numara = ?";
                PreparedStatement pStmt = connect.prepareStatement(rmQuery);
                pStmt.setInt(1, Integer.parseInt(no)); //1 değeri, ? işaretine atanacak değerin sıra numarasını belirtir.
                int sonuc = pStmt.executeUpdate();

                if (sonuc > 0) { //kaydın silindiğini veya bulunamadığını belirten koşul
                    JOptionPane.showMessageDialog(null, "Kayıt silindi.");
                } else {
                    JOptionPane.showMessageDialog(null, "Kayıt bulunamadı.");
                }
            } else { // -------------------------------------------------------------------------------------------------1
                JOptionPane.showMessageDialog(null, "Hatalı ID girildi lütfen kontrol ediniz !", "ID Hatası", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Yapılmak istenen işlem algılanamadı .", "İşlem Bulunamadı", JOptionPane.ERROR_MESSAGE);
        }
        connect.close();
    }
}
