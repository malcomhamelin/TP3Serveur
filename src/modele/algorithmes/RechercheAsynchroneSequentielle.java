package modele.algorithmes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import modele.Bibliotheque;
import modele.Livre;
import modele.RechercheAsynchroneAbstraite;

public class RechercheAsynchroneSequentielle extends RechercheAsynchroneAbstraite {

	public RechercheAsynchroneSequentielle(String nomAlgo) {
		super(nomAlgo);
	}
	
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		List<Future<Optional<HyperLien<Livre>>>> resultats = new ArrayList<>();
		Optional<HyperLien<Livre>> livreResultat = Optional.empty();
		
		for (int i = 0 ; i < bibliotheques.size() ; i++) {
			resultats.add(rechercheAsync(bibliotheques.get(i), l, client));
		}
		
		try {
			
			for (int j = 0 ; j < resultats.size() ; j++) {
				livreResultat = resultats.get(j).get();
				
				if (livreResultat.isPresent()) {
					return livreResultat;
				}
			}
			
		} 
		catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		return livreResultat;
	}


}
