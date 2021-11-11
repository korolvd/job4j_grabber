package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SqlRuDateTimeParse implements DateTimeParser {

    private static final Map<String, String> MONTHS = Map.ofEntries(
            Map.entry("янв", "01"),
            Map.entry("фев", "02"),
            Map.entry("мар", "03"),
            Map.entry("апр", "04"),
            Map.entry("май", "05"),
            Map.entry("июн", "06"),
            Map.entry("июл", "07"),
            Map.entry("авг", "08"),
            Map.entry("сен", "09"),
            Map.entry("окт", "10"),
            Map.entry("ноя", "11"),
            Map.entry("дек", "12")
            );

    @Override
    public LocalDateTime parse(String parse) {
        StringBuilder str = new StringBuilder(parse);
        String newDate;
        int[] range = new int[2];
        if (parse.contains("сегодня")) {
            newDate = DateTimeFormatter.ofPattern("d MM yy")
                    .format(LocalDateTime.now());
            range[1] = 7;
        } else if (parse.contains("вчера")) {
            newDate = DateTimeFormatter.ofPattern("d MM yy")
                    .format(LocalDateTime.now().minusDays(1));
            range[1] = 5;
        } else {
            range[0] = str.indexOf(" ") + 1;
            range[1] = str.indexOf(" ") + 4;
            newDate = MONTHS.get(str.substring(range[0], range[1]));
        }
        str.replace(range[0], range[1], newDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MM yy, HH:mm");
        return LocalDateTime.parse(str.toString(), formatter);
    }
}
