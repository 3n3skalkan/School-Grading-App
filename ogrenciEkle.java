package obs;

import java.util.*;

public class ogrenciEkle {

    static String secilenAd;
    static String secilenSoyad;
    static String agno;
    static String ogrNo;
    static Set<String> agnoSet = new HashSet<>();

    void adUret() {
        String[] ad = {"Fatma", "Ayşe", "Emine", "Hatice", "Zeynep", "Elif", "Meryem", "Şerife", "Sultan", "Zehra", "Ali", "Hüseyin", "Hasan", "İbrahim", "İsmail", "Osman", "Yusuf", "Murat", "Ömer", "Ramazan"};
        Random random = new Random();

        int rndIndex = random.nextInt(ad.length);
        secilenAd = ad[rndIndex];
    }

    void soyadUret() {
        String[] soyad = {"Yıldız", "Yıldırım", "Öztürk", "Aydın", "Özdemir", "Arslan", "Doğan", "Kılıç", "Aslan", "Çetin", "Kara", "Koç", "Kurt", "Özkan", "Yılmaz", "Kaya", "Demir", "Şahin"};
        Random random = new Random();

        int rndIndex = random.nextInt(soyad.length);
        secilenSoyad = soyad[rndIndex];
    }

    void agnoUret() {
        String numbers = "0123456789";
        StringBuilder word = new StringBuilder();
        Random random = new Random();

        for (int a = 0; a < 4; a++) {
            if (a == 1) {
                word.append('.');
            } else if (a == 0) {
                String firstDigit = "23";
                int rndIndex = random.nextInt(firstDigit.length());
                char chr = firstDigit.charAt(rndIndex);
                word.append(chr);
            } else {
                int rndIndex = random.nextInt(numbers.length());
                char chr = numbers.charAt(rndIndex);
                word.append(chr);
            }
        }
        agno = word.toString();
    }

    void harfNotu() {
        double agnoDouble = Double.parseDouble(agno);
        String harfNotu;
        if (agnoDouble < 2.50 && agnoDouble >= 2.00) {
            harfNotu = "CC";
        }
        if (agnoDouble < 3.00 && agnoDouble >= 2.50) {
            harfNotu = "BC";
        }
        if (agnoDouble < 3.50 && agnoDouble >= 3.00) {
            harfNotu = "BB";
        }
        if (agnoDouble < 3.80 && agnoDouble >= 3.50) {
            harfNotu = "AB";
        }
        if (agnoDouble <= 4.00 && agnoDouble >= 3.80) {
            harfNotu = "AA";
        }
    }

    void ogrNo() {
        String ogrenciNo;
        String numbers = "0123456789";
        StringBuilder word = new StringBuilder("210202");
        Random random = new Random();
        for (int a = 0; a < 3; a++) {
            int rndIndex = random.nextInt(numbers.length());
            char chr = numbers.charAt(rndIndex);
            word.append(chr);
        }
        ogrenciNo = word.toString();
        ogrNo = ogrenciNo;
        if (!agnoSet.contains(ogrNo)) {
            agnoSet.add(ogrNo);
        } else {
            ogrNo();
        }
    }
}
