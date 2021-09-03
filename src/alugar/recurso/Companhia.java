package alugar.recurso;

import alugar.recurso.veiculo.Carro;
import alugar.recurso.veiculo.Classe;
import java.util.*;
import p2.tempo.*;

public class Companhia {

	// vector com todos os alugueres da companhia
	private Vector<Aluguer> alugueres = new Vector<Aluguer>();
	
	// usar mapas para associar os ids aos v�rios objectos
	private Map<Character,Classe> classes = new HashMap<Character, Classe>();
	private Map<Integer,Carro> carros = new HashMap<Integer, Carro>();
	private Map<Integer,Estacao> estacoes = new HashMap<Integer, Estacao>();

	/**
	 * adicionar uma classe � companhia
	 * @param c a classe a adicionar
	 */
	public void addClasse( Classe c ){
		classes.put(c.getID(), c);		
	}
	
	/**
	 * devolve todas as classes presentes no sistema
	 * @return todas as classes presentes no sistema
	 */
	public Classe[] getClasses(){
		return classes.values().toArray(new Classe[classes.values().size()]);
	}
	
	/**
	 * retorna a classe com o id indicado
	 * @param id id da classe
	 * @return a classe com o id indicado
	 */
	public Classe getClasse( char id ){
		return classes.get( id );
	}
	
	/**
	 * remove a classe com o id indicado
	 * @param id id da classe a remover
	 */
	public void removeClasse( char id ){
		classes.remove( id );
	}
	
	/**
	 * devolve todas as classes que est�o dispon�veis no per�odo de tempo indicado.
	 * Uma classe est� dispon�vel se tiver pelo menos uma viatura dispon�vel
	 * @param p periodo de tempo a testar  
	 * @return as classes que est�o dispon�veis no per�odo de tempo indicado
	 */
	public Vector<Classe> getClassesDisponiveis( PeriodoTempo p ){
            Vector<Classe> classe = new Vector(classes.size());
            for (Aluguer aluguere : alugueres) {
                if (!aluguere.getPeriodo().estaContido(p)) {
                    if (!classe.contains(aluguere.getCarro().getClasse())) {
                        classe.add(aluguere.getCarro().getClasse());
                    }
                }
            }
            return classe;
	}
	
	/**
	 * adicionar um carro � companhia
	 * @param c o carro a adicionar
	 */
	public void addCarro( Carro c ){
            carros.put(c.getID(), c);// adicionar um carro � companhia
        }
	
	/**
	 * remove o carro com o id indicado da companhia
	 * @param id id do carro a remover
	 */
	public void removeCarro( int id ){
		carros.remove( id );
	}
	
	/**
	 * retortna o carro com um dado id
	 * @param id id do carro a retornar
	 * @return o carro com o id indicado
	 */
	public Carro getCarro( int id ){
		return carros.get( id );
	}
	
	/** retorna todos os carros na companhia
	 * @return todos os carros na companhia
	 */
	public Carro[] getCarros() {
		return carros.values().toArray(new Carro[carros.values().size()]);
	}
        

	/** adicionar uma esta��o � companhia 
	 * @param e esta��o a adicionar
	 */
	public void addEstacao( Estacao e ){
		estacoes.put(e.getID(), e);
	}
	
	/** remove a esta��o com o id da companhia
	 * @param id id da esta��o a remover
	 */
	public void removeEstacao( int id ){
		estacoes.remove( id );
	}
	
	/** retorna a esta��o que tem o id indicado
	 * @param id id da esta��o
	 * @return a esta��o que tem o id indicado
	 */
	public Estacao getEstacao( int id ){
		return estacoes.get( id );
	}
	
	/** retorna todas as ta��es da companhia
	 * @return todas as esta�a�es da companhia
	 */
	public Estacao[] getEstacoes() {
		return estacoes.values().toArray(new Estacao[estacoes.size()]);
	}
        
	/** retorna todos os carros que est�o presentes na esta��o num dado tempo
	 * @param e esta��o onde devem estar os carros
	 * @param t quando l� devem estar os carros
	 * @return os carros que est�o presentes na esta��o naquele tempo
	 */
	public ArrayList<Carro> getCarrosEstacao( Estacao e, Tempo t ){
            ArrayList<Carro> carro = new ArrayList<Carro>(carros.size());
            Carro[] cars = getCarros();
            boolean disponivel;
            for (Carro c : cars){
                disponivel = true;
                if (c.getEstacao().equals(e)){
                    if(!alugueres.isEmpty()){
                        for(Aluguer a : alugueres){
                            if(a.getCarro().equals(c)){
                                if(a.getPeriodo().estaDentro(t)){
                                    disponivel= false; 
                                }
                            }
                        }
                    }
                } else {
                    disponivel=false;
                }
                if(disponivel){
                    carro.add(c);
                }    
            }
            return carro;
	}

	/** retorna todos os carros que est�o presentes na esta��o num dado per�odo de tempo
	 * @param e esta��o onde devem estar os carros
	 * @param t quando l� devem estar os carros
	 * @return os carros que est�o presentes na esta��o naquele per�odo de tempo
	 */
	public ArrayList<Carro> getCarrosDisponiveisEstacao( Estacao e, PeriodoTempo t ){
                ArrayList<Carro> carro = new ArrayList<>(carros.size());
                Carro[] cars = getCarros();
            boolean disponivel;
            for (Carro c : cars){
                disponivel = true;
                if (c.getEstacao().equals(e)){
                    if(!alugueres.isEmpty()){
                        for(Aluguer a : alugueres){
                            if(a.getPeriodo().estaContido(t)){
                               disponivel= false; 
                            }
                            
                        }
                    }
                    for (Carro car : carro){
                        if (car.equals(c)){
                            disponivel = false;
                        }
                    }
                } else {
                        disponivel=false;
                }
                if(disponivel){
                    carro.add(c);
                }
               
                
            }
            
		return carro;
	}

	/**  retorna todos os carros de uma dada classe que est�o
	 * presentes na esta��o num dado per�odo de tempo
	 * @param e esta��o onde devem estar os carros
	 * @param cls a que classe devm os carros pertencer
	 * @param t quando l� devem estar os carros
	 * @return todos os carros de uma dada classe que est�o
	 * presentes na esta��o num dado per�odo de tempo
	 */
	public ArrayList<Carro> getCarrosDisponiveisEstacao( Estacao e, Classe cls, PeriodoTempo t ){
            ArrayList<Carro> carro = new ArrayList<>(carros.size());
            Classe cars = classes.get(cls.getID());
            for(Carro c : cars.getCarrosDisponiveis(t)){
                if(c.getEstacao().equals(e)){
                    carro.add(c);
                }
            }
            return carro;
	}
	
	/**
	 * retorna todos os alugueres na companhia
	 * @return todos os alugueres na companhia
	 */
	public Vector<Aluguer> getAlugueres() {
		return alugueres;
	}
	
	/** adiciona um aluguer � companhia
	 * @param a aluguer a adicioanr
	 */
	public void addAluguer( Aluguer a ){
		alugueres.add(a);
                carros.get(a.getCarro().getID()).setAluguer(a);
	}
	
	/** remove um aluguer da companhia
	 * @param a aluguer a remover
	 */
	public void removeAluguer( Aluguer a ){
            alugueres.remove(a);
	}	
}