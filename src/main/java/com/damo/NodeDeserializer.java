package com.damo;

import java.io.IOException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class NodeDeserializer extends JsonDeserializer<Node> {

    public NodeDeserializer() {
        this(null);
    }

    public NodeDeserializer(Class<?> vc) {
        super();
    }

    @Override
    public Node deserialize(JsonParser parser, DeserializationContext context)
            throws IOException, JacksonException {
        Node noderetval = null;
        JsonNode mainNode = context.readTree(parser);
        if (mainNode.get("value") != null) {
            noderetval = new Node(mainNode.get("value").intValue(), null, null);
        }
        JsonNode rightNode = mainNode.get("right");
        JsonNode leftNode = mainNode.get("left");

        if (rightNode != null) {
            noderetval.right = deserialize(rightNode.traverse(), context);
        }

        if (leftNode != null) {
            noderetval.left = deserialize(leftNode.traverse(), context);
        }

        return noderetval;
    }

}
