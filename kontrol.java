package obs;

import java.time.LocalDate;

public class kontrol {

    static LocalDate zaman;
    static LocalDate mevcutTarih = LocalDate.now();
    static int a = 0;

    static void tarihKontrol() {
        if (zaman.isBefore(mevcutTarih)) {
            a = 1;
        } else {
            a = 0;
        }
    }
}
