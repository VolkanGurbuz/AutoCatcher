import Util.Util;

public class Test {
    public static void main(String[] args) {

        String url = "https://ihale.zirvemotomotiv.com.tr/login/girisyap";

        String capchaLink = "http://azcaptcha.com/in.php?key=kj8qqnyrgmc32tfdjb6mwh74rnhxvpzw&method=userrecaptcha&googlekey=6Le-wvkSVVABCPBMRTvw0Q4Muexq1bi0DJwx_mJ-&pageurl=https://ihale.zirvemotomotiv.com.tr/verify";
        System.out.println(Util.sendPostRequest(capchaLink ,null));
        String captch = Util.sendPostRequest(capchaLink ,null).substring(7);
        System.out.println("capth: " + captch);
        String formData = "eposta=july234%40gmail.com&sifre=4535345&captcha_code="+captch;
        System.out.println(Util.sendPostRequest(url, formData));
    }


}
