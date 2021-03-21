package br.com.alura.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.dao.MovimentacaoDao;
import br.com.alura.jpa.modelo.Conta;

public class TesteRelatorioDasMovimentacoes {
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		MovimentacaoDao dao = new MovimentacaoDao(em);
		
		List<Conta> contas = dao.getBuscaTodosRegistrosDiferentesDaConta();
		
		for(Conta conta : contas) {
			System.out.println("Titular: " + conta.getTitular());
			System.out.println("Agencia: " + conta.getMovimentacao());
			System.out.println("Numero: " + conta.getNumero());
			System.out.println("Movimentações: " + conta.getMovimentacao());
		}
		
		
		
		
	}
}
