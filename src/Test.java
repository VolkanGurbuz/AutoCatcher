import Util.Util;
import Util.Screenshoot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import org.json.*;
import java.util.regex.*;

import java.io.File;
import java.util.Base64;

public class Test {
    public static void main(String[] args) throws Exception {

        //avoid exception sockettimeout
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8182");
        String url = "";

        WebDriver driver;

        Screenshoot screenshoot = new Screenshoot();
        driver = screenshoot.screenshootTake();
        Util util = new Util();

        String encodedString =util.encodedString();
        String jsonString = Util.getResult(encodedString);

        String code =  util.getCode(jsonString);
        System.out.println("code: " + code);
    //    screenshoot.login(driver, code);

    }

}
