import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson; // Assuming you want to use Gson for parsing JSON

public class ApiParser {
    public static double getExchangeRate(String monedaOrigen, String monedaDestino) throws IOException, InterruptedException {
        String API_KEY = System.getenv("API_KEY");
        //  example request https://v6.exchangerate-api.com/v6/68711f94b735d0794e49a422/latest/USD

        // Build the complete URL including destination currency
        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + monedaOrigen + "/" + monedaDestino;

        // Create an HTTP client and send a GET request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check for successful response
        if (response.statusCode() != 200) {
            throw new IOException("Failed to retrieve exchange rate. Status code: " + response.statusCode());
        }

        // Parse the JSON response
        Gson gson = new Gson();
        // Assuming the response contains an exchange rate for the destination currency
        JsonObject json = gson.fromJson(response.body(), JsonObject.class);
        double exchangeRate = json.get("conversion_rate").getAsDouble();

        return exchangeRate;
    }
}