
package parqueoscallejeros;


/**
 * @author Kendall Ariel Rojas Cartin
 **/

public class Inspector extends Usuarios {
    private static final long serialVersionUID = 1L; //version de serializacion
    
    private String terminal; //6 caracteres exactos
    
    /**
     * Constructor de clase inspector con los parametros
     * @param pNombre String: El nombre del admin de 2 a 20 caracteres
     * @param pApellidos String: Los apellidos del admin de 1 a 40 caracteres
     * @param pTelefono int: numero de telefono del admin de 8 digitos exactos
     * @param pCorreo String: Correo del admin con el debido formato: parte1@parte2, parte1 y parte2 son strings con un tamaño mínimo de 3 caracteres cada uno 
     * @param pDireccionFisica String: Direccion fisica del admin de 5 a 60 caracteres
     * @param pIdUsuario String: Identificacion del admin 2 a 25 caracteres
     * @param pin String: pin del admin de 4 caracteres exactos
     **/
    
    Inspector(String pNombre, String pApellidos, int pTelefono, String pCorreo, String pDireccionFisica, String pIdUsuario, String pin){
        super(pNombre,pApellidos,pTelefono,pCorreo,pDireccionFisica,pIdUsuario,pin);
    }
    
}
