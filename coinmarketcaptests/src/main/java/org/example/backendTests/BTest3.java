package org.example.backendTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BTest3 {

    private static final String API_KEY = "8d958aa8-26e3-4821-afc2-345aadceab10";
    private static final String BASE_URL = "https://pro-api.coinmarketcap.com/";

    public static void main(String[] args) {
        retrieveMineableCurrencies();

    }
    private static void retrieveMineableCurrencies(){
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("X-CMC_PRO_API_KEY", API_KEY)
                .contentType(ContentType.JSON)
                .queryParam("id", "1,2,3,4,5,6,7,8,9,10")
                .get("v2/cryptocurrency/info");

        if (response.getStatusCode() == 200){
            JSONObject data = new JSONObject(response.getBody().asString()).getJSONObject("data");
            // Step 2: Check for mineable tag and collect the IDs
            List<String> mineableCurrencies = new ArrayList<>();
            for (String id : data.keySet()){
                JSONObject currency = data.getJSONObject(id);
                if (currency.has("tags")){
                    JSONArray tags = currency.getJSONArray("tags");
                    if (tags.toList().contains("mineable")){
                        mineableCurrencies.add(id);
                    }
                }
            }
            // Step 3: Print out the mineable cryptocurrencies
            if (!mineableCurrencies.isEmpty()) {
                System.out.println("The following currencies are mineable:");
                for (String id : mineableCurrencies) {
                    String name = data.getJSONObject(id).getString("name");
                    System.out.println("ID: " + id + ", Name: " + name);
                }
            } else {
                System.out.println("No mineable currencies found among the first 10.");
            }

        }else {
            System.out.println("Failed to retrieve info. Status Code: " + response.getStatusCode());
            System.out.println("Response: " + response.getBody().asString());
        }

    }
}
