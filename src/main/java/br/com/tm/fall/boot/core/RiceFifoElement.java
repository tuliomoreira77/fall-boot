package br.com.tm.fall.boot.core;

public class RiceFifoElement {

    private Class<?> rice;
    private Integer fifoCount;

    public RiceFifoElement(Class<?> rice, Integer fifoCount) {
        this.rice = rice;
        this.fifoCount = fifoCount;
    }

    public Class<?> getRice() {
        return rice;
    }

    public Integer getFifoCount() {
        return fifoCount;
    }

    public void incrementFifoCount() {
        this.fifoCount++;
    }
}
