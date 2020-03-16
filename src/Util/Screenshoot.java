package Util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;


public class Screenshoot {

    public void screenshootTake() throws Exception {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        WebDriver driver;
        driver = new FirefoxDriver();
        //goto url
        driver.get("https://ihale.zirvemotomotiv.com.tr/login");
        //Call user enter driver

        WebElement ele = driver.findElement(By.xpath("//img[@id='captcha']"));

        Screenshot ss = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver,ele);

        ImageIO.write(ss.getImage(), "jpg", new File("c://ElementScreenshot.jpg"));

        login(driver);
       // alo testde bişeyler sorcam  ne testi kanka bi roje gelebilir de onla ilgili test tamam haber et onceden kanka hazir olam tamam resmi ner
        Thread.sleep(6000);

        //taking whole page
      //  this.takeSnapShot(driver, "c://test.png");

    }

    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        //Call getScreenshotAs method to create image file

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

        File DestFile = new File(fileWithPath);

        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);




    }

    public void login(WebDriver driver) throws InterruptedException {
        try {

            WebElement userName = driver.findElement(By.id("eposta"));
            WebElement userPassword = driver.findElement(By.id("sifre"));
            //kanka hesezer rahat birakmior dkfjg açamı amk projeyi hata verio dip
            // h prboıj e  sende calisiyor mu kanak bu sendkeys ne işe yarıo // kanka keys yani klavye tuslarini gonderiyor basiyor neden buna gerek var
             userName.sendKeys("deneme");
            userPassword.sendKeys("aloonur");
            WebElement captchCode = driver.findElement(By.xpath("//input[@name='captcha_code']"));
            captchCode.sendKeys("353453");

            Thread.sleep(1000);

            WebElement loginButton = driver.findElement(By.id("loginbut"));
            loginButton.click();


        }
        catch (Exception e){

            System.err.println("error login " + e.toString());
        }


    }



}
