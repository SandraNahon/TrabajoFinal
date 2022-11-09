package curso.jbdc.repositorio;

import java.util.List;

import curso.jbdc.modelo.Guerrero;

public interface IGuerrero<T> {
	
	
	void defender1(int ataque, int def, int vida);

	 static int atacar(int ataque) {
		 
		int ataqueFinal=0;
		ataqueFinal = ataque *(int)(Math.random()*10 + 1);
		return ataqueFinal;
		
		
		
	}
	// public void defender(int ataque, int defensa, int vida);
	

}
