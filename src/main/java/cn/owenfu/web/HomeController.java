package cn.owenfu.web;

import cn.owenfu.common.Result;
import cn.owenfu.model.article.Article;
import cn.owenfu.service.article.ArticleService;
import cn.owenfu.service.user.UserService;
import cn.owenfu.web.admin.article.ArticleController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Owen
 * Date: 2017/9/18
 * Time: 5:11
 * Just Do It!
 * AdminController description:
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;


    /**
     * 跳转文章列表首页
     *
     * @return
     */

    @RequestMapping(value = {"", "list"})
    public String getList(ModelMap map) {
        Result result = new Result();
        /*List<Object> list = new ArrayList<>();
        list.add(articleService.findAll());*/
        result.setData(articleService.findAll());
        map.addAttribute(result);
        return "theme1/index";
    }
    /**
     * 跳转文章列表首页
     *
     * @return
     */

    @RequestMapping(value = "about")
    public String about(ModelMap map) {
        Result result = new Result();
        /*List<Object> list = new ArrayList<>();
        list.add(articleService.findAll());*/
        result.setData(articleService.findAll());
        map.addAttribute(result);
        return "theme1/about";
    }

    /**
     * 跳转文章列表首页
     *
     * @return
     */

    @RequestMapping(value = "/article")
    public String getList(Article article , ModelMap map) {
        Result result = new Result();
        /*List<Object> list = new ArrayList<>();
        list.add(articleService.findAll());*/
        result.setData(articleService.get(article.getId()));
        map.addAttribute(result);
        return "theme1/article";
    }


    //@ApiOperation(value="登陆后台主界面", notes="登陆成功后跳转后台首页")
    @RequestMapping(value = "adminIndex", method = RequestMethod.POST)
    public String index() {
        return "admin/index";
    }

    @RequestMapping(value = "thymeleaf", method = RequestMethod.GET)
    public String thymeleaf() {
        return "admin/thymeleaf";
    }

    @RequestMapping(value = "403")
    public String error403() {
        return "403";
    }

}
