package modele;

import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;

public abstract class RechercheSynchroneAbstraite implements AlgorithmeRecherche {
	
	NomAlgorithme nomAlgo;
	
	public RechercheSynchroneAbstraite(String nomAlgo) {
		this.nomAlgo = new ImplemNomAlgorithme(nomAlgo);
	}
	
	protected Optional<HyperLien<Livre>> rechercheSync(HyperLien<Bibliotheque> h, Livre l, Client client) {
		Bibliotheque proxy = LienVersRessource.proxy(client, h, Bibliotheque.class);
		return proxy.chercher(l);
	}
	
	public NomAlgorithme nom() {
		return this.nomAlgo;
	}

}
