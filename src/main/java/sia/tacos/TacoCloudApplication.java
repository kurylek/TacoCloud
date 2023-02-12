package sia.tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sia.tacos.enums.TypeOfIngredient;
import sia.tacos.model.Ingredient;
import sia.tacos.repository.IngredientRepository;

@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repo.save(new Ingredient("FLTO", "pszenna", TypeOfIngredient.WRAP));
				repo.save(new Ingredient("COTO", "kukurydziana", TypeOfIngredient.WRAP));
				repo.save(new Ingredient("GRBF", "mielona wołowina", TypeOfIngredient.PROTEIN));
				repo.save(new Ingredient("CARN", "kawałki mięsa", TypeOfIngredient.PROTEIN));
				repo.save(new Ingredient("TMTO", "pomidory pokrojone w kostkę", TypeOfIngredient.VEGGIES));
				repo.save(new Ingredient("LETC", "sałata", TypeOfIngredient.VEGGIES));
				repo.save(new Ingredient("CHED", "cheddar", TypeOfIngredient.CHEESE));
				repo.save(new Ingredient("JACK", "Monterrey Jack", TypeOfIngredient.CHEESE));
				repo.save(new Ingredient("SLSA", "salsa", TypeOfIngredient.SAUCE));
				repo.save(new Ingredient("SRCR", "śmietana", TypeOfIngredient.SAUCE));
			}
		};
	}
}
