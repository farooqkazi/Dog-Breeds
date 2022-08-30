# Dog Breed Info
List of Dog Breeds

An Android application to showcase list of Dog Breeds. The application allows users to see
various dog breeds alongside their images. The application consumes data from the [Dog CEO API](https://dog.ceo/dog-api/)
Minimum Api Level : 26
compileSdkVersion : 32
Build System : [Gradle](https://gradle.org/)

## Table of Contents

- [Architecture](#architecture)
- [Libraries](#libraries)
- [Solution](#solution)


## Architecture
The application is built with scalability in mind and care for having multiple developers working
on it. I used the Clean architectural principles to build the application.
I choose this architecture because it fosters better separation of concerns
and testability.

The Application is split into a three layer architecture:

- Data
- Domain
- Presentation

#### Data
The data layer handles the business logic and provides data from the
Dog Ceo API. This layer uses the Repository pattern to fetch data from the data source which in
this case is the DOG API.

#### Domain
The domain layer contains the application specifics logic. It contains
repository interfaces/use cases that expose the actions that can be performed in the application.

#### Presentation
I used the MVVM pattern for the presentation layer. The Model essentially exposes
the various states the view can be in. The ViewModel handles the UI logic and provides
data via Android architectural component LiveData to the view. The ViewModel talks to
the domain layer with the individual use cases.


## Libraries
Some of the libraries used in the application are:

- [Jetpack](https://developer.android.com/jetpack)
    - [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI related data in a lifecycle conscious way
      and act as a channel between use cases and UI.
    - [Data Binding](https://developer.android.com/topic/libraries/data-binding) - support library that allows binding of UI components in layouts to data sources.
    - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Retrofit](https://square.github.io/retrofit/) - type safe http client and supports coroutines out of the box and default parameters.
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines. I used this for asynchronous programming in order
  to obtain data from the network.
- [Glide](https://github.com/bumptech/glide)
- [Hilt](https://dagger.dev/hilt/) - Dependency injection plays a central role in the architectural pattern used.

## Solution
In general, any particular flow can be said to follow the steps below:
- The view sends an action to the ViewModel
- The ViewModel reaches out to the UseCase/Interactor
- The UseCase via an abstraction layer reaches out to the repository
- The repository retrieves the data and returns (mapped to domain representation)
- The UseCase gets the returned value and hand it over to the ViewModel
- The ViewModel maps the returned value to the presentation object.
- Finally, the ViewModel creates a view to model the state of the view and hand it over the view.
