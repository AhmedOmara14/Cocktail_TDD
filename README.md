<!-- Cocktail Quiz App Repository -->

<h1>Overview</h1>
<p> This test suite focuses on applying testing at the network layer, specifically targeting the <code>QuestionsService</code> interface. This service is responsible for communication with a remote API to fetch responses. The tests utilize the MockWebServer library, allowing for the simulation of network responses to verify the correct behavior of the service.</p>


<p> The primary objectives of the test cases are as follows:</p>

* Enqueues a mock response with a predefined JSON script representing a list of questions.
* Verifies that the service makes a network request with the expected URL.
* Enqueues a mock response to simulate a successful request.
* Asserts that no errors occurred during the network request.

<h2>Testing Tools</h2>
<p>The project leverages the following testing tools to achieve a comprehensive testing strategy:</p>
<ul>
  <li><strong>Mockito:</strong> An influential mocking framework that facilitates the creation of mock objects in unit tests. This aids in isolating components and simulating behavior, contributing to more effective and thorough testing.</li>
   <li><strong>Mock Web Server:</strong> mock responses from network request.</li>
</ul>

<p>Your contributions and feedback are highly valued.</p>

<p><strong>Happy coding! 🍹🧠</strong></p>
