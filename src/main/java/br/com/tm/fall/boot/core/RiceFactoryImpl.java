package br.com.tm.fall.boot.core;

import br.com.tm.fall.boot.annot.RiceScoped;
import br.com.tm.fall.boot.container.DependencyContainer;
import br.com.tm.fall.boot.models.InvalidRiceBuildException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RiceFactoryImpl implements RiceFactory {

    private final DependencyContainer dependencyContainer;
    private final InstanceBuilder instanceBuilder;

    public RiceFactoryImpl(DependencyContainer dependencyContainer) {
        this.dependencyContainer = dependencyContainer;
        this.instanceBuilder = new InstanceBuilder(this.dependencyContainer);
    }

    public void createDependencyContainer(Set<Class<?>> rices) {
        rices.forEach(instanceBuilder::verifyValidConstructor);
        RiceFifo fifo = new RiceFifo(buildRiceFifoElements(rices));
        for(RiceFifoElement riceFifoElement = fifo.getNext(); riceFifoElement != null; riceFifoElement = fifo.getNext()) {
            if(riceFifoElement.getRice().isAnnotationPresent(RiceScoped.class)) {
                createScopedRice(riceFifoElement, fifo);
            } else {
                createSingletonRice(riceFifoElement, fifo, rices.size() +1);
            }
        }
        postConstructVerify(rices);
    }

    private void createSingletonRice(RiceFifoElement riceFifoElement, RiceFifo fifo, Integer limit) {
        Object instance = instanceBuilder.buildInstance(riceFifoElement.getRice());
        if(instance == null) {
            fifo.add(riceFifoElement);
            riceVerifier(riceFifoElement, limit);
        } else {
            dependencyContainer.setRice(instance);
        }
    }

    private void createScopedRice(RiceFifoElement riceFifoElement, RiceFifo fifo) {
        dependencyContainer.setRice(riceFifoElement.getClass());
    }

    private List<RiceFifoElement> buildRiceFifoElements(Set<Class<?>> rices) {
        return rices.stream().map(el -> new RiceFifoElement(el, 0)).collect(Collectors.toList());
    }

    private void riceVerifier(RiceFifoElement riceFifoElement, Integer limit) {
        if(riceFifoElement.getFifoCount() > limit) {
            throw new InvalidRiceBuildException(riceFifoElement.getRice().getName());
        }
    }

    private void postConstructVerify(Set<Class<?>> rices) {
        for(var rice : rices) {
            Object instance = dependencyContainer.getRice(rice);
            if(instance == null) {
                throw new InvalidRiceBuildException(rice.getName());
            }
        }
    }

}
