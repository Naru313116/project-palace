package pl.rest.webservices.restfulwebservices.controller;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import pl.rest.webservices.restfulwebservices.model.Post;
import pl.rest.webservices.restfulwebservices.model.User;
import pl.rest.webservices.restfulwebservices.repository.UserRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaController {

    private final UserRepository repository;

    public UserJpaController(UserRepository repository) {
        this.repository = repository;

    }

    @GetMapping("/jpa/users")
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> getUser(@PathVariable Integer id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link =
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());

        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping("jpa/users")
    ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
        User userToBeSaved = repository.save(user);
        // location header
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(userToBeSaved.getId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable Integer id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        return user.get().getPosts();
    }
}
