package p2.tempo;

/**
 * Esta classe representa um período de tempo

 */
public class Periodo {

	private Hora ini;
	private Hora fim;
	
	/**
	 * Criar um período indicando a hora inicial e final
	 * garantindo que a inicial é menor que a final
	 * @param ini hora inicial do periodo
	 * @param fim hora final do periodo
	 */
	public Periodo(Hora ini, Hora fim) {
		super();
		if( ini.compareTo(fim) > 0 ){
			this.ini = fim;
			this.fim = ini;			
		}
		else {
			this.ini = ini;
			this.fim = fim;
		}
	}	

	/**
	 * Criar um período indicando a hora inicial e a duração em segundos
	 * @param ini hora inicial do periodo
	 * @param duracao duracao do período em segundos
	 */
	public Periodo(Hora ini, int duracao ) {
		super();
		this.ini = ini;
		// tem de ser um clone senão na linha seguinte altera também a hora inicial
		fim = ini.clone();  
		fim.somaSegundos( duracao );
	}	


	/**
	 * devolve a hora final
	 * @return a hora final
	 */
	public Hora getFim() {
		return fim;
	}


	/**
	 * altera a hora final
	 * @param fim a nova hora final
	 */
	public void setFim(Hora fim) {
		if( fim.compareTo( ini) >= 0)
			this.fim = fim;
	}


	/**
	 * devole a hora inicial
	 * @return a hora inicial
	 */
	public Hora getIni() {
		return ini;
	}


	/**
	 * altera a hora inicial
	 * @param ini a nova hora inicial
	 */
	public void setIni(Hora ini) {
		if( ini.compareTo( fim ) <= 0)
			this.ini = ini;
	}

	/**
	 * retorna o Período em forma de String
	 * @return o Período em forma de String
	 */
	public String toString(){
		return "[ " + ini + " - " + fim + " ]";
	}

	/**
	 * indica se uma da hora está dentro deste período de tempo
	 * @param h a hora que se pretende verificr se está dentro do período de tempo
	 * @return se a hora está dentro ou não
	 */
	public boolean estaDentro( Hora h ){
		return h.compareTo( ini ) >= 0 && h.compareTo( fim ) <= 0;
	}
	
	
	/**
	 * Verifica se um, outro, periódo de tempo intersecta ou não este período
	 * @param outro perido que se pretende verificar a intersecção
	 * @return se outro período intersecta o nosso
	 */
	public boolean intersecta( Periodo outro ){
		if( outro.fim.compareTo( ini ) <= 0 )
			return false;
		if( outro.ini.compareTo( fim ) >= 0 )
			return false;		
		return true;		
	}
	
	
	/**
	 * indica se o nosso periodo está englobado dentro do periodo p 
	 * Um periodo está englobado noutro se estiver completamente dentro dele 
	 * @param p onde o nosso pode estar englobado 
	 * @return se o nosso período está englobado em p  
	 */
	public boolean estaContido( Periodo p ){
		return p.ini.compareTo( ini ) < 0 && p.fim.compareTo( fim ) > 0;
	}
	
	
	/**
	 * indica se o periodo p está englobado do nosso período
	 * Um periodo está englobado noutro se estiver completamente dentro dele 
	 * @param p período que pode estar englobado no nosso
	 * @return se p está englobado no nosso período
	 */
	public boolean contem( Periodo p ){
		return p.estaContido( this );
	}
	
	
	/**
	 * Junta o período p ao nosso. A junção só é válida se os períodos se intersectarem,
	 * caso contrário, não há junção
	 * @param p período a juntar ao nosso
	 */
	public void junta( Periodo p ){
		if( !intersecta(p))
			return;
		
		if( p.ini.compareTo(ini) < 0 )
			ini = p.ini.clone();
		if( p.fim.compareTo(fim) > 0 )
			fim = p.fim.clone();		
	}
	
	
	/**
	 * devolve a união do nosso período com o período p
	 * Se os períodos não se intersectarem não há união. Se se intersectarem
	 * a união indica o maior periodo de tempo possível juntando os dois
	 * @param p período a testar
	 * @return a união do nosso período com o período p
	 */
	public Periodo getUniao( Periodo p){
		if( !intersecta( p ))
			return null;
		Hora i = p.ini.compareTo(ini) < 0? p.ini.clone(): ini.clone();
		Hora f = p.fim.compareTo(fim) > 0? p.fim.clone(): fim.clone();
		return new Periodo( i, f);
	}
	
	
	/**
	 * Indica a intersecção do nosso período com o período p, ou seja,
	 * qual o período de tempo que possuem em comum
	 * @param p perído a testar
	 * @return o período de tempo que possuem em comum
	 */
	public Periodo getInterseccao( Periodo p){
		if( !intersecta(p) )
			return null;
		
		Hora i = p.ini.compareTo(ini) > 0? p.ini.clone(): ini.clone();
		Hora f = p.fim.compareTo(fim) < 0? p.fim.clone(): fim.clone();
		return new Periodo( i, f);
	}
}
