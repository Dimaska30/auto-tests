package com.olga.finance.pages;

import com.olga.finance.menus.ClientInfoMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.olga.finance.objects.Human;

import java.util.ArrayList;
import java.util.List;

public class TeamPage {
    private WebDriver driver;

    public TeamPage(WebDriver driver) {
        this.driver = driver;
    }

    private By filters = By.xpath("//table/thead/tr/th");
    private By tableRows = By.xpath("//table/tbody/tr");

    public boolean clickFilter(String name) {
        List<WebElement> columns_name = driver.findElements(filters);
        for (int i = 0; i < columns_name.size(); i++) {
            if (columns_name.get(i).getText().equals(name)) {
                columns_name.get(i).click();
                return true;
            }
        }
        return false;
    }

    public List<Human> getHumans() {
        List<WebElement> rows = driver.findElements(tableRows);
        List<Human> people = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            people.add(new Human(rows.get(i)));
        }
        return people;
    }

    public ClientInfoMenu clickHuman(int n){
        getHumans().get(n).click();
        return new ClientInfoMenu(driver);
    }

    public List<String> getColumns(String name) {
        List<WebElement> elements = driver.findElements(filters);
        int index = -1;
        for (int i = 0; i < elements.size(); i++) {
            String temp = elements.get(i).getText();
            if (elements.get(i).getText().equals(name)) {
                index = i;
                break;
            }
        }
        return getcolumns(index);
    }

    private List<String> getcolumns(int i) {
        List<String> column = new ArrayList<>();
        List<Human> elements = getHumans();
        for (Human el : elements) {
            column.add(el.getColumn(i));
        }
        return column;

    }
}
