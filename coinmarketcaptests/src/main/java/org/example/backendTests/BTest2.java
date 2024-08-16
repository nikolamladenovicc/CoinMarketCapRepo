package org.example.backendTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class BTest2 {

    private static final String API_KEY = "8d958aa8-26e3-4821-afc2-345aadceab10";
    private static final String BASE_URL = "https://pro-api.coinmarketcap.com/";

    public static void main(String[] args) {
        retrieveEthereumInfo();
    }

    private static void retrieveEthereumInfo(){
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("X-CMC_PRO_API_KEY", API_KEY)
                .contentType(ContentType.JSON)
                .queryParam("id", "1027")
                .get("v2/cryptocurrency/info");

        if (response.getStatusCode() == 200){
            JSONObject data = new JSONObject(response.getBody().asString()).getJSONObject("data").getJSONObject("1027");
            System.out.println(data);
            validateEthereumInfo(data);

        } else {
            System.out.println("Failed to retrieve info. Status Code: " + response.getStatusCode());
            System.out.println("Response: " + response.getBody().asString());

        }

    }
    private static void validateEthereumInfo(JSONObject data){
        String logoURL = data.getString("logo");

        if (logoURL.equals("https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png")){
            System.out.println("TEST PASSED| Logo URL is correct. Logo URL is: " + logoURL);

        }else {
            System.out.println("TEST FAILED| Logo URL is incorrect: " + logoURL);

        }

        // 2. Validate the technical documentation URI
        if (data.has("urls")) {
            JSONObject urls = data.getJSONObject("urls");
            if (urls.has("technical_doc")) {
                boolean foundDoc = urls.getJSONArray("technical_doc").toList().contains("https://github.com/ethereum/wiki/wiki/White-Paper");
                if (foundDoc) {
                    System.out.println("TEST PASSED| Technical documentation URI is correct. URI is:" + urls.getJSONArray("technical_doc").toList());
                } else {
                    System.out.println("TEST FAILED| Technical documentation URI is missing or incorrect.");
                }
            } else {
                System.out.println("TEST FAILED| No technical documentation found.");
            }
        } else {
            System.out.println("TEST FAILED| URLs section is missing.");
        }

        String symbol = data.getString("symbol");
        if (symbol.equals("ETH")) {
            System.out.println("TEST PASSED| Symbol is correct. Symbol is: " + symbol);
        } else {
            System.out.println("TEST FAILED| Symbol is incorrect: " + symbol);
        }

        String dateAdded = data.getString("date_added");
        if (dateAdded.equals("2015-08-07T00:00:00.000Z")) {
            System.out.println("TEST PASSED| Date added is correct. Date added: " + dateAdded);

        } else {
            System.out.println("TEST FAILED| Date added is incorrect: " + dateAdded);
        }

        // 5. Validate the platform is null
        if (data.isNull("platform")) {
            System.out.println("TEST PASSED| Platform is correctly set to null.");
        } else {
            System.out.println("TEST FAILED| Platform is not null.");
        }

        // 6. Validate the 'mineable' tag
        if (data.has("tags")) {
            boolean isMineable = data.getJSONArray("tags").toList().contains("mineable");
            if (isMineable) {
                System.out.println("TEST PASSED| Mineable tag is present.");
            } else {
                System.out.println("TEST FAILED| 'Mineable' tag does not exist.");
            }
        } else {
            System.out.println("TEST FAILED| No tags found.");
        }
    }
}
