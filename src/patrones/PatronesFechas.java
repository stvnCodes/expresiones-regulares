package patrones;

import classes.Patron;

public class PatronesFechas {
  // ------------------- Fecha ------------------- //
  public static Patron fecha() {
    Patron patron = new Patron("Fecha en formato dd/mm/aaaa",
        "\\b(((0[1-9]|1\\d|2[0-8])/(0[1-9]|1[0-2])|(29|30)/(0[13-9]|1[0-2])|31/(0[13578]|1[02]))/(19|20)\\d{2}"
        + "|29/02/((19|20)(0[48]|[2468][048]|[13579][26])|2000))\\b");
    patron.setFormato("Día y mes de dos dígitos y año de 1900 a 2099, separados por /. El día debe existir en el mes.");
    patron.setExplicacion("Los días 01 a 28 sirven en todos los meses, 29 y 30 en todos menos febrero, y 31 en siete meses. "
        + "La última parte acepta 29/02 solo en años bisiestos.");
    patron.setValidas("01/01/2023", "31/12/1999", "29/02/2024");
    patron.setInvalidas("31/04/2023", "29/02/2023", "1/1/2023", "15/13/2023", "2023/01/01");
    patron.setDemostracion("Inicia el 01/02/2026 y termina el 30/06/2026", "[FECHA]");
    return patron;
  }

  // ------------------- Horas ------------------- //
  public static Patron hora24() {
    Patron patron = new Patron("Hora en formato de 24 horas hh:mm:ss",
        "\\b([01]\\d|2[0-3])(:[0-5]\\d){2}\\b");
    patron.setFormato("Horas de 00 a 23, minutos y segundos de 00 a 59.");
    patron.setExplicacion("[01]\\d va de 00 a 19 y 2[0-3] de 20 a 23. "
        + "(:[0-5]\\d){2} repite dos veces los dos puntos y un valor de 00 a 59.");
    patron.setValidas("14:30:00", "00:00:00", "23:59:59");
    patron.setInvalidas("24:00:00", "14:60:00", "14:30", "4:30:00");
    patron.setDemostracion("La clase va de 14:30:00 a 16:15:30", "[HORA]");
    return patron;
  }

  public static Patron hora12() {
    Patron patron = new Patron("Hora en formato de 12 horas hh:mm:ss",
        "\\b(0[1-9]|1[0-2])(:[0-5]\\d){2} ([AP]M|[ap]m)\\b");
    patron.setFormato("Horas de 01 a 12, minutos y segundos de 00 a 59, un espacio y AM o PM.");
    patron.setExplicacion("0[1-9] va de 01 a 09 y 1[0-2] de 10 a 12. "
        + "([AP]M|[ap]m) acepta AM o PM, en mayúsculas o minúsculas.");
    patron.setValidas("02:30:00 AM", "12:00:00 PM", "11:59:59 pm");
    patron.setInvalidas("00:30:00 AM", "13:00:00 PM", "02:30:00", "02:30:00PM");
    patron.setDemostracion("Abre a las 08:00:00 AM y cierra a las 06:30:00 PM", "[HORA]");
    return patron;
  }
}
