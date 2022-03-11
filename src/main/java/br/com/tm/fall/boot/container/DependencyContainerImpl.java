package br.com.tm.fall.boot.container;

import br.com.tm.fall.boot.core.InstanceUtils;
import br.com.tm.fall.boot.models.utils.NamespaceUtils;

import java.util.HashMap;
import java.util.Map;

public class DependencyContainerImpl implements DependencyContainer {

    private final Map<String, Object> dependencies = new HashMap<>(100);
    private final Map<String, Class<?>> runtimeClasses = new HashMap<>(100);
    private final InstanceUtils instanceBuilder = new InstanceUtils(this);

    @Override
    public <T> T getRice(String name, Class<T> clazz) {
        Class<?> runtimeClass = runtimeClasses.get(name);
        if(runtimeClass != null) {
            return (T) buildNewInstance(clazz);
        }

        return (T) dependencies.get(name);
    }

    @Override
    public <T> T getRice(Class<T> clazz) {
        String name = NamespaceUtils.generateRiceName(clazz);
        return getRice(name, clazz);
    }

    public void setRice(String name, Object rice) {
        dependencies.put(name, rice);
    }

    @Override
    public void setRice(Object rice) {
        dependencies.put(NamespaceUtils.generateRiceName(rice.getClass()), rice);
    }

    public void setRice(String name, Class<?> riceClass) {
        runtimeClasses.put(name, riceClass);
    }

    @Override
    public void setRice(Class<?> riceClass) {
        runtimeClasses.put(NamespaceUtils.generateRiceName(riceClass), riceClass);
    }

    private Object buildNewInstance(Class<?> clazz) {
        try {
            return instanceBuilder.buildInstance(clazz);
        } catch (Exception e) {
            return null;
        }
    }

}
