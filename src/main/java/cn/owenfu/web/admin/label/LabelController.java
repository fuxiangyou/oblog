package cn.owenfu.web.admin.label;

import cn.owenfu.common.BaseController;
import cn.owenfu.common.Result;
import cn.owenfu.common.ResultMsg;
import cn.owenfu.model.label.Label;
import cn.owenfu.service.label.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: Owen
 * Date: 2017/9/21
 * Time: 22:38
 * Just Do It!
 * LabelController description:
 * 标签控制层
 */
@Controller
@RequestMapping(value = "admin/label")
public class LabelController extends BaseController {
    @Autowired
    private LabelService labelService;

    /**
     * 跳转标签列表页
     *
     * @return
     */
    @RequestMapping(value = {"", "list"})
    public String getList(ModelMap map) {
        Result result = new Result();
        result.setData(labelService.findAll());
        map.addAttribute(result);
        return "admin/label";
    }
    /**
     * 跳转修改标签标题页
     *
     * @return
     */
    @RequestMapping(value = "update")
    public String update(Long id,ModelMap map) {
        //如果没有传id说明是增加文章，否者是修改文章
        if (id != null && !"".equals(id)) {
            Result result = new Result();
            result.setData(labelService.get(id));
            map.addAttribute("result",result);
        }
        return "admin/update_label";
    }
    /**
     * 添加/修改标签
     * @param label
     * @param map
     * @return
     */
    @RequestMapping(value = "addLabel")
    public String addArticle(Label label, ModelMap map){
        Result result = new Result();
        try {
            if (label.getId() != null) {
                Label label1 = labelService.get(label.getId());
                label.setArticles(label1.getArticles());
            }
            Label label1 = labelService.save(label);
            result.setData(label1);
            result.setCode(ResultMsg.SUCCESSLA.getCode());
            result.setMessage(ResultMsg.SUCCESSLA.getMsg());
        } catch (Exception e) {
            logger.info(e.getMessage());
            result.setCode(ResultMsg.EXCEPTIONLA.getCode());
            result.setMessage(ResultMsg.EXCEPTIONLA.getMsg());
        }
        map.addAttribute(result);
        return "redirect:/admin/label";
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
                labelService.delete(id);
                result.setCode(ResultMsg.SUCCESSLD.getCode());
                result.setMessage(ResultMsg.SUCCESSLD.getMsg());
            }catch (Exception e) {
                logger.info(e.getMessage());
                result.setCode(ResultMsg.EXCEPTIONLD.getCode());
                result.setMessage(ResultMsg.EXCEPTIONLD.getMsg());
            }
        }
        map.addAttribute(result);
        return "redirect:/admin/label";
    }

}
