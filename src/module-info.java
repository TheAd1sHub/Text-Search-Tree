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
    exports test.src.model;
}