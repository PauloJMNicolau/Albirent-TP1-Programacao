/**
 * @author Paulo Nicolau
 * @author Waldir Vigário
 */
package p2.tempo;

public class PeriodoTempo {

	private Tempo ini;
	private Tempo fim;
	
	/**
	 * Criar um periodo indicando a hora inicial e final
	 * garantindo que a inicial é menor que a final
	 * @param ini hora inicial do periodo
	 * @param fim hora final do periodo
	 */
	public PeriodoTempo(Tempo ini, Tempo fim) {
		super();
		if( ini.compareTo(fim) > 0 ){
			this.ini = fim;
			this.fim = ini;			
		}else {
			this.ini = ini;
			this.fim = fim;
		}
	}	

	/**
	 * Criar um período indicando a hora inicial e a duração em segundos
	 * @param ini hora inicial do periodo
	 * @param duracao duracao do período em segundos
	 */
	public PeriodoTempo(Tempo ini, int duracao ) {
		super();
		this.ini = ini;
		// tem de ser um clone senão na linha seguinte altera também a hora inicial
		fim = ini.clone();  
		fim.somaSegundos( duracao );
	}	


	/**
	 * devolve o Tempo final
	 * @return o Tempofinal
	 */
	public Tempo getFim() {
		return fim;
	}


	/**
	 * altera o Tempo final
	 * @param fim o nova Tempo final
	 */
	public void setFim(Tempo fim) {
            Tempo aux= this.ini;
            if( fim.compareTo(ini) >= 0)
                this.fim = fim;
            else{
                this.ini =fim;
                this.fim= aux;
            }
	}


	/**
	 * devole o Tempo inicial
	 * @return o tempo inicial
	 */
	public Tempo getIni() {
		return ini;
	}


	/**
	 * altera o Tempo inicial
	 * @param ini o Tempo inicial
	 */
	public void setIni(Tempo ini) {
            Tempo aux = this.fim;
            if( ini.compareTo(fim ) <= 0)
		this.ini = ini;
            else{
                this.ini=aux;
                this.fim=ini;
            }
	}

	/**
	 * retorna o Período em forma de String
	 * @return o Período em forma de String
	 */
	public String toString(){
		return "[ " + ini + " - " + fim + " ]";
	}

	/**
	 * indica se um Tempo está dentro deste período de tempo
	 * @param t o tempoque se pretende verificar se está dentro do período de tempo
	 * @return se o tempo está dentro ou não
	 */
	public boolean estaDentro(Tempo t){
            return t.compareTo(ini) >= 0 && t.compareTo( fim ) <= 0;
	}
	
	
	/**
	 * Verifica se um, outro, periodo de tempo intersecta ou não este período
	 * @param outro perido que se pretende verificar a intersecção
	 * @return se outro período intersecta o nosso
	 */
	public boolean intersecta(PeriodoTempo outro){
            if(outro.fim.compareTo(ini) <= 0 ) {
                return false;
            }
            return outro.ini.compareTo(fim) < 0;		
	}
	
	
	/**
	 * indica se o nosso periodo está englobado dentro do periodo p 
	 * Um periodo está englobado noutro se estiver completamente dentro dele 
	 * @param p onde o nosso pode estar englobado 
	 * @return se o nosso período está englobado em p  
	 */
	public boolean estaContido(PeriodoTempo p){
		return p.ini.compareTo(ini) < 0 && p.fim.compareTo(fim) > 0;
	}
	
	
	/**
	 * indica se o periodo p está englobado do nosso período
	 * Um periodo está englobado noutro se estiver completamente dentro dele 
	 * @param p período que pode estar englobado no nosso
	 * @return se p está englobado no nosso período
	 */
	public boolean contem( PeriodoTempo p ){
		return p.estaContido( this );
	}
	
	
	/**
	 * Junta o período p ao nosso. A junção só é válida se os períodos se intersectarem,
	 * caso contrário, não há junção
	 * @param p período a juntar ao nosso
	 */
	public void junta( PeriodoTempo p ){
            if( !intersecta(p)) {
                return;
            }
            if( p.ini.compareTo(ini) < 0 ) {
                ini = p.ini.clone();
            }
            if( p.fim.compareTo(fim) > 0 ) {
                fim = p.fim.clone();
            }		
	}
	
	
	/**
	 * devolve a união do nosso período com o período p
	 * Se os períodos não se intersectarem não há união. Se se intersectarem
	 * a união indica o maior periodo de tempo possível juntando os dois
	 * @param p período a testar
	 * @return a união do nosso período com o período p
	 */
	public PeriodoTempo getUniao( PeriodoTempo p){
            if( !intersecta( p )) {
                return null;
            }
            Tempo i = p.ini.compareTo(ini) < 0? p.ini.clone(): ini.clone();
            Tempo f = p.fim.compareTo(fim) > 0? p.fim.clone(): fim.clone();
            return new PeriodoTempo(i,f);
	}
	
	
	/**
	 * Indica a intersecão do nosso período com o período p, ou seja,
	 * qual o período de tempo que possuem em comum
	 * @param p período a testar
	 * @return o período de tempo que possuem em comum
	 */
	public PeriodoTempo getInterseccao( PeriodoTempo p){
            if( !intersecta(p) ) {
                return null;
            }
            Tempo i = p.ini.compareTo(ini) > 0? p.ini.clone(): ini.clone();
            Tempo f = p.fim.compareTo(fim) < 0? p.fim.clone(): fim.clone();
            return new PeriodoTempo( i, f);
	}
        
        public long getDuracaoSegs(){
            return fim.toSegs()-ini.toSegs();
        }
        
        public boolean comecaAntes(PeriodoTempo p){
            return ini.compareTo(p.ini) > 0;
        }
        
        public boolean comecaDepois(PeriodoTempo p){
            return fim.compareTo(p.ini) < 0;
        }
        
        public boolean acabaAntes(PeriodoTempo p){
            return ini.compareTo(p.fim) > 0;
        }
        
        public boolean acabaDepois(PeriodoTempo p){
            return fim.compareTo(p.fim) < 0;
        }
        
        public long calcNumDias(){
            return this.getDuracaoSegs() / 86400;
        }
        
        public long calcNumHoras(){
            long horas = getDuracaoSegs() % 86400;
            return horas / 3600;
        }
        
}
