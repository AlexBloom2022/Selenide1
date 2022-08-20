package netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {

    LocalDate localDate = LocalDate.now().plusDays(3);
    DateTimeFormatter data = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    String strData = localDate.format(data);

    @Test
    public void shouldTestMap() {
        Configuration.holdBrowserOpen = true;
        open("http://0.0.0.0:7777");
        $x("//input[@placeholder='Город']").val("Новосибирск");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").val(strData);
        $x("//input[@name='name']").val("Ал Альф Алберто");
        $x("//input[@name='phone']").val("+79000000000");
        $("[data-test-id=agreement] .checkbox__box").click();
        $(withText("Забронировать")).click();
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на " + (strData)), Duration.ofSeconds(15));

    }

}