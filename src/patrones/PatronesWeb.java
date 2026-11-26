package patrones;

import classes.Patron;

public class PatronesWeb {
  // ------------------- Direcciones ------------------- //
  public static Patron correo() {
    Patron patron = new Patron("Direcciones de correo electrónico",
        "\\b[\\w+-]+(\\.[\\w+-]+)*@([A-Za-z0-9]+(-[A-Za-z0-9]+)*\\.)+[A-Za-z]{2,}\\b");
    patron.setFormato("usuario@dominio.extensión. Sin espacios ni puntos seguidos, y con extensión de dos letras o más.");
    patron.setExplicacion("[\\w+-]+(\\.[\\w+-]+)* es el usuario, con partes separadas por un punto. Después de @ van una o más "
        + "partes del dominio y [A-Za-z]{2,} es la extensión.");
    patron.setValidas("ana@correo.com", "juan.perez@elpoli.edu.co", "user+1@mi-sitio.org");
    patron.setInvalidas("ana.correo.com", "ana@correo", "ana..perez@correo.com", "ana@-correo.com", "ana perez@correo.com");
    patron.setDemostracion("Escriba a ana@correo.com o a juan.perez@elpoli.edu.co", "[CORREO]");
    return patron;
  }

  public static Patron url() {
    Patron patron = new Patron("URL",
        "\\b(https?://|www\\.)([A-Za-z0-9]+(-[A-Za-z0-9]+)*\\.)+[A-Za-z]{2,}\\b"
        + "(:\\d{1,5})?([/?#](\\S*[^\\s.,;:!?])?)?");
    patron.setFormato("Empieza por http://, https:// o www. y tiene dominio con extensión. El puerto y la ruta son opcionales.");
    patron.setExplicacion("https? acepta http y https. Luego van el dominio y la extensión, (:\\d{1,5})? es el puerto "
        + "y lo último es la ruta, que no toma la puntuación final de una frase.");
    patron.setValidas("https://www.google.com", "http://mi-sitio.co:8080/inicio?id=1", "www.elpoli.edu.co/");
    patron.setInvalidas("google.com", "https://google", "http:/google.com", "ftp://sitio.com", "https://mi sitio.com");
    patron.setDemostracion("Visite https://www.google.com y www.elpoli.edu.co/noticias", "[URL]");
    return patron;
  }

  // ------------------- Seguridad ------------------- //
  public static Patron contrasena() {
    Patron patron = new Patron("Contraseña segura",
        "(?<!\\S)(?=\\S*\\p{Ll})(?=\\S*\\p{Lu})(?=\\S*\\d)(?=\\S*\\p{Punct})\\S{8,20}(?!\\S)");
    patron.setFormato("De 8 a 20 caracteres sin espacios, con una minúscula, una mayúscula, un dígito y un símbolo como mínimo.");
    patron.setExplicacion("Cada (?=...) exige algo sin avanzar: minúscula (\\p{Ll}), mayúscula (\\p{Lu}), dígito y símbolo. "
        + "\\S{8,20} toma la contraseña "
        + "y (?<!\\S) y (?!\\S) comprueban que no haya más caracteres pegados.");
    patron.setValidas("Medellin#2026", "Cafe_con3", "aB3$aB3$");
    patron.setInvalidas("medellin#2026", "MEDELLIN#2026", "Medellin#", "Medellin2026", "aB3$", "Mede llin#2026");
    patron.setDemostracion("Claves: Medellin#2026 y Cafe_con3", "[CLAVE]");
    return patron;
  }
}
