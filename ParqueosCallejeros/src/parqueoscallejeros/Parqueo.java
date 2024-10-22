
package parqueoscallejeros;

//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import java.io.*; //manejo de archivos

/**
 * @author Kendall Ariel Rojas Cartin
 **/

public class Parqueo implements Serializable { 
    private static final long serialVersionUID = 1L; //version de serializacion
    
    private String placa;
    private int espacio; 
    private LocalDateTime salida;
    
    private int precioHora; //entero PAR
    private String horaInicio; // hh:mm
    private String horaFinal; // hh:mm
    private int tiempoMinimoMinutos; // Ej: si es 30 se puede comprar minimo 30 y luego 60 90 120 150 etc..
    private int costoMulta; // cuando un vehiculo este en un parqueo y no lo pague
    
    final private static List<Integer> espaciosParqueo = new ArrayList(); //Espacios del parqueo (Pueden estar ocupados o no)
    final private static List<Parqueo> listaParqueos = new ArrayList(); //cada espacio debe tener de 1 a 5 digitos es decir 1 a 99999
    
    /**
     * Constructor del parqueo sin parametros
     **/
    
    Parqueo(){} //se realizo un constructor no personalizado para el uso de programas de este en otros.
    
    /**
     * Constructor de clase con los parametros completos
     * @param pPlaca String: placa  
     * @param pEspacio int: Espacio debe tener de 1 a 5 digitos es decir 1 a 99999
     * @param pPrecioHora int: Precio por hora
     * @param pHoraInicio String: Hora de inicio del parqueo
     * @param pHoraFinal String: Hora de finalizacion del parqueo
     * @param pTiempoMinimoMinutos int: Tiempo minimo para comprar en minutos
     * @param pCostoMulta int: Costo por multa
     **/
    
    Parqueo(String pPlaca, int pEspacio, int pPrecioHora, String pHoraInicio, String pHoraFinal, int pTiempoMinimoMinutos, int pCostoMulta){ //total
        setPlaca(pPlaca);
        setEspacio(pEspacio);
        setPrecioHora(pPrecioHora);
        setHoraInicio(pHoraInicio);
        setHoraFinal(pHoraFinal);
        setTiempoMinimoMinutos(pTiempoMinimoMinutos);
        setCostoMulta(pCostoMulta); 
    }
    
    /**
     * Constructor de clase solo con el parqueo y el espacio
     * @param pPlaca String placa
     * @param pEspacio int espacio
     **/
    
    Parqueo(String pPlaca, int pEspacio){ //Parquear
        setPlaca(pPlaca);
        setEspacio(pEspacio);
    }
    
    /**
     * Contructor de configuracion de la clase 
     * @param pPrecioHora int precio por hora
     * @param pHoraInicio String hora de inicio
     * @param pHoraFinal String hora de finalizacion
     * @param pTiempoMinimoMinutos int tiempo minimo en minutos
     * @param pCostoMulta int costo de la multa
     **/
    
    Parqueo(int pPrecioHora, String pHoraInicio, String pHoraFinal, int pTiempoMinimoMinutos, int pCostoMulta){
        setPrecioHora(pPrecioHora);
        setHoraInicio(pHoraInicio);
        setHoraFinal(pHoraFinal);
        setTiempoMinimoMinutos(pTiempoMinimoMinutos);
        setCostoMulta(pCostoMulta); 
        setParqueo(this);
    }
   
    /**
     * Guardar en un archivo txt la informacion usando programacion generica
     * @param listaObjetos lista de los objetos a guardar
     * @param pNombreArchivo nombre del archivo
     **/
    
