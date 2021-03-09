package modele.algorithmes;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.Outils;
import modele.Bibliotheque;
import modele.Livre;
import modele.RechercheAsynchroneAbstraite;

public class RechercheAsynchroneStreamParallele extends RechercheAsynchroneAbstraite {

	public RechercheAsynchroneStreamParallele(String nomAlgo) {
		super(nomAlgo);
	}
	
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		Optional<HyperLien<Livre>> livreResultat = bibliotheques.parallelStream().map((lienBiblio) -> rechercheAsync(lienBiblio, l, client))
				.map(Outils::remplirPromesse).filter((x) -> x.isEmpty()).findAny().orElse(Optional.empty());
		
		return livreResultat;
	}
	
}
