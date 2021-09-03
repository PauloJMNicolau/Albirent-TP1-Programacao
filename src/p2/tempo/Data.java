package p2.tempo;
/**
 * 
 */
import java.util.Calendar;

/**
 * @author fsergio
 *
 */
public class Data implements Cloneable {
	
    private int dia, mes, ano;   
    
    
	public Data( ){
		Calendar cal = Calendar.getInstance();
		dia = cal.get( Calendar.DAY_OF_MONTH );
		mes = cal.get( Calendar.MONTH ) + 1;
		ano = cal.get( Calendar.YEAR );
	}
	
	
	public Data( int d, int m, int a ){
		validaAno( a );
		validaMes( m );
		validaDia( d );
	}
	
	
	public Data( int diaAno, int a ){
	    validaAno( a );
	    
	    if( diaAno < 1 )
	        diaAno = 1;

	    // verificar se dias do ano est�o entre 1 e 365 (ou 366 nos bissesxtos)
	    int maxDias = numDiasAno();
	    if( diaAno > maxDias )
	        diaAno = maxDias;
	    
	    mes = 1; // Come�ar com Janeiro
	    // enquanto o n� de dias n�o couber no m�s em quest�o
	    while( diaAno > numDiasMes( ) )
	    {
	        // subtrai-se o n� de dias do m�s ao total dos dias
	        diaAno -= numDiasMes( ); 
	        // e passa-se ao m�s seguinte
	        mes++ ;                          
	    }
	    // os dias que sobram � o Dia do m�s
	    dia = diaAno;		
	}

	
	public Data( String dStr )
	{
	    // ler a string no formato dia/m�s/ano
		String str[] = dStr.split("/");	    
    
	    // verificar se valores s�o v�lidos
        validaAno( Integer.valueOf( str[2] ).intValue() );
	    validaMes( Integer.valueOf( str[1] ).intValue() );
	    validaDia( Integer.valueOf( str[0] ).intValue() );	    
	}
	
	private void validaDia( int d ){				
		dia = d < 1? 1: d;
		dia = dia > numDiasMes( )? numDiasMes( ): dia;
	}
	
	
	private void validaMes( int m ){
		mes = m < 1? 1: m;
		mes = mes >12? 12: mes;
	}
	
	
	private void validaAno( int a ){
		ano = a == 0 ? 1: a;		
	}

	
	
	// retorna o n� de dias do m�s actual
	public int numDiasMes( ){
		return numDiasMes( mes );
	}
	

