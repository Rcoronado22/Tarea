import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Conector {
	// Atributos de la clase
		private static Connection con;
		private static Conector INSTANCE = null;
		
		private Conector() {
			
		}
		// Crear Instancia
		private synchronized static void  crearInstancia() {
			if(INSTANCE == null) {
				INSTANCE =new Conector();
				crearConexion();
			}
		}
		
		// Obtener Instancia
		
		public static Conector getInstancia() {
			if(INSTANCE == null) {
				crearInstancia();
			}
			return INSTANCE;
		}
		// Crear Conexion
		private static void crearConexion() {
			String host = "127.0.0.1";
			String user = "NuevoUsuarioRenato";
			String password = "Vilela70"; // Poner su contraseña
			String dataBase = "cuarentena";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String urlConexion = "jdbc:mysql://"+host+"/"+dataBase+"?user="+user+"&password="+password;
				con = DriverManager.getConnection(urlConexion);
				System.out.println("Lo lograste :') ");
			}catch (Exception e) {
				System.out.println("Error al conectar a la base de datos");
				System.out.println(e);
			}
			
		}
		
		public ArrayList<String> getPaciente() throws SQLException{
			ArrayList<String> listaPacientes=new ArrayList<String>();
			PreparedStatement ps =con.prepareStatement("select persona.nombres from persona inner join paciente on paciente.personaID=persona.CI;");
			ResultSet rs= ps.executeQuery();
			while(rs.next()){
				listaPacientes.add(rs.getString("nombres"));
			}
			rs.close();
			return listaPacientes;
		}

		public ArrayList<String> getDoctores() throws SQLException {
			ArrayList<String> listaDoctores = new ArrayList<String>();
			PreparedStatement ps = con.prepareStatement("Select nombres, consultorio.nro from persona inner join doctor on persona.ID= Doctor.PersonaID inner join consultorio on doctor.consultorioID=consultorio.ID");
			ResultSet rs = ps.executeQuery();
				
			while(rs.next()) {
					listaDoctores.add(rs.getString("nombres"));
					listaDoctores.add(rs.getString("consultorio.nro"));
				}
	      
	      rs.close();
		  	return listaDoctores;
		}
}
