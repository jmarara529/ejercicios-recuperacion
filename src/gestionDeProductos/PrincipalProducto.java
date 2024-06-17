package gestionDeProductos;

import java.io.*;
import java.util.*;

public class PrincipalProducto implements Serializable {
    private final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        final Map<String, List<Producto>> listadoTiendas = new TreeMap<>();

        cargarTiendas(listadoTiendas);

        boolean salir = false;
        while (!salir) {
            switch (dibujarMenuTiendas()) {
                case 1:
                    crearTienda(listadoTiendas);
                    break;
                case 2:
                    listarTienda(listadoTiendas);
                    break;
                case 3:
                    eliminarTienda(listadoTiendas);
                    break;
                case 4:
                    seleccionarTienda(listadoTiendas);
                    break;
                case 0:
                    guardarTiendas(listadoTiendas);
                    salir = true;
                    break;
            }
        }

        sc.close();
    }






    public static int dibujarMenuTiendas() {
            System.out.println();
            System.out.println("Elige una opción:");
            System.out.println("1. Crear una tienda");
            System.out.println("2. Listar tienda");
            System.out.println("3. Eliminar tienda");
            System.out.println("4. Seleccionar tienda");
            System.out.println("0. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            return opcion;
        }

        private static void crearTienda(Map<String, List<Producto>> listadoTienda) {
            String tienda = leerTienda();
            if (listadoTienda.containsKey(tienda)) {
                System.out.println("La tienda ya existe");
            } else {
                listadoTienda.put(tienda, new ArrayList<>());
                System.out.println("Tienda creada con exito");
            }
        }

        public static void listarTienda(Map<String, List<Producto>> listado) {
            for (String Tienda : listado.keySet()) {
                System.out.println("> " + Tienda);
            }
        }

        private static void eliminarTienda(Map<String, List<Producto>> listadoTienda) {
            String tienda = leerTienda();
            if (!listadoTienda.containsKey(tienda)) {
                System.out.println("La tienda no existe");
            } else {
                listadoTienda.remove(tienda);
                System.out.println("Tienda eliminada con éxito");
            }
        }

        private static void seleccionarTienda(Map<String, List<Producto>> listadoTienda) {
            String tienda = leerTienda();
            List<Producto> listadoTiendas = listadoTienda.get(tienda);

            if (null == listadoTiendas) {
                System.out.println("La tienda no existe");
            } else {
                mainProductos(listadoTiendas);
            }
        }

        public static String leerTienda() {
            System.out.print("Tienda: ");
            return sc.nextLine();
        }

        public static void guardarTiendas(Map<String, List<Producto>> listadoTiendas) throws IOException {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("producto.dat"))) {
                oos.writeObject(listadoTiendas);
                System.out.println("Productos guardados con éxito.");
            } catch (IOException e) {
                throw new IOException("Error al guardar");
            }
        }

        public static void cargarTiendas(Map<String, List<Producto>> listadoTiendas) throws IOException {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Producto.dat"))) {
                Map<String, List<Producto>> tiendasCargadas = (Map<String, List<Producto>>) ois.readObject();
                listadoTiendas.putAll(tiendasCargadas);
                System.out.println("Productos cargados con éxito.");
            } catch (IOException | ClassNotFoundException e) {
                throw new IOException("Error al guardar");
            }
        }

        private static void mainProductos(List<Producto> listadoProductos) {
            boolean salir = false;
            while (!salir) {
                switch (dibujarMenuProductos()) {
                    case 1:
                        listarProductos(listadoProductos);
                        break;
                    case 2:
                        anyadirProductos(listadoProductos);
                        break;
                    case 3:
                        insertarProductos(listadoProductos);
                        break;
                    case 4:
                        ordenarProductosPorPrecio(listadoProductos);
                        break;
                    case 5:
                        eliminarProducto(listadoProductos);
                        break;
                    case 6:
                        eliminarProductos(listadoProductos);
                        break;
                    case 0:
                        salir = true;
                }
            }
        }

        private static int dibujarMenuProductos() {
            System.out.println();
            System.out.println("Elige una opción:");
            System.out.println("1. Listar productos");
            System.out.println("2. Añadir un producto al final ");
            System.out.println("3. Insertar un producto en la posición indicada");
            System.out.println("4. Ordenar el listado por precio");
            System.out.println("5. Eliminar el producto de la posición indicada");
            System.out.println("6. Eliminar todos los productos de la tienda");
            System.out.println("0. Salir");

            int opcion = sc.nextInt();
            sc.nextLine();

            return opcion;
        }

        private static void listarProductos(List<Producto> listadoProductos) {
            for (int i = 0; i < listadoProductos.size(); i++) {
                System.out.printf("%02d: %s\n", i + 1, listadoProductos.get(i));
            }
        }

        private static void anyadirProductos(List<Producto> listadoProductos) {
            Producto p = leerProducto();
            listadoProductos.add(p);
            System.out.println("Producto añadido");
        }

        private static void insertarProductos(List<Producto> listadoProductos) {
            int pos = leerPosicion(listadoProductos.size() + 1);
            Producto p = leerProducto();
            listadoProductos.add(pos, p);
            System.out.println("Producto insertado");
        }

        private static void ordenarProductosPorPrecio(List<Producto> listadoProductos) {
            listadoProductos.sort((p1, p2) -> (int) (p1.getPrecio() - p2.getPrecio()));
        }


        private static void eliminarProducto(List<Producto> listadoProductos) {
            int pos = leerPosicion(listadoProductos.size());
            Producto p = listadoProductos.remove(pos);
            System.out.println("Producto eliminado: " + p);
        }

        private static void eliminarProductos(List<Producto> listadoProductos) {
            listadoProductos.clear();
            System.out.println("Productos de la tienda eliminados");
        }

        public static Producto leerProducto() {
            String marca;
            String descripcion;
            float precio;

            System.out.print("Marca: ");
            marca = sc.nextLine();
            System.out.print("Descripción: ");
            descripcion = sc.nextLine();
            System.out.print("Precio: ");
            precio = sc.nextFloat();
            sc.nextLine();

            return new Producto(marca, descripcion, precio);
        }

        public static int leerPosicion(int max) {
            int num;

            do {
                System.out.printf("Posición [1-%d]: ", max);
                num = sc.nextInt();
            } while (num < 1 || num > max);
            sc.nextLine();

            return num - 1;
        }

}
