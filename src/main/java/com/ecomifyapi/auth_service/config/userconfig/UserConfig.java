package com.ecomifyapi.auth_service.config.userconfig;

import com.ecomifyapi.auth_service.application.gateway.UserGateway;
import com.ecomifyapi.auth_service.application.usecase.CretedUser;
import com.ecomifyapi.auth_service.application.usecase.DeleteUser;
import com.ecomifyapi.auth_service.application.usecase.UpdatePassword;
import com.ecomifyapi.auth_service.application.usecase.UpdateUserRole;
import com.ecomifyapi.auth_service.controller.mapper.UserDtoMapper;
import com.ecomifyapi.auth_service.infra.gateway.UserEntityMapper;
import com.ecomifyapi.auth_service.infra.gateway.UserRepositoryGateway;
import com.ecomifyapi.auth_service.infra.persistence.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserConfig {
    @Bean
    CretedUser created (UserGateway userGateway) {
        return new CretedUser(userGateway);
    }

    @Bean
    UserGateway userGateway (UserRepository userRepository, UserEntityMapper userEntityMapper, BCryptPasswordEncoder passwordEncoder) {
        return new UserRepositoryGateway(userRepository, userEntityMapper, passwordEncoder);
    }

    @Bean
    UserEntityMapper userEntityMapper() {
        return new UserEntityMapper();
    }

    @Bean
    UserDtoMapper userDtoMapper() {
        return new UserDtoMapper();
    }

    @Bean
    UpdateUserRole updateUserRole(UserGateway userGateway) {
        return new UpdateUserRole(userGateway);
    }

    @Bean
    UpdatePassword updatePassword(UserGateway userGateway) {
        return new UpdatePassword(userGateway);
    }

    @Bean
    DeleteUser deleteUser(UserGateway userGateway) {
        return new DeleteUser(userGateway);
    }
}
