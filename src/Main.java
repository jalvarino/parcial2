import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Clase Usuario
class Usuario {
    private String nombre;
    private String correo;
    private String contraseña;

    public Usuario(String nombre, String correo, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    @Override
    public String toString() {
        return "Usuario: " + nombre + ", Correo: " + correo;
    }
}

// Clase Validador
class Validador {
    // Validar nombre (solo letras y espacios)
    public static boolean validarNombre(String nombre) {
        String regex = "^[a-zA-Z\\s]+$";
        return nombre.matches(regex);
    }

    // Validar correo electrónico
    public static boolean validarCorreo(String correo) {
        String regex = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,}$";
        return correo.matches(regex);
    }

    // Validar contraseña (mínimo 8 caracteres, al menos una letra mayúscula, una minúscula, un número)
    public static boolean validarContraseña(String contraseña) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        return contraseña.matches(regex);
    }
}

// Clase SistemaRegistro
class SistemaRegistro {
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    // Método para registrar un usuario
    public void registrarUsuario(String nombre, String correo, String contraseña) {
        if (Validador.validarNombre(nombre) && Validador.validarCorreo(correo) && Validador.validarContraseña(contraseña)) {
            Usuario usuario = new Usuario(nombre, correo, contraseña);
            usuarios.add(usuario);
            System.out.println("¡Registro exitoso!");
        } else {
            System.out.println("Error en los datos ingresados. Revise las validaciones.");
        }
    }

    // Método para mostrar todos los usuarios registrados
    public void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            usuarios.forEach(System.out::println);
        }
    }
}

// Clase principal
public class Main {
    public static void main(String[] args) {
        SistemaRegistro sistema = new SistemaRegistro();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Mostrar usuarios registrados");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese correo: ");
                    String correo = scanner.nextLine();
                    System.out.print("Ingrese contraseña: ");
                    String contraseña = scanner.nextLine();
                    sistema.registrarUsuario(nombre, correo, contraseña);
                    break;
                case 2:
                    sistema.mostrarUsuarios();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
