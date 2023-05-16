package challenge.one.api.controller;

import challenge.one.api.domain.repository.UserRepository;
import challenge.one.api.domain.user.CreateUserDto;
import challenge.one.api.domain.user.DetailsUserDto;
import challenge.one.api.domain.user.User;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailsUserDto> create (@RequestBody @Valid CreateUserDto data, UriComponentsBuilder uriBuilder) {
        User user = new User(data);
        userRepository.save(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsUserDto(user));
    }
}
