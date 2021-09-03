package alugar.recurso.veiculo;

import alugar.recurso.Aluguer;
import alugar.recurso.Estacao;
import java.util.Vector;
import p2.tempo.PeriodoTempo;
import p2.tempo.Tempo;

/**
 * 
 * @author Paulo Nicolau
 * @author waldir Vigário
 */
public class Carro {
    private int id;
    private String modelo;
    private Classe classe;
    private Estacao estacao;
    private Vector<Aluguer> alugueres = new Vector<Aluguer>();
    
    public Carro(int id, String modelo, Classe classe, Estacao estacao){
        setID(id);
        setModelo(modelo);
        setClasse(classe);
        setEstacao(estacao);
    }
    
    public void setID(int id){
        this.id = validaID(id);
    }
    
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    
    public void setClasse(Classe classe){
        this.classe= classe;
    }
    
    public void setEstacao(Estacao estacao){
        this.estacao = estacao;
    }
    
    public void setAluguer(Aluguer a){
        this.alugueres.add(a);
    }
    
    /**
     * Devolve id do carro
     * @return id carro
     */
    public int getID(){
        return id;
    }
    
    /**
     * Devolve modelo do carro
     * @return modelo do carro
     */
    public String getModelo(){
        return modelo;
    }
    
    /**
     * Devolve classe do carro
     * @return idClasse Classe do carro
     */
    public Classe getClasse(){
        return classe;
    }
    
    /**
     * Devolve estaçao inicial do carro
     * @return estacaoInicial do carro
     */
    public Estacao getEstacao(){
        return estacao;
    } 
    
    public Vector<Aluguer> getAluguer(){
        return alugueres;
    }
    
    /**
     * Valida valor do id
     * @param id valor do id do carro
     * @return id - valor do carro validado
     */
    private int validaID(int id){
        if (id < 0)
            id = 0;
        return id;
    }
    
    /**
     * Verifica se os Carros são iguais
     * @param c
     * @return id
     */
    public boolean equals(Carro c){
        return c.id == id;
    }
    
    public boolean estaDisponivel(Tempo t){
        boolean disponivel = true;
        for (Aluguer aluguere : alugueres) {
            if (aluguere.getPeriodo().estaDentro(t)) {
                disponivel = false;
            }
        }
        return disponivel;
    }
    
    public boolean estaDisponivel(PeriodoTempo t){
        boolean disponivel = true;
        if (alugueres != null){
            for (Aluguer aluguere : alugueres) {
                if (aluguere.getPeriodo().contem(t)) {
                    disponivel = false;
                }
            }
        }
        return disponivel;
    }
    
    public Estacao ondeEsta(Tempo t){
        if (alugueres !=null){
            for (Aluguer aluguere : alugueres) {
                if (aluguere.getPeriodo().estaDentro(t)) {
                    return aluguere.getEstEntrega();
                }
            }
        }
        return estacao;
    }
}
