/**
 * @author Paulo Nicolau
 * @author Waldir Vigário
 */

package alugar.recurso;

public class Estacao {
    //Atributos da Classe
    private int id;
    private String nome;
    
    /**
     * Construtores
     */
    
    public Estacao(int id, String nome){
        validaID(id);
        validaNome(nome);    
    }
    
    /**
     * SetID
     * @param id - ID da Estação 
     */
    public void setID(int id){
        validaID(id);
    }
    
    /**
     * SetNome
     * @param nome - nome da Estação 
     */
    public void setNome(String nome){
        validaNome(nome);
    }
    
    /**
     * GetID
     * @return id - Valor do id da Estação 
     */
    public int getID(){
        return id;
    }
    
    /**
     * GetNome
     * @return nome - Valor do nome da Estação 
     */
    public String getNome(){
        return nome;
    }
    
    /**
     * Metodo de Validação de Dados ID
     * @param id - Valor do id da estação
     */
    private void validaID(int id){
        if(id < 0)
           id=0;
        this.id = id;
    }
    
    /**
     * Metodo de Validação do Nome
     * @param nome - Nome da Estação
     */
    private void validaNome(String nome){
        if (nome.equals(" "))
            nome="Sede";
        this.nome = nome;
    }
    
    /**
     * Verifica se as estações são iguais
     * @param estacao - Uma outra estação
     * @return true se id for igual, false se id for diferente
     */
    public boolean equals(Estacao estacao){
        return (id == estacao.getID());
    }
}
