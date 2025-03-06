import java.util.LinkedList;
import java.util.Queue;

class NodoBST {
    int asiento;
    String nombre;
    NodoBST izquierda, derecha;

    public NodoBST(int asiento, String nombre) {
        this.asiento = asiento;
        this.nombre = nombre;
        this.izquierda = this.derecha = null;
    }
}

class GestorBoletos {
    private NodoBST raiz;
    private Queue<NodoBST> colaReservas;

    public GestorBoletos() {
        this.raiz = null;
        this.colaReservas = new LinkedList<>();
    }

    // Método para registrar una nueva reservación
    public void registrarReserva(String nombre, int asiento) {
        if (verificarAsiento(asiento)) {
            System.out.println("Error: El asiento " + asiento + " ya está ocupado.");
            return;
        }
        raiz = insertarEnArbol(raiz, asiento, nombre);
        NodoBST nuevaReserva = new NodoBST(asiento, nombre);
        colaReservas.add(nuevaReserva);
        System.out.println("Reserva añadida: " + nombre + " en asiento " + asiento);
    }

    // Método auxiliar para insertar en el BST
    private NodoBST insertarEnArbol(NodoBST nodo, int asiento, String nombre) {
        if (nodo == null) {
            return new NodoBST(asiento, nombre);
        }
        if (asiento < nodo.asiento) {
            nodo.izquierda = insertarEnArbol(nodo.izquierda, asiento, nombre);
        } else if (asiento > nodo.asiento) {
            nodo.derecha = insertarEnArbol(nodo.derecha, asiento, nombre);
        }
        return nodo;
    }

    // Método para verificar si un asiento está ocupado
    public boolean verificarAsiento(int asiento) {
        return buscarEnArbol(raiz, asiento) != null;
    }

    // Método auxiliar para buscar en el BST
    private NodoBST buscarEnArbol(NodoBST nodo, int asiento) {
        if (nodo == null || nodo.asiento == asiento) {
            return nodo;
        }
        if (asiento < nodo.asiento) {
            return buscarEnArbol(nodo.izquierda, asiento);
        }
        return buscarEnArbol(nodo.derecha, asiento);
    }

    // Método para cancelar una reserva
    public void cancelarReserva(int asiento) {
        if (!verificarAsiento(asiento)) {
            System.out.println("Error: No existe una reserva para el asiento " + asiento);
            return;
        }
        raiz = eliminarEnArbol(raiz, asiento);
        colaReservas.removeIf(reserva -> reserva.asiento == asiento);
        System.out.println("Reserva eliminada en asiento " + asiento);
    }

    // Método auxiliar para eliminar en el BST
    private NodoBST eliminarEnArbol(NodoBST nodo, int asiento) {
        if (nodo == null) {
            return null;
        }
        if (asiento < nodo.asiento) {
            nodo.izquierda = eliminarEnArbol(nodo.izquierda, asiento);
        } else if (asiento > nodo.asiento) {
            nodo.derecha = eliminarEnArbol(nodo.derecha, asiento);
        } else {
            if (nodo.izquierda == null) {
                return nodo.derecha;
            } else if (nodo.derecha == null) {
                return nodo.izquierda;
            }
            NodoBST sucesor = encontrarMinimo(nodo.derecha);
            nodo.asiento = sucesor.asiento;
            nodo.nombre = sucesor.nombre;
            nodo.derecha = eliminarEnArbol(nodo.derecha, sucesor.asiento);
        }
        return nodo;
    }

    // Método auxiliar para encontrar el nodo mínimo en un BST
    private NodoBST encontrarMinimo(NodoBST nodo) {
        while (nodo.izquierda != null) {
            nodo = nodo.izquierda;
        }
        return nodo;
    }

    // Método para mostrar todas las reservaciones en orden de llegada
    public void mostrarReservaciones() {
        if (colaReservas.isEmpty()) {
            System.out.println("No hay reservaciones.");
            return;
        }
        System.out.println("Reservaciones en orden de llegada:");
        for (NodoBST reserva : colaReservas) {
            System.out.println(reserva.nombre + " en asiento " + reserva.asiento);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        GestorBoletos gestor = new GestorBoletos();
        
        // Prueba de registro de reservas
        gestor.registrarReserva("Carlos", 5);
        gestor.registrarReserva("Ana", 3);
        gestor.registrarReserva("Luis", 7);
        gestor.registrarReserva("Marta", 5); // Intento de reserva duplicada
        
        // Mostrar reservaciones
        gestor.mostrarReservaciones();
        
        // Verificación de asientos
        System.out.println("¿El asiento 3 está ocupado? " + gestor.verificarAsiento(3));
        System.out.println("¿El asiento 10 está ocupado? " + gestor.verificarAsiento(10));
        
        // Cancelación de reserva
        gestor.cancelarReserva(3);
        gestor.cancelarReserva(10); // Intento de cancelación inexistente
        
        // Mostrar reservaciones después de la cancelación
        gestor.mostrarReservaciones();
    }
}
