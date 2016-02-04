package com.company;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void jsonSimple(String text) throws ParseException {
        JSONObject obj = (JSONObject)new JSONParser().parse(text);
        JSONObject query = (JSONObject) obj.get("query");
        JSONObject results = (JSONObject) query.get("results");
        JSONArray rate = (JSONArray) results.get("rate");
        List<ValueCourse> listOfValues = new ArrayList<ValueCourse>();
        for (int i = 0; i < rate.size(); i++) {
            JSONObject cur = (JSONObject) rate.get(i);
            ValueCourse vc = new ValueCourse();
            vc.name = (String) cur.get("Name");
            vc.ask = (String) cur.get("Ask");
            vc.bid = (String) cur.get("Bid");
            vc.id = (String) cur.get("id");
            vc.rate = (String) cur.get("Rate");
            vc.time = (String) cur.get("Time");
            vc.date = (String) cur.get("Date");
            listOfValues.add(vc);
        }
    }

    public static void gsonParse(String text) {
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        //CourseValue cv = gson.fromJson(text, CourseValue.class);
        //System.out.println(cv.query.count);
        Dan dan = gson.fromJson(text, Dan.class);
        for (String interest : dan.interestsOfMyFuckingLife) {
            System.out.println(interest);
        }
    }

    public static String loadJsonString(String url) throws IOException {
        BufferedReader input = null;
        try {
            input = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String aux = "";
        String text = "";
        while ((aux = input.readLine()) != null) {
            text += aux;
        }
        return text;
    }



    public static void main(String[] args) throws IOException, ParseException {
        String urlString = "https://query.yahooapis.com/v1/public/yql?q=select+*+from+yahoo.finance.xchange+where+pair+=+%22USDRUB,EURRUB%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
        String jsonString = "{\"query\":{\"count\":2,\"created\":\"2016-02-02T16:23:54Z\",\"lang\":\"en-US\",\"results\":{\"rate\":[\n" +
                "    {\"id\":\"USDRUB\",\"Name\":\"USD/RUB\",\"Rate\":\"78.8080\",\"Date\":\"2/2/2016\",\"Time\":\"4:23pm\",\"Ask\":\"78.8320\",\"Bid\":\"78.8080\"},\n" +
                "    {\"id\":\"EURRUB\",\"Name\":\"EUR/RUB\",\"Rate\":\"85.9125\",\"Date\":\"2/2/2016\",\"Time\":\"4:23pm\",\"Ask\":\"85.9426\",\"Bid\":\"85.8824\"}]}}}";
        String danjson = "{\n" +
                "    \"name\": \"Dan\",\n" +
                "    \"surname\": \"Yaschenko\",\n" +
                "    \"age\": 19,\n" +
                "    \"interests\": [\n" +
                "        \"football\",\n" +
                "        \"gopro\",\n" +
                "        \"programming\"\n" +
                "    ]\n" +
                "}";
        String upperCaseJson = "{\n" +
                "    \"name\": \"Dan\",\n" +
                "    \"surname\": \"Yaschenko\",\n" +
                "    \"age\": 19,\n" +
                "    \"interests_of_my_fucking_life\": [\n" +
                "        \"football\",\n" +
                "        \"gopro\",\n" +
                "        \"programming\"\n" +
                "    ]\n" +
                "}";
        gsonParse(upperCaseJson);

    }
}
