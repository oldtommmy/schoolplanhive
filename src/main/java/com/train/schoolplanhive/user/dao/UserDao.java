package com.train.schoolplanhive.user.dao;
import com.train.schoolplanhive.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * create by tommychan at 2023/7/18
 *
 * @Description: 用户操作持久层
 */

@Mapper
@Repository
public interface UserDao {
    @Select("select * from userinfo where username=#{username}")
    public User getUser(String username);

    @Select("select * from userinfo where email=#{email}")
    public User getUserByEmail(String email);

    @Select("select * from userinfo where id=#{id}")
    public User getUserById(Integer id);

    @Update("update userinfo set username=#{username}, real_name=#{realName}, email=#{email}, " +
            "mobile=#{mobile},gender=#{gender},\n" +
            "status=#{status}\n" +
            "where id=#{id}")
    public void updateUser(User user);

    @Update("update userinfo set pwd=#{pwd}" +
            "where id=#{id}")
    public void updatePwd(User user);
}
