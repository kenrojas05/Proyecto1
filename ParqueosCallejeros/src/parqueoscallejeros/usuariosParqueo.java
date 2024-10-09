package parqueoscallejeros;
                                                    //correo jvim woqi nqnz puyb

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime; // Sirve para dar la hora del sistema
import javax.mail.*; // Se añadió la librería lib al proyecto para la API javax.mail
import java.util.Properties; 
import javax.mail.internet.*; // Importa las clases necesarias para MIME

public class usuariosParqueo extends Usuarios{
    private long tarjeta; // Natural, 16 dígitos (tiene que ser única dentro de la aplicación)
    private String tarjetaVencimiento; // Mes y año, ej: 02/24 o 02/2024
    private int codigoVal; // Natural, 3 dígitos exactos

    private static List<usuariosParqueo> usuarios = new ArrayList<>(); // Para guardar a estos usuarios 
    private List<String> placaCarros = new ArrayList<>(); //1 a 6 caracteres
    
    private LocalDateTime fechaIngreso;
    
    usuariosParqueo(String pNombre, String pApellidos, int pTelefono, String pCorreo, String pDireccionFisica, String pIdUsuario, String pin, long pTarjeta, String pTarjetaVencimiento, int pCodigoVal){
        super(pNombre, pApellidos, pTelefono, pCorreo, pDireccionFisica, pIdUsuario, pin);
        setTarjeta(pTarjeta);
        setTarjetaVencimiento(pTarjetaVencimiento);
        setCodigoVal(pCodigoVal);
        sacarIngresoSistema();
        
        agregarUserParqueo(this);
        
    }
    
    public void agregarUserParqueo(usuariosParqueo user){
        for (usuariosParqueo i : usuarios){
            if (i.getTarjeta() == user.getTarjeta()){
                throw new IllegalArgumentException("Tarjeta debe ser unica!");
            }
        }

        usuarios.add(user);
    } 
    
        public String toStringUserParqueo(){
        return "Usuario: " + getNombre() + " "+ getApellidos() +" "+ getTelefono() + " " + getCorreo() +" "+ getDireccionFisica() +" "+ getIdUsuario() + " " + getPin() + " " + getTarjeta() + " " + getTarjetaVencimiento() + " " + getCodigoVal();
    }
    
    public String toStringUsuariosParqueo(){
        String res = "";
        for (Usuarios i : getUsuarios()){
            if(i instanceof usuariosParqueo){
                usuariosParqueo parqueo = (usuariosParqueo) i;
                res += parqueo.toStringUserParqueo() + "\n";
            }
            }
        return res;
    }

    public void sacarIngresoSistema() {
        LocalDateTime fechaActual = LocalDateTime.now(); // Obtiene la fecha y hora actual
        fechaIngreso = fechaActual; // Asigna la fecha de ingreso
        System.out.println(fechaIngreso); // Imprime la fecha de ingreso
    }
    
    public void enviarCorreoDatos(String correo, String nombre, usuariosParqueo usuario) {
        Properties propiedades = new Properties(); //las propiedades del mail (configurado para gmail)
            propiedades.put("mail.smtp.auth", "true"); //valida la autentificacion a traves de smtp 
            propiedades.put("mail.smtp.starttls.enable", "true"); // Transport Layer Security o TLS para la conexión SMTP.
            propiedades.put("mail.smtp.host", "smtp.gmail.com"); // servidor de corres para smtp (en este caso gmail)
            propiedades.put("mail.smtp.port", "587"); //el puerto (para smtp se usa 587 en conexiones STARTTLS)

            Session sesion = Session.getInstance(propiedades, /* se crea una sesion con el correo, propiedades y la autentificacion*/
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() { //autentifica el correo
                        return new PasswordAuthentication("proyectokendall@gmail.com", "jvim woqi nqnz puyb"); // correo del proyecto desde donde se envia (correo y contrasena)
                    }
                });

            try {
                Message mensaje = new MimeMessage(sesion); //iniciar mensaje con la sesion indicada
                mensaje.setFrom(new InternetAddress("kendallrc05@gmail.com", "Parqueos Callejeros")); // desde quien se manda el correo
                mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress("kenrojas@estudiantec.cr", "Kendall")); // el destino del correo o destinatario InternetAdress(correo,persona)
                mensaje.setSubject("Datos del usuario: " + usuario.getNombre()); // El asunto o titulo del correo
                mensaje.setText(datosUsuario(usuario)); // Todo el mensaje del correo

