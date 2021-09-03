package p2.tempo;
import java.util.Calendar;

/**
 * @author fsergio
 *
 */
public class Hora implements Cloneable {
	private int horas;
	private int minutos;
	private int segundos;
	
	// constantes para vários cálculos
	// eles ainda não sabem criar costantes por isso indicar que devem fazer assim e que mais tarde
	// vamos aprender o porque de ser assim
	public static final int SEGS_POR_HORA = 3600;
	public static final int SEGS_POR_MINUTO = 60;
	public static final int MINS_POR_HORA = 60;
	
	public static final long SEGS_POR_DIA = 24 * SEGS_POR_HORA; // o compilador que faça a conta :-) 
	
	
    public Hora( ){
		Calendar cal = Calendar.getInstance();
		horas = cal.get( Calendar.HOUR_OF_DAY );
		minutos = cal.get( Calendar.MINUTE );
		segundos = cal.get( Calendar.SECOND );    	
    }
    
    public Hora( int h, int m, int s){
    	setHoras( h );
    	setMinutos( m );
    	setSegundos( s );
    }
    
    public Hora( long nSegsMeiaNoite ){
        // verificar se nº de segundos não é inválido
        if( nSegsMeiaNoite < 0 )
        	nSegsMeiaNoite =0;
        if( nSegsMeiaNoite >= SEGS_POR_DIA )
        	nSegsMeiaNoite = SEGS_POR_DIA - 1; // SEGS_POR_DIA - 1 porque segundos começam em 0 
        
        // o (int) serve só para evitar mensagens de warning
        horas = (int)(nSegsMeiaNoite / SEGS_POR_HORA);
        minutos = (int)((nSegsMeiaNoite % SEGS_POR_HORA) / SEGS_POR_MINUTO);
        segundos = (int)((nSegsMeiaNoite % SEGS_POR_HORA) % SEGS_POR_MINUTO);
    }
       
    public Hora( String hStr ){
	    // ler a string no formato dia/mês/ano
		String str[] = hStr.split(":");	    
    
	    // verificar se valores são válidos
        setHoras( Integer.parseInt( str[0] ) );
	    setMinutos( Integer.parseInt( str[1] ) );
	    setSegundos( Integer.parseInt( str[2] ) );	        	
    }

    // funções de acesso
    public int getHoras( ) {
    	return horas;
    }
    
    public int getMinutos( ) {
    	return minutos;
    }
    
    public int getSegundos( ) {
    	return segundos;
    }
    
    public void setHoras( int h ){
    	horas = h < 0? 0: h;
    	if( horas > 23 )
    		horas = 23;    	
    }
    
    public void setMinutos( int m ){
    	minutos = m < 0? 0: m;
    	if( minutos > 59 )
    		minutos = 59;    	
    }
    
    public void setSegundos( int s ){
    	segundos = s < 0? 0: s;
    	if( segundos > 59 )
    		segundos = 59;    	
    }
    
    
    // métodos de implementação
    // retornam quantas vezes passaram de 24 horas (nº de dias)
    public int somaHoras( int numHoras ){
        // para garantir que o nº de horas é entre 0 e 23 aceita-se apenas o resto
        // da divisão por 24
        // exemplo: se Horas = 20 e se somar 10 fica = 30 dividindo por 24 dá
        //          30/24 = 1 e resto 6
        //          ficariam então as horas como sendo 6 da manhã
       	int totalHoras = horas + numHoras;
        horas = totalHoras % 24;
        return totalHoras / 24;    	 	
    }
    
    public int somaMinutos( int numMinutos ){
        // somar os novos minutos ao já existentes
        minutos += numMinutos;
        // se a soma dos minutos superar os 59 tem-se de somar horas,
        // tantas quantas couberem dentro dos minutos somados
        int nDias = somaHoras( minutos/SEGS_POR_MINUTO );
        // garantir que minutos está emtre 0 e 59
        minutos = minutos % SEGS_POR_MINUTO;
        return nDias;    	
    }
    
    
    public int somaSegundos( long numSegundos ){
        // procedimento semelhante aos minutos
        // usa-se seg porque segundos é int e perde-se resolução ao somar com long
        long seg = segundos + numSegundos;
        int nDias = somaMinutos( (int)(seg/SEGS_POR_MINUTO) );  // conversão long para int, para o compilador nãos e queixar
        segundos = (int)(seg%SEGS_POR_MINUTO);
        return nDias;     	
    }
    
    // método que retorna o nº de segundos desde a meia-noite desse dia
    public int toSegundos( ) {
    	return horas * SEGS_POR_HORA + minutos * SEGS_POR_MINUTO + segundos;
    }
    
    // método para comparar Horas    
    public int  compareTo( Hora outra){
        if( horas != outra.horas )
            return horas - outra.horas;
        if( minutos != outra.minutos )
            return minutos - outra.minutos;
        return segundos - outra.segundos;       	
    }
    
    public long getDiferencaSegs( Hora outra ){
        return (horas - outra.horas) * SEGS_POR_HORA + (minutos - outra.minutos) * SEGS_POR_MINUTO +
        	   (segundos - outra.segundos);    	
    }
    
    // método para imprimir no écran
    public String toString() {
    	return horas + ":" + minutos + ":" + segundos;
    }

	public Hora clone( ) {
		return new Hora( horas, minutos, segundos);
	}
}
