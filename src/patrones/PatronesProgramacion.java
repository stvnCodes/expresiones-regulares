package patrones;

import classes.Patron;

public class PatronesProgramacion {
  // ------------------- Piezas comunes ------------------- //
  // Un entero que empieza en 0 Java lo lee en base 8, por eso solo se permite el 0 solo
  private static final String ENTERO = "(0|[1-9]\\d*)";
  private static final String NOMBRE = "[\\p{L}_$][\\p{L}\\d_$]*";
  private static final String VALOR = "(-?\\d+(\\.\\d+)?|" + NOMBRE + "(\\." + NOMBRE + ")*(\\(\\))?)";
  private static final String CUENTA = VALOR + "(\\s*[-+*/%]\\s*" + VALOR + ")*";
  private static final String ESCAPE = "\\\\[btnfr\"'\\\\]";
  private static final String RESERVADAS = "abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|"
      + "default|do|double|else|enum|extends|final|finally|float|for|goto|if|implements|import|instanceof|int|"
      + "interface|long|native|new|package|private|protected|public|return|short|static|strictfp|super|switch|"
      + "synchronized|this|throw|throws|transient|try|void|volatile|while|true|false|null|_";

  // ------------------- Variables ------------------- //
  public static Patron variable() {
    Patron patron = new Patron("Declaración de variables en Java",
        "\\b(?!\\w+\\s+(" + RESERVADAS + ")(?![\\p{L}\\d_$]))("
            + declaracion("byte|short|int", "[+-]?" + ENTERO) + "|"
            + declaracion("long", "[+-]?" + ENTERO + "[lL]?") + "|"
            + declaracion("float", "[+-]?(" + ENTERO + "|(\\d*\\.)?\\d+[fF])") + "|"
            + declaracion("double", "[+-]?(" + ENTERO + "|\\d*\\.\\d+)([eE][+-]?\\d+)?[dD]?") + "|"
            + declaracion("boolean", "(true|false)") + "|"
            + declaracion("char", "'([^'\\\\]|" + ESCAPE + ")'") + "|"
            + declaracion("String", "(null|\"([^\"\\\\]|" + ESCAPE + ")*\")")
            + ")\\s*;");
    patron.setFormato("Tipo primitivo o String, nombre, valor inicial opcional del mismo tipo y punto y coma.");
    patron.setExplicacion("Hay una opción por cada grupo de tipos, con los valores que admite. El nombre empieza por letra, "
        + "_ o $, y el (?!...) del comienzo impide usar palabras reservadas.");
    patron.setValidas("int contador = 0;", "double precio = 3.5;", "String nombre = \"Ana\";", "boolean activo;", "char letra = 'a';");
    patron.setInvalidas("int contador = 0", "int 1contador = 0;", "int contador = \"hola\";", "boolean activo = 1;", "int class = 5;");
    patron.setDemostracion("int contador = 0; luego String nombre = \"Ana\"; y nada más", "[VARIABLE]");
    return patron;
  }

  private static String declaracion(String tipos, String valor) {
    return "(" + tipos + ")\\s+" + NOMBRE + "(\\s*=\\s*" + valor + ")?";
  }

  // ------------------- Ciclos ------------------- //
  public static Patron cicloFor() {
    Patron patron = new Patron("Estructura de un ciclo for",
        "\\b[Ff]or\\s*\\(\\s*((byte|short|int|long|float|double|char)\\s+)?" + NOMBRE + "\\s*=\\s*" + CUENTA
            + "\\s*;\\s*" + CUENTA + "\\s*(<=?|>=?|==|!=)\\s*" + CUENTA
            + "\\s*;\\s*(" + NOMBRE + "(\\+\\+|--)|(\\+\\+|--)" + NOMBRE
            + "|" + NOMBRE + "\\s*[-+*/]?=\\s*" + CUENTA + ")\\s*\\)");
    patron.setFormato("for o For con inicio, condición y cambio de la variable, separados por punto y coma.");
    patron.setExplicacion("El inicio asigna un valor, la condición compara dos valores y el cambio puede ser ++, --, "
        + "+= u otra asignación. Los \\s* permiten espacios.");
    patron.setValidas("For(k=1; k<N;k++)", "for (int i = 0; i < 10; i++)", "for(i=n-1; i>=0; i-=2)", "for(int i=0; i<datos.length; ++i)");
    patron.setInvalidas("for(i=0, i<10, i++)", "for(i=0; i<10)", "for(i=0; i; i++)", "while(i<10)", "for(i=0; i<10; i++");
    patron.setDemostracion("Primero for(i=0; i<10; i++) y después For(k=1; k<N;k++) para terminar", "[FOR]");
    return patron;
  }

  // ------------------- Números ------------------- //
  public static Patron numeroDouble() {
    Patron patron = new Patron("Número double",
        "(?<![\\w.])[+-]?(\\d*\\.\\d+([eE][+-]?\\d+)?|\\d+[eE][+-]?\\d+)(?!\\.?\\w)");
    patron.setFormato("Signo opcional, parte decimal con punto y exponente opcional. Sin punto debe tener exponente.");
    patron.setExplicacion("\\d*\\.\\d+ son los decimales y [eE][+-]?\\d+ es el exponente. "
        + "(?<![\\w.]) y (?!\\.?\\w) evitan tomar un pedazo de otro número.");
    patron.setValidas("3.14159", "-0.5", "+2.0", ".75", "1.5e-3", "2E10");
    patron.setInvalidas("3", "3,14", "3.", "1.2.3", "abc", "1.5e", "--2.0");
    patron.setDemostracion("El valor 3.14159 es pi y -0.5 es negativo", "[DOUBLE]");
    return patron;
  }
}
