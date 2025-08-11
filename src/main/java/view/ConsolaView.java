// src/main/java/view/ConsolaView.java

package view;

import model.Emotion; // Necesitamos importar la clase Emotion
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException; // Para manejar errores de tipo
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
        System.out.println("\n--- Mi Diario Interior ---");
        System.out.println("1. Añadir un nuevo momento");
        System.out.println("2. Ver todos los momentos");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        
        // Lee la opción y maneja posibles errores si no es un número
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada no válida. Por favor, introduce un número.");
            scanner.next(); // Consume la entrada no válida
            System.out.print("Elige una opción: ");
        }
        
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpia el buffer del scanner
        
        return opcion;
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
     * Solicita una fecha y la valida.
     * @return Un objeto LocalDate si la fecha es válida, o null si no.
     */
    public LocalDate solicitarFecha() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print("Introduce la fecha del suceso (dd/mm/yyyy): ");
            String fechaTexto = scanner.nextLine();
            try {
                return LocalDate.parse(fechaTexto, formatter);
            } catch (DateTimeParseException e) {
                mostrarMensajeError("Formato de fecha inválido. Inténtalo de nuevo.");
            }
        }
    }
    
    /**
     * Muestra las emociones disponibles y solicita una selección.
     * @return El objeto Emotion seleccionado por el usuario.
     */
    public Emotion solicitarEmocion() {
        System.out.println("Elige una emoción:");
        Emotion[] emociones = Emotion.values(); // Obtenemos las emociones del enum
        for (int i = 0; i < emociones.length; i++) {
            System.out.println((i + 1) + ". " + emociones[i].getName());
        }
        
        while (true) {
            System.out.print("Elige el número de la emoción: ");
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiamos el buffer
                if (opcion > 0 && opcion <= emociones.length) {
                    return emociones[opcion - 1];
                } else {
                    mostrarMensajeError("Opción no válida. Elige un número del 1 al " + emociones.length + ".");
                }
            } catch (InputMismatchException e) {
                mostrarMensajeError("Entrada no válida. Por favor, introduce un número.");
                scanner.nextLine(); // Limpiamos la entrada incorrecta
            }
        }
    }
}
