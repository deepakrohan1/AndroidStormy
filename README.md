# AndroidStormy

This application uses the below key concepts

1. Use Of intents
2. JSON Parsing 
3. Use of forecast API
4. List View
5. Recycler View
6. Use of ButterKnife for easier handling of Input Fields
7. Use of External Styling techniques

Main Activity
--------------

The main activity is as shown in the below screen. It is the basic screen and has a way to fetch the JSON using the **_OkHttpClient_**
to fetch the JSON data from the [Forecast.IO api](http://forecast.io/#/f/40.7142,-74.0064). From the JSON is parsed to get the 
daily and hourly data and stored in ArrayList

![main activity](https://cloud.githubusercontent.com/assets/2250200/13722209/9268d338-e80b-11e5-92c5-529e97ed4214.png)
<img src="https://github.com/favicon.ico" width="48">

DailyList Activity
------------------

The activity displays the list view of all the weather info on weekly basis. ListView is used in the view. OnItemClick listeners 
is used to display default string information.

![daily list activity](https://cloud.githubusercontent.com/assets/2250200/13722210/957b8b24-e80b-11e5-8414-d3b743d6489c.png)

HourlyDay Activity
------------------

The activity displays the hourly info of the weather. To improve the efficiency here we have used the Recycler view. A custom 
adapter has been built for the recycler view has been built called the Hour Adapter, below is the screenshot of the 
hourly info being displayed

![Hourly Activity](https://cloud.githubusercontent.com/assets/2250200/13722211/968df8e4-e80b-11e5-91f2-08532c9b3e23.png)

All the assets of images have been stored in the _minmap_ folder, custom layout for creation of gradient has been added to 
_drawable_ folder under resources. 

MVC pattern has been followed throughout the project. Hour and Day implement Parcelable by using this the data is passed 
over between the intents. Check for more methods in the _model_ package.
