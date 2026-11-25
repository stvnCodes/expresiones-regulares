import classes.Evaluador;
import classes.Patron;
import patrones.Catalogo;

public class PruebaCatalogo {
  public static void main(String[] args) {
    Catalogo catalogo = new Catalogo();
    for (int i = 1; i <= catalogo.cantidad(); i++) {
      revisar(catalogo.obtener(i));
    }
    Verificacion.verificar("numero 0", falla(catalogo, 0));
    Verificacion.verificar("numero mayor", falla(catalogo, catalogo.cantidad() + 1));
    Verificacion.mostrarTotal("Catálogo");
  }

  private static void revisar(Patron patron) {
    Evaluador evaluador = new Evaluador(patron.getExpresion());
    Verificacion.verificar(patron.getNombre() + " completo", patron.getValidas().length > 0
        && patron.getInvalidas().length > 0 && !patron.getFormato().isEmpty() && !patron.getExplicacion().isEmpty());
    for (String valida : patron.getValidas()) {
      Verificacion.verificar(patron.getNombre() + ": " + valida, evaluador.matches(valida));
    }
    for (String invalida : patron.getInvalidas()) {
      Verificacion.verificar(patron.getNombre() + ": " + invalida, !evaluador.matches(invalida));
    }
    Verificacion.verificar(patron.getNombre() + " demostracion", evaluador.find(patron.getTextoDemostracion()).size() >= 2);
  }

  private static boolean falla(Catalogo catalogo, int numero) {
    try {
      catalogo.obtener(numero);
      return false;
    } catch (IllegalArgumentException error) {
      return true;
    }
  }
}
