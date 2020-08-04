Revision History
=

<pre><b>Date&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Revision&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Description&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Author</b>
<pre>11/Jan/2020  1.0      Initial Version              Rajesh Debnath

Introduction
=

This is the solution to <a href="https://www.geektrust.in/coding-problem/backend/tame-of-thrones/">problem 1</a> of geektrust.in coding challenge.

Principles
=

The following Software Engineering principles have been followed:

- DRY - There is no duplicate code
- Favour immutability - all the model objects which hold state are immutable
- Objects which are mutable are made thread-safe using lock-free data structure only (i.e. no use of <b>synchronized</b> keyword)
- Objects which are not thread safe are annotated with @NotThreadSafe. Usually, these are light weight objects (mostly Builders).
- Unchecked exceptions are favoured instead of Checked exceptions
- While using Java 8 Stream API, all lambdas are ensured to be side-effect free
- While leveraging principles of Functional Programming paradigm, OOPS is not compromised. After all, it's not Functional vs OOPS - it's Declarative vs Imperative.
- Components are well designed to be able communicate with each other but yet loosely coupled. This gives us flexibility to extend the application or replace specific components without affecting other parts of the application.
- DI - Dependency Injection is followed heavily. On that, the usage of Singleton is kept minimal because Singleton's hide dependencies.
- Software is good only if it works. Almost all the classes have been covered by enough of JUnit tests.
- Documentation - Although code is the best document, good amount of javadoc is in place to help understand the components better and faster.

Project Structure
=

It is a maven project, so code is placed in standard folders.

src/main/java &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- &nbsp;&nbsp;&nbsp;&nbsp; contains application code which will be shipped
src/main/resources &nbsp;- &nbsp;&nbsp;&nbsp;&nbsp; contains resources which is needed by the application. (Nothing there yet).
src/test/java &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- &nbsp;&nbsp;&nbsp;&nbsp; contains unit test code which will NOT be shipped
src/test/resources &nbsp;&nbsp;&nbsp;- &nbsp;&nbsp;&nbsp;&nbsp; contains resources needed by unit tests

<h4>Package Structure:</h4>

<b>com.geektrust.challenge.got.core</b> - The core classes of the application is present in this package<br>

<b>com.geektrust.challenge.got.model</b> - The model objects are here<br>

<b>com.geektrust.challenge.got.util</b> - Common utility classes are present here<br>

<h4>Note for Developers who use Eclipse:</h4>

After the project is imported. There is a folder called <b>ide</b>. It has two launcher files.

The following launchers must be available automatically in Run Configuration:<br>
1) Application (Failure) and<br>
2) Application (Success).<br>
As the name suggests, each one reads from input files which produces unsuccessful result (i.e. not enough Kingdom's won) and successful result (i.e. enough Kingdom's have been won to become ruler).

Assumptions
=

Application does what it is expected to do. The following validations are in place to process input data.

- King names mentioned in the input file which are unknown are ignored silently

- Application works in two modes: 1) all letters are processed as UPPER case and 2) all letters are processed in lower case. By default, it is the UPPER case. So, even if the input files contain lower or mixed cases, they will be converted to the mode in which application is running.

- Blank lines in the input file are ignored. 

The following has not been tested:

- Behaviour of application is undefined if there are duplicate rows in the input file.

Build And Execution
=

The program builds using maven and produces the jar as per the expectations defined <a href="https://github.com/geektrust/coding-problem-artefacts/blob/master/Java/README.md">here</a>.

<pre>mvn clean install<br>
java -jar &lt;path_to&gt;/geektrust.jar &lt;absolute_path_to_input_file&gt;<br>
</pre>

Output is displayed on the console.

 