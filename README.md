<!-- Cocktail Quiz App Repository -->

# Cocktail Quiz App

Welcome to the Cocktail Quiz App repository! This project is a straightforward Android quiz app centered around the theme of cocktails. The primary focus is on Test-Driven Development (TDD) practices, ensuring robust code quality and functionality.

## Overview

This repository houses the source code for the Cocktail Quiz App, featuring two major testing strategies:

### 1. App Logic Testing (TDD Approach)

The app logic testing focuses on the core functionality of the quiz app, emphasizing the application of Test-Driven Development (TDD) principles. Key test files include:

- **MainViewModelTest:** Tests LiveData observations, ViewModel functionality, and shared preferences interactions.
- **CocktailRepositoryImplTest:** Ensures correct interactions with shared preferences using Mockito.
- **GameTest, QuestionTest, ScoreTest:** Validates the logic within game-related model classes.

### 2. Network Layer Testing

The network layer testing targets the `QuestionsService` interface responsible for fetching questions from a remote API. The tests utilize the MockWebServer library to simulate network responses. Key objectives include:

- Enqueue a mock response with a predefined JSON script representing a list of questions.
- Verify that the service makes a network request with the expected URL.
- Simulate a successful request and assert that no errors occurred during the network request.

## Testing Tools

The project leverages the following testing tools:

- **JUnit:** A widely adopted testing framework for Java and Kotlin.
- **Mockito:** An influential mocking framework facilitating the creation of mock objects.
- **MockWebServer:** Used to mock responses from network requests.

## Integration and Collaboration

To create a cohesive testing strategy, the project encourages collaboration and integration between app logic and network layer tests. The unified test suite covers end-to-end scenarios, promoting a holistic approach to testing. Developers are encouraged to follow TDD practices, leverage shared testing utilities, and contribute to the continuous improvement of the testing strategy.


Feel free to explore the test files, dive into the TDD-driven development approach, and contribute to the enhancement of the Cocktail Quiz App. Your feedback and contributions are highly valued.

Happy coding! 🍹🧠
