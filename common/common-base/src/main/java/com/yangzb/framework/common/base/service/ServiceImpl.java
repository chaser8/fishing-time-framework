package com.yangzb.framework.common.base.service;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.fishing.common.base.page.*;
import com.yangzb.framework.common.base.page.*;
import com.yangzb.framework.common.base.util.SpringBeanHelper;
import com.yangzb.framework.common.base.entity.EntityBase;
import com.yangzb.framework.common.base.mapper.BaseMapper;
import com.tydic.dev1.common.base.page.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.*;

/**
 * 主要做了新增修改字段适配
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-03-27 17:54
 **/
public class ServiceImpl<M extends BaseMapper<T>, T> extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<M, T> implements IService<T> {

    protected Map<Class, BaseMapper> mappers = new HashMap<>();

    @Autowired
    protected SqlSessionFactory sqlSessionFactory;
    /***
     * 废弃，调用 public PageResult<T> page(PageInfo pageInfo,T queryParams)
     * @Description:
     * @param entity
     * @return PageResponseBody<T>
     * @author yzb
     * @date 2019-06-19 09:45
     * @throws
     */
    @Override
    @Deprecated
    public PageResponseBody<T> page(PageRequestBody entity) {
        QueryWrapper wrapper = new QueryWrapper(entity);
        return page(entity, wrapper);
    }


    /***
     * 弃用，请使用  public PageResult<T> page(PageInfo pageInfo,Wrapper wrapper)
     * @Description:
     * @param entity
     * @param wrapper
     * @return PageResponseBody<T>
     * @author yzb
     * @date 2019-06-19 09:48
     * @throws
     */
    @Override
    @Deprecated
    public PageResponseBody<T> page(PageRequestBody entity, Wrapper wrapper) {
        Page page = new Page(entity);
        this.page(page, wrapper);
        PageResponseBody pageResponseBody = new PageResponseBody(page);
        return pageResponseBody;
    }

    /**
     * page 查询
     *
     * @param pageInfo
     * @param queryParams
     * @return PageResult<T>
     * @throws
     * @author yzb
     * @date
     */
    @Override
    public PageResult<T> page(PageInfo pageInfo, T queryParams) {
        QueryWrapper wrapper = new QueryWrapper(queryParams);
        return this.page(pageInfo, wrapper);
    }

    /**
     * 分页查询
     *
     * @param pageInfo
     * @param wrapper
     * @return PageResult<T>
     * @throws
     * @author yzb
     * @date
     */
    @Override
    public PageResult<T> page(PageInfo pageInfo, Wrapper wrapper) {
        IPage page = pageInfo.getPage();
        this.page(page, wrapper);
        return new PageResult<>(page);
    }

    /******************************************此部分方法为重写实现数据库新增修改数据库字段无需重写service方案 start******************************************************/
    @Override
    public boolean save(T entity) {
        return retBool(ReflectUtil.invoke(getMapperByEntity(entity.getClass()),"insert",entity));
    }

    @Override
    public boolean remove(Wrapper<T> wrapper) {
        return SqlHelper.delBool(ReflectUtil.invoke(getMapperByEntity(wrapper.getEntity().getClass()),"delete",wrapper));
    }

    @Override
    public boolean updateById(T entity) {
        return retBool(ReflectUtil.invoke(getMapperByEntity(entity.getClass()),"updateById",entity));
    }

