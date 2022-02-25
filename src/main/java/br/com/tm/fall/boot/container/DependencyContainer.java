package br.com.tm.fall.boot.container;

public interface DependencyContainer {

    <T> T getRice(String name, Class<T> clazz);

    <T> T getRice(Class<T> clazz);

    void setRice(Object rice);

    void setRice(Class<?> riceClass);
}
