package Util;

import net.bytebuddy.implementation.bytecode.Throw;
import okhttp3.*;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Screenshoot {

    final String regex = "[A-Za-z0-9]";
    final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

    public WebDriver screenshootTake() throws Exception {
        System.setProperty("webdriver.gecko.driver", "geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        //dpi reset
        options.addPreference("layout.css.devPixelsPerPx", "1");
        WebDriver driver;
        driver = new FirefoxDriver(options);
        driver.get("https://ihale.zirvemotomotiv.com.tr/login");
       // driver.manage().window().fullscreen();

        //kanka get ile sitenin simini yazdik daha

        WebElement ele = driver.findElement(By.xpath("//img[@id='captcha']"));
        //burada da takescreenshoot metodu ile element ve driverin screenshot aldik.  captche için mi butrası evet tmm alta kaydedi resmi evey tmm çıkıomn ben o zman // cekersiniz projeyi ok ok
        Screenshot ss = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver, ele);

        ImageIO.write(ss.getImage(), "png", new File("elementScreenshot.png"));

        Thread.sleep(3000);

        return driver;


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

    public void login(WebDriver driver, String code) throws InterruptedException {
        try {

            WebElement userName = driver.findElement(By.id("eposta"));
            WebElement userPassword = driver.findElement(By.id("sifre"));
            userName.sendKeys("deneme");
            userPassword.sendKeys("aloonur");
            WebElement captchCode = driver.findElement(By.xpath("//input[@name='captcha_code']"));
            Matcher veri = pattern.matcher(code);

            if (code.length() == 5 && veri.matches()) {
                captchCode.sendKeys(code);
                Thread.sleep(1000);
                WebElement loginButton = driver.findElement(By.id("loginbut"));
                loginButton.click();
            }
            else{
                System.out.println("code eksik veya duzensiz geldi.");
            }

          //  loginButton.click();
        } catch (Exception e) {

            System.err.println("error login " + e.toString());
        }


    }





}
