package com.fishingtime.framework.springboot.starter.tk.mybatis.service;

import com.fishingtime.framework.springboot.starter.tk.mybatis.mapper.BaseMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program:
 * @description:
 * @create: 2019-08-08 10:10
 **/
public class ServiceImpl<T> implements IService<T> {

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param record
     * @return
     */
    public T selectOne(T record) {
        return mapper.selectOne(record);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param record
     * @return
     */
    
    public List<T> select(T record) {
        return mapper.select(record);
    }

    /**
     * 查询全部结果
     *
     * @return
     */
    
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param record
     * @return
     */
    
    public int selectCount(T record) {
        return mapper.selectCount(record);
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key
     * @return
     */
    
    public T selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    /**
     * 根据主键字段查询总数，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key
     * @return
     */
    
    public boolean existsWithPrimaryKey(Object key) {
        return mapper.existsWithPrimaryKey(key);
    }

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param record
     * @return
     */
    
    public int insert(T record) {
        return mapper.insert(record);
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param record
     * @return
     */
    
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param record
     * @return
     */
    
    public int updateByPrimaryKey(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param record
     * @return
     */
    
    public int updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param record
     * @return
     */
    public int delete(T record) {
        return mapper.delete(record);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param key
     * @return
     */
    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    public T selectOneByExample(Object example) {
        return mapper.selectOneByExample(example);
    }

    /**
     * 根据Example条件进行查询总数
     *
     * @param example
     * @return
     */
    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    /**
     * 根据Example条件删除数据
     *
     * @param example
     * @return
     */
    public int deleteByExample(Object example) {
        return mapper.deleteByExample(example);
    }

    /**
     * 根据Example条件更新实体`record`包含的全部属性，null值会被更新
     *
     * @param record
     * @param example
     * @return
     */
    public int updateByExample(T record, Object example) {
        return mapper.updateByExample(record, example);
    }

    /**
     * 根据Example条件更新实体`record`包含的不是null的属性值
     *
     * @param record
     * @param example
     * @return
     */
    public int updateByExampleSelective(T record, Object example) {
        return mapper.updateByExampleSelective(record, example);
    }

    /**
     * 根据example条件和RowBounds进行分页查询
     *
     * @param example
     * @param rowBounds
     * @return
     */
    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
        return mapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    /**
     * 根据实体属性和RowBounds进行分页查询
     *
     * @param record
     * @param rowBounds
     * @return
     */
    public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
        return mapper.selectByRowBounds(record, rowBounds);
    }
    @Autowired
    protected BaseMapper<T> mapper;
}
