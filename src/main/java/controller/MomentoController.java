package controller;

import model.Emotion;
import model.Momento;
import repository.DiarioRepository;
import view.ConsolaView;
import java.util.Optional;

import java.time.LocalDate;
import java.util.List; 

public class MomentoController {

    // Dependencias: el controlador necesita una instancia de la Vista y el Repositorio (Modelo).
    private final DiarioRepository diarioRepository;
    private final ConsolaView consolaView;

    /**
     * Constructor del controlador.
     * Recibe las dependencias a través de inyección.
     */
    public MomentoController(DiarioRepository diarioRepository, ConsolaView consolaView) {
        this.diarioRepository = diarioRepository;
        this.consolaView = consolaView;
    }

    /**
     * Coordina la creación de un nuevo momento.
     * 1. Solicita los datos al usuario a través de la Vista.
     * 2. Crea el objeto Momento.
     * 3. Lo guarda en el repositorio.
     * 4. Muestra un mensaje de confirmación al usuario.
     */
    public void crearNuevoMomento() {
        // 1. La Vista solicita los datos al usuario
        String titulo = consolaView.solicitarTitulo();
        String descripcion = consolaView.solicitarDescripcion();
        LocalDate fechaSuceso = consolaView.solicitarFecha();
        Emotion emocion = consolaView.solicitarEmocion();

        // 2. El Controlador crea un nuevo objeto Momento con los datos
        Momento nuevoMomento = new Momento(titulo, descripcion, emocion, fechaSuceso);

        // 3. El Controlador utiliza el Repositorio (Modelo) para guardar el objeto
        diarioRepository.addMomento(nuevoMomento);

        // 4. La Vista muestra un mensaje de éxito al usuario
        consolaView.mostrarMensajeExito("Momento vivido añadido correctamente.");
    } 
    /**
     * Coordina la visualización de todos los momentos.
     * 1. Solicita al Repositorio la lista de momentos.
     * 2. Le pasa esa lista a la Vista para que la muestre.
     */
    public void verTodosLosMomentos() {
        // El Controlador pide la lista de momentos al Repositorio (Modelo)
        List<Momento> momentos = diarioRepository.getTodosLosMomentos();

        // El Controlador le pasa la lista a la Vista para que la muestre
        consolaView.mostrarMomentos(momentos);
    }

        public void eliminarMomento() {
        // 1. Mostrar todos los momentos para que el usuario elija
        this.verTodosLosMomentos();
        
        // Si no hay momentos, no podemos eliminar nada
        if (diarioRepository.getTodosLosMomentos().isEmpty()) {
            return;
        }

        // 2. Solicitar el ID del momento a eliminar
        int id = consolaView.solicitarId();
        
        // 3. Eliminar el momento y mostrar el resultado
        boolean eliminado = diarioRepository.eliminarMomento(id);
        if (eliminado) {
            consolaView.mostrarMensajeExito("Momento con ID " + id + " eliminado correctamente.");
        } else {
            consolaView.mostrarMensajeError("No se encontró un momento con el ID " + id + ".");
        }
    }

    public void filtrarMomentos() {
        int opcionFiltro;
        do {
            opcionFiltro = consolaView.mostrarMenuFiltro();
            
            switch (opcionFiltro) {
                case 1:
                    filtrarPorEmocion();
                    break;
                case 0:
                    consolaView.mostrarMensajeExito("Volviendo al menú principal.");
                    break;
                default:
                    consolaView.mostrarMensajeError("Opción no válida.");
                    break;
            }
        } while (opcionFiltro != 0);
    }
    
    private void filtrarPorEmocion() {
        Emotion emocion = consolaView.solicitarEmocionFiltro();
        List<Momento> momentosFiltrados = diarioRepository.getMomentosByEmocion(emocion);
        consolaView.mostrarMomentos(momentosFiltrados);
    }
}
