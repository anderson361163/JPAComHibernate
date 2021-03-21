package br.com.alura.jpa.modelo;

public class MediaComData {
	
	private Integer dia;
	private Integer mes;
	private Double media;

	public MediaComData(Integer dia, Integer mes, Double media) {
		this.dia = dia;
		this.mes = mes;
		this.media = media;
	}
	public Integer getDia() {
		return dia;
	}
	public void setDia(Integer dia) {
		this.dia = dia;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public Double getMedia() {
		return media;
	}
	public void setMedia(Double media) {
		this.media = media;
	}
}
