package com.bysj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bysj.beans.User;
import com.bysj.mapper.UserMapper;
import com.bysj.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
