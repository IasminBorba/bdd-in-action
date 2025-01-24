package com.testing.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

//@DefaultUrl("https://gh-users-search.netlify.app/")
public class githubPage extends PageObject {
//    @FindBy(xpath = "//input[@data-testid='search-bar'")
//    WebElement searchBar;

    @Step
    public void openApp() {
//        open();
    }

    @Step
    public void searchAndClickUser(String user) {
//        searchBar.sendKeys(user);
//        find(By.xpath("//button[@type='submit'")).click();
    }

    @Step
    public String validateUser(String username) {
//        WebElement username=$("//div[contains(@class, 'so-jSUZER')]/parent::section/following-sibling::section[2]/descendant::h4");
//        return username.getText();
        return username;
    }
}
