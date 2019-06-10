package life.maijiang.comunity.controller;


import life.maijiang.comunity.dto.PaginationDTO;
import life.maijiang.comunity.dto.QuestionDTO;
import life.maijiang.comunity.mapper.UserMapper;
import life.maijiang.comunity.model.User;
import life.maijiang.comunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    /**
     * 方式1
     * 在 idea 使用 mybatis 的情况下，用@Autowired 总是会提示错误，但是代码是可以运行的，而且一点问题也没有
     * 将 @Autowired 注解换成 @Resource
     * 这样它就不会报错了
     *
     * 方式2
     * Preferences | Editor | Inspections中选择Spring | Spring Core | Code | Autowiring for Bean Class降低检测级别
     *
     * 方式3
     * 去除关联Spring
     *
     * 方式4
     * qualifier来跟bean去匹配：@Qualifier("userMapper")
     * 参考文章：https://blog.csdn.net/afsvsv/article/details/78771591
     */
    // @Resource
    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String greeting(HttpServletRequest request,
                           Model model,
                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "2") Integer size) {
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
