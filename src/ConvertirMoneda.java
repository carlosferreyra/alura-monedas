// Path: src/ConvertirMoneda.java

import java.io.IOException;

public class ConvertirMoneda {
    public static double convertirMoneda(String monedaOrigen, String monedaDestino, double monto) throws IOException, InterruptedException {
        // acceder a la api de exchange rate
        double exchangeRate = ApiParser.getExchangeRate(monedaOrigen, monedaDestino);
        return monto * exchangeRate;
}
}
