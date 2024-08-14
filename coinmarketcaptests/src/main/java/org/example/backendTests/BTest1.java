package org.example.backendTests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class BTest1 {

    private static final String API_KEY = "8d958aa8-26e3-4821-afc2-345aadceab10";
    private static final String BASE_URL = "https://pro-api.coinmarketcap.com/";

    public static void main(String[] args) {
        // Step 1: Retrieve the IDs of BTC, USDT, and ETH
        int btcId = getCryptoId("BTC");
        int usdtId = getCryptoId("USDT");
        int ethId = getCryptoId("ETH");

        // Step 2: Convert the cryptocurrencies to Bolivian Boliviano (BOB)
        convertToBoliviano(btcId);
        convertToBoliviano(usdtId);
        convertToBoliviano(ethId);
    }

    /**
     * Retrieves the cryptocurrency ID using the symbol (e.g., BTC, USDT, ETH).
     *
     * @param symbol The cryptocurrency symbol.
     * @return The cryptocurrency ID.
     */
    private static int getCryptoId(String symbol) {
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("X-CMC_PRO_API_KEY", API_KEY)
                .contentType(ContentType.JSON)
                .get("v1/cryptocurrency/map");

        if (response.getStatusCode() == 200) {
            JSONArray dataArray = new JSONObject(response.getBody().asString()).getJSONArray("data");
            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject crypto = dataArray.getJSONObject(i);
                if (crypto.getString("symbol").equals(symbol)) {
                    System.out.println("Found " + symbol + " with ID: " + crypto.getInt("id"));
                    return crypto.getInt("id");
                }
            }
        } else {
            System.out.println("Failed to retrieve the ID for " + symbol + ". Status Code: " + response.getStatusCode());
        }
        return -1;
    }

    /**
     * Converts the cryptocurrency to Bolivian Boliviano (BOB) using the given ID.
     *
     * @param cryptoId The cryptocurrency ID.
     */


    private static void convertToBoliviano(int cryptoId) {
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("X-CMC_PRO_API_KEY", API_KEY)
                .contentType(ContentType.JSON)
                .queryParam("id", cryptoId)
                .queryParam("amount", 1)  // Convert 1 unit of the cryptocurrency
                .queryParam("convert", "BOB")
                .get("v2/tools/price-conversion");

        if (response.getStatusCode() == 200) {
            JSONObject data = new JSONObject(response.getBody().asString()).getJSONObject("data");
            double priceInBob = data.getJSONObject("quote").getJSONObject("BOB").getDouble("price");
            System.out.println("Price of ID " + cryptoId + " in BOB: " + priceInBob);
        } else {
            System.out.println("Failed to convert ID " + cryptoId + " to BOB. Status Code: " + response.getStatusCode());
            System.out.println("Response: " + response.getBody().asString());
        }
    }
}
