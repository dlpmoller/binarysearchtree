package com.damo;

/**
 * Class to hold all API Request actions
 **/
public class ApiRequest {

    // Is the format correct?
    public static boolean verifyBody(String requestBody) {
        boolean bretval = false;

        String[] requestArray = requestBody.split(",");

        if (requestArray.length > 1) {
            if (requestArray[0].contains("value") && requestArray[1].contains("tree")) {
                bretval = true;
            }
        }

        return bretval;
    }

    // Extracts the values of the payload
    public static String processBody(String requestBody) {
        String sretval = "";
        int valueToAdd;
        String root = "";
        Node convertedTreeNodes = null;
        Node binaryTreeNodes = null;
        String[] requestArray = requestBody.split("\"tree\": ");

        // This can likely be improved on.
        valueToAdd = Integer
                .parseInt(requestArray[0].substring(requestArray[0].length() - 3, requestArray[0].length() - 2));
        root = requestArray[1].substring(0, requestArray[1].length() - 1);

        // Deserialize provided tree
        try {
            convertedTreeNodes = BinarySearchTree.deserializeNode(root);
        } catch (Exception e) {
            // Come on, it was easier for me
            return e.getMessage();
        }

        // Slot the value into the tree
        binaryTreeNodes = BinarySearchTree.insertIntoTree(convertedTreeNodes, valueToAdd);
        // Convert it back to JSON
        sretval = BinarySearchTree.serializeNode(binaryTreeNodes);
        return sretval;
    }
}
