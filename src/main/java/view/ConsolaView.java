// src/main/java/view/ConsolaView.java

package view;

import model.Emotion; 
import dto.MomentoDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException; 
import java.util.List;
import java.util.Scanner;

public class ConsolaView {

    private final Scanner scanner;

    public ConsolaView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Muestra el menú principal y solicita una opción al usuario.
     * @return El número de la opción elegida por el usuario.
     */
    public int mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\n--- Mi Diario Interior ---");
            System.out.println("1. Añadir un nuevo momento");
            System.out.println("2. Ver todos los momentos");
            System.out.println("3. Eliminar un momento"); 
            System.out.println("4. Filtrar momentos"); 
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // <-- AÑADIDO: Limpia el buffer después de leer el número
                return opcion;
            } catch (InputMismatchException e) {
                mostrarMensajeError("Entrada no válida. Por favor, introduce un número.");
                scanner.nextLine(); // <-- Limpia el buffer si hay una excepción
            }
        }
    }
    
    /**
     * Muestra un mensaje de éxito al usuario.
     * @param mensaje El mensaje a mostrar.
     */
    public void mostrarMensajeExito(String mensaje) {
        System.out.println("✅ " + mensaje);
    }
    
    /**
     * Muestra un mensaje de error al usuario.
     * @param mensaje El mensaje a mostrar.
     */
    public void mostrarMensajeError(String mensaje) {
        System.out.println("❌ " + mensaje);
    }


    /**
     * Solicita y devuelve el título del momento.
     * @return El título introducido por el usuario.
     */
    public String solicitarTitulo() {
        System.out.print("Introduce el título del momento: ");
        return scanner.nextLine();
    }

    /**
     * Solicita y devuelve la descripción del momento.
     * @return La descripción introducida por el usuario.
     */
    public String solicitarDescripcion() {
        System.out.print("Introduce la descripción del momento: ");
        return scanner.nextLine();
    }
    

    /**
     * Solicita una fecha en formato dd/mm/yyyy.
     * @return La fecha ingresada como LocalDateTime.
     */
    public LocalDateTime solicitarFecha() {
        while (true) {
            System.out.print("Fecha del momento (dd/mm/yyyy): ");
            String fechaStr = scanner.nextLine();
            try {
                LocalDate fecha = LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return fecha.atStartOfDay(); // <-- Convertimos el LocalDate a LocalDateTime
            } catch (DateTimeParseException e) {
                mostrarMensajeError("Formato de fecha no válido. Usa dd/mm/yyyy.");
            }
        }
    }
    
    /**
     * Muestra las emociones disponibles y solicita una selección.
     * @return El objeto Emotion seleccionado por el usuario.
     */
    public Emotion solicitarEmocion() {
        System.out.println("Elige una emoción:");
        Emotion[] emociones = Emotion.values(); 
        for (int i = 0; i < emociones.length; i++) {
            System.out.println((i + 1) + ". " + emociones[i].getName());
        }
        
        while (true) {
            System.out.print("Elige el número de la emoción: ");
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // <-- AÑADIDO: Limpia el buffer
                if (opcion > 0 && opcion <= emociones.length) {
                    return emociones[opcion - 1];
                } else {
                    mostrarMensajeError("Opción no válida. Elige un número del 1 al " + emociones.length + ".");
                }
            } catch (InputMismatchException e) {
                mostrarMensajeError("Entrada no válida. Por favor, introduce un número.");
                scanner.nextLine(); // Limpia el buffer
            }
        }
    }

    /**
     * Muestra una lista de Momentos.
     * @param momentoDTOs La lista de DTOs a mostrar.
     */
    public void mostrarMomentos(List<MomentoDTO> momentoDTOs) {
        if (momentoDTOs.isEmpty()) {
            System.out.println("No se encontraron momentos.");
            return;
        }

        System.out.println("\n--- Momentos registrados ---");
        for (MomentoDTO dto : momentoDTOs) {
            System.out.println("ID: " + dto.getId());
            System.out.println("Título: " + dto.getTitulo());
            System.out.println("Descripción: " + dto.getDescripcion());
            System.out.println("Fecha: " + dto.getFecha());
            System.out.println("Emoción: " + dto.getEmocion());
            System.out.println("------------------------------------");
        }
    }

        /**
     * Solicita al usuario el ID del momento que desea eliminar.
     * @return El ID introducido por el usuario.
     */
    public int solicitarId() {
        while (true) {
            try {
                System.out.print("Introduce el ID del momento que deseas eliminar: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // <-- AÑADIDO: Limpia el buffer
                return id;
            } catch (InputMismatchException e) {
                mostrarMensajeError("Entrada no válida. Por favor, introduce un número.");
                scanner.nextLine(); // Limpia el buffer
            }
        }
    }

    /**
     * Muestra el menú de filtro y solicita una opción.
     * @return La opción elegida por el usuario.
     */
    public int mostrarMenuFiltro() {
        while (true) {
            System.out.println("\n--- Menú de Filtros ---");
            System.out.println("1. Filtrar por Emoción");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");
            
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // <-- AÑADIDO: Limpia el buffer
                return opcion;
            } catch (InputMismatchException e) {
                mostrarMensajeError("Entrada no válida. Por favor, introduce un número.");
                scanner.nextLine(); // Limpia el buffer
            }
        }
    }
    
    /**
     * Muestra la lista de emociones disponibles y solicita al usuario que elija una.
     * @return La emoción elegida por el usuario.
     */
    public Emotion solicitarEmocionFiltro() {
        System.out.println("Elige una emoción para filtrar:");
        Emotion[] emociones = Emotion.values();
        for (int i = 0; i < emociones.length; i++) {
            System.out.println((i + 1) + ". " + emociones[i].getName());
        }
        
        while (true) {
            System.out.print("Elige el número de la emoción: ");
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpia el buffer
                if (opcion > 0 && opcion <= emociones.length) {
                    return emociones[opcion - 1];
                } else {
                    mostrarMensajeError("Opción no válida. Elige un número del 1 al " + emociones.length + ".");
                }
            } catch (InputMismatchException e) {
                mostrarMensajeError("Entrada no válida. Por favor, introduce un número.");
                scanner.nextLine(); // Limpia el buffer
            }
        }
    }


    public int solicitarMes() {
        while(true) {
            try {
                System.out.print("Mes (1-12): ");
                int mes = Integer.parseInt(scanner.nextLine());
                if (mes >= 1 && mes <= 12) {
                    return mes;
                } else {
                    mostrarMensajeError("Mes no válido. Por favor, ingresa un número entre 1 y 12.");
                }
            } catch (NumberFormatException e) {
                mostrarMensajeError("Entrada no válida. Por favor, ingresa un número.");
            }
        }
    }

    public int solicitarAnio() {
        while(true) {
            try {
                System.out.print("Año: ");
                int anio = Integer.parseInt(scanner.nextLine());
                if (anio > 0) {
                    return anio;
                } else {
                    mostrarMensajeError("Año no válido. Por favor, ingresa un número positivo.");
                }
            } catch (NumberFormatException e) {
                mostrarMensajeError("Entrada no válida. Por favor, ingresa un número.");
            }
        }
    }
}