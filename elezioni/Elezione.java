package elezioni;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



public class Elezione {
	
	private Collection<Cittadino>elettori = new ArrayList<>();
	private Map<String,Lista> liste = new HashMap<>();

	public Elezione(){
		
	}
	
	public Cittadino aggiungiElettore(String nome, String cognome){
		Cittadino cittadino = new Elettore(nome, cognome);
		elettori.add(cittadino);
		return cittadino;
	}
	
	public Collection getElettori(){
		return elettori;
	}
	
	public Cittadino getElettore(String nome, String cognome){
		for (Cittadino cittadino : elettori) {
			if(cittadino.getNome() == nome && cittadino.getCognome() == cognome){
				return cittadino;
			}
		}
		System.out.println("Cittadino non presente in elettori");
		return null;
	}
	
	public void registraLista(Lista lista){
		liste.put(lista.getNome(), lista);
	}

    /**
     * Il cittadino votante esprime un voto per la lista ed 
     * un voto di preferenza per il candidato identificato
     * da nome e cognome
     * @throws TentatoDoppioVoto se il cittadino ha gi� votato
     * @throws TaglioNonPermesso se il candidato per cui si esprime
     * 							la preferenza non appartiene alla lista
     */	
	public void vota(Cittadino votante, String lista, String nome, String cognome)
		throws TentatoDoppioVoto, TaglioNonPermesso{
	}

	/**
	 * Il cittadino votante esprime un voto per la lista
	 * il voto di preferenza va automaticamente al capolista
	 * @throws TentatoDoppioVoto se il cittadino ha gi� votato
	 */	
	public void vota(Cittadino votante, String lista)
		throws TentatoDoppioVoto{
	}
	
	public long getNumeroVotanti(){
		return -1;
	}
	
	public Collection getRisultatiListe(){
		return null;
	}

	public Collection getRisultatiCandidati(){
		return null;
	}
	
	
}
