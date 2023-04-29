package com.olga.finance;

import com.olga.finance.pages.MainPage;
import com.olga.finance.pages.TeamPage;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.model.Status;
import org.junit.*;
import org.openqa.selenium.NoSuchContextException;
import org.openqa.selenium.TakesScreenshot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.qameta.allure.Allure.parameter;

@Epic("Страница 'Projects'")
@Feature("Проверка работоспособности фильтра")

public class TeamFilterTest {
    static private String ADMIN_LOGIN = "admin@gmail.com";
    static private String ADMIN_PASSWORD = "olga_finance";

    static private String EXPERTIZE_FIELD_NAME = "Expertize";

    static private String DEFAULT_RATE_FIELD_NAME = "Default Rate";

    static private MainPage mainPage;

    static private TeamPage teamPage;

    @BeforeClass
    @Step("Авторизация на сайте.")
    public static void beforeClass() {
        mainPage = RegisterOnSite.registrationOnSite(ADMIN_LOGIN, ADMIN_PASSWORD);
    }

    @Before
    @Step("Переключение на страницу 'Team'.")
    public void before() {
        teamPage = mainPage.clickTeam();
        teamPage.hideLeftPannel();
        if (teamPage.getHumans().size() == 0) {
            step("Проверили, что таблица Team не пустая.", Status.FAILED);
            throw new NoSuchContextException("В таблице нет проектов!");
        }
        if (checkColumnBySort(DEFAULT_RATE_FIELD_NAME)) {
            sortedColumn(EXPERTIZE_FIELD_NAME);
        }
        step("Проверили, что таблица Team не пустая.");
    }

    @DisplayName("Тестирование фильтра Default Rate")
    @Description("Проверка фильтра Default Rate.")
    @Link(name = "Ссылка на странницу", url = "https://olga-finance.effective.band/team")
    @Owner(value = "Плескунов Дмитрий")
    @Test
    public void RateFilterTest() {
        parameter("Column", DEFAULT_RATE_FIELD_NAME);

        screenshot("Начало");
        sortedColumn(DEFAULT_RATE_FIELD_NAME);
        screenshot("Конец");
        junit.framework.Assert.assertTrue(checkColumnBySort(DEFAULT_RATE_FIELD_NAME));
    }

    @After
    @Step("Выход из системы.")
    public void after() {
        mainPage.getDriver().quit();
    }

    @AfterClass
    public static void afterClass() {
    }

    @Step("Сортируем столбец {n}.")
    public static void sortedColumn(String n) {
        teamPage.clickFilter(n);
    }

    @Step("Проверка отсортированности столбца {n}.")
    public static boolean checkColumnBySort(String n) {
        List<String> column = teamPage.getWholeColumn(n);
        List<Integer> column_num = new ArrayList<>();
        column.forEach(x -> column_num.add(Integer.parseInt(x)));

        Iterator<Integer> iter = column_num.iterator();
        Integer current, previous = iter.next();
        boolean result = true;
        while (iter.hasNext()) {
            current = iter.next();
            if (!(checkTwoNum(previous, current))) {
                result = false;
                break;
            }
            previous = current;
        }
        return result;
    }

    @Step("Проверка двух чисел {a} и {b} на неубывание.")
    private static boolean checkTwoNum(int a, int b) {
        return a <= b;
    }

    @Attachment(value = "{name}", type = "image/png", fileExtension = ".png")
    public static byte[] screenshot(String name) {
        return ((TakesScreenshot) mainPage.getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
    }
}
