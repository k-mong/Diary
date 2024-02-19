package com.diary.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class OpenApiService {

    @Value("${weather.api-key}")
    private String apiKey;

    public String getWeatherString(String area){
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q="+area+"&appid="+apiKey;
        System.out.println(apiUrl);

        try {
            //url 클래스의 openConnection 메소드를 통해 HttpURLConnection 인스턴스를 반환
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // --접속
            int responseCode = connection.getResponseCode();
            BufferedReader br;

            if(responseCode == 200){
                br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }else{
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String str;
            StringBuilder sb = new StringBuilder();
            while((str = br.readLine()) != null){
                sb.append(str);
            }
            br.close();
            System.out.println(sb.toString());
            return sb.toString();

        } catch (Exception e) {
            return "fail getWeatherString";
        }
    }

    public HashMap<String,Object> jsonParseString(String weatherInfo){
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject;

        try{
            jsonObject = (JSONObject) jsonParser.parse(weatherInfo);
        }catch(ParseException e){
            throw new RuntimeException(e);
        }

        JSONObject main = (JSONObject) jsonObject.get("main");
        JSONArray weatherArr = (JSONArray) jsonObject.get("weather");
        JSONObject weather = (JSONObject) weatherArr.get(0);

        HashMap<String,Object> map = new HashMap<>();
        map.put("main",weather.get("main"));
        map.put("icon",weather.get("icon"));
        map.put("temp",main.get("temp"));

        return map;
    }
}
