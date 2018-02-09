package br.com.igbeni.redventuresbackend.repository;

import br.com.igbeni.redventuresbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
