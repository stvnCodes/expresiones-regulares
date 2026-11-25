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
}
