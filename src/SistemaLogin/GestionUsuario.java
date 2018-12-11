package SistemaLogin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class GestionUsuario {

    private Login lg;

    public static void main(String[] args) throws IOException, NoSuchProviderException, NoSuchAlgorithmException {
        //Aca un ejemplo del proceso, primero creamos el directorio de usuarios
        Login.crearArchivoUsuario();
        //Y ahora creamos el usuario, en el archivo quedara su contraseña codificada y la sal
        Login.crearUsuario("Prometeo", "Fuego12345");
        Login.crearUsuario("Prometeo", "sadsad");
        //Esto queda como
        // Prometeo,f70fba0213f8c2c0fcf2e7d61cef0b97
    }
}
