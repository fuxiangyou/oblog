package cn.owenfu.web.admin.user;


import cn.owenfu.common.Result;
import cn.owenfu.model.user.User;
import cn.owenfu.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin/user")
public class UserController {

    @Autowired
    private UserService userService;



    //@ApiOperation(value="登陆", notes="根据User对象登陆")
    //@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = {"list",""} ,method = RequestMethod.GET)
    public String login( User user,ModelMap map) {
        Result result = userService.login(user);
        map.addAttribute("data",result);
        return "admin/index";
    }


}
