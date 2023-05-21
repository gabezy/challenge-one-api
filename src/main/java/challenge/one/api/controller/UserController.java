package challenge.one.api.controller;

import challenge.one.api.domain.repository.UserRepository;
import challenge.one.api.domain.service.UserService;
import challenge.one.api.domain.user.CreateUserDto;
import challenge.one.api.domain.user.DetailsUserDto;
import challenge.one.api.domain.user.UpdateUserDto;
import challenge.one.api.domain.user.User;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailsUserDto> create(@RequestBody @Valid CreateUserDto data, UriComponentsBuilder uriBuilder) {
        if (userService.usernameAlreadyExists(data.username())) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User(data);
        userRepository.save(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsUserDto(user));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetailsUserDto> update(@PathVariable String id, @RequestBody @Valid UpdateUserDto data) {
        if (userService.usernameAlreadyExists(data.username())) {
            throw new RuntimeException("Username already exists");
        }
        var user = userRepository.getReferenceById(id);
        user.update(data);
        return ResponseEntity.ok().body(new DetailsUserDto(user));
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable String id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
