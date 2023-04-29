package com.olga.finance.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.olga.finance.objects.Project;
import com.olga.finance.menus.AddProjectMenu;

import java.util.ArrayList;
import java.util.List;

public class ProjectPage {

    private WebDriver driver;

    public ProjectPage(WebDriver driver) {
        this.driver = driver;
    }

    private By tableRows = By.xpath("//table/tbody/tr");
    private By addProjectButton = By.xpath("/html/body/div/div/div[2]/div[2]/div[1]/div[1]/div[2]/div/button");
    private By hideLeftPannelButton = By.xpath("//button[contains(@class, 'LoggedLayout_openButton__lojt7')]");

    private By countLabel = By.className("MuiTablePagination-displayedRows");

    public List<Project> getProjects() {
        List<WebElement> rows = driver.findElements(tableRows);
        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            projects.add(new Project(rows.get(i)));
        }
        return projects;
    }

    public List<String> getcolumns(int i) {
        List<String> column = new ArrayList<>();
        List<Project> elements = getProjects();
        elements.forEach(el -> column.add(el.getcolumn(i)));
        return column;
    }

    public AddProjectMenu addProjectButtonClick() {
        driver.findElement(addProjectButton).click();
        return new AddProjectMenu(driver);
    }

    public int getAllProject_count() {
        return Integer.parseInt(driver.findElement(countLabel).getText().split(" of ")[1]);
    }

    public void hideLeftPannel() {
        driver.findElement(hideLeftPannelButton).click();
    }
}
