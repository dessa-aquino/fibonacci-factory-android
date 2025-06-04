# Fibonacci Factory

A simple yet well-structured Android application developed in a few hours as part of a job application process. The app calculates Fibonacci numbers and measures the calculation time for each number in the sequence.

## Features and Technical Highlights

### Architecture and Design Patterns
- Clean Architecture principles
- MVVM (Model-View-ViewModel) pattern
- Repository pattern for data management
- Dependency Injection using Koin

### State Management
- Comprehensive state handling (Success, Error, Loading)
- Coroutines for asynchronous operations
- StateFlow for reactive state updates

### UI/UX
- Modern UI built with Jetpack Compose
- Material 3 design components
- Reusable components shared across different screens (InfoCard)
- Loading state with visual feedback
- Error handling with user-friendly messages

### Testing
- Unit tests for complex business logic
- JUnit and MockK for testing
- Focused on testing critical components (UseCase, ViewModel)

### Other Features
- Performance measurement for calculations
- Summary screen with historical data
- Local data persistence
- Navigation between screens

## Next Steps

### Testing Improvements
- Increase unit test coverage
- Add UI tests using Compose testing framework
- Implement end-to-end (E2E) tests
- Add integration tests

### Feature Enhancements
- Cloud repository integration for data backup
- Enhanced UI/UX design
- More detailed performance analytics
- Additional calculation options

### Design and UX
- Polish visual design
- Add animations
- Improve accessibility
- Support for different screen sizes

## Technical Requirements
- Android 8.0 (API 26) or higher
- Kotlin 1.9+
- Android Studio Hedgehog or newer

## Libraries Used
- Jetpack Compose for UI
- Material 3 for design
- Koin for dependency injection
- Room for local storage
- Navigation Compose
- Kotlin Coroutines
- JUnit and MockK for testing 