package cn.owenfu.dao;

import cn.owenfu.dao.article.ArticleDao;
import cn.owenfu.dao.classify.ClassifyDao;
import cn.owenfu.model.article.Article;
import cn.owenfu.model.classify.Classify;
import cn.owenfu.service.article.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Owen
 * Date: 2017/9/20
 * Time: 21:05
 * Just Do It!
 * ArticleDaoTest description:
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleDaoTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ClassifyDao classifyDao;
    @Test
    public void Test1(){

        long id = 2;
        try {

            Classify classify = classifyDao.getOne(2L);
            System.out.println(classify.getName());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
       /* List<Article> list = articleService.findAll();
        PageRequest pageRequest = new PageRequest(0,1);
        Page<Article> list1 = articleDao.findAll(pageRequest);

        list1.forEach(o -> System.out.println(o));*/
    }

}
