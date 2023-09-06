package br.com.llealcruz.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateUtils {

    private DateUtils() {
        // vazio
    }

    public static String getDataValueString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return Objects.nonNull(date) ? date.format(formatter) : "";
    }
}
