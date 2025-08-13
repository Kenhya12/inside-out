package controller;

import model.Emotion;
import model.Momento;
import repository.DiarioRepository;
import view.ConsolaView;
import dto.MomentoDTO;
import mapper.MomentoMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MomentoController {

    private final DiarioRepository diarioRepository;
    private final ConsolaView consolaView;

    public MomentoController(DiarioRepository diarioRepository, ConsolaView consolaView) {
        this.diarioRepository = diarioRepository;
        this.consolaView = consolaView;
    }

    public void crearNuevoMomento() {
        String titulo = consolaView.solicitarTitulo();
        String descripcion = consolaView.solicitarDescripcion();
        LocalDateTime fecha = consolaView.solicitarFecha();
        Emotion emocion = consolaView.solicitarEmocion();

        Momento nuevoMomento = new Momento(titulo, descripcion, fecha, emocion);
        diarioRepository.addMomento(nuevoMomento);
        consolaView.mostrarMensajeExito("Momento añadido con éxito: " + nuevoMomento.getTitulo());
    }

    public void verTodosLosMomentos() {
        List<Momento> momentos = diarioRepository.getTodosLosMomentos();
        List<MomentoDTO> momentoDTOs = momentos.stream()
            .map(MomentoMapper::toDTO)
            .collect(Collectors.toList());
        consolaView.mostrarMomentos(momentoDTOs);
    }
    
    public void eliminarMomento() {
        this.verTodosLosMomentos();
        
        if (diarioRepository.getTodosLosMomentos().isEmpty()) {
            consolaView.mostrarMensajeError("No hay momentos para eliminar.");
            return;
        }

        int id = consolaView.solicitarId();
        
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
                case 2:
                    filtrarPorFecha();
                    break;
                case 3:
                    filtrarPorMesYAnio();
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
        List<MomentoDTO> momentoDTOs = momentosFiltrados.stream()
            .map(MomentoMapper::toDTO)
            .collect(Collectors.toList());
        consolaView.mostrarMomentos(momentoDTOs);
    }
    
    private void filtrarPorFecha() {
        LocalDateTime fecha = consolaView.solicitarFecha();
        List<Momento> momentosFiltrados = diarioRepository.getMomentosByFecha(fecha);
        List<MomentoDTO> momentoDTOs = momentosFiltrados.stream()
            .map(MomentoMapper::toDTO)
            .collect(Collectors.toList());
        consolaView.mostrarMomentos(momentoDTOs);
    }
    
    private void filtrarPorMesYAnio() {
        int mes = consolaView.solicitarMes();
        int anio = consolaView.solicitarAnio();
        List<Momento> momentosFiltrados = diarioRepository.getMomentosByMesAndAnio(mes, anio);
        List<MomentoDTO> momentoDTOs = momentosFiltrados.stream()
            .map(MomentoMapper::toDTO)
            .collect(Collectors.toList());
        consolaView.mostrarMomentos(momentoDTOs);
    }
}


