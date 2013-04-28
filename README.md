vaadin-lazyquery-sample
=======================

A sample Vaadin application using LazyQueryContainer

Required Environment
--------------------
* Java 7
* Maven 3
* [TomEE](http://tomee.apache.org/) (tested with 1.5.1)

Run application
---------------
* Clone repository
* `mvn clean install`
* Deploy `lqc-demo.war` into TomEE's webapps folder
* Open [http://localhost:8080/lqc-demo/](http://localhost:8080/lqc-demo/) in a browser

Main features
-------------
* Lazy loading of entities via [LazyQueryContainer](https://vaadin.com/directory#addon/lazy-query-container)
* Using JPA for fetching the entities
* Using [QueryDSL](http://www.querydsl.com/) for construction of the queries
* Compile-safe binding of properties with generated QueryDSL classes
  * Sorting
  * Filtering
  * Specifying visible columns
* Forms
* Bean Validation
* Navigation
