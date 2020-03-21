import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conector instancia = Conector.getInstancia();
		try {
			
			ArrayList<String> listNombresPacientes = instancia.getPaciente();
			ArrayList<String> listaNombresYConsultorioDoctores = instancia.getDoctores();
			
			
			System.out.println( "Nombre Paciente: ");
			System.out.println("----------------");
			
			for(String nombre: listNombresPacientes) {
				System.out.println(nombre);
			}
			
			System.out.println("----------------");
			System.out.println();
      
			System.out.println("Nombre   /  Nro Consultorio");
			System.out.println("----------------");
      
			int count=0;
			for(String nombreYConsultorioDoctor: listaNombresYConsultorioDoctores) {
			  System.out.print(nombreYConsultorioDoctor + " ");
        
				count++;
        
				if(count==2) {
					System.out.println();
					count=0;
				}
			
			}
			System.out.println("----------------");
			
		}catch (SQLException e ) {
			e.printStackTrace();
		}
	
	}
	

}
