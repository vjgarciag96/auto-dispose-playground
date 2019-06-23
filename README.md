# Auto dispose playground

This project is an experiment with AutoDispose library (https://github.com/uber/AutoDispose), 
which is a library built by Uber with the aim of "automatically" handling the disposition/cancellation 
of rxjava streams.

In the most of the Android projects following a "clean architecture" approach, the subscriptions to rx streams 
are performed on view models or presenters. However, the library doesn't provide a built-in solution for this kind of components.  

In this project we have implemented a sample where we work with a simple scope for view models in order to automatically
disposing all of its streams (scopes are the approach the library follows to handle the automatical cancelation/disposition). 

## Sample

The sample consists on a screen which shows the seconds that elapsed since it appeared. 

These seconds are the stream we will work with in order to test the library.

There are two samples, one for a view model where the scope is used with composition and one where it's used with inheritance.

The screen contains some UI controls that allow us to simulate the creation/destruction of the view model and watch the result.

![alt text](https://lh3.googleusercontent.com/bxypbCtN3gXphWxJdAd_uuV_XfN0nMHuaGsWNviqLzqqtOcW_InWonUERj-URUS8IgWvZhTaSZfYCA-WUGpQZpzybfYMaSZE7aVsrBagX1_YE9vB10t-tC0DtSRsfeQUJk44QUi29k3dPabdL8thzqRW533iJhbWWlVzpQJptr8ySD76_cJmyOojmOqLDQQKTKFjvcfBENnm-427AYpjL3UnCJvOJr5HGD4rPLvEDmyJ-KbpeN9HYb4uBVOBgau_wUtl7OOppFJWqg1ZaUY5Di9xs-qpzNu1xq4NkMGyMyRJ7eGhmuqBfz1qERR5DzQtEJIwQ4ZUX3TDMEmIyH3b3X8CYGtFk60Qlfertyf007zSq5Yq0H5sR_p5F8_2_epWw5saAxahoCdXZCRl-wqaJa8xxZVOH8Cb6CdhGL8q4wlsehSDWJkjMh1nFoPNdHtwyK7xgEJFN3ygGNinfg0zBa0p3yYBKlZ7emJ-CdpXHfosfMDTh1efkI29WSue5oIU09qyEWHnJDeJMI7MMl5UUbl6fjSPWVU6rKDxdosReNhQGvCV3TETfAztWhiWqsFE9bTiVgYP32qMUu-oajS-piwpQfqZ9O-yVHjdgq4=w1784-h1608) 

![alt text](https://lh3.googleusercontent.com/4mI7uCeL95NRbwfPgT90sdu89-VfAirZB0QdCkBOUBUhcCx-vHSjy3UUIFq36ogi1Vbg8ZJ_jqmSTPIVcp0a0J7lOtY3MdU2V2q0kIL38ks5UkqGPEIdypjC9NFA1QMMRoShFDDI7pMv6ai5qm7Bwqin4n95tuAhITw_3EBiREeYGVN99Xu-iSEsyqIuIYbuwJH4l81MDjhZr4n714dkK8SxAwAlXTauiZcZhsv5XnQpzEeiXyZKEYjp4jObHCsk6W0OUwfxeMoNTfwvrveml4jjMBPYNoNFnMyn2XzgYR5_ENSmp-g7oP0xHHtBXx74mogYOaq0r4ET-PFSW0DdLoehAO0OaBHzL1TK9SO3m5ugP0Hqx8YKcCo7DoXBdYw3jXwiZh7t8a2Slpb2vdxhaXb1sZZ2XevwWnAyztBHS8UTBze4dfF2Om0A43ScUiYLWYhMhG3aN-FgS04a4vc52wN7DDt4kC8VTUWplokHVwQHxCJQaABkc5R4ZBXXvHdqpO_sNRQZXbqx8iA5rCyK0E6gp0Pez_kFc0lFgBVX6Pjz3umtcoA71bJF6HsDwa2aXYtHmzQhkyW3VmqCN9zfxDf3yYUSH70B4I_RTts=w1784-h1608)

Moreover, a test have been added for the implemented scope to make easier understanding how that scope and its events work. 
You can find it at *ViewModelScopeTest.kt* file

## Run it

To install the app just clone the repo and execute the next command from the project base directory:

````
./gradlew assembleDebug
````

And to run the mentioned test just execute the next command:

````
./gradlew test
