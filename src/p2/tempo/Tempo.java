package p2.tempo;

public class Tempo implements Cloneable {
	
	private Data aData;
	private Hora aHora;

	public Tempo(){
		aData =new Data();
		aHora = new Hora();
	}
	
	public Tempo( Data d, Hora h ){
		aData = d;
		aHora = h;
	}
	

    // m�todos de acesso
	public Data getData( ) {
    	return aData;
    }
    
    public Hora getHora( ) {
    	return aHora;
    }
    
    
    public void setData( Data d ) {
    	aData = d;
    }
    
    
    public void setHora( Hora h ) {
    	aHora = h;
    }

    // m�todos para manipula��o de dias e horas simultaneamente    
    public void somaDias( int numDias )   {
    	aData.somaDias( numDias );
    }
    
    public void somaMeses( int numMeses ) {
    	aData.somaMeses( numMeses );
    }
    
    public void somaAnos( int numAnos ) {
    	aData.somaAnos( numAnos );
    }
        
    public void somaHoras( int numHoras ){
    	aData.somaDias( aHora.somaHoras( numHoras ) );    	   
    }
        
    public void somaMinutos( int numMinutos ){
    	aData.somaDias( aHora.somaMinutos( numMinutos ) );
    }
        
    public void somaSegundos( long numSegundos ) {
    	aData.somaDias( aHora.somaSegundos( numSegundos ) );
    }
    
    
    // m�todos de compara��o
    public int compareTo( Tempo outro ) {
        int difData = aData.compareTo( outro.aData ); // compara as datas
        
        if( difData != 0 )  // se as datas forem diferentes a compara��o est� feita
            return difData;          
            
        return aHora.compareTo( outro.aHora );        // sen�o � preciso ver as horas    	
    }
    
    public long getDiferencaSegs( Tempo outro ){
        long nDias = aData.getDiferencaDias( outro.aData );       // ver diferen�a em dias
        long nSegs = aHora.getDiferencaSegs( outro.aHora );
        
        return nDias*Hora.SEGS_POR_DIA + nSegs;     	
    }
    
    
    public int getDiferencaDias( Tempo outro ){
        return (int)(getDiferencaSegs( outro ) / Hora.SEGS_POR_DIA);    	
    }
        
    // m�todo para imprimir
    public String toString(){
    	return aData + " " + aHora;
    }
	
	public Tempo clone( ) {
		return new Tempo( aData.clone(), aHora.clone() );
	}

    public long toSegs(){
        Tempo unixTime = new Tempo(new Data(1,1,1970), new Hora(0,0,0));
        return unixTime.getDiferencaSegs(this);
    }    
}
