# PixabayGallery

PixabayGallery is a project to showcase development skills in developing Android apps. It fetches images from a server and caches them offline.

Features
---

This project is written using *Kotlin* and following *Clean Architecure* with *Model-view-viewModel (MVVM)* architecture pattern that follows *separation of concerns*
principle by keeping android classes as lean as possible and following Google architecutre guidlines and best practices.

The following technologies are used:

- ViewModel to store and manage UI-related data in a lifecycle conscious way.
- Hilt library to do dependency injection and reducing the boilerplate code.
- RxJava & RxAndroid for doing work on a background thread and communicating to a main thread.
- Retrofit library used to proivde esay way to make api requests.
- Paging 3 library to load and display pages of data from a larger dataset from local storage or over network.
- Room library for persisting data locally to provide seamles user experience allwoing the user browse app content while they are offline.
- Navigation component simplifies navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer. 
Also ensures a consistent and predictable user experience.
- Data binding allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.

Preview
--

![Animated GIF-downsized_large](https://media.giphy.com/media/T0zIAFCyxCBhy2kr58/giphy.gif)
