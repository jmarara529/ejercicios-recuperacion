import java.util.Arrays;
import java.util.Scanner;

public class JokerPrimitivaV2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Número de participaciones: ");
        int participaciones = scanner.nextInt();
        scanner.nextLine();

        while (participaciones-- > 0) {
            System.out.print("Números del usuario (7 dígitos): ");
            String numUsuario = scanner.nextLine();
            if (!numUsuario.matches("\\d{7}")) {
                throw new IllegalArgumentException("Los números deben tener exactamente 7 dígitosy estar formado unicamente por numeros.");
            }

            System.out.print("Números ganadores (7 dígitos): ");
            String numGanadores = scanner.nextLine();
            if (!numGanadores.matches("\\d{7}")) {
                throw new IllegalArgumentException("Los números deben tener exactamente 7 dígitos y estar formado unicamente por numeros.");
            }

            char[] userNumbers = numUsuario.toCharArray();
            char[] winnerNumbers = numGanadores.toCharArray();

            Arrays.sort(userNumbers);
            Arrays.sort(winnerNumbers);

            String result = Arrays.equals(userNumbers, winnerNumbers) ? "GANA" : "PIERDE";
            System.out.println(result);
        }

        scanner.close();
    }
}

