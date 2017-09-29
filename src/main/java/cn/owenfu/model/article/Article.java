package cn.owenfu.model.article;

import cn.owenfu.common.BaseEntity;
import cn.owenfu.model.classify.Classify;
import cn.owenfu.model.label.Label;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Owen
 * Date: 2017/9/19
 * Time: 20:55
 * Just Do It!
 * Article description:
 * 文章model
 */
@Entity
public class Article extends BaseEntity<Article> {

    private String title; //标题
    @Lob
    private String content;//内容
    private Long likeNum;//点赞次数
    private byte visibility; //0 公开 1私密
    @ManyToMany(fetch = FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "ArticleLabel",joinColumns = {@JoinColumn(name = "aid")},inverseJoinColumns = {@JoinColumn(name = "lid")})
    private Set<Label> labels = new HashSet<>();//对应标签 多对多
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ArticleClassify",joinColumns = {@JoinColumn(name = "aid")},inverseJoinColumns = {@JoinColumn(name = "cid")})
    private Set<Classify> classifies = new HashSet<>();//对应分类 多对多

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    public Set<Classify> getClassifies() {
        return classifies;
    }

    public void setClassifies(Set<Classify> classifies) {
        this.classifies = classifies;
    }


}
