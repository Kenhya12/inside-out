package controller;

import model.Emotion;
import model.Momento;
import repository.DiarioRepository;
import view.ConsolaView;

import java.time.LocalDate;


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
}