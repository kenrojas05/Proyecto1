
package parqueoscallejeros;

//Import nuevos investigados para la verificacion de patrones en un string (utilizado para el correo ☺)
import java.util.regex.Matcher; //operaciones de match (verificar si concuerdan) realizado en una secuencia que interpreta un patron
import java.util.regex.Pattern; // una expresion regular compilada (para ser usada en matcher en este caso); 
import java.util.ArrayList; //Listas
import java.util.List;

import com.itextpdf.text.Document; //Para la generacion de pdf
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException; //para el manejo de archivos
import java.io.FileOutputStream;


/**
 * @author Kendall Ariel Rojas Cartin
 * 
 **/

public class Usuarios extends Parqueo{
    private static final long serialVersionUID = 1L; //version de serializacion
    
    //variables
    private String nombre; //de 2 a 20 caracteres
    private String apellidos; //de 1 a 40 caracteres
    private int telefono; //de 8 digitos exactos
    private String correo; // string con formato parte1@parte2, parte1 y parte2 son strings con un tamaño mínimo de 3 caracteres cada uno 
    private String direccionFisica; //de 5 a 60 caracteres

    //identificar un usuario 
    private String idUsuario; //2 a 25 caracteres
    private String pin; // 4 caracteres exactos
    
    private static List<Usuarios> listaUsuarios = new ArrayList(); //para guardar a cada cuenta/usuario
    
    /**
     * Constructo de usuario sin parametros
     **/
    
    Usuarios(){} //se realizo un constructor no personalizado para el uso de programas de este en otros.
    
    /**
     * Constructor de clase y ademas agrega el usuario a la lista (Usuario persona)
     * @param pNombre String: El nombre del usuario de 2 a 20 caracteres
     * @param pApellidos String: Los apellidos del usuario de 1 a 40 caracteres
     * @param pTelefono int: numero de telefono del usuario de 8 digitos exactos
     * @param pCorreo String: Correo del usuario con el debido formato: parte1@parte2, parte1 y parte2 son strings con un tamaño mínimo de 3 caracteres cada uno 
     * @param pDireccionFisica String: Direccion fisica del usuario de 5 a 60 caracteres
     * @param pIdUsuario String: Identificacion del usuario 2 a 25 caracteres
     * @param pin String: pin del usuario de 4 caracteres exactos
     * 
     **/
    
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
    
    /**
     * Genera un pdf con el nombre e informacion que se le pasen
     * @param informacion La informacion del documentio
     * @param nombre El nombre que tendra el PDF
     **/
    
    public static void reportePDF(String informacion, String nombre){
        Document document = new Document(); 
        try {
            //PdfWriter sirve para escribir en el archivo
            PdfWriter.getInstance(document, new FileOutputStream(nombre + ".pdf")); //crea el archivo con el nombre FOS sirve para dirigir la informacion al archivo digamos

            document.open();
            document.add(new Paragraph(informacion));
            document.close();

            System.out.println("se creo el pdf!");
        } catch (DocumentException | FileNotFoundException e) {
            throw new IllegalArgumentException ("Problema al generar PDF");
        }
    }
    
    /**
     * Para agregar el usuario a la lista de usuarios 
     * @param nuevoUsuario Usuarios: El usuario a agregar
     * @throws IllegalArgumentException Si el usuario ya fue creado
     **/
    
    public void agregarUsuario(Usuarios nuevoUsuario){
        for (Usuarios i : listaUsuarios){
            if (i.getPin().equals(nuevoUsuario.getPin()) && i.getIdUsuario().equals(nuevoUsuario.getIdUsuario())){

                throw new IllegalArgumentException("Usuario ya creado");
            }
        }

        listaUsuarios.add(nuevoUsuario);
    }
    
    /**
     * Verifica si el usuario existe y si es asi lo regresa casteado a su tipo de objeto/clase
     * @param id String: identificacion del usuario
     * @param pin String: pin del usuario
     * @return el usuario dependiendo del tipo (Admin/inspector/usuariosParqueo) o solo usuario de no poder
     **/
    
    public static Usuarios existeUsuario(String id, String pin){ //devuelve el usuario casteado a su tipo con su objeto
        Usuarios usuario = null;
        for (Usuarios i : listaUsuarios){
            if (i.getPin().equals(pin) && i.getIdUsuario().equals(id)){
                System.out.println("Usuario encontrado");
                usuario = i;
                if (usuario instanceof UsuariosParqueo){
                    UsuariosParqueo userParqueo = (UsuariosParqueo) usuario;
                    return userParqueo;
                }
                if (usuario instanceof Administrador){
                    Administrador admin = (Administrador) usuario;
                    return admin;
                }  
                if (usuario instanceof Inspector){ //Inspector no existe de momento...
                    Inspector inspec = (Inspector) usuario;
                    return inspec;
                }  
            }
        }
        return usuario;
    }
    
    /**
     * toString del usuario base
     * @return String del usuario base
     **/
    
    public String toString(){
        return "Usuario: " + getNombre() + " "+ getApellidos() +" "+ getTelefono() + " " + getCorreo() +" "+ getDireccionFisica() +" "+ getIdUsuario();
    }
    
    /**
     * toString de los usuarios del sistema
     * @return String usuarios
     **/
    
    public String toStringUsuarios(){
        String res = "";
        for (Usuarios i : listaUsuarios){
            res += i.toString() + "\n";
        }
        return res;
    }
    
    //setters
    
    /**
     * Set nombre del usuario
     * @param pNombre String: nombre 
     * @throws IllegalArgumentException Si el nombre esta vacio o es invalido
     * @throws IllegalArgumentException Si el nombre tiene una cantidad erronea de caracteres
     **/
    
