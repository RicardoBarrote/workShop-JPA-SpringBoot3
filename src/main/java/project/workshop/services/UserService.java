package project.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.workshop.entities.User;
import project.workshop.repositories.UserRepository;
import project.workshop.requestPayLoad.UserRequestPayLoad;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        Optional<User> userId = userRepository.findById(id);
        return userId.get();
    }

    public User createdUser(UserRequestPayLoad payLoad) {
        User user = new User(payLoad);
        userRepository.save(user);
        return user;
    }

    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Usuário não existe, ID informado: " + id));
        userRepository.delete(user);
    }

    public User updateUser(Integer id, UserRequestPayLoad payLoad) {
        Optional<User> newUser = userRepository.findById(id);

        if (newUser.isPresent()) {
            User rawUser = newUser.get();

            rawUser.setName(payLoad.name());
            rawUser.setEmail(payLoad.email());
            rawUser.setPhone(payLoad.phone());

            userRepository.save(rawUser);
            return rawUser;
        }
        throw new NoSuchElementException();
    }
}
