
package parqueoscallejeros;

import java.time.LocalDateTime; //sirve para dar la hora del sistema
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kendall Ariel Rojas Cartin
 **/

public class Administrador extends Usuarios {
    
    private String pinAdmin;
    private String idUsuarioAdmin;
    
    private LocalDateTime fechaIngresoTrabajador; //SIN USAR DE MOMENTO
    private static List<Administrador> listaAdmins = new ArrayList(); //SIN USAR DE MOMENTO
   
    /**
     * Constructor de clase administrador con los parametros
     * @param pNombre String: El nombre del admin de 2 a 20 caracteres
     * @param pApellidos String: Los apellidos del admin de 1 a 40 caracteres
     * @param pTelefono int: numero de telefono del admin de 8 digitos exactos
     * @param pCorreo String: Correo del admin con el debido formato: parte1@parte2, parte1 y parte2 son strings con un tamaño mínimo de 3 caracteres cada uno 
     * @param pDireccionFisica String: Direccion fisica del admin de 5 a 60 caracteres
     * @param pIdUsuario String: Identificacion del admin 2 a 25 caracteres
     * @param pin String: pin del admin de 4 caracteres exactos
     **/
    
    Administrador(String pNombre, String pApellidos, int pTelefono, String pCorreo, String pDireccionFisica, String pIdUsuario, String pin){
        super(pNombre,pApellidos,pTelefono,pCorreo,pDireccionFisica,pIdUsuario,pin);
        setIdAdmin(pIdUsuario);
        setPinAdmin(pin);
    }
    
    /**
     *Envia un correo al actualizar la configuracion SIN TERMINAR
     **/
    
    public void correoActualizacion(){}
    
    /**
     * Configura el parqueo y sus datos SIN TERMINAR
     **/
    
    public void configurarParqueo(){}
    
    /**
     * toString del admin
     * @return String con los datos del administrador concatenados
     **/
    
    public String toStringAdmin(){
        return "Admin: " + getNombre() + " "+ getApellidos() +" "+ getTelefono() + " " + getCorreo() +" "+ getDireccionFisica() +" "+ getIdAdmin() + " " + getPinAdmin(); 
    }
    
    /**
     * toString de todos los administradores del sistema
     * @return String de los administradores
     **/
    
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
    
    /**
     * Desde un numero inicio hasta otro final agrega espacios del parqueo
     * @param inicio int numero inicial 
     * @param fin int numero final
     * 
     * @throws IllegalArgumentException Si el inicio o final estan fuera de rango
     * @throws IllegalArgumentException Si el inicio es mayor que el final
     * @throws IllegalArgumentException Si el espacio ya existia
     **/
    
    public void agregarEspaciosParqueo(int inicio, int fin){
        Parqueo parqueo = new Parqueo();
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
                for (int i= inicio; i<=fin; i++){
                    try{
                        parqueo.setEspaciosParqueo(i);
                    }
                    catch(Exception e){
                        throw new IllegalArgumentException("Espacio ya existente");
                    }
                }
            }
        }
        
    }
    
    /**
     * Elimina los espacios del parqueo desde inicio a fin excepto los que tengan parqueo asignado
     * @param inicio int numero inicial a eliminar
     * @param fin int numero final a eliminar
     * 
     * @throws IllegalArgumentException Si el inicio o final estan fuera de rango
     * @throws IllegalArgumentException Si el inicio es mayor al final
     * @throws IllegalArgumentException Si el espacio a eliminar ya esta siendo ocupado
     **/
    
    public void eliminarEspaciosParqueo(int inicio, int fin) {
        Parqueo parqueo = new Parqueo();
        List<Integer> listaEspacios = parqueo.getEspaciosParqueo();
        List<Parqueo> listaParqueos = parqueo.getListaParqueos();

        if (String.valueOf(inicio).length() > 5 || String.valueOf(inicio).length() < 1 || 
            String.valueOf(fin).length() > 5 || String.valueOf(fin).length() < 1) {
            throw new IllegalArgumentException("Inicio o final fuera de rango");
        }

        if (inicio > fin){
            throw new IllegalArgumentException("Inicio mayor final");
        }
        List<Integer> espaciosAEliminar = new ArrayList<>();
        for (int i = inicio; i <= fin; i++) {
            boolean usado = false;
            for (Parqueo parqueoActual : listaParqueos) { 
                if (parqueoActual.getEspacio() == i) { //Si en un parqueo se encuentra ese espacio es porque esta siendo usado
                    usado = true;
                    break;
                }
            }
            if (!usado) {
                espaciosAEliminar.add(i);
            } 
            else {
                throw new IllegalArgumentException("El espacio " + i + " esta siendo usado");
            }
        }
        listaEspacios.removeAll(espaciosAEliminar); // elimina todos los espacios en la lista
    }
    
    
    /**
     * Saca la hora de ingreso al sistema del admin 
     **/
    
    public void sacarIngreso(){
        LocalDateTime fechaActual = LocalDateTime.now();
        fechaIngresoTrabajador = fechaActual;
        System.out.println(fechaActual); // Imprime la fecha de ingreso
    }
    
    // setters
    
    /**
     * Set id de admin
     * @param pIdUsuarioAdmin String del id del usuario admin
     **/
    
    public void setIdAdmin(String pIdUsuarioAdmin){
        this.idUsuarioAdmin = pIdUsuarioAdmin;
    }
    
    /**
     * Set pin de admin
     * @param pinAdmin String pin del admin
     **/
    
    public void setPinAdmin(String pinAdmin){
        this.pinAdmin = pinAdmin;
    }
    
    //getters
    
    /**
     * Get id de admin
     * @return String idUsuarioAdmin
     **/
    
    public String getIdAdmin(){
        return idUsuarioAdmin;
    }
    
    /**
     * Get pin de admin
     * @return String pinAdmin
     **/
    
    public String getPinAdmin(){
        return pinAdmin;
    }
    

}
