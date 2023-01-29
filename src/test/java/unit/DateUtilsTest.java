package unit;

import app.foot.utils.DateUtils;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateUtilsTest {
    DateUtils dateUtils = new DateUtils();

    @Test
    void hello_test_ok() {
        assertEquals(1, 1);
    }

    @Test
    void parse_date_ok() {
        assertEquals(LocalDate.now().toString(), dateUtils.parseDate(Instant.now()));
    }

    @Test
    void parse_date_ko() {
        assertThrows(NullPointerException.class, () -> dateUtils.parseDate(null));
    }
}
