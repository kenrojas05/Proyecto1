
package parqueoscallejeros;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime; //sirve para dar la hora del sistema
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message; // libreria para envio de mails
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author Kendall Ariel Rojas Cartin
 **/

public class Administrador extends Usuarios {
    
    private static final long serialVersionUID = 1L; //version de serializacion
    
    private String pinAdmin;
    private String idUsuarioAdmin;
    
    private LocalDateTime ingresoAdmin; 
    private static List<Administrador> listaAdmins = new ArrayList(); 
   
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
        listaAdmins.add(this);
    }
    
    /**
     * Configurar el parqueo
     * @param pPrecioHora precio por hora
     * @param pHoraInicio hora de inicio
     * @param pHoraFinal hora de cierre o finalizacion
     * @param pTiempoMinimoMinutos tiempo minimo a comprar en minutos
     * @param pCostoMulta costo de la multa
     **/
    
    public void configurarParqueo(int pPrecioHora, String pHoraInicio, String pHoraFinal, int pTiempoMinimoMinutos, int pCostoMulta){
        List<Parqueo> parqueos = Parqueo.getListaParqueos();

        if (parqueos.isEmpty()){
            Parqueo confiParqueo = new Parqueo( pPrecioHora, pHoraInicio, pHoraFinal, pTiempoMinimoMinutos, pCostoMulta);
        }
        else {
            for (Parqueo i: parqueos){
                if (i.getPlaca()==null){
                    parqueos.remove(i);
                }
                i.setPrecioHora(pPrecioHora);
                i.setHoraInicio(pHoraInicio);
                i.setHoraFinal(pHoraFinal);
                i.setTiempoMinimoMinutos(pTiempoMinimoMinutos);
                i.setCostoMulta(pCostoMulta);
            }
        }
    }
    
    /**
     * Envia los datos por correo al administrador
     * @param correo el correo de este
     * @param datosEnviar los datos que se van a enviar
     * 
     * @return String un string que indica el resultado del envio
     **/
    
    public String enviarCorreoDatosAdmin(String correo, String datosEnviar) {
    String validacion;
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
            mensaje.setFrom(new InternetAddress("proyectokendall@gmail.com", "Parqueos Callejeros")); // desde quien se manda el correo
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(correo, this.getNombre())); // el destino del correo o destinatario InternetAdress(correo,persona)
            mensaje.setSubject("Datos del usuario: " + this.getNombre()); // El asunto o titulo del correo
            mensaje.setText(datosEnviar); // Todo el mensaje del correo

            // Enviar el mensaje
            Transport.send(mensaje);
            validacion = "Correo enviado exitosamente!";
            return validacion;

        } catch (AddressException e) {
            validacion = e.getMessage(); //error relacionado con la dirección del correo
            return validacion;
        } catch (MessagingException e) {
            validacion = e.getMessage(); // Manejo de errores relacionados con el envío de correo
            return validacion;
        } catch (UnsupportedEncodingException e) {
            validacion = e.getMessage(); // Manejo de errores relacionados con la codificación del mensaje
            return validacion;
        }
    }
    
    /**
     * Saca los datos del admin para enviar
     * @param admin el administrador
     * @return los datos en String
     **/
    
    public String datosAdmin(Administrador admin){ //datos para cuando se actualiza el usuario
        String datos = "";
        for (Usuarios i : getUsuarios()){
            if (i instanceof Administrador || i.getPin() == this.getPin() || i.getIdUsuario() == this.getIdUsuario()){
                admin.sacarIngreso();
                datos = "Nombre: " + admin.getNombre() +"\n"
                       +"Apellidos: " +admin.getApellidos()+ "\n"
                       +"Telefono:  "+admin.getTelefono()+"\n"
                       +"Correo:    "+admin.getCorreo()+"\n"
                       +"Direccion: "+admin.getDireccionFisica()+"\n"
                       +"ID: "+admin.getIdUsuario()+"\n"
                       +"PIN: "+admin.getPin()+"\n"
                       +"Fecha ingreso: " + admin.getIngresoAdmin() + "\n";
            }
        }
        
        return datos ;
    }
    
    /**
     * toString del admin
     * @return String con los datos del administrador concatenados
     **/
    
    public String toStringAdmin(){
        return "Admin: " + getNombre() + " "+ getApellidos() +" "+ getTelefono() + " " + getCorreo() +" "+ getDireccionFisica() +" "+ getIdAdmin() + " "; 
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
        System.out.println(inicio + " " + fin);
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
        System.out.println(inicio+" "+fin);
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
        System.out.println(espaciosAEliminar);
        listaEspacios.removeAll(espaciosAEliminar); // elimina todos los espacios en la lista
    }
    
    
    /**
     * Saca la hora de ingreso al sistema del admin 
     **/
    
    public void sacarIngreso(){
        LocalDateTime fechaActual = LocalDateTime.now();
        ingresoAdmin = fechaActual;
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
    
    /**
     * get del ingreso del admin
     * @return la hora en la que ingreso
     **/
    
    public LocalDateTime getIngresoAdmin(){
        return ingresoAdmin;
    }
    
    /**
     * get la lista de administradores
     * @return lista de admins listaAdmins
     **/
    
    public static List<Administrador> getListaAdmin(){
        return listaAdmins;
    }

}
