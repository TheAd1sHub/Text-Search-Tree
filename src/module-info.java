/**
 * 
 */
/**
 * 
 */
module TestSearcher {
    // General
    requires java.xml;
    requires java.instrument;
    requires java.sql;
    requires java.compiler;

    // For JUnit
    requires junit;
    requires java.net.http;
    requires java.desktop;
    requires org.jsoup;
    requires org.jetbrains.annotations;
    requires java.naming;
    exports test.src.model;
}