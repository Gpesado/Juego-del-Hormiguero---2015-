package hormiguero;

import java.util.ArrayList;

public class Tablero {

	private ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
	private Casillero casilleroActual;
	private boolean estadoJuego;
	private boolean victoria;

//*************************************************************************************//
//                                   Constructor                                       //
//*************************************************************************************//
	
	public Tablero(int n) {
		
		for (int i = 0; i < n; i++) 
		{
			this.casilleros.add(new Casillero(i + 1 ));	
		}
		this.setEstadoJuego(true);
		this.setVictoria(false);
		this.setCasilleroActual(primerCasillero());
	}
	
//*************************************************************************************//
//                             GETTERS AND SETTERS                                     //
//*************************************************************************************//
	
	public ArrayList<Casillero> getCasilleros() { return casilleros; }
	
	public void setCasilleros(ArrayList<Casillero> casilleros) {this.casilleros = casilleros;}
	
	public Casillero getCasilleroX(int x){return this.casilleros.get(x);}
	
	public Casillero getCasilleroActual() {return casilleroActual;}
	
	public void setCasilleroActual(Casillero casilleroActual) {this.casilleroActual = casilleroActual;}
	
	public int ultimaCasilla() {return this.casilleroActual.getCasilla();}
	
	public boolean isEstadoJuego() { return estadoJuego; }

	public void setEstadoJuego(boolean estadoJuego) { this.estadoJuego = estadoJuego; }
	
	public int decimeCasillero (){ return casilleroActual.getCasilla();}
	
	public boolean isVictoria() { return victoria;}

	public void setVictoria(boolean victoria) {this.victoria = victoria; }
	
	public boolean existeAtajo (){ return casilleroActual.existeSalto();
	}
	
//*************************************************************************************//
//                                FUNCIONES                                            //
//*************************************************************************************//
	
	public void agregarAtajos(int desde, int hasta)
	{
		if( hasta > getCasilleros().size() || desde == cantidadCasilleros()){System.err.println("Atajo invalido");}
		else {getCasilleros().get(desde - 1).setSalto(hasta - 1);}
	}
	
	public void SetSalto() 
	{
		this.casilleroActual.setCasilla(this.casilleroActual.getSalto());
	}
		
	public boolean hormigaFueraTablero(int i) 
	{
		if (i + getCasilleros().indexOf(casilleroActual) >= cantidadCasilleros() - 1){
			return true;
		}
		return false;
	}
	
	public boolean hormigaFinal(int i)
	{
		if (i + getCasilleros().indexOf(casilleroActual) == cantidadCasilleros() - 1){
			return true;
		}
		return false;
	}
	
	public boolean hormigaDentroTablero(int i)
	{
		if (i + getCasilleros().indexOf(casilleroActual) <= cantidadCasilleros() - 1){
			return true;
		}
		return false;
	}
	
	public void moverHormiga(int i)
	{
		if (hormigaFueraTablero(i)){this.setEstadoJuego(false);}
		
		if(hormigaFinal(i))
		{
			this.setVictoria(true);
			this.setEstadoJuego(false);
		}
		if (hormigaDentroTablero(i)) {casilleroActual = moverLaHormiga(i);}
	}
	
	public Casillero moverLaHormiga(int i){return getCasilleros().get(auxMoverLaHormiga(i));}
	
	private int auxMoverLaHormiga(int i){return getCasilleros().indexOf(casilleroActual) + i;}
	
	public void saltar(){casilleroActual = getCasilleros().get(casilleroActual.getSalto());}
	
	public Casillero auxSaltar(){return getCasilleroX(casilleroActual.getSalto());}
		
	public boolean iEnHormiga(int i){return casilleroActual == getCasilleros().get(i);}
	
	public Casillero primerCasillero(){return this.getCasilleros().get(0);}

	public int cantidadCasilleros(){return this.getCasilleros().size();}
		
	public void ponerDefault()
	{
		setCasilleroActual(primerCasillero());
		this.estadoJuego = true;
		this.victoria = false;
	}
}