package pl.rest.webservices.restfulwebservices.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import pl.rest.webservices.restfulwebservices.exception.UserPostNotFoundException;
import pl.rest.webservices.restfulwebservices.model.Post;
import pl.rest.webservices.restfulwebservices.model.User;
import pl.rest.webservices.restfulwebservices.repository.UserRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    private final UserRepository repository;
    private final PostRepository postRepository;

    public PostController(UserRepository repository, PostRepository postRepository) {
        this.repository = repository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPostsForUser(@PathVariable Integer id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostsForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }

        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedPost.getId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{userId}/posts/{postId}")
    public Post retrievePostForUser(@PathVariable Integer userId, @PathVariable Integer postId) {
        Optional<User> user = repository.findById(userId);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id: " + userId);
        }
        Optional<Post> post = user.get().getPosts()
                .stream()
                .filter(eachPost -> eachPost.getId().equals(postId))
                .findFirst();

        if (post.isEmpty()) {
            throw new UserPostNotFoundException("id: " + postId);
        }

        return post.get();
    }
}
