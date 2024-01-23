package obs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ogrenci extends JFrame {

    static String title;
    static int secilebilecekDersFlag = 0;
    static int dersID;
    static int[] ogretmenDizi;
    static int ogretmen_id;
    static int id_kontrol;
    static int ogrenciID;

    JPanel panel;
    JButton dersSecim;
    JButton derslerim;
    JButton talepIslem;
    JButton pdf;

    DefaultTableModel model;
    JTable talepTablosu;
    JScrollPane sP;

    JComboBox dersler;
    JComboBox hocalar;
    JButton onayButton;
    JTextArea mesaj;

    ImageIcon ico;
    ImageIcon pdfIco;
    ImageIcon backIco;

    public ogrenci() throws SQLException {
        ico = new ImageIcon("img\\reading-book.png");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setTitle(title);
        setIconImage(ico.getImage());
        setResizable(false);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("img\\HD-wallpaper-blue-faded-colors-abstract.jpg").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new BorderLayout());

        dersSecim = new JButton("Ders Seçimi");
        derslerim = new JButton("Derslerim");
        talepIslem = new JButton("Taleplerim");
        pdf = new JButton();

        model = new DefaultTableModel();
        model.addColumn("Öğretmen ID");
        model.addColumn("Ders ID");
        model.addColumn("Talep Durumu");
        talepTablosu = new JTable(model);
        sP = new JScrollPane(talepTablosu);
        sP.setBounds(0, 0, 600, 450);
        sP.setVisible(false);
        String[] adSoyad = getTitle().split(" ");
        if (adSoyad.length == 2) {
            String ad = adSoyad[0];
            String soyad = adSoyad[1];
    
            try {
                connection.baglan();
                String sorgu = "select ogrenci_numara from ogrenci_tablo where ogrenci_ad=? and ogrenci_soyad=?";
                PreparedStatement preparedStatement = connection.connect.prepareStatement(sorgu);
                preparedStatement.setString(1, ad);
                preparedStatement.setString(2, soyad);
    
                ResultSet resultSet = preparedStatement.executeQuery();
    
                if (resultSet.next()) {
                    ogrenciID = resultSet.getInt("ogrenci_numara");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connection.baglan();
            String listelemeQuery = "select * from talepler_tablo where ogrencino = ?";
            PreparedStatement listelemeStmt = connection.connect.prepareStatement(listelemeQuery);
            listelemeStmt.setInt(1, ogrenciID);
            ResultSet listelemeSet = listelemeStmt.executeQuery();
            while (listelemeSet.next()) {
                if (listelemeSet.getInt("talep_durumu") == 0) {
                    model.addRow(new Object[] {listelemeSet.getInt("ogretmenno"), listelemeSet.getInt("ders_id"), "Beklemede"});
                } else if (listelemeSet.getInt("talep_durumu") == 1) {
                    model.addRow(new Object[] {listelemeSet.getInt("ogretmenno"), listelemeSet.getInt("ders_id"), "Onaylandı"});
                } else {
                    model.addRow(new Object[] {listelemeSet.getInt("ogretmenno"), listelemeSet.getInt("ders_id"), "Reddedildi"});
                }
            }
        }
        talepTablosu.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    // Alt yön tuşuna basıldığında JTable'ı belirli bir miktar aşağı kaydırın
                    JScrollBar verticalScrollBar = sP.getVerticalScrollBar();
                    verticalScrollBar.setValue(verticalScrollBar.getValue() + 50); // 50 piksel aşağı kaydırın
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        talepTablosu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = talepTablosu.getSelectedColumn();
                if (column >= 0) {
                    int response = JOptionPane.showConfirmDialog(null, "Talebi onaylamak istediğinize emin misiniz ?" + "\n" + "\nÖğretim görevlisi tarafından gönderilen talep .", "Talep Durum", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        connection.baglan();
                        String durumGuncelleQuery = "update talepler_tablo set talep_durumu = ? where ogrencino = ? and ders_id = ?";
                        PreparedStatement durumGuncelleStmt;
                        try {
                            int seciliSatir = talepTablosu.getSelectedRow();
                            int seciliSutun = talepTablosu.getSelectedColumn();
                            String veri = talepTablosu.getValueAt(seciliSatir, seciliSutun).toString();
                            String veri1 = talepTablosu.getValueAt(seciliSatir, seciliSutun + 1).toString();
                            durumGuncelleStmt = connection.connect.prepareStatement(durumGuncelleQuery);
                            durumGuncelleStmt.setInt(1, 1);
                            durumGuncelleStmt.setInt(2, Integer.parseInt(veri));
                            durumGuncelleStmt.setInt(3, Integer.parseInt(veri1));
                            durumGuncelleStmt.executeUpdate();
                            connection.connect.close();
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } else if (response == JOptionPane.NO_OPTION){
                        JOptionPane.showMessageDialog(rootPane, "Öğrenci reddetme işlemi gerçekleştiremez .");
                    }
                }
            }
        });
        talepTablosu.setFocusable(true);
        talepTablosu.requestFocusInWindow();

        dersler = new JComboBox<>();
        dersler.setVisible(false);
        dersler.removeAllItems();
        hocalar = new JComboBox<>();
        hocalar.setVisible(false);
        hocalar.removeAllItems();
        onayButton = new JButton("ONAYLA");
        onayButton.setVisible(false);
        mesaj = new JTextArea();
        mesaj.setVisible(false);

        backIco = new ImageIcon("C:\\Users\\enesk\\Documents\\NetBeansProjects\\obs\\src\\img\\left.png");
        pdfIco = new ImageIcon("C:\\Users\\enesk\\Documents\\NetBeansProjects\\obs\\src\\img\\pdf.png");

        add(dersSecim);
        add(derslerim);
        add(talepIslem);
        add(pdf);
        add(dersler);
        add(hocalar);
        add(onayButton);
        add(mesaj);
        add(sP);
        add(panel);

        dersSecim.setBounds(200, 125, 200, 50);
        dersSecim.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    dersSecimMouseClicked(evt);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        derslerim.setBounds(200, 195, 200, 50);

        talepIslem.setBounds(200, 265, 200, 50);
        talepIslem.addActionListener((ActionEvent e) -> {
        dersSecim.setVisible(false);
        derslerim.setVisible(false);
        talepIslem.setVisible(false);
        pdf.setVisible(false);
        panel.add(sP, BorderLayout.CENTER);
        sP.setVisible(true);
        });

        pdf.setBounds(520, 20, 42, 42);
        pdf.setContentAreaFilled(false);
        pdf.setBorderPainted(false);
        pdf.setIcon(pdfIco);
        pdf.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Dosyaları", "pdf");
            fileChooser.setFileFilter(filter);

            int pdfSecim = fileChooser.showOpenDialog(null);
            if (pdfSecim == JFileChooser.APPROVE_OPTION) {
                File secilenDosya = fileChooser.getSelectedFile();
                String dosyaYolu = secilenDosya.getAbsolutePath();
                JOptionPane.showMessageDialog(rootPane, "Seçilen PDF dosyasının yolu: " + dosyaYolu, "PDF Seçildi", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    private void dersSecimMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {
        if (kontrol.zaman == null) {
            kontrol.zaman = LocalDate.now();
        }
        kontrol.tarihKontrol();
        if (kontrol.a == 1) {
            dersSecim.setEnabled(false);
        } else {
            dersSecim.setVisible(false);
            derslerim.setVisible(false);
            talepIslem.setVisible(false);
            pdf.setVisible(false);

            dersler.removeAllItems();
            dersler.addItem("Dersler");
            connection.baglan();
            String dersQuery = "select * from dersler_tablo";
            PreparedStatement pDStmt = connection.connect.prepareStatement(dersQuery);
            ResultSet rDSet = pDStmt.executeQuery();
            while (rDSet.next()) {
                String veri = rDSet.getString("dersler");
                dersler.addItem(veri);
            }
            connection.connect.close();
            dersler.setBounds(100, 50, 150, 50);
            dersler.setVisible(true);
            dersler.setFont(new Font("Arial", Font.BOLD, 19));

            hocalar.removeAllItems();
            hocalar.addItem("Hoca Seçimi");
            connection.baglan();
            String hocaQuery = "select * from ogretmen_tablo";
            PreparedStatement pHStmt = connection.connect.prepareStatement(hocaQuery);
            ResultSet rHSet = pHStmt.executeQuery();
            while (rHSet.next()) {
                String veri = rHSet.getString("ogretmen_ad") + " " + rHSet.getString("ogretmen_soyad");
                hocalar.addItem(veri);
            }
            connection.connect.close();
            hocalar.setBounds(250, 50, 150, 50);
            hocalar.setVisible(true);
            hocalar.setFont(new Font("Arial", Font.BOLD, 19));

            onayButton.setVisible(true);
            onayButton.setBackground(new Color(106, 90, 205));
            onayButton.setForeground(Color.WHITE);
            onayButton.setBounds(400, 50, 100, 50);
            onayButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    onayButtonMouseClicked(evt);
                }
            });

            mesaj.setVisible(true);
            mesaj.setBounds(100, 120, 400, 250);
            mesaj.setBorder(new EmptyBorder(7, 7, 7, 7));
            mesaj.setText("Öğretim görevlisine iletmek istediğiniz mesajı giriniz .");
            mesaj.setFont(new Font("Arial", Font.BOLD, 19));
            mesaj.setForeground(Color.GRAY);
            mesaj.setLineWrap(true);
            mesaj.setWrapStyleWord(true);
            mesaj.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (mesaj.getText().equals("Öğretim görevlisine iletmek istediğiniz mesajı giriniz .")) {
                        mesaj.setForeground(Color.BLACK);
                        mesaj.setText("");
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (mesaj.getText().isEmpty()) {
                        mesaj.setForeground(Color.GRAY);
                        mesaj.setText("Öğretim görevlisine iletmek istediğiniz mesajı giriniz .");
                    }
                }
            });
        }
    }

    private void derslerMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {
        connection.baglan();
        StringBuilder sBuild = new StringBuilder();
        for (int a = 0; a < dersler.getSelectedItem().toString().length(); a++) {
            if (dersler.getSelectedItem().toString().charAt(a) == '0' || dersler.getSelectedItem().toString().charAt(a) == '1' || dersler.getSelectedItem().toString().charAt(a) == '2' || dersler.getSelectedItem().toString().charAt(a) == '3' || dersler.getSelectedItem().toString().charAt(a) == '4' || dersler.getSelectedItem().toString().charAt(a) == '5' || dersler.getSelectedItem().toString().charAt(a) == '6' || dersler.getSelectedItem().toString().charAt(a) == '7' || dersler.getSelectedItem().toString().charAt(a) == '8' || dersler.getSelectedItem().toString().charAt(a) == '9') {
                sBuild.append(dersler.getSelectedItem().toString().charAt(a));
            }
        }
        dersID = Integer.parseInt(sBuild.toString());
        String ogretmenDersKontrol = "select * from ogretmen_ders_tablo where ders_id = ?";
        PreparedStatement pOgrDersKontrol = connection.connect.prepareStatement(ogretmenDersKontrol);
        pOgrDersKontrol.setInt(1, dersID);
        ResultSet rOgrDersKontrol = pOgrDersKontrol.executeQuery();
        while (rOgrDersKontrol.next()) {
            int a = 0;
            ogretmenDizi[a] = rOgrDersKontrol.getInt("verdigiders");
            if (ogretmenDizi[a] == dersID) {
                ogretmen_id = rOgrDersKontrol.getInt("ogretmen_no");
                a++;
            } else {
                a++;
                JOptionPane.showMessageDialog(rootPane, "Seçilen dersi veren herhangi bir öğretim görevlisi bulunmuyor.", "Hoca Bulunamadı", JOptionPane.PLAIN_MESSAGE);
            }
        }
        String ogretmenQuery = "select * from ogretmen_tablo where sicilno = ?", ad;
        PreparedStatement ogrStmt = connection.connect.prepareStatement(ogretmenQuery);
        ResultSet ogrSet = ogrStmt.executeQuery();
        while (ogrSet.next()) {
            id_kontrol = ogrSet.getInt("sicilno");
            if (id_kontrol == ogretmen_id) {
                ad = ogrSet.getString("ogretmen_ad") + " " + ogrSet.getString("ogretmen_soyad");
                hocalar.addItem(ad);
            }
        }
    }

    private void onayButtonMouseClicked(java.awt.event.MouseEvent evt) {
        ogretmen.mesaj = mesaj.getText();

        String[] adSoyad = getTitle().split(" ");
        String[] ogretmenAdSoyad = hocalar.getSelectedItem().toString().split(" ");
    
        if (adSoyad.length == 2 && ogretmenAdSoyad.length >= 2) {
            String ad = adSoyad[0];
            String soyad = adSoyad[1];
            String ogretmenAd = ogretmenAdSoyad[0];
            String ogretmenSoyad = ogretmenAdSoyad[1];
    
            try {
                connection.baglan();
                String sorgu = "select ogrenci_numara from ogrenci_tablo where ogrenci_ad=? and ogrenci_soyad=?";
                String ogretmenSorgu = "select sicilno from ogretmen_tablo where ogretmen_ad=? and ogretmen_soyad=?";
                PreparedStatement preparedStatement = connection.connect.prepareStatement(sorgu);
                PreparedStatement ogretmenStatement = connection.connect.prepareStatement(ogretmenSorgu);
                preparedStatement.setString(1, ad);
                preparedStatement.setString(2, soyad);
                ogretmenStatement.setString(1, ogretmenAd);
                ogretmenStatement.setString(2, ogretmenSoyad);
    
                ResultSet resultSet = preparedStatement.executeQuery();
                ResultSet ogretmenSet = ogretmenStatement.executeQuery();
    
                if (resultSet.next() && ogretmenSet.next()) {
                    int ogrenciID = resultSet.getInt("ogrenci_numara");
                    int ogretmenID = ogretmenSet.getInt("sicilno");
                    int dersID;
                    String dersEkleQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) values (?,?,?,?)";
                    String dersIDQuery = "select ders_id from dersler_tablo where dersler = ?";
                    PreparedStatement dersStmt = connection.connect.prepareStatement(dersIDQuery);
                    dersStmt.setString(1, dersler.getSelectedItem().toString());
                    ResultSet dersIDSet = dersStmt.executeQuery();
                    if (dersIDSet.next()) {
                        dersID = dersIDSet.getInt("ders_id");
                        PreparedStatement dersEkleStmt = connection.connect.prepareStatement(dersEkleQuery);
                        dersEkleStmt.setInt(1, ogrenciID);
                        dersEkleStmt.setInt(2, dersID);
                        dersEkleStmt.setInt(3, ogretmenID);
                        dersEkleStmt.setInt(4, 0);
                        dersEkleStmt.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Hata mesajını görüntülemek için
            } finally {
                try {
                    connection.connect.close(); // Veritabanı bağlantısını kapat
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
}
