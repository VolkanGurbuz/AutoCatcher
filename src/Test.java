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

        final String regex = "[A-Za-z0-9]";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);

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

        byte[] fileContent = FileUtils.readFileToByteArray(new File("elementScreenshot.png"));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        String jsonString = Util.getResult(encodedString);

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("ParsedResults");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj1 = jsonArray.getJSONObject(i);
            String code = obj1.getString("ParsedText");
            System.out.println(code);
            Matcher veri = pattern.matcher(code);
            if (code.length() != 5 && !veri.matches()){
                System.err.println("Kod 5 Karakterli Değil veya İçerisinde Özel Karakter Barındırıyor!");
                System.exit(0);
            }
            screenshoot.login(driver,code);
        }

        driver.quit();

        System.exit(0);


    }



}
