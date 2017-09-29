package cn.owenfu.model.label;

import cn.owenfu.common.BaseEntity;
import cn.owenfu.model.article.Article;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Owen
 * Date: 2017/9/19
 * Time: 21:03
 * Just Do It!
 * Label description:
 * 标签基础类
 */
@Entity
public class Label extends BaseEntity<Label> {

    private String name; //标签名字
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ArticleLabel",joinColumns = {@JoinColumn(name = "lid")},inverseJoinColumns = {@JoinColumn(name = "aid")})
    private Set<Article> articles = new HashSet<>(); //文章 多对多

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
