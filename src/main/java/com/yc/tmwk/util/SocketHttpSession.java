package com.yc.tmwk.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SocketHttpSession extends /*SpringConfigurator*/Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) throws NullPointerException {
        //super.modifyHandshake(sec, request, response);
        HttpSession session = (HttpSession) request.getHttpSession();
        // throws null pointer exception ,need to be catch
        sec.getUserProperties().put(HttpSession.class.getName(),session);
    }

    /*private static final String NO_VALUE = ObjectUtils.identityToString(new Object());
    private static final Log logger = LogFactory.getLog(SpringConfigurator.class);
    private static final Map<String, Map<Class<?>, String>> cache = new ConcurrentHashMap();


    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        String beanName;
        if (wac == null) {
            beanName = "Failed to find the root WebApplicationContext. Was ContextLoaderListener not used?";
            logger.error(beanName);
            throw new IllegalStateException(beanName);
        } else {
            beanName = ClassUtils.getShortNameAsProperty(endpointClass);
            if (wac.containsBean(beanName)) {
                T endpoint = wac.getBean(beanName, endpointClass);
                if (logger.isTraceEnabled()) {
                    logger.trace("Using @ServerEndpoint singleton " + endpoint);
                }

                return endpoint;
            } else {
                Component ann = (Component) AnnotationUtils.findAnnotation(endpointClass, Component.class);
                if (ann != null && wac.containsBean(ann.value())) {
                    T endpoint = wac.getBean(ann.value(), endpointClass);
                    if (logger.isTraceEnabled()) {
                        logger.trace("Using @ServerEndpoint singleton " + endpoint);
                    }

                    return endpoint;
                } else {
                    beanName = this.getBeanNameByType(wac, endpointClass);
                    if (beanName != null) {
                        return (T) wac.getBean(beanName);
                    } else {
                        if (logger.isTraceEnabled()) {
                            logger.trace("Creating new @ServerEndpoint instance of type " + endpointClass);
                        }

                        return wac.getAutowireCapableBeanFactory().createBean(endpointClass);
                    }
                }
            }
        }
    }

    @Nullable
    private String getBeanNameByType(WebApplicationContext wac, Class<?> endpointClass) {
        String wacId = wac.getId();
        Map<Class<?>, String> beanNamesByType = (Map)cache.get(wacId);
        if (beanNamesByType == null) {
            beanNamesByType = new ConcurrentHashMap();
            cache.put(wacId, beanNamesByType);
        }

        if (!((Map)beanNamesByType).containsKey(endpointClass)) {
            String[] names = wac.getBeanNamesForType(endpointClass);
            if (names.length == 1) {
                ((Map)beanNamesByType).put(endpointClass, names[0]);
            } else {
                ((Map)beanNamesByType).put(endpointClass, NO_VALUE);
                if (names.length > 1) {
                    throw new IllegalStateException("Found multiple @ServerEndpoint's of type [" + endpointClass.getName() + "]: bean names " + Arrays.asList(names));
                }
            }
        }

        String beanName = (String)((Map)beanNamesByType).get(endpointClass);
        return NO_VALUE.equals(beanName) ? null : beanName;
    }*/
}
