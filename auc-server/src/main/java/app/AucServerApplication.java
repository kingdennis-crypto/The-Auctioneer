package app;

import app.models.Bid;
import app.models.Offer;
import app.models.User;
import app.models.enums.Status;
import app.repositories.BidsRepositoryJpa;
import app.repositories.OffersRepositoryJpa;
import app.repositories.UserRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class AucServerApplication implements CommandLineRunner {

	@Autowired
	private OffersRepositoryJpa offersRepo;

	@Autowired
	private BidsRepositoryJpa bidsRepo;

	@Autowired
	private UserRepositoryJpa usersRepo;

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(AucServerApplication.class, args);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running CommandLine Startup");

		this.initializeData();
//		this.createInitialOffers();
//		this.createInitialUsers();
//		this.createInitialBids();
	}

	public void createInitialOffers() {
		List<Offer> offers = this.offersRepo.findAll();

		if (offers.size() > 0) return;

		System.out.println("Configuring some initial Offer data");;

		for (int i = 0; i < 9; i++) {
			offersRepo.save(Offer.createSampleOffer());
		}
	}

	public void createInitialUsers() {
		List<User> users = this.usersRepo.findAll();
		List<Bid> bids = this.bidsRepo.findAll();

		if (users.size() > 0) return;

		System.out.println("Configuring some initial User data");

		User dennis = User.createSampleUser("dennis", "Pesant");
		User simon = User.createSampleUser("simon", "Pesant");
		User yahia = User.createSampleUser("yahia", "Admin");

		this.usersRepo.save(dennis);
		this.usersRepo.save(simon);
		this.usersRepo.save(yahia);
	}

	public void createInitialBids() {
		Random random = new Random();
		List<Bid> bids = this.bidsRepo.findAll();
		List<Offer> offers = this.offersRepo.findAll();
		List<User> users = this.usersRepo.findAll();

		if (bids.size() > 0) return;

		System.out.println("Configuring some initial Bids data");;

		System.out.println(offers.size());

		for (Offer offer : offers) {
			System.out.println(offer);
			int randomNum = random.nextInt(5) + 1;

			if (offer.getStatus() == Status.NEW) {
				return;
			}

			double highestBid = 0;

			for (int i = 0; i < randomNum; i++) {
				highestBid += random.nextDouble(10);

				Bid bid = new Bid(highestBid);
				User user = users.get(random.nextInt(users.size()));

				bid.associateUser(user);
				bid.associateOffer(offer);

				bidsRepo.save(bid);
			}

			offer.setValueHighestBid((int) highestBid);
		}
	}

	public void initializeData() {
		Random random = new Random();
		List<Offer> offers = new ArrayList<>();

		System.out.println("Configuring some initial data");

		User dennis = usersRepo.save(User.createSampleUser("dennis", "Pesant"));
		User simon = usersRepo.save(User.createSampleUser("simon", "Pesant"));
		User yahia = usersRepo.save(User.createSampleUser("yahia", "Admin"));

		List<User> users = new ArrayList<>(List.of(dennis, simon, yahia));

		for (int i = 0; i < 9; i++) {
			Offer offer = offersRepo.save(Offer.createSampleOffer());

			if (offer.getStatus() == Status.NEW) {
				continue;
			}

			int randomNum = random.nextInt(5) + 1;

			double highestBid = 0;

			for (int j = 0; j < randomNum; j++) {
				highestBid += random.nextDouble(10);

				Bid bid = new Bid(highestBid);
				User user = users.get(random.nextInt(users.size()));

				bid.associateUser(user);
				bid.associateOffer(offer);

				bidsRepo.save(bid);
			}
		}
	}
}
