package com.zebrunner.carina.demo;

import com.zebrunner.agent.core.annotation.TestLabel;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.core.registrar.tag.Priority;
import com.zebrunner.carina.core.registrar.tag.TestPriority;
import com.zebrunner.carina.demo.gui.pages.common.BrandModelsPageBase;
import com.zebrunner.carina.demo.gui.pages.common.HomePageBase;
import com.zebrunner.carina.demo.gui.pages.common.ModelInfoPageBase;
import com.zebrunner.carina.demo.gui.pages.desktop.HomePage;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class WebPracticalTest implements IAbstractTest {

    @Test
    @MethodOwner(owner = "qpspractical")
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testSuccessLogin() throws InterruptedException {
        // Open GSM Arena home page and verify page is opened
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        // Get email field and enter valid email.
        HomePage homePage1 = new HomePage(getDriver());
        homePage1.getLogInButton().click();
        homePage1.getEmailField().click();
        homePage1.getEmailField().type("s9rowa@mail.ru");
        // Get password field and enter correct password.
        homePage1.getPasswordField().click();
        homePage1.getPasswordField().type("changeme");
        // Submit valid credentials
        homePage1.getSubmitLoginButton().click();

        Thread.sleep(3000);
    }

    @Test
    @MethodOwner(owner = "qpspractical")
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testFailedLoginWithEmail() {
        // Open GSM Arena home page and verify page is opened
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        // Get email field and enter invalid email.
        HomePage homePage1 = new HomePage(getDriver());
        homePage1.getLogInButton().click();
        homePage1.getEmailField().click();
        homePage1.getEmailField().type("s8rowa@mail.ru");
        // Get password field and enter correct password.
        homePage1.getPasswordField().click();
        homePage1.getPasswordField().type("changeme");
        homePage1.getSubmitLoginButton().click();
        // Get text of the actual result
        String actual = homePage1.getUserNotFoundText().getText();
        String expected = "Reason: User record not found.";
        // Compare the actual result from the expected result
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual, expected);
    }

    @Test
    @MethodOwner(owner = "qpspractical")
    @TestPriority(Priority.P3)
    @TestLabel(name = "feature", value = { "web", "regression" })
    public void testFailedLoginWithPassword() {
        // Open GSM Arena home page and verify page is opened
        HomePageBase homePage = initPage(getDriver(), HomePageBase.class);
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        // Get email field and enter valid email.
        HomePage homePage1 = new HomePage(getDriver());
        homePage1.getLogInButton().click();
        homePage1.getEmailField().click();
        homePage1.getEmailField().type("s9rowa@mail.ru");
        // Get password field and enter invalid password.
        homePage1.getPasswordField().click();
        homePage1.getPasswordField().type("dontchangeme");
        homePage1.getSubmitLoginButton().click();
        // Get text of the actual result
        String actual = homePage1.getWrongPasswordText().getText();
        String expected = "Reason: Wrong password.";
        // Compare the actual result from the expected result
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual, expected);
    }
}
