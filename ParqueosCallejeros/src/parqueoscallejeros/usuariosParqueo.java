

package parqueoscallejeros;  //correo jvim woqi nqnz puyb
                                                   
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime; // Sirve para dar la hora del sistema
import javax.mail.*; // Se añadió la librería llib al proyecto para la API javax.mail
import java.util.Properties; 
import javax.mail.internet.*; // Importa las clases necesarias para MIME

/**
 * @author Kendall Ariel Rojas Cartin
 * 
 **/

public class UsuariosParqueo extends Usuarios{
    
    private long tarjeta; // Natural, 16 dígitos (tiene que ser única dentro de la aplicación)
    private String tarjetaVencimiento; // Mes y año, ej: 02/24 o 02/2024
    private int codigoVal; // Natural, 3 dígitos exactos

    private int tiempoGuardado; //minutos
    private int tiempoComprado; //minutos tambien
    
    private static List<UsuariosParqueo> usuarios = new ArrayList<>(); // Para guardar a estos usuarios 
    private List<String> placaCarros = new ArrayList<>(); //1 a 6 caracteres
    
    private LocalDateTime fechaIngreso; //LocalDateTime.now();    
    private LocalDateTime ingresoParqueo; 
    
    /**
     * Constructor de usuarios del Parqueo sin parametros
     **/
    
    UsuariosParqueo(){} //se realizo un constructor no personalizado para el uso de programas de este en otros.
    
    /**
     * Constructor que inicializa la clase usuariosParqueo con los valores correspondientes
     * @param pNombre String: El nombre del usuario de 2 a 20 caracteres
     * @param pApellidos String: Los apellidos del usuario de 1 a 40 caracteres
     * @param pTelefono int: numero de telefono del usuario de 8 digitos exactos
     * @param pCorreo String: Correo del usuario con el debido formato: parte1@parte2, parte1 y parte2 son strings con un tamaño mínimo de 3 caracteres cada uno 
     * @param pDireccionFisica String: Direccion fisica del usuario de 5 a 60 caracteres
     * @param pIdUsuario String: Identificacion del usuario 2 a 25 caracteres
     * @param pin String: pin del usuario de 4 caracteres exactos
     * @param pTarjeta long: Tarjeta del usuario (Tiene que ser numero natural de 15 digitos y unica)
     * @param pTarjetaVencimiento String: Vencimiento tarjeta Mes y año, ej: 02/24 o 02/2024
     * @param pCodigoVal int: Codigo de validacion numero natural de 3 digitos exactos
     * 
     **/
    
    UsuariosParqueo(String pNombre, String pApellidos, int pTelefono, String pCorreo, String pDireccionFisica, String pIdUsuario, String pin, long pTarjeta, String pTarjetaVencimiento, int pCodigoVal){
        super(pNombre, pApellidos, pTelefono, pCorreo, pDireccionFisica, pIdUsuario, pin);
        setTarjeta(pTarjeta);
        setTarjetaVencimiento(pTarjetaVencimiento);
        setCodigoVal(pCodigoVal);
        //sacarIngresoSistema();
        agregarUserParqueo(this);
            
    }
    
    /**
     * Funcion para parquear un vehiculo en la lista de parqueos
     * 
     * @param placa String: La placa del vehiculo con 1 a 6 caracteres
     * @param espacioParqueo int: El espacio del parqueo, cada espacio debe tener de 1 a 5 digitos es decir 1 a 99999
     * @param tiempoComprado int: El tiempo comprado por el usuario 
     * 
     * @throws IllegalArgumentException Si el espacio buscado no existe.
     * @throws IllegalArgumentException Si el espacio está ocupado.
     * @throws IllegalArgumentException Si hay un error con el tiempo comprado.
     *  
     **/
    
