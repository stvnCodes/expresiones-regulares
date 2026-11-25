package patrones;

import classes.Patron;

public class PatronesColombia {
  // ------------------- Vehículos ------------------- //
  public static Patron placa() {
    Patron patron = new Patron("Placas de carro en Colombia", "\\b[A-Z]{3}[- ]?\\d{3}\\b");
    patron.setFormato("Tres letras mayúsculas y tres dígitos, con guion o espacio opcional.");
    patron.setExplicacion("[A-Z]{3} son tres letras, [- ]? es el separador opcional y \\d{3} son tres dígitos. "
        + "Los \\b evitan tomar un pedazo de un código más largo.");
    patron.setValidas("ABC123", "XYZ-987", "JKL 456");
    patron.setInvalidas("AB1234", "abc123", "ABC12", "ABC1234", "ABC--123");
    patron.setDemostracion("ABC123 chocó con XYZ-987 en el parqueadero", "[PLACA]");
    return patron;
  }

  // ------------------- Ubicación y teléfonos ------------------- //
  public static Patron codigoPostal() {
    Patron patron = new Patron("Códigos postales para Medellín",
        "\\b0500\\d{2}\\b");
    patron.setFormato("Seis dígitos que empiezan en 0500.");
    patron.setExplicacion("0500 identifica a Medellín dentro de Antioquia (05) y \\d{2} es la zona. "
        + "Los \\b evitan tomar parte de un número más largo.");
    patron.setValidas("050001", "050012", "050030");
    patron.setInvalidas("110111", "05001", "0500123", "0500A1");
    patron.setDemostracion("De la zona 050012 se envió a la 050030", "[POSTAL]");
    return patron;
  }

  public static Patron telefonoFijo() {
    Patron patron = new Patron("Teléfonos fijos en Medellín",
        "(\\+57 ?|\\b)604[- ]?\\d{3}[- ]?\\d{2}[- ]?\\d{2}\\b");
    patron.setFormato("604 y siete dígitos. El +57 y los separadores (espacio o guion) son opcionales.");
    patron.setExplicacion("(\\+57 ?|\\b) permite el indicativo del país, 604 es el de Antioquia "
        + "y los siete dígitos se agrupan en 3, 2 y 2 con separador opcional.");
    patron.setValidas("6044441234", "604 444 1234", "+57 604 444 12 34");
    patron.setInvalidas("4441234", "6014441234", "604444123", "60444412345");
    patron.setDemostracion("Llame al 6044441234 o al 604 511 1234", "[FIJO]");
    return patron;
  }

  public static Patron celular() {
    Patron patron = new Patron("Teléfonos celulares en Colombia",
        "(\\+57 ?|\\b)3\\d{2}[- ]?\\d{3}[- ]?\\d{2}[- ]?\\d{2}\\b");
    patron.setFormato("Diez dígitos que empiezan en 3. El +57 y los separadores (espacio o guion) son opcionales.");
    patron.setExplicacion("(\\+57 ?|\\b) permite el indicativo del país, 3\\d{2} es el inicio del celular "
        + "y los otros siete dígitos se agrupan en 3, 2 y 2 con separador opcional.");
    patron.setValidas("3001234567", "300 123 4567", "+57 310-456-7890");
    patron.setInvalidas("2001234567", "300123456", "30012345678", "6044441234");
    patron.setDemostracion("Ana: 3001234567, Luis: +57 310 456 7890", "[CELULAR]");
    return patron;
  }

  // ------------------- Productos ------------------- //
  public static Patron codigoBarras() {
    Patron patron = new Patron("Código de barras en Colombia",
        "\\b77[01]\\d{10}\\b");
    patron.setFormato("EAN-13: trece dígitos que empiezan en 770 o 771.");
    patron.setExplicacion("77[01] acepta 770 o 771, los prefijos de GS1 Colombia, y \\d{10} completa los trece dígitos. "
        + "El dígito de control no se calcula.");
    patron.setValidas("7701234567890", "7712345678901");
    patron.setInvalidas("7501234567890", "770123456789", "77012345678901", "770-123456789");
    patron.setDemostracion("Leche 7701234567890 y pan 7712345678901", "[BARRAS]");
    return patron;
  }

  public static Patron precio() {
    Patron patron = new Patron("Precios de productos",
        "\\$ ?[1-9](\\d{0,2}(\\.\\d{3})+|\\d*)(,\\d{2})?(?![.,]?\\d)");
    patron.setFormato("Pesos colombianos mayores o iguales a 1, con punto de miles y dos decimales opcionales.");
    patron.setExplicacion("\\$ ? es el signo con espacio opcional y [1-9] evita ceros a la izquierda. Luego van grupos de miles "
        + "(\\.\\d{3})+ o dígitos seguidos, y (,\\d{2})? son los centavos. (?![.,]?\\d) impide cortar un número mal escrito.");
    patron.setValidas("$ 12.500", "$12500", "$ 1.250.000,50");
    patron.setInvalidas("12.500", "$ 12.50", "$ 1.2500", "$ 012", "$ 12.500,5");
    patron.setDemostracion("Arroz $ 4.500 y aceite $12.900,50", "[PRECIO]");
    return patron;
  }

  // ------------------- Normas ------------------- //
  public static Patron norma() {
    Patron patron = new Patron("Leyes, decretos y artículos en Colombia",
        "\\b(([Ll]ey|[Dd]ecreto) [1-9]\\d{0,3} de (18|19|20)\\d{2}|[Aa]rtículo [1-9]\\d{0,3})\\b");
    patron.setFormato("Ley o decreto con número y año (1800 a 2099), o artículo con número.");
    patron.setExplicacion("La barra | separa dos opciones: ley o decreto, con número de hasta cuatro dígitos y año, "
        + "o la palabra artículo con su número. [1-9] evita que el número empiece en cero.");
    patron.setValidas("Ley 100 de 1993", "Decreto 1072 de 2015", "Artículo 23");
    patron.setInvalidas("Ley de 1993", "Decreto 1072", "Artículo veintitrés", "Ley 100 de 93");
    patron.setDemostracion("Según el Artículo 48 de la Ley 100 de 1993 y el Decreto 1072 de 2015", "[NORMA]");
    return patron;
  }
}
