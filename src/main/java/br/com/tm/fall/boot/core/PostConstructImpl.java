package br.com.tm.fall.boot.core;

import br.com.tm.fall.boot.annot.AfterBuild;
import br.com.tm.fall.boot.container.DependencyContainer;
import br.com.tm.fall.boot.models.AfterBuildException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class PostConstructImpl {

    private final DependencyContainer dependencyContainer;
    private List<Method> methodsToExecute;
    private final Set<Class<?>> rices;

    public PostConstructImpl(DependencyContainer dependencyContainer, Set<Class<?>> rices) {
        this.dependencyContainer = dependencyContainer;
        this.methodsToExecute = new ArrayList<>(100);
        this.rices = rices;
    }

    public void execute() {
        createPostConstructMap();
        orderMethods();
        executeMethods();
    }

    private void createPostConstructMap() {
        rices.forEach(rice -> {
            methodsToExecute.addAll(Arrays.stream(rice.getMethods()).filter(el -> el.isAnnotationPresent(AfterBuild.class)).collect(Collectors.toList()));
        });
    }

    private void orderMethods() {
        methodsToExecute = methodsToExecute.stream().sorted(this::compareMethods).collect(Collectors.toList());
    }

    private void executeMethods() {
        for (var method : methodsToExecute) {
            try {
                Object instance = dependencyContainer.getRice(method.getDeclaringClass());
                method.invoke(instance);
            } catch (Exception e) {
                throw new AfterBuildException(method.getDeclaringClass().getName(), method.getName());
            }
        }
    }

    private Integer compareMethods(Method m1, Method m2) {
        return Integer.compare(m1.getAnnotation(AfterBuild.class).priority(), m2.getAnnotation(AfterBuild.class).priority());
    }
}
