package br.com.tm.fall.boot.container;

public class DependencyContainerFactory {

    private DependencyContainerFactory() {}

    public static DependencyContainer getInstance() {
        return DependencyContainerImpl.getInstance();
    }
}
