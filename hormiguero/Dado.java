package hormiguero;
import java.util.Random;

public class Dado 
{
	private int numero;
	
//*************************************************************************************//
//	   						   GETTERS AND SETTERS                                     //
//*************************************************************************************//
	
	public int getNumero() { return numero; }
	
//*************************************************************************************//
// 								  FUNCIONES                                            //
//*************************************************************************************//
	
	public int random ()
	{
		Random  rnd = new Random();
		return  (int)(rnd.nextDouble() * 5 + 1);
	}
	
	public void lanzardado (){ this.numero = random();}
}