
package parqueoscallejeros;

public class ParqueosCallejeros {

    
    public static void main(String[] args) {
        Usuarios prueba = new Usuarios("Pepe","Rojas",64572406,"kenrojas@estudiantec.cr","San jose", "El pepe", "1221");
        Usuarios prueba2 = new Usuarios("Pepe","Rojas",64572406,"kenrojas@estudiantec.cr","San jose", "El pepe", "1211");
       
        System.out.println(prueba.toString());
        System.out.print(prueba.toStringUsuarios());
    }
    
}
