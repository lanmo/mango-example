package com.yn.mango.test.dao;

import com.yn.mango.annotation.DAO;
import com.yn.mango.annotation.DB;
import com.yn.mango.annotation.SQL;
import com.yn.mango.test.pojo.GeoBlockOrder;

/**
 * Created by yangnan on 16/10/22.
 * 操作DAO的类
 */
@DB(
        database = "geo",
        table = "geo_block_order"
)
public interface UserDAO {

    @SQL("select * from geo_block_order where id = ${id}")
    GeoBlockOrder select(int id);
}
