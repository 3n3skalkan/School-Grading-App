package obs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class kayit extends JFrame {

    static int flag;

    JLabel panelText;

    JButton panelButton;
    JButton frame;
    JTextField noField;
    JTextField adField;
    JTextField soyadField;
    JTextField agnoField;

    JPanel panel;

    ImageIcon ico;

    kayit() {
        ico = new ImageIcon("img\\setting.png");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(ico.getImage());

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon("img\\HD-wallpaper-blue-faded-colors-abstract.jpg").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new BorderLayout());

        panelText = new JLabel();
        panelButton = new JButton();
        noField = new JTextField();
        adField = new JTextField();
        soyadField = new JTextField();
        agnoField = new JTextField();

        frame = new JButton();
        frame.setContentAreaFilled(false);
        frame.setBorderPainted(false);
        frame.setBounds(0, 0, 400, 300);

        add(panelText);
        add(panelButton);
        add(noField);
        add(adField);
        add(soyadField);
        add(agnoField);
        add(frame);
        add(panel);

        if (flag == 0) {
            setTitle("Kayıt Ekleme Paneli");
            panelText.setText("Eklenecek Kişinin Bilgilerini Giriniz: ");
            panelText.setFont(new Font("Arial", Font.BOLD, 17));
            noField.setBounds(75, 75, 100, 50);
            panelButton.setBounds(125, 205, 150, 50);
            adField.setVisible(true);
            soyadField.setVisible(true);
            agnoField.setVisible(true);
        } else if (flag == 1) {
            setTitle("Kayıt Silme Paneli");
            panelText.setText("Silinecek Kişinin ID Numarasını Giriniz: ");
            panelText.setFont(new Font("Arial", Font.BOLD, 16));
            noField.setBounds(125, 100, 150, 50);
            panelButton.setBounds(125, 175, 150, 50);
            adField.setVisible(false);
            soyadField.setVisible(false);
            agnoField.setVisible(false);
        } else {
            setTitle("Kayıt Güncelleme Paneli");
            panelText.setText("Güncellenecek Kişinin ID'sini Giriniz :");
            panelText.setFont(new Font("Arial", Font.BOLD, 16));
            noField.setBounds(75, 75, 100, 50);
            panelButton.setBounds(125, 205, 150, 50);
            adField.setVisible(true);
            soyadField.setVisible(true);
            agnoField.setVisible(true);
        }
        panelText.setForeground(new Color(214, 219, 223));
        panelText.setBounds(50, 15, 300, 50);
        noField.setFont(new Font("Arial", Font.BOLD, 13));
        noField.setText("ID Giriniz");
        noField.setForeground(Color.GRAY);
        noField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (noField.getText().equals("ID Giriniz")) {
                    noField.setForeground(Color.BLACK);
                    noField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (noField.getText().isEmpty()) {
                    noField.setForeground(Color.GRAY);
                    noField.setText("ID Giriniz");
                }
            }
        });
        noField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = noField.getText();
                if (!text.isEmpty()) {
                    char firstChar = text.charAt(0);
                    if (firstChar == '1') {
                        agnoField.setText("Ders ID Giriniz");
                    } else {
                        agnoField.setText("Agno Giriniz");
                    }
                } else {
                }
            }
        });
        noField.setBorder(new EmptyBorder(5, 5, 5, 5));
        adField.setFont(new Font("Arial", Font.BOLD, 13));
        adField.setText("Adı Giriniz");
        adField.setForeground(Color.GRAY);
        adField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (adField.getText().equals("Adı Giriniz")) {
                    adField.setForeground(Color.BLACK);
                    adField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (adField.getText().isEmpty()) {
                    adField.setForeground(Color.GRAY);
                    adField.setText("Adı Giriniz");
                }
            }
        });
        adField.setBounds(200, 75, 100, 50);
        adField.setBorder(new EmptyBorder(5, 5, 5, 5));
        soyadField.setFont(new Font("Arial", Font.BOLD, 13));
        soyadField.setText("Soyadı Giriniz");
        soyadField.setForeground(Color.GRAY);
        soyadField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (soyadField.getText().equals("Soyadı Giriniz")) {
                    soyadField.setForeground(Color.BLACK);
                    soyadField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (soyadField.getText().isEmpty()) {
                    soyadField.setForeground(Color.GRAY);
                    soyadField.setText("Soyadı Giriniz");
                }
            }
        });
        soyadField.setBounds(200, 135, 100, 50);
        soyadField.setBorder(new EmptyBorder(5, 5, 5, 5));
        agnoField.setFont(new Font("Arial", Font.BOLD, 13));
        agnoField.setText("Agno Giriniz");
        agnoField.setForeground(Color.GRAY);
        agnoField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (agnoField.getText().equals("Agno Giriniz") || agnoField.getText().equals("Ders ID Giriniz")) {
                    agnoField.setForeground(Color.BLACK);
                    agnoField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (agnoField.getText().isEmpty()) {
                    agnoField.setForeground(Color.GRAY);
                    if (noField.getText().charAt(0) == '2') {
                        agnoField.setText("Agno Giriniz");
                    } else {
                        agnoField.setText("Ders ID Giriniz");
                    }
                }
            }
        });
        agnoField.setBounds(75, 135, 100, 50);
        agnoField.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelButton.setText("TAMAMLA");
        panelButton.setFont(new Font("Arial", Font.BOLD, 20));
        panelButton.setBackground(new Color(106, 90, 205));
        panelButton.setForeground(new Color(214, 219, 223));
        panelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    panelButtonMouseClicked(evt);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    private void panelButtonMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {
        connection.baglan();
        connection.ekleCikar(noField.getText().toString(), adField.getText().toString(), soyadField.getText().toString(), agnoField.getText().toString());
    }

}
