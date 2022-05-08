package com.inetbanking.testCases;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;


public class BaseClass {
    private ReadConfig rc = new ReadConfig();
    private String baseURL=rc.getURL();
    public String username= rc.getUsername();
    public String password= rc.getPassword();
    public WebDriver driver;
    public static Logger logger;

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser)
    {
        logger= Logger.getLogger("ebanking");
        PropertyConfigurator.configure("Log4j.properties");

        if(browser.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", rc.getChromepath());
            driver = new ChromeDriver();
            driver.get(baseURL);
            logger.info("URL is opened");
            driver.manage().window().maximize();
            LoginPage loginPage = new LoginPage(driver);


            try
            {
                 loginPage.advancedButtonClicked();
                 logger.info("Advanced button clicked");
                 loginPage.securityAgree();
                 logger.info("Security Accepted");
                 Thread.sleep(3000);
            } catch (Exception e){

            }
            try
            {
                driver.switchTo().frame("gdpr-consent-notice");
                loginPage.clickSave();
                logger.info("Privacy accepted");
            }catch (Exception e){

            }

            try
            {
                loginPage.clickConsent();
            }catch (Exception e) {

            }

        }
        else if(browser.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", rc.getFirefoxpath());
            driver = new FirefoxDriver();
            driver.get(baseURL);
            logger.info("URL is opened");
            driver.manage().window().maximize();
            LoginPage loginPage = new LoginPage(driver);
            try {
                Thread.sleep(3000);
                driver.switchTo().frame("gdpr-consent-notice");
                loginPage.clickSave();
                logger.info("Privacy accepted");
                driver.switchTo().defaultContent();
            } catch (Exception e)
            {
            }


        }


    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
        FileUtils.copyFile(source, target);
        System.out.println("Screenshot taken");
    }
}
