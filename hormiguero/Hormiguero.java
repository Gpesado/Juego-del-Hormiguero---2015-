/*
 * Interfaz:
Hormiguero (n) //tamaño del hormiguero, donde n > 1
Int cantJugadas() //devuelve el nro de jugadas hasta el momento
Void agregarAtajo (int desde, int hasta) //Agrega un atajo
void jugar() // tira los dos dados
String ver() // muestra el estado del tablero y de la hormiga
Int ultimaCasilla() // casilla actual de la hormiga
Ejemplo del código principal:
Hormiguero m = new Hormiguero(8); // Instancia: n == 8
m. agregarAtajo(6,1);//atajo desde 6 hasta 1
m.agregarCasilla(3, 7);
while m. ultimaCasilla() != 8{ //como máximo hacerlo 1000 veces
m.jugar(); // La hormiga “tira” el dado
System.out.println(m.ver()) //Se muestra un resumen del
hormiguero
}
System.out.println(m. cantJugadas ());
Test1
Modelar la instancia del enunciado.
b) Escribir el invariante de representación del Hormiguero.
Describir que Hormigueros son instancias validas del Hormiguero que se quiere
representar
 */
package hormiguero;

public class Hormiguero 
{
	private int cantidadJugadas;
	private int totalJugadas = 1000;
	private Dado dado = new Dado();
	private Tablero tablero;
	
	//*************************************************************************************//
	//                             GETTERS AND SETTERS                                     //
	//*************************************************************************************//
	
	public int getCantidadJugadas() {return cantidadJugadas;}

	public void setCantidadJugadas(int cantidadJugadas) { this.cantidadJugadas = cantidadJugadas;}
	
	public int cantJugadas() { return cantidadJugadas; }
	
	public boolean isVictoria() { return tablero.isVictoria();}
	
	public boolean juegoOn(){return tablero.isEstadoJuego() == true;}
	
	//*************************************************************************************//
	//                               CONSTRUCTORES                                         //
	//*************************************************************************************//
	
	public Hormiguero(int cantidadCasilleros)
	{
		this.tablero = new Tablero(cantidadCasilleros);
	
	}
	
	public void agregarAtajo(int desde, int hasta)
	{
		tablero.agregarAtajos(desde,hasta);
	}
	
	public void ponerDefault()
	{
		this.cantidadJugadas=0;
		tablero.ponerDefault();
	}

	//*************************************************************************************//
	//                                FUNCIONES                                            //
	//*************************************************************************************//
		
	public void moverHormiga(int i)
	{
		msjMoverHormiga();
		tablero.moverHormiga(i);
	}
		
	public void lanzarDado()
	{
		dado.lanzardado();
		msjLanzarDado();
	}


	public void jugar()
	{
		if (quedanJugadas())
		{
		aumentarJugada();
		lanzarDado();
		moverHormiga(dado.getNumero());
		estadoJuego();
		
		hayAtajo();
		}
		else {PerderPorVidas();}
			
	}
	
	public boolean quedanJugadas() 
	{
		return cantidadJugadas != totalJugadas;		
	}
	
	public void aumentarJugada() 
	{ 
		this.cantidadJugadas++;
		msjCantidadJugadas();
	}
	
	public void estadoJuego() 
	{
		if (juegoOn())
		{
			msjDecimeDondeCayo();
		}
		else 
			if(isVictoria()){msjVictoria();}
			else{msjDerrota();}
	}

public void saltar()
{
	tablero.saltar();
	msjHormigaCayoEnAtajo();
}
 
 public void hayAtajo()
 {
	 if (tablero.existeAtajo())
	 {
		 saltar();
	 }	 
 }
	//*************************************************************************************//
	//                            ELEMENTOS GRAFICOS                                       //
	//*************************************************************************************//
 
public void msjDecimeDondeCayo()
{
	System.out.println(" hasta " + tablero.decimeCasillero());
}

public void msjMoverHormiga()
{
	System.out.print("La hormiga se mueve desde el casillero " + tablero.decimeCasillero ());
}
 
public void msjLanzarDado()
{
	System.out.println("Usted saco un " + dado.getNumero());
}
public void msjCantidadJugadas()
{
	System.out.println("Usted posee 1000 tiros y actualmente uso " + cantidadJugadas);
}

public void msjHormigaCayoEnAtajo()
{
	System.out.println("La hormiga entro en un micro-tunel... la cual la deja en la casilla " + tablero.decimeCasillero());
}
public void msjVictoria()
{
	System.out.println(" hasta el hormiguero");
}

public void msjDerrota()
{
	System.out.println(" pero se topo con el final y se cayo al vacio :_");
}

public void mostrarTablero(){System.out.println(ver());}
	
public String ver()
{
	String tablero = " ";
	System.out.println();
	
	if (this.juegoOn()) {tablero = armarTableroNormal(tablero);}
	
	else {tablero = armarTableroEspecial(tablero);}
	
	return tablero;
}
	
public String armarTableroNormal(String tablero)
{
	for (int i = 0; i < this.tablero.cantidadCasilleros(); i++) 
	{
		if(auxArmarTableroNormal(i))
			tablero+= " * ";
		else 
			tablero+= " _ ";
	}
	return tablero;
}

public boolean auxArmarTableroNormal(int i)
{
	return tablero.iEnHormiga(i);
}

public String armarTableroEspecial (String tablero ) 
{
	for (int i = 0; i < this.tablero.cantidadCasilleros(); i++)
		tablero+= " _ ";

	if ( this.isVictoria() ) { tablero+= " ( * )";  }

	else{ tablero+="...........*";}
	return tablero;
}
	
public void msjResultadoJuego()
{
	System.out.println();
	if(this.isVictoria())System.out.println("Ganaste Lince !!!");
	
	else System.out.println("Perdiste como un campeon");
	System.out.println();
	System.out.println("Presione ENTER para volver a jugar");	
}

public void PerderPorVidas(){
	System.err.println("Usted no posee mas vidas, vuelva a intentarlo");
	tablero.setEstadoJuego(false);
	msjResultadoJuego();
	ponerDefault();
}

public void msjTirarDado()
{
	System.out.println();
	System.out.println("Presione ENTER para tirar un dado ");
	System.out.println();
}

public String posicionHormiga()
{
	String msj = "La hormiga esta en el casillero nº " + tablero.decimeCasillero();
	return msj;
}

public void msjBienvenida()
{
	System.out.println("----------------------------------------------------------------------------------------------------------------------------");
	System.out.println("Bienvenidos al JUEGO DEL HORMIGUERO !!");
	System.out.println("el juego consiste lograr que la hormiga llege hasta el hormiguero sin que se pase");
	System.out.println("para ello usted dispondra de " + totalJugadas + " jugadas totales");
	System.out.println("RECUERDE !!, hay pequeños micro-tuneles distribuidos por el tablero que podran hacer avanzar a la hormiga");
	System.out.println("PRECAUCION !!, algunos micro-tuneles pueden hacer retroceder a tu valiente hormiguita, asi que... cuidado con ellos!");
	System.out.println("----------------------------------------------------------------------------------------------------------------------------");
}
}