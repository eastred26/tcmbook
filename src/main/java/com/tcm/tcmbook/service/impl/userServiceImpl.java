package com.tcm.tcmbook.service.impl;

import com.tcm.tcmbook.pojo.user;
import com.tcm.tcmbook.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private com.tcm.tcmbook.dao.userDao userDao;
    @Override
    public user getById(Integer id){return userDao.findById(id);}
    @Override
    public String getPwdByAccount(String account){return userDao.findPwdByAccount(account);}
    @Override
    public int getMaxId(){
        Integer maxUid=userDao.findMaxId();
        if(maxUid==null)return 0;
        return maxUid;
    }
    @Override
    public user check(String account, String pwd){ return userDao.check(account, pwd);}
    @Override
    public void AddUser(Integer id,String name,String account,String pwd){userDao.AddUser(id, name, account, pwd);}
    @Override
    public Integer findNum(){return userDao.findNum();}
    @Override
    public List<user> findAll(){return userDao.findAll();}
    @Override
    public void DeleteUser(Integer uid){userDao.DeleteUser(uid);}
}
