package patrones;

import classes.Patron;

public class Catalogo {
  // ------------------- Atributos ------------------- //
  private Patron[] patrones;

  // ------------------- Constructores ------------------- //
  // Siguen el orden del enunciado. ISBN, diptongos e hiatos tienen dos entradas; para agregar un patrón basta con incluirlo aquí
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
        PatronesProgramacion.variable(),
        PatronesWeb.url(),
        PatronesTexto.isbn13(),
        PatronesTexto.isbn10(),
        PatronesColombia.codigoBarras(),
        PatronesTexto.nombreCompleto(),
        PatronesWeb.contrasena(),
        PatronesProgramacion.cicloFor(),
        PatronesProgramacion.numeroDouble(),
        PatronesColombia.norma(),
        PatronesReferencias.ieee(),
        PatronesReferencias.apa(),
        PatronesColombia.precio(),
        PatronesTexto.diptongo(),
        PatronesTexto.hiato()
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
