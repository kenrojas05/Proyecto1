
package parqueoscallejeros;

/**
 * @author Kendall Ariel Rojas Cartin
 **/

public class ParqueosCallejeros {

    /**
     * El main
     * @param args String[]
     **/
    
    public static void main(String[] args) {
        //GUIProyecto1 inicio = new GUIProyecto1();
        //inicio.setVisible(true);
        
        
        UsuariosParqueo user1 = new UsuariosParqueo("Kendall", "Rojas Cartin", 64572406, "kenrojas@estudiantec.cr", "Costa rica", "ggpraaaa777", "1234",223344, "02/24", 123);
        UsuariosParqueo user2 = new UsuariosParqueo("Pepe", "Rojas Cartin", 64572406, "kenrojas@estudiantec.cr", "Costa rica", "juanillo", "8902",223345, "12/20", 223);
        
        Administrador admin1 = new Administrador("AdminK", "Rojas Cartin", 61441164, "kendallrc05@gmail.com", "Cartago", "El admin","7777");
        Administrador admin2 = new Administrador("AdminP", "Rojas Cartin", 61441164, "kendallrc05@gmail.com", "Cartago", "El adminc","7777");
        
        Parqueo parqueo = new Parqueo();
        
        admin1.agregarEspaciosParqueo(100, 200);
        
        
        
        Parqueo parqueo1 = new Parqueo("BMY381", 100, 30, "20:12", "23:00", 15, 3); 
        
        parqueo1.setParqueo(parqueo1);
        
        user1.setCarro("BMY381");
        user1.setCarro("BMY391");
        user1.setCarro("BMY311");
        user2.setCarro("BMY385");
        
        user1.Parquear("BYE333",130, 30);
        user2.Parquear("HER434",120, 20);
        
        admin1.eliminarEspaciosParqueo(110, 140);
        
        System.out.println(user1.revisarCarros());
        
        System.out.println(parqueo.getEspaciosParqueo());
        System.out.println(parqueo.toStringListaParqueos());
        System.out.println(user1.getCarros());
        System.out.println(user2.getCarros());
        
        user1.desaparcarAutomatico();
        

        
    }
    
}
