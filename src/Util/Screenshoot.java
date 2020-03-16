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


}
