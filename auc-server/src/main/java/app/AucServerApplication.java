package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AucServerApplication {

//	@Autowired
//	private OffersRepositoryJpa offersRepo;
//
//	@Autowired
//	private BidsRepositoryJpa bidsRepo;

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(AucServerApplication.class, args);
//		OffersRepositoryMockImpl offersRepositoryMock = applicationContext.getBean(OffersRepositoryMockImpl.class);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("Running CommandLine Startup");
//
//		this.createInitialOffers();
//		this.createInitialBids();
//	}
//
//	public void createInitialOffers() {
//		List<Offer> offers = this.offersRepo.findAll();
//
//		if (offers.size() > 0) return;
//
//		System.out.println("Configuring some initial Offer data");;
//
//		for (int i = 0; i < 9; i++) {
//			Offer offer = offersRepo.save(Offer.createSampleOffer(0));
//		}
//	}
//
//	public void createInitialBids() {
//		List<Bid> bids = this.bidsRepo.findAll();
//		List<Offer> offers = this.offersRepo.findAll();
//
//		if (bids.size() > 0) return;
//
//		System.out.println("Configuring some initial Bid data");
//
//		for (int i = 0; i < 9; i++) {
//			Bid bid = Bid.createSampleBid();
//			Offer offer = offers.get(i);
//
//			bid.setOffer(offer);
//			bidsRepo.save(bid);
//		}
//	}
}
