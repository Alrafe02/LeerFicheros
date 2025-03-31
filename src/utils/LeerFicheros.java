package utils;




import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class LeerFicheros {

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) throws IOException {
		//Ruta de la carpeta a leer
		File folder = new File("Ruta de la carpeta a leer");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		        leer(file.getName());
		    }
		}




	}



	public static void leer(String ficheroNombre) throws IOException {
		//Creamos fecha para el nombre del archivo
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		//Fichero donde escribirá los resultados
		BufferedWriter ficheroEscribir= new BufferedWriter(new FileWriter("Ruta de la carpeta a escribir "+timeStamp+".txt", true));
		Scanner s = null;
		//System.out.println("Num a buscar");
		//String num = scan.nextLine();
		int contador = 0;
		ArrayList<String> prueba = new ArrayList<>();
        final String regex = "2116[8-9][0-9][0-9][0-9][0-9][0-9][0-9]";

        



		//Ruta de la carpeta + nombre del fichero a leer
        File fichero = new File("Ruta de la carpeta a leer""+ficheroNombre+"");
		try {
			// Leemos el contenido del fichero
			System.out.println("... Leemos el contenido del fichero ...");
			s = new Scanner(fichero);

			// Leemos linea a linea el fichero
			while (s.hasNextLine()) {
				String linea = s.nextLine(); 	// Guardamos la linea en un String
				prueba.add(linea); //Guardamos la linea en un array por si queremos recoger la linea anterior
				if(linea.matches(".*\"Code\": \""+regex+".*")) { //Comprobamos la linea con un regex
				
					
				//Le damos formato para que quede bien en el archivo
				String nextLane = s.nextLine();
				String nextLane2 = s.nextLine();
				linea = linea.trim();
				linea = linea.replace("\"Code\": \"","");
				linea = linea.replace("\"","");
				nextLane = nextLane.trim();
				nextLane = nextLane.replace("\"Batch\": \"","");
				nextLane = nextLane.replace("\"","");
				nextLane2 = nextLane2.trim();
				nextLane2 = nextLane2.replace("\"Status\": \"","");
				nextLane2 = nextLane2.replace("\",","");
				//Lo escribimos en la consola y en el fichero
				System.out.println(linea + nextLane + nextLane2);
				ficheroEscribir.write(linea + nextLane + nextLane2 +"\n" );
				}
				contador++;
      // Imprimimos la linea
			}
			ficheroEscribir.close();
		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println("Mensaje 2: " + ex2.getMessage());
			}
		}
	}
}