	// este m�todo aceita este par�metro porque assim vai ser �til em muitos lados
	private int numDiasMes( int m ){
		int nDiasMes[] = {0,31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if( eBissexto( ))
			nDiasMes[2] = 29;
		return nDiasMes[ m ];
	}
	
	
	
	public String toString( ){
		return dia + "/" + mes + "/" + ano;		
	}

	
	public void setDia( int d ) {
		validaDia( d );
	}

		
	public void setMes( int m ) {
		validaMes( m );
		validaDia( dia );
	}

	
	
	public void setAno( int a ) {
		validaAno( a );
		validaDia( dia );
	}
	
	
	
	public int compareTo( Data outra ){
		if( ano != outra.ano )
			return ano - outra.ano;
		if( mes != outra.mes )
			return mes - outra.mes;
		return dia - outra.dia;		
	}
	
	
	public Data clone( ) {
		return new Data( dia, mes , ano );
	}
	
	
	
	public boolean eBissexto( ) {
	    // um ano � bissexto se for divis�vel por 4
	    //   excepto nos casos em que � divisivel por 100
	    //     mas volta a ser nos casos em que � div�sivel por 400
	    return ((ano%4 == 0) && (ano % 100 != 0)) || (ano % 400 == 0);
	}
	
	
	
    public int numDiasAno( ) {
    	return (eBissexto() ? 366: 365);
    }

    
	
	public int getDiaAno() {
	    int nDiasAno=dia; // incializar contagem dos dias do ano com o dia do m�s
	    
	    // enquanto n�o chegar ao m�s certo ir somando os dias deste m�s
	    for( int m = 1; m < mes; m++)
	      nDiasAno += numDiasMes( m );
	    
	    return nDiasAno;
	}
	
	
	public void somaDias( int numDias )	{
		int totalDias = dia + numDias;
		dia = 1; // para evitar altera��es do dia pela mudan�a do m�s que se vai fazer
		
		while( totalDias > numDiasMes() ){
			totalDias -= numDiasMes();
			somaMeses( 1 );			
		}
		setDia( totalDias );
		
		
		/* vers�o antiga
	    // calcula quantos dias faltam para o fim do m�s
	    int distFimMes = numDiasMes( ) - dia;
	    
	    // enquanto os dias n�o couberem no pr�prio m�s
	    while( numDias > distFimMes ) {
	        dia = 1;                          // estamos no �nicio de um novo m�s
	        somaMeses( 1 );                   // passa para o m�s seguinte
	        numDias -= (distFimMes+1);        // decrementa o n�mero de dias que
	                                          // andou (o +1 � para passar de m�s)
	        distFimMes = numDiasMes( ) - 1;   // o pr�ximo m�s come�a do 1
	    }
	    
	    // no fim basta colocar o dia ao n� de dias que sobram
	    setDia( dia+numDias );
	    */
	}



	public void somaMeses( int numMeses ) {
	    int mesFinal = mes + numMeses;
	    
	    // ver se se passou de ano
	    if( mesFinal > 12 ) {
	    	// para estas contas � necess�rio ter em conta que meses come�am em 1 e n�o em 0
	    	// ou seja, para fazer divis�es temos blocos de 13 meses e n�o 12. Uma solu��o
	    	// � a apresentada que � subtrair 1 ao m�s para o colocar de 0 a 11 e no final
	    	// voltar a somar 1 para o recolocar de 1 a 12
	        int nAnos = (mesFinal-1)/12;     // ver quantos anos se passaram
	        somaAnos( nAnos );               // somar esses anos a ano
	        mesFinal = (mesFinal-1)%12 + 1;  // ver em que m�s ficamos 
	    }
	    setMes( mesFinal );             // alterar o m�s, lembrar que � necess�rio
	                                    // verificar o dia	    
	    
	    // uma solu��o mais perceptivel, mas menos eficaz, seria
	    // int mesFinal += numMeses;
	    // while( mesFinal > 12 ){
	    // 	    somaAnos( 1 );
	    //  	mesFinal -= 12;
	    // }
	    // setMes( mesFinal );
	    // esta solu��o faz o mesmo que a anterior mas em vez de fazer as contas de uma vez vai
	    // fazendo-as ano a ano
	}
	
	
    public void somaAnos( int numAnos ) {
    	setAno( ano + numAnos );
    }
	

    
    public int getDiferencaDias( Data outra ) {   
        // o algoritmo exige que a nossa Data seja maior que a outra
        // se a nossa data for menor que a outra ent�o vamos calcular a diferen�a
        // entre a outra e a nossa e dar valor negativo
        if( this.compareTo( outra ) < 0 )
            return -outra.getDiferencaDias( this );   
        
        // se estiverem no mesmo ano basta ver a diferen�a de dias
        // exemplo: 12/2/2003 e 18/2/2003 s�o do mesmo ano
        //          12/2/2003 � o dia 43 do ano
        //          18/2/2003 � o dia 49 do ano
        //          logo a diferen�a entre elas � de 49-43 = 6 dias
        if( ano == outra.ano )
            return getDiaAno() - outra.getDiaAno( );

        // se n�o est�o no mesmo ano vamos ter de fazer contas com a data    
        // e vamos alterar a data por isso precisamos de uma auxiliar
        Data dAux = (Data)outra.clone();
        
        // a diferen�a de dias � inicializada com o n�mero de dias que faltam para
        // acabar o ano da data menor: dAux
        // exemplo: para saber a diferen�a entre 24/12/2001 e 5/1/2002
        //          � preciso saber quantos dias faltam para acabar 2001
        //          e, em 2001, saber quantos dias faltam para chegar a 5/1   
        //          assim a diferen�a de dias � a soma das duas quantidades
        //          vamos pois inicializ�-la com o n�mero de dias que faltam para
        //          acabar o ano
        int nDiasDif = dAux.numDiasAno( ) - dAux.getDiaAno( );
        
        // j� contamos os dias deste ano por isso passar para o ano seguinte
        dAux.somaAnos( 1 );              
        
        // enquanto as datas n�o estiverem no mesmo ano somam-se os dias dos anos completos                
        while( dAux.ano < ano )
        {
            nDiasDif += dAux.numDiasAno();
            dAux.somaAnos( 1 );
        }
        
        // j� est�o no mesmo ano, agora � somar os dias da nossa data  
        nDiasDif += getDiaAno( );
        
        return nDiasDif;
    }



    public long getDiferencaMeses( Data outra ) {   
        // o algoritmo exige que a nossa Data seja maior que a outra
        // se a nossa data for menor que a outra ent�o vamos calcular a diferen�a
        // entre a outra e a nossa e dar valor negativo
        if( this.compareTo( outra ) < 0 )
            return -outra.getDiferencaMeses( this );   
        
        // se estiverem no mesmo ano basta ver a diferen�a de meses
        // exemplo: 12/2/2003 e 8/3/2003 s�o do mesmo ano
        //          logo a diferen�a entre elas � de 3-2 = 1 m�s
        if( ano == outra.ano )
            return mes - outra.mes;

        // a diferen�a de meses � inicializada com o n�mero de meses que faltam para
        // acabar o ano da data menor: dAux
        // exemplo: para saber a diferen�a entre 24/11/2001 e 5/3/2002
        //          � preciso saber quantos meses faltam para acabar 2001
        //          e, em 2002, saber quantos meses faltam para chegar ao m�s 3   
        //          assim a diferen�a de meses � a soma das duas quantidades
        //          vamos pois inicializ�-la com o n�mero de meses que faltam para
        //          acabar o ano
        long nMesesDif = 12 - outra.mes;

        // depois somam-se os meses dos anos completos entre as duas datas 
        // (dai o -1 porque o primeiro ano j� foi calculado)
        nMesesDif += 12 * (ano - outra.ano - 1);
           
        nMesesDif += mes;
        
        return nMesesDif;
    }

    
    // sem numera��o esta parte ficaria 
    // public static final int DOMINGO = 0; 
    // public static final int SEGUNDA = 1; 
    // etc
    // parte com a enumera��o dos dias da semana
    public enum DiaSemana {
    	DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO;
    	
    	DiaSemana somaDias( int n ){
    		int fim = (this.ordinal() + n) % 7;
    		return DiaSemana.values()[ fim ];
    	}
    	
    }

    public DiaSemana getDiaSemana( ) {    
        // a Data de 1/1/1970 � a data de refer�ncia em muitos sistemas
        // e era uma quinta-feira
        // para calcular o dia da semana basta saber quantos dias de diferen�a h�
        // entre as duas datas
        Data ref = new Data( 1, 1, 1970);
        
        int nDias = getDiferencaDias( ref );                 
        return DiaSemana.QUINTA.somaDias( nDias );
        
        // sem a enumera��o seria, assumindo que DOMINGO = 0, SEGUNDA = 1; etc
        // para calcular o dia da semana basta saber quantos dias de diferen�a h�
        // entre as duas datas e depois tomar o resto da divis�o por 7
        // return (QUINTA + nDias )% 7; // a %7 � para dar um n� de 0 a 6 
    }
        
}