    public static <T> void guardarEnArchivo(List<T> listaObjetos, String pNombreArchivo) { //Programacion generica vista para quiz4
        
        try (FileOutputStream archiFos = new FileOutputStream("files/"+pNombreArchivo);
             ObjectOutputStream ObjOs = new ObjectOutputStream(archiFos)) {

            for (T objeto : listaObjetos) {
                ObjOs.writeObject(objeto); // Escribir cada objeto en el archivo
            }


            System.out.println("Objetos guardados exitosamente.");

        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    /**
     * Subir la informacion del archivo txt al programa usando programacion generica
     * @param pNombreArchivo nombre del archivo de donde se extrae la informacion
     **/
    
    public static <T> void leerArchivo(String pNombreArchivo) { //Programacion generica vista para quiz4
        List<T> listaObjetos = new ArrayList<>(); //guardar objetos para despues imprimirlos
        try {
            FileInputStream archiFos = new FileInputStream("files/"+pNombreArchivo); // para escribir datos hacia un archivo (en este caso pNombreArchivo)
            ObjectInputStream ObjOis = new ObjectInputStream(archiFos);  // usando el outputstream sirve para escribir objetos java en este caso
            
            while (true) { //para leer todo el archivo
                try {
                    T obj = (T) ObjOis.readObject(); // leer cada objeto del archivo 
                    listaObjetos.add(obj); // agregar a la lista
                    } 
                catch (EOFException e) { // cuando se alcanza el final del archivo ( eof significa End Of Line)
                    break; // si ya no hay nada romper el bucle
                    }
            }
        } catch (IOException e) { //excepcion en los inputs o outputs
            System.err.println("Error al leer los objetos: " + e.getMessage()); // getMessage es para soltar solo el mensaje del error
            
        } catch (ClassNotFoundException e) { //si no se encontrara la clase al hacer casting
            System.err.println("Clase no encontrada: " + e.getMessage()); // getMessage es para soltar solo el mensaje del error
        }
        
        
        if (pNombreArchivo.equals("Parqueo.txt")){
            for (T i : listaObjetos){ //para imprimir el archivo leido
                Parqueo.getListaParqueos().add((Parqueo)i);
                System.out.println(i.toString());
            }        
        }
        
        if (pNombreArchivo.equals("Espacios.txt")){
            for (T i : listaObjetos){ //para imprimir el archivo leido
                Parqueo.getEspaciosParqueo().add((Integer)i);
                System.out.println(i.toString());
            }        
        }
        
        if (pNombreArchivo.equals("Usuarios.txt")){
            for (T i : listaObjetos){ //para imprimir el archivo leido
                Usuarios.getUsuarios().add((Usuarios)i);
                System.out.println(i.toString());
            }        
        }
        if (pNombreArchivo.equals("Administrador.txt")){
            for (T i : listaObjetos){ //para imprimir el archivo leido
                Administrador.getListaAdmin().add((Administrador)i);
                System.out.println(i.toString());
            }        
        }
        if (pNombreArchivo.equals("Inspector.txt")){
            for (T i : listaObjetos){ //para imprimir el archivo leido
               // Inspector.add((Inspector)i);
                System.out.println(i.toString());
            }        
        }
        if (pNombreArchivo.equals("UsuariosParqueo.txt")){
            for (T i : listaObjetos){ //para imprimir el archivo leido
                UsuariosParqueo.getUsuariosParqueo().add((UsuariosParqueo)i);
                System.out.println(i.toString());
            }        
        }


        
    }
    
    /**
     * Establece los parqueos para que todos tengan los mismos datos que dicte el admin como la multa el inicio y fin etc...
     **/
    
    public void establecerParqueos(){ //El administrador digita los datos para el parqueo pero por si hubiera un parqueo con datos unicos...
        for (Parqueo i : getListaParqueos()){
            if (i.getPrecioHora() != 0 || i.getHoraInicio() != null || i.getHoraFinal() != null || i.getTiempoMinimoMinutos() != 0 || i.getCostoMulta() == 0){
                for (Parqueo j : getListaParqueos()){
                    j.setPrecioHora(i.getPrecioHora()); 
                    j.setHoraInicio(i.getHoraInicio());
                    j.setHoraFinal(i.getHoraFinal());
                    j.setTiempoMinimoMinutos(i.getTiempoMinimoMinutos());
                    j.setCostoMulta(i.getCostoMulta());
                }
            }
            if (i.getPlaca() == null){
                getListaParqueos().remove(i);
            }
        }

    }
    
    /**
     * Revisa los carros parqueados (Ademas si un vehiculo ya esta fuera del limite lo desaparca)
     * @return Retorna un string con la informacion de los carros en el parqueo
     **/
    
    public String revisarCarros() {                   
        UsuariosParqueo usuarioParqueo = new UsuariosParqueo();
        LocalDateTime horaActual = LocalDateTime.now();
        List<Parqueo> listaParqueos = Parqueo.getListaParqueos();
        List<Integer> listaEspacios = Parqueo.getEspaciosParqueo();

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
        UsuariosParqueo usuarioParqueo = new UsuariosParqueo();
        LocalDateTime horaActual = LocalDateTime.now();
        List<Parqueo> listaParqueos = Parqueo.getListaParqueos();
        List<Integer> listaEspacios = Parqueo.getEspaciosParqueo();

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
     * toString del parqueo
     * @return String del parqueo con sus datos concatenados
     **/
    
    public String toStringParqueo(){
        return "Placa: " +getPlaca()+" Espacio:"+ getEspacio() +" Precio por hora: "+getPrecioHora()+" Hora de inicio:"+getHoraInicio()+" Hora de finalizacion:"+getHoraFinal()+" Tiempo minimo al comprar en minutos: "+getTiempoMinimoMinutos()+" Costo de multas: "+getCostoMulta()+" Tu hora de salida:" + getHoraSalida();
    }
    
    /**
     * toString de los espacios
     * @return String de cada espacio del parqueo
     **/
    
    public static String toStringEspacios(){
        String res = "";
        for (Integer i: espaciosParqueo){
            res += "Espacio: " + i + "\n";
        }
        return res;
    }
    
    /**
     * toString de todos los parqueos usados de la lista de parqueos
     * @return String de los parqueos
     **/
    
    public static String toStringListaParqueos(){
        String res = "";
        for (Parqueo i: listaParqueos){
            res += "Parqueo: " + i.toStringParqueo() + "\n";
        }
        return res;
    }
    
    /**
     * toString los espacios ocupados del parqueo
     * @return espacios ocupados 
     **/
    
    public static String toStringEspaciosOcupados(){
        String res = "";
        for (Parqueo i: listaParqueos){
            res += "Espacio ocupado: " + i.getEspacio()+ "\n";
        }
        return res;
    }
    
    /**
     * toString de los espacios disponibles del parqueo
     * @return espacios disponibles
     **/
    
    public static String toStringEspaciosDisponibles(){
        String res = "";
        List<Integer> numeros = new ArrayList();
        List<Integer> numerosOcupados = new ArrayList();
        for (Parqueo i : listaParqueos){
            numerosOcupados.add(i.getEspacio());
        }
        
        for (Integer i : espaciosParqueo){
            if (!numeros.contains(i) && !numerosOcupados.contains(i)){
                res += "Espacio disponible: " + i + "\n";
                numeros.add(i);
            }
        }
        
        return res;
    }
    
    /**
     * Regresa una lista con los {espacios, espacios disponibles, espacios ocupados}
     * @return lista con 3 datos formato int
     **/
    
    public static List<Integer> cantidadEspacios(){
        List<Integer> espacios = new ArrayList();
        int espaciosTotales = 0;
        int espaciosDisponibles = 0;
        int espaciosOcupados = 0;

        for (Parqueo i : listaParqueos){
            espaciosOcupados += 1;
        }
        
        for (Integer j : espaciosParqueo){
            espaciosTotales += 1;
        }
            
        espaciosDisponibles = espaciosTotales - espaciosOcupados;
        
        espacios.add(espaciosTotales);
        espacios.add(espaciosDisponibles);
        espacios.add(espaciosOcupados);
        
        return espacios;
    }
    
    /**
     * Buscar el parqueo por la placa
     * @return el Parqueo
     **/
    
    public static Parqueo buscarParqueoPlaca(String placa){
        for (Parqueo i : listaParqueos){
            if (i.getPlaca()==placa){
                return i;
            }
        }
        return null;
    }
    
    //setters
    
    /**
     * Set de la placa
     * @param pPlaca String de la placa
     * @throws IllegalArgumentException Si la placa tiene un tamano invalido
     **/
    
    public void setPlaca(String pPlaca){
        if (pPlaca.length()>6 || pPlaca.length()<1){
            throw new IllegalArgumentException("placa con tamano invalido!");
        }
        else{ placa = pPlaca;}
    }
    
    /**
     * Set de la hora de salida
     * @param pSalida LocalDateTime la hora de salida
     **/
    
    public void setHoraSalida(LocalDateTime pSalida){
        salida = pSalida;
    }
    
    /**
     * Set del espacio (lugar)
     * @param pEspacio int numero del espacio
     * @throws IllegalArgumentException Si el espacio no existe o es invalido
     * @throws IllegalArgumentException si el espacio tiene un tamano9 invalido
     **/
    
    public void setEspacio(int pEspacio){
        if (!this.getEspaciosParqueo().contains(pEspacio)){
            throw new IllegalArgumentException("Espacio no existe o no es valido!");
        }
        if (String.valueOf(pEspacio).length()>5 || String.valueOf(pEspacio).length()<1){
            throw new IllegalArgumentException("espacio con tamano invalido!");
        }
        else{ 
            espacio = pEspacio;
        }
    }
    
    /**
     * Set del precio por hora
     * @param pPrecio int el precio por hora
     * @throws IllegalArgumentException Si el precio esta vacio o es invalido
     * @throws IllegalArgumentException Si NO es par
     **/
    
    public void setPrecioHora(int pPrecio){ //entero par
        if (String.valueOf(pPrecio) == null || String.valueOf(pPrecio).isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("El precio esta vacio o es invalido");//se utiliza para mandar una excepcion al sistema 
        }                                                                          //IllegalArgumentException es una clase que proviene de java.lang y sirve para indicar argumento ilegal
        if (pPrecio % 2 != 0){
            throw new IllegalArgumentException("Tiene que ser entero par");
        }
        else{precioHora = pPrecio;}
    }
    
    /**
     * Set de hora de inicio
     * @param pHoraInicio String hora de inicio del parqueo
     * @throws IllegalArgumentException Si la hora de inicio esta vacia o es invalida
     * @throws IllegalArgumentException Si el formato no es el correcto
     **/
    
    public void setHoraInicio(String pHoraInicio){
        String regex = "(0?[0-9]|1[0-9]|2[0-3]):(0?[0-9]|[1-5][0-9])"; //formato de horas: 0? significa que puede ir solo #[inicio-fin] rango de numeros que pueden ir a la par de # 0-24:0-59
        if ( pHoraInicio == null || pHoraInicio.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("La hora de inicio esta vacia o es invalida");//se utiliza para mandar una excepcion al sistema 
        }                                                                          //IllegalArgumentException es una clase que proviene de java.lang y sirve para indicar argumento ilegal
        if (!String.valueOf(pHoraInicio).matches(regex)){ //recordar ! es not
            throw new IllegalArgumentException("Formato Ej: 18/20");
        }
        else{horaInicio = pHoraInicio;}
    }
    
    /**
     * Set de la hora de finalizacion del parqueo
     * @param pHoraFinal String hora de finalizacion
     * @throws IllegalArgumentException Si la hora final esta vacia o es invalida
     * @throws IllegalArgumentException Si el formato es invalido
     **/
    
    public void setHoraFinal(String pHoraFinal){
        String regex = "(0?[0-9]|1[0-9]|2[0-3]):(0?[0-9]|[1-5][0-9])"; //formato de horas: 0? significa que puede ir solo #[inicio-fin] rango de numeros que pueden ir a la par de # 0-24:0-59
        if ( pHoraFinal == null || pHoraFinal.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("Hora final esta vacia o es invalido");//se utiliza para mandar una excepcion al sistema 
        }                                                                          //IllegalArgumentException es una clase que proviene de java.lang y sirve para indicar argumento ilegal
        if (!pHoraFinal.matches(regex)){ //recordar ! es not
            throw new IllegalArgumentException("Formato Ej: 23/58");
        }
        else{horaFinal = pHoraFinal;}
    }
    
    /**
     * Set del tiempo minimo por minutos comprados
     * @param pTiempoMinimoMinutos int tiempo minimo que se puede comprar por minutos
     * @throws IllegalArgumentException El tiempo minimo es invalido
     **/
    
    public void setTiempoMinimoMinutos(int pTiempoMinimoMinutos){ //si es 5 entonces 5 10 15 si es 20 entonces 20 40 60
        if(String.valueOf(pTiempoMinimoMinutos) == null || String.valueOf(pTiempoMinimoMinutos).isEmpty() ){
            throw new IllegalArgumentException("Tiempo minimo invalido");
        }
        else{tiempoMinimoMinutos = pTiempoMinimoMinutos;}
    }
    
    /**
     * Set del costo por multa
     * @param pCostoMulta int costo por multa
     * @throws IllegalArgumentException Si la multa es invalida
     **/
    
    public void setCostoMulta(int pCostoMulta){ //se aplica cuando un carro esta en un parque y no paga
        if(String.valueOf(pCostoMulta) == null || String.valueOf(pCostoMulta).isEmpty() ){
            throw new IllegalArgumentException("Multa invalida");
        }
        else{costoMulta = pCostoMulta;}
    }
    
    /**
     * Set de los espacios del parqueo a la lista de espacios
     * @param espacioAgregar
     * @throws IllegalArgumentException Si el espacio ya existe en el parqueo
     **/
    
    public void setEspaciosParqueo(int espacioAgregar){
        List<Integer> espacios = this.getEspaciosParqueo();
        for (Integer i : espacios){
            if (i == espacioAgregar){
                throw new IllegalArgumentException("Espacio ya existente");
            }
        }
        espacios.add(espacioAgregar);
    }
    
    /**
     * Set de un parqueo en la lista de parqueos (un parqueo ocupado)
     * @param parqueo Parqueo el parqueo a poner
     * @throws IllegalArgumentException Si el parqueo ya existe
     **/
    
    public void setParqueo(Parqueo parqueo){
        List<Parqueo> lista = this.getListaParqueos();
        for (Parqueo i : lista){
            if (i == parqueo || i.getEspacio() == parqueo.getEspacio()){ //lo segundo talvez borrarlo
                throw new IllegalArgumentException("Parqueo ya existente");
            }
        }
        lista.add(parqueo);
    }
    
    //getters
    
    /**
     * Get placa
     * @return String de la placa
     **/
    
    public String getPlaca(){
        return placa;
    }
    
    /**
     * Get hora de salida del parqueo
     * @return LocalDateTime salida
     **/
    
    public LocalDateTime getHoraSalida(){
        return salida;
    }
    
    /**
     * Get espacio (lugar)
     * @return int espacio
     **/
    
    public int getEspacio(){
        return espacio;
    }
    
    /**
     * Get precio por hora
     * @return int precioHora
     **/
    
    public int getPrecioHora(){
        return precioHora;
    }
    
    /**
     * Get hora de inicio
     * @return String horaInicio
     **/
    
    public String getHoraInicio(){
        return horaInicio;
    }
    
    /**
     * Get hora de finalizacion
     * @return String horaFinal
     **/
    
    public String getHoraFinal(){
        return horaFinal;
    }
    
    /**
     * Get el tiempo minimo en minutos
     * @return int tiempoMinimoMinutos
     **/
    
    public int getTiempoMinimoMinutos(){
        return tiempoMinimoMinutos;
    }
    
    /**
     * Get el costo de la multa
     * @return int costoMulta
     **/
    
    public int getCostoMulta(){
        return costoMulta;
    }
    
    /**
     * Get la lista de parqueos Static
     * @return List Parqueo listaParqueos
     **/
    
    public static List<Parqueo> getListaParqueos(){
        return listaParqueos;
    }
    
    /**
     * Get espacios del parqueo Static
     * @return List Integer espaciosParqueo
     **/
    
    public static List<Integer> getEspaciosParqueo(){
        return espaciosParqueo;
    }
}
