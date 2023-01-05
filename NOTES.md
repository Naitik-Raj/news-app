#Android News App

##General News App

###News Api

- URL
```
https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=226adfe008df47d4b85acfae1713a421
```
- Category
```
technology,
science
```

###Setup dependencies
- Retrofit (networking)
- Coroutine (asynchronous lib)
- Koin (dependencies injection)

###Setup networking and data layer
- Create webservices
- Create data response with plugin JsonToKotlin
    - Nullable
    - Gson serializer
- Setup Retrofit Provider
- Setup Koin Module for provide webservices instance

###Create koin provider
- Bind Koin provider into the Application

###Create Repository in domain and datalayer
- Setup data domain (entity)
    - Non-Nullable for avoiding nullable in view layer
    - Default value
    - Immutable property
- Create mapper for map `News Response` into `News`
- Setup koin module

##Setup view model
- Convert `Flow` from repository into `LiveData` for observer in view
- Setup dependencies for provided `asLiveData()`
- Setup koin module

##Setup simple view
- Observe live data in activity
- Check result in textView
- Run on Emulator

## Render Result in RecyclerView

###Setup recyclerview
- Add recyclerview in layout
- Create item layout for each news
- Create view holder for each news item
- Setup Glide library dependencies for image networking
  - Change `annotationProcessor` with `kotlin-kapt`
-Create News Adapter
  - Create auto notifier instead of `notifyDataSetChanged`
  - Change `addNews` to kotlin style
- Setup in MainActivity
- Create clickable item

###ViewBinding
- Setup Gradle
- Setup binding
- Change all `findViewById` to id generated

###State Event (MVI)
- Create event for `Idle`, `Loading`, `Success`, `Error`
- Implement state event in repository
- Create view for each event
- Add delay between `Loading` and `Success` event in repository

###Kotlin extensions for utilities
- Refactor to extensions util:
  - Fetching web services with state
  - When operator

###Error handling
- Git branch feat/error_handling
- Create fake error api
- Create coroutine error handler to ui event
- Create Simple UI Event
- Check api for detail page
  - Api for detail item not available
  - Create from item

- Create base activity with binding
- Create gson string converter for convert data to string without parcelize
- Add glide extensions
- Add read more with chrome tabs

- Done
- Update to Git



