package ru.rushydro.vniig.ias.util;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;

public class TimeUtil {

    public static LocalDateTime convertToLocalDateTime(XMLGregorianCalendar calendar) {
        return calendar.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
    }
}
