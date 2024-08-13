package project.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.workshop.entities.User;
import project.workshop.repositories.UserRepository;

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

    public User createdUser(User user) {
        return userRepository.save(user);
    }

    public void delete(Integer id){
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Usuário não existe, ID informado: " + id));
        userRepository.delete(user);
    }

    public User updateUser(Integer id, User user) {
        Optional<User> newUser = userRepository.findById(id);

        if (newUser.isPresent()) {
            User rawUser = newUser.get();

            rawUser.setName(user.getName());
            rawUser.setEmail(user.getEmail());
            rawUser.setPhone(user.getPhone());

            userRepository.save(rawUser);
            return rawUser;
        }
        throw new NoSuchElementException();
    }
}
