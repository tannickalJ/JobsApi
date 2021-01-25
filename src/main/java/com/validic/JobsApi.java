package com.validic;

import org.apache.http.client.utils.URIBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JobsApi {
    public void run (String... args) {

    }

    public static void gson() throws IOException, URISyntaxException {

        List<String> cities = new ArrayList<>();
        cities.add("Boston");
//        cities.add("San Fransisco");
//        cities.add("Los Angeles");
//        cities.add("Denver");
//        cities.add("Boulder");
//        cities.add("Chicago");
//        cities.add("New York");
//        cities.add("Raleigh");

        List<String> langs = new ArrayList<>();
        langs.add("Java");
        langs.add("C#");
        langs.add("Python");
        langs.add("Swift");
        langs.add("Objective-C");
        langs.add("Ruby");
        langs.add("Kotlin");
        langs.add("Go%20");
        langs.add("C++");
        langs.add("Javascript");

        URIBuilder ub = new URIBuilder("https://jobs.github.com/positions.json");
        HttpURLConnection con = null;
        String id;
        int count = 0;

        for (int i = 0; i < cities.size(); i++) {
            ub.addParameter("location", cities.get(i));
            for (int j = 0; j < langs.size(); j++) {
                ub.addParameter("description", langs.get(j));
                URL url = new URL(ub.toString());
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("Accept", "application/json");

                if (con.getResponseCode() != 200) {
                    throw new RuntimeException("Error code :" + con.getResponseCode());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response = new StringBuffer();
                String output;
                while ((output = br.readLine()) != null) {
                    // String out = output;
                    String []tokens = output.split(":");
                    response.append(output);
                    response.toString();
                    if (response.equals("[]")) {
                        continue;
                    }
                    else {
                        count++;

                        System.out.println(response.toString());
                    }
                }
            }


        }
        System.out.println(count);

//        URIBuilder ub = new URIBuilder("https://jobs.github.com/positions.json");
//        ub.addParameter("description", "python");
//        ub.addParameter("location", "ny");
//        URL url = new URL(ub.toString());
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("Accept", "application/json");
//
//        if (con.getResponseCode() != 200) {
//            throw new RuntimeException("Error code :" + con.getResponseCode());
//        }
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String output;
//        while ((output = br.readLine()) != null) {
//            System.out.println(output);
//        }

        con.disconnect();

    }

    public static void main(String[] args) throws URISyntaxException, IOException {
//        SpringApplication.run(JobsApi.class);
        gson();
    }
}
