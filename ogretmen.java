package obs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ogretmen extends JFrame {

    static String title;
    static int ogretmenID;
    static String mesaj;
    JPanel panel;

    JButton frame;
    JButton bilgiGuncelleme;
    JButton talepler;
    JButton notlandirma;
    JButton ilgiAlaniButton;
    JButton verilenDersEkleB;
    JButton verilenDersSilmeB;
    JButton kriterDersEklemeB;
    JButton kriterDersSilmeB;

    JComboBox ilgiAlaniBox;
    JTextField verilenDersBox;
    JTextField kriterDersBox;

    DefaultTableModel model;
    JTable talepTablo;
    JScrollPane sP;
    JButton talepYolla;
    JTextField ogrenciID;
    JTextField talepOgrenciID;
    JTextField talepDersID;
    JButton talepOnayButton;

    JTable talepTablosu;

    ImageIcon ico;

    ogretmen() throws SQLException {
        ico = new ImageIcon("img\\teacher.png");
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

        frame = new JButton();
        
        model = new DefaultTableModel();
        talepTablosu = new JTable(model);

        bilgiGuncelleme = new JButton("Bilgi Güncelleme");
        bilgiGuncelleme.setBounds(200, 125, 200, 50);
        bilgiGuncelleme.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bilgiGuncellemeMouseClicked(evt);
            }
        });

        ilgiAlaniBox = new JComboBox<>();
        ilgiAlaniBox.setVisible(false);
        ilgiAlaniBox.addItem("İlgi Alanları");
        ilgiAlaniBox.setBounds(125, 125, 175, 50);
        try {
            connection.baglan();
            String ilgiAlaniQuery = "select * from ilgialanlar_tablo";
            PreparedStatement ilgiAlaniStmt = connection.connect.prepareStatement(ilgiAlaniQuery);
            ResultSet ilgiAlaniSet = ilgiAlaniStmt.executeQuery();
            while (ilgiAlaniSet.next()) {
                ilgiAlaniBox.addItem(ilgiAlaniSet.getString("ilgialanlari"));
            }
        } catch (SQLException e) {
            e.getErrorCode();
        }
        ilgiAlaniButton = new JButton("KAYDET");
        ilgiAlaniButton.setVisible(false);
        ilgiAlaniButton.setBounds(300, 125, 175, 50);
        ilgiAlaniButton.addActionListener((ActionEvent e) -> extracted());

        verilenDersBox = new JTextField("Verilen Ders");
        verilenDersBox.setVisible(false);
        verilenDersBox.setBounds(125, 195, 150, 50);
        verilenDersBox.setForeground(Color.GRAY);
        verilenDersBox.setBorder(new EmptyBorder(5, 5, 5, 5));
        verilenDersBox.setFont(new Font("Arial", Font.BOLD, 19));
        verilenDersBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (verilenDersBox.getText().equals("Verilen Ders")) {
                    verilenDersBox.setForeground(Color.BLACK);
                    verilenDersBox.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (verilenDersBox.getText().isEmpty()) {
                    verilenDersBox.setForeground(Color.GRAY);
                    verilenDersBox.setText("Verilen Ders");
                }
            }
        });
        verilenDersEkleB = new JButton("EKLE");
        verilenDersEkleB.setVisible(false);
        verilenDersEkleB.setBounds(275, 195, 100, 50);
        verilenDersEkleB.addActionListener((ActionEvent e) -> extractedVerilenEkle());

        verilenDersSilmeB = new JButton("SİL");
        verilenDersSilmeB.setVisible(false);
        verilenDersSilmeB.setBounds(375, 195, 100, 50);
        verilenDersSilmeB.addActionListener((ActionEvent e) -> extractedVerilenSil());

        kriterDersBox = new JTextField("Kriter Ders");
        kriterDersBox.setVisible(false);
        kriterDersBox.setBounds(125, 265, 150, 50);
        kriterDersBox.setForeground(Color.GRAY);
        kriterDersBox.setBorder(new EmptyBorder(5, 5, 5, 5));
        kriterDersBox.setFont(new Font("Arial", Font.BOLD, 19));
        kriterDersBox.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (kriterDersBox.getText().equals("Kriter Ders")) {
                    kriterDersBox.setForeground(Color.BLACK);
                    kriterDersBox.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (kriterDersBox.getText().isEmpty()) {
                    kriterDersBox.setForeground(Color.GRAY);
                    kriterDersBox.setText("Kriter Ders");
                }
            }
        });
        kriterDersEklemeB = new JButton("EKLE");
        kriterDersEklemeB.setVisible(false);
        kriterDersEklemeB.setBounds(275, 265, 100, 50);
        kriterDersEklemeB.addActionListener((ActionEvent e) -> extractedKriterEkle());

        kriterDersSilmeB = new JButton("SİL");
        kriterDersSilmeB.setVisible(false);
        kriterDersSilmeB.setBounds(375, 265, 100, 50);
        kriterDersSilmeB.addActionListener((ActionEvent e) -> extractedKriterSil());


        talepler = new JButton("Talepler");
        talepler.setBounds(200, 195, 200, 50);
        talepler.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taleplerMouseClicked(evt);
            }
        });

        model.addColumn("Öğrenci No");
        model.addColumn("Ders ID");
        model.addColumn("Talep Durumu");
        JTableHeader tableHeader = talepTablosu.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 11));
        sP = new JScrollPane(talepTablosu);
        sP.setBounds(200, 0, 400, 450);
        sP.setVisible(false);
        String[] adSoyad = getTitle().split(" "); // Başlığı boşluk karakterine göre ayır

            if (adSoyad.length == 2) {
                String ad = adSoyad[0];
                String soyad = adSoyad[1];

                try {
                    connection.baglan();
                    String sorgu = "select sicilno from ogretmen_tablo where ogretmen_ad=? and ogretmen_soyad=?";
                    PreparedStatement preparedStatement = connection.connect.prepareStatement(sorgu);
                    preparedStatement.setString(1, ad);
                    preparedStatement.setString(2, soyad);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        ogretmenID = resultSet.getInt("sicilno");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                    }
        connection.baglan();
        String talepAlmaQuery = "select * from talepler_tablo where ogretmenno = ?";
        PreparedStatement talepAlmaStmt = connection.connect.prepareStatement(talepAlmaQuery);
        talepAlmaStmt.setInt(1, ogretmenID);
        ResultSet talepAlmaSet = talepAlmaStmt.executeQuery();
        while (talepAlmaSet.next()) {
            if (talepAlmaSet.getInt("talep_durumu") == 0) {
            model.addRow(new Object[] {talepAlmaSet.getInt("ogrencino"), talepAlmaSet.getInt("ders_id"), "Beklemede"});
        } else if (talepAlmaSet.getInt("talep_durumu") == 1) {
            model.addRow(new Object[] {talepAlmaSet.getInt("ogrencino"), talepAlmaSet.getInt("ders_id"), "Onaylandı"});
        } else if (talepAlmaSet.getInt("talep_durumu") == 2) {
            model.addRow(new Object[] {talepAlmaSet.getInt("ogrencino"), talepAlmaSet.getInt("ders_id"), "Reddedildi"});
        } else {
            model.addRow(new Object[] {talepAlmaSet.getInt("ogrencino"), talepAlmaSet.getInt("ders_id"), "Talep Yapılmadı"});
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
                    int response = JOptionPane.showConfirmDialog(null, "Talebi onaylamak istediğinize emin misiniz ?" + "\n" + "Öğrencinin mesajı: \n" + mesaj, "Talep Durum", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        connection.baglan();
                        String durumGuncelleQuery = "update talepler_tablo set talep_durumu = ? where ogrencino = ? and ders_id = ? and ogretmenno = ?";
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
                            durumGuncelleStmt.setInt(4, ogretmenID);
                            durumGuncelleStmt.executeUpdate();
                            connection.connect.close();
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } else if (response == JOptionPane.NO_OPTION){
                        connection.baglan();
                        String durumGuncelleQuery = "update talepler_tablo set talep_durumu = ? where ogrencino = ? and ders_id = ? and ogretmenno = ?";
                        PreparedStatement durumGuncelleStmt;
                        try {
                            int seciliSatir = talepTablosu.getSelectedRow();
                            int seciliSutun = talepTablosu.getSelectedColumn();
                            String veri = talepTablosu.getValueAt(seciliSatir, seciliSutun).toString();
                            String veri1 = talepTablosu.getValueAt(seciliSatir, seciliSutun + 1).toString();
                            durumGuncelleStmt = connection.connect.prepareStatement(durumGuncelleQuery);
                            durumGuncelleStmt.setInt(1, 2);
                            durumGuncelleStmt.setInt(2, Integer.parseInt(veri));
                            durumGuncelleStmt.setInt(3, Integer.parseInt(veri1));
                            durumGuncelleStmt.setInt(4, ogretmenID);
                            durumGuncelleStmt.executeUpdate();
                            connection.connect.close();
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
        talepTablosu.setFocusable(true);
        talepTablosu.requestFocusInWindow();


        notlandirma = new JButton("Notlandırma");
        notlandirma.setBounds(200, 265, 200, 50);
        notlandirma.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notlandirmaMouseClicked(evt);
            }
        });

        talepOgrenciID = new JTextField();
        talepOgrenciID.setVisible(false);
        talepOgrenciID.setForeground(Color.GRAY);
        talepOgrenciID.setBounds(20, 90, 160, 40);
        talepOgrenciID.setText("Öğrenci Numarası");
        talepOgrenciID.setFont(new Font("Arial", Font.BOLD, 12));
        talepOgrenciID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (talepOgrenciID.getText().equals("Öğrenci Numarası")) {
                    talepOgrenciID.setForeground(Color.BLACK);
                    talepOgrenciID.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (talepOgrenciID.getText().isEmpty()) {
                    talepOgrenciID.setForeground(Color.GRAY);
                    talepOgrenciID.setText("Öğrenci Numarası");
                }
            }
        });

        talepDersID = new JTextField();
        talepDersID.setVisible(false);
        talepDersID.setForeground(Color.GRAY);
        talepDersID.setBounds(20, 190, 160, 40);
        talepDersID.setText("Ders ID");
        talepDersID.setFont(new Font("Arial", Font.BOLD, 12));
        talepDersID.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (talepDersID.getText().equals("Ders ID")) {
                    talepDersID.setForeground(Color.BLACK);
                    talepDersID.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (talepDersID.getText().isEmpty()) {
                    talepDersID.setForeground(Color.GRAY);
                    talepDersID.setText("Ders ID");
                }
            }
        });

        talepOnayButton = new JButton("Talep Yolla");
        talepOnayButton.setVisible(false);
        talepOnayButton.setBounds(20, 290, 160, 40);
        talepOnayButton.addActionListener((ActionEvent e) -> {
            try {
                extracted2();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        add(bilgiGuncelleme);
        add(ilgiAlaniBox);
        add(ilgiAlaniButton);
        add(verilenDersBox);
        add(verilenDersEkleB);
        add(verilenDersSilmeB);
        add(kriterDersBox);
        add(kriterDersEklemeB);
        add(kriterDersSilmeB);
        add(talepler);
        add(talepOgrenciID);
        add(talepDersID);
        add(talepOnayButton);
        add(sP);
        add(notlandirma);
        add(panel);

        frame.setBounds(0, 0, 600, 450);
        frame.setContentAreaFilled(false);
    }

    private void extracted2() throws SQLException {
        String[] adSoyad = getTitle().split(" "); // Başlığı boşluk karakterine göre ayır
        if (adSoyad.length == 2) {
            String ad = adSoyad[0];
            String soyad = adSoyad[1];

                connection.baglan();
                String sorgu = "select sicilno from ogretmen_tablo where ogretmen_ad=? and ogretmen_soyad=?";
                PreparedStatement preparedStatement = connection.connect.prepareStatement(sorgu);
                preparedStatement.setString(1, ad);
                preparedStatement.setString(2, soyad);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    ogretmenID = resultSet.getInt("sicilno");
                }
                }
        connection.baglan();
        String ogrTalepQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) values (?,?,?,?)";
        try {
            PreparedStatement ogrTalepStmt = connection.connect.prepareStatement(ogrTalepQuery);
            ogrTalepStmt.setInt(1, Integer.parseInt(talepOgrenciID.getText().toString()));
            ogrTalepStmt.setInt(2, Integer.parseInt(talepDersID.getText().toString()));
            ogrTalepStmt.setInt(3, ogretmenID);
            ogrTalepStmt.setInt(4, 0);
            ogrTalepStmt.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private void extractedVerilenEkle() {
        if (getTitle().equals("Öğretmen Paneli")) {
            JOptionPane.showMessageDialog(rootPane, "İlgi alanı eklemek için önce giriş yapmanız gerekmektedir .", "Doğrulama Hatası", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] adSoyad = getTitle().split(" "); // Başlığı boşluk karakterine göre ayır

            if (adSoyad.length == 2) {
                String ad = adSoyad[0];
                String soyad = adSoyad[1];

                try {
                    connection.baglan();
                    String sorgu = "select sicilno from ogretmen_tablo where ogretmen_ad=? and ogretmen_soyad=?";
                    PreparedStatement preparedStatement = connection.connect.prepareStatement(sorgu);
                    preparedStatement.setString(1, ad);
                    preparedStatement.setString(2, soyad);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        int ogretmenID = resultSet.getInt("sicilno");
                        int dersID;
                        String dersEkleQuery = "insert into ogretmen_ders_tablo (ogretmen_no, verdigiders) values (?,?)";
                        String dersIDQuery = "select ders_id from dersler_tablo where dersler = ?";
                        PreparedStatement dersStmt = connection.connect.prepareStatement(dersIDQuery);
                        dersStmt.setString(1, verilenDersBox.getText());
                        ResultSet dersIDSet = dersStmt.executeQuery();
                        if (dersIDSet.next()) {
                            dersID = dersIDSet.getInt("ders_id");
                            PreparedStatement dersEkleStmt = connection.connect.prepareStatement(dersEkleQuery);
                            dersEkleStmt.setInt(1, ogretmenID);
                            dersEkleStmt.setInt(2, dersID);
                            dersEkleStmt.executeUpdate();
                        }
                    }
                } catch (SQLException e) {
                    e.getErrorCode();
                }
            }
        }
    }

    private void extractedVerilenSil() {
        if (getTitle().equals("Öğretmen Paneli")) {
            JOptionPane.showMessageDialog(rootPane, "İlgi alanı eklemek için önce giriş yapmanız gerekmektedir .", "Doğrulama Hatası", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] adSoyad = getTitle().split(" "); // Başlığı boşluk karakterine göre ayır

            if (adSoyad.length == 2) {
                String ad = adSoyad[0];
                String soyad = adSoyad[1];

                try {
                    connection.baglan();
                    String sorgu = "select sicilno from ogretmen_tablo where ogretmen_ad=? and ogretmen_soyad=?";
                    PreparedStatement preparedStatement = connection.connect.prepareStatement(sorgu);
                    preparedStatement.setString(1, ad);
                    preparedStatement.setString(2, soyad);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        int ogretmenID = resultSet.getInt("sicilno");
                        int dersID;
                        String dersSilmeQuery = "delete from ogretmen_ders_tablo where ogretmen_no=? and verdigiders=?";
                        String dersIDQuery = "select ders_id from dersler_tablo where dersler = ?";
                        PreparedStatement dersStmt = connection.connect.prepareStatement(dersIDQuery);
                        dersStmt.setString(1, verilenDersBox.getText());
                        ResultSet dersIDSet = dersStmt.executeQuery();
                        if (dersIDSet.next()) {
                            dersID = dersIDSet.getInt("ders_id");
                            PreparedStatement dersSilStmt = connection.connect.prepareStatement(dersSilmeQuery);
                            dersSilStmt.setInt(1, ogretmenID);
                            dersSilStmt.setInt(2, dersID);
                            dersSilStmt.executeUpdate();
                        }
                    }
                } catch (SQLException e) {
                    e.getErrorCode();
                }
            }
        }
    }

    private void extractedKriterEkle() {
        if (getTitle().equals("Öğretmen Paneli")) {
            JOptionPane.showMessageDialog(rootPane, "İlgi alanı eklemek için önce giriş yapmanız gerekmektedir .", "Doğrulama Hatası", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] adSoyad = getTitle().split(" "); // Başlığı boşluk karakterine göre ayır

            if (adSoyad.length == 2) {
                String ad = adSoyad[0];
                String soyad = adSoyad[1];

                try {
                    connection.baglan();
                    String sorgu = "select sicilno from ogretmen_tablo where ogretmen_ad=? and ogretmen_soyad=?";
                    PreparedStatement preparedStatement = connection.connect.prepareStatement(sorgu);
                    preparedStatement.setString(1, ad);
                    preparedStatement.setString(2, soyad);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        int ogretmenID = resultSet.getInt("sicilno");
                        int dersID;
                        String dersEkleQuery = "insert into ogretmen_kriterders (ogretmen_no, kriterders) values (?,?)";
                        String dersIDQuery = "select ders_id from dersler_tablo where dersler = ?";
                        PreparedStatement dersStmt = connection.connect.prepareStatement(dersIDQuery);
                        dersStmt.setString(1, kriterDersBox.getText());
                        ResultSet dersIDSet = dersStmt.executeQuery();
                        if (dersIDSet.next()) {
                            dersID = dersIDSet.getInt("ders_id");
                            PreparedStatement dersEkleStmt = connection.connect.prepareStatement(dersEkleQuery);
                            dersEkleStmt.setInt(1, ogretmenID);
                            dersEkleStmt.setInt(2, dersID);
                            dersEkleStmt.executeUpdate();
                        }
                    }
                } catch (SQLException e) {
                    e.getErrorCode();
                }
            }
        }
    }

    private void extractedKriterSil() {
        if (getTitle().equals("Öğretmen Paneli")) {
            JOptionPane.showMessageDialog(rootPane, "İlgi alanı eklemek için önce giriş yapmanız gerekmektedir .", "Doğrulama Hatası", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] adSoyad = getTitle().split(" "); // Başlığı boşluk karakterine göre ayır

            if (adSoyad.length == 2) {
                String ad = adSoyad[0];
                String soyad = adSoyad[1];

                try {
                    connection.baglan();
                    String sorgu = "select sicilno from ogretmen_tablo where ogretmen_ad=? and ogretmen_soyad=?";
                    PreparedStatement preparedStatement = connection.connect.prepareStatement(sorgu);
                    preparedStatement.setString(1, ad);
                    preparedStatement.setString(2, soyad);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        int ogretmenID = resultSet.getInt("sicilno");
                        int dersID;
                        String dersSilmeQuery = "delete from ogretmen_kriterders where ogretmen_no=? and kriterders=?";
                        String dersIDQuery = "select ders_id from dersler_tablo where dersler = ?";
                        PreparedStatement dersStmt = connection.connect.prepareStatement(dersIDQuery);
                        dersStmt.setString(1, kriterDersBox.getText());
                        ResultSet dersIDSet = dersStmt.executeQuery();
                        if (dersIDSet.next()) {
                            dersID = dersIDSet.getInt("ders_id");
                            PreparedStatement dersSilStmt = connection.connect.prepareStatement(dersSilmeQuery);
                            dersSilStmt.setInt(1, ogretmenID);
                            dersSilStmt.setInt(2, dersID);
                            dersSilStmt.executeUpdate();
                        }
                    }
                } catch (SQLException e) {
                    e.getErrorCode();
                }
            }
        }
    }

    private void extracted() {
        if (getTitle().equals("Öğretmen Paneli")) {
            JOptionPane.showMessageDialog(rootPane, "İlgi alanı eklemek için önce giriş yapmanız gerekmektedir .", "Doğrulama Hatası", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] adSoyad = getTitle().split(" "); // Başlığı boşluk karakterine göre ayır

            if (adSoyad.length == 2) {
                String ad = adSoyad[0];
                String soyad = adSoyad[1];

                try {
                    connection.baglan();
                    String sorgu = "select sicilno from ogretmen_tablo where ogretmen_ad=? AND ogretmen_soyad=?";
                    PreparedStatement preparedStatement = connection.connect.prepareStatement(sorgu);
                    preparedStatement.setString(1, ad);
                    preparedStatement.setString(2, soyad);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        int ogretmenID = resultSet.getInt("sicilno");
                        String ilgiEkleQuery = "insert into ogretmen_ilgialan_tablo (ogretmen_no, ilgialani) values (?,?)";
                        PreparedStatement ilgiEkleStmt = connection.connect.prepareStatement(ilgiEkleQuery);
                        ilgiEkleStmt.setInt(1, ogretmenID);
                        ilgiEkleStmt.setInt(2, ilgiAlaniBox.getSelectedIndex());
                        ilgiEkleStmt.executeUpdate();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void bilgiGuncellemeMouseClicked(java.awt.event.MouseEvent evt) {
        bilgiGuncelleme.setVisible(false);
        talepler.setVisible(false);
        notlandirma.setVisible(false);
        ilgiAlaniBox.setVisible(true);
        ilgiAlaniButton.setVisible(true);
        verilenDersBox.setVisible(true);
        verilenDersEkleB.setVisible(true);
        verilenDersSilmeB.setVisible(true);
        kriterDersBox.setVisible(true);
        kriterDersEklemeB.setVisible(true);
        kriterDersSilmeB.setVisible(true);
    }

    private void taleplerMouseClicked(java.awt.event.MouseEvent evt) {
        bilgiGuncelleme.setVisible(false);
        talepler.setVisible(false);
        notlandirma.setVisible(false);
        sP.setVisible(true);
        talepOgrenciID.setVisible(true);
        talepDersID.setVisible(true);
        talepOnayButton.setVisible(true);
    }

    private void notlandirmaMouseClicked(java.awt.event.MouseEvent evt) {
        // Notlandırmayı işle
    }
}
