import classes.Evaluador;

import java.util.Arrays;
import java.util.regex.Pattern;

public class PruebaEvaluador {
  private static int total = 0;

  public static void main(String[] args) {
    Evaluador numeros = new Evaluador(Pattern.compile("\\d+"));
    String texto = "hoy 25 del 2026";

    verificar("matches completo", numeros.matches("25"));
    verificar("matches parcial", !numeros.matches(texto) && !numeros.matches("25 "));
    verificar("lookingAt", numeros.lookingAt("25 hoy") && !numeros.lookingAt(texto));
    verificar("find", numeros.find(texto).toString()
        .equals("[\"25\" (desde 4 hasta 6), \"2026\" (desde 11 hasta 15)]"));
    verificar("replaceAll", numeros.replaceAll(texto, "#").equals("hoy # del #"));
    verificar("replaceFirst", numeros.replaceFirst(texto, "#").equals("hoy # del 2026"));
    verificar("split", Arrays.equals(numeros.split(texto), new String[] {"hoy ", " del "}));

    // Sin coincidencias, texto vacío y reemplazos con símbolos especiales
    verificar("find sin coincidencias", numeros.find("hola").isEmpty());
    verificar("replaceAll sin coincidencias", numeros.replaceAll("hola", "#").equals("hola"));
    verificar("split sin coincidencias", Arrays.equals(numeros.split("hola"), new String[] {"hola"}));
    verificar("split de solo el patron", numeros.split("25").length == 0);
    verificar("vacio", !numeros.matches("") && numeros.find("").isEmpty());
    verificar("reemplazo literal", numeros.replaceAll("a1", "$1\\").equals("a$1\\")
        && numeros.replaceFirst("a1", "$").equals("a$"));

    System.out.println("Evaluador: " + total + " pruebas correctas");
  }

  private static void verificar(String nombre, boolean correcto) {
    if (!correcto) {
      throw new AssertionError("Falló: " + nombre);
    }
    total++;
  }
}
