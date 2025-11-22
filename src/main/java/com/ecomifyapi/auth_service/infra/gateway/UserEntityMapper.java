package com.ecomifyapi.auth_service.infra.gateway;

import com.ecomifyapi.auth_service.domain.entities.User;
import com.ecomifyapi.auth_service.infra.persistence.UserEntity;

public class UserEntityMapper {
    UserEntity toEntity(User userDomainObj) {
        return new UserEntity(userDomainObj.getName(), userDomainObj.getEmail(), userDomainObj.getPassword(), userDomainObj.getRole(), userDomainObj.getCreatedAt());
    }

    User toDomainObj (UserEntity userEntity) {
        return new User(userEntity.getName(), userEntity.getEmail(), userEntity.getPassword());
    }
}
