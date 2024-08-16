package project.workshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.workshop.entities.User;
import project.workshop.repositories.UserRepository;
import project.workshop.requestPayLoad.UserRequestPayLoad;
import project.workshop.services.exceptions.ResourcerNotFound;

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
        return userRepository.findById(id).orElseThrow(() -> new ResourcerNotFound(id));
    }

    public User createdUser(UserRequestPayLoad payLoad) {
        User user = new User(payLoad);
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Usuário não existe, ID informado: " + id));
        userRepository.delete(user);
    }

    public User updateUser(Integer id, UserRequestPayLoad payLoad) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(payLoad.name());
                    user.setEmail(payLoad.email());
                    user.setPhone(payLoad.phone());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResourcerNotFound(id));
    }
}
