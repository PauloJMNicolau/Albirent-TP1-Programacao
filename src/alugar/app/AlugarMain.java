package alugar.app;

import alugar.recurso.*;
import alugar.recurso.veiculo.*;

public class AlugarMain {

	public static void main(String[] args) {
		// cria��o da companhia de aluguer
		Companhia companhia = new Companhia();
		
		/*
		 * Criar aqui os carros, classes e esta��es 
		 */
		Estacao e1 = new Estacao(1,"Castelo Branco");
                companhia.addEstacao(e1);
                Estacao e2 = new Estacao(2,"Portalegre");
                companhia.addEstacao(e2);
                Classe c1 = new Classe('A',"Carros económicos e ágeis",80,4,50);
                companhia.addClasse(c1);
                Classe c2 = new Classe('B',"Carros compactos",100,5,50);
		companhia.addClasse(c2);
                Classe c3 = new Classe('C',"Carros compactos mas potentes",120,6,80);
                companhia.addClasse(c3);
                Classe c4 = new Classe('D',"Carros familiares",150,7,100);
                companhia.addClasse(c4);
                Classe c5 = new Classe('E',"Carros familiares de luxo",250,11,150);
                companhia.addClasse(c5);
                Classe c6 = new Classe('F',"Carros desportivos",2500,300,200);
                companhia.addClasse(c6);
                /* Carros */
               
                companhia.addCarro(new Carro(1,"Peugeot 107 1.0", c1,e1));
               
                companhia.addCarro(new Carro(2,"Peugeot 107 1.0", c1,e2));
            
                companhia.addCarro(new Carro(3,"Open Ágila 1.0", c1,e1));
                companhia.addCarro(new Carro(4,"Opel Ágila 1.0", c1,e2));
                companhia.addCarro(new Carro(5,"vW Up 1.0", c1, e1));
                companhia.addCarro(new Carro(6,"vW Up 1.0", c1, e2));
                companhia.addCarro(new Carro(7,"Fiat Panda 0.8", c1, e1));
                companhia.addCarro(new Carro(8,"Fiat Panda 0.8", c1, e2));
                companhia.addCarro(new Carro(10,"Fiat Punto 1.3", c2, e1));
                companhia.addCarro(new Carro(11,"Fiat Punto 1.3", c2, e2));
                companhia.addCarro(new Carro(12,"Opel Corsa 1.2", c2, e1));
                companhia.addCarro(new Carro(13,"Opel Corsa 1.2", c2, e2));
                companhia.addCarro(new Carro(14,"VW Polo 1.2", c2, e1));
                companhia.addCarro(new Carro(15,"VW Polo 1.2", c2, e2));
                companhia.addCarro(new Carro(16,"Peugeot 208 1.2", c2, e1));
                companhia.addCarro(new Carro(17,"Peugeot 208 1.2", c2, e2));
                companhia.addCarro(new Carro(18,"Kia Rio 1.2", c2, e1));
                companhia.addCarro(new Carro(19,"Kia Rio 1.2", c2, e2));
                companhia.addCarro(new Carro(20,"Citroen C3 1.2", c2, e1));
                companhia.addCarro(new Carro(21,"Citroen C3 1.2", c2, e2));
                companhia.addCarro(new Carro(30,"Fiat Bravo 1.6", c3, e1));
                companhia.addCarro(new Carro(31,"Fiat Bravo 1.6", c3, e2));
                companhia.addCarro(new Carro(32,"VW Golf 1.6", c3, e1));
                companhia.addCarro(new Carro(33,"VW Golf 1.6", c3, e2));
                companhia.addCarro(new Carro(34,"Opel Astra 1.7", c3, e1));
                companhia.addCarro(new Carro(35,"Opel Astra 1.7", c3, e2));
                companhia.addCarro(new Carro(36,"Peugeot 308 1.6", c3, e1));
                companhia.addCarro(new Carro(37,"Peugeot 308 1.6", c3, e2));
                companhia.addCarro(new Carro(38,"Citroen C4 1.6", c3, e1));
                companhia.addCarro(new Carro(39,"Citroen C4 1.6", c3, e2));
                companhia.addCarro(new Carro(40,"Ford Focus 1.6", c3, e1));
                companhia.addCarro(new Carro(41,"Ford Focus 1.6", c3, e2));
                companhia.addCarro(new Carro(50,"Toyota Avensis 1.6", c4, e1));
                companhia.addCarro(new Carro(51,"Toyota Avensis 1.6", c4, e2));
                companhia.addCarro(new Carro(52,"VW Passat 1.6", c4, e1));
                companhia.addCarro(new Carro(53,"VW Passat 1.6", c4, e2));
                companhia.addCarro(new Carro(54,"Citroen C5 1.6", c4, e1));
                companhia.addCarro(new Carro(55,"Citroen C5 1.6", c4, e2));
                companhia.addCarro(new Carro(56,"Peugeot 508 1.6", c4, e1));
                companhia.addCarro(new Carro(57,"Peugeot 508 1.6", c4, e2));
                companhia.addCarro(new Carro(60,"BMW 530d", c5, e1));
                companhia.addCarro(new Carro(61,"BMW 530d", c5, e2));
                companhia.addCarro(new Carro(62,"BMW 530d", c5, e1));
                companhia.addCarro(new Carro(63,"Audi A6 2.2", c5, e2));
                companhia.addCarro(new Carro(64,"Audi A6 2.2", c5, e1));
                companhia.addCarro(new Carro(65,"Audi A6 2.2", c5, e2));
                companhia.addCarro(new Carro(66,"Mercedes C220 2.2", c5, e1));
                companhia.addCarro(new Carro(67,"Mercedes C220 2.2", c5, e2));
                companhia.addCarro(new Carro(68,"Mercedes C220 2.2", c5, e1));
                companhia.addCarro(new Carro(70,"Ferrari 458 Italia", c6, e2));
                companhia.addCarro(new Carro(71,"Lamborghini Gallardo", c6, e1));
                companhia.addCarro(new Carro(72,"Aston Martin V8 Vantage S", c6, e2));
                companhia.addCarro(new Carro(73,"Porche 911 Turbo Coupe", c6, e1));
                for(Carro c : companhia.getCarros()){
                    Classe cls = companhia.getClasse(c.getClasse().getID());
                        cls.setCarros(c);
                
                }
                    
                // criar a aplica��o e execut�-la
		AlbiRentApp m = new AlbiRentApp( companhia );
		m.menuPrincipal();
	}
}
