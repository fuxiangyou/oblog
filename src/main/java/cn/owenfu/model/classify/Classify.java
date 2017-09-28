package cn.owenfu.model.classify;

import cn.owenfu.common.BaseEntity;
import cn.owenfu.model.article.Article;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Owen
 * Date: 2017/9/19
 * Time: 21:05
 * Just Do It!
 * Classify description:
 * 分类 基础类
 */
@Entity
public class Classify extends BaseEntity<Classify> {


    private String name; //类名
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ArticleClassify",joinColumns = {@JoinColumn(name = "cid")},inverseJoinColumns = {@JoinColumn(name = "aid")})
    private Set<Article> articles;//文章 多对多
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
