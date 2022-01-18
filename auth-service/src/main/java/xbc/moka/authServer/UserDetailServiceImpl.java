package xbc.moka.authServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xbc.moka.authServer.Mapper.UserMapper;
import xbc.moka.cloudsc.common.entity.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.selectByUserName(s);
        if(user!=null){
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole())
            );
        }else{
            throw new UsernameNotFoundException("用户[" + s + "]不存在");
        }
    }
}
