package utils;

import java.io.BufferedReader;
import java.io.Console;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Entrada {
  // ------------------- Atributos ------------------- //
  private static final int MAXIMO_CARACTERES = 500;

  private BufferedReader lector;

  // ------------------- Constructores ------------------- //
  public Entrada() {
    lector = new BufferedReader(new InputStreamReader(System.in, codificacion()));
  }

  // ------------------- Lectura y validación ------------------- //
  public int leerOpcion(String mensaje, int minimo, int maximo) throws IOException {
    while (true) {
      try {
        int opcion = Integer.parseInt(leerLinea(mensaje).trim());
        if (opcion >= minimo && opcion <= maximo) {
          return opcion;
        }
        System.out.println("Elija una opción entre " + minimo + " y " + maximo + ".");
      } catch (NumberFormatException error) {
        System.out.println("Ingrese un número entero válido.");
      }
    }
  }

  // No se quitan los espacios porque pueden cambiar el resultado
  public String leerTexto(String mensaje) throws IOException {
    while (true) {
      String texto = leerLinea(mensaje);
      if (texto.isBlank()) {
        System.out.println("El texto no puede estar vacío.");
      } else if (texto.length() > MAXIMO_CARACTERES) {
        System.out.println("El texto no puede pasar de " + MAXIMO_CARACTERES + " caracteres.");
      } else {
        return texto;
      }
    }
  }

  // ------------------- Apoyo interno ------------------- //
  private String leerLinea(String mensaje) throws IOException {
    System.out.print(mensaje);
    String linea = lector.readLine();
    if (linea == null) {
      throw new EOFException();
    }
    return linea;
  }

  // Usa la codificación de la terminal para leer bien tildes y ñ
  private Charset codificacion() {
    Console consola = System.console();
    return consola == null ? Charset.defaultCharset() : consola.charset();
  }
}
