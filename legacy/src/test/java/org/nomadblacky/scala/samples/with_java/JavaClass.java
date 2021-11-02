package org.nomadblacky.scala.samples.with_java;

public class JavaClass {
    private final int i;

    public JavaClass(int i) {
        this.i = i;
    }

    public String f() {
        return String.valueOf(i);
    }
}
