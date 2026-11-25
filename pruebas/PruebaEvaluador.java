import classes.Evaluador;

import java.util.Arrays;
import java.util.regex.Pattern;

public class PruebaEvaluador {
  public static void main(String[] args) {
    Evaluador numeros = new Evaluador(Pattern.compile("\\d+"));
    String texto = "hoy 25 del 2026";

    Verificacion.verificar("matches completo", numeros.matches("25"));
    Verificacion.verificar("matches parcial", !numeros.matches(texto) && !numeros.matches("25 "));
    Verificacion.verificar("lookingAt", numeros.lookingAt("25 hoy") && !numeros.lookingAt(texto));
    Verificacion.verificar("find", numeros.find(texto).toString()
        .equals("[\"25\" (desde 4 hasta 6), \"2026\" (desde 11 hasta 15)]"));
    Verificacion.verificar("replaceAll", numeros.replaceAll(texto, "#").equals("hoy # del #"));
    Verificacion.verificar("replaceFirst", numeros.replaceFirst(texto, "#").equals("hoy # del 2026"));
    Verificacion.verificar("split", Arrays.equals(numeros.split(texto), new String[] {"hoy ", " del "}));

    // Sin coincidencias, texto vacío y reemplazos con símbolos especiales
    Verificacion.verificar("find sin coincidencias", numeros.find("hola").isEmpty());
    Verificacion.verificar("replaceAll sin coincidencias", numeros.replaceAll("hola", "#").equals("hola"));
    Verificacion.verificar("split sin coincidencias", Arrays.equals(numeros.split("hola"), new String[] {"hola"}));
    Verificacion.verificar("split de solo el patron", numeros.split("25").length == 0);
    Verificacion.verificar("vacio", !numeros.matches("") && numeros.find("").isEmpty());
    Verificacion.verificar("reemplazo literal", numeros.replaceAll("a1", "$1\\").equals("a$1\\")
        && numeros.replaceFirst("a1", "$").equals("a$"));

    Verificacion.mostrarTotal("Evaluador");
  }
}
