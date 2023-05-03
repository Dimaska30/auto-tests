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

@Epic("Страница 'Team'")
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
    public void before() {
        teamPage = mainPage.clickTeam();
        teamPage.hideLeftPannel();
        step("Переключение на страницу 'Team'.");
        if (teamPage.getHumans().size() == 0) {
            step("Проверили, что таблица Team не пустая.", Status.FAILED);
            throw new NoSuchContextException("В таблице нет проектов!");
        }
        if (checkColumnBySort(DEFAULT_RATE_FIELD_NAME, true)) {
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
        parameter("Login", ADMIN_LOGIN);
        parameter("Password", ADMIN_PASSWORD);
        parameter("Column", DEFAULT_RATE_FIELD_NAME);

        sortedColumn(DEFAULT_RATE_FIELD_NAME);
        screenshot("Результат");
        junit.framework.Assert.assertTrue(checkColumnBySort(DEFAULT_RATE_FIELD_NAME, false));
    }

    @After
    @Step("Выход из системы.")
    public void after() {
        mainPage.getDriver().quit();
    }

    @AfterClass
    public static void afterClass() {
    }

    public static void sortedColumn(String n) {
        teamPage.clickFilter(n);
        step("Сортируем столбец "+n);
    }

    public static boolean checkColumnBySort(String n, boolean isReverse) {
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

        if(isReverse)
            step("Cтолбeц " + n + " неотсортирован", result? Status.FAILED : Status.PASSED);
        else 
            step("Cтолбeц " + n + " отсортирован", result? Status.PASSED  : Status.FAILED);
        
        return result;
    }

    private static boolean checkTwoNum(int a, int b) {
        return a <= b;
    }

    @Attachment(value = "{name}", type = "image/png", fileExtension = ".png")
    public static byte[] screenshot(String name) {
        return ((TakesScreenshot) mainPage.getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
    }
}
