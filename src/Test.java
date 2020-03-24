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
        String url = "https://ihale.zirvemotomotiv.com.tr/login";

      /*  String capchaLink = "http://azcaptcha.com/in.php?key=2nfdhwxcv9ymzg8bbkxzpmf74q6hwyjc&method=userrecaptcha&googlekey=6Le-wvkSVVABCPBMRTvw0Q4Muexq1bi0DJwx_mJ-&pageurl=https://ihale.zirvemotomotiv.com.tr/verify";
        System.out.println(Util.sendPostRequest(capchaLink ,null));

        String captch = Util.sendPostRequest(capchaLink ,null).substring(7);
        System.out.println("capth: " + captch);*/
/*
        String formData = "eposta=july234%40gmail.com&sifre=w435345&captcha_code=223424";
        System.out.println(Util.sendPostRequest(url, formData));*/

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
