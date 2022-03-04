package br.com.tm.fall.boot.core;


import br.com.tm.fall.boot.annot.Injected;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RiceFifo {

    private List<RiceFifoElement> rices;

    public RiceFifo(List<RiceFifoElement> rices) {
        this.rices = rices;
        this.rices = this.rices
            .stream()
            .sorted((c1, c2) ->
                compare(
                    Arrays.stream(c1.getRice().getConstructors()).filter(c -> c.getAnnotation(Injected.class) != null).findFirst().get().getParameterCount(),
                    Arrays.stream(c2.getRice().getConstructors()).filter(c -> c.getAnnotation(Injected.class) != null).findFirst().get().getParameterCount()
                )
            )
            .collect(Collectors.toList());
    }

    public RiceFifoElement getNext() {
        if(rices.isEmpty()) return null;

        var rice = rices.get(0);
        rices.remove(0);
        return rice;
    }

    public void add(RiceFifoElement rice) {
        rice.incrementFifoCount();
        this.rices.add(rice);
    }

    private Integer compare(Integer number1, Integer number2) {
        return number1.compareTo(number2);
    }

}
