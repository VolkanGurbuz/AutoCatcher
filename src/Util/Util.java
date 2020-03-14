package Util;


import org.htmlcleaner.*;
import org.unbescape.html.HtmlEscape;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.*;

public final class Util {

    //  also known as Helper class, is a class, which contains just static helper method

    private static HttpClient client = HttpClient.newHttpClient();
    private XPathFactory xpf = XPathFactory.newInstance();
    private XPath xpath = xpf.newXPath();


    public static String sendGetRequest(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            }
        } catch (Exception e) {
            System.err.println("Failure , " + e.toString());
        }
        return null;
    }

    public static String sendPostRequest(String URL, String postForm) {
        String response = null;
        try {
            BufferedReader br;
            StringBuilder sb = new StringBuilder();
            URL url = new URL(URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.getOutputStream().write(postForm.getBytes("UTF-8"));
            con.getInputStream();

            if (200 <= con.getResponseCode() && con.getResponseCode() <= 299) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            while ((response = br.readLine()) != null) {
                sb.append(response);
            }
            return sb.toString();
        } catch (Exception e) {
            System.err.println("Failure , " + e.toString());
        }
        return null;
    }

    public static String getURLSource(String url) throws IOException {
        URL urlObject = new URL(url);
        URLConnection urlConnection = urlObject.openConnection();
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");

        return toString(urlConnection.getInputStream());
    }

    private static String toString(InputStream inputStream) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
            String inputLine;
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            return stringBuilder.toString();
        }
    }



    private static String parsePrice(String price) {
        try {
            List<Character> numberList = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',');
            String tempPrice = "";
            for (int i = 0; i < price.length(); i++) {
                if (numberList.contains(price.charAt(i))) {
                    if (price.charAt(i) == ',') {
                        tempPrice += ".";
                    } else {
                        tempPrice += (Character.toString(price.charAt(i)));
                    }
                }
            }

            return tempPrice;
        } catch (Exception e) {
            System.err.println("parsePrice " + e);
        }
        return null;
    }


    private static void dumpToFile(String content, String fileName) {
        try {
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (Exception e) {
            System.err.println("dumpToFile " + e);

        }
    }

    public static Document wrapToDocument(String document) {
        try {
            HtmlCleaner cleaner = new HtmlCleaner();
            TagNode rootNode = cleaner.clean(document);
            CleanerProperties properties = cleaner.getProperties();
            DomSerializer domSerializer = new DomSerializer(properties);
            Serializer serializer = new SimpleHtmlSerializer(properties);
            StringWriter writer = new StringWriter();
            serializer.write(rootNode, writer, "UTF-8");
            dumpToFile(writer.toString(), "file.html");
            return domSerializer.createDOM(rootNode);
        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String clearTurkishChars(String str) {
        String ret = HtmlEscape.unescapeHtml(str);
        ;
        char[] turkishChars = new char[]{0x131, 0x130, 0xFC, 0xDC, 0xF6, 0xD6, 0x15F, 0x15E, 0xE7, 0xC7, 0x11F, 0x11E};
        char[] englishChars = new char[]{'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G'};
        for (int i = 0; i < turkishChars.length; i++) {
            ret = ret.replaceAll(new String(new char[]{turkishChars[i]}), new String(new char[]{englishChars[i]}));
        }
        return ret;
    }


    private String getCurrentDate() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            return dateFormat.format(date);

        } catch (Exception e) {
            System.err.println("errror getCurrentDate " + e);
        }
        return null;
    }

}