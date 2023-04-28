package com.olga.finance;

import com.olga.finance.menus.ClientInfoMenu;
import com.olga.finance.objects.Human;
import com.olga.finance.pages.MainPage;
import com.olga.finance.pages.TeamPage;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.model.Status;
import org.junit.*;
import org.openqa.selenium.NoSuchContextException;

import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;
import static org.junit.Assert.assertEquals;

@Epic("Страница 'Projects'")
@Feature("Проверка дублирования проекта")

public class ChangeTeamTest {
    static private String ADMIN_LOGIN = "admin@gmail.com";
    static private String ADMIN_PASSWORD = "olga_finance";

    static private String EXPERTIZE_FIELD_VALUE = "QA";

    static private String EXPERTIZE_FILED_NAME = "Expertize";

    static private String expertizeFieldValueOld ="";

    static private MainPage mainPage;

    static private TeamPage teamPage;

    static private ClientInfoMenu menu;
    @BeforeClass
    @Step("Авторизация на сайте.")
    public static void beforeClass() {
        mainPage = RegisterOnSite.registrationOnSite(ADMIN_LOGIN, ADMIN_PASSWORD);
    }

    @Before
    @Step("Переключение на страницу 'Team'.")
    public void before() {
        teamPage = mainPage.clickTeam();
        if (teamPage.getHumans().size() == 0){
            step("Проверили, что таблица Team не пустая.",  Status.FAILED);
            throw new NoSuchContextException("В таблице нет проектов!");
        }
        step("Проверили, что таблица Team не пустая.");
    }
    @DisplayName("Изменения существующего клиента")
    @Description("Проверка изменения существующего Клиента.")
    @Link(name="Ссылка на странницу", url="https://olga-finance.effective.band/team")
    @Owner(value = "Плескунов Дмитрий")
    @Test
    public void ChangeHumanTest() {
        clickFirstHuman();

        clickChangeButton();

        changeExpertiseField(EXPERTIZE_FIELD_VALUE);

        saveChanged();

        checkChange(EXPERTIZE_FIELD_VALUE);
    }

    @After
    @Step("Выход из системы.")
    public void after() {
        rechange();
        mainPage.getDriver().quit();
    }

    @AfterClass
    public static void afterClass() {
    }

    @Step("Нажатие на первую запись.")
    private static void clickFirstHuman(){
        expertizeFieldValueOld = teamPage.getColumns(EXPERTIZE_FILED_NAME).get(0);
        menu = teamPage.clickHuman(0);
    }

    @Step("Нажатие кнопки редактирования.")
    private static void clickChangeButton(){
        menu.clickEdit();
    }

    @Step("Изменение поля Expertise на значение \"QA\"")
    private static void changeExpertiseField(String value){
        menu.selectExpertise(value);
    }

    @Step("Сохранение изменений")
    private static void saveChanged(){
        teamPage = menu.clickSave();
    }

    @Step("Проверка, что данные изменены.")
    private static void checkChange(String expertise){
        Human any_man = teamPage.getHumans().get(0);
        checkField("Expertise", expertise, any_man.getExperience());
    }

    @Step("Проверка поля {field}.")
    private static void checkField(String field, String expected, String actual) {
        assertEquals(expected, actual);
    }

    @Step("Возвращаем изменения.")
    private static void rechange(){
        menu = teamPage.clickHuman(0);
        menu.clickEdit();
        menu.selectExpertise(expertizeFieldValueOld);
        teamPage = menu.clickSave();
    }
}
