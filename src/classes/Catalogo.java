package classes;

import patrones.PatronesColombia;

public class Catalogo {
  // ------------------- Atributos ------------------- //
  private Patron[] patrones;

  // ------------------- Constructores ------------------- //
  // Para agregar un patrón basta con incluirlo aquí
  public Catalogo() {
    patrones = new Patron[] {
        PatronesColombia.placa()
    };
  }

  // ------------------- Consultas ------------------- //
  public int cantidad() {
    return patrones.length;
  }

  // Las opciones del menú empiezan en 1
  public Patron obtener(int numero) {
    if (numero < 1 || numero > patrones.length) {
      throw new IllegalArgumentException("No existe el patrón " + numero + ".");
    }
    return patrones[numero - 1];
  }
}
