package vn.food.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.food.entity.FoodEntity;
import vn.food.model.OrderForm;
import vn.food.repository.FoodRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public void createFood(FoodEntity foodEntity) {
        try {
            foodRepository.save(foodEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ResponseEntity<?> updateFood(FoodEntity foodEntity) {
        try {
            Boolean checkExist = foodRepository.existsById(foodEntity.getId()); // kiem tra ton tai trong DB  bang ID
            if (checkExist) {
                foodRepository.save(foodEntity);
                return ResponseEntity.ok(HttpStatus.OK);
            } else {
                this.createFood(foodEntity);
                return ResponseEntity.ok(HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public ResponseEntity<?> order(OrderForm orderForm) {
        try {
            Boolean checkExist = foodRepository.existsById(orderForm.getId()); // kiem tra ton tai trong DB  bang ID
            if (checkExist) {
                Optional<FoodEntity> foodEntity = foodRepository.findById(orderForm.getId());
                Integer soluong = foodEntity.get().getSoluong() - orderForm.getQuantity();
                foodEntity.get().setSoluong(soluong);
                foodEntity.get().setUpdateAt(orderForm.getUpdateAt());
                foodRepository.save(foodEntity.get());
                return ResponseEntity.ok(HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

    public List<FoodEntity> getAllFood() {
        return foodRepository.findAll();
    }

    public Optional<FoodEntity> getById(Integer id) {
        return foodRepository.findById(id);
    }

    public void deleteById(Integer id) {
        foodRepository.deleteById(id);
    }

}
// findById - select table bang id -> lay len 1 record
// findBy... - selet * table bang cai gi do (name , price...) -> list (ArrayList) danh sach  san pham boi cai BY do
//findAll - > select *