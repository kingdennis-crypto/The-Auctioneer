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

		this.createInitialOffers();
		this.createInitialUsers();
		this.createInitialBids();
	}

	public void createInitialOffers() {
		List<Offer> offers = this.offersRepo.findAll();

		if (offers.size() > 0) return;

		System.out.println("Configuring some initial Offer data");;

		for (int i = 0; i < 9; i++) {
			offersRepo.save(Offer.createSampleOffer());
		}
	}

	public void createInitialBids() {
		Random random = new Random();
		List<Bid> bids = this.bidsRepo.findAll();
		List<Offer> offers = this.offersRepo.findAll();
		List<User> users = this.usersRepo.findAll();

		if (bids.size() > 0) return;

		System.out.println("Configuring some initial Bids data");;

//		Associate between zero and five bids on each offer,
//		except for the offers with status NEW.
//		The values of the bids should be increasing.
//		Associate each of the Bids at random with one of the Users.

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
		}

		for (int i = 0; i < 9; i++) {
			Bid bid = Bid.createSampleBid();
			offers.get(i).associateBid(bid);
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
}
