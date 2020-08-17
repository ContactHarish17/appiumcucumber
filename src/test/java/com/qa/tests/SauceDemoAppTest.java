package com.qa.tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import sun.misc.Cache;

import java.net.MalformedURLException;

public class SauceDemoAppTest extends  SampleSauceTest {


    public AndroidDriver  androidDriver;

    @Parameters({"emulator","deviceName","platformVersion","testname" })
    @BeforeTest
    public void setUp(String emulator,String deviceName, String platformVersion, String testname)
    {

        try {
            this.createDriver(emulator,deviceName,platformVersion,testname);
            androidDriver = this.getAndroidDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void login() throws InterruptedException, MalformedURLException {
        try {

            Thread.sleep(4000);
            System.out.println("Inside the test methog");
            androidDriver.findElement(By.xpath("//android.view.ViewGroup[@index='0']/android.widget.ImageView[@index='0']")).isDisplayed();
            androidDriver.findElementByAccessibilityId("test-Username").sendKeys("standard_user");
            androidDriver.findElementByAccessibilityId("test-Password").sendKeys("secret_sauce");
            androidDriver.findElementByAccessibilityId("test-LOGIN").click();

            System.out.println("Inside the Second Test methog");
            Assert.assertTrue(androidDriver.findElement(By.xpath("//android.widget.TextView[@text='PRODUCTS']")).isDisplayed());
            androidDriver.findElementByAccessibilityId("test-Menu").click();
            androidDriver.findElementByAccessibilityId("test-Close").click();
        }catch (Exception e)
        {}
    }


    @AfterTest
    public void tearDown()
    {
        try {
            this.stopServer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
