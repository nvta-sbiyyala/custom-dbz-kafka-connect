# Customized Kafka connect 
`kafka-custom-connect` is a central piece in the `Outbox pattern` [implementation](https://github.com/nvta-sbiyyala/spike-tracking-service) using `Kafka/Debezium`, written in `Kotlin`. 
It decorates out-of-box `debezium-connect` to:
1. Include `JDBC`, `Elasticsearch` and `Snowflake` dependencies to enable `connectors` to be registered
2. Provides an `"outbox"` transformer which reacts to "create" events from `Debezium` (hooked to an `outbox` table)

## Generate docker image
[Currently assumes you have Java 11 locally]

From project root:
```bash
$ ./gradlew :clean :uberJar
$ docker build -t dbz-custom-connect:1.0 . 
```
