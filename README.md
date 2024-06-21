# Binary Search Tree Microservice

This is a project aimed at creating a REST API for manipulating a binary search tree.

## What went right

### REST API

Use of the [Spark micro framework](https://sparkjava.com/) greatly helped with this part of the task, as it is lightweight and very easy on the human eye.

Main:
This is where the endpoints are, where requests are validated before calling for the processing of the payload

ApiRequest:
This class functions as the middleman between endpoints and functionality, though it also sanitizes the input before having it processed through the deserializer.

### Functionality

Deserializing the JSON was helped along by using [Jackson](https://github.com/FasterXML/jackson), though I did have to construct a custom deserializer to handle recursive objects.

BinarySearchTree:
This class handles the logic of deserializing, inserting a new node, then serializing the tree once more before returning the new JSON-encoded tree through the response body.

I will however remark that it does indeed break when you pass off non-integers to the value field of any node in the tree.

Node:
This is what one part of the tree is, which are objects that contains Node objects as well.

NodeDeserializer:
This is the custom deserializer, which recursively calls itself to handle embedded nodes.

### Basic Pipeline

Seeing as there's out-of-the-box solutions on Github, it at least checks if it can build properly. If I had more time I would have used the pipeline to actually unit test after every push.

I am pretty certain you can see build results under the Actions tab.

## What went wrong

### Unit testing and integration of these into the pipeline

I actually spent the entire first day trying and failing to get a REST API off the ground in the ASP.NET framework, but decided to shift to Java instead, with the result being what you see here.

Unfortunately, due to this time crunch I did not manage to write up any unit tests at all, seeing as the functionality itself needed to be set up.

### Dockerization

I decided to throw in the towel on this point, as I did not have the time to properly look into why it was that when I build the Dockerfile using `docker image build -t docker-java-jar:latest .` it gave me the error `Error: Unable to access jarfile /src/target/app.jar`.

## How to run

Seeing as the Spark framework uses Jetty on its own, it just works when run. You can use `http://localhost:4567/insert` to check for functionality using e.g. Postman. Input is as written in the instructions.

My VSCode `launch.json` configuration however is as such:

```
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Current File",
            "request": "launch",
            "mainClass": "${file}"
        },
        {
            "type": "java",
            "name": "Main",
            "request": "launch",
            "mainClass": "com.damo.Main",
            "projectName": "binarysearchtree"
        }
    ]
}
```
