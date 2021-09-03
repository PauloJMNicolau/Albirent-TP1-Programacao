package alugar.app;

import alugar.recurso.Aluguer;
import alugar.recurso.Companhia;
import alugar.recurso.Estacao;
import alugar.recurso.veiculo.Carro;
import alugar.recurso.veiculo.Classe;
import consola.SConsola;
import java.util.ArrayList;

import p2.tempo.Data;
import p2.tempo.Hora;
import p2.tempo.PeriodoTempo;
import p2.tempo.Tempo;

/**
 * classe que representa a interface da aplica��o
 */
public class AlbiRentApp {

	// a consola de intera��o
	private SConsola consola = new SConsola("AlbiRent", 400, 750);
	// os dados da companhia de aluguer
	private Companhia companhia;
	
	/**
	 * construtor da Aplica��o
	 * @param companhia os dados da companhia de aluguer
	 */
	public AlbiRentApp( Companhia companhia ){
		this.companhia = companhia;
	}

	/**
	 * menu principal da aplica��o
	 */
	public void menuPrincipal(){
		String menu = "Alugueres de automóvel\n" + 
				"1- Fazer aluguer\n"+
				"2- Ver Alugueres\n" + 
				"3- Ver Carros\n"+
				"4- Ver Classes\n"+
				"5- Ver Carros numa estacao" + 
				"\n0- Sair";

		char opcao = 0;
		do {
			consola.clear();
			consola.println( menu );
			opcao = consola.readChar();
			switch( opcao ){
			case '1':
				fazerAluguer();
				break;
			case '2':
				verAlugueres();
				break;
			case '3':
				verCarros();
				break;
			case '4':
				verClasses();
				break;
			case '5':
				verCarrosEstacao();
				break;
			case '0': break;
			default:
				consola.println( "opção inválida");
				consola.readLine();
			}
		} while( opcao != '0' );

		consola.close();					// desligar o menu da central		
	}

	/**
	 * Pede os dados do aluguer, cria-o e associa-o � companhia
	 */
	private void fazerAluguer() {
		consola.clear();
		consola.println("Menu de aluguer de viatura");
		Estacao estIni = getEstacao( "Estação de recolha?" );
		Estacao estFim = getEstacao( "Estação de entrega?");
		// ver se as estações são a mesma 
		boolean mesmaEstacao = estIni.equals( estFim );
		PeriodoTempo pAluguer = getTempoAluguer();			
		Classe c = escolherClasse(pAluguer, mesmaEstacao );		
		Carro v = escolherViatura(pAluguer, c, estIni);
		
		// criar o aluguer e associa-lo á companhia
                float custo = c.getCusto(pAluguer, mesmaEstacao);
                Aluguer a = new Aluguer(pAluguer, estIni,estFim,custo,v);
                companhia.addAluguer(a);
		// ...
	}

	/**
	 * m�todo que apresenta as classes e permite escolher qual a usada para o aluguer
	 * @param pAluguer o periodo do aluguer
	 * @param mesmaEstacao se o aluguer usa a mesma esta��o na recolha e entrega
	 * @return a classe escolhida
	 */
	private Classe escolherClasse(PeriodoTempo pAluguer, boolean mesmaEstacao ) {
				
		consola.println("Escolha a classe");
		// listar classes
		// para cada classe apresentar
                int numViaturas =0;
                for (Classe c : companhia.getClasses()) {
                    numViaturas= c.getNumCarrosDisponiveis(pAluguer);
                   
                   consola.println(c.getID() + " - " + c.getDescricao() + "\n  " + c.getCusto(pAluguer, mesmaEstacao) +"€ viaturas disponíveis: " + numViaturas);
                }  
                
		Classe c = null;
		do {
			consola.println("Classe? ");
			char ch =  Character.toUpperCase( consola.readChar() );
			//associar a classe ao id da mesma
                        c = companhia.getClasse(ch);
			//associar a classe ao id da mesma
			// enquanto n�o for um id v�lid pedir novamente
		} while( c == null );
		return c;
	}	

	/**
	 * Apresenta as viaturas dispon�veis na classe, durasnte o periodo do aluguer e na esta��o inicial
	 * @param pAluguer o periodo de aluguer
	 * @param c a classe das visturas a apresentar
	 * @param ini a esta��o de levantamento
	 * @return o carro escolhido
	 */
	private Carro escolherViatura(PeriodoTempo pAluguer, Classe c, Estacao ini) {
		consola.println("Escolha a viatura");
		// listar todos os carros
                for(Carro car : companhia.getCarrosDisponiveisEstacao(ini, c, pAluguer)){
                    consola.println(car.getID() + " - " + car.getModelo() ); 
                    
                }
		Carro v = null;
		do {
			consola.println("carro (id)? ");
			int cid = consola.readInt();	
			// associar o id ao carro
                        v = companhia.getCarro(cid);
			// associar o id ao carro
		} while( false /* o id n�o � dos carros apresentados */ );
		return v;
	}

	/**
	 * pede os dados do periodo de tempo do aluguer
	 * @return o periodo de tempo do aluguer
	 */
        