                // Enviar el mensaje
                Transport.send(mensaje);
                System.out.println("Correo enviado exitosamente!");

            } catch (AddressException e) {
                e.printStackTrace(); // Manejo de errores relacionados con la dirección de correo
            } catch (MessagingException e) {
                e.printStackTrace(); // Manejo de errores relacionados con el envío de correo
                System.out.println("Error al enviar el correo: " + e.getMessage());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace(); // Manejo de errores relacionados con la codificación del mensaje
            }
        }
    
    public String datosUsuario(usuariosParqueo usuario){
        String datos = "";
        for (Usuarios i : getUsuarios()){
            if (i instanceof usuariosParqueo || i.getPin() == this.getPin() || i.getIdUsuario() == this.getIdUsuario()){
                datos = "Nombre: " + usuario.getNombre() +"\n"
                       +"Apellidos: " +usuario.getApellidos()+ "\n"
                       +"Telefono:  "+usuario.getTelefono()+"\n"
                       +"Correo:    "+usuario.getCorreo()+"\n"
                       +"Direccion: "+usuario.getDireccionFisica()+"\n"
                       +"ID: "+usuario.getIdUsuario()+"\n"
                       +"PIN: "+usuario.getPin()+"\n"
                       +"Tarjeta:   "+usuario.getTarjeta()+"\n"
                       +"Vencimiento de Tarjeta: "+usuario.getTarjetaVencimiento()+"\n"
                       +"Codigo de Tarjeta: "+usuario.getCodigoVal()+" "+"\n";
            }
        }
        if (placaCarros.isEmpty()) {
            return datos +"Carros: 0"+"\n"; //FALTA AGREGAR OTROS DATOS 

        }
        int contador = 0;
        String carros = "";
        for (String i: placaCarros){
            contador+=1;
            carros += "Carro: "+contador+" Placa: "+i+"\n";
            ;
        }
        return datos + carros;
    }

    //setters y getters
    
    public void setTarjeta(long pTarjeta){ 
        String cantidad = String.valueOf(pTarjeta);
        if (cantidad == null || cantidad.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("tarjeta invalida!");//se utiliza para mandar una excepcion al sistema 
        }                                                                          //IllegalArgumentException es una clase que proviene de java.lang y sirve para indicar argumento ilegal
        if (cantidad.length()>16){
            throw new IllegalArgumentException("tarjeta con tamano invalido!");
        }
        else{tarjeta = pTarjeta;}
    }
    
    public void setTarjetaVencimiento(String pTarjetaVencimiento){
         String regex = "(\\d{1,2})/(\\d+)"; //formato de tarjeta 0..12/0..+

        if (!pTarjetaVencimiento.matches(regex)) {
            throw new IllegalArgumentException("Tarjeta V Formato inválido. '0-12/0-...'");
        }
        else{tarjetaVencimiento = pTarjetaVencimiento;}
        
    }
    public void setCodigoVal(int pCodigoVal){
        String cantidad = String.valueOf(pCodigoVal);
        if (cantidad.length()!=3 || cantidad == null){
            throw new IllegalArgumentException("El Codigo de validacion tiene una cantidad erronea de caracteres o es invalido");
        }
        else{codigoVal = pCodigoVal;}
    }
    
    public void setCarro( String placa){ 
        if (placa == null || placa.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("placa invalida!");//se utiliza para mandar una excepcion al sistema 
        }                                                                          //IllegalArgumentException es una clase que proviene de java.lang y sirve para indicar argumento ilegal
        if (placa.length()>6 || placa.length()<1){
            throw new IllegalArgumentException("placa con tamano invalido!");
        }
        else{placaCarros.add(placa);}
    }
    
    public long getTarjeta(){
        return tarjeta;
    }
    public String getTarjetaVencimiento(){
        return tarjetaVencimiento;
    }
    public int getCodigoVal(){
        return codigoVal;
    }  

    public List<usuariosParqueo> getUsuariosParqueo(){
        return usuarios;
    }  
    
    public List<String> getCarros(){
        return placaCarros;
    }  
}
