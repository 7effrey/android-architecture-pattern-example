# Android Architecture Pattern Example
A collection of simple examples to show different architectures and patterns for Android apps.

## MVP (Model - View - Presenter)
The most widely adopted pattern. Presenter interacts with View through an interface so presenter is unit testable because it doesn't have reference to Android specific views or API. Concerns that should be pointed out is to avoid bigger and bigger Presenter.

## MVVM (Model - View - View Model) with Data Binding
This pattern is more modular than MVP as View Model is loosely coupled with View because View Model is only providing observable data to the View to send response. Data Binding also give benefit to reduce amount of code to set View based on the observable data. Unit Testing can be done easier because there is no dependency to the View so mock View is not needed and observable variables can be verified directly. Similar concern that should be avoided is adding code to XML layout via views binding expression.

## MVVM (Model - View - View Model) with RxJava
This is similar to MVVM with Data Binding but it is using Rx Observable. As it doesn't use Android data binding functionality, it will have a lot of glue code to set View based on its state when binding manually. It is also easy to do unit test by verifying emitted data from Observable.

## MVVM (Model - View - View Model) with Architecture Components
This is similar to MVVM with RxJava but it is observing LiveData. LiveData is a lifecycle aware component so it doesn't need to be manually disposed / unsubscribed the subscription. However, it's a bit anti-pattern at the same time because unit test cases will have reference to android related components which are androidx.arch.* & androidx.lifecycle.*.

## VIPER (View - Interactor - Presenter - Entity - Router)
VIPER is an extension of MVP and trying to solve bigger and bigger Presenter issue by delegating some of the tasks to Interactor and Router.

## RIBs (Router - Interactor - Builder)
RIBs is cross-platform mobile architecture framework from [Uber](https://github.com/uber/RIBs). Unlike the others, RIBs doesn't have to have a View so business logic drives the app, not the view tree. Referring SOLID principle, RIBs is trying to separate builder that is strictly for building and utilising Dependency Injection.