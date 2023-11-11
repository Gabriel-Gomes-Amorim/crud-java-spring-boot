package crud.spring.boot.crudspringboot.infra.user.http;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crud.spring.boot.crudspringboot.domain.user.service.UserService;
import crud.spring.boot.crudspringboot.infra.user.model.UserModel;
import io.swagger.v3.oas.annotations.Operation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService; 

    @Operation(description = "criar usuário")
    @PostMapping()
    public ResponseEntity<UserModel> create(@RequestBody UserModel userModel) {
        // Use o serviço para criar um usuário
        UserModel userCreated = userService.createUser(userModel);

        // Retorna uma resposta HTTP com status 201 (Created) e o usuário criado no corpo da resposta.
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @Operation(description = "buscar usuário por id")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        try {
            UserModel user = userService.findById(id);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar usuário: " + e.getMessage());
        }
    }

    @Operation(description = "atualizar usuário")
    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateUsuario(
            @PathVariable UUID id,
            @RequestBody UserModel data
    ) {
        try {
            UserModel updatedUser = userService.updateUser(id, data);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar o usuário: " + e.getMessage());
        }
    }

    @Operation(description = "deletar usuário")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUsuario(
            @PathVariable UUID id
    ) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao excluir o usuário: " + e.getMessage());
        }
    }

}
