package edu.eci.dosw;

import edu.eci.dosw.reto1.Reto1TiendaDonPepe;
import edu.eci.dosw.reto2.Reto2RestauranteHamburguesa;
import edu.eci.dosw.reto3.Reto3ReinoVehiculos;
import edu.eci.dosw.reto4.Reto4CambioMoneda;
import edu.eci.dosw.reto5.Reto5Cafeteria;
import edu.eci.dosw.reto6.Reto6Tecnicos;
import edu.eci.dosw.reto7.Reto7MagicControl;

public class Application {

    public static void main(String[] args) {
        System.out.println("Maven project configured and running correctly.");
        System.out.println();

        Reto1TiendaDonPepe.run();
        Reto2RestauranteHamburguesa.run();
        Reto3ReinoVehiculos.run();
        Reto4CambioMoneda.run();
        Reto5Cafeteria.run();
        Reto6Tecnicos.run();
        Reto7MagicControl.run();

    }
}
