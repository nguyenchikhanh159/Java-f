package vn.food.jwt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import vn.food.entity.RoleEntity;
import vn.food.entity.SysUsersEntity;
import vn.food.service.SysUsersService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private SysUsersService userService;

    @Autowired
    public UserDetailsServiceImpl(SysUsersService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) {
        SysUsersEntity user = userService.findUserByEmail(email);

        RoleEntity userRoles = userService.findRolyById(user.getRoleId());
        List<GrantedAuthority> authorities = getUserAuthority(userRoles);
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(RoleEntity userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userRoles.getRoleName()));
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(SysUsersEntity user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                true, true, true, true, authorities);
    }
}
