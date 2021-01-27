package vn.food.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.food.base.BaseResponse;
import vn.food.entity.RoleEntity;
import vn.food.entity.SysUsersEntity;
import vn.food.jwt.JwtToken;
import vn.food.model.RegisterUserSystemForm;
import vn.food.reponse.LoginResponse;
import vn.food.repository.RoleRepository;
import vn.food.repository.SysUsersRepository;

import java.util.Date;

@Service
public class SysUsersService {

    @Autowired
    SysUsersRepository sysUsersRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtToken tokenProvider;


    public ResponseEntity<?> getUser(int id) {
        SysUsersEntity s = new SysUsersEntity();

        SysUsersEntity sysUsersEntity = sysUsersRepository.findAllById(id);

        sysUsersRepository.save(sysUsersEntity);

        return ResponseEntity.ok(sysUsersEntity);
    }

    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(sysUsersRepository.findAll());
    }

    public SysUsersEntity findUserByEmail(String email) {
        return sysUsersRepository.findAllByEmail(email);
    }

    public RoleEntity findRolyById(Integer id) {
        return roleRepository.findAllById(id);
    }

    public ResponseEntity<BaseResponse> generateToken(String email) {
        BaseResponse response = new BaseResponse();
        try {
            String jwt = tokenProvider.generateToken(email);
            LoginResponse loginResponse = new LoginResponse(jwt, "Bearer");
            response.setResultCode(HttpStatus.OK.value());
            response.setResultMessage("success");
            response.setBody(loginResponse);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BaseResponse> createUser(RegisterUserSystemForm registerUserSystemForm) {
        BaseResponse response = new BaseResponse();
        try {
            SysUsersEntity sysUsersEntity = new SysUsersEntity();
            sysUsersEntity.setName(registerUserSystemForm.getName());
            sysUsersEntity.setEmail(registerUserSystemForm.getEmail());
            sysUsersEntity.setPassword(bCryptPasswordEncoder.encode(registerUserSystemForm.getPassword()));
            sysUsersEntity.setRoleId(registerUserSystemForm.getRoleId());
            sysUsersEntity.setCreatedAt(new Date());
            sysUsersEntity.setUpdatedAt(new Date());
            response.setResultCode(HttpStatus.OK.value());
            response.setResultMessage("success");
            sysUsersRepository.save(sysUsersEntity);
        } catch (Exception e) {
            response.setResultCode(HttpStatus.BAD_REQUEST.value());
            response.setResultMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }


}
