# Customized Kafka connect 
`kafka-custom-connect` is a central piece in the `Outbox pattern` implementation using `Kafka/Debezium`, written in `Kotlin`. 
It decorates out-of-box `debezium-connect` to:
1. Include `JDBC` and `Elasticsearch` dependencies to enable `connectors` to be registered
2. Provides an `"outbox"` transformer which reacts to "create" events from `Debezium` (hooked to an `outbox` table)

## Generate docker image
[Currently assumes you have Java 11 locally]

From project root:
```bash
$ ./gradlew build
$ docker build -t dbz-custom-connect:1.0 . 
```

## Issues
1. Currently the `Kotlin` `transformer` fails to work, complaining about:
```bash
Caused by: java.lang.ClassNotFoundException: kotlin.TypeCastException
	at java.base/java.net.URLClassLoader.findClass(URLClassLoader.java:471)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:588)
	at org.apache.kafka.connect.runtime.isolation.PluginClassLoader.loadClass(PluginClassLoader.java:104)
	at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:521)
```  
The `java` version of the Transformer is included temporarily (that works) 

Actively fixing..