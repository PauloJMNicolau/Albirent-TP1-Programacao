package alugar.recurso.veiculo;

import alugar.recurso.Aluguer;
import java.util.Vector;
import p2.tempo.PeriodoTempo;

/**
 *
 * @author Paulo Nicolau
 * @author Waldir Vigário
 */
public class Classe {
    private char id;
    private String descricao;
    private float custoDia;
    private float custoHora;
    private int taxaViagem;
    private Vector<Carro> carros = new Vector<Carro>();
    
    public Classe(char id, String descricao, float custoDia, float custoHora, int taxa){
        this.validaID(id);
        this.validaDescricao(descricao);
        this.custoDia=this.validaCusto(custoDia);
        this.custoHora=this.validaCusto(custoHora);
        this.validaTaxa(taxa);
    }
    
    public void setCarros(Carro c){
            carros.add(c);
    }
    
    public void removeCarro(Carro c){
        carros.remove(c);
    }
    
    public Vector<Carro> getCarros(){
        return carros;
    }
    
    public int getNumCarros(){
        return carros.size();
    }
    
    private void validaID(char id){
        if (id < 'A') {
            id = 'A';
        } else if(id > 'F') {
            id = 'F';
        }
        this.id=id;
    }
    
    private void validaDescricao(String descricao){
        if (descricao.equals(" ")) {
            descricao="Carros económicos e ágeis";
        }
        this.descricao=descricao;
    }
    
    private float validaCusto(float custo){
        if(custo < 0)
            custo=0;
        return custo;
    }
    
    private void validaTaxa(int taxa){
        if (taxa < 0)
            taxa=50;
        this.taxaViagem=taxa;
    }
    
    public void setID(char id){
        validaID(id);
    }
    
    public void setDescricao(String descricao){
        validaDescricao(descricao);
    }
    
    public void setCustoDia(int custo){
        custoDia= validaCusto(custo);
    }
    
    public void setCustoHora(int custo){
        custoHora= validaCusto(custo);
    }
    
    public void setTaxa(int taxa){
        validaTaxa(taxa);
    }
    
    public char getID(){
        return id;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public float getCustoDia(){
        return custoDia;
    }
    
    public float getCustoHora(){
        return custoHora;
    }
    
    public int getTaxa(){
        return taxaViagem;
    }
    
    public boolean equals(Classe c){
        return c.id == id;
    }
    
    public float getCusto(PeriodoTempo t, boolean mesmaEst){
        if (mesmaEst) {
            return calcCustoMesmaEst(t);
        }
        return calcCustoOutraEst(t);
    }
    
    public int getNumCarrosDisponiveis(PeriodoTempo t){
        int count=0;
        boolean disponivel=true;
        if (!carros.isEmpty()){
            for(Carro c : carros){
                disponivel=true;
                for(Aluguer a : c.getAluguer()){
                    if (a.getPeriodo().intersecta(t)){
                        disponivel = false;
                    }
                }
                if (disponivel){
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public Vector<Carro> getCarrosDisponiveis(PeriodoTempo t){
        Vector<Carro> car= new Vector<Carro>(carros.size());
        boolean disponivel=true;
        if (!carros.isEmpty()){
            for(Carro c : carros){
                disponivel=true;
                for(Aluguer a : c.getAluguer()){
                    if (a.getPeriodo().intersecta(t)){
                        disponivel = false;
                    }
                }
                if (disponivel){
                    car.add(c);
                }
            }
        }
        return car;
    }
    
    
    private float calcCustoMesmaEst(PeriodoTempo t){
        return t.calcNumDias() * custoDia + t.calcNumHoras() * custoHora ;
    }

    private float calcCustoOutraEst(PeriodoTempo t){
        return t.calcNumDias() * custoDia + t.calcNumHoras() * custoHora + taxaViagem;
    }
}
