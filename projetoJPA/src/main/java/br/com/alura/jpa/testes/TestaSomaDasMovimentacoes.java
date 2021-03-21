package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import br.com.alura.jpa.dao.MovimentacaoDao;
import br.com.alura.jpa.modelo.Movimentacao;


public class TestaSomaDasMovimentacoes {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		MovimentacaoDao dao = new MovimentacaoDao(em);
		
		System.out.println("A soma das movimentações é: "+ dao.getSomaDasMovimentacoes());
		
		//----média
		
		System.out.println("A média das movimentações é: "+ dao.getSomaDasMovimentacoes2());
		
		//----média
		
		/* CriteriaBuilder é uma interface, para implementa-la
		 * essa interface precisa do EntityManager que possui 
		 * o criador de dele
		 */
		CriteriaBuilder builder = em.getCriteriaBuilder();
		//Para inicializar o objeto precisamos informar o retorno da query
		CriteriaQuery<BigDecimal> query = builder.createQuery(BigDecimal.class);
		
		//Aqui estamos definindo qual classe que vamos mexer nos atributos dela
		Root<Movimentacao> root = query.from(Movimentacao.class);
		
		//Aqui vai ser definido quais parametros da classe vamos usar e qual vai ser o retorno
		Expression<BigDecimal> sum = builder.sum(root.<BigDecimal>get("valor"));
		
		//aqui executamos a query
		TypedQuery<BigDecimal> typedQuery = em.createQuery(sum);
		
		System.out.println("A soma das movimentacoes é: "+typedQuery.getSingleResult());
		
	}

}
