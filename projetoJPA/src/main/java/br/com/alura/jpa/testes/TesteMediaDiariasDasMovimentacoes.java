package br.com.alura.jpa.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.alura.jpa.dao.MovimentacaoDao;
import br.com.alura.jpa.modelo.MediaComData;

public class TesteMediaDiariasDasMovimentacoes {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();
		
		MovimentacaoDao dao = new MovimentacaoDao(em);
		
		
		for (Double media : dao.getListaMediaMovimentacoesPorDia()) {
			System.out.println("A média das movimentações é: " + media);
		}
		
		System.out.println("---------------------------------");
		
		
		for (Object[] mediaComData : dao.getListaMediaMovimentacoesPorDiaListaObjetos()) {
			System.out.println("A média das movimentações do dia "+ mediaComData[1]+ "/" +mediaComData[2] +"é: " + mediaComData[0]);
		}
		System.out.println("---------------------------------");
		
		List<MediaComData> mediaDasMovimentacoesComDataDiferente = new MovimentacaoDao(em).getMediaDiariaDasMovimentacoes();
		
		for (MediaComData mediaComData : mediaDasMovimentacoesComDataDiferente) {
			System.out.println("A média das movimentações do dia "+ mediaComData.getDia() + "/" +mediaComData.getMes() +"é: " + mediaComData.getMedia());
		}
		System.out.println("---------------------------------");
		
		
		
	}
	
}
