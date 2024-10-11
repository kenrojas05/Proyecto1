
package parqueoscallejeros;

//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Parqueo {
    private String placa;
    private int espacio; 
    private LocalDateTime salida;
    
    private int precioHora; //entero PAR
    private String horaInicio; // hh:mm
    private String horaFinal; // hh:mm
    private int tiempoMinimoMinutos; // Ej: si es 30 se puede comprar minimo 30 y luego 60 90 120 150 etc..
    private int costoMulta; // cuando un vehiculo este en un parqueo y no lo pague
    
    final private static List<Integer> espaciosParqueo = new ArrayList(); //Espacios del parqueo (Pueden estar ocupados o no)
    final private static List<Parqueo> listaParqueos = new ArrayList(); //cada entero debe tener de 1 a 5 digitos es decir 1 a 99999
    
    Parqueo(){}
    
    Parqueo(String pPlaca, int pEspacio, int pPrecioHora, String pHoraInicio, String pHoraFinal, int pTiempoMinimoMinutos, int pCostoMulta){ //total
        setPlaca(pPlaca);
        setEspacio(pEspacio);
        setPrecioHora(pPrecioHora);
        setHoraInicio(pHoraInicio);
        setHoraFinal(pHoraFinal);
        setTiempoMinimoMinutos(pTiempoMinimoMinutos);
        setCostoMulta(pCostoMulta); 
    }
    
    Parqueo(String pPlaca, int pEspacio){ //Parquear
        setPlaca(pPlaca);
        setEspacio(pEspacio); 
    }
    
    Parqueo(int pPrecioHora, String pHoraInicio, String pHoraFinal, int pTiempoMinimoMinutos, int pCostoMulta){ //Basico
        setPrecioHora(pPrecioHora);
        setHoraInicio(pHoraInicio);
        setHoraFinal(pHoraFinal);
        setTiempoMinimoMinutos(pTiempoMinimoMinutos);
        setCostoMulta(pCostoMulta); 
    }
    
    public void setHoraSalida(LocalDateTime pSalida){
        salida = pSalida;
    }
    
    public LocalDateTime getHoraSalida(){
        return salida;
    }
    
    
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
        }
    }
    
    public String toStringParqueo(){
        return getPlaca()+" "+ getEspacio() +" "+getPrecioHora()+" "+getHoraInicio()+" "+getHoraFinal()+" "+getTiempoMinimoMinutos()+" "+getCostoMulta()+" " ;
    }
    
    public String toStringEspacios(){
        String res = "";
        for (Integer i: espaciosParqueo){
            res += "Espacio: " + i + "\n";
        }
        return res;
    }
    
    public String toStringListaParqueos(){
        String res = "";
        for (Parqueo i: listaParqueos){
            res += "Parqueo: " + i.toStringParqueo() + "\n";
        }
        return res;
    }
    
    
    //setters 
    public void setPlaca(String pPlaca){
        if (pPlaca.length()>6 || pPlaca.length()<1){
            throw new IllegalArgumentException("placa con tamano invalido!");
        }
        else{ placa = pPlaca;}
    }
    
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
    public void setPrecioHora(int pPrecio){ //entero par
        if (String.valueOf(pPrecio) == null || String.valueOf(pPrecio).isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("El nombre esta vacio o es invalido");//se utiliza para mandar una excepcion al sistema 
        }                                                                          //IllegalArgumentException es una clase que proviene de java.lang y sirve para indicar argumento ilegal
        if (pPrecio % 2 != 0){
            throw new IllegalArgumentException("Tiene que ser entero par");
        }
        else{precioHora = pPrecio;}
    }
    
    public void setHoraInicio(String pHoraInicio){
        String regex = "(0?[0-9]|1[0-9]|2[0-3]):(0?[0-9]|[1-5][0-9])"; //formato de horas: 0? significa que puede ir solo #[inicio-fin] rango de numeros que pueden ir a la par de # 0-24:0-59
        if ( pHoraInicio == null || pHoraInicio.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("El nombre esta vacio o es invalido");//se utiliza para mandar una excepcion al sistema 
        }                                                                          //IllegalArgumentException es una clase que proviene de java.lang y sirve para indicar argumento ilegal
        if (!String.valueOf(pHoraInicio).matches(regex)){ //recordar ! es not
            throw new IllegalArgumentException("Formato Ej: 18/20");
        }
        else{horaInicio = pHoraInicio;}
    }
    
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
    
    public void setTiempoMinimoMinutos(int pTiempoMinimoMinutos){ //si es 5 entonces 5 10 15 si es 20 entonces 20 40 60
        if(String.valueOf(pTiempoMinimoMinutos) == null || String.valueOf(pTiempoMinimoMinutos).isEmpty() ){
            throw new IllegalArgumentException("Tiempo minimo invalido");
        }
        else{tiempoMinimoMinutos = pTiempoMinimoMinutos;}
    }
    public void setCostoMulta(int pCostoMulta){ //se aplica cuando un carro esta en un parque y no paga
        if(String.valueOf(pCostoMulta) == null || String.valueOf(pCostoMulta).isEmpty() ){
            throw new IllegalArgumentException("Multa invalida");
        }
        else{costoMulta = pCostoMulta;}
    }
    
    public void setEspaciosParqueo(int espacioAgregar){
        List<Integer> espacios = this.getEspaciosParqueo();
        for (Integer i : espacios){
            if (i == espacioAgregar){
                throw new IllegalArgumentException("Espacio ya existente");
            }
        }
        espacios.add(espacioAgregar);
    }
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
    
    public String getPlaca(){
        return placa;
    }
    public int getEspacio(){
        return espacio;
    }
    public int getPrecioHora(){
        return precioHora;
    }
    public String getHoraInicio(){
        return horaInicio;
    }
    public String getHoraFinal(){
        return horaFinal;
    }
    public int getTiempoMinimoMinutos(){
        return tiempoMinimoMinutos;
    }
    public int getCostoMulta(){
        return costoMulta;
    }
    public List<Parqueo> getListaParqueos(){
        return listaParqueos;
    }
    public List<Integer> getEspaciosParqueo(){
        return espaciosParqueo;
    }
}
