package xbc.moka.authServer.Mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import xbc.moka.cloudsc.common.entity.User;

@Mapper
public interface UserMapper {
    User selectByUserName(@Param("username") String username);
}
