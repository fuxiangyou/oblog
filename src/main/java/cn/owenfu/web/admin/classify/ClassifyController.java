package cn.owenfu.web.admin.classify;

import cn.owenfu.common.BaseController;
import cn.owenfu.common.Result;
import cn.owenfu.common.ResultMsg;
import cn.owenfu.model.classify.Classify;
import cn.owenfu.service.classify.ClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Owen
 * Date: 2017/9/19
 * Time: 21:49
 * Just Do It!
 * ClassifyController description:
 * 分类控制层
 */
@Controller
@RequestMapping(value = "admin/classify")
public class ClassifyController extends BaseController {
    @Autowired
    private ClassifyService classifyService;

    /**
     * 跳转分类列表页
     *
     * @return
     */
    @RequestMapping(value = {"", "list"})
    public String getList(ModelMap map) {
        Result result = new Result();
        result.setData(classifyService.findAll());
        map.addAttribute(result);
        return "admin/classify";
    }
    /**
     * 跳转修改分类标题页
     *
     * @return
     */
    @RequestMapping(value = "update")
    public String update(Long id,ModelMap map) {
        //如果没有传id说明是增加文章，否者是修改文章
        if (id != null && !"".equals(id)) {
            Result result = new Result();
            result.setData(classifyService.get(id));
            map.addAttribute("result",result);
        }
        return "admin/update_classify";
    }
    /**
     * 添加/修改分类
     * @param classify
     * @param map
     * @return
     */
    @RequestMapping(value = "addClassify")
    public String addArticle(Classify classify,ModelMap map){
        Result result = new Result();
        try {
            if (classify.getId() != null) {
                Classify classify1 = classifyService.get(classify.getId());
                classify.setArticles(classify1.getArticles());
            }
            Classify classify1 = classifyService.save(classify);
            result.setData(classify1);
            result.setCode(ResultMsg.SUCCESSCA.getCode());
            result.setMessage(ResultMsg.SUCCESSCA.getMsg());
        } catch (Exception e) {
            logger.info(e.getMessage());
            result.setCode(ResultMsg.EXCEPTIONCA.getCode());
            result.setMessage(ResultMsg.EXCEPTIONCA.getMsg());
        }
        map.addAttribute(result);
        return "redirect:/admin/classify";
    }
    /**
     * 删除
     *
     * @return
     */
    @RequestMapping(value = "del")
    public String del(Long id,ModelMap map) {
        Result result = new Result();
        if (id == null) {
            result.setCode(ResultMsg.NULLA.getCode());
            result.setMessage(ResultMsg.NULLA.getMsg());
        } else {
            try {
                classifyService.delete(id);
                result.setCode(ResultMsg.SUCCESSCD.getCode());
                result.setMessage(ResultMsg.SUCCESSCD.getMsg());
            }catch (Exception e) {
                logger.info(e.getMessage());
                result.setCode(ResultMsg.EXCEPTION.getCode());
                result.setMessage(ResultMsg.EXCEPTION.getMsg());
            }
        }
        map.addAttribute(result);
        return "admin/classify";
    }


}
