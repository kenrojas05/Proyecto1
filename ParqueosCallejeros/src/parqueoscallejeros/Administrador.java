
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
        setIdAdmin(pIdUsuario);
        setPinAdmin(pin);
    }
    
    public void correoActualizacion(){}
    
    public String toStringAdmin(){
        return "Admin: " + getNombre() + " "+ getApellidos() +" "+ getTelefono() + " " + getCorreo() +" "+ getDireccionFisica() +" "+ getIdAdmin() + " " + getPinAdmin(); 
    }
    
    public String toStringAdministradores(){
        String res = "";
        for (Usuarios i : getUsuarios()){
            if(i instanceof Administrador){
                Administrador admin = (Administrador) i;
                res += admin.toStringAdmin() + "\n";
            }
            }
        return res;
    }
    
    public void agregarEspaciosParqueo(int inicio, int fin, Parqueo parqueo){
        if  (String.valueOf(inicio).length()>5 || String.valueOf(inicio).length()<1 || String.valueOf(fin).length()>5 || String.valueOf(fin).length()<1){
            throw new IllegalArgumentException("Inicio o final fuera de rango");
        
        }
        else if (inicio > fin) {
            throw new IllegalArgumentException("Inicio mayor que final");
        }
        else {
            if (inicio == fin){
                try{
                    parqueo.setEspaciosParqueo(inicio);
                }
                catch(Exception e){
                    throw new IllegalArgumentException("Espacio ya existente");
                }
            }
            else{
                for (int i= inicio; i<fin; i++){
                    try{
                        parqueo.setEspaciosParqueo(inicio);
                    }
                    catch(Exception e){
                        throw new IllegalArgumentException("Espacio ya existente");
                    }
                }
            }
        }
        
    }
    
    public void eliminarEspaciosParqueo(int inicio, int fin, Parqueo parqueo){
        List<Integer> listaEspacios = parqueo.getEspaciosParqueo();
        //List<Parqueo> listaParqueos = parqueo.getListaParqueos(); PUEDE QUE TENGA QUE ELIMINAR EL OBJ PARQUEO TAMBIEN
        if  (String.valueOf(inicio).length()>5 || String.valueOf(inicio).length()<1 || String.valueOf(fin).length()>5 || String.valueOf(fin).length()<1){
               throw new IllegalArgumentException("Inicio o final fuera de rango");

           }
           else if (inicio > fin) {
               throw new IllegalArgumentException("Inicio mayor que final");
           }
           else {
               if (inicio == fin){
                   try{
                       listaEspacios.remove(inicio);
                   }
                   catch(Exception e){
                       throw new IllegalArgumentException("Error al eliminar");
                   }
               }
               else{
                   for (int i= inicio; i<fin; i++){
                       try{
                           listaEspacios.remove(inicio);
                       }
                       catch(Exception e){
                           throw new IllegalArgumentException("Error al eliminarlos");
                       }
                   }
               }
           }} 
    
    
    public void sacarIngreso(){
        LocalDateTime fechaActual = LocalDateTime.now();
        fechaIngresoTrabajador = fechaActual;
        System.out.println(fechaActual); // Imprime la fecha de ingreso
    }
    
    // setters
    
    public void setIdAdmin(String pIdUsuarioAdmin){
        this.idUsuarioAdmin = pIdUsuarioAdmin;
    }
    public void setPinAdmin(String pinAdmin){
        this.pinAdmin = pinAdmin;
    }
    
    //getters
    
    public String getIdAdmin(){
        return idUsuarioAdmin;
    }
    public String getPinAdmin(){
        return pinAdmin;
    }
    

}
