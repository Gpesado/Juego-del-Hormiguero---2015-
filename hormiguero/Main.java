package hormiguero;

public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Hormiguero h = new Hormiguero (10);
		h.agregarAtajo(5, 6);
		h.agregarAtajo(7, 2);
		h.msjBienvenida();
		
		while(h.juegoOn())
		
		{
			h.msjTirarDado();
			Entrada.readLine();
			h.jugar();
			h.mostrarTablero();
			if (!h.juegoOn()) 
			{
				h.msjResultadoJuego();
				Entrada.readLine();
				h.ponerDefault();
			}	
		}	
	}
}