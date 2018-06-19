# Popular Movies
An Android app that helps users to discover movies. This is Project 1 & 2 of the Android Developer Nanodegree Program with Udacity.
### Features:
* Discover the most popular and highest rated movies
* Watch movie trailers and teasers
* Read reviews from other users
* Mark movies as favorites
* Save favorite movies locally to view them offline
* Search for movies
### Libraries:
* [ButterKnife](http://jakewharton.github.io/butterknife/ "ButterKnife")
* [RxJava2](https://github.com/ReactiveX/RxJava "RxJava2")
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [Retrofit](http://square.github.io/retrofit/ "Retrofit")
* [Gson](https://github.com/google/gson "Gson")
* [Picasso](http://square.github.io/picasso/ "Picasso")
## Screens
![Movie Detail Screen](https://github.com/ssouthwell1/Popular-Movies-App/blob/development/screenshot-details.png)
## Developer setup
### Requirements
* Java 8
* Latest Version of Android SDK and Android Build Tools
* Movie DB API Key
### API Key
The app uses [MovieDB](https://developers.themoviedb.org/3) API to get movie information and posters. You must provide your own API key in order to build the app.
####
Add your API Key into the `app/build.gradle` file:
``` 
buildTypes.each {
            it.buildConfigField('String', 'MOVIE_DB_API_KEY', "\"ADD_API_KEY_HERE\"")
        }
```        
 ## License
 ```
 Copyright 2018 Shamari Southwell

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
