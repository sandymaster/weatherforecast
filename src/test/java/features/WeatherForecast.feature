Feature: Weather forecast

@Weather
Scenario: Enter city name, get 5 day weather forecast
Given Open the Browser navigate to URL
When User enters the City "Dundee" and Enter
Then System displays five day weather forecast

@Weather
Scenario: Select day, get 3 hourly forecast
Given Open the Browser navigate to URL
When User enters the City "Dundee" and Enter
Then System displays three hourly forecast for day selection

@Weather
Scenario: Select day again, hide 3 hourly forecast
Given Open the Browser navigate to URL
When User enters the City "Dundee" and Enter
Then System displays three hourly forecast for day selection
And User select day again and hides hourly forecast

@Weather
Scenario: Select day again, hide 3 hourly forecast
Given Open the Browser navigate to URL
When User enters the City "Dundee" and Enter
Then System displays three hourly forecast for day selection
And Daily forecast should summarize the hour data
