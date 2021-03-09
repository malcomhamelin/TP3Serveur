package modele;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;

import configuration.JAXRS;
import infrastructure.jaxrs.HyperLien;
import infrastructure.langage.Types;

public class ImplemRechercheAsynchroneAbstraite extends RechercheAsynchroneAbstraite {

	public ImplemRechercheAsynchroneAbstraite(String nomAlgo) {
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
	protected Future<Optional<HyperLien<Livre>>> rechercheAsync(HyperLien<Bibliotheque> h, Livre l, Client client) {
		WebTarget webTarget = client.target(h.getUri());
		return webTarget.request().accept(JAXRS.TYPE_MEDIATYPE).async().put(Entity.entity(l, JAXRS.TYPE_MEDIATYPE),
				Types.typeRetourChercherAsync());
	}

	@Override
	protected Future<Optional<HyperLien<Livre>>> rechercheAsyncAvecRappel(HyperLien<Bibliotheque> h, Livre l,
			Client client, InvocationCallback<Optional<HyperLien<Livre>>> retour) {
		WebTarget webTarget = client.target(h.getUri());
		return webTarget.request().accept(JAXRS.TYPE_MEDIATYPE).async().put(Entity.entity(l, JAXRS.TYPE_MEDIATYPE),
				retour);
	}

}
