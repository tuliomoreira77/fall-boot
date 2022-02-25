package br.com.tm.fall.boot.core;

import br.com.tm.fall.boot.annot.Rice;
import br.com.tm.fall.boot.container.DependencyContainerFactory;

public class FallBootApplication {

    private FallBootApplication() {}

    public static void run(String... packages) {
        RiceFactoryImpl riceFactory = new RiceFactoryImpl(DependencyContainerFactory.getInstance());
        riceFactory.createDependencyContainer(ClassScanner.scanPackages(Rice.class, packages));
    }

}
