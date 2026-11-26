import patrones.PatronesReferencias;
import patrones.PatronesTexto;

public class PruebaTexto {
  public static void main(String[] args) {
    probarIsbn();
    probarNombres();
    probarSilabas();
    probarReferencias();
    Verificacion.mostrarTotal("Texto y libros");
  }

  private static void probarIsbn() {
    Verificacion.acepta(PatronesTexto.isbn13(), "9790000000000", "978-1-12-123456-1", "978-12345-123-1-1");
    Verificacion.rechaza(PatronesTexto.isbn13(), "", "97800000000000", "978-1-1-123456-1", "978-1234567-12345-1", "978-958-42-7689", "978-958-42-76896-");
    Verificacion.encuentra(PatronesTexto.isbn13(), 0, "x9789584276896 9789584276896-1");
    Verificacion.acepta(PatronesTexto.isbn10(), "000000000X", "1-1234567-1-1", "12345-123-1-X");
    Verificacion.rechaza(PatronesTexto.isbn10(), "", "000000000", "00000000000", "1-1-1234567-1", "9789584276896", "84-481-5638-2-");
    Verificacion.encuentra(PatronesTexto.isbn10(), 0, "9789584276896 978-958-42-7689-6");
  }

  private static void probarNombres() {
    Verificacion.acepta(PatronesTexto.nombreCompleto(), "Ña Ñu", "ÚRSULA IGUARÁN", "Ana del Valle", "Ana de los Ríos Ruiz", "Ana DE LA HOZ");
    Verificacion.rechaza(PatronesTexto.nombreCompleto(), "", "Ana ", " Ana Ruiz", "Ana-María Ruiz", "A Ruiz", "Ana de", "de Ana Ruiz",
        "Ana Ruiz.", "Anne O'Neil", "Ana dE Ruiz", "Ana y Ruiz");
    Verificacion.encuentra(PatronesTexto.nombreCompleto(), 0, "MaríaJosé y juan pérez");
  }

  private static void probarSilabas() {
    Verificacion.acepta(PatronesTexto.diptongo(), "peine", "deuda", "oigo", "tierra", "radio", "fuego", "antiguo", "vergüenza",
        "hoy", "muy", "ESTOY", "huir", "viuda", "cuota", "guante", "lingüística");
    Verificacion.rechaza(PatronesTexto.diptongo(), "", "guitarra", "quise", "seguir", "mayo", "hoyo", "país", "río", "oído", "baúl", "poeta");
    Verificacion.encuentra(PatronesTexto.diptongo(), 3, "Hoy el AGUA y la tierra, pero no el queso.");
    Verificacion.acepta(PatronesTexto.hiato(), "creer", "zoológico", "caer", "oído", "río", "Raúl", "frío", "reúne", "quería", "maestro", "ÓLEO");
    Verificacion.rechaza(PatronesTexto.hiato(), "", "ruido", "causa", "peine", "agua", "hoy", "ciudad");
    Verificacion.encuentra(PatronesTexto.hiato(), 2, "El poeta y el río.");
  }

  private static void probarReferencias() {
    Verificacion.acepta(PatronesReferencias.ieee(), "[12] A. Pérez et al., Redes. Bogotá, Colombia: Norma, 1999.",
        "A. Ruiz, B. Díaz, and C. Gómez, ¿Qué es Java?, 2.ª ed. Cali: Alfa, 2020.");
    Verificacion.rechaza(PatronesReferencias.ieee(), "", "A. Ruiz, Redes. Cali: Alfa, 20.", "A. Ruiz, Redes Cali: Alfa, 2020.",
        "A. Ruiz, Redes. Cali: Alfa, 2020", "A. Ruiz, Redes. Cali, Alfa, 2020.", "1] A. Ruiz, Redes. Cali: Alfa, 2020.");
    Verificacion.acepta(PatronesReferencias.apa(), "Ruiz, A. (s.f.). Redes. Alfa.", "Ruiz, A. B., & Díaz, C. (2020). ¿Qué es Java? (2.ª ed.). Alfa Omega.");
    Verificacion.rechaza(PatronesReferencias.apa(), "", "Ruiz, A. (2020). Redes. alfa.", "Ruiz A. (2020). Redes. Alfa.", "Ruiz, A. (20). Redes. Alfa.",
        "Ruiz, A. (2020). Redes (2 ed.). Alfa.", "Ruiz, A., Díaz, C. (2020). Redes. Alfa.");
  }
}
