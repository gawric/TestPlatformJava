package com.server.testplatform.testplatform.service.service;


import com.server.testplatform.testplatform.model.CustomUser;
import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.service.db.inter.IServiceUserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
public class CustomDetailsService  implements UserDetailsService {

    @Autowired
    private IServiceUserDb serviceUserDb;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CustomUser loadUserByUsername(final String username) throws UsernameNotFoundException {

        try {
            CustomUser cUser = getCustom(serviceUserDb.findByUsername(username));

            return getCustomUser(cUser ,  username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
    }

    private CustomUser getCustomUser(CustomUser cUser , String username)
    {
        if(cUser == null) throw new UsernameNotFoundException("User " + username + " was not found in the database");

        return cUser;
    }

    private CustomUser getCustom(UserModel userEntity)
    {
        if(userEntity != null) return new CustomUser(userEntity);
        return null;
    }



}