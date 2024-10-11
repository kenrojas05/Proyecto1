package parqueoscallejeros; //
                                                    //correo jvim woqi nqnz puyb

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime; // Sirve para dar la hora del sistema
import javax.mail.*; // Se añadió la librería lib al proyecto para la API javax.mail
import java.util.Properties; 
import javax.mail.internet.*; // Importa las clases necesarias para MIME

public class UsuariosParqueo extends Usuarios{
    private long tarjeta; // Natural, 16 dígitos (tiene que ser única dentro de la aplicación)
    private String tarjetaVencimiento; // Mes y año, ej: 02/24 o 02/2024
    private int codigoVal; // Natural, 3 dígitos exactos

    private int tiempoGuardado; //minutos
    private int tiempoComprado; //minutos tambien
    
    private static List<UsuariosParqueo> usuarios = new ArrayList<>(); // Para guardar a estos usuarios 
    private List<String> placaCarros = new ArrayList<>(); //1 a 6 caracteres
    
    private LocalDateTime fechaIngreso; //LocalDateTime.now();    
    private LocalDateTime ingresoParqueo; //DATOS DE PRUEBA
    
    UsuariosParqueo(){}
    
    UsuariosParqueo(String pNombre, String pApellidos, int pTelefono, String pCorreo, String pDireccionFisica, String pIdUsuario, String pin, long pTarjeta, String pTarjetaVencimiento, int pCodigoVal){
        super(pNombre, pApellidos, pTelefono, pCorreo, pDireccionFisica, pIdUsuario, pin);
        setTarjeta(pTarjeta);
        setTarjetaVencimiento(pTarjetaVencimiento);
        setCodigoVal(pCodigoVal);
        //sacarIngresoSistema();
        agregarUserParqueo(this);
            
    }
    public void Parquear(String placa, int espacioParqueo, int tiempoComprado){
        //Buscar espacio
        List<Integer> listaTiempo = new ArrayList<>();
        Parqueo parqueo = new Parqueo();
        List<Integer> listaEspacios = parqueo.getEspaciosParqueo(); //lista con todos los espacios que existen
        List<Parqueo> listaParqueos = parqueo.getListaParqueos(); //Lista con parqueos realizados y configuracion
        
        if (!listaEspacios.contains(espacioParqueo)){
            throw new IllegalArgumentException("El espacio buscado no existe!");
        }
        
        for (Parqueo i: listaParqueos){
            if (i.getEspacio() == espacioParqueo){ //tener en cuenta desaparcar
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
        Parqueo parqueoUsuario = new Parqueo(placa, espacioParqueo);
        setCarro(placa);
        parqueoUsuario.setParqueo(parqueoUsuario);
        parqueoUsuario.establecerParqueos();
        this.setIngresoParqueo();
        parqueoUsuario.setHoraSalida(sacarTiempoRestante(placa));
        System.out.println("Ingreso: "+this.getIngresoParqueo()+" Salida: "+parqueoUsuario.getHoraSalida());

        System.out.println(sacarTiempoRestante(placa));
        System.out.println(parqueoUsuario.toStringParqueo());
        System.out.println("Esta disponible yei!");
        System.out.println("Tiempo comprado: "+ getTiempoComprado());
        System.out.println("Tiempo guardado: "+ getTiempoGuardado());
        
    }
    
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
            throw new IllegalArgumentException("Error al comprar");
        }

        // Crear una lista y agregar res y tiempo
        List<Integer> resultado = new ArrayList<>();
        resultado.add(res);
        resultado.add(tiempo);

        return resultado;
    }
    
    
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
    
    
    public void revisarCarros() {                   //NO DEBE SER CON HORA ACTUAL DEBE SER CON LA HORA QUE ELLOS COMPRARON
        Parqueo parqueo = new Parqueo();
        UsuariosParqueo usuarioParqueo = new UsuariosParqueo();
        LocalDateTime horaActual = LocalDateTime.now();
        List<Parqueo> listaParqueos = parqueo.getListaParqueos();
        List<Integer> listaEspacios = parqueo.getEspaciosParqueo();

        String placa = "";

        // Verificar si ambas listas no son nulas
        if (listaParqueos != null && listaEspacios != null) {
            for (Integer espacio : listaEspacios) {
                System.out.println(espacio);

                for (Parqueo parqueoObj : listaParqueos) {
                    System.out.println(parqueoObj);

                    if (parqueoObj.getEspacio() == espacio) {
                        System.out.println(parqueoObj.getEspacio());
                        placa = parqueoObj.getPlaca();

                        for (UsuariosParqueo usuario : usuarioParqueo.getUsuariosParqueo()) {
                            System.out.println(usuario.toStringCarros());

                            for (String carro : usuario.getCarros()) {
                                if (carro.equals(placa)) {
                                    LocalDateTime ingreso = usuario.getIngresoParqueo();

                                    if (ingreso.isAfter(horaActual)) {
                                        System.out.println(horaActual+" "+ingreso);
                                        System.out.println("El tiempo de estacionamiento ya vencio.");
                                    } else if (ingreso.isBefore(horaActual)) {
                                        System.out.println("Aun no ha vencido.");
                                        System.out.println(horaActual+" "+ingreso);
                                    } else {
                                        System.out.println("El tiempo de estacionamiento coincide con la hora actual.");
                                        System.out.println(horaActual+" "+ingreso);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void desaparcar(){ //Sin terminar
        LocalDateTime horaInicial = ingresoParqueo;
    }
    
    public void agregarUserParqueo(UsuariosParqueo user){
        for (UsuariosParqueo i : usuarios){
            if (i.getTarjeta() == user.getTarjeta()){
                throw new IllegalArgumentException("Tarjeta debe ser unica!");
            }
        }

        usuarios.add(user);
    } 
    
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

    public void setFechaIngreso() {
        LocalDateTime fechaActual = LocalDateTime.now(); // Obtiene la fecha y hora actual
        fechaIngreso = fechaActual; // Asigna la fecha de ingreso
        System.out.println(fechaIngreso); // Imprime la fecha de ingreso
    }
    
    public void setIngresoParqueo() {
        LocalDateTime fechaActual = LocalDateTime.now(); // Obtiene la fecha y hora actual
        ingresoParqueo = fechaActual; // Asigna la fecha de ingreso
        System.out.println(ingresoParqueo); // Imprime la fecha de ingreso
    }
    
    public void enviarCorreoDatos(String correo, String nombre, UsuariosParqueo usuario) {
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
    
    public String datosUsuario(UsuariosParqueo usuario){
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

    //setters 
    public void setTiempoGuardado(int pTiempoGuardado){
        tiempoGuardado = pTiempoGuardado;
    }
    
    public void setTiempoComprado(int pTiempoComprado){ //FALTA LOGICA
        tiempoComprado = pTiempoComprado;
    }
    
    
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
         String regex = "(0?[1-9]|1[0-2])/(\\d{1,2})";//formato de tarjeta Ej: 02/24 0? significa que puede ir solo #[inicio-fin] rango de numeros que pueden ir a la par de #

        if (!pTarjetaVencimiento.matches(regex)) {
            throw new IllegalArgumentException("Tarjeta V Formato invalido. '0-12/0-...'");
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
    
    //getters
    
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
    
    public long getTarjeta(){
        return tarjeta;
    }
    public String getTarjetaVencimiento(){
        return tarjetaVencimiento;
    }
    public int getCodigoVal(){
        return codigoVal;
    }  

    public List<UsuariosParqueo> getUsuariosParqueo(){
        return usuarios;
    }  
    
    public List<String> getCarros(){
        return placaCarros;
    }  
    
    public LocalDateTime getIngresoParqueo(){
        
        return this.ingresoParqueo;
    } 
    
    public int getTiempoGuardado(){
        return tiempoGuardado;
    }
    
    public int getTiempoComprado(){
        return tiempoComprado;
    }
    
}
