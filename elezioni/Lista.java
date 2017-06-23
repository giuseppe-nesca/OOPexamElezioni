package elezioni;

import java.util.ArrayList;
import java.util.Collection;


public class Lista {

	private String nome, motto;
	private Cittadino capolista;
	private Collection<Cittadino> candidati = new ArrayList<>();
	
	public Lista(String nome, String motto){
		this.nome = nome;
		this.motto = motto;
		Cittadino capolista;
	}
	
	public String getNome(){
		return nome;
	}

	public String getMotto(){
		return motto;
	}
	
	public void assegnaCapolista(Cittadino capolista)
			throws CandidatoNonValido {
		if(!capolista.isCapolista()){
			this.capolista = capolista;
			Candidato candidato = (Candidato) capolista;
			candidato.setCapolista(true);
		}else{
			throw new CandidatoNonValido("il cittadino selezionato e' gia' capolista di una lista");
		}
	}

	public void aggiungiCandidato(Cittadino capolista) //e invece e' capolista
			throws CandidatoNonValido {
		if(!capolista.isCandidato()){
			candidati.add(capolista);
			Candidato candidato = (Candidato) capolista;
			candidato.setCandidato(true);
		}else{
			throw new CandidatoNonValido("il cittadino selezionato e' gia' in una lista");
		}
	}

	public Cittadino getCapolista(){
		return capolista;
	}

	/**
	 * Restuisce la collezione dei candidati
	 * (NON include il capolista)
	 */
	public Collection getCandidati(){
		return candidati;
	}
	
	
	public long getNumeroVoti(){
		return -1;
	}

	public double getPercentualeVoti(){
		return -1.1;
	}
}
