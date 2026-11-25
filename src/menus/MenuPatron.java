package menus;

import classes.Evaluador;
import classes.Patron;
import utils.Entrada;

import java.io.IOException;
import java.util.List;

public class MenuPatron {
  // ------------------- Atributos ------------------- //
  private Entrada entrada;
  private Patron patron;
  private Evaluador evaluador;

  // ------------------- Constructores ------------------- //
  public MenuPatron(Entrada entrada, Patron patron) {
    this.entrada = entrada;
    this.patron = patron;
    this.evaluador = new Evaluador(patron.getExpresion());
  }

  // ------------------- Flujo principal ------------------- //
  public void ejecutar() throws IOException {
    int opcion;
    do {
      mostrarMenu();
      opcion = entrada.leerOpcion("Opción: ", 0, 4);
      procesar(opcion);
    } while (opcion != 0);
  }

  private void mostrarMenu() {
    System.out.println("\n--- " + patron.getNombre() + " ---");
    System.out.println("1. Ver formato y expresión");
    System.out.println("2. Ver ejemplos válidos e inválidos");
    System.out.println("3. Demostrar los seis métodos");
    System.out.println("4. Probar mi propio texto");
    System.out.println("0. Volver");
  }

  private void procesar(int opcion) throws IOException {
    switch (opcion) {
      case 1:
        mostrarInformacion();
        break;
      case 2:
        mostrarEjemplos("Ejemplos válidos", patron.getValidas());
        mostrarEjemplos("Ejemplos inválidos", patron.getInvalidas());
        break;
      case 3:
        mostrarMetodos(patron.getTextoDemostracion(), patron.getReemplazo());
        break;
      case 4:
        probarTexto();
        break;
    }
  }

  // ------------------- Opciones ------------------- //
  private void mostrarInformacion() {
    System.out.println("\nFormato: " + patron.getFormato());
    System.out.println("Expresión: " + patron.getExpresion());
    System.out.println("Explicación: " + patron.getExplicacion());
  }

  private void mostrarEjemplos(String titulo, String[] ejemplos) {
    System.out.println("\n" + titulo + " (matches):");
    for (String ejemplo : ejemplos) {
      System.out.println("  \"" + ejemplo + "\" -> " + respuesta(evaluador.matches(ejemplo)));
    }
  }

  private void probarTexto() throws IOException {
    String texto = entrada.leerTexto("Texto a probar: ");
    String reemplazo = entrada.leerTexto("Cambiar lo encontrado por: ");
    mostrarMetodos(texto, reemplazo);
  }

  // ------------------- Resultados ------------------- //
  private void mostrarMetodos(String texto, String reemplazo) {
    System.out.println("\nTexto: \"" + texto + "\"");
    System.out.println("matches (¿todo el texto cumple?): " + respuesta(evaluador.matches(texto)));
    System.out.println("lookingAt (¿el comienzo cumple?): " + respuesta(evaluador.lookingAt(texto)));
    mostrarCoincidencias(evaluador.find(texto));
    System.out.println("replaceAll (cambia todas): " + evaluador.replaceAll(texto, reemplazo));
    System.out.println("replaceFirst (cambia la primera): " + evaluador.replaceFirst(texto, reemplazo));
    mostrarPartes(evaluador.split(texto));
  }

  private void mostrarCoincidencias(List<String> encontradas) {
    System.out.println("find (apariciones en el texto): " + encontradas.size());
    for (String coincidencia : encontradas) {
      System.out.println("  " + coincidencia);
    }
  }

  private void mostrarPartes(String[] partes) {
    System.out.println("split (partes que quedan al cortar): " + partes.length);
    for (String parte : partes) {
      System.out.println("  \"" + parte + "\"");
    }
  }

  private String respuesta(boolean valor) {
    return valor ? "Sí" : "No";
  }
}
