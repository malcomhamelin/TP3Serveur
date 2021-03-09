package modele.algorithmes;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

import javax.ws.rs.client.Client;

import infrastructure.jaxrs.HyperLien;
import modele.Bibliotheque;
import modele.Livre;
import modele.RechercheSynchroneAbstraite;

public class RechercheSynchroneMultiTaches extends RechercheSynchroneAbstraite {

	ExecutorService executorService;
	
	public RechercheSynchroneMultiTaches(String nomAlgo) {
		super(nomAlgo);
		this.executorService = Executors.newCachedThreadPool();
	}
	
	@Override
	public Optional<HyperLien<Livre>> chercher(Livre l, List<HyperLien<Bibliotheque>> bibliotheques, Client client) {
		
		CountDownLatch countDown = new CountDownLatch(1);
		AtomicReference<Optional<HyperLien<Livre>>> livreResultat = new AtomicReference<>(Optional.empty());
		
		for (HyperLien<Bibliotheque> lienBiblio: bibliotheques) {
			
			executorService.submit(() -> {
				
				Optional<HyperLien<Livre>> tempo = this.rechercheSync(lienBiblio, l, client);
				
				if (tempo.isEmpty()) {
					countDown.countDown();
				}
				else {
					livreResultat.set(tempo);
				}
			});
			
		}
		try {
			countDown.await();
		}
		catch (InterruptedException e) {
			
		}
		
		return livreResultat.get();
		
	}

}
