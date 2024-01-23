package obs;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;

public class admin extends JFrame {

    static String[] ilgialani;
    static int indis = 0;
    static int sayac;
    static int uzunluk = 0;

    JPanel panel;

    JButton frame;
    JButton giris;
    JButton kayit;
    JButton update;
    JButton ders;
    JButton setDate;
    JButton updateFilter;
    JButton addData;
    JButton updData;
    JButton rmData;
    JButton createData;
    JButton maxDersButton;
    JButton alanAdd;
    JButton alanRm;
    JButton ogretmenButton;
    JButton atamaButton;
    JButton randomAtama;
    JButton notAtama;

    JLabel adminText;
    JLabel dateText;
    JLabel maxDersText;
    JLabel alanText;
    JLabel ogretmenText;
    JLabel talepText;
    JLabel atamaText;

    JComboBox gun;
    JComboBox ay;
    JComboBox yil;
    JComboBox updateChooser;
    JComboBox ogretmenBox;
    JComboBox dersAtama;

    JTable kayitTablosu;
    JScrollPane sP;

    DefaultTableModel listModel;
    JTable listTable;
    JScrollPane lsP;

    JTextField adminAd;
    JTextField maxDersField;
    JTextField alanField;
    JTextField ogretmenField;
    JTextField ogrenciAtama;
    JTextField ogretmenAtama;

    JPasswordField adminSifre;

    ImageIcon ico;
    ImageIcon icon;

    admin() throws SQLException {
        ico = new ImageIcon("img\\setting.png");
        icon = new ImageIcon("img\\search.png");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setTitle("Admin Paneli");
        setIconImage(ico.getImage());
        setResizable(false);
        ilgialani = new String[100];
        try {
            connection.baglan();
            String ilgiListeleme = "select * from ilgialanlar_tablo";
            PreparedStatement listeStmt = connection.connect.prepareStatement(ilgiListeleme);
            ResultSet listeSet = listeStmt.executeQuery();
            while (listeSet.next()) {
                ilgialani[indis] = listeSet.getString("ilgialanlari");
                indis++;
            }
        } catch (SQLException e) {
            e.getErrorCode();
        }
        for (String deneme : ilgialani) {
            if (deneme == null || deneme.isEmpty()) {
                uzunluk++;
            }
        }
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (kayit.isVisible() == false && notAtama.isVisible() == true) {
                        kayit.setVisible(true);
                        update.setVisible(true);
                        ders.setVisible(true);
                        lsP.setVisible(false);
                        talepText.setVisible(false);
                        atamaText.setVisible(false);
                        ogrenciAtama.setVisible(false);
                        ogretmenAtama.setVisible(false);
                        dersAtama.setVisible(false);
                        atamaButton.setVisible(false);
                        randomAtama.setVisible(false);
                        notAtama.setVisible(false);
                    }
                    if (update.isVisible() == false && sP.isVisible() == true) {
                        kayit.setVisible(true);
                        update.setVisible(true);
                        ders.setVisible(true);
                        updateChooser.setVisible(false);
                        updateFilter.setVisible(false);
                        sP.setVisible(false);
                        addData.setVisible(false);
                        updData.setVisible(false);
                        rmData.setVisible(false);
                        createData.setVisible(false);
                    }
                    if (ders.isVisible() == false && maxDersButton.isVisible() == true) {
                        kayit.setVisible(true);
                        update.setVisible(true);
                        ders.setVisible(true);
                        gun.setVisible(false);
                        ay.setVisible(false);
                        yil.setVisible(false);
                        dateText.setVisible(false);
                        setDate.setVisible(false);
                        maxDersButton.setVisible(false);
                        maxDersText.setVisible(false);
                        maxDersField.setVisible(false);
                        alanText.setVisible(false);
                        alanField.setVisible(false);
                        alanAdd.setVisible(false);
                        alanRm.setVisible(false);
                        ogretmenText.setVisible(false);
                        ogretmenBox.setVisible(false);
                        ogretmenField.setVisible(false);
                        ogretmenButton.setVisible(false);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

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
        frame.setBorderPainted(false);

        adminText = new JLabel("Admin Paneli");
        adminText.setBounds(125, 50, 350, 50);
        adminText.setFont(new Font("Arial", Font.BOLD, 47));
        adminText.setForeground(new Color(214, 219, 223));

        adminAd = new JTextField();
        adminAd.setFont(new java.awt.Font("Arial", 1, 19));
        adminAd.setText("Kullanıcı Adı");
        adminAd.setPreferredSize(new java.awt.Dimension(300, 50));
        adminAd.setForeground(Color.GRAY);
        adminAd.setBounds(150, 125, 300, 50);
        adminAd.setBorder(new EmptyBorder(5, 5, 5, 5));
        adminAd.setFont(new Font("Arial", Font.BOLD, 19));
        adminAd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (adminAd.getText().equals("Kullanıcı Adı")) {
                    adminAd.setForeground(Color.BLACK);
                    adminAd.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (adminAd.getText().isEmpty()) {
                    adminAd.setForeground(Color.GRAY);
                    adminAd.setText("Kullanıcı Adı");
                }
            }
        });