	private PeriodoTempo getTempoAluguer() {
            
		consola.println( "Dia/mês do início do aluguer?" );
                String sdi;
                do{
                    sdi = consola.readLine();
                }while(!sdi.matches("\\d{1,2}"+"/"+"\\d{1,2}"));
                Data di = new Data( sdi+"/2014" );
		
		consola.println( "Hora de início do aluguer?" );
		int ihi = consola.readInt();
		Hora hi = new Hora( ihi, 0, 0);
		
		consola.println( "Dia/mês do fim do aluguer?" );
		String sdf;
                do{
                    sdf = consola.readLine();
                }while(!sdf.matches("\\d{1,2}"+"/"+"\\d{1,2}"));
                
		Data df = new Data( sdf+"/2014" );
		
		consola.println( "Hora de fim do aluguer?" );
		int ihf = consola.readInt();
		Hora hf = new Hora( ihf, 0, 0);
		
		// criar e retornar o periodo de tempo
                PeriodoTempo p = new PeriodoTempo(new Tempo(di,hi), new Tempo(df,hf));
		return p;
	}
	
	/**
	 * pede os dados ed uma esta��o
	 * @param msg mensagem para pedir a esta��o
	 * @return a esta��o indicada pelo utilizador
	 */
	private Estacao getEstacao( String msg ){
		consola.println( "Estações disponíveis");
                for(Estacao e : companhia.getEstacoes()){
                    consola.println(e.getID() + " " + e.getNome());
                }
                consola.println( msg );
                
		// associar a esta��o ao id e retorn�-la
		int id = consola.readInt();
                Estacao est = companhia.getEstacao(id);
                return est;
	}

	/**
	 * apresenta todos os alugueres existentes na companhia
	 */
	private void verAlugueres() {
		consola.clear();
		consola.println( "Alugueres no sistema" );
                for(Aluguer a : companhia.getAlugueres()){
		    consola.println(a.getPeriodo().getIni() + " " + a.getEstLevant().getNome() + " - " + 
		                     a.getPeriodo().getFim() + " " + a.getEstEntrega().getNome() + " " + a.getCusto() + " " + a.getCarro().getModelo());
                }
		consola.readLine();		
	}

	/**
	 * lista todos os carros do sistema e os detalhes do carro escolhido
	 */
	private void verCarros() {
		consola.clear();
		consola.println( "Carros no sistema" );
		// apresentar todos as viatura do sistema
                for(Carro c : companhia.getCarros()){
                    consola.println(c.getID() + " " + c.getModelo());
                }
                
		consola.println("Id para visualizar: ");
		int id = consola.readInt();
		// para o carro escolhido
                Carro car = companhia.getCarro(id);
		consola.clear();
		consola.println( car.getID() +" "+ car.getModelo() + " classe " + car.getClasse().getDescricao() + " em " + car.getEstacao().getNome());
                
                // listar todos os alugueres do carro
                if (companhia.getAlugueres() != null){
                for(Aluguer a : companhia.getAlugueres()){
                    if(a.getCarro().equals(car)){
                        consola.println(a.getPeriodo().getIni() + " " + a.getEstLevant().getNome() + " - " + 
		                     a.getPeriodo().getFim() + " " + a.getEstEntrega().getNome() + " " + a.getCusto());
                    }
                }
                }
			//consola.println( "<Inicio aluguer> <Esta��o ini> - <Fim aluguer> <Esta��o fim>  <pre�o>�" );
		consola.readLine();		
	}

	/**
	 * apresenta as classes do sistema
	 */
	private void verClasses() {
		consola.clear();
		consola.println( "Classes no sistema" );
		// listar todas as classes do sistema
                for (Classe c : companhia.getClasses()){
                    consola.println(c.getID() + " - " + c.getDescricao() );  
                }
		// pedir a classe a visualizar
		consola.println("Id para visualizar: ");
		char id = Character.toUpperCase( consola.readChar() );
		consola.clear();
		// para a classe escolhida
                Classe cls = companhia.getClasse(id);
		consola.println( cls.getID() + " - " + cls.getDescricao());
		consola.println( "carros nesta classe" );
		// listar todos os carros da classe
                for (Carro c : companhia.getCarros()){
                    if (c.getClasse().equals(cls)){
                        consola.println( c.getID() + " - " + c.getModelo() + " em " + c.getEstacao().getNome() );
                    }
                }
		consola.readLine();
	}

	/**
	 * Ver quais os carros presentes numa esta��o num dado momento
	 */
	private void verCarrosEstacao() {
		consola.clear();
                Estacao est = getEstacao( "Estação a visualizar?" );
		consola.println( "Data?" );
		String sd = consola.readLine();
		Data d = new Data( sd+"/2014" );
		consola.println( "Hora?" );
		int ih = consola.readInt();
		Hora h = new Hora( ih, 0, 0);
		Tempo t =  new Tempo( d, h );
		consola.println( "Carros na estação" );
		// listar todos os carros na esta��o
                ArrayList<Carro> c = companhia.getCarrosEstacao(est, t);
                if (!c.isEmpty()){
                    for (Carro car : c){ 

                        consola.println(car.getID() + " - " + car.getModelo() + " classe " + car.getClasse().getID());  
                    }
                }
		consola.readLine();		
	}

   
}
