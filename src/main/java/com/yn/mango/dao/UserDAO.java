package com.yn.mango.dao;

import com.yn.mango.annotation.DAO;

/**
 * Created by yangnan on 16/10/22.
 * 操作DAO的类
 */
@DAO(
        database = "test",
        table = "user"
)
public interface UserDAO {
}
