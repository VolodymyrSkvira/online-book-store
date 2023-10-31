package s.volodymyr.onlinebookstore.repository.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import s.volodymyr.onlinebookstore.model.Role;
import s.volodymyr.onlinebookstore.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
}
