import java.util.Stack;

// Clase que representa el Diario de Aventuras usando una Pila (Stack)
public class DiarioAventurero {
    private Stack<String> misiones;

    // Constructor
    public DiarioAventurero() {
        misiones = new Stack<>();
    }

    // Método para registrar una nueva misión completada
    public void registrarMision(String mision) {
        misiones.push(mision);
        System.out.println("Misión registrada: " + mision);
    }

    // Método para eliminar la última misión si fue fallida
    public void eliminarUltimaMision() {
        if (!misiones.isEmpty()) {
            System.out.println("Eliminando última misión: " + misiones.pop());
        } else {
            System.out.println("No hay misiones para eliminar.");
        }
    }

    // Método para mostrar todas las misiones en orden de finalización
    public void mostrarMisiones() {
        if (misiones.isEmpty()) {
            System.out.println("No hay misiones registradas.");
        } else {
            System.out.println("Misiones completadas en orden de finalización:");
            for (int i = misiones.size() - 1; i >= 0; i--) {
                System.out.println("- " + misiones.get(i));
            }
        }
    }

    // Método para buscar si una misión específica ya fue completada
    public boolean buscarMision(String mision) {
        return misiones.contains(mision);
    }

    // Método principal para probar el DiarioAventurero
    public static void main(String[] args) {
        DiarioAventurero diario = new DiarioAventurero();

        // Registrar misiones
        diario.registrarMision("Explorar el Bosque Oscuro");
        diario.registrarMision("Rescatar a la princesa");
        diario.registrarMision("Derrotar al dragón");

        // Mostrar misiones registradas
        diario.mostrarMisiones();

        // Buscar si una misión específica fue completada
        System.out.println("¿Se completó 'Rescatar a la princesa'?: " + diario.buscarMision("Rescatar a la princesa"));

        // Eliminar la última misión y volver a mostrar la lista
        diario.eliminarUltimaMision();
        diario.mostrarMisiones();

        // Buscar nuevamente después de eliminar
        System.out.println("¿Se completó 'Derrotar al dragón'?: " + diario.buscarMision("Derrotar al dragón"));
    }
}
