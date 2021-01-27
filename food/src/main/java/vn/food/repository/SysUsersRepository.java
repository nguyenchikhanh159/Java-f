package vn.food.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.food.entity.SysUsersEntity;


@Repository
public interface SysUsersRepository extends JpaRepository<SysUsersEntity, Integer> {

    SysUsersEntity findAllById(@Param("id") int id);

    SysUsersEntity findAllByEmail(@Param("email") String email);

}
