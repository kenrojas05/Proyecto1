
package parqueoscallejeros;

import java.time.LocalDateTime; //sirve para dar la hora del sistema
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuarios {

    
    private LocalDateTime fechaIngresoTrabajador;
    
    Administrador(){
    
    }
    
    
    public void sacarIngreso(){
        LocalDateTime fechaActual = LocalDateTime.now();
        fechaIngresoTrabajador = fechaActual;
    }
    
    // setters
    

}
