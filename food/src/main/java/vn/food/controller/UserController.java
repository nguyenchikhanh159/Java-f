package vn.food.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.food.model.RegisterUserSystemForm;
import vn.food.service.SysUsersService;


@RestController
@CrossOrigin(origins = "localhost:3000")
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private SysUsersService sysUsersService;

    //    @Bean
//    public WebMvcConfigurer configure(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMapping(CorsRegistry registry){
//                registry.addMapping("/*").allowedOrigins("http://localhost:3000");
//            }
//        };
//    }
    @PostMapping(value = "/user/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody RegisterUserSystemForm registerUserSystemForm) {
        return sysUsersService.createUser(registerUserSystemForm);
    }

}
