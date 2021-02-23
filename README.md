# countries-service-repo
A spring boot microservice to get various information on countries

There are total 195 independent countries in the world as of the date I pushed the project onto Github and you can retrieve information on these countries using this microservice.

Available information is:
- Name of the country
- Capital
- Currencies used
- Languages spoken
- Continent it resides in

Available links are:

- http://localhost:8200/countries/{id}
- http://localhost:8200/countries/list
- http://localhost:8200/countries/list?language={language}
- http://localhost:8200/countries/list?currency={currency}
- http://localhost:8200/languages/list
- http://localhost:8200/currencies/list

The applocation tries to register to a Eureka server
