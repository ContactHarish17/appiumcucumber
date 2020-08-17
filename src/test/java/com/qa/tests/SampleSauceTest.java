package com.qa.tests;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
public class SampleSauceTest
{
    String sauceUserName = System.getenv("SAUCE_USERNAME");
    String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");


    public ThreadLocal<String> sessionId = new ThreadLocal<>();
    public ThreadLocal<AndroidDriver> androidDriver = new ThreadLocal<>();
    String Androidapp = "https://github.com/saucelabs/sample-app-mobile/releases/download/2.3.0/Android.SauceLabs.Mobile.Sample.app.2.3.0.apk?raw=true";


    public void createDriver(String emulator, String deviceName, String platform, String testname) throws MalformedURLException, InterruptedException
    {
        DesiredCapabilities caps = new DesiredCapabilities();
       // caps.setCapability("username", sauceUserName);
       // caps.setCapability("accessKey", sauceAccessKey);
       // caps.setCapability("deviceName","Samsung_Galaxy_S9_free");
        if(emulator.equalsIgnoreCase("true"))
        {
            caps.setCapability("avd", deviceName);
            caps.setCapability("avdLaunchTimeout", 120000);
        }
        caps.setCapability("udid",deviceName);
        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("browserName", "");
        caps.setCapability("platformVersion",platform);
        caps.setCapability("platformName","Android");
        caps.setCapability("name", testname);
        caps.setCapability("app",Androidapp);
        androidDriver.set(new AndroidDriver<>(new URL("https://harish.ramakrishna:453d0f8f-02cf-43af-972b-305bdcd007da@ondemand.us-west-1.saucelabs.com:443/wd/hub"),caps));
     //   androidDriver.set(new AndroidDriver<>(new URL("https://"+System.getenv("SAUCE_USERNAME")+":"+System.getenv("SAUCE_ACCESS_KEY")+"ondemand.us-west-1.saucelabs.com:443/wd/hub"), caps));
        String id = ((RemoteWebDriver) getAndroidDriver()).getSessionId().toString();
        sessionId.set(id);

    }


    public void stopServer() throws Exception {
        androidDriver.get().quit();
    }

    public AndroidDriver getAndroidDriver() {

        return androidDriver.get();
    }

    public String getSessionId() {
        return sessionId.get();
    }
}