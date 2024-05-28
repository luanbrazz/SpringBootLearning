package springbootlearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootlearning.model.User;
import springbootlearning.model.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}
