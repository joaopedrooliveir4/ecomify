package com.ecomifyapi.auth_service.infra.gateway;

import com.ecomifyapi.auth_service.application.gateway.UserGateway;
import com.ecomifyapi.auth_service.domain.entities.User;
import com.ecomifyapi.auth_service.infra.persistence.UserEntity;
import com.ecomifyapi.auth_service.infra.persistence.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserRepositoryGateway(UserRepository userRepository, UserEntityMapper userEntityMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User userDomainObj) {
        String plainPassword = userDomainObj.getPassword();
        String hashedPassword = passwordEncoder.encode(plainPassword);

        userDomainObj.setPassword(hashedPassword);

        UserEntity userEntity = userEntityMapper.toEntity(userDomainObj);
        UserEntity saveObj = userRepository.save(userEntity);

        return userEntityMapper.toDomainObj(saveObj);
    }

    @Override
    public Optional<User> findByEmail(User user) {
        return Optional.empty();
    }
}
