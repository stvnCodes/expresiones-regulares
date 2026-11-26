import classes.Patron;
import patrones.PatronesColombia;
import patrones.PatronesFechas;
import patrones.PatronesProgramacion;
import patrones.PatronesReferencias;
import patrones.PatronesTexto;
import patrones.PatronesWeb;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Escribe en HTML la sección de desarrollo con los resultados reales de cada patrón
public class GenerarDesarrollo {
  private static StringBuilder html = new StringBuilder();

  public static void main(String[] args) {
    seccion("Placas de carro en Colombia", PatronesColombia.placa());
    seccion("Códigos postales para Medellín", PatronesColombia.codigoPostal());
    seccion("Números de teléfonos fijos en Medellín", PatronesColombia.telefonoFijo());
    seccion("Números de teléfonos celulares en Colombia", PatronesColombia.celular());
    seccion("Direcciones de correo electrónico", PatronesWeb.correo());
    seccion("Fecha en formato dd/mm/aaaa", PatronesFechas.fecha());
    seccion("Hora en formato de 24 horas", PatronesFechas.hora24());
    seccion("Hora en formato de 12 horas", PatronesFechas.hora12());
    seccion("Declaración de variables en Java", PatronesProgramacion.variable());
    seccion("URL", PatronesWeb.url());
    seccion("Código ISBN", PatronesTexto.isbn13(), PatronesTexto.isbn10());
    seccion("Código de barras en Colombia", PatronesColombia.codigoBarras());
    seccion("Nombres y apellidos con tildes y ñ", PatronesTexto.nombreCompleto());
    seccion("Contraseña segura", PatronesWeb.contrasena());
    seccion("Estructura de un ciclo for", PatronesProgramacion.cicloFor());
    seccion("Número double", PatronesProgramacion.numeroDouble());
    seccion("Leyes, decretos y artículos en Colombia", PatronesColombia.norma());
    seccion("Referencias de libros impresos en IEEE", PatronesReferencias.ieee());
    seccion("Referencias de libros impresos en APA", PatronesReferencias.apa());
    seccion("Precios de productos", PatronesColombia.precio());
    seccion("Diptongos e hiatos", PatronesTexto.diptongo(), PatronesTexto.hiato());
    System.out.print(html);
  }

  private static int numero = 0;

  private static void seccion(String titulo, Patron... patrones) {
    numero++;
    html.append("<h3>2.").append(numero).append(". ").append(titulo).append("</h3>\n");
    for (Patron patron : patrones) {
      if (patrones.length > 1) {
        html.append("<h4>").append(escapar(patron.getNombre())).append("</h4>\n");
      }
      describir(patron);
      mostrarEjemplos(patron);
      mostrarCodigo(patron);
    }
  }

  private static void describir(Patron patron) {
    html.append("<p><b>Formato esperado.</b> ").append(escapar(patron.getFormato())).append("</p>\n");
    html.append("<pre class=\"regex\">").append(escapar(patron.getExpresion().pattern())).append("</pre>\n");
    html.append("<p>").append(escapar(patron.getExplicacion())).append("</p>\n");
  }

  private static void mostrarEjemplos(Patron patron) {
    html.append("<table class=\"ejemplos\"><tr><th>Válidas: matches() = true</th>")
        .append("<th>Inválidas: matches() = false</th></tr>\n<tr><td>");
    agregarCadenas(patron, patron.getValidas(), true);
    html.append("</td><td>");
    agregarCadenas(patron, patron.getInvalidas(), false);
    html.append("</td></tr></table>\n");
  }

  // Solo se muestran si el resultado real coincide con lo esperado
  private static void agregarCadenas(Patron patron, String[] cadenas, boolean esperado) {
    for (String cadena : cadenas) {
      if (patron.getExpresion().matcher(cadena).matches() != esperado) {
        throw new IllegalStateException("Ejemplo incorrecto: " + cadena);
      }
      html.append("<code>").append(escapar(cadena)).append("</code><br>");
    }
  }

  // El código mostrado es el mismo que se ejecuta para obtener cada resultado
  private static void mostrarCodigo(Patron patron) {
    Pattern p = patron.getExpresion();
    String texto = patron.getTextoDemostracion();
    String reemplazo = patron.getReemplazo();
    String primera = patron.getValidas()[0];
    Matcher buscador = p.matcher(texto);
    StringBuilder encontradas = new StringBuilder();
    while (buscador.find()) {
      encontradas.append("\n//   \"").append(buscador.group()).append("\" (").append(buscador.start())
          .append("-").append(buscador.end()).append(")");
    }
    String codigo = "Pattern patron = Pattern.compile(" + literal(p.pattern()) + ");\n"
        + "String texto = " + literal(texto) + ";\n\n"
        + linea("patron.matcher(" + literal(primera) + ").matches();", p.matcher(primera).matches())
        + linea("patron.matcher(texto).matches();", p.matcher(texto).matches())
        + linea("patron.matcher(texto).lookingAt();", p.matcher(texto).lookingAt())
        + "Matcher m = patron.matcher(texto);\nwhile (m.find()) { ... }" + encontradas + "\n"
        + linea("patron.matcher(texto).replaceAll(" + literal(reemplazo) + ");", p.matcher(texto).replaceAll(reemplazo))
        + linea("patron.matcher(texto).replaceFirst(" + literal(reemplazo) + ");", p.matcher(texto).replaceFirst(reemplazo))
        + linea("patron.split(texto);", Arrays.toString(Arrays.stream(p.split(texto)).map(s -> "\"" + s + "\"").toArray()));
    html.append("<div class=\"codigo\"><p><b>Código y resultados.</b></p>\n<pre>").append(escapar(codigo)).append("</pre></div>\n");
  }

  // El resultado va al lado si cabe; si no, en la línea siguiente
  private static String linea(String instruccion, Object resultado) {
    String separador = instruccion.length() + String.valueOf(resultado).length() > 80 ? "\n" : " ";
    return instruccion + separador + "// " + resultado + "\n";
  }

  private static String literal(String texto) {
    return "\"" + texto.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
  }

  private static String escapar(String texto) {
    return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
  }
}
