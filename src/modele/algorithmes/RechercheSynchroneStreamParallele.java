package modele.algorithmes;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import modele.Bibliotheque;
import modele.Livre;
import modele.RechercheSynchroneAbstraite;

public class RechercheSynchroneStreamParallele extends RechercheSynchroneAbstraite {

	public RechercheSynchroneStreamParallele(String nomAlgo) {
		super(nomAlgo);
	}
	
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		Optional<HyperLien<Livre>> livreResultat = bibliotheques.parallelStream()
				.map(lienBiblio -> this.rechercheSync(lienBiblio, l, client)).filter(x -> x.isPresent()).findAny()
				.orElse(Optional.empty());
		
		return livreResultat;
	}

}
