package com.testing.stepDefs;

import com.testing.pages.githubPage;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.*;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

public class githubStepsDef {
//    @Managed(driver = "chrome")
//    WebDriver driver;

    @Steps
    githubPage page;

    @Given("launch github page")
    public void launchGithub() {
        page.openApp();
    }

    @When("search github <user>")
    public void searchUser(@Named("user") String user) {
        page.searchAndClickUser(user);
    }

    @Then("validate <username>")
    public void validateUser(@Named("username") String username) {
        String name = page.validateUser(username);
        Assertions.assertThat(name).isEqualTo(username);
    }
}
