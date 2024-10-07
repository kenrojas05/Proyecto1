
package parqueoscallejeros;

public class ParqueosCallejeros {

    
    public static void main(String[] args) {
        //GUIProyecto1 inicio = new GUIProyecto1();
        //inicio.setVisible(true);
        
        usuariosParqueo user1 = new usuariosParqueo("Kendall", "Rojas Cartin", 64572406, "kenrojas@estudiantec.cr", "Costa rica", "ggpraaaa777", "1234",223344, "02/24", 1234);
        user1.enviarCorreoDatos(user1.getCorreo(), user1.getNombre(), user1);
    }
    
}
