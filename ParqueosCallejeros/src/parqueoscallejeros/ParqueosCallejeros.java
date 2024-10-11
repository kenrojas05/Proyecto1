
package parqueoscallejeros;

public class ParqueosCallejeros {

    
    public static void main(String[] args) {
        //GUIProyecto1 inicio = new GUIProyecto1();
        //inicio.setVisible(true);
        Parqueo parqueo = new Parqueo();
        
        UsuariosParqueo user1 = new UsuariosParqueo("Kendall", "Rojas Cartin", 64572406, "kenrojas@estudiantec.cr", "Costa rica", "ggpraaaa777", "1234",223344, "02/24", 123);
        UsuariosParqueo user2 = new UsuariosParqueo("Pepe", "Rojas Cartin", 64572406, "kenrojas@estudiantec.cr", "Costa rica", "juanillo", "8902",223345, "12/20", 223);
        
        Administrador admin1 = new Administrador("AdminK", "Rojas Cartin", 61441164, "kendallrc05@gmail.com", "Cartago", "El admin","7777");
        Administrador admin2 = new Administrador("AdminP", "Rojas Cartin", 61441164, "kendallrc05@gmail.com", "Cartago", "El adminc","7777");
        
        user1.setCarro("BMY381");
        user2.setCarro("BMY385");
        
      //  user1.setIngresoParqueo();
      //  user2.setIngresoParqueo();
        
        parqueo.setEspaciosParqueo(100);
        parqueo.setEspaciosParqueo(110);
        Parqueo parqueo1 = new Parqueo("BMY381", 100, 30, "20:12", "23:00", 15, 3);
        Parqueo parqueo2 = new Parqueo("BMY385", 110, 30, "20:12", "23:00", 15, 3);
        
        System.out.println(user1.toStringUsuariosParqueo());
        
        System.out.println(user1.toStringCarros());
        System.out.println(user2.toStringCarros());
        
        parqueo1.setParqueo(parqueo1);
        parqueo2.setParqueo(parqueo2);
        
        System.out.println(parqueo1.toStringParqueo());
        System.out.println(parqueo2.toStringParqueo());
        
        System.out.println(parqueo2.toStringEspacios());
        System.out.println(parqueo2.toStringListaParqueos());
        
        System.out.println(user1.getIngresoParqueo());
        
        user1.revisarCarros();
        /*
        System.out.println(user1.getCarros());
        
        System.out.println(admin1.toStringUsuarios());
        
        System.out.println(admin1.toStringAdministradores());
        
        System.out.println(user1.toStringUsuariosParqueo());*/
        //user1.enviarCorreoDatos(user1.getCorreo(), "Kendall", user1);
        
    }
    
}
