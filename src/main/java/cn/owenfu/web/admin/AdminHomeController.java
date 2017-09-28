package cn.owenfu.web.admin;

import cn.owenfu.common.BaseController;
import cn.owenfu.common.Result;
import cn.owenfu.model.user.User;
import cn.owenfu.service.article.ArticleService;
import cn.owenfu.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
@RequestMapping(value = "admin")
public class AdminHomeController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
/*

    //@ApiOperation(value="跳转登陆页面", notes="登陆页面跳转")
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {
        System.out.println("AdminHomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");

        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.
        return "admin/login";
    }
*/

    @GetMapping("/login")
    public String loginForm() {
        return "admin/login";
    }
   @PostMapping("/login")
   public String login(User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
       if (bindingResult.hasErrors()) {
           return "/admin/login";
       }
       String loginName = user.getUserName();
       logger.info("准备登陆用户 => {}", loginName);
       UsernamePasswordToken token = new UsernamePasswordToken(loginName,user.getPassword());
       //获取当前的Subject
       Subject currentUser = SecurityUtils.getSubject();
       try {
           //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
           //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
           //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
           logger.info("对用户[" + loginName + "]进行登录验证..验证开始");
           currentUser.login(token);
           logger.info("对用户[" + loginName + "]进行登录验证..验证通过");
       } catch (UnknownAccountException uae) {
           logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,未知账户");
           redirectAttributes.addFlashAttribute("message", "未知账户");
       } catch (IncorrectCredentialsException ice) {
           logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,错误的凭证");
           redirectAttributes.addFlashAttribute("message", "密码不正确");
       } catch (LockedAccountException lae) {
           logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,账户已锁定");
           redirectAttributes.addFlashAttribute("message", "账户已锁定");
       } catch (ExcessiveAttemptsException eae) {
           logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,错误次数过多");
           redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
       } catch (AuthenticationException ae) {
           //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
           logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,堆栈轨迹如下");
           ae.printStackTrace();
           redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
       }
       //验证是否登录成功
       if (currentUser.isAuthenticated()) {
           logger.info("用户[" + loginName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
           return "redirect:/admin";
       } else {
           token.clear();
           return "redirect:/admin/login";
       }
   }

    //@ApiOperation(value="登陆后台主界面", notes="登陆成功后跳转后台首页")
    @RequestMapping(value = {"list",""} ,method = RequestMethod.GET)
    public String login( User user,ModelMap map) {
        Result result = userService.login(user);
        map.addAttribute("data",result);
        return "admin/index";
    }


}
