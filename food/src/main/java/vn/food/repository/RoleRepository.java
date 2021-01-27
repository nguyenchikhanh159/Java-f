package vn.food.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.food.entity.RoleEntity;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    RoleEntity findAllById(@Param("id") int id);
}
