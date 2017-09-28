package cn.owenfu.common;


import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface BaseDao<T extends BaseEntity,ID extends Serializable> extends JpaRepository<T, ID> {


}