    public void Parquear(String placa, int espacioParqueo, int tiempoComprado){
        //Variables
        List<Integer> listaTiempo = new ArrayList<>();
        Parqueo parqueo = new Parqueo();
        
        List<Integer> listaEspacios = parqueo.getEspaciosParqueo(); //lista con todos los espacios que existen
        List<Parqueo> listaParqueos = parqueo.getListaParqueos(); //Lista con parqueos realizados y configuracion
        
        if (!listaEspacios.contains(espacioParqueo)){
            throw new IllegalArgumentException("El espacio buscado no existe!");
        }
        
        for (Parqueo i: listaParqueos){
            if (i.getEspacio() == espacioParqueo){ //tener en cuenta desaparcar (En proceso...)
                throw new IllegalArgumentException("El espacio: "+ espacioParqueo +" esta ocupado");
            }

        }
        //Tiempo de parqueo...
        try{
            listaTiempo = this.tiempoComprar(tiempoComprado);
        }
        catch(Exception e){
            throw new IllegalArgumentException("Error en el tiempo");
        }
        this.setTiempoComprado(listaTiempo.get(0));
        this.setTiempoGuardado(listaTiempo.get(1));
        
        //Parquear
        Parqueo parqueoUsuario = new Parqueo(placa, espacioParqueo);
        setCarro(placa);
        parqueoUsuario.setParqueo(parqueoUsuario);
        parqueoUsuario.establecerParqueos();
        this.setIngresoParqueo();
        parqueoUsuario.setHoraSalida(sacarTiempoRestante(placa));
        System.out.println("Ingreso: "+this.getIngresoParqueo()+" Salida: "+parqueoUsuario.getHoraSalida());

        System.out.println(sacarTiempoRestante(placa));
        System.out.println(parqueoUsuario.toStringParqueo());
        
        System.out.println("Tiempo comprado: "+ getTiempoComprado());
        System.out.println("Tiempo guardado: "+ getTiempoGuardado());
        
    }
    
    /**
     *Dado un numero para el tiempo calcula teniendo en cuenta el tiempo minimo el tiempo comprado y sobrante
     * @param tiempo int: Numero entero que determina el tiempo que el usuario quiere comprar
     * @return Retorna una List conformada por dos numeros el tiempor comprado index 0 y el tiempo guardado index 1
     * 
     * @throws IllegalArgumentException Si hay un error al conseguir el tiempo
     **/
    
