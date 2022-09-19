package com.server.testplatform.testplatform.service.db.impl;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.upload.UploadImageModel;
import com.server.testplatform.testplatform.repository.UserRepository;
import com.server.testplatform.testplatform.service.db.inter.IServiceUserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
@Service("IServiceUserDb")
public class ServiceUserDbImpl implements IServiceUserDb {

    @Autowired
    private UserRepository userModelRepository;

    //@Autowired
    //private TransactionSychronizationManager tsm ;

    @Override
    @Transactional(readOnly = true)
    public UserModel findByUsername(String username) {
        UserModel userm = userModelRepository.findByUsername(username);
        addRoleMap(userm);
        return userm;
    }

    @Override
    @Transactional(readOnly = true)
    public UserModel findById(long id) {
        return userModelRepository.findById(id);
    }


    @Override
    public boolean saveUser(UserModel userm)
    {
        if(userm != null)
        {
            System.out.println();
            userModelRepository.save(userm);
            return true;
        }

        return false;
    }


    @Override
    @Transactional(readOnly = true)
    public boolean findExistByname(String name) {
        return userModelRepository.findExistByname(name);
    }


    @Override
    public boolean deleteUser(long id) {
        userModelRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean findExistById(long id) {
        return userModelRepository.findExistById(id);
    }

    @Override
    public UploadImageModel findUploadById(long id) {
        return userModelRepository.findUploadById(id);
    }

    @Override
    public boolean findUploadByPath(String path) {
        return userModelRepository.findUploadByPath(path);
    }

    @Override
    public UploadImageModel getUploadByPath(String path) {
        return userModelRepository.getUploadByPath(path);
    }

    @Override
    public Iterable<UserModel> getAllUser() {
        return userModelRepository.findAll();
    }


    private void addRoleMap(UserModel userm)
    {
        if(userm == null) return;

        String myrole = userm.getRole();
        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(myrole);
        grantedAuthoritiesList.add(grantedAuthority);
        //userm.setGrantedAuthoritiesList(grantedAuthoritiesList);

    }


}
