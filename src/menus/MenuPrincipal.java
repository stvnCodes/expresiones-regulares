package menus;

import patrones.Catalogo;
import utils.Entrada;

import java.io.IOException;

public class MenuPrincipal {
  // ------------------- Atributos ------------------- //
  private Entrada entrada;
  private Catalogo catalogo;

  // ------------------- Constructores ------------------- //
  public MenuPrincipal(Entrada entrada, Catalogo catalogo) {
    this.entrada = entrada;
    this.catalogo = catalogo;
  }

  // ------------------- Flujo principal ------------------- //
  public void ejecutar() throws IOException {
    int opcion;
    do {
      mostrarMenu();
      opcion = entrada.leerOpcion("Opción: ", 0, catalogo.cantidad());
      if (opcion != 0) {
        new MenuPatron(entrada, catalogo.obtener(opcion)).ejecutar();
      }
    } while (opcion != 0);
  }

  private void mostrarMenu() {
    System.out.println("\n--- Patrones ---");
    for (int i = 1; i <= catalogo.cantidad(); i++) {
      System.out.println(i + ". " + catalogo.obtener(i).getNombre());
    }
    System.out.println("0. Salir");
  }
}