    public List<Integer> tiempoComprar(int tiempo) {
        Parqueo parqueo = new Parqueo();
        int res = 0;
        try {
            for (Parqueo i : parqueo.getListaParqueos()) {
                if (i.getTiempoMinimoMinutos() != 0){
                    while (tiempo >= i.getTiempoMinimoMinutos()) {
                        tiempo -= i.getTiempoMinimoMinutos(); // Restar el tiempo mínimo
                        res += i.getTiempoMinimoMinutos();    // Acumular en res
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al conseguir");
        }

        // Crear una lista y agregar res y tiempo
        List<Integer> resultado = new ArrayList<>();
        resultado.add(res); //tiempo comprado
        resultado.add(tiempo); //tiempo sobrante o a guardar

        return resultado;
    }
    
    /**
     * Saca el tiempo restante que le queda a un carro buscandolo a traves de su placa
     * @param placa String: la placa del vehiculo
     * @return Retorna un LocalDateTime si encuentra el vehiculo y logra sacar el tiempo restante si no retorna null
     * 
     **/
    
    public LocalDateTime sacarTiempoRestante(String placa){
        Parqueo parqueo = new Parqueo();
        List<Parqueo> listaParqueos = parqueo.getListaParqueos();
        List<Integer> listaEspacios = parqueo.getEspaciosParqueo();
        
        // Verificar si ambas listas no son nulas
        if (listaParqueos != null && listaEspacios != null) {
            for (Parqueo i : listaParqueos){
                for (String k : this.getCarros()){
                    if (i.getPlaca().equals(k) && i.getPlaca().equals(placa)){
                        return this.getIngresoParqueo().plusMinutes(this.getTiempoComprado()).minusMinutes(getTiempoGuardado());
                    }
                }

            }
            
        }
        return null;
    }
    
    /**
     * Revisa los carros parqueados (Ademas si un vehiculo ya esta fuera del limite lo desaparca)
     * @return Retorna un string con la informacion de los carros en el parqueo
     **/
    
    public String revisarCarros() {                   
        Parqueo parqueo = new Parqueo();
        UsuariosParqueo usuarioParqueo = new UsuariosParqueo();
        LocalDateTime horaActual = LocalDateTime.now();
        List<Parqueo> listaParqueos = parqueo.getListaParqueos();
        List<Integer> listaEspacios = parqueo.getEspaciosParqueo();

        String placa;
        String revision = "";
        

        // Verificar si ambas listas no son nulas
        if (listaParqueos != null && listaEspacios != null) {
            for (Integer espacio : listaEspacios) { //Para cada espacio en la lista espacios

                for (Parqueo parqueoObj : listaParqueos) { //para cada parqueo en la lista parqueos

                    if (parqueoObj.getEspacio() == espacio) { //si el parqueo tiene asignado un espacio en lista espacios
                        revision += "\nEl espacio "+ espacio+ " del parqueo corresponde con " + parqueoObj.getEspacio()+"\n";
                        placa = parqueoObj.getPlaca(); //la placa del parqueo
                        revision += "Espacio con placa:  "+ placa+"\n";
                        for (UsuariosParqueo usuario : usuarioParqueo.getUsuariosParqueo()) { //Para cada usuario en los usuarios del parqueo

                            for (String carro : usuario.getCarros()) {
                                if (carro.equals(placa)) {
                                    LocalDateTime ingreso = usuario.getIngresoParqueo();
                                    if (parqueoObj.getHoraSalida() == null){
                                        revision += "El tiempo del parqueo es null (Arreglar).\n";
                                    }
                                    else if (ingreso.isAfter(parqueoObj.getHoraSalida())) {
                                        revision += "El tiempo de estacionamiento ya vencio!!.\n";
                                        revision += horaActual+" "+parqueoObj.getHoraSalida()+"\n";
                                        listaParqueos.remove(parqueoObj); //
                                    } 
                                    else if (ingreso.isBefore(parqueoObj.getHoraSalida())) {
                                        revision += "Aun no ha vencido.\n";
                                        revision += horaActual+" "+parqueoObj.getHoraSalida()+"\n";
                                    } 
                                    else {
                                        revision += "El tiempo de estacionamiento coincide con la hora actual.\n";
                                        revision += horaActual+" "+ingreso+"\n";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return revision;
    }
    
    /**
     * Revisa los vehiculos aparcados y si uno se excede entonces desaparca el vehiculo
     **/
    
    public void desaparcarAutomatico(){ //Realizar funcion de desaparcar automatico?
        Parqueo parqueo = new Parqueo();
        UsuariosParqueo usuarioParqueo = new UsuariosParqueo();
        LocalDateTime horaActual = LocalDateTime.now();
        List<Parqueo> listaParqueos = parqueo.getListaParqueos();
        List<Integer> listaEspacios = parqueo.getEspaciosParqueo();

        String placa;
        
        // Verificar si ambas listas no son nulas
        if (listaParqueos != null && listaEspacios != null) {
            for (Integer espacio : listaEspacios) { //Para cada espacio en la lista espacios

                for (Parqueo parqueoObj : listaParqueos) { //para cada parqueo en la lista parqueos

                    if (parqueoObj.getEspacio() == espacio) { //si el parqueo tiene asignado un espacio en lista espacios
                        System.out.println("El espacio "+ espacio+ " del parqueo corresponde con " + parqueoObj.getEspacio()+"\n");
                        placa = parqueoObj.getPlaca(); //la placa del parqueo
                        System.out.println("Espacio con placa:  "+ placa+"\n");
                        for (UsuariosParqueo usuario : usuarioParqueo.getUsuariosParqueo()) { //Para cada usuario en los usuarios del parqueo

                            for (String carro : usuario.getCarros()) {
                                if (carro.equals(placa)) {
                                    LocalDateTime ingreso = usuario.getIngresoParqueo();
                                    if (parqueoObj.getHoraSalida() != null){
                                        if (ingreso.isAfter(parqueoObj.getHoraSalida())) {
                                            System.out.println( "El tiempo de estacionamiento ya vencio Sera desaparcado!!.\n");
                                            System.out.println( horaActual+" "+parqueoObj.getHoraSalida()+"\n");
                                            listaParqueos.remove(parqueoObj); //
                                        } 
                                    }

                                    
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Agrega un usuariosParqueo a la lista de usuarios del parqueo 
     * @param user UsuariosParqueo: Usuario del parqueo a agregar
     * 
     * @throws IllegalArgumentException Si la tarjeta no es unica
     **/
    
    public void agregarUserParqueo(UsuariosParqueo user){
        for (UsuariosParqueo i : usuarios){
            if (i.getTarjeta() == user.getTarjeta()){
                throw new IllegalArgumentException("Tarjeta debe ser unica!");
            }
        }

        usuarios.add(user);
    } 
    
    /**
     * Convierte el usuario del parqueo en un string
     * @return String de los datos del usuario
     **/
    
    public String toStringUserParqueo(){
        return "UsuarioParqueo: " +  getNombre() 
                             + " "+  getApellidos() 
                             +" "+   getTelefono() 
                             + " " + getCorreo() 
                             +" "+   getDireccionFisica() 
                             +" "+   getIdUsuario() 
                             + " " + getPin() 
                             + " " + getTarjeta() 
                             + " " + getTarjetaVencimiento() 
                             + " " + getCodigoVal() 
                             + " " + getTiempoGuardado() 
                             +" "+   getTiempoComprado() 
                             + "\n" + toStringCarros();
    }
    
    /**
     * Regresa un string de cada usuario en lka lista de usuarios
     * @return String de los usuarios
     **/
    
    public String toStringUsuariosParqueo(){
        String res = "";
        for (Usuarios i : getUsuarios()){
            if(i instanceof UsuariosParqueo){
                UsuariosParqueo parqueo = (UsuariosParqueo) i;
                res += parqueo.toStringUserParqueo() + "\n";
            }
            }
        return res;
    }
    
    
    /**
     * Funcion para enviar un correo con datos desde el correo del proyecto gmail hacia el correo que indiquen
     * @param correo la direccion de correo hacia donde se envia
     * @param datosEnviar los datos que se enviaran en el correo
     * 
     * @return Informacion de si el correo fue exitoso o no en String
     **/
    
    public String enviarCorreoDatos(String correo, String datosEnviar) {
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
     * Datos del usuarios del parqueo
     * @param usuario el usuario del cual se sacaran los datos
     * @return Se retornan los datos del usuario concatenados
     **/
    
    public String datosUsuario(UsuariosParqueo usuario){ //datos para cuando se actualiza el usuario
        String datos = "";
        for (Usuarios i : getUsuarios()){
            if (i instanceof UsuariosParqueo || i.getPin() == this.getPin() || i.getIdUsuario() == this.getIdUsuario()){
                datos = "Nombre: " + usuario.getNombre() +"\n"
                       +"Apellidos: " +usuario.getApellidos()+ "\n"
                       +"Telefono:  "+usuario.getTelefono()+"\n"
                       +"Correo:    "+usuario.getCorreo()+"\n"
                       +"Direccion: "+usuario.getDireccionFisica()+"\n"
                       +"ID: "+usuario.getIdUsuario()+"\n"
                       +"PIN: "+usuario.getPin()+"\n"
                       +"Tarjeta:   "+usuario.getTarjeta()+"\n"
                       +"Vencimiento de Tarjeta: "+usuario.getTarjetaVencimiento()+"\n"
                       +"Codigo de Tarjeta: "+usuario.getCodigoVal()+" "+"\n"
                       +"Tiempo guardado: " + usuario.getTiempoGuardado() + "\n"
                       +"Fecha ingreso: " + usuario.getFechaIngreso() + "\n";
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
    
     /**
     * toString de los carros del usuario
     * @return String de los carros del usuario
     **/
    
    public String toStringCarros(){
        int contador = 0;
        String carros = "";
        for (String i: placaCarros){
            contador+=1;
            carros += "Carro: "+contador+" Placa: "+i+"\n";
            ;
        }
        return carros;
    }

    //setters 
    
     /**
     * Obtiene la fecha actual y la coloca como fecha de ingreso del usuario
     **/
    
    public void setFechaIngreso() {
        LocalDateTime fechaActual = LocalDateTime.now(); // Obtiene la fecha y hora actual
        fechaIngreso = fechaActual; // Asigna la fecha de ingreso
        System.out.println(fechaIngreso); // Imprime la fecha de ingreso
    }
    
     /**
     * Obtiene la fecha actual y la coloca como fecha de ingreso del usuario al parqueo
     **/
    
    public void setIngresoParqueo() {
        LocalDateTime fechaActual = LocalDateTime.now(); // Obtiene la fecha y hora actual
        ingresoParqueo = fechaActual; // Asigna la fecha de ingreso
        System.out.println(ingresoParqueo); // Imprime la fecha de ingreso
    }
    
    /**
     * Set el tiempo guardado
     * @param pTiempoGuardado el tiempo a guardar
     **/
    
    public void setTiempoGuardado(int pTiempoGuardado){
        tiempoGuardado += pTiempoGuardado;
    }
    
    /**
     * Set el tiempo comprado y lo acumula Ej: 0 -> 120 -> 150 dependiendo de las compras de tiempo para despues recolectar cuanto cobrar
     * @param pTiempoComprado el tiempo que se esta comprando
     **/
    
    public void setTiempoComprado(int pTiempoComprado){ 
        
        tiempoComprado += pTiempoComprado;
        
    }
    
    /**
     * Set tarjeta del usuario
     * @param pTarjeta long: la tarjeta con su cnatidad de tamano indicado
     * 
     * @throws IllegalArgumentException Si la tarjeta es invalida
     * @throws IllegalArgumentException Si la tarjeta es mayor a 16 caracteres
     **/
    
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
    
    /**
     * Set la fecha de vencimiento de la tarjeta formato Ej: "01/12"
     * @param pTarjetaVencimiento String: La tarjeta a ingresar
     * 
     * @throws IllegalArgumentException Si el formato es invalido
     **/
    
    public void setTarjetaVencimiento(String pTarjetaVencimiento){
         String regex = "(0?[1-9]|1[0-2])/(\\d{1,2})";//formato de tarjeta Ej: 02/24 0? significa que puede ir solo #[inicio-fin] rango de numeros que pueden ir a la par de #

        if (!pTarjetaVencimiento.matches(regex)) {
            throw new IllegalArgumentException("Tarjeta V Formato invalido. '0-12/0-...'");
        }
        else{tarjetaVencimiento = pTarjetaVencimiento;}
        
    }
    
    /**
     * Set codigo de validacion de la tarjeta
     * @param pCodigoVal int: Codigo de la tarjeta
     * @throws IllegalArgumentException Si tiene una cantidad erronea de caracteres
     **/
    
    public void setCodigoVal(int pCodigoVal){
        String cantidad = String.valueOf(pCodigoVal);
        if (cantidad.length()!=3 || cantidad == null){
            throw new IllegalArgumentException("El Codigo de validacion tiene una cantidad erronea de caracteres o es invalido");
        }
        else{codigoVal = pCodigoVal;}
    }
    
    /**
     * Set placa del carro
     * @param placa String: La placa del vehiculo
     * @throws IllegalArgumentException Si la placa es invalida
     * @throws IllegalArgumentException Si tiene un tamano invalido
     **/
    
    public void setCarro( String placa){ 
        if (placa == null || placa.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("placa invalida!");//se utiliza para mandar una excepcion al sistema 
        }                                                                          //IllegalArgumentException es una clase que proviene de java.lang y sirve para indicar argumento ilegal
        if (placa.length()>6 || placa.length()<1){
            throw new IllegalArgumentException("placa con tamano invalido!");
        }
        else{placaCarros.add(placa);}
    }
    
    //getters
    
    /**
     * Get de la tarjeta
     * @return long tarjeta
     **/
    
    public long getTarjeta(){
        return tarjeta;
    }
    
    /**
     * Get la tarjeta de vencimiento
     * @return String tarjetaVencimiento
     **/
    
    public String getTarjetaVencimiento(){
        return tarjetaVencimiento;
    }
    
    /**
     * Get codigo de validacion
     * @return int codigoVal
     **/
    
    public int getCodigoVal(){
        return codigoVal;
    }  

    /**
     * Get lista de usuarios del parqueo
     * @return List UsuariosParqueo usuarios
     **/
    
    public List<UsuariosParqueo> getUsuariosParqueo(){
        return usuarios;
    }  
    
    /**
     * Get lista de carros del usuario
     * @return List String placaCarros
     **/
    
    public List<String> getCarros(){
        return placaCarros;
    }  
    
    /**
     * Get fecha de ingreso
     * @return LocalDateTime fechaIngreso
     **/
    
    public LocalDateTime getFechaIngreso(){
        return fechaIngreso;
    }
    
    /**
     * Get fecha y hora del ingreso al parqueo
     * @return LocalDateTime ingresoParqueo
     **/
    
    public LocalDateTime getIngresoParqueo(){
        return ingresoParqueo;
    } 
    
    /**
     * Get tiempo guardado del usuario
     * @return int tiempoGuardado
     **/
    
    public int getTiempoGuardado(){
        return tiempoGuardado;
    }
    
    /**
     * Get tiempo comprado del usuario
     * @return int tiempoComprado
     **/
    
    public int getTiempoComprado(){
        return tiempoComprado;
    }
    
}
