import Util.Util;
import Util.Screenshoot;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Base64;

public class Test {
    public static void main(String[] args) throws Exception {

        String url = "https://ihale.zirvemotomotiv.com.tr/login";

      /*  String capchaLink = "http://azcaptcha.com/in.php?key=2nfdhwxcv9ymzg8bbkxzpmf74q6hwyjc&method=userrecaptcha&googlekey=6Le-wvkSVVABCPBMRTvw0Q4Muexq1bi0DJwx_mJ-&pageurl=https://ihale.zirvemotomotiv.com.tr/verify";
        System.out.println(Util.sendPostRequest(capchaLink ,null));

        String captch = Util.sendPostRequest(capchaLink ,null).substring(7);
        System.out.println("capth: " + captch);*/
/*
        String formData = "eposta=july234%40gmail.com&sifre=w435345&captcha_code=223424";
        System.out.println(Util.sendPostRequest(url, formData));*/

        Screenshoot screenshoot = new Screenshoot();
      //  screenshoot.screenshootTake();

        byte[] fileContent = FileUtils.readFileToByteArray(new File("verify.png"));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        Util.getResult(encodedString);


    }



}
