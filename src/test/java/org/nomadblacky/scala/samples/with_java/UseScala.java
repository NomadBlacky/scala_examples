package org.nomadblacky.scala.samples.with_java;

public class UseScala {
    private final ScalaClass scalaClass;

    public UseScala(ScalaClass scalaClass) {
        this.scalaClass = scalaClass;
    }

    public int f() {
        return scalaClass.i();
    }

    public String f2(ScalaCaseClass caseClass) {
        return caseClass.toString();
    }
}
