package com.ohgiraffers.springlastteam.login.service;

import com.ohgiraffers.springlastteam.entity.Users;
import com.ohgiraffers.springlastteam.login.dto.UserDTO;
import com.ohgiraffers.springlastteam.login.repository.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final LoginRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(LoginRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users login(UserDTO userDTO) {
        Optional<Users> userOptional = userRepository.findByUserIdAndUserPwd(userDTO.getUserId(), userDTO.getUserPwd());
        return userOptional.orElse(null);
    }

    @Override
    public void register(UserDTO userDTO) throws Exception {
        if (userRepository.existsByUserId(userDTO.getUserId())) {
            throw new Exception("Id already in use");
        }
        Users user = new Users();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setUserPwd(userDTO.getUserPwd());
        user.setUserPhone(userDTO.getUserPhone());
        user.setUserApartment(userDTO.getUserApartment());
        user.setUserApartNum(userDTO.getUserApartNum());
        user.setUserRights("N");

        userRepository.save(user);
    }
}
