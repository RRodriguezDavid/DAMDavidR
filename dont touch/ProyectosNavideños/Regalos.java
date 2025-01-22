package ProyectosNavideños;

public class Regalos {
    private String nombre;
    private String recipient;
    private double coste;

    public Regalos(String nombre, String recipient, double coste) {
        this.nombre = nombre;
        this.recipient = recipient;
        this.coste = coste;
    }
    public String getNombre() {
        return nombre;
    }
    public String getRecipient() {
        return recipient;
    }

    public double getCoste() {
        return coste;
    }

    public String toString() {
        return "Regalo: " + nombre + ". Receptor: " + recipient + ". Coste: " + coste;
    }
}
