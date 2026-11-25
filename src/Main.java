import menus.MenuPrincipal;
import patrones.Catalogo;
import utils.Entrada;

import java.io.EOFException;
import java.io.IOException;

public class Main {
  // ------------------- Punto de entrada ------------------- //
  public static void main(String[] args) {
    System.out.println("Práctica de expresiones regulares");
    try {
      new MenuPrincipal(new Entrada(), new Catalogo()).ejecutar();
    } catch (EOFException error) {
      System.out.println("\nFin de la entrada.");
    } catch (IOException error) {
      System.out.println("\nNo se pudo leer la entrada: " + error.getMessage());
    }
    System.out.println("Aplicación finalizada.");
  }
}
