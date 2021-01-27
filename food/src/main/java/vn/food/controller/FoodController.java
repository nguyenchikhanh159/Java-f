package vn.food.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.food.entity.FoodEntity;
import vn.food.model.OrderForm;
import vn.food.service.FoodService;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "localhost:3000")
@RequestMapping("/api")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping(value = "/food/create", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> created(@RequestBody FoodEntity foodEntity) {
        foodService.createFood(foodEntity);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(value = "/food/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody FoodEntity foodEntity) {
        return foodService.updateFood(foodEntity);
    }

    @PostMapping(value = "/food/order", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> order(@RequestBody OrderForm orderForm) {
        return foodService.order(orderForm);
    }

    @GetMapping(value = "/food/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<FoodEntity> getAll() {
        return foodService.getAllFood();
    }

    @GetMapping(value = "/food/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Optional<FoodEntity> getById(@PathVariable("id") Integer id) {
        return foodService.getById(id);
    }

    @PostMapping(value = "/food/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteById(@PathVariable("id") Integer id) {
        foodService.deleteById(id);
    }

}

