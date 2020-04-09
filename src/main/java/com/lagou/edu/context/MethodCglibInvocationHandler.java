package com.lagou.edu.context;

import com.lagou.edu.annotation.Transactional;
import com.lagou.edu.context.transaction.TransactionManager;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodCglibInvocationHandler implements MethodInterceptor {

    private Object obj;

    private TransactionManager transactionManager;

    public MethodCglibInvocationHandler(TransactionManager transactionManager,Object object){
        this.transactionManager = transactionManager;
        this.obj = object;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;

        if(obj.getClass().getMethod(method.getName(),method.getReturnType()).getAnnotation(Transactional.class) == null){
            return method.invoke(obj,objects);
        }

        try{
            // 开启事务(关闭事务的自动提交)
            transactionManager.beginTransaction();
            result = method.invoke(obj,objects);
            // 提交事务
            transactionManager.commit();
        }catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            transactionManager.rollback();

            // 抛出异常便于上层servlet捕获
            throw e;

        }
        return result;
    }
}
