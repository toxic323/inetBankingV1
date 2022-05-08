package com.inetbanking.testCases;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class TC_AddCustomerTest_003 extends BaseClass{

    @Test
    public void TC_AddCustomerTest_003() throws InterruptedException, IOException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUserName(username);
        logger.info("Username: '" + username + "' entered");
        loginPage.setPassword(password);
        logger.info("Password: '" + password + "' entered");
        loginPage.clickSubmit();
        logger.info("Submitted");


        AddCustomerPage addCustomerPage = new AddCustomerPage(driver);


        addCustomerPage.clickAddNewCustomer();

        logger.info("providing customer details....");


        addCustomerPage.custName("Pavan");
        addCustomerPage.custgender("male");
        addCustomerPage.custdob("10","15","1985");
        Thread.sleep(4000);
        addCustomerPage.custaddress("INDIA");
        addCustomerPage.custcity("HYD");
        addCustomerPage.custstate("AP");
        addCustomerPage.custpinno("5000074");
        addCustomerPage.custtelephoneno("987890091");

        String email=randomString()+"@gmail.com";
        addCustomerPage.custemailid(email);
        addCustomerPage.custpassword("abcdef");
        addCustomerPage.custsubmit();

        Thread.sleep(1000);

        logger.info("validation started....");

        if(isAlertPresent()){
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            logger.info("test case failed....");
            captureScreen(driver,"addNewCustomer");
            Assert.assertTrue(false);
        }

        boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");

        if(res==true)
        {
            Assert.assertTrue(true);
            captureScreen(driver,"lastcorrectlycreatedCUSTOMER");
            logger.info("test case passed....");

        }
        else
        {
            logger.info("test case failed....");
            captureScreen(driver,"addNewCustomer");
            Assert.assertTrue(false);
        }

    }

    private boolean isAlertPresent(){

        try
        {
            driver.switchTo().alert();
            return true;
        }catch(Exception e)
        {
            return false;
        }
    }
    private String randomString(){
        Random rand = new Random();
        StringBuilder str = new StringBuilder("");
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789!#$%";
        for(int i=0;i<10;i++)
        {
            str.append(chars.charAt(rand.nextInt(chars.length()-1)));
        }
        return str.toString();
    }
}
