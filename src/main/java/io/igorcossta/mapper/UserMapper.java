package io.igorcossta.mapper;

import io.igorcossta.registration.RegistrationRequest;
import io.igorcossta.user.User;
import io.igorcossta.user.UserDto;
import io.igorcossta.util.PrettyTimeFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Autowired
    protected BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    protected PrettyTimeFormatter prettyTimeFormatter;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "role", expression = "java(io.igorcossta.user.Role.USER)")
    @Mapping(target = "isEnabled", constant = "false")
    @Mapping(target = "isAccountNonLocked", constant = "true")
    @Mapping(target = "isAccountNonExpired", constant = "true")
    @Mapping(target = "isCredentialsNonExpired", constant = "true")
    @Mapping(target = "password", expression = "java(bCryptPasswordEncoder.encode(request.password()))")
    public abstract User fromNewRegistration(RegistrationRequest request);

    @Mapping(target = "createdAt", expression = "java(prettyTimeFormatter.formatToMonthDayYear(user.getCreatedAt()))")
    public abstract UserDto toDto(User user);
}
