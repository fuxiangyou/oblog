package cn.owenfu.web.admin.article;

import cn.owenfu.common.BaseController;
import cn.owenfu.common.Result;
import cn.owenfu.common.ResultMsg;
import cn.owenfu.model.article.Article;
import cn.owenfu.model.classify.Classify;
import cn.owenfu.model.label.Label;
import cn.owenfu.service.article.ArticleService;
import cn.owenfu.service.classify.ClassifyService;
import cn.owenfu.service.label.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Owen
 * Date: 2017/9/19
 * Time: 21:33
 * Just Do It!
 * ArticleController description:
 * 文章后台控制器
 */
@Controller
@RequestMapping(value = "admin/article")
public class ArticleController extends BaseController {


    @Autowired
    private ArticleService articleService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private ClassifyService classifyService;

    /**
     * 跳转文章列表页
     *
     * @return
     */
    @RequestMapping(value = {"", "list"})
    public String getList(ModelMap map) {
        Result result = new Result();
        result.setData(articleService.findAll());
        map.addAttribute(result);
        return "admin/article";
    }

    /**
     * 跳转增加文章页
     *
     * @return
     */
    @RequestMapping(value = "add")
    public String add(Long id,ModelMap map) {
        //如果没有传id说明是增加文章，否者是修改文章
        if (id != null && !"".equals(id)) {
            Result result = new Result();
            result.setData(articleService.get(id));
            map.addAttribute("result",result);
        }
        //查询所有的标签
        List<Label> labels = labelService.findAll();
        //查询所有的分类
        List<Classify> classifies = classifyService.findAll();
        map.addAttribute("labels",labels);
        map.addAttribute("classifies",classifies);
        return "admin/add_article";
    }

    /**
     * 添加/修改文章
     * @param article
     * @param map
     * @return
     */
    @RequestMapping(value = "addArticle")
    public String addArticle(Article article, HttpServletRequest request,ModelMap map){
        Result result = new Result();
        String cid = request.getParameter("classify.id");
        Article article1 = new Article();
        if (cid != null) {
            Set<Classify> classifies = article.getClassifies();
            Long l = Long.valueOf(cid);
            Classify classify = classifyService.get(l);
            classifies.add(classify);
            article.setClassifies(classifies);
        }
        try {
             article1 = articleService.save(article);
            result.setData(article1);
            result.setCode(ResultMsg.SUCCESSAA.getCode());
            result.setMessage(ResultMsg.SUCCESSAA.getMsg());
        } catch (Exception e) {
            logger.info(e.getMessage());
            result.setCode(ResultMsg.EXCEPTIONAA.getCode());
            result.setMessage(ResultMsg.EXCEPTIONAA.getMsg());
        }
        map.addAttribute(result);
        return "redirect:add?id="+article1.getId();
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
                articleService.delete(id);
                result.setCode(ResultMsg.SUCCESSAD.getCode());
                result.setMessage(ResultMsg.SUCCESSAD.getMsg());
            }catch (Exception e) {
                logger.info(e.getMessage());
                result.setCode(ResultMsg.EXCEPTION.getCode());
                result.setMessage(ResultMsg.EXCEPTION.getMsg());
            }
        }
        map.addAttribute(result);
        return "admin/article";
    }



}
