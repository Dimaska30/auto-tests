package com.olga.finance;

import com.olga.finance.menus.AddProjectMenu;
import com.olga.finance.objects.Project;
import com.olga.finance.pages.MainPage;
import com.olga.finance.pages.ProjectPage;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;

import org.junit.*;
import static io.qameta.allure.Allure.parameter;
import org.junit.Test;
import org.openqa.selenium.TakesScreenshot;

import static org.junit.Assert.assertEquals;

@Epic("Страница 'Projects'")
@Feature("Добавление проекта")
public class AddProjectTest {

    static private MainPage mainPage;
    static private ProjectPage projectPage;
    static private AddProjectMenu menu;

    static private String ADMIN_LOGIN = "admin@gmail.com";
    static private String ADMIN_PASSWORD = "olga_finance";

    static private String PROJECT_NAME = "111_testproject";
    static private String CLIENT = "OOO \"Handsome\"";
    static private String COLOR = "Orange";
    static private String COLOR_CODE = "rgba(243, 109, 37, 1)";
    static private String START_DATE = "-";
    static private String END_DATE = "-";
    static private String MANAGER = "n/a";
    static private String CONTRACT_STATUS = "n/a";


    @BeforeClass
    @Step("Авторизация на сайте.")
    public static void beforeClass() {
        mainPage = RegisterOnSite.registrationOnSite(ADMIN_LOGIN, ADMIN_PASSWORD);
    }

    @Before
    @Step("Переключение на страницу 'Projects'.")
    public void before() {
        projectPage = mainPage.clickProject();
    }

    
    @DisplayName("Добавление проекта в систему")
    @Description("Проверка на создание проектов в системе.")
    @Link(name="Ссылка на странницу", url="https://olga-finance.effective.band/projects")
    @Owner(value = "Красотина Арина")
    @Test
    public void addProjectTest() {
        parameter("Login", ADMIN_LOGIN);
        parameter("Password", ADMIN_PASSWORD);

        clickToButtAddProject();
        enterData(PROJECT_NAME, CLIENT, COLOR);
        saveProject();

        checkProject(PROJECT_NAME, 
                     COLOR_CODE,
                     CLIENT,
                     START_DATE,
                     END_DATE,
                     MANAGER,
                     CONTRACT_STATUS);
    }

    @After
    @Step("Выход из системы.")
    public void after() {
        mainPage.getDriver().quit();
    }

    @AfterClass
    public static void afterClass() {
    }

    @Step("Клик по кнопкой 'Add Project'.")
    private void clickToButtAddProject() {
        menu = projectPage.addProjectButtonClick();
        screenshot();
    }

    @Step("Ввод данных")
    private void enterData(String projectName,  String client, String color) {
        enterProjectName(projectName);
        choiseClient(client);
        choiseColor(color);
        screenshot();
    }
    
    @Step("Ввод названия проекта.")
    private void enterProjectName(String projectName) {
        menu.enterProjectName(projectName);
    }

    @Step("Выбор заказчика.")
    private void choiseClient(String client) {
        menu.enterClient(client);
    }

    @Step("Выбор цвета.")
    private void choiseColor(String color) {
        menu.enterColor(color);
    }

    @Step("Сохранение проекта.")
    private void saveProject() {
        projectPage = menu.clickSave();
    }

    @Step("Проверка, что проект создан.")
    private void checkProject(String projectName,
                              String color, 
                              String client, 
                              String startDate, 
                              String endDate, 
                              String manager, 
                              String contractStatus) {

        Project firstRow = projectPage.getProjects().get(0);

        checkField("project name",projectName, firstRow.getProjectName());
        checkField("color", color, firstRow.getColor());
        checkField("client",client, firstRow.getClient());
        checkField("start date",startDate, firstRow.getStartDate());
        checkField("end date",endDate, firstRow.getEndDate());
        checkField("manager",manager, firstRow.getManager());
        checkField("contract status",contractStatus, firstRow.getContractStatus());
        screenshot();
    }

    @Step("Проверка поля {field}.")
    private void checkField(String field, String expected, String  actual) {
        assertEquals(expected, actual);
    }

    @Attachment(value = "Вложение",type = "image/png", fileExtension = ".png")
    public static byte[] screenshot() {
        return ((TakesScreenshot)mainPage.getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
    }
}
