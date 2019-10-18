package com.yangzb.framework.common.codegenerator;

import cn.hutool.core.util.ReflectUtil;
import com.yangzb.framework.common.base.entity.EntityBase;
import com.yangzb.framework.common.base.util.SpringBeanHelper;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.Properties;

/**
 * 编码生成拦截器
 * @program: sale-center
 * @description:
 * @author: yzb
 * @create: 2019-05-15 11:38
 **/
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class CodeInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object param = args[1];
        MappedStatement mappedStatement = (MappedStatement)args[0];
        //只有再insert以及入参为实体对象时才执行编码生成
        if(mappedStatement.getSqlCommandType()== SqlCommandType.INSERT&&param instanceof EntityBase){
            Field[] fields = ReflectUtil.getFields(param.getClass());
            if(null!=fields){
                for (Field field : fields) {
                    CodeGenerator aCodeGenerator = field.getAnnotation(CodeGenerator.class);
                    Optional.ofNullable(aCodeGenerator).ifPresent(codeGenerator -> {
                        ICodeGenerator generator= SpringBeanHelper.getBean(codeGenerator.value());
                        Object code = generator.generate(codeGenerator.params());
                        ReflectUtil.setFieldValue(param,field,code);
                    });
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        //此处可以接收到配置文件的property参数
        System.out.println(properties.getProperty("name"));
    }

}