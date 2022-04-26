package com.example.demo.stocktrade;

import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.net.URLDecoder;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.concurrent.atomic.AtomicLong;

@RestController
public class StockController {
    private static final String template = "%s";

    @CrossOrigin
    @GetMapping("/stockPrice")
    public Stock stock(@RequestParam String code,@RequestParam String area ) {


        String sURL ="http://qt.gtimg.cn/q=" + area + code;
        String inputLine = "";
        JSONParser parser = new JSONParser();
        try {
            URL url = new URL(sURL); // URL to Parse
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"GBK"));

            inputLine = in.readLine();
            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Stock(String.format(template, inputLine));
    }

    @CrossOrigin
    @GetMapping("/stockPriceTask")
    public Stock stockPriceTask(@RequestParam String codeList) {
      //  System.out.println(codeList);
        String sURL ="http://qt.gtimg.cn/q=" + codeList;
        String inputLine = "";
        String result = "";
        JSONParser parser = new JSONParser();
        try {
            URL url = new URL(sURL); // URL to Parse
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"GBK"));


            while ((inputLine = in.readLine()) != null) {
                result += inputLine;
            }
            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Stock(String.format(template, result));
    }
}
