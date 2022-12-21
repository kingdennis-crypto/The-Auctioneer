package app;

import app.models.Bid;
import app.models.Offer;
import app.repositories.BidsRepositoryJpa;
import app.repositories.OffersRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
public class AucServerApplication implements CommandLineRunner {

	@Autowired
	private OffersRepositoryJpa offersRepo;

	@Autowired
	private BidsRepositoryJpa bidsRepo;

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(AucServerApplication.class, args);
//		OffersRepositoryMockImpl offersRepositoryMock = applicationContext.getBean(OffersRepositoryMockImpl.class);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running CommandLine Startup");

		this.createInitialOffers();
		this.createInitialBids();
	}

	public void createInitialOffers() {
		List<Offer> offers = this.offersRepo.findAll();

		if (offers.size() > 0) return;

		System.out.println("Configuring some initial Offer data");;

		for (int i = 0; i < 9; i++) {
			Offer offer = offersRepo.save(Offer.createSampleOffer());
		}
	}

	public void createInitialBids() {
		List<Bid> bids = this.bidsRepo.findAll();

		if (bids.size() > 0) return;

		System.out.println("Configuring some initial Bids data");;

		for (int i = 0; i < 9; i++) {
			Bid bid = bidsRepo.save(Bid.createSampleBid());
		}
	}
}
