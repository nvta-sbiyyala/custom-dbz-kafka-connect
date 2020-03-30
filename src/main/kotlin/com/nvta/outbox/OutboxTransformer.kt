package com.nvta.outbox

import org.apache.kafka.common.config.ConfigDef
import org.apache.kafka.connect.connector.ConnectRecord
import org.apache.kafka.connect.data.Schema
import org.apache.kafka.connect.data.Struct
import org.apache.kafka.connect.header.Headers
import org.apache.kafka.connect.transforms.Transformation

/**
 * Used for configuring "outbox" CDC
 */
class OutboxTransformer<R : ConnectRecord<R>?> : Transformation<R> {
    override fun apply(record: R?): R? {
        val kStruct: Struct = record?.value() as Struct
        val dbOperation = kStruct.getString("op")
        return if ("c".equals(dbOperation, true)) {
            val after: Struct = kStruct.get("after") as Struct
            val uuid = after.getString("uuid")
            val payload = after.getString("payload")
            val topic = after.getString("event_type").toLowerCase()
            val headers: Headers = record.headers()
            headers.addString("eventId", uuid)

            record.newRecord(
                topic, null, Schema.STRING_SCHEMA, uuid, null, payload,
                record.timestamp(), headers
            )
        } else {
            record
        }
    }

    override fun config(): ConfigDef {
        return ConfigDef()
    }

    override fun close() {}
    override fun configure(configs: Map<String?, *>?) {}
}