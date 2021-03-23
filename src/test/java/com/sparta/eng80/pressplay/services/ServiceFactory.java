package com.sparta.eng80.pressplay.services;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.lang.annotation.Annotation;

public class ServiceFactory {
    public static Object createService(ServiceTypes type) {
        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize();

        var service = weldContainer.select((Annotation) type.getServiceType()).get();
        weldContainer.close();

        return service;
    }
}
