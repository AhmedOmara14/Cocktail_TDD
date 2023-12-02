<!-- Cocktail Quiz App Repository -->

<h1>Overview</h1>
<p>This repository houses the source code for a straightforward Android quiz app centered around the theme of cocktails. The primary focus of this project lies in the diligent application of Test-Driven Development (TDD) practices, ensuring robust code quality and functionality.</p>

<h2>Test-Driven Development (TDD)</h2>
<p>A fundamental aspect of this project is the implementation of Test-Driven Development (TDD) principles. Each module's tests have been meticulously crafted before the actual implementation, ensuring a solid foundation and correctness in the codebase. The tests are thoughtfully distributed across various files:</p>
<ul>
  <li>MainViewModelTest</li>
  <li>CocktailRepositoryImplTest</li>
  <li>GameTest</li>
  <li>QuestionTest</li>
  <li>ScoreTest</li>
</ul>

<p>These test files collectively cover a wide array of aspects related to the application logic, fostering a reliable and stable app environment.</p>

<h2>Testing Focus</h2>
<p>The testing coverage focuses on key components:</p>
<ul>
  <li><strong>Shared Preferences:</strong> Utilizes Mockito for testing CocktailRepositoryImpl, ensuring correct interactions with shared preferences.</li>
  <li><strong>LiveData:</strong> Tests in MainViewModelTest focus on LiveData observations, ensuring the ViewModel updates appropriately.</li>
  <li><strong>ViewModel:</strong> MainViewModelTest covers ViewModel functionality, testing methods for initializing the game, loading questions, and handling answers.</li>
  <li><strong>Model Classes:</strong> GameTest, QuestionTest, and ScoreTest validate the logic within the game-related model classes.</li>
</ul>

<h2>Testing Tools</h2>
<p>The project leverages the following testing tools to achieve a comprehensive testing strategy:</p>
<ul>
  <li><strong>JUnit:</strong> A widely adopted testing framework for Java and Kotlin. JUnit provides annotations for test definition and assertions for validating expected outcomes.</li>
  <li><strong>Mockito:</strong> An influential mocking framework that facilitates the creation of mock objects in unit tests. This aids in isolating components and simulating behavior, contributing to more effective and thorough testing.</li>
</ul>

<p>Feel free to explore the test files and dive into the TDD-driven development approach that underlies the Cocktail Quiz App. Your contributions and feedback are highly valued.</p>

<p><strong>Happy coding! 🍹🧠</strong></p>
