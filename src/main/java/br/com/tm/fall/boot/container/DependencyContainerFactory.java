package br.com.tm.fall.boot.container;

public class DependencyContainerFactory {

    private static DependencyContainer instance;

    private DependencyContainerFactory() {}

    static {
        instance = new DependencyContainerImpl();
    }

    public static DependencyContainer getInstance() {
        return instance;
    }
}
