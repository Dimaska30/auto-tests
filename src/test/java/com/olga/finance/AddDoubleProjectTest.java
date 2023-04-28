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

import io.qameta.allure.model.Status;
import org.junit.*;
import static io.qameta.allure.Allure.parameter;
import org.junit.Test;
import org.openqa.selenium.NoSuchContextException;
import org.openqa.selenium.TakesScreenshot;

import static io.qameta.allure.Allure.step;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

@Epic("Страница 'Projects'")
@Feature("Проверка дублирования проекта")
public class AddDoubleProjectTest {

    static private MainPage mainPage;
    static private ProjectPage projectPage;
    static private AddProjectMenu menu;

   static private String ADMIN_LOGIN = "admin@gmail.com";
    static private String ADMIN_PASSWORD = "olga_finance";

    static private String PROJECT_NAME = "";
    static private String CLIENT = "OOO \"Handsome\"";
    static private String COLOR = "Orange";

    static private int COUNT_PROJECT = 0;

    @BeforeClass
    @Step("Авторизация на сайте.")
    public static void beforeClass() {
        mainPage = RegisterOnSite.registrationOnSite(ADMIN_LOGIN, ADMIN_PASSWORD);
    }

    @Before
    @Step("Переключение на страницу 'Projects'.")
    public void before() {
        projectPage = mainPage.clickProject();
        if (projectPage.getProjects().size() == 0){
            step("Проверили, что таблица Projects не пустая.",  Status.FAILED);
            throw new NoSuchContextException("В таблице нет проектов!");
        }
        step("Проверили, что таблица Projects не пустая.");
    }

    
    @DisplayName("Дублирование проекта в систему")
    @Description("Проверка на не создание дублей проектов в системе.")
    @Link(name="Ссылка на странницу", url="https://olga-finance.effective.band/projects")
    @Owner(value = "Красотина Арина")
    @Test
    public void addProjectTest() {
        parameter("Login", ADMIN_LOGIN);
        parameter("Password", ADMIN_PASSWORD);

        rememberFirstProject();

        clickToButtAddProject();
        enterData(PROJECT_NAME, CLIENT, COLOR);
        saveProject();

        checkProject(PROJECT_NAME, COUNT_PROJECT);
    }

    @After
    @Step("Выход из системы.")
    public void after() {
        mainPage.getDriver().quit();
    }

    @AfterClass
    public static void afterClass() {
    }

    @Step("Запоминание первого проекта")
    private void rememberFirstProject() {
        PROJECT_NAME = projectPage.getProjects().get(0).getProjectName();
        COUNT_PROJECT = projectPage.getAllProject_count();
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

    @Step("Проверка, что проект единственный.")
    private void checkProject(String projectName, int n) {

        Project firstRow = projectPage.getProjects().get(0);
        Project secondRow = projectPage.getProjects().get(1);

        checkField("project name",1,projectName, firstRow.getProjectName());
        not_checkField("project name",2,projectName, secondRow.getProjectName());
        checkField("кол-ва проектов",n, projectPage.getAllProject_count());

        screenshot();
    }

    @Step("Проверка {field}.")
    private void checkField(String field, int expected, int  actual) {
        assertEquals(expected, actual);
    }
    @Step("Проверка поля {field} в строке {n} на соотвествие.")
    private void checkField(String field,int n, String expected, String  actual) {
        assertEquals(expected, actual);
    }

    @Step("Проверка поля {field} в строке {n} на несоответсвие.")
    private void not_checkField(String field,int n, String expected, String  actual) {
        assertNotSame(expected, actual);
    }

    @Attachment(value = "Вложение",type = "image/png", fileExtension = ".png")
    public static byte[] screenshot() {
        return ((TakesScreenshot)mainPage.getDriver()).getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
    }
}