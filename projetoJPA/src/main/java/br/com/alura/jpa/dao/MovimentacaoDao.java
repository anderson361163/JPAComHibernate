package br.com.alura.jpa.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.MediaComData;
import br.com.alura.jpa.modelo.Movimentacao;

public class MovimentacaoDao {
	
	private EntityManager em;
	
	public MovimentacaoDao(EntityManager em) {
		this.em= em;
	}
	
	public List<Movimentacao> getMovimentacoesFiltradasPorData(Integer dia, Integer mes, Integer ano){
		
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Movimentacao> query = builder.createQuery(Movimentacao.class);
		
		
		Root<Movimentacao> root = query.from(Movimentacao.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(dia != null) {
			
			Predicate predicate = builder.equal(builder.function("day", Integer.class, root.get("data")), dia);
			predicates.add(predicate);
		}
		
		if(mes != null) {
			
			Predicate predicate = builder.equal(builder.function("moth", Integer.class, root.get("data")), mes);
			predicates.add(predicate);
		}
		
		if(ano != null) {
			
			Predicate predicate = builder.equal(builder.function("year", Integer.class, root.get("data")), ano);
			predicates.add(predicate);
		}
		
		query.where((Predicate[]) predicates.toArray(new Predicate[0]));
		
		TypedQuery<Movimentacao> typedQuery = em.createQuery(query);
		
		return typedQuery.getResultList();
	}
	
	
	
	public List<MediaComData> getMediaDiariaDasMovimentacoes(){
		/*
		 * EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		 * EntityManager em = emf.createEntityManager();
		 */
		String listaMediaComDataDiferente = "SELECT new br.com.alura.jpa.modelo.MediaComData(AVG(m.valor), day(m.data), moth(m.data)) FROM Movimentacao m GROUP BY DAY(m.data), moth(m.data), YEAR(m.data)";
		
		TypedQuery<MediaComData> qy = em.createQuery(listaMediaComDataDiferente, MediaComData.class);
		return qy.getResultList();
		
	}
	
	public BigDecimal getSomaDasMovimentacoes() {
		/*
		 * EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		 * EntityManager em = emf.createEntityManager();
		 */
		String somaJPQL = "SELECT SUM(m.valor) FROM Conta c LEFT JOIN FETCH c.movimentacoes";
		TypedQuery<BigDecimal> q = em.createQuery(somaJPQL, BigDecimal.class);
		return q.getSingleResult();
	}
	
	public Double getSomaDasMovimentacoes2() {
		/*
		 * EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		 * EntityManager em = emf.createEntityManager();
		 * 
		 */
		String mediaJPQL = "SELECT AVG(m.valor) FROM Conta c LEFT JOIN FETCH c.movimentacoes";
		TypedQuery<Double> query = em.createQuery(mediaJPQL, Double.class);
		return query.getSingleResult();
	}
	
	public List<Double> getListaMediaMovimentacoesPorDia(){
		String somaJPQL = "SELECT AVG(m.valor) FROM Movimentacao m GROUP BY DAY(m.data), moth(m.data), YEAR(m.data)";
		TypedQuery<Double> q = em.createQuery(somaJPQL, Double.class);
		return q.getResultList();
	}
	
	public List<Object[]> getListaMediaMovimentacoesPorDiaListaObjetos(){
		String listaMediaComData = "SELECT AVG(m.valor), day(m.data), moth(m.data) FROM Movimentacao m GROUP BY DAY(m.data), moth(m.data), YEAR(m.data)";
		
		Query query = em.createQuery(listaMediaComData);
		return query.getResultList();
	}
	
	public List<Conta> getBuscaTodosRegistrosDiferentesDaConta(){
		
		String jpql = "SELECT DISTINCT c FROM Conta c LEFT JOIN FETCH c.movimentacoes";
		
		TypedQuery<Conta> query = em.createQuery(jpql, Conta.class);
		
		return query.getResultList();
	}
	
	
}
