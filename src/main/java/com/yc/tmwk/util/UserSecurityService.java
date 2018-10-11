package com.yc.tmwk.util;

import com.yc.tmwk.model.Role;
import com.yc.tmwk.model.Users;
import com.yc.tmwk.service.UserRoleService;
import com.yc.tmwk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSecurityService implements UserDetailsService {

    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;


    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        Users user = userService.queryByName(loginName);
        if(user == null){
            throw new UsernameNotFoundException("User is not exist.");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = userRoleService.findByUserId(user.getUserId());

        for(int i = 0 ; roles != null & i < roles.size(); i++){
            authorities.add(new SimpleGrantedAuthority(String.valueOf(roles.get(i).getRoleId())));
        }

        return new User(user.getLoginName(), user.getLoginPwd(), authorities);
    }


    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //"0"
        String encode = bCryptPasswordEncoder.encode("0");
        System.out.println(encode);
        //：$2a$10$b/z5MpJF/izZSpWbXmnF7O0n/DDzrLOpCWya5F9HGi.Y3SG9sJkOu
    }
}
