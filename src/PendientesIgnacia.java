import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class PendientesIgnacia {
    public static void main(String[] args) throws FileNotFoundException {
        try {
            Scanner fileScanner = new Scanner(new File("Pendientes.txt"));
            int numPendientes = fileScanner.nextInt();

            while (numPendientes != 0) {
                HashMap<Integer, Integer> pendientes = new HashMap<>();
                int parejas = 0;

                for (int i = 0; i < numPendientes; i++) {
                    int pendiente = fileScanner.nextInt();
                    pendientes.put(pendiente, pendientes.getOrDefault(pendiente, 0) + 1);

                    if (pendientes.get(pendiente) % 2 == 0) {
                        parejas++;
                    }
                }

                System.out.println(parejas);
                numPendientes = fileScanner.nextInt();
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Error al leer el archivo, no se puede leer el fichero o no existe");
        }
    }
}
