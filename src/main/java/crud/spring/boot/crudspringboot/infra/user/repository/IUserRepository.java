package crud.spring.boot.crudspringboot.infra.user.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import crud.spring.boot.crudspringboot.infra.user.model.UserModel;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByEmail(String username);
    Optional<UserModel> findById(UUID id);
}
