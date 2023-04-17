package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TeamPage {
    private WebDriver driver;
    public TeamPage(WebDriver driver){
        this.driver=driver;
    }

    private By filters = By.cssSelector("MuiButtonBase-root MuiTableSortLabel-root css-dx096b");
    private By rows=By.cssSelector("MuiTableRow-root MuiTableRow-hover TableTeam_tableRow__rcUSt TableTeam_tableRowOnClick__OWfRu css-1wonh2x");

    private By addMemberButton
    public int clickFilter(String name) {
        return  -1;
    }
    public List<WebElement> getColumn(int index) {
        return new ArrayList<WebElement>();
    }


    public void clickAddMemberButton(){

    }
}
