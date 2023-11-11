package crud.spring.boot.crudspringboot.domain.user.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import crud.spring.boot.crudspringboot.domain.exceptions.EmailException;
import crud.spring.boot.crudspringboot.domain.exceptions.IdNotFoundException;
import crud.spring.boot.crudspringboot.infra.user.model.UserModel;
import crud.spring.boot.crudspringboot.infra.user.repository.IUserRepository;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    // criar usuário
    public UserModel createUser(UserModel userModel) {
        // Verifica se o e-mail do usuário já existe no repositório.
        var isEmailAlreadyExists = userRepository.findByEmail(userModel.getEmail());

        // Se o e-mail já existe, lança uma exceção EmailException.
        if (isEmailAlreadyExists != null) {
            throw new EmailException();
        }

        // Utiliza a biblioteca BCrypt para criar um hash da senha do usuário.
        var passwordHashed = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

        // Define a senha do modelo do usuário como o hash da senha.
        userModel.setPassword(passwordHashed);

        // Salva o novo usuário no repositório.
        return userRepository.save(userModel);
    }

    // buscar usuário pelo id
    public UserModel findById(UUID id) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
        // Retorna o UserModel ou null se não for encontrado
        return optionalUser.orElse(null); 
    }

    // atualizar usuário
    public UserModel updateUser(UUID id, UserModel updatedUserModel) {
        Optional<UserModel> optionalUser = userRepository.findById(id);
    
        if (optionalUser.isPresent()) {
            UserModel existingUser = optionalUser.get();
    
            // Verifica se os campos devem ser atualizados e se os novos valores não são nulos ou vazios.
            if (updatedUserModel.getName() != null && !updatedUserModel.getName().isEmpty()) {
                existingUser.setName(updatedUserModel.getName());
            }
    
            if (updatedUserModel.getEmail() != null && !updatedUserModel.getEmail().isEmpty()) {
                existingUser.setEmail(updatedUserModel.getEmail());
            }
    
            if (updatedUserModel.getPassword() != null && !updatedUserModel.getPassword().isEmpty()) {
                String newPasswordHashed = BCrypt.withDefaults().hashToString(12, updatedUserModel.getPassword().toCharArray());
                existingUser.setPassword(newPasswordHashed);
            }
    
            if (updatedUserModel.getPhone() != null && !updatedUserModel.getPhone().isEmpty()) {
                existingUser.setPhone(updatedUserModel.getPhone());
            }
    
            // Salva as alterações no repositório.
            return userRepository.save(existingUser);
        } else {
            throw new IdNotFoundException(id);
        }
    }

    public void deleteUser(UUID id) {
    // Verifica se o usuário com o ID fornecido existe no repositório.
    Optional<UserModel> optionalUser = userRepository.findById(id);

    if (optionalUser.isPresent()) {
        // Se o usuário existe, exclui do repositório.
        userRepository.delete(optionalUser.get());
    } else {
        throw new IdNotFoundException(id);
    }
}
    
}
