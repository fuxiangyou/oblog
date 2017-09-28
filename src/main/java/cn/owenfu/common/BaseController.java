package cn.owenfu.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: Owen
 * Date: 2017/9/19
 * Time: 21:38
 * Just Do It!
 * BaseController description:
 * 控制层抽象类
 */
public class BaseController<S extends BaseService<D,E>,D extends BaseDao<E,Long>,E extends BaseEntity<E>> {

    protected Logger logger = LogManager.getLogger(getClass());




}
