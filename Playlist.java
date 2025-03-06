import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Clase Cancion: Representa una canción con título, artista y duración.
class Cancion {
    private String titulo;
    private String artista;
    private int duracion; // en segundos

    public Cancion(String titulo, String artista, int duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracion() {
        return duracion;
    }

    @Override
    public String toString() {
        return titulo + " - " + artista + " (" + duracion + "s)";
    }
}

// Clase PlaylistManager: Maneja la lista de reproducción con sus operaciones.
class PlaylistManager {
    private List<Cancion> canciones;

    public PlaylistManager() {
        this.canciones = new ArrayList<>();
    }

    // Método para agregar una canción a la playlist
    public void agregarCancion(String titulo, String artista, int duracion) {
        canciones.add(new Cancion(titulo, artista, duracion));
        System.out.println("Canción agregada: " + titulo);
    }

    // Método para eliminar una canción por título
    public void eliminarCancion(String titulo) {
        boolean encontrada = false;
        for (Cancion c : canciones) {
            if (c.getTitulo().equalsIgnoreCase(titulo)) {
                canciones.remove(c);
                System.out.println("Canción eliminada: " + titulo);
                encontrada = true;
                break;
            }
        }
        if (!encontrada) {
            System.out.println("La canción no se encontró en la playlist.");
        }
    }

    // Método para reproducir la siguiente canción (FIFO)
    public void reproducirSiguiente() {
        if (!canciones.isEmpty()) {
            Cancion siguiente = canciones.remove(0);
            System.out.println("Reproduciendo: " + siguiente);
        } else {
            System.out.println("La playlist está vacía.");
        }
    }

    // Método para ordenar las canciones por duración
    public void ordenarPorDuracion() {
        canciones.sort(Comparator.comparingInt(Cancion::getDuracion));
        System.out.println("Playlist ordenada por duración.");
    }

    // Método para ordenar las canciones por artista
    public void ordenarPorArtista() {
        canciones.sort(Comparator.comparing(Cancion::getArtista));
        System.out.println("Playlist ordenada por artista.");
    }

    // Método para mostrar todas las canciones en la playlist
    public void mostrarPlaylist() {
        if (canciones.isEmpty()) {
            System.out.println("La playlist está vacía.");
        } else {
            System.out.println("Playlist actual:");
            for (Cancion c : canciones) {
                System.out.println("- " + c);
            }
        }
    }
}

// Clase Principal: Prueba todas las funcionalidades de la playlist.
public class Playlist {
    public static void main(String[] args) {
        PlaylistManager miPlaylist = new PlaylistManager();

        // Agregar canciones a la playlist
        miPlaylist.agregarCancion("Bohemian Rhapsody", "Queen", 354);
        miPlaylist.agregarCancion("Imagine", "John Lennon", 183);
        miPlaylist.agregarCancion("Smells Like Teen Spirit", "Nirvana", 301);
        miPlaylist.agregarCancion("Hotel California", "Eagles", 390);

        // Mostrar playlist actual
        miPlaylist.mostrarPlaylist();

        // Eliminar una canción
        miPlaylist.eliminarCancion("Imagine");

        // Mostrar playlist después de eliminar una canción
        miPlaylist.mostrarPlaylist();

        // Reproducir la siguiente canción
        miPlaylist.reproducirSiguiente();

        // Mostrar playlist después de la reproducción
        miPlaylist.mostrarPlaylist();

        // Ordenar por duración y mostrar
        miPlaylist.ordenarPorDuracion();
        miPlaylist.mostrarPlaylist();

        // Ordenar por artista y mostrar
        miPlaylist.ordenarPorArtista();
        miPlaylist.mostrarPlaylist();
    }
}
