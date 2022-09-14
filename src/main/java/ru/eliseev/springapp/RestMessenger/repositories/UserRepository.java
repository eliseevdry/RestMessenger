package ru.eliseev.springapp.RestMessenger.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eliseev.springapp.RestMessenger.domain.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
