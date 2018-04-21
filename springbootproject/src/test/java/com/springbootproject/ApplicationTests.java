package com.springbootproject;

import com.springbootproject.domain.User;
import com.springbootproject.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource(name = "userService")
    private UserService userService;

    /**
     * 任何应用考虑到安全,绝不能明文的方式保存密码。
     * 密码应该通过哈希算法进行加密。
     * 有很多标准的算法比如SHA或者MD5,结合salt(盐)是一个不错的选择。
     * Spring Security 提供了BCryptPasswordEncoder类,
     * 实现Spring的PasswordEncoder接口使用BCrypt强哈希方法来加密密码。
     * <p>
     * BCrypt强哈希方法:每次加密的结果都不一样。
     */
    @Test
    public void getPassword() { // 加密
        System.out.println(passwordEncoder.encode("123456"));
    }

    @Test
    public void test1() {
        List<User> list = userService.find();
        for (User a : list) {
            System.out.println(a.getUsername());
        }
    }

    @Test
    public void test8() {   // 切割的用法
        String f = "1,2,3,";
        String s[] = f.split(",");

        Integer a[] = new Integer[s.length];
        for (int i = 0; i < s.length; i++) {
            a[i] = Integer.parseInt(s[i]);

        }
        System.out.println(Arrays.toString(a));

    }

/*    @Test
    public void test7() {
        String f = "1,2,";
        System.out.println(userService.addUA(7, f));
    }*/

    @Test
    public void test6() {
        System.out.println(userService.addUA(7, new Integer[]{1, 2}));
    }
}
