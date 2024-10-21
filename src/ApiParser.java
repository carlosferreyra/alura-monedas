import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson; // libreria para mapear json con una java class
import com.google.gson.JsonObject; // java class que usa gson para el mapeo
import io.github.cdimascio.dotenv.Dotenv; // accede a al API key en un archivo .env por seguridad.
public class ApiParser {
    public static double getExchangeRate(String monedaOrigen, String monedaDestino) throws IOException, InterruptedException {
        Dotenv dotenv = Dotenv.load();
        // no se olvide de generar su propia api key de la plataforma.
        String API_KEY = dotenv.get("API_KEY");
        if (API_KEY == null || API_KEY.isEmpty()) {
            throw new ConnectException("Missing, Wrong or Invalid API Key,");
        }
        //  example request https://v6.exchangerate-api.com/v6/68711f94b735d0794e49a422/latest/USD
        System.out.println("API KEY: " + API_KEY);
//        sleep for two seconds
        Thread.sleep(2000);
        // Build the complete URL including destination currency
        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + monedaOrigen;

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
        return json.get("conversion_rates").getAsJsonObject().get(monedaDestino).getAsDouble();
    }
}