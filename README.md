# CryptoNews
This application shows news about crypto currency, using the [NewsApi](https://newsapi.org).
### Important:
To run the program do not forget to add your api key to gradle.properties.

## Flow
- Application presents a infinity scroll RecyclerView, clicking on "NewsApi" you'll be redirect to the NewsApi homepage
- Clicking on a news you are redirect to a screen with more information about the article.
- Clicking on "Continue Reading" you are redirected to a WebView with the full article.
- When there is no internet connection the app tries to retry the connection until it comes back.

## Technologies 
- MVVM
- Databinding
- LiveData
- Paging
- Coroutines
- Dagger2

Obs:
All the ViewModels are beeing injected by Dagger2. I used it to pass values to the ViewModel's constructor and remove the ViewModel creation logic from the views. This [awesome talk](https://www.youtube.com/watch?v=9fn5s8_CYJI) discuss the Dagger solution used here

## Images
![home](https://user-images.githubusercontent.com/6085389/63264047-807f8300-c260-11e9-94b3-bf97e12aa31a.png)
![details](https://user-images.githubusercontent.com/6085389/63264048-81181980-c260-11e9-8021-f6a2a8fdabf3.png)
![webview](https://user-images.githubusercontent.com/6085389/63264049-81181980-c260-11e9-82c8-c9b1857430a5.png)
