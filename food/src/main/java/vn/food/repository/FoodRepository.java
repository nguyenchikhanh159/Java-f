package vn.food.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.food.entity.FoodEntity;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer> {

}
