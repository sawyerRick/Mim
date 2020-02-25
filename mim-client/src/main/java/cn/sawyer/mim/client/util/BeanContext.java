package cn.sawyer.mim.client.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-24 21:06
 **/
@Component
public final class BeanContext implements ApplicationContextAware {

    private static ApplicationContext context;

    public static <T> T getBean(Class<T> clazz) {

        return context.getBean(clazz);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
