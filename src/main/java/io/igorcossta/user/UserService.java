package io.igorcossta.user;

import io.igorcossta.mapper.UserMapper;
import io.igorcossta.util.PageableResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public PageableResponse findAllUsers(String username, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> pageUser;
        if (username == null) {
            pageUser = userRepository.findAll(pageable);
        } else {
            pageUser = userRepository.findAllByUsernameContaining(username, pageable);
        }

        List<UserDto> content = pageUser.getContent()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return new PageableResponse()
                .put("user", content)
                .build(pageUser);
    }

    public boolean exists(String username) {
        return userRepository.findUserByUsername(username).isPresent();
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserAccountNotFoundException("user account %s not found".formatted(username)));
    }

    @Transactional
    public User activateUserAccount(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserAccountNotFoundException("user id %s not found".formatted(id.toString())));
        user.setIsEnabled(true);
        return save(user);
    }

    @Transactional
    public User save(final User user) {
        return userRepository.save(user);
    }

}
