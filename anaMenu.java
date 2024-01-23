package obs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class anaMenu extends JFrame {

    JPanel panel;

    JButton cikis;
    JButton admin;
    JButton ogrenciGiris;
    JButton ogrenciFrame;
    JButton hocaGiris;
    JButton hocaFrame;

    JLabel ogrenciText;
    JLabel hocaText;
    JLabel create;

    JTextField ogrenciAd;
    JTextField hocaAd;

    JPasswordField ogrenciSifre;
    JPasswordField hocaSifre;

    ImageIcon cikisPNG;
    ImageIcon adminPNG;
    ImageIcon ico;

    anaMenu() throws InterruptedException, SQLException {
        ico = new ImageIcon("img\\modular.png");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLayout(new BorderLayout());
        setIconImage(ico.getImage());
        setResizable(false);

        // Arka plan resmi eklemek için oluşturulan JPanel
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("img\\HD-wallpaper-blue-faded-colors-abstract.jpg").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new BorderLayout());

        cikisPNG = new ImageIcon("img\\logout.png");
        cikis = new JButton(cikisPNG);

        adminPNG = new ImageIcon("img\\setting.png");
        admin = new JButton(adminPNG);

        ogrenciText = new JLabel("Öğrenci Giriş");
        ogrenciAd = new JTextField("Kullanıcı Adı");
        ogrenciAd.setForeground(Color.GRAY);
        ogrenciSifre = new JPasswordField("Şifre");
        ogrenciSifre.setForeground(Color.GRAY);
        ogrenciGiris = new JButton("GİRİŞ");
        ogrenciFrame = new JButton();

        hocaText = new JLabel("Öğretmen Giriş");
        hocaAd = new JTextField("Kullanıcı Adı");
        hocaAd.setForeground(Color.GRAY);
        hocaSifre = new JPasswordField("Şifre");
        hocaSifre.setForeground(Color.GRAY);
        hocaGiris = new JButton("GİRİŞ");
        hocaFrame = new JButton();

        create = new JLabel("created by Bones & MaltazaR");

        add(cikis);
        add(admin);
        add(create);
        add(ogrenciText);
        add(ogrenciAd);
        add(ogrenciSifre);
        add(ogrenciGiris);
        add(ogrenciFrame);
        add(hocaText);
        add(hocaAd);
        add(hocaSifre);
        add(hocaGiris);
        add(hocaFrame);
        add(panel);

        cikis.setBounds(1450, 30, 64, 64);
        cikis.setContentAreaFilled(false);
        cikis.setBorder(new EmptyBorder(0, 0, 0, 0));
        cikis.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        admin.setBounds(1350, 30, 64, 64);
        admin.setContentAreaFilled(false);
        admin.setBorder(new EmptyBorder(0, 0, 0, 0));
        admin.addActionListener((ActionEvent e) -> {
            admin adminPanel;
            try {
                adminPanel = new admin();
            adminPanel.setVisible(true);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        ogrenciText.setBounds(235, 175, 450, 90);
        ogrenciText.setFont(new Font("Arial", Font.BOLD, 52));
        ogrenciText.setForeground(new Color(214, 219, 223));

        ogrenciAd.setBounds(255, 300, 300, 50);
        ogrenciAd.setBackground(new Color(214, 219, 223));
        ogrenciAd.setBorder(new EmptyBorder(5, 5, 5, 5));
        ogrenciAd.setFont(new Font("Arial", Font.BOLD, 19));
        ogrenciAd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ogrenciAd.getText().equals("Kullanıcı Adı")) {
                    ogrenciAd.setForeground(Color.BLACK);
                    ogrenciAd.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ogrenciAd.getText().isEmpty()) {
                    ogrenciAd.setForeground(Color.GRAY);
                    ogrenciAd.setText("Kullanıcı Adı");
                }
            }
        });

        ogrenciSifre.setBounds(255, 390, 300, 50);
        ogrenciSifre.setBackground(new Color(214, 219, 223));
        ogrenciSifre.setBorder(new EmptyBorder(5, 5, 5, 5));
        ogrenciSifre.setFont(new Font("Arial", Font.BOLD, 19));
        ogrenciSifre.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (ogrenciSifre.getText().equals("Şifre")) {
                    ogrenciSifre.setForeground(Color.BLACK);
                    ogrenciSifre.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (ogrenciSifre.getText().isEmpty()) {
                    ogrenciSifre.setForeground(Color.GRAY);
                    ogrenciSifre.setText("Şifre");
                }
            }
        });
        ogrenciGiris.setBounds(255, 480, 300, 70);
        ogrenciGiris.setBorder(new EmptyBorder(0, 0, 0, 0));
        ogrenciGiris.setFont(new Font("Arial", Font.BOLD, 20));
            ogrenciGiris.setBackground(new Color(106, 90, 205));
        ogrenciGiris.setForeground(new Color(214, 219, 223));
        ogrenciGiris.addActionListener((ActionEvent e) -> {
            connection.baglan();
            String ogrenciQuery = "select * from ogrenci_tablo";
            try (PreparedStatement pStmt = connection.connect.prepareStatement(ogrenciQuery)) {
                ResultSet rSet = pStmt.executeQuery();
                try {
                    while (rSet.next()) {
                        int sayac = 0;
                        if ((ogrenciAd.getText().equals(rSet.getString("ogrenci_numara")) || ogrenciAd.getText().equals("ogr")) && ogrenciSifre.getText().equals("ogr")) {
                            if (ogrenciAd.getText().equals("ogr")) {
                                ogrenci.title = "Öğrenci Paneli";
                            } else {
                                ogrenci.title = rSet.getString("ogrenci_ad") + " " + rSet.getString("ogrenci_soyad");
                            }
                            ogrenciAd.setForeground(Color.GRAY);
                            ogrenciAd.setText("Kullanıcı Adı");
                            ogrenciSifre.setForeground(Color.GRAY);
                            ogrenciSifre.setText("Şifre");
                            ogrenci ogrenciPanel = new ogrenci();
                            ogrenciPanel.setVisible(true);
                        } else {

                        }
                    }
                } catch (HeadlessException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        ogrenciFrame.setBounds(150, 130, 500, 600);
        ogrenciFrame.setContentAreaFilled(false);
        ogrenciFrame.setBorder(new LineBorder(Color.PINK, 2));

        hocaText.setBounds(935, 175, 450, 90);
        hocaText.setFont(new Font("Arial", Font.BOLD, 47));
        hocaText.setForeground(new Color(214, 219, 223));

        hocaAd.setBounds(955, 300, 300, 50);
        hocaAd.setBackground(new Color(214, 219, 223));
        hocaAd.setBorder(new EmptyBorder(5, 5, 5, 5));
        hocaAd.setFont(new Font("Arial", Font.BOLD, 19));
        hocaAd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (hocaAd.getText().equals("Kullanıcı Adı")) {
                    hocaAd.setForeground(Color.BLACK);
                    hocaAd.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (hocaAd.getText().isEmpty()) {
                    hocaAd.setForeground(Color.GRAY);
                    hocaAd.setText("Kullanıcı Adı");
                }
            }
        });

        hocaSifre.setBounds(955, 390, 300, 50);
        hocaSifre.setBackground(new Color(214, 219, 223));
        hocaSifre.setBorder(new EmptyBorder(5, 5, 5, 5));
        hocaSifre.setFont(new Font("Arial", Font.BOLD, 19));
        hocaSifre.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (hocaSifre.getText().equals("Şifre")) {
                    hocaSifre.setForeground(Color.BLACK);
                    hocaSifre.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (hocaSifre.getText().isEmpty()) {
                    hocaSifre.setForeground(Color.GRAY);
                    hocaSifre.setText("Şifre");
                }
            }
        });
        hocaGiris.setBounds(955, 480, 300, 70);
        hocaGiris.setBorder(new EmptyBorder(0, 0, 0, 0));
        hocaGiris.setFont(new Font("Arial", Font.BOLD, 20));
        hocaGiris.setBackground(new Color(106, 90, 205));
        hocaGiris.setForeground(new Color(214, 219, 223));
        hocaGiris.addActionListener((ActionEvent e) -> {
            connection.baglan();
            String ogretmenQuery = "select * from ogretmen_tablo";
            try (PreparedStatement pStmt = connection.connect.prepareStatement(ogretmenQuery)) {
                ResultSet rSet = pStmt.executeQuery();
                try {
                    while (rSet.next()) {
                        int sayac = 0;
                        if ((hocaAd.getText().equals(rSet.getString("sicilno")) || hocaAd.getText().equals("ogr")) && hocaSifre.getText().equals("ogr")) {
                            if (hocaAd.getText().equals("ogr")) {
                                ogretmen.title = "Öğretmen Paneli";
                            } else {
                                ogretmen.title = rSet.getString("ogretmen_ad") + " " + rSet.getString("ogretmen_soyad");
                            }
                            hocaAd.setForeground(Color.GRAY);
                            hocaAd.setText("Kullanıcı Adı");
                            hocaSifre.setForeground(Color.GRAY);
                            hocaSifre.setText("Şifre");
                            ogretmen ogretmenPanel = new ogretmen();
                            ogretmenPanel.setVisible(true);
                        } else {

                        }
                    }
                } catch (HeadlessException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        hocaFrame.setBounds(850, 130, 500, 600);
        hocaFrame.setContentAreaFilled(false);
        hocaFrame.setBorder(new LineBorder(Color.PINK, 2));

        create.setBounds(1250, 825, 300, 40);
        create.setFont(new Font("Arial", Font.BOLD, 19));
        create.setForeground(Color.WHITE);
    }
}
