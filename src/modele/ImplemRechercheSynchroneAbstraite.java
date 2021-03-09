package modele;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import infrastructure.jaxrs.LienVersRessource;

public class ImplemRechercheSynchroneAbstraite extends RechercheSynchroneAbstraite {

	public ImplemRechercheSynchroneAbstraite(String nomAlgo) {
		super(nomAlgo);
	}

	NomAlgorithme nom;

	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NomAlgorithme nom() {
		return this.nom;
	}

	@Override
	protected Optional<HyperLien<Livre>> rechercheSync(HyperLien<Bibliotheque> h, Livre l, Client client) {
		return LienVersRessource.proxy(client, h, Bibliotheque.class).chercher(l);
	}

}
