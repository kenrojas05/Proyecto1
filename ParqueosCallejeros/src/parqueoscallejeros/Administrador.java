
package parqueoscallejeros;

import java.time.LocalDateTime; //sirve para dar la hora del sistema
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuarios {
    
    private String pinAdmin;
    private String idUsuarioAdmin;
    
    private LocalDateTime fechaIngresoTrabajador;
    private static List<Administrador> listaUsuarios = new ArrayList();
   
    
    Administrador(String pNombre, String pApellidos, int pTelefono, String pCorreo, String pDireccionFisica, String pIdUsuario, String pin){
        super(pNombre,pApellidos,pTelefono,pCorreo,pDireccionFisica,pIdUsuario,pin);
        sacarIngreso();
        setIdAdmin(pIdUsuario);
        setIdUsuarioAdmin(pin);
    }
    
    
    public void sacarIngreso(){
        LocalDateTime fechaActual = LocalDateTime.now();
        fechaIngresoTrabajador = fechaActual;
    }
    
    // setters
    
    public void setIdAdmin(String pIdUsuarioAdmin){
        this.idUsuarioAdmin = pIdUsuarioAdmin;
    }
    public void setIdUsuarioAdmin(String pinAdmin){
        this.pinAdmin = pinAdmin;
    }
    

}
