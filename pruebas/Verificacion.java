import classes.Evaluador;
import classes.Patron;

public class Verificacion {
  private static int total = 0;

  public static void verificar(String nombre, boolean correcto) {
    if (!correcto) {
      throw new AssertionError("Falló: " + nombre);
    }
    total++;
  }

  public static void acepta(Patron patron, String... textos) {
    for (String texto : textos) {
      verificar(patron.getNombre() + " acepta " + texto, new Evaluador(patron.getExpresion()).matches(texto));
    }
  }

  public static void rechaza(Patron patron, String... textos) {
    for (String texto : textos) {
      verificar(patron.getNombre() + " rechaza " + texto, !new Evaluador(patron.getExpresion()).matches(texto));
    }
  }

  // Cuántas veces aparece dentro de un texto más largo
  public static void encuentra(Patron patron, int cantidad, String texto) {
    int encontradas = new Evaluador(patron.getExpresion()).find(texto).size();
    verificar(patron.getNombre() + " encuentra " + encontradas + " en " + texto, encontradas == cantidad);
  }

  public static void mostrarTotal(String nombre) {
    System.out.println(nombre + ": " + total + " pruebas correctas");
  }
}
