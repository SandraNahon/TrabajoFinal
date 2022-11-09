package curso.jbdc;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.Message;
import org.hibernate.internal.build.AllowSysOut;

import curso.jbdc.modelo.Guerrero;
import curso.jbdc.repositorio.IGuerrero;
import curso.jbdc.util.ConexionBaseDatos;
import jakarta.persistence.EntityManager;


public class MainJdbc {
	
	//Encargado de mostrar las trazas
		private static final Logger logger = LogManager.getLogger(MainJdbc.class);
		

	public static void main(String[] args) {
		MainJdbc main = new MainJdbc();
	
		try {
			
			//GuerreroImpl repositorio = new GuerreroImpl();
			logger.debug("=========== listar guerreros =============== ");
			logger.debug(listar());
			
			main.iniciarGuerra();
			
			
			
		}  catch (Exception e) {
        	logger.error("Empezando error " +e.getMessage());
        	logger.fatal("Empezando fatal");
            e.printStackTrace();
        }

	}
	
	
	
	
	
	@SuppressWarnings("unused")
	private Connection getConnection() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }
	

	public static List<Guerrero> listar() {
			logger.debug("Listando guerreros");
			
			List<Guerrero> guerreros = new ArrayList<>();
			
			try(Statement stmt =  ConexionBaseDatos.getInstance().createStatement();
		             ResultSet rs = stmt.executeQuery("SELECT * FROM tb_guerra")) {
				
				while(rs.next()) {
					Guerrero g = crearGuerrero(rs);
					guerreros.add(g);
				}
			} catch (SQLException e) {
	            e.printStackTrace();
	        }
//			if (guerreros.size()!=0) {
//				logger.info("Encontrados "+guerreros.size()+" guerreros");
//			}else {
//				logger.warn("No se han encontrado los guerreros");
//			}
			
			return guerreros;
			
		
	}


	private static Guerrero crearGuerrero(ResultSet rs) throws SQLException {
		Guerrero g = new Guerrero();
		 
        g.setIdnew_table(rs.getLong("idnew_table"));
        g.setNombre(rs.getString("nombre"));
        g.setTipo(rs.getString("tipo"));
        g.setPuntosAtaque(rs.getInt("puntosAtaque"));
        g.setPuntosDefensa(rs.getInt("puntosDefensa"));
        g.setVida(rs.getInt("vida"));
        
        return g;
	}
	
	
	private void iniciarGuerra() {
		logger.debug("Se inicia la pelea");
		Scanner scan = new Scanner(System.in);
		
		int turnos, turno = 0, ataque;
		int contador = 1;
		
        Guerrero guerrero1 =  new Guerrero("A"," ",8,5,100);
        Guerrero guerrero2 = new Guerrero("A"," ",8,5,100);
        
        do {
        	logger.debug("Empieza la pelea , turno numero: " + contador);
        	turno++;
        	//turno 1 Guerrero A
        	
        	if(turno == 1) {
        		 logger.debug("Esta atacando el  guerrero " + guerrero1.getNombre());
        		 ataque = guerrero1.atacar1(guerrero1.getPuntosAtaque());
        		 guerrero2.defender1(ataque, guerrero1.getPuntosDefensa(), guerrero2.getVida());
        		 turno++;
        		 
        		 
        		//Guerrero B
        	}else if(turno == 3) {
        		 logger.debug("Esta atacando el  guerrero " + guerrero2.getNombre());
        		 ataque = guerrero2.atacar1(guerrero2.getPuntosAtaque());
        		 guerrero1.defender1(ataque, guerrero1.getPuntosDefensa(), guerrero1.getVida());
        		 turno = 0;
        		
        	}
        	contador++;
        	
        } while(guerrero1.getVida() > 0 && guerrero2.getVida() > 0);
        if(guerrero1.getVida() > 0 ) {
        	System.out.println("\nEl guerrero"+guerrero1.getNombre()+" ha ganado el combate con un total de "+guerrero1.getVida()+" puntos de vida");
        }else {
        	System.out.println("\nEl guerrero "+guerrero2.getNombre()+" ha ganado el combate con un total de "+guerrero2.getVida()+" puntos de vida");
        }
        
        System.out.println("\n");
        logger.debug("Ha terminado");
		
	}


}
