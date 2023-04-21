package com.olga.finance;

import com.olga.finance.menus.AddProjectMenu;
import com.olga.finance.objects.Project;
import com.olga.finance.pages.MainPage;
import com.olga.finance.pages.ProjectPage;
import org.junit.*;
import org.junit.Assert;

import static io.qameta.allure.Allure.step;
import static org.junit.Assert.assertEquals;


public class AddProjectTest {

    static private MainPage mainPage;
    static private ProjectPage projectPage;
    @BeforeClass
    public static void beforeClass() {
        mainPage = RegisterOnSite.registrationOnSite("admin@gmail.com","olga_finance",true);
        step("Мы зашли на сайт! :)");
    }

    @Before
    public void before() {
        projectPage = mainPage.clickProject();
        step("Мы зашли на страницу проекта!)");
    }

    @Test
    public void addProjectTest() {
        AddProjectMenu menu = projectPage.addProjectButtonClick();
        menu.enterProjectName("111_testproject");
        menu.enterClient("OOO \"Handsome\"");
        menu.enterColor("Orange");
        projectPage = menu.clickSave();

        Project firstRow = projectPage.getProjects().get(0);
        assertEquals("111_testproject", firstRow.getProjectName());
        assertEquals("OOO \"Handsome\"", firstRow.getClient());
        assertEquals("-", firstRow.getStartDate());
        assertEquals("-", firstRow.getEndDate());
        assertEquals("n/a", firstRow.getManager());
        assertEquals("n/a", firstRow.getContractStatus());
    }

    @After
    public void after() {
        step("Step inside after");
    }

    @AfterClass
    public static void afterClass() {
        step("Step inside afterClass");
    }
}
