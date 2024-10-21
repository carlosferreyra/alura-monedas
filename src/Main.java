//  Path: src/ConvertirMoneda.java
import java.io.IOException;
import java.util.*;


public class Main {

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
        double monto;
        // arraylist para guardar los valores de las monedas
        while (true) {
            System.out.println("Ingrese un numero para operar: ");
            System.out.println("0. Salir");
            for (int i = 0; i < operaciones.size(); i++) {
                System.out.println(i + 1 + ". " + operaciones.get(i).get(0) + " =>> " + operaciones.get(i).get(1));
            }
            System.out.println("********************************");
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();
            try {
                if (opcion == 0) {
                    System.out.println("Gracias por usar el conversor de moneda =]");
                    break;
                }
                if (opcion < 0 || opcion > operaciones.size()) {
                    System.out.println("Opcion no valida, intente de nuevo =]]");
                    continue;
                }else{
                System.out.println("Ingrese el monto a convertir: ");
                monto = scanner.nextDouble();}
                try {

                    if (monto < 0) {
                        throw new Exception("El monto no puede ser negativo");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("El monto ingresado debe ser un número. Intente nuevamente.");
                    continue;
                } catch (Exception e) {
                    System.out.println("un error al recibir el monto");
                    continue;
                }
                // sleep for 2 seconds
                try {
                    System.out.println("recuerde: sin la correcta API-KEY, el algoritmo va a fallar");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // iniciamos la conversion de moneda y accedemos a la API de conversion de moneda
                List<String> monedaPair = operaciones.get(opcion - 1);
                String monedaOrigen = monedaPair.get(0);
                String monedaDestino = monedaPair.get(1);
                // Perform currency conversion using the ConvertirMoneda class
                double resultado;
                try {
                    resultado = ConvertirMoneda.convertirMoneda(monedaOrigen, monedaDestino, monto);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }


                
                System.out.println("********************************");
                System.out.println("El la tasa de cambio para " +monedaOrigen+ monto + " es: " + monedaDestino + resultado);
                System.out.println("********************************");
            } catch (InputMismatchException e){
                System.out.println("la operacion no existe");
            }


            System.out.print("\033[H\033[2J");

        }

    }
}