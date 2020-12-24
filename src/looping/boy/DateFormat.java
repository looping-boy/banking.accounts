package looping.boy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormat {
        // METHODE QUI CONVERTIT DES FORMATS DE DATES
    public static String DateFormat(LocalDateTime temps) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = temps.format(formatter);
        return formatDateTime;
    }
}