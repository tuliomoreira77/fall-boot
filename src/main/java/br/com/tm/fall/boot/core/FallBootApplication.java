package br.com.tm.fall.boot.core;

import br.com.tm.fall.boot.annot.Rice;
import br.com.tm.fall.boot.container.DependencyContainer;
import br.com.tm.fall.boot.container.DependencyContainerFactory;

public class FallBootApplication {

    private FallBootApplication() {}

    public static void run(String... packages) {
        DependencyContainer dependencyContainer = DependencyContainerFactory.getInstance();
        var rices = ClassScanner.scanPackages(Rice.class, packages);

        new RiceFactoryImpl(dependencyContainer).createDependencyContainer(rices);
        new PostConstructImpl(dependencyContainer, rices).execute();
    }

}
