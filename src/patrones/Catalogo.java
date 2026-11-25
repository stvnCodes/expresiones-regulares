package patrones;

import classes.Patron;

public class Catalogo {
  // ------------------- Atributos ------------------- //
  private Patron[] patrones;

  // ------------------- Constructores ------------------- //
  // Siguen el orden del enunciado; para agregar un patrón basta con incluirlo aquí
  public Catalogo() {
    patrones = new Patron[] {
        PatronesColombia.placa(),
        PatronesColombia.codigoPostal(),
        PatronesColombia.telefonoFijo(),
        PatronesColombia.celular(),
        PatronesWeb.correo(),
        PatronesFechas.fecha(),
        PatronesFechas.hora24(),
        PatronesFechas.hora12(),
        PatronesWeb.url(),
        PatronesColombia.codigoBarras(),
        PatronesWeb.contrasena(),
        PatronesColombia.norma(),
        PatronesColombia.precio()
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