    @Override
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        return retBool(ReflectUtil.invoke(getMapperByEntity(entity.getClass()),"update",entity,updateWrapper));
    }

    @Override
    public T getById(Serializable id) {
        Class subEntityClass = getSubEntityClass();
        if(null!=subEntityClass){
            return ReflectUtil.invoke(getMapperByEntity(subEntityClass),"selectById",id);
        }else{
            return super.getById(id);
        }
    }


    @Override
    public Collection<T> listByIds(Collection<? extends Serializable> idList) {
        Class subEntityClass = getSubEntityClass();
        if(null!=subEntityClass){
            return ReflectUtil.invoke(getMapperByEntity(subEntityClass),"selectBatchIds",idList);
        }else{
            return super.listByIds(idList);
        }
    }

    @Override
    public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
        if (throwEx) {
            return ReflectUtil.invoke(getMapperByEntity(queryWrapper.getEntity().getClass()),"selectOne",queryWrapper);
        }
        return SqlHelper.getObject(ReflectUtil.invoke(getMapperByEntity(queryWrapper.getEntity().getClass()),"selectList",queryWrapper));
    }

    @Override
    public int count(Wrapper<T> queryWrapper) {
        return SqlHelper.retCount(ReflectUtil.invoke(getMapperByEntity(queryWrapper.getEntity().getClass()),"selectCount",queryWrapper));
    }

    @Override
    public List<T> list(Wrapper<T> queryWrapper) {
        return (List<T>)ReflectUtil.invoke(getMapperByEntity(queryWrapper.getEntity().getClass()),"selectList",queryWrapper);
    }

    @Override
    public IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper) {
        return ReflectUtil.invoke(getMapperByEntity(queryWrapper.getEntity().getClass()),"selectPage",page,queryWrapper);
    }

    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper) {
        return ReflectUtil.invoke(getMapperByEntity(queryWrapper.getEntity().getClass()),"selectMapsPage",page,queryWrapper);
    }

    /**
     *
     * 通过实体获取到对应的mapper
     * getMapperByEntity
     *
     * @param entityClass
     * @return BaseMapper<?>
     * @author yzbx
     * @date 2019-07-08 18:03
     */
    protected BaseMapper<?> getMapperByEntity(Class<?> entityClass){
        if(mappers.containsKey(entityClass)){
            return mappers.get(entityClass);
        }

        BaseMapper baseMapper = this.baseMapper;
        Class thisEntityClass = this.getEntityClass();

        //判断是否为当前对象mapper
        if(thisEntityClass!=null&&!thisEntityClass.equals(entityClass)){
            Collection<Class<?>> mappers = sqlSessionFactory.getConfiguration().getMapperRegistry().getMappers();
            for (Class<?> mapper : mappers) {
                Class<?> aClass = extractModelClass(mapper);
                if(aClass!=null&&aClass.equals(entityClass)){
                    baseMapper = (BaseMapper) SpringBeanHelper.getBean(mapper);
                    this.mappers.put(entityClass,baseMapper);
                    break;
                }
            }
        }
        return baseMapper;
    }

    /**
     * 提取泛型模型,多泛型的时候请将泛型T放在第一位
     *
     * @param mapperClass mapper 接口
     * @return mapper 泛型
     */
    protected Class<?> extractModelClass(Class<?> mapperClass) {
        Type[] types = mapperClass.getGenericInterfaces();
        ParameterizedType target = null;
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                Type[] typeArray = ((ParameterizedType) type).getActualTypeArguments();
                if (ArrayUtils.isNotEmpty(typeArray)) {
                    for (Type t : typeArray) {
                        if (t instanceof TypeVariable || t instanceof WildcardType) {
                            break;
                        } else {
                            target = (ParameterizedType) type;
                            break;
                        }
                    }
                }
                break;
            }
        }
        return target == null ? null : (Class<?>) target.getActualTypeArguments()[0];
    }

    /**
     *
     * 获取当前对象实体泛型
     * getEntityClass
     *
     * @param
     * @return java.lang.Class
     * @author yzb
     * @date 2019-07-08 18:02        
     */
    protected Class getEntityClass(){
        Class entityClass = null;
        Class implClass = this.getClass();
        if(!(implClass.getGenericSuperclass() instanceof ParameterizedType)){
            implClass = this.getClass().getSuperclass();
        }
        try {
            Type actualTypeArgument = ((ParameterizedType) implClass.getGenericSuperclass()).getActualTypeArguments()[1];
            if(actualTypeArgument instanceof Class&&EntityBase.class.isAssignableFrom((Class)actualTypeArgument)){
                entityClass = (Class)actualTypeArgument;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return entityClass;
    }

    /**
     *获取子类
     * getSubEntityClass
     *
     * @param
     * @return java.lang.Class
     * @author yzb
     * @date 2019-07-08 18:02
     */
    protected  Class getSubEntityClass(){
        Class subEntityClass= null;
        Set<Class<?>> subClasses = ClassUtil.scanPackage("com.tydic.dev1",aClass -> {
            if(this.getEntityClass().isAssignableFrom(aClass)&&ClassUtil.getShortClassName(aClass.getName()).endsWith("Custom")){
                return true;
            }
            return false;
        });
        if(subClasses.size()==1){
            subEntityClass = subClasses.iterator().next();
        }
        return subEntityClass;
    }
    /******************************************此部分方法为重写实现数据库新增修改数据库字段无需重写service方案 end******************************************************/

}
