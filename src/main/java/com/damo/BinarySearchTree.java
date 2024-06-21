package com.damo;

/**
* Class containing logic to process binary search trees
**/

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BinarySearchTree {

    // Recursive function to insert a new key into the tree
    static Node insertIntoTree(Node root, int key) {
        // If the tree is null, return a new root.
        if (root == null) {
            root = new Node(key, null, null);
            return root;
        }

        // TODO: Handle equal values?
        // Go down the tree
        else if (key < root.value) {
            root.left = insertIntoTree(root.left, key);
        } else if (key > root.value) {
            root.right = insertIntoTree(root.right, key);
        }

        return root;
    }

    // Recursive function to encode a binary tree to JSON
    static String serializeNode(Node root) {
        StringBuilder sbretval = new StringBuilder();

        // Add the current node's value
        sbretval.append("{\"value\": ");
        sbretval.append(root.value);
        sbretval.append(", ");

        // Check the left node; is it null?
        sbretval.append("\"left\": ");
        if (root.left == null) {
            sbretval.append("null, ");
        }
        // Follow downwards if it's not
        else {
            sbretval.append(serializeNode(root.left));
            sbretval.append(", ");
        }

        // Check the right node; is it null?
        sbretval.append("\"right\": ");
        if (root.right == null) {
            sbretval.append("null");
        }
        // Follow downwards if it's not
        else {
            sbretval.append(serializeNode(root.right));
        }

        sbretval.append("}");
        return sbretval.toString();
    }

    static Node deserializeNode(String root) throws JsonMappingException, JsonProcessingException {
        ObjectMapper treeMapper = new ObjectMapper();
        Node noderetval = null;

        if (root == "null") {
            return noderetval;
        }

        noderetval = treeMapper.readValue(root, new TypeReference<Node>() {
        });

        return noderetval;
    }
}
