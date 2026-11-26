package patrones;

import classes.Patron;

public class PatronesReferencias {
  // ------------------- Piezas comunes ------------------- //
  private static final String APELLIDO = "\\p{Lu}[\\p{L}'-]+( \\p{Lu}[\\p{L}'-]+)*";
  private static final String INICIALES = "\\p{Lu}\\.( \\p{Lu}\\.)*";
  private static final String TITULO = "[¿¡]?\\p{Lu}";
  private static final String EDICION = "\\d+(\\.ª|st|nd|rd|th) ed\\.";
  private static final String ANIO = "(1[5-9]|20)\\d{2}";

  // ------------------- IEEE ------------------- //
  public static Patron ieee() {
    String autor = INICIALES + " " + APELLIDO;
    Patron patron = new Patron("Referencias de libros impresos en IEEE",
        "(\\[\\d+\\] )?" + autor + "((, " + autor + ")*,? (and|y) " + autor + "| et al\\.)?, "
            + TITULO + "[^.]*?(, " + EDICION + "|\\.) "
            + "\\p{Lu}[\\p{L} ]*(, \\p{Lu}[\\p{L} ]*)*: \\p{Lu}[^,]*, " + ANIO + "\\.");
    patron.setFormato("[n] Iniciales Apellido, Título, edición opcional. Ciudad, País: Editorial, año.");
    patron.setExplicacion("El número [n] es opcional. Los autores llevan las iniciales antes del apellido y el último "
        + "va después de and o y. El título termina en punto, o en coma si sigue la edición. "
        + "Luego van la ciudad y el país, dos puntos, la editorial y el año.");
    patron.setValidas("[1] B. Klaus and P. Horn, Robot Vision. Cambridge, MA, USA: MIT Press, 1986.",
        "L. Joyanes Aguilar, Fundamentos de programación, 4.ª ed. Madrid, España: McGraw-Hill, 2008.",
        "R. C. Gonzalez y R. E. Woods, Digital Image Processing, 4th ed. New York, NY, USA: Pearson, 2018.");
    patron.setInvalidas("Luis Joyanes Aguilar, Fundamentos de programación. Madrid, España: McGraw-Hill, 2008.",
        "L. Joyanes Aguilar, Fundamentos de programación. Madrid, España: McGraw-Hill.",
        "L. Joyanes Aguilar, Fundamentos de programación. McGraw-Hill, 2008.",
        "Joyanes Aguilar, L. (2008). Fundamentos de programación. McGraw-Hill.");
    patron.setDemostracion("Fuentes: [1] B. Klaus and P. Horn, Robot Vision. Cambridge, MA, USA: MIT Press, 1986. "
        + "[2] L. Joyanes Aguilar, Fundamentos de programación, 4.ª ed. Madrid, España: McGraw-Hill, 2008.", "[IEEE]");
    return patron;
  }

  // ------------------- APA ------------------- //
  public static Patron apa() {
    String autor = APELLIDO + ", " + INICIALES;
    Patron patron = new Patron("Referencias de libros impresos en APA",
        autor + "((, " + autor + ")*,? (&|y) " + autor + ")? \\((" + ANIO + "|s\\.f\\.)\\)\\. "
            + TITULO + "[^.()]*( \\(" + EDICION + "\\))?\\. \\p{Lu}[\\p{L}\\d&' -]*\\.");
    patron.setFormato("Apellido, Iniciales. (año). Título (edición opcional). Editorial.");
    patron.setExplicacion("Los autores llevan el apellido antes de las iniciales y el último va después de & o y. "
        + "El año va entre paréntesis, o s.f. si no tiene fecha. La edición va entre paréntesis después del título "
        + "y al final está la editorial, sin ciudad, como en la séptima edición de APA.");
    patron.setValidas("Joyanes Aguilar, L. (2008). Fundamentos de programación (4.ª ed.). McGraw-Hill.",
        "Deitel, P., & Deitel, H. (2016). Cómo programar en Java (10.ª ed.). Pearson Educación.",
        "Cormen, T. H., Leiserson, C. E., Rivest, R. L., y Stein, C. (2009). Introduction to algorithms. MIT Press.");
    patron.setInvalidas("Joyanes Aguilar, L. 2008. Fundamentos de programación. McGraw-Hill.",
        "Joyanes Aguilar, L. (2008) Fundamentos de programación. McGraw-Hill.",
        "L. Joyanes Aguilar (2008). Fundamentos de programación. McGraw-Hill.",
        "Joyanes Aguilar, L. (2008). Fundamentos de programación.");
    patron.setDemostracion("Libros: Joyanes Aguilar, L. (2008). Fundamentos de programación (4.ª ed.). McGraw-Hill. "
        + "y también Deitel, P., & Deitel, H. (2016). Cómo programar en Java. Pearson Educación.", "[APA]");
    return patron;
  }
}
