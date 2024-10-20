//  Path: src/ConvertirMoneda.java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        System.out.println("********** Challenge **********");
        System.out.println("Sea bienvenido/a al conversor de moneda =]");
        System.out.println("********************************");
        // arraylist para guardar los prefijos de las monedas
        List<List<String>> operaciones = Arrays.asList(
            Arrays.asList("USD", "ARS"),
            Arrays.asList("ARS", "USD"),
            Arrays.asList("USD", "BRL"),
            Arrays.asList("BRL", "USD"),
            Arrays.asList("USD", "COP"),
            Arrays.asList("COP", "USD")
        );
        // arraylist para guardar los valores de las monedas
        while (true) {
            System.out.println("Ingrese un numero para operar: ");
            System.out.println("0. Salir");
            for (int i = 0; i < operaciones.size(); i++) {
                System.out.println(i + 1 + ". " + operaciones.get(i).get(0) + " =>> " + operaciones.get(i).get(1));
            }
            System.out.println("********************************");
            Scanner scanner = new Scanner(System.in, "UTF-8");
            int opcion = scanner.nextInt();
            try {
                if (opcion == 0) {
                    System.out.println("Gracias por usar el conversor de moneda =]");
                    break;
                }
                if (opcion < 0 || opcion > operaciones.size()) {
                    throw new Exception("Opcion no valida, intente de nuevo =]");
                }
                System.out.println("Ingrese el monto a convertir: ");
                double monto = scanner.nextDouble();
                try {
                    if (monto < 0) {
                        throw new Exception("El monto no puede ser negativo");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    continue;
                }
                // iniciamos la conversion de moneda y accedemos a la API de conversion de moneda
                List<String> monedaPair = operaciones.get(opcion - 1);
                String monedaOrigen = monedaPair.get(0);
                String monedaDestino = monedaPair.get(1);
                // Perform currency conversion using the ConvertirMoneda class
                double resultado = ConvertirMoneda.convertirMoneda(monedaOrigen, monedaDestino, monto);
                // cast double to str
                
                System.out.println("********************************");
                System.out.println("El la tasa de cambio para " +monedaOrigen+ Double.toString(monto) + " es: " + monedaDestino + Double.toString(resultado));
                System.out.println("********************************");
            } catch (Exception e) {
                System.out.println("Opcion no valida, intente de nuevo =]");
            }
            // clear screen
            // sleep for 2 seconds
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("\033[H\033[2J");

        }

    }
}