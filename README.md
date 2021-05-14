# MyntDataFramework

Main GitHub page for the framework MyntDataFramework.

## Introduction
MyntDataFramework is a framework constructed to simplify operations regarding data-files and java class models, specifically .json, .xml and .csv files. The goal of the framework is to provide clients with more clean and readable code when working with objects from mentioned datafiles and making it easier and faster to execute operations regarding the scope.

Our main audience for the framework are clients who have a specific class of object and a datafile containing these objects. The client wishes to operate on these objects from the datafile and can easily use our framework to assist in their mission to generate clean and readable code for these actions.

## Important
This project and framework was created as a submission to the course ITF20119-1 21V Rammeverk at HIOF university.

## Third party
We use a third party library in our project to help with serialization and deserialization. The library that we use is the jackson library and you can also [view their GitHub page](https://github.com/FasterXML/jackson "Main portal of Jackson library in GitHub"). This library is widely popular amongst java-developers and we have great confidence that this framework is solid and a trustworthy source to help our framework with operations such as serialization and deserialization.

## Download the framework
You are free to download the source-code for the framework through our github page and build a .jar file if you want to use it for you projects. Instructions regarding how to create a .jar file is mentioned down below.

## Creating the .jar file from this repository
Download the source code and open up your IDE. We use IntelliJ IDE for this example.
When the project with the framework is downloaded click on:
  1. File --> Project structure
  2. Go to "Artifacts" --> click on the "+" button --> JAR --> From modules with dependencies
  3. In the "Module:" tab click and select "MyntDataFramework.main" and don't select any classes on the "Main class:" tab
  4. Name your .jar file accordingly and press "Apply"
  5. That tab should close and you can now click on the "Build" on the tab at the top and then click on "Build artifacts"
  6. Select the artifact that you just created and "Build"
  7. After the build has been completed the .jar file should be processed and you can see that in your project folder a new "out" folder is generated
  8. In this folder select the .jar file and place it in your new project
