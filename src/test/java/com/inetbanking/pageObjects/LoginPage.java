package com.inetbanking.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver ldriver;

    public LoginPage(WebDriver driver){
        this.ldriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name="uid")
    @CacheLookup
    private WebElement txtUserName;

    @FindBy(id ="save")
    @CacheLookup
    private WebElement btnSave;

    @FindBy(name="password")
    @CacheLookup
    private WebElement txtPassword;

    @FindBy(name="btnLogin")
    @CacheLookup
    private WebElement btnLogin;

    @FindBy(id="proceed-link")
    @CacheLookup
    private WebElement secu;

    @FindBy(id="details-button")
    @CacheLookup
    private WebElement btnAdvanced;

    @FindBy(css="a[href=\"Logout.php\"]")
    @CacheLookup
    private WebElement btnLogout;

    @FindBy(how = How.XPATH, using ="//button[@class='fc-button fc-cta-consent fc-primary-button']")
    @CacheLookup
    WebElement btnConsent;

    public void setUserName(String uname){
        this.txtUserName.sendKeys(uname);
    }
    public void setPassword(String pwd){
        this.txtPassword.sendKeys(pwd);
    }
    public void clickSubmit(){
        this.btnLogin.click();
    }
    public void clickSave() { this.btnSave.click(); }
    public void securityAgree() { this.secu.click(); }
    public void advancedButtonClicked() { this.btnAdvanced.click(); }
    public void logout() { this.btnLogout.click(); }
    public void clickConsent() { this.btnConsent.click(); }
}
