// src/main/java/Main.java

import controller.MomentoController;
import repository.DiarioEnMemoria;
import repository.DiarioRepository;
import view.ConsolaView;

public class Main {
    public static void main(String[] args) {
        
        // 1. Inicialización de la arquitectura MVC
        
        // Creamos la instancia del Repositorio (la implementación del Modelo)
        DiarioRepository diarioRepository = new DiarioEnMemoria();
        
        // Creamos la instancia de la Vista
        ConsolaView consolaView = new ConsolaView();
        
        // Creamos la instancia del Controlador, inyectando las dependencias
        MomentoController momentoController = new MomentoController(diarioRepository, consolaView);
        
        // 2. Bucle principal de la aplicación
        int opcion;
        do {
            opcion = consolaView.mostrarMenuPrincipal();
            
            switch (opcion) {
                case 1:
                    momentoController.crearNuevoMomento();
                    break;
                case 2:
                    // Aquí podríamos implementar la lógica para ver todos los momentos
                    momentoController.verTodosLosMomentos();
                    break;
                case 3: // <-- Caso para eliminar!
                    momentoController.eliminarMomento();
                    break;
                case 4: // <-- Caso para filtar!
                    momentoController.filtrarMomentos();
                    break;
                case 0:
                    consolaView.mostrarMensajeExito("Saliendo de la aplicación. ¡Hasta pronto!");
                    break;
                default:
                    consolaView.mostrarMensajeError("Opción no válida. Por favor, elige una opción del menú.");
            }
        } while (opcion != 0);
    }
}