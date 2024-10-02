
package parqueoscallejeros;

//Import nuevos investigados para la verificacion de patrones en un string (utilizado para el correo ☺)
import java.util.regex.Matcher; //operaciones de match (verificar si concuerdan) realizado en una secuencia que interpreta un patron
import java.util.regex.Pattern; // una expresion regular compilada (para ser usada en matcher en este caso); 

import java.util.ArrayList;
import java.util.List;

public class Usuarios {
    //variables
    private String nombre; //de 2 a 20 caracteres
    private String apellidos; //de 1 a 40 caracteres
    private int telefono; //de 8 digitos exactos
    private String correo; // string con formato parte1@parte2, parte1 y parte2 son strings con un tamaño mínimo de 3 caracteres cada uno 
    private String direccionFisica; //de 5 a 60 caracteres

    //identificar un usuario 
    private String idUsuario; //2 a 25 caracteres
    private String pin; // 4 caracteres exactos
    
    private static List<Usuarios> listaUsuarios = new ArrayList();
    
    Usuarios(String pNombre, String pApellidos, int pTelefono, String pCorreo, String pDireccionFisica, String pIdUsuario, String pin){
        setNombre(pNombre);
        setApellidos(pApellidos);
        setTelefono(pTelefono);
        setCorreo(pCorreo);
        setDireccionFisica(pDireccionFisica);
        setIdUsuario(pIdUsuario);
        setPin(pin);
        
        agregarUsuario(this);
    }
    
    public void agregarUsuario(Usuarios nuevoUsuario){
        for (Usuarios i : listaUsuarios){
            if (i.getPin() == nuevoUsuario.getPin()){
                throw new IllegalArgumentException("Usuario ya creado");
            }
        }

        listaUsuarios.add(nuevoUsuario);
    }    
    
    public String toString(){
        return "Usuario: " + getNombre() + " "+ getApellidos() +" "+ getTelefono() + " " + getCorreo() +" "+ getDireccionFisica() +" "+ getIdUsuario() + " " + getPin();
    }
    
    public String toStringUsuarios(){
        String res = "";
        for (Usuarios i : listaUsuarios){
            res += i.toString() + "\n";
        }
        return res;
    }
    
    //setters
    
    public void setNombre(String pNombre){ 
        if (pNombre == null || pNombre.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("El nombre esta vacio o es invalido");//se utiliza para mandar una excepcion al sistema 
        }                                                                          //IllegalArgumentException es una clase que proviene de java.lang y sirve para indicar argumento ilegal
        if (pNombre.length()<2 || pNombre.length()>20){
            throw new IllegalArgumentException("El nombre tiene una cantidad erronea de caracteres (20>nombre>2)");
        }
        else{nombre = pNombre;}
    }
    
    public void setApellidos(String pApellidos){ 
        if (pApellidos == null || pApellidos.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("Los appelidos estan vacios o es invalido");//se utiliza para mandar una excepcion al sistema 
        }                                                                          //IllegalArgumentException es una clase que proviene de java.lang y sirve para indicar argumento ilegal
        if (pApellidos.length()<1 || pApellidos.length()>40){
            throw new IllegalArgumentException("Los apellidos tienen una cantidad erronea de caracteres (40>apellidos>1)");
        }
        else{apellidos = pApellidos;}

    }
    public void setTelefono(int pTelefono){                                                                           //IllegalArgumentException es una clase que proviene de java.lang y sirve para indicar argumento ilegal
        String cantidad = String.valueOf(pTelefono);
        if (cantidad.length() != 8){
            throw new IllegalArgumentException("Telefono sin 8 digitos");
        }
        else{telefono = pTelefono;}
    }
    public void setCorreo(String pCorreo){ 
        if (pCorreo == null || pCorreo.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("El correo esta vacio o es invalido");//se utiliza para mandar una excepcion al sistema 
        }
        String formatoCorreo = "^.{3,}@.{3,}\\..{2,}$"; // ^ indica inicio de string, [...] caracteres permitidos \\ en regex el . se usa para indicar que cualquier caracter {...} caracteres $ final de cadena
        
        Pattern pattern = Pattern.compile(formatoCorreo); //lo compila
        Matcher matcher = pattern.matcher(pCorreo); // matcher con el correo
        if (matcher.matches() == false){
            throw new IllegalArgumentException("El correo es invalido");//se utiliza para mandar una excepcion al sistema 
        }
        else{correo = pCorreo;}
        
    }
    public void setDireccionFisica(String pDireccionFisica){ 
        if (pDireccionFisica == null || pDireccionFisica.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("Direccion vacia o invalida");//se utiliza para mandar una excepcion al sistema 
        }                                                                          //IllegalArgumentException es una clase que proviene de java.lang y sirve para indicar argumento ilegal
        if (pDireccionFisica.length()<5 || pDireccionFisica.length()>60){
            throw new IllegalArgumentException("Direccion con tamano invalido");
        }
        
        else{direccionFisica = pDireccionFisica;}
    }
    
    public void setIdUsuario(String pIdUsuario){
        if (pIdUsuario == null || pIdUsuario.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("El nombre esta vacio o es invalido");//se utiliza para mandar una excepcion al sistema 
        }                                                                          //IllegalArgumentException es una clase que proviene de java.lang y sirve para indicar argumento ilegal
        if (pIdUsuario.length()<2 || pIdUsuario.length()>25){
            throw new IllegalArgumentException("El nombre tiene una cantidad erronea de caracteres (20>nombre>2)");
        }
        else{idUsuario = pIdUsuario;}
    }
    
    public void setPin(String pin){
        if (pin.length()!=4 || pin == null){
            throw new IllegalArgumentException("El PIN tiene una cantidad erronea de caracteres o es invalido");
        }
        else{this.pin = pin;}
    }
    
    
    //getters
    
    public String getNombre(){
        return nombre;
    }
    public String getApellidos(){
        return apellidos;
    }
    public int getTelefono(){
        return telefono;
    }
    public String getCorreo(){
        return correo;
    }
    public String getDireccionFisica(){
        return direccionFisica;
    }
    public String getIdUsuario(){
        return idUsuario;
    }
    public String getPin(){
        return pin;
    }
    public List<Usuarios> getUsuarios(){
        return listaUsuarios;
    }

}
