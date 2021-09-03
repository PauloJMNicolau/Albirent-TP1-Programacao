package alugar.recurso;

import alugar.recurso.veiculo.Carro;
import p2.tempo.PeriodoTempo;

/**
 *
 * @author Paulo Nicolau
 * @author Waldir Vigário
 */
public class Aluguer {
    private PeriodoTempo periodo;
    private Estacao estLevant;
    private Estacao estEntrega;
    private float custo;
    private Carro carro;
    
    public Aluguer(PeriodoTempo periodo, Estacao estLevant, Estacao estEntrega, float custo, Carro carro){
        this.setPeriodo(periodo);
        this.setEstLevant(estLevant);
        this.setEstEntrega(estEntrega);
        this.setCusto(custo);
        this.setCarro(carro);
    }
    
    
    public PeriodoTempo getPeriodo(){
        return periodo;
    }
    
    public Estacao getEstLevant(){
        return estLevant;
    }
    
    public Estacao getEstEntrega(){
        return estEntrega;
    }
    
    public float getCusto(){
        return custo;
    }
    
    public Carro getCarro(){
        return carro;
    }
    
    public void setPeriodo(PeriodoTempo tempo){
        periodo = tempo;
    }
    
    public void setEstLevant(Estacao estLevant){
        this.estLevant = estLevant;
    }
    
    public void setEstEntrega(Estacao estRecolha){
        estEntrega = estRecolha;
    }
    
    public void setCusto(float custo){
        this.custo=custo;
    }
    
    public void setCarro(Carro carro){
        this.carro=carro;
    }
}
