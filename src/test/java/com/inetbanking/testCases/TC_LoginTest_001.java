package com.inetbanking.testCases;

import com.inetbanking.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;



public class TC_LoginTest_001 extends BaseClass{


    @Test
    public void loginTest() throws IOException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName(username);
        logger.info("Username: '" + username + "' entered");
        loginPage.setPassword(password);
        logger.info("Password: '" + password + "' entered");
        loginPage.clickSubmit();
        logger.info("Submitted");


        try{
            driver.getTitle();
        }catch(Exception e){
            logger.info("LoginTest FAILED");
            captureScreen(driver, "loginTest");
            Assert.assertTrue(false);
        }

        if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
        {
            logger.info("LoginTest PASSED");
            Assert.assertTrue(true);

        }
        else
        {
            logger.info("LoginTest FAILED");
            captureScreen(driver, "loginTest");
            Assert.assertTrue(false);

        }

    }
}
