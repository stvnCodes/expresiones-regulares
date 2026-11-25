package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Evaluador {
  // ------------------- Atributos ------------------- //
  private Pattern expresion;

  // ------------------- Constructores ------------------- //
  public Evaluador(Pattern expresion) {
    this.expresion = expresion;
  }

  // ------------------- Validación ------------------- //
  // Todo el texto debe cumplir
  public boolean matches(String texto) {
    return expresion.matcher(texto).matches();
  }

  // Basta con que cumpla el comienzo
  public boolean lookingAt(String texto) {
    return expresion.matcher(texto).lookingAt();
  }

  // ------------------- Búsqueda ------------------- //
  // Devuelve cada coincidencia con su posición; el final no se incluye
  public List<String> find(String texto) {
    List<String> encontradas = new ArrayList<>();
    Matcher matcher = expresion.matcher(texto);
    while (matcher.find()) {
      encontradas.add("\"" + matcher.group() + "\" (desde " + matcher.start() + " hasta " + matcher.end() + ")");
    }
    return encontradas;
  }

  // ------------------- Reemplazo y división ------------------- //
  // quoteReplacement evita que $ y \ se tomen como símbolos especiales
  public String replaceAll(String texto, String reemplazo) {
    return expresion.matcher(texto).replaceAll(Matcher.quoteReplacement(reemplazo));
  }

  public String replaceFirst(String texto, String reemplazo) {
    return expresion.matcher(texto).replaceFirst(Matcher.quoteReplacement(reemplazo));
  }

  public String[] split(String texto) {
    return expresion.split(texto);
  }
}
