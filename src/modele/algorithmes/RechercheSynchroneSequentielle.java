package modele.algorithmes;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import modele.Bibliotheque;
import modele.Livre;
import modele.RechercheSynchroneAbstraite;

public class RechercheSynchroneSequentielle extends RechercheSynchroneAbstraite {
	
	public RechercheSynchroneSequentielle(String nomAlgo) {
		super(nomAlgo);
	}
	
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		Optional<HyperLien<Livre>> resultatLivre = null;
		
		for (int i = 0 ; i < bibliotheques.size() ; i++) {
			Optional<HyperLien<Livre>> livre = this.rechercheSync(bibliotheques.get(i), l, client);
			if(!livre.isEmpty()) {
				resultatLivre = livre;
			}
		}
		
		return resultatLivre;
	}

	
}
