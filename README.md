# coding-challenge

A master-detail application that displays a list of items obtained from a iTunes Search API and show a detailed view of each item. The data is obtained from:
<br>
&emsp;&emsp;   https://itunes.apple.com/search?term=star&amp;country=au&amp;media=movie&amp;all
<br>
The list shows the following details from the API:
<br>
&emsp;Track Name
<br>
&emsp;Artwork
<br>
&emsp;Price
<br>
&emsp;Genre
<br>
&emsp;In addition, the detail view shows the Long Description for the given item.
<br>
<br>
The app is based of Android Studio's built-in master-flow template which uses <b>Material Design</b> and a <b>RecyclerView</b> for the list of items.

<br>
<b>Some libraries used in the app:</b>

<br>
&emsp;  For handling the API and JSON:
<br>
&emsp;&emsp;    Retrofit
<br>
&emsp;&emsp;    Moshi
<br>
  
<br>
&emsp;  For loading the images:
<br>
&emsp;&emsp;    Picasso
<br>
<br>
&emsp;  Others:
<br>
&emsp;&emsp;    ViewModel and LiveData
<br>
&emsp;&emsp;    Coroutines
<br>
<br>
Design pattern used:
<br>
<b>MVVM (Model View ViewModel)</b>
<br>

And to keep it simple, I only used SharedPreferences to save and load the last time the user visited the app, shown inside the list header.
<br>


<h2>Screenshots</h2>
<h5> Loading screen while the data is being fetched</h5>
<img src="images/loading-screen.png" width=250>
<h5> Main screen after the data has been loaded</h5>
<img src="images/main-screen.png" width=250>
<h5> Detailed screen when clicking an item</h5>
<img src="images/detail-screen.png" width=250>
<h5> Detatiled screen when scrolling down</h5>
<img src="images/detail-screen-scrolled.png" width=250>
<h5> Error screen when data failed to load (e.g. no internet)</h5>
<img src="images/error-screen.png" width=600>
<h5> Tablet version of the main screen + details screen</h5>
<img src="images/tablet-detail-screen.png" width=600>