        adminSifre = new JPasswordField();
        adminSifre.setFont(new java.awt.Font("Arial", 0, 19));
        adminSifre.setText("Şifre");
        adminSifre.setPreferredSize(new java.awt.Dimension(300, 50));
        adminSifre.setForeground(Color.GRAY);
        adminSifre.setBounds(150, 195, 300, 50);
        adminSifre.setBorder(new EmptyBorder(5, 5, 5, 5));
        adminSifre.setFont(new Font("Arial", Font.BOLD, 19));
        adminSifre.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (adminSifre.getText().equals("Şifre")) {
                    adminSifre.setForeground(Color.BLACK);
                    adminSifre.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (adminSifre.getText().isEmpty()) {
                    adminSifre.setForeground(Color.GRAY);
                    adminSifre.setText("Şifre");
                }
            }
        });

        giris = new JButton("GİRİŞ");
        giris.setPreferredSize(new java.awt.Dimension(300, 50));
        giris.setBounds(150, 265, 300, 50);
        giris.setBorder(new EmptyBorder(0, 0, 0, 0));
        giris.setFont(new Font("Arial", Font.BOLD, 20));
        giris.setBackground(new Color(106, 90, 205));
        giris.setForeground(new Color(214, 219, 223));
        giris.addActionListener((ActionEvent e) -> {
            if (adminAd.getText().equals("admin") && adminSifre.getText().equals("root")) {
                adminAd.setVisible(false);
                adminSifre.setVisible(false);
                giris.setVisible(false);
                adminText.setVisible(false);
                kayit.setVisible(true);
                update.setVisible(true);
                ders.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Girilen bilgileri kontrol ediniz !", "Doğrulama Hatası", JOptionPane.ERROR_MESSAGE);
            }
        });

        kayit = new JButton();
        kayit.setVisible(false);
        kayit.setText("Kayıt İşlemleri");
        kayit.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    kayitMouseClicked(evt);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        kayit.setBounds(200, 125, 200, 50);

        listModel = new DefaultTableModel();
        listModel.addColumn("Öğrenci ID");
        listModel.addColumn("Öğretmen ID");
        listModel.addColumn("Talep Durumu");
        listTable = new JTable(listModel);
        JTableHeader LtableHeader = listTable.getTableHeader();
        LtableHeader.setFont(new Font("Arial", Font.BOLD, 14));
        lsP = new JScrollPane(listTable);
        connection.baglan();
        String talepQuery = "select * from talepler_tablo";
        PreparedStatement talepStmt = connection.connect.prepareStatement(talepQuery);
        ResultSet talepSet = talepStmt.executeQuery();
        while (talepSet.next()) {
            talepSet.getInt("ogrencino");
            talepSet.getInt("ogretmenno");
            if (talepSet.getInt("talep_durumu") == 0) {
                listModel.addRow(new Object[]{talepSet.getInt("ogrencino"), talepSet.getInt("ogretmenno"), "Beklemede"});
            } else if (talepSet.getInt("talep_durumu") == 1) {
                listModel.addRow(new Object[]{talepSet.getInt("ogrencino"), talepSet.getInt("ogretmenno"), "Onaylandı"});
            } else {
                listModel.addRow(new Object[]{talepSet.getInt("ogrencino"), talepSet.getInt("ogretmenno"), "Reddedildi"});
            }
        }
        lsP.setBounds(200, 100, 400, 350);
        lsP.setVisible(false);

        update = new JButton();
        update.setVisible(false);
        update.setText("Bilgi Güncelleme");
        update.setMaximumSize(new java.awt.Dimension(200, 50));
        update.setMinimumSize(new java.awt.Dimension(200, 50));
        update.setPreferredSize(new java.awt.Dimension(200, 50));
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateMouseClicked(evt);
            }
        });
        update.setBounds(200, 195, 200, 50);
        kayitTablosu = new JTable(new DefaultTableModel());
        JTableHeader tableHeader = kayitTablosu.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 11));
        sP = new JScrollPane(kayitTablosu);
        sP.setBounds(150, 0, 435, 450);
        sP.setVisible(false);
        kayitTablosu.addKeyListener(new KeyListener() {
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
        kayitTablosu.setFocusable(true);
        kayitTablosu.requestFocusInWindow();

        ders = new JButton();
        ders.setVisible(false);
        ders.setText("Ders İşlemleri");
        ders.setPreferredSize(new java.awt.Dimension(200, 50));
        ders.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    dersMouseClicked(evt);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        ders.addActionListener((java.awt.event.ActionEvent evt) -> {
            dersActionPerformed(evt);
        });
        ders.setBounds(200, 265, 200, 50);

        dateText = new JLabel();
        dateText.setVisible(false);
        dateText.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 18));
        dateText.setText("Ders seçimi için son gün:");
        dateText.setForeground(new Color(214, 219, 223));
        dateText.setBounds(32, 30, 200, 40);

        setDate = new JButton();
        setDate.setVisible(false);
        setDate.setText("AYARLA");
        setDate.setBounds(470, 40, 77, 27);
        setDate.setFont(new Font("Arial", Font.BOLD, 9));
        setDate.addActionListener((ActionEvent e) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMMyyyy");
            String ayarlananTarih = gun.getSelectedItem().toString() + ay.getSelectedItem().toString() + yil.getSelectedItem().toString();
            LocalDate girilenTarih = LocalDate.parse(ayarlananTarih, formatter);
            kontrol.zaman = girilenTarih;
            kontrol.tarihKontrol();
            if (kontrol.a == 1) {
                JOptionPane.showMessageDialog(rootPane, "Lütfen geçerli bir tarih giriniz .", "Hata", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Tarih ayarlandı .", "Tarih", JOptionPane.PLAIN_MESSAGE);
            }
        });

        gun = new JComboBox<>();
        gun.setVisible(false);
        gun.addActionListener((java.awt.event.ActionEvent evt) -> {
            gunActionPerformed(evt);
        });
        gun.setBounds(230, 40, 76, 26);
        gun.addItem("Gün");
        for (int a = 1; a < 32; a++) {
            gun.addItem("" + a);
        }

        ay = new JComboBox<>();
        ay.setVisible(false);
        ay.setBounds(310, 40, 76, 26);
        ay.addItem("Ay");
        ay.addItem("Ocak");
        ay.addItem("Şubat");
        ay.addItem("Mart");
        ay.addItem("Nisan");
        ay.addItem("Mayıs");
        ay.addItem("Haziran");
        ay.addItem("Temmuz");
        ay.addItem("Ağustos");
        ay.addItem("Eylül");
        ay.addItem("Ekim");
        ay.addItem("Kasım");
        ay.addItem("Aralık");

        yil = new JComboBox<>();
        yil.setVisible(false);
        yil.setBounds(390, 40, 76, 26);
        yil.addItem("Yıl");
        yil.addItem("2023");
        yil.addItem("2024");

        updateChooser = new JComboBox<>();
        updateChooser.setVisible(false);
        updateChooser.setBounds(10, 46, 97, 28);
        updateChooser.addItem("Seçiniz");
        updateChooser.addItem("Öğrenci");
        updateChooser.addItem("Öğretmen");

        updateFilter = new JButton(icon);
        updateFilter.setVisible(false);
        updateFilter.setBounds(107, 40, 40, 40);
        updateFilter.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                updateFilterActionPerformed(evt);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        updateFilter.setBorder(new EmptyBorder(5, 5, 5, 5));
        updateFilter.setContentAreaFilled(false);

        addData = new JButton("Kayıt Ekle");
        addData.setVisible(false);
        addData.setBounds(10, 110, 130, 40);
        addData.addActionListener((java.awt.event.ActionEvent evt) -> {
            addDataActionPerformed(evt);
        });

        updData = new JButton("Kayıt Güncelle");
        updData.setVisible(false);
        updData.setBounds(10, 180, 130, 40);
        updData.addActionListener((java.awt.event.ActionEvent evt) -> {
            updDataActionPerformed(evt);
        });

        rmData = new JButton("Kayıt Sil");
        rmData.setVisible(false);
        rmData.setBounds(10, 250, 130, 40);
        rmData.addActionListener((java.awt.event.ActionEvent evt) -> {
            rmDataActionPerformed(evt);
        });

        createData = new JButton("Rasgele Kayıt Ekle");
        createData.setVisible(false);
        createData.setBounds(10, 320, 130, 40);
        createData.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                createDataActionPerformed(evt);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        maxDersButton = new JButton("AYARLA");
        maxDersButton.setVisible(false);
        maxDersButton.setBounds(447, 90, 100, 40);
        maxDersButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            maxDersButtonActionPerformed(evt);
        });

        maxDersText = new JLabel("Alınabilecek en fazla ders sayısı:");
        maxDersText.setVisible(false);
        maxDersText.setBounds(32, 90, 275, 40);
        maxDersText.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 18));
        maxDersText.setForeground(new Color(214, 219, 223));

        maxDersField = new JTextField("Ders Sayısı");
        maxDersField.setVisible(false);
        maxDersField.setBounds(322, 90, 105, 40);
        maxDersField.setForeground(Color.GRAY);
        maxDersField.setFont(new Font("Arial", Font.BOLD, 12));
        maxDersField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (maxDersField.getText().equals("Ders Sayısı")) {
                    maxDersField.setForeground(Color.BLACK);
                    maxDersField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (maxDersField.getText().isEmpty()) {
                    maxDersField.setForeground(Color.GRAY);
                    maxDersField.setText("Ders Sayısı");
                }
            }
        });

        alanText = new JLabel("İlgi alanı:");
        alanText.setVisible(false);
        alanText.setBounds(32, 150, 120, 40);
        alanText.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 18));
        alanText.setForeground(new Color(214, 219, 223));

        alanField = new JTextField("İlgi Alanı");
        alanField.setVisible(false);
        alanField.setBounds(182, 150, 150, 40);
        alanField.setForeground(Color.GRAY);
        alanField.setFont(new Font("Arial", Font.BOLD, 12));
        alanField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (alanField.getText().equals("İlgi Alanı")) {
                    alanField.setForeground(Color.BLACK);
                    alanField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (alanField.getText().isEmpty()) {
                    alanField.setForeground(Color.GRAY);
                    alanField.setText("İlgi Alanı");
                }
            }
        });

        alanAdd = new JButton("Ekle");
        alanAdd.setVisible(false);
        alanAdd.setBounds(347, 150, 90, 40);
        alanAdd.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                alanAddActionPerformed(evt);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        alanRm = new JButton("Sil");
        alanRm.setVisible(false);
        alanRm.setBounds(457, 150, 90, 40);
        alanRm.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                alanRmActionPerformed(evt);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        ogretmenText = new JLabel("Öğretmen Adı:");
        ogretmenText.setVisible(false);
        ogretmenText.setFont(new java.awt.Font("Times New Roman", Font.PLAIN, 18));
        ogretmenText.setForeground(new Color(214, 219, 223));
        ogretmenText.setBounds(32, 210, 150, 40);

        ogretmenBox = new JComboBox<>();
        ogretmenBox.removeAllItems();
        ogretmenBox.setVisible(false);
        ogretmenBox.setBounds(207, 210, 100, 40);
        ogretmenBox.addItem("Öğretmen");

        ogretmenField = new JTextField("Kontenjan");
        ogretmenField.setVisible(false);
        ogretmenField.setBounds(325, 210, 100, 40);
        ogretmenField.setForeground(Color.GRAY);
        ogretmenField.setFont(new Font("Arial", Font.BOLD, 12));
        ogretmenField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ogretmenField.getText().equals("Kontenjan")) {
                    ogretmenField.setForeground(Color.BLACK);
                    ogretmenField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ogretmenField.getText().isEmpty()) {
                    ogretmenField.setForeground(Color.GRAY);
                    ogretmenField.setText("Kontenjan");
                }
            }
        });

        ogretmenButton = new JButton("AYARLA");
        ogretmenButton.setVisible(false);
        ogretmenButton.setBounds(447, 210, 100, 40);
        ogretmenButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                ogretmenButtonActionPerformed(evt);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        talepText = new JLabel("Ders Talep Geçmişi");
        talepText.setVisible(false);
        talepText.setBounds(200, 0, 400, 100);
        talepText.setFont(new Font("Arial", Font.BOLD, 41));
        talepText.setForeground(new Color(0, 0, 52));
        talepText.setOpaque(true);
        talepText.setBackground(new Color(235, 235, 235));

        atamaText = new JLabel("Ders Atama İşlemleri");
        atamaText.setVisible(false);
        atamaText.setBounds(20, 0, 160, 40);
        atamaText.setFont(new Font("Arial", Font.BOLD, 16));
        atamaText.setForeground(new Color(214, 219, 223));

        ogrenciAtama = new JTextField("Öğrenci No");
        ogrenciAtama.setVisible(false);
        ogrenciAtama.setBounds(20, 40, 160, 40);
        ogrenciAtama.setForeground(Color.GRAY);
        ogrenciAtama.setFont(new Font("Arial", Font.BOLD, 12));
        ogrenciAtama.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ogrenciAtama.getText().equals("Öğrenci No")) {
                    ogrenciAtama.setForeground(Color.BLACK);
                    ogrenciAtama.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ogrenciAtama.getText().isEmpty()) {
                    ogrenciAtama.setForeground(Color.GRAY);
                    ogrenciAtama.setText("Öğrenci No");
                }
            }
        });

        ogretmenAtama = new JTextField("Öğretmen Sicil No");
        ogretmenAtama.setVisible(false);
        ogretmenAtama.setBounds(20, 100, 160, 40);
        ogretmenAtama.setForeground(Color.GRAY);
        ogretmenAtama.setFont(new Font("Arial", Font.BOLD, 12));
        ogretmenAtama.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ogretmenAtama.getText().equals("Öğretmen Sicil No")) {
                    ogretmenAtama.setForeground(Color.BLACK);
                    ogretmenAtama.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ogretmenAtama.getText().isEmpty()) {
                    ogretmenAtama.setForeground(Color.GRAY);
                    ogretmenAtama.setText("Öğretmen Sicil No");
                }
            }
        });

        dersAtama = new JComboBox<>();
        dersAtama.addItem("Ders Adı");
        dersAtama.setVisible(false);
        dersAtama.setBounds(20, 160, 160, 40);

        atamaButton = new JButton("EKLE");
        atamaButton.setVisible(false);
        atamaButton.setBounds(20, 220, 160, 40);
        atamaButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                atamaButtonActionPerformed(evt);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        randomAtama = new JButton("Rasgele Atama");
        randomAtama.setVisible(false);
        randomAtama.setBounds(20, 280, 160, 40);
        randomAtama.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                randomAtamaActionPerformed(evt);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        notAtama = new JButton("Agno' ya Göre Atama");
        notAtama.setVisible(false);
        notAtama.setBounds(20, 340, 160, 40);
        notAtama.addActionListener((java.awt.event.ActionEvent evt) -> {
            try {
                notAtamaActionPerformed(evt);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        add(adminText);
        add(adminAd);
        add(adminSifre);
        add(giris);
        add(kayit);
        add(update);
        add(ders);
        add(dateText);
        add(gun);
        add(ay);
        add(yil);
        add(updateChooser);
        add(updateFilter);
        add(sP);
        add(setDate);
        add(addData);
        add(updData);
        add(rmData);
        add(createData);
        add(maxDersButton);
        add(maxDersText);
        add(maxDersField);
        add(alanText);
        add(alanField);
        add(alanAdd);
        add(alanRm);
        add(ogretmenText);
        add(ogretmenBox);
        add(ogretmenField);
        add(ogretmenButton);
        add(lsP);
        add(talepText);
        add(atamaText);
        add(ogrenciAtama);
        add(ogretmenAtama);
        add(dersAtama);
        add(atamaButton);
        add(randomAtama);
        add(notAtama);
        add(frame);
        add(panel);

        frame.setBounds(0, 0, 600, 450);
        frame.setContentAreaFilled(false);
    }

    private void kayitMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {
        kayit.setVisible(false);
        update.setVisible(false);
        ders.setVisible(false);
        lsP.setVisible(true);
        talepText.setVisible(true);
        atamaText.setVisible(true);
        ogrenciAtama.setVisible(true);
        ogretmenAtama.setVisible(true);
        dersAtama.setVisible(true);
        atamaButton.setVisible(true);
        randomAtama.setVisible(true);
        notAtama.setVisible(true);
        dersAtama.removeAllItems();
        dersAtama.addItem("Ders Adı");
        connection.baglan();
        String dersQuery = "select * from dersler_tablo";
        PreparedStatement pStmt = connection.connect.prepareStatement(dersQuery);
        ResultSet rSet = pStmt.executeQuery();
        while (rSet.next()) {
            String veri = rSet.getString("dersler");
            dersAtama.addItem(veri);
        }
    }

    private void updateMouseClicked(java.awt.event.MouseEvent evt) {
        kayit.setVisible(false);
        update.setVisible(false);
        ders.setVisible(false);
        updateChooser.setVisible(true);
        updateFilter.setVisible(true);
        sP.setVisible(true);
        addData.setVisible(true);
        updData.setVisible(true);
        rmData.setVisible(true);
        createData.setVisible(true);
    }

    private void dersMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {
        sinirla();
        kayit.setVisible(false);
        update.setVisible(false);
        ders.setVisible(false);
        gun.setVisible(true);
        ay.setVisible(true);
        yil.setVisible(true);
        dateText.setVisible(true);
        setDate.setVisible(true);
        maxDersButton.setVisible(true);
        maxDersText.setVisible(true);
        maxDersField.setVisible(true);
        alanText.setVisible(true);
        alanField.setVisible(true);
        alanAdd.setVisible(true);
        alanRm.setVisible(true);
        ogretmenText.setVisible(true);
        ogretmenBox.setVisible(true);
        ogretmenField.setVisible(true);
        ogretmenButton.setVisible(true);
        ogretmenBox.removeAllItems();
        ogretmenBox.addItem("Öğretmen");
        connection.baglan();
        String ogretmenQuery = "select * from ogretmen_tablo";
        PreparedStatement pStmt = connection.connect.prepareStatement(ogretmenQuery);
        ResultSet rSet = pStmt.executeQuery();
        while (rSet.next()) {
            String veri = rSet.getString("ogretmen_ad") + " " + rSet.getString("ogretmen_soyad") + "-" + rSet.getInt("sicilno");
            ogretmenBox.addItem(veri);
        }
    }

    private void dersActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void ogretmenButtonActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        connection.baglan();
        String secilenDeger = ogretmenBox.getSelectedItem().toString();
        StringBuilder sB = new StringBuilder();
        int no;
        for (int a = 0; a < secilenDeger.length(); a++) {
            if (secilenDeger.charAt(a) == '0' || secilenDeger.charAt(a) == '1' || secilenDeger.charAt(a) == '2' || secilenDeger.charAt(a) == '3' || secilenDeger.charAt(a) == '4' || secilenDeger.charAt(a) == '5' || secilenDeger.charAt(a) == '6' || secilenDeger.charAt(a) == '7' || secilenDeger.charAt(a) == '8' || secilenDeger.charAt(a) == '9') {
                sB.append(secilenDeger.charAt(a));
            }
        }
        no = Integer.parseInt(sB.toString());
        int kontenjan = Integer.parseInt(ogretmenField.getText());
        String guncellemeQuery = "update ogretmen_tablo set kontenjan = ? where sicilno = ?";
        PreparedStatement pStmt = connection.connect.prepareStatement(guncellemeQuery);
        pStmt.setInt(1, kontenjan);
        pStmt.setInt(2, no);
        pStmt.executeQuery();
    }

    private void atamaButtonActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        if (ogrenciAtama.getText() != null && ogretmenAtama.getText() != null && dersAtama.getSelectedIndex() != 0) {
        connection.baglan();
        String guncellemeQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) values(?,?,?,?)";
        PreparedStatement pStmt = connection.connect.prepareStatement(guncellemeQuery);
        pStmt.setInt(1, Integer.parseInt(ogrenciAtama.getText()));
        pStmt.setInt(2, dersAtama.getSelectedIndex());
        pStmt.setInt(3, Integer.parseInt(ogretmenAtama.getText()));
        pStmt.setInt(4, 1);
        pStmt.executeUpdate();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Gerekli alanları doldurunuz .", "Bilgi Eksikliği", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void notAtamaActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        try {
            connection.baglan();
            Statement statement = connection.connect.createStatement();

            // talepler_tablo'daki öğrenci_no değerlerini al
            ResultSet resultSet = statement.executeQuery("select distinct ogrencino from talepler_tablo");

            List<Integer> existingStudentNumbers = new ArrayList<>();
            while (resultSet.next()) {
                int ogrenciNo = resultSet.getInt("ogrencino");
                existingStudentNumbers.add(ogrenciNo);
            }

            resultSet.close();

            // ogrenci_tablo'daki tüm öğrenci numaralarını al
            ResultSet studentResultSet = statement.executeQuery("select ogrenci_numara from ogrenci_tablo");
            List<Integer> allStudentNumbers = new ArrayList<>();
            List<Float> allStudentAverage = new ArrayList<>();
            while (studentResultSet.next()) {
                int ogrenciNo = studentResultSet.getInt("ogrenci_numara");
                float averageNo = studentResultSet.getFloat("not_ort");
                allStudentNumbers.add(ogrenciNo);
                allStudentAverage.add(averageNo);
            }

            studentResultSet.close();

            // ogrenci_tablo'da olmayan öğrenci numaralarını tespit et
            List<Integer> missingStudentNumbers = new ArrayList<>();
            List<Float> missingStudentAverage = new ArrayList<>();
            for (int ogrenciNo : allStudentNumbers) {
                if (!existingStudentNumbers.contains(ogrenciNo)) {
                    missingStudentNumbers.add(ogrenciNo);
                }
            }

            // Talepler_tablo'ya veri girişi yap
            for (int ogrenciNo : missingStudentNumbers) {
                int rasgele = 0;
                if (rasgele % 3 == 0) {
                    if (rasgele % 2 == 0) {
                String insertQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) VALUES (" + ogrenciNo + ", 1, 103033567,1)";
                statement.executeUpdate(insertQuery);
                    } else {
                String insertQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) VALUES (" + ogrenciNo + ", 2, 103033567,1)";
                statement.executeUpdate(insertQuery);
                    }
                rasgele++;
                } else if (rasgele % 3 == 1) {
                    if (rasgele % 2 == 0) {
                String insertQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) VALUES (" + ogrenciNo + ", 1, 103033572,1)";
                statement.executeUpdate(insertQuery);
                    } else {
                String insertQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) VALUES (" + ogrenciNo + ", 2, 103033572,1)";
                statement.executeUpdate(insertQuery);
                    }
                    rasgele++;
                } else {
                    if (rasgele % 2 == 0) {
                String insertQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) VALUES (" + ogrenciNo + ", 1, 103033576,1)";
                statement.executeUpdate(insertQuery);
                    } else {
                String insertQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) VALUES (" + ogrenciNo + ", 2, 103033576,1)";
                statement.executeUpdate(insertQuery);
                    }
                    rasgele++;
                }
            }

            statement.close();
            connection.connect.close();

            JOptionPane.showMessageDialog(rootPane, "Tüm öğrenciler derslere rasgele ve eşit şekilde atandı .", "Rasgele Atama Tamamlandı", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void randomAtamaActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {
        try {
            connection.baglan();
            Statement statement = connection.connect.createStatement();

            // talepler_tablo'daki öğrenci_no değerlerini al
            ResultSet resultSet = statement.executeQuery("select distinct ogrencino from talepler_tablo");

            List<Integer> existingStudentNumbers = new ArrayList<>();
            while (resultSet.next()) {
                int ogrenciNo = resultSet.getInt("ogrencino");
                existingStudentNumbers.add(ogrenciNo);
            }

            resultSet.close();

            // ogrenci_tablo'daki tüm öğrenci numaralarını al
            ResultSet studentResultSet = statement.executeQuery("select ogrenci_numara from ogrenci_tablo");
            List<Integer> allStudentNumbers = new ArrayList<>();
            while (studentResultSet.next()) {
                int ogrenciNo = studentResultSet.getInt("ogrenci_numara");
                allStudentNumbers.add(ogrenciNo);
            }

            studentResultSet.close();

            // ogrenci_tablo'da olmayan öğrenci numaralarını tespit et
            List<Integer> missingStudentNumbers = new ArrayList<>();
            for (int ogrenciNo : allStudentNumbers) {
                if (!existingStudentNumbers.contains(ogrenciNo)) {
                    missingStudentNumbers.add(ogrenciNo);
                }
            }

            // Talepler_tablo'ya veri girişi yap
            for (int ogrenciNo : missingStudentNumbers) {
                int rasgele = 0;
                if (rasgele % 3 == 0) {
                    if (rasgele % 2 == 0) {
                String insertQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) VALUES (" + ogrenciNo + ", 1, 103033567,1)";
                statement.executeUpdate(insertQuery);
                    } else {
                String insertQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) VALUES (" + ogrenciNo + ", 2, 103033567,1)";
                statement.executeUpdate(insertQuery);
                    }
                rasgele++;
                } else if (rasgele % 3 == 1) {
                    if (rasgele % 2 == 0) {
                String insertQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) VALUES (" + ogrenciNo + ", 1, 103033572,1)";
                statement.executeUpdate(insertQuery);
                    } else {
                String insertQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) VALUES (" + ogrenciNo + ", 2, 103033572,1)";
                statement.executeUpdate(insertQuery);
                    }
                    rasgele++;
                } else {
                    if (rasgele % 2 == 0) {
                String insertQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) VALUES (" + ogrenciNo + ", 1, 103033576,1)";
                statement.executeUpdate(insertQuery);
                    } else {
                String insertQuery = "insert into talepler_tablo (ogrencino, ders_id, ogretmenno, talep_durumu) VALUES (" + ogrenciNo + ", 2, 103033576,1)";
                statement.executeUpdate(insertQuery);
                    }
                    rasgele++;
                }
            }

            statement.close();
            connection.connect.close();

            JOptionPane.showMessageDialog(rootPane, "Tüm öğrenciler derslere rasgele ve eşit şekilde atandı .", "Rasgele Atama Tamamlandı", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void gunActionPerformed(ActionEvent evt) {
    }

    private void updateFilterActionPerformed(ActionEvent evt) throws SQLException {
        if (updateChooser.getSelectedItem().toString() == "Öğrenci") {
            connection.baglan();
            String listQuery = "select * from ogrenci_tablo";
            PreparedStatement pStmt = connection.connect.prepareStatement(listQuery);
            ResultSet rSet = pStmt.executeQuery();
            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            model.addColumn("Öğrenci No");
            model.addColumn("Ad");
            model.addColumn("Soyad");
            model.addColumn("Not Ortalaması");

            while (rSet.next()) {
                // Her bir satırı modele ekleyin
                model.addRow(new Object[]{
                    rSet.getInt("ogrenci_numara"),
                    rSet.getString("ogrenci_ad"),
                    rSet.getString("ogrenci_soyad"),
                    rSet.getDouble("not_ort")
                });
            }

            // Modeli JTable'a ayarlayın
            kayitTablosu.setModel(model);
        } else if (updateChooser.getSelectedItem().toString() == "Öğretmen") {
            connection.baglan();
            String listQuery = "select * from ogretmen_tablo";
            PreparedStatement pStmt = connection.connect.prepareStatement(listQuery);
            ResultSet rSet = pStmt.executeQuery();
            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            model.addColumn("Sicil No");
            model.addColumn("Ad");
            model.addColumn("Soyad");

            while (rSet.next()) {
                model.addRow(new Object[]{
                    rSet.getInt("sicilno"),
                    rSet.getString("ogretmen_ad"),
                    rSet.getString("ogretmen_soyad")
                });
            }

            kayitTablosu.setModel(model);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Lütfen görüntülemek istediğiniz tabloyu seçiniz .", "Seçim Hatası", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void alanAddActionPerformed(ActionEvent evt) throws SQLException {
        if (alanField.getText() != null) {
            connection.baglan();
            String eklemeQuery = "insert into ilgialanlar_tablo (ilgialan_id, ilgialanlari) values (?,?)";
            PreparedStatement eklemeStmt = connection.connect.prepareStatement(eklemeQuery);
            sayac = ilgialani.length - uzunluk + 1;
            eklemeStmt.setInt(1, sayac);
            eklemeStmt.setString(2, alanField.getText());
            eklemeStmt.executeUpdate();
            sayac++;
            connection.connect.close();
            uzunluk--;
        }
    }

    private void alanRmActionPerformed(ActionEvent evt) throws SQLException {
        for (int a = 0; a < ilgialani.length; a++) {
            if (alanField.getText().equals(ilgialani[a])) {
                indis = a + 1;
            }
        }
        if (alanField.getText() != null) {
            connection.baglan();
            String ogretmenIlgiQuery = "delete from ogretmen_ilgialan_tablo where ilgialani = ?";
            PreparedStatement ogretmenIlgiStmt = connection.connect.prepareStatement(ogretmenIlgiQuery);
            ogretmenIlgiStmt.setInt(1, indis);
            ogretmenIlgiStmt.executeUpdate();
            connection.connect.close();
            connection.baglan();
            String silmeQuery = "delete from ilgialanlar_tablo where ilgialan = ?";
            PreparedStatement silmeStmt = connection.connect.prepareStatement(silmeQuery);
            silmeStmt.setInt(1, indis);
            silmeStmt.executeUpdate();
            connection.connect.close();
        }
    }

    private void addDataActionPerformed(ActionEvent evt) {
        kayit k = new kayit();
        k.setVisible(true);
        k.flag = 0;
        connection.flag = 0;
    }

    private void updDataActionPerformed(ActionEvent evt) {
        kayit k = new kayit();
        k.setVisible(true);
        k.flag = 2;
        connection.flag = 2;
    }

    private void rmDataActionPerformed(ActionEvent evt) {
        kayit k = new kayit();
        k.setVisible(true);
        k.flag = 1;
        connection.flag = 1;
    }

    private void createDataActionPerformed(ActionEvent evt) throws SQLException {
        ogrenciEkle oE = new ogrenciEkle();
        String ad, soyad, agno, ogrNo;
        for (int a = 0; a < 50; a++) {
            /*veritabanı bağlandığında burada öğrenciEkle sınıfından bir nesne oluşturulup bu bilgilerin veritabanına aktarılması lazım */
            oE.adUret();
            oE.soyadUret();
            oE.agnoUret();
            oE.ogrNo();
            ad = oE.secilenAd;
            soyad = oE.secilenSoyad;
            agno = oE.agno;
            ogrNo = oE.ogrNo;
            connection.baglan();
            connection.ekleCikar(ogrNo, ad, soyad, agno);
        }
    }

    private void maxDersButtonActionPerformed(ActionEvent evt) {
        kontenjanKisitlamasi();
        if (kontenjanKisitlamasi() == false) {
            JOptionPane.showMessageDialog(rootPane, "Kontenjan alanına sadece sayı girilebilir .", "Hata", JOptionPane.ERROR_MESSAGE);
        } else if (maxDersField.getText().length() > 2 || maxDersField.getText().length() <= 0) {
            JOptionPane.showMessageDialog(rootPane, "Kontenjan 0-100 arasında olmalı .", "Kontenjan Hatası", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(rootPane, "İşlem Gerçekleşti .", "İşlem Oldu", JOptionPane.PLAIN_MESSAGE);
            ogrenci.secilebilecekDersFlag = Integer.parseInt(maxDersField.getText());
            maxDersField.setText("Ders Sayısı");
            maxDersField.setForeground(Color.GRAY);
        }
    }

    private void sinirla() {
        DocumentFilter filter = new DocumentFilter() {
            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= 100) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) <= 100) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };
        ((AbstractDocument) alanField.getDocument()).setDocumentFilter(filter);
    }

    private boolean kontenjanKisitlamasi() {
        try {
            Integer.parseInt(maxDersField.getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
