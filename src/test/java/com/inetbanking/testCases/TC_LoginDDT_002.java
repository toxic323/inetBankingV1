package com.inetbanking.testCases;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;
;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginDDT_002  extends BaseClass {

    @Test(dataProvider = "XXX")
    public void loginDDT(String usn, String pwd){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.setUserName(usn);
        logger.info("Username: '" + username + "' entered");
        loginPage.setPassword(pwd);
        logger.info("Password: '" + password + "' entered");
        loginPage.clickSubmit();
        logger.info("Submitted");
        if(isAlertPresent()==true)
        {
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            Assert.assertTrue(false);
        }else
        {
            loginPage.logout();
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
            Assert.assertTrue(true);
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

    @DataProvider(name="XXX")
    String[][] getData() throws IOException {

        String dataPath = System.getProperty("user.dir") + "/src/test/java/com/inetbanking/testData/TestLogin.xlsx";
        int rownum = XLUtils.getRowCount(dataPath,"Arkusz1");
        int colnum = XLUtils.getCellCount(dataPath, "Arkusz1", 1);
        String loginData[][] =new String[rownum][colnum];

        for(int i=1;i<=rownum;i++)
        {
            for (int j=0;j<colnum;j++)
            {
                loginData[i-1][j]=XLUtils.getCellData(dataPath,"Arkusz1", i,j);
            }
        }

        return loginData;
    }
}
