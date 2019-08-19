# CryptoNews
This application shows news about crypto currency, using the [NewsApi](https://newsapi.org).
### Important:
To run the program do not forget to add your api key to gradle.properties.

## Flow
- Application present a infinity scroll RecyclerView, clickin on "NewsApi" you'll be redirect to the NewsApi homepage
- Clicking on a news you are redirect to a screen with more information about the article.
- Clicking on "Continue Reading" you are redirected to a WebView with the full article.
- When there is no internet connection the app retry the connection until it comes back.

## Technologies 
- MVVM
- Databinding
- LiveData
- Paging
- Coroutines
