package elezioni;

public class Elettore implements Cittadino {
	
	private String nome, cognome;
	private boolean haVotato = false;
	private boolean isCapolista = false;
	private boolean isCandidato = false;
	
	public Elettore(String nome, String cognome){
		this.nome = nome;
		this.cognome = cognome;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getCognome() {
		return cognome;
	}

	@Override
	public boolean haVotato() {
		return haVotato;
	}

	@Override
	public boolean isCapolista() {
		return isCapolista;
	}

	@Override
	public boolean isCandidato() {
		return isCandidato;
	}

	@Override
	public long getNumeroVoti() {
		return 0;
	}

}
