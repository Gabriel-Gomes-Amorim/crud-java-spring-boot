package crud.spring.boot.crudspringboot.infra.user.model;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * A anotação @Data do Lombok gera automaticamente os métodos comuns (getters, setters, equals, hashCode e toString)
 * para todos os campos da classe, reduzindo a verbosidade do código.
 */
@Data
@Entity(name = "tb_users")
public class UserModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
