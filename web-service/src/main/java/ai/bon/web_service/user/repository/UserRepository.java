package ai.bon.web_service.user.repository;

import ai.bon.web_service.user.model.BonUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<BonUser, Integer> {}
