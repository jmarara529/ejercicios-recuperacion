import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Potitos {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new FileReader("Potitos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int numPotitos = Integer.parseInt(line);
                if (numPotitos > 25) {
                    throw new IllegalArgumentException("El número de potitos no puede ser mayor a 25.");
                }

                Set<String> si = new HashSet<>();
                Set<String> no = new HashSet<>();

                for (int i = 0; i < numPotitos; i++) {
                    String[] partes = br.readLine().split(": ");
                    String estado = partes[0];
                    String[] ingredientes = partes[1].split(" ");
                    for (String ingrediente : ingredientes) {
                        if (ingrediente.equals("FIN")) {
                            break;
                        }
                        if (estado.equals("SI")) {
                            si.add(ingrediente);
                        } else if (estado.equals("NO")) {
                            no.add(ingrediente);
                        }
                    }
                }

                no.removeAll(si);

                List<String> resultado = new ArrayList<>(no);
                Collections.sort(resultado);

                for (String ingrediente : resultado) {
                    System.out.print(ingrediente + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}