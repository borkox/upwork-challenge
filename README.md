# Upwork-challenge

## URL Shortening Service

### Prerequisites
 * Java 11
 * Maven

### App structure

Spring boot application is started and 2 REST controllers are introduced.
UrlShortController is responsible to create short links based
on generating a unique ID. The DB will validate that the id is unique.
Example link: http://localhost:8080/1jsU1XA
Here the ID is : `1jsU1XA` and consists of latin letters and numbers 0-9
and is case-sensitive.

Then LinkResolveController is responsible to retrieve the link based on
the id. So if you type in the browser the link 
`http://localhost:8080/1jsU1XA` the controller will recognize pattern
`http://localhost:8080/{linkId}` and will retrieve the link information.
Complexity is nearly O(1) since we only find in DB by ID and we don't 
do select by other criteria.

Housekeeping is triggered with @Scheduler annotation every couple of minutes(configurable)
and will clean old links. When link is created it is assigned an expiration time (configurable).

#### UrlShortController: 
 * POST `/api/url` 
   <br>Example:
```text
POST http://localhost:8080/api/url
Accept: application/json
Content-Type: application/json

{
 "url": "https://elinpelin.org/section-250-content.html"
}
```
Response:
```text
{
  "shortUrl": "http://localhost:8080/1jsU1XA",
  "longUrl": "https://elinpelin.org/section-250-content.html"
}
```
#### LinkResolveController
 * GET `/{urlId}`
 <br>Example: `http://localhost:8080/1jsU1XA`
 <br>Response: HTTP 303 Link: https://elinpelin.org/section-250-content.html

### Build 
`mvn clean package`
### Run the app
`"C:\Program Files\Java\jdk-11.0.7\bin\java" -jar target/url-shotener-0.0.1-SNAPSHOT.jar`


# Monetizing ideas
* Links can be used to trace when and who clicked on it and how many times, this can be visible for 
  payed users to url shortening service
* Generating many links on demand can be visible to payed users only
* Increasing expiry time for links can be a payed feature
* Generating good looking URLs and not ugly can be payed feature, 
  for example: `http://localhost:8080/welcome-upwork`

