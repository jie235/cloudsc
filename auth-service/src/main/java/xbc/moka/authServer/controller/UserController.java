package xbc.moka.authServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import xbc.moka.authServer.Mapper.UserMapper;
import xbc.moka.cloudsc.common.entity.User;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("getByName")
    public User getByName() {
        return userMapper.selectByUserName("zhangjian");
    }

    @GetMapping("current/get")
    public Principal user(Principal principal) {
        return principal;
    }

    public void test() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("");
    }
}
