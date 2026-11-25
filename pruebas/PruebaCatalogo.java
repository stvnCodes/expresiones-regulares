import classes.Catalogo;
import classes.Evaluador;
import classes.Patron;

public class PruebaCatalogo {
  private static int total = 0;

  public static void main(String[] args) {
    Catalogo catalogo = new Catalogo();
    for (int i = 1; i <= catalogo.cantidad(); i++) {
      revisar(catalogo.obtener(i));
    }
    verificar("numero 0", falla(catalogo, 0));
    verificar("numero mayor", falla(catalogo, catalogo.cantidad() + 1));
    System.out.println("Catálogo: " + total + " pruebas correctas");
  }

  private static void revisar(Patron patron) {
    Evaluador evaluador = new Evaluador(patron.getExpresion());
    verificar(patron.getNombre() + " completo", patron.getValidas().length > 0
        && patron.getInvalidas().length > 0 && !patron.getFormato().isEmpty() && !patron.getExplicacion().isEmpty());
    for (String valida : patron.getValidas()) {
      verificar(patron.getNombre() + ": " + valida, evaluador.matches(valida));
    }
    for (String invalida : patron.getInvalidas()) {
      verificar(patron.getNombre() + ": " + invalida, !evaluador.matches(invalida));
    }
    verificar(patron.getNombre() + " demostracion", evaluador.find(patron.getTextoDemostracion()).size() >= 2);
  }

  private static boolean falla(Catalogo catalogo, int numero) {
    try {
      catalogo.obtener(numero);
      return false;
    } catch (IllegalArgumentException error) {
      return true;
    }
  }

  private static void verificar(String nombre, boolean correcto) {
    if (!correcto) {
      throw new AssertionError("Falló: " + nombre);
    }
    total++;
  }
}
