package io.igorcossta.util;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class PrettyTimeFormatter {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.ENGLISH);
    private static final PrettyTime PRETTY_TIME = new PrettyTime();

    public String format(LocalDateTime date) {
        return PRETTY_TIME.format(date);
    }

    public String formatToMonthDayYear(LocalDateTime date) {
        return DATE_TIME_FORMATTER.format(date);
    }
}
