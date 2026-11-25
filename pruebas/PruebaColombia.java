import patrones.PatronesColombia;

public class PruebaColombia {
  public static void main(String[] args) {
    Verificacion.acepta(PatronesColombia.placa(), "AAA000", "ZZZ-999");
    Verificacion.rechaza(PatronesColombia.placa(), "", " ABC123", "ABC123 ", "ABC12D", "ÑBC123", "ABC  123");
    Verificacion.encuentra(PatronesColombia.placa(), 0, "XABC123 ABC1234");

    Verificacion.acepta(PatronesColombia.codigoPostal(), "050001", "050099");
    Verificacion.rechaza(PatronesColombia.codigoPostal(), "", "05001", "0500100", "050101", "05001a", "110111");
    Verificacion.encuentra(PatronesColombia.codigoPostal(), 0, "10500105");

    Verificacion.acepta(PatronesColombia.telefonoFijo(), "6040000000", "+576044441234", "+57 604-444-12-34");
    Verificacion.rechaza(PatronesColombia.telefonoFijo(), "", "604444123", "60444412345", "+1 6044441234", "57 6044441234", "604  4441234");
    Verificacion.encuentra(PatronesColombia.telefonoFijo(), 0, "960444412345");

    Verificacion.acepta(PatronesColombia.celular(), "3000000000", "+573001234567", "300-123-4567", "300 123 45 67");
    Verificacion.rechaza(PatronesColombia.celular(), "", "300123456", "30012345678", "4001234567", "+58 3001234567", "300.123.4567");
    Verificacion.encuentra(PatronesColombia.celular(), 1, "llame al +573001234567 hoy");
    Verificacion.encuentra(PatronesColombia.celular(), 0, "13001234567");

    Verificacion.acepta(PatronesColombia.codigoBarras(), "7700000000000", "7719999999999");
    Verificacion.rechaza(PatronesColombia.codigoBarras(), "", "770000000000", "77000000000000", "7720000000000", "770 0000000000");
    Verificacion.encuentra(PatronesColombia.codigoBarras(), 0, "97700000000000");

    Verificacion.acepta(PatronesColombia.norma(), "Ley 1 de 1800", "decreto 9999 de 2099", "artículo 1");
    Verificacion.rechaza(PatronesColombia.norma(), "", "Ley 0 de 1993", "Ley 100 de 1799", "Ley 100 de 2100", "Ley 10000 de 1993", "Artículo 0", "Articulo 23", "Ley 100");
    Verificacion.encuentra(PatronesColombia.norma(), 2, "el Artículo 5 de la Ley 100 de 1993.");

    Verificacion.acepta(PatronesColombia.precio(), "$1", "$ 999", "$ 1.000", "$1000", "$ 1.000.000,99");
    Verificacion.rechaza(PatronesColombia.precio(), "", "$", "$ 0", "$ 01.000", "$ 1000.000", "$ 1.0000", "$ 5,5", "$ 5,555", "$ -5", "$  5", "5");
    Verificacion.encuentra(PatronesColombia.precio(), 0, "$ 12.50 y $ 1.2500");
    Verificacion.encuentra(PatronesColombia.precio(), 2, "Vale $ 12.500, antes $15000.");

    Verificacion.mostrarTotal("Colombia");
  }
}
