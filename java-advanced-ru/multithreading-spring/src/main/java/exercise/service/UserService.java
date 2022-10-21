package exercise.service;

import exercise.model.User;
import exercise.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    // BEGIN
    public Mono<User> create(User user) {
        return userRepository.save(user);
    }

    public Mono<User> update(long userId, User newUserData) {

        return userRepository.save(newUserData);
    }

    public Mono<User> findById(long userId) {
        return userRepository.findById((int) userId);
    }

    public Mono<Void> deleteById(long userId) {
//        return userRepository.deleteById(userId);
        Mono<User> user = userRepository.findById((int) userId);
        return userRepository.deleteById((int) userId);
//        return user;
    }

    // END
}
