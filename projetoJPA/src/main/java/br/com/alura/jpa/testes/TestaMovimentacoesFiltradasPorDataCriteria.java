package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.dao.MovimentacaoDao;
import br.com.alura.jpa.modelo.Movimentacao;

public class TestaMovimentacoesFiltradasPorDataCriteria {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		MovimentacaoDao movimentacaoDao = new MovimentacaoDao(em);
		List<Movimentacao> movimentacoesFiltradasPorData = 
				movimentacaoDao.getMovimentacoesFiltradasPorData(null, null, 2017);
		
		for(Movimentacao movimentacao : movimentacoesFiltradasPorData) {
			System.out.println("Descrição -> " + movimentacao.getDescricao());
			System.out.println("Descrição -> " + movimentacao.getData());
			System.out.println("-----------------------------------------");
		}
		
		System.out.println("*************************************************************");
	
		MovimentacaoDao movimentacaoDao2 = new MovimentacaoDao(em);
		List<Movimentacao> movimentacoesFiltradasPorData2 = 
				movimentacaoDao2.getMovimentacoesFiltradasPorData(12, null, 2017);
		
		for(Movimentacao movimentacao : movimentacoesFiltradasPorData2) {
			System.out.println("Descrição -> " + movimentacao.getDescricao());
			System.out.println("Descrição -> " + movimentacao.getData());
			System.out.println("-----------------------------------------");
		}
	
		System.out.println("*************************************************************");
		
		MovimentacaoDao movimentacaoDao3 = new MovimentacaoDao(em);
		List<Movimentacao> movimentacoesFiltradasPorData3 = 
				movimentacaoDao3.getMovimentacoesFiltradasPorData(12, 05, 2017);
		
		for(Movimentacao movimentacao : movimentacoesFiltradasPorData3) {
			System.out.println("Descrição -> " + movimentacao.getDescricao());
			System.out.println("Descrição -> " + movimentacao.getData());
			System.out.println("-----------------------------------------");
		}
		
		System.out.println("*************************************************************");
		
		MovimentacaoDao movimentacaoDao4 = new MovimentacaoDao(em);
		List<Movimentacao> movimentacoesFiltradasPorData4 = 
				movimentacaoDao4.getMovimentacoesFiltradasPorData(null, 05, null);
		
		for(Movimentacao movimentacao : movimentacoesFiltradasPorData4) {
			System.out.println("Descrição -> " + movimentacao.getDescricao());
			System.out.println("Descrição -> " + movimentacao.getData());
			System.out.println("-----------------------------------------");
		}
		
		System.out.println("*************************************************************");
		
		//Como não foi aplicado nenhum filtro ele trouxe todos os registros
		MovimentacaoDao movimentacaoDao5 = new MovimentacaoDao(em);
		List<Movimentacao> movimentacoesFiltradasPorData5 = 
				movimentacaoDao5.getMovimentacoesFiltradasPorData(null, null, null);
		
		for(Movimentacao movimentacao : movimentacoesFiltradasPorData5) {
			System.out.println("Descrição -> " + movimentacao.getDescricao());
			System.out.println("Descrição -> " + movimentacao.getData());
			System.out.println("-----------------------------------------");
		}
		
		
	}
}
