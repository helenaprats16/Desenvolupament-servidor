
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class EjerciciosStreamsApp {
    public static void main(String[] args) {
        Persona persona1 = new Persona(111,"Juan", "Zamora","Onteniente");
        Persona persona2 = new Persona(222,"Ana", "Zurrieta","Gandia");
        Persona persona3 = new Persona(333,"Ana", "Balbastre","Xativa");
        Persona persona4 = new Persona(333,"Pedro", "Gimenez","Gandia");
        List<Persona> lista = new ArrayList<Persona>(
            List.of(persona1,persona2,persona3,persona4));

        System.out.println("Lista de nombres no repetidos que contiene una 'A' ordenados:");
        //Deberá devolver:
        //Ana
        //Juan

        lista.stream().map(p->p.getNombre()).filter(p->p.contains("a")).sorted().distinct().forEach(System.out::println);
        
        
        System.out.println("Lista de apellidos no repetidos ordenados inversamente en una sola linea:");
        //Deberá devolver:
        //Zurrieta,Zamora,Gimenez,Balbastre
        System.out.println(lista.stream().map(p->p.getApellido()).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.joining(",")));


        
        
        System.out.println("Nombre y apellidos de la primera persona de Gandia si lo ordenamos por apellidos:");
        //Deberá devolver:
        //Pedro Gimenez

        lista.stream().filter(p-> p.getDireccion().equals("Gandia")).sorted(Comparator.comparing(Persona::getApellido)).findFirst().ifPresent(p-> System.out.println(p.getNombre()+ " "+p.getApellido()));

        
        
    }
}
