package patrones;

import classes.Patron;

public class PatronesTexto {
  // ------------------- Piezas comunes ------------------- //
  private static final String PALABRA = "([A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+|[A-ZÁÉÍÓÚÜÑ]{2,})";
  private static final String CONECTOR = "(de|del|la|las|los|DE|DEL|LA|LAS|LOS)";
  private static final String ABIERTA = "[aeoáéó]";
  private static final String DIPTONGO = ABIERTA + "[iu]|[aeou]y(?![aeiouáéíóú])|[iü]" + ABIERTA
      + "|(?<!q)u[aoáó]|(?<![qg])u[eé]|(?<![qg])ui|iu|ü[ií]";
  private static final String HIATO = ABIERTA + ABIERTA + "|" + ABIERTA + "[íú]|[íú]" + ABIERTA;

  // ------------------- Libros ------------------- //
  public static Patron isbn13() {
    Patron patron = new Patron("Código ISBN-13",
        "(?<![\\w-])(97[89]\\d{10}|(?=[\\d-]{17}(?![\\w-]))97[89]-\\d{1,5}-\\d{1,7}-\\d{1,6}-\\d)(?![\\w-])");
    patron.setFormato("ISBN-13: trece dígitos que empiezan en 978 o 979, seguidos o en cinco grupos separados por guion.");
    patron.setExplicacion("La primera opción son los trece dígitos seguidos. En la segunda, (?=[\\d-]{17}...) exige "
        + "diecisiete caracteres: trece dígitos y cuatro guiones. El dígito de control no se calcula.");
    patron.setValidas("9789584276896", "978-958-42-7689-6", "979-10-90636-07-1");
    patron.setInvalidas("9771234567890", "978958427689", "978-958-427-6896", "978--958-42-768-96", "978 958 42 7689 6");
    patron.setDemostracion("Los libros 978-958-42-7689-6 y 9780134685991 están en la biblioteca", "[ISBN]");
    return patron;
  }

  public static Patron isbn10() {
    Patron patron = new Patron("Código ISBN-10",
        "(?<![\\w-])(\\d{9}[\\dX]|(?=[\\d-]{12}[\\dX](?![\\w-]))\\d{1,5}-\\d{1,7}-\\d{1,6}-[\\dX])(?![\\w-])");
    patron.setFormato("ISBN-10: diez caracteres, o cuatro grupos separados por guion. El último puede ser X.");
    patron.setExplicacion("Nueve dígitos y un último carácter que es dígito o X, que vale 10. Con guiones, "
        + "(?=...) exige trece caracteres: diez y tres guiones. El dígito de control no se calcula.");
    patron.setValidas("8448156382", "84-481-5638-2", "0-306-40615-X");
    patron.setInvalidas("844815638", "84481563821", "84-481-56382", "0-306-40615-x", "X-306-40615-2");
    patron.setDemostracion("Edición antigua 84-481-5638-2 y otra 030640615X", "[ISBN]");
    return patron;
  }

  // ------------------- Personas ------------------- //
  public static Patron nombreCompleto() {
    Patron patron = new Patron("Nombres y apellidos",
        "(?<!\\p{L})" + PALABRA + "( (" + CONECTOR + " )*" + PALABRA + ")+(?!\\p{L})");
    patron.setFormato("Dos palabras o más, con mayúscula inicial o todo en mayúsculas. "
        + "Admite tildes, ü, ñ y conectores como de o del.");
    patron.setExplicacion("Cada palabra empieza en mayúscula y sigue en minúsculas, o va toda en mayúsculas. "
        + "( (conector )*palabra)+ exige al menos otra palabra, separada por un solo espacio.");
    patron.setValidas("Juan Estiven Posada Rúa", "María José Peña", "Ángela de la Hoz", "JOSÉ GÜELL");
    patron.setInvalidas("Juan", "juan posada", "Juan  Posada", "JuAn Posada", "Juan Posada3", "Juan de");
    patron.setDemostracion("Asistieron: Juan Estiven Posada Rúa, Ángela de la Hoz.", "[NOMBRE]");
    return patron;
  }

  // ------------------- Sílabas ------------------- //
  public static Patron diptongo() {
    Patron patron = new Patron("Diptongos",
        "(?iu)(?<!\\p{L})\\p{L}*(" + DIPTONGO + ")\\p{L}*(?!\\p{L})");
    patron.setFormato("Palabra con dos vocales seguidas en la misma sílaba: abierta y cerrada sin tilde, o dos cerradas distintas.");
    patron.setExplicacion("Las abiertas son a, e, o y las cerradas i, u (y final, como en rey). Las opciones cubren "
        + "abierta + cerrada, cerrada + abierta y cerrada + cerrada. La u de que, qui, gue y gui no suena, por eso "
        + "(?<![qg]) la descarta. (?iu) ignora mayúsculas y \\p{L}* completa la palabra.");
    patron.setValidas("aire", "causa", "piano", "ciudad", "cuidado", "agua", "pingüino", "rey", "DESPUÉS");
    patron.setInvalidas("queso", "guerra", "quiso", "casa", "día", "león", "aire libre");
    patron.setDemostracion("El agua de la ciudad llega por el puente", "[DIPTONGO]");
    return patron;
  }

  public static Patron hiato() {
    Patron patron = new Patron("Hiatos",
        "(?iu)(?<!\\p{L})\\p{L}*(" + HIATO + ")\\p{L}*(?!\\p{L})");
    patron.setFormato("Palabra con dos vocales seguidas en sílabas distintas: dos abiertas, o cerrada con tilde junto a una abierta.");
    patron.setExplicacion("La primera opción son dos abiertas (le-ón, le-er). Las otras son una cerrada con tilde "
        + "antes o después de una abierta (dí-a, pa-ís), porque la tilde rompe el diptongo.");
    patron.setValidas("león", "poeta", "leer", "aéreo", "día", "país", "baúl", "MAÍZ");
    patron.setInvalidas("aire", "piano", "ciudad", "casa", "cuidado", "día feliz");
    patron.setDemostracion("El poeta leía en el baúl", "[HIATO]");
    return patron;
  }
}
