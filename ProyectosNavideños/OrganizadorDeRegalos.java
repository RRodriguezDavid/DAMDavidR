package ProyectosNavideños;
import java.io.*;
import java.util.*;

public class OrganizadorDeRegalos {
    private List<Regalos> regalos;
    private static final String FILE_NAME = "Regalos.txt";

    public OrganizadorDeRegalos() {
        this.regalos = new ArrayList<>();
        loadregalosFromFile();
    }

    public void añadirRegalo(String nombre, String recipient, double coste) {
        regalos.add(new Regalos(nombre,recipient,coste));
        System.out.println("Regalo agregado exitosamente");
    }

    public void eliminarRegalo(String nombre) {
        boolean eliminado = false;
        Iterator<Regalos> iterator = regalos.iterator();
        while(iterator.hasNext()) {
            Regalos regalo = iterator.next();
            if (regalo.getNombre().equalsIgnoreCase(nombre)){
                iterator.remove();
                eliminado = true;
                System.out.println("Regalo eliminado exitosamente");
                break;
            }
        }
        if (!eliminado) {
            System.out.println("Regalo no encontrado");
        }
    }

    public void mostrarRegalos() {
        if (regalos.isEmpty()) {
            System.out.println("No hay regalos en la lista aun");
            return;
        }

        Map<String, List <Regalos>> regalosByRecipient = new TreeMap<>();
        for (Regalos regalo : regalos) {
            regalosByRecipient.putIfAbsent(regalo.getRecipient(), new ArrayList<>());
            regalosByRecipient.get(regalo.getRecipient()).add(regalo);
        }

        for (String recipient : regalosByRecipient.keySet()) {
            System.out.println("\nDestinatario: " + recipient);
            for (Regalos regalo : regalosByRecipient.get(recipient)) {
                System.out.println(regalo.toString());
            }
        }
    }

    public void calcularCoste() {
        double total = 0;
        for (Regalos regalo : regalos) {
            total += regalo.getCoste();
        }
        System.out.println("Coste total de los regalos: " + total + "€");
    }

    public void guardarRegalosToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Regalos regalo : regalos) {
                writer.write(regalo.getNombre() + "," + regalo.getRecipient() + "," + regalo.getCoste());
                writer.newLine();
            }
            System.out.println("Regalos guardado exitosamente en el archivo");
        } catch (IOException e){
            System.out.println("Error al guardar los regalos " + e.getMessage());
        }
    }

    private void loadregalosFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0];
                    String recipient = partes[1];
                    double coste = Double.parseDouble(partes[2]);
                    regalos.add(new Regalos(nombre, recipient, coste));
                }
            }
            System.out.println("Regalos cargados desde el archivo");
        } catch (IOException e) {
            System.out.println("Error al cargar los regalos " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        OrganizadorDeRegalos manager = new OrganizadorDeRegalos();
        Scanner teclado = new Scanner(System.in);
        int eleccion ;

        do {
            System.out.println("\n--- Sistema de Gestión de Regalos ---" +
                    "\n1. Agregar Regalo" +
                    "\n2. Elimnar Regalo" +
                    "\n3. Mostrar lista de Regalos" +
                    "\n4. Calcular coste total" +
                    "\n5. Guardar y salir" +
                    "\nElija una opción");
            eleccion = teclado.nextInt();
            teclado.nextLine();
            switch (eleccion) {
                case 1:
                    System.out.println("Nombre del regalo: ");
                    String nombre = teclado.nextLine();
                    System.out.println("Receptor del regalo: ");
                    String recipient = teclado.nextLine();
                    System.out.println("Coste del regalo: ");
                    double coste = teclado.nextDouble();
                    manager.añadirRegalo(nombre, recipient, coste);
                    break;
                case 2:
                    System.out.println("Nombre del regalo a eliminar: ");
                    String nombreRegalo = teclado.nextLine();
                    manager.eliminarRegalo(nombreRegalo);
                    break;
                case 3:
                    manager.mostrarRegalos();
                    break;
                case 4:
                    manager.calcularCoste();
                    break;
                case 5:
                    manager.guardarRegalosToFile();
                    System.out.println("Adios");
                    break;
                default:
                        System.out.println("Opcion no valida. Intente de nuevo");
            }
        } while (eleccion != 5);

        teclado.close();
    }
}
