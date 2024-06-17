import java.io.*;
import java.util.Arrays;

public class JokerPrimitiva {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("SorteoJoker.txt"))) {

            int participaciones = Integer.parseInt(reader.readLine());

            while (participaciones-- > 0) {
                String[] line = reader.readLine().split(" ");
                String numUsuario = line[0];
                String numGanadores = line[1];

                if (numUsuario.length() != 7 || numGanadores.length() != 7) {
                    throw new IllegalArgumentException("Los números deben tener 7 dígitos.");
                }

                for (char c : numUsuario.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        throw new IllegalArgumentException("Las cadenas deben contener solo números.");
                    }
                }
                for (char c : numGanadores.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        throw new IllegalArgumentException("Las cadenas deben contener solo números.");
                    }
                }

                char[] userNumbers = numUsuario.toCharArray();
                char[] winnerNumbers = numGanadores.toCharArray();

                Arrays.sort(userNumbers);
                Arrays.sort(winnerNumbers);

                String result = Arrays.equals(userNumbers, winnerNumbers) ? "GANA" : "PIERDE";
                System.out.println(result);
            }

        } catch (IOException e) {
            throw new NullPointerException("Error al leer el archivo, el archivo no existe");
        }
    }
}

