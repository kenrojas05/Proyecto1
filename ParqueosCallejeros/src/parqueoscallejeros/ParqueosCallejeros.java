
package parqueoscallejeros;

public class ParqueosCallejeros {

    
    public static void main(String[] args) {
        GUIProyecto1 inicio = new GUIProyecto1();
        inicio.setVisible(true);
        
        usuariosParqueo user1 = new usuariosParqueo("Kendall", "Rojas Cartin", 64572406, "kenrojas@estudiantec.cr", "Costa rica", "ggpraaaa777", "1234",223344, "02/24", 123);
        System.out.println(user1.toString());
        
        System.out.println(user1.toStringUsuarios());
        
        System.out.println(user1.toStringUserParqueo());
        
        System.out.println(user1.toStringUsuariosParqueo());
        
        
        //user1.enviarCorreoDatos(user1.getCorreo(), "Kendall", user1);
        
    }
    
}
