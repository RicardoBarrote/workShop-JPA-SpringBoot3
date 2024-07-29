package project.workshop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import project.workshop.entities.User;
import project.workshop.repositories.UserRepository;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Ricardo Barrote", "ricardo@gmail.com", "159753", "81985748758");
        User u2 = new User(null, "Ademilton", "ademilton@gmail.com", "5858585", "81985421578");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
