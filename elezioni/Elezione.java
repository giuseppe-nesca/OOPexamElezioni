package elezioni;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;



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
		if(votante.haVotato()){
			throw new TentatoDoppioVoto(""+votante.getCognome() + " " + votante.getCognome() + "ha tentato di votare piu' di una volta");
		}
		Lista list = liste.get(lista);
		Collection<Cittadino> candidati = list.getCandidati();
		for (Cittadino cittadino : candidati) {
			if( cittadino.getCognome() == cognome && cittadino.getNome() == nome ){
				Candidato candidato = (Candidato) cittadino;
				candidato.addVoto();
				list.addVoto();
				((Elettore) votante).setHaVotato(true);
				return;
			}
		}
		throw new TaglioNonPermesso("il candidato selezionato dall'elettore non e' esistente nella lista selezionata pertanto la votazione e' invalida");
	}

	/**
	 * Il cittadino votante esprime un voto per la lista
	 * il voto di preferenza va automaticamente al capolista
	 * @throws TentatoDoppioVoto se il cittadino ha gi� votato
	 */	
	public void vota(Cittadino votante, String lista)
		throws TentatoDoppioVoto{
		if(votante.haVotato()){
			throw new TentatoDoppioVoto(""+votante.getCognome() + " " + votante.getCognome() + "ha tentato di votare piu' di una volta");
		}
		Lista list = liste.get(lista);
		list.addVoto();
		((Elettore) votante).setHaVotato(true);
	}
	
	public long getNumeroVotanti(){
		return elettori.stream()
			.filter( e -> e.haVotato() )
			.count();
	}
	
	public Collection getRisultatiListe(){
		return liste.values().stream()
					.sorted(new Comparator<Lista>() {
						@Override
						public int compare(Lista l1, Lista l2){
							return (int) (l2.getNumeroVoti() - l1.getNumeroVoti());
						}
					})
					.collect(Collectors.toList())
					;
	}

	public Collection getRisultatiCandidati(){
		return liste.values().stream()
					.flatMap( l -> l.getCandidati().stream() )
					.sorted(new Comparator<Cittadino>() {
						@Override
						public int compare(Cittadino c1, Cittadino c2){
							long conf = ((Candidato)c2).getNumeroVoti() - ((Candidato)c1).getNumeroVoti();
							if (conf>0) return 1;
							if (conf<0) return -1;
							return 0;
						}
					})
					.collect(Collectors.toList())
				;
	}
	
	
}
