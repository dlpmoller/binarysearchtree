package com.damo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = NodeDeserializer.class)
public class Node {
    int value;
    Node left, right;

    @JsonCreator
    public Node(
            @JsonProperty("value") int input,
            @JsonProperty("right") Node right,
            @JsonProperty("left") Node left) {
        value = input;
    }
}
