package com.olga.finance.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Human {
    private WebElement element;

    public Human(WebElement element) {
        this.element = element;
    }

    public String getId() {
        return element.findElement(By.xpath(".//td[1]")).getText();
    }

    public String getFullName() {
        return element.findElement(By.xpath(".//td[3]")).getText();
    }

    public String getEmail() {
        return element.findElement(By.xpath(".//td[4]")).getText();
    }

    public String getLevel() {
        return element.findElement(By.xpath(".//td[5]")).getText();
    }

    public String getExperience() {
        return element.findElement(By.xpath(".//td[6]")).getText();
    }

    public String getDefaultRate() {
        return element.findElement(By.xpath(".//td[6]")).getText();
    }

    public void click() {
        element.click();
    }

    public String getColumn(int i) throws IllegalArgumentException {
        if (i > 0 && i < 8) {
            return element.findElement(By.xpath(".//td[" + (i + 1) + "]")).getText();
        } else {
            throw new IllegalArgumentException("Wrong column number");
        }
    }
}
