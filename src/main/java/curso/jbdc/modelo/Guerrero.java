package curso.jbdc.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import curso.jbdc.repositorio.IGuerrero;
import curso.jbdc.util.ConexionBaseDatos;

public  class Guerrero implements IGuerrero{
	
	private static final Logger logger = LogManager.getLogger(Guerrero.class);

	
	private Long idnew_table;
	private String nombre;
	private String tipo;
	private int puntosAtaque = 8;
	protected int puntosDefensa = 5;
	protected int vida = 100;
	
	
	
	public Guerrero() {
		super();
	}

	public Guerrero(String nombre, String tipo, int puntosAtaque, int puntosDefensa, int vida) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
		this.puntosAtaque = puntosAtaque;
		this.puntosDefensa = puntosDefensa;
		this.vida = vida;
	}




	public Long getIdnew_table() {
		return idnew_table;
	}

	public void setIdnew_table(Long idnew_table) {
		this.idnew_table = idnew_table;
	}

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public int getPuntosAtaque() {
		return puntosAtaque;
	}



	public void setPuntosAtaque(int puntosAtaque) {
		this.puntosAtaque = puntosAtaque;
	}



	public int getPuntosDefensa() {
		return puntosDefensa;
	}



	public void setPuntosDefensa(int puntosDefensa) {
		this.puntosDefensa = puntosDefensa;
	}



	public int getVida() {
		return vida;
	}



	public void setVida(int vida) {
		this.vida = vida;
	}



	@Override
	public String toString() {
		return "GuerreroImpl [id=" + idnew_table + ", nombre=" + nombre + ", tipo=" + tipo + ", puntosAtaque=" + puntosAtaque
				+ ", puntosDefensa=" + puntosDefensa + ", vida=" + vida + "]";


	}
	
	@Override
	public void defender1(int ataque, int def, int vida) {
		
		int resultado;
		def *=(int)(Math.random()*5 + 1);
		resultado = ataque - def ;
		//ataque = 8;
		
		logger.debug("vida " + this.getVida());
		logger.debug("defensa : " + this.getPuntosDefensa());
		
		if(resultado > 0) {
			logger.debug("AL guerrero  le han quitado " + resultado + " puntos de vida");
			vida -= resultado;
		
			logger.debug("Le queda de vida : " + vida);
		}else {
			logger.debug("No le ha quitado vida " + vida);
		}
		
		
	}

	public int atacar1(int ataque) {
		int ataqueFinal=0;
		ataqueFinal = puntosAtaque *(int)(Math.random()*10 + 1);
		
		return ataqueFinal;
		
	}


}
