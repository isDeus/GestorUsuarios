package SistemaLogin;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Scanner;

public class Login {

    private static Scanner sc;
    private static Scanner x;

    public static void main(String[] args) throws IOException, NoSuchProviderException, NoSuchAlgorithmException {
        String username = "Bob69";
        String password = "123";
        String filepath = "D:\\Users\\Mauricio\\Desktop\\usuario.txt";
        verificarLogin(username, password, filepath);
        System.out.println(System.getProperty("user.home"));
        crearArchivoAdmin();
        editarUsuario("Bob34", filepath, "6969");
        eliminarUsuario(filepath, "OscarGay");
    }

    public static void verificarLogin(String usuario, String contraseña, String direccionArchivo) {

        boolean found = false;
        String tempUsername = "";
        String tempPassword = "";

        try {
            sc = new Scanner(new File(direccionArchivo));
            sc.useDelimiter("[,\n]");

            while (sc.hasNext() && !found) {
                tempUsername = sc.next();
                tempPassword = sc.next();

                if (tempUsername.trim().equals(usuario.trim()) && tempPassword.trim().equals(contraseña.trim())) {
                    found = true;
                }
            }
            sc.close();
            System.out.println("found!");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void crearUsuario(String id, String password) throws FileNotFoundException {

        String destino = System.getProperty("user.home");
        destino += File.separator + "SIVU";
        destino += File.separator + "Usuario";
        destino += File.separator + "usuario.txt";

        boolean estado = comprobarExistenciaUsuario(id);

        if (estado = false) {
            String contraseñaSegura = Seguridad.getMD5(password);
            try {
                FileWriter fw = new FileWriter(destino, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                pw.println(id + "," + contraseñaSegura);
                pw.flush();
                pw.close();

            } catch (Exception e) {

            }
        }
        else{
            System.out.println("El usuario ya se encuentra creado");
        }

    }

    public static boolean comprobarExistenciaUsuario(String user) throws FileNotFoundException {

        String destino = System.getProperty("user.home");
        destino += File.separator + "SIVU";
        destino += File.separator + "Usuario";
        destino += File.separator + "usuario.txt";

        String usuario = "";
        String contraseña = "";
        boolean estado = false;
        x = new Scanner(new File(destino));
        x.useDelimiter("[,\n]");

        while (x.hasNext()) {
            usuario = x.next();
            contraseña = x.next();

            if (usuario.equals(user)) {
                estado = true;
            } else {
                estado = false;
            }
        }
        x.close();
        return estado;
    }

    public static void editarUsuario(String idEditar, String filepath, String password) {
        String tempFile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        String usuario = "";
        String contraseña = "";

        String contraseñaSegura = Seguridad.getMD5(password);

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while (x.hasNext()) {
                usuario = x.next();
                contraseña = x.next();

                if (usuario.equals(idEditar)) {
                    pw.println(idEditar + "," + contraseñaSegura);
                } else {
                    pw.println(usuario + "," + contraseña);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);
        } catch (Exception e) {

        }
    }

    public static void eliminarUsuario(String filepath, String removeTerm) {
        String tempFile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);

        String usuario = "";
        String contraseña = "";

        try {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");

            while (x.hasNext()) {

                usuario = x.next();
                contraseña = x.next();

                if (!usuario.equals(removeTerm)) {
                    pw.println(usuario + "," + contraseña);
                }
            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File(filepath);
            newFile.renameTo(dump);

        } catch (Exception e) {

        }
    }


    public static void crearArchivoAdmin() throws IOException {
        String destino = System.getProperty("user.home");
        destino += File.separator + "SIVU";
        destino += File.separator + "Admin";
        File customDir = new File(destino);

        if (customDir.exists()) {
            System.out.println(customDir + " ya existe");
        } else if (customDir.mkdirs()) {
            System.out.println(customDir + " fue creado");
        } else {
            System.out.println(customDir + " no fue creado");
        }
        File file = new File(customDir, "admin.txt");
        file.createNewFile();
    }

    public static void crearArchivoUsuario() throws IOException {
        String destino = System.getProperty("user.home");
        destino += File.separator + "SIVU";
        destino += File.separator + "Usuario";
        File customDir = new File(destino);

        if (customDir.exists()) {
            System.out.println(customDir + " ya existe");
        } else if (customDir.mkdirs()) {
            System.out.println(customDir + " fue creado");
        } else {
            System.out.println(customDir + " no fue creado");
        }
        File file = new File(customDir, "usuario.txt");
        file.createNewFile();
    }
}