    private void setNombre(String pNombre){ 
        if (pNombre == null || pNombre.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("El nombre esta vacio o es invalido");//manda una excepcion al sistema 
        }                                                                          
        if (pNombre.length()<2 || pNombre.length()>20){
            throw new IllegalArgumentException("El nombre tiene una cantidad erronea de caracteres (20>nombre>2)");
        }
        else{nombre = pNombre;}
    }
    
    /**
     * Set apellidos del usuario
     * @param pApellidos String: Apellidos del usuario
     * @throws IllegalArgumentException Si el apellido esta vacio o es invalido
     * @throws IllegalArgumentException Si el apellido tiene una cantidad erronea de caracteres
     **/
    
    public void setApellidos(String pApellidos){ 
        if (pApellidos == null || pApellidos.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("Los apellidos estan vacios o es invalido");//mandar una excepcion
        }                                                                          
        if (pApellidos.length()<1 || pApellidos.length()>40){
            throw new IllegalArgumentException("Los apellidos tienen una cantidad erronea de caracteres (40>apellidos>1)");
        }
        else{apellidos = pApellidos;}

    }
    
    /**
     * Set telefono del usuario
     * @param pTelefono int: El telefono del usuario
     * @throws IllegalArgumentException Si el telefono esta vacio
     * @throws IllegalArgumentException Si el telefono no tiene 8 digitos
     **/
    
    public void setTelefono(int pTelefono){  
         String cantidad = String.valueOf(pTelefono);
        if (cantidad == null || cantidad.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("Telefono vacio");//mandar una excepcion
        } 
        if (cantidad.length() != 8){
            throw new IllegalArgumentException("Telefono sin 8 digitos");
        }
        else{telefono = pTelefono;}
    }
    
    /**
     * Set el correo del usuario
     * @param pCorreo String: Correo del usuario
     * @throws IllegalArgumentException Si el correo esta vacio o es invalido
     * @throws IllegalArgumentException Si el correo es invalido
     **/
    
    public void setCorreo(String pCorreo){ 
        if (pCorreo == null || pCorreo.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("El correo esta vacio o es invalido");// mandar una excepcion 
        }
        String formatoCorreo = "^.{3,}@.{3,}\\..{2,}$"; // ^ indica inicio de string, [...] caracteres permitidos \\ en regex el . se usa para indicar que cualquier caracter {...} caracteres $ final de cadena
        
        Pattern pattern = Pattern.compile(formatoCorreo); //lo compila
        Matcher matcher = pattern.matcher(pCorreo); // matcher con el correo
        if (matcher.matches() == false){
            throw new IllegalArgumentException("El correo es invalido");//mandar una excepcion 
        }
        else{correo = pCorreo;}
        
    }
    
    /**
     * Set direccion fisica
     * @param pDireccionFisica
     * @throws IllegalArgumentException Si la direccion esta vacia o es invalida
     * @throws IllegalArgumentException Si el tamano es invalido
     **/
    
    public void setDireccionFisica(String pDireccionFisica){ 
        if (pDireccionFisica == null || pDireccionFisica.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("Direccion vacia o invalida");//mandar una excepcion
        }                                                                          
        if (pDireccionFisica.length()<5 || pDireccionFisica.length()>60){
            throw new IllegalArgumentException("Direccion con tamano invalido");
        }
        
        else{direccionFisica = pDireccionFisica;}
    }
    
    /**
     * Set id del usuario
     * @param pIdUsuario el string del usuario
     * @throws IllegalArgumentException Si el id esta vacio o es invalido
     * @throws IllegalArgumentException Si el id tiene una cantidad erronea de caracteres
     **/
    
    public void setIdUsuario(String pIdUsuario){
        if (pIdUsuario == null || pIdUsuario.isEmpty() ){  // || = or , .isEmpty() = true if length()=0
            throw new IllegalArgumentException("El ID esta vacio o es invalido");//mandar una excepcion
        }                                                                          
        if (pIdUsuario.length()<2 || pIdUsuario.length()>25){
            throw new IllegalArgumentException("El ID tiene una cantidad erronea de caracteres (20>nombre>2)");
        }
        else{idUsuario = pIdUsuario;}
    }
    
    /**
     * Set pin del usuario
     * @param pin String: pone el pin
     * @throws IllegalArgumentException Si el pin tiene una cantidad erronea de caracteres o es invalido
     **/
    
    public void setPin(String pin){
        if (pin.length()!=4 || pin == null){
            throw new IllegalArgumentException("El PIN tiene una cantidad erronea de caracteres o es invalido");
        }
        else{this.pin = pin;}
    }
    
    
    //getters
    
    /**
     * Get nombre
     * @return String: nombre
     **/
    
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Get Apellidos
     * @return String: Apellidos
     **/
    
    public String getApellidos(){
        return apellidos;
    }
    
    /**
     * Get telefono
     * @return int: telefono
     **/
    
    public int getTelefono(){
        return telefono;
    }
    
    /**
     * Get Correo
     * @return String correo
     **/
    
    public String getCorreo(){
        return correo;
    }
    
    /**
     * Get Direccion fisica
     * @return String: direccionFisica
     **/
    
    public String getDireccionFisica(){
        return direccionFisica;
    }
    
    /**
     * Get id usuario
     * @return String idUsuario
     **/
    
    public String getIdUsuario(){
        return idUsuario;
    }
    
    /**
     * Get pin
     * @return String pin
     **/
    
    public String getPin(){
        return pin;
    }
    
    /**
     * Get usuarios del sistema
     * @return List Usuarios listaUsuarios
     **/
    
    public static List<Usuarios> getUsuarios(){
        return listaUsuarios;
    }

}
