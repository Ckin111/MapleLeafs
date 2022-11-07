---
geometry: margin=1in
---
# PROJECT Design Documentation

> _The following template provides the headings for your Design
> Documentation.  As you edit each section make sure you remove these
> commentary 'blockquotes'; the lines that start with a > character
> and appear in the generated PDF in italics._

## Team Information
* Team name: Toronto Maple Leafs
* Team members
  * Domenic Lo Iacono
  * Niccolls Evsseef
  * Claire Kreisel
  * Ming Creekmore

## Executive Summary

This project is a full stack application including a persistent storage, backend, and user interface. 
The goal of this application is to host a jersey store for the Toronto Maple Leafs. 
As an online store, the admin is able to manage the site by adding and removing jerseys, editing existing jerseys, and other site features. 
Furthermore, users that visit the site are able to browse through the jerseys available for sale, view specific jersey's attributes like cost, search 
for jerseys, and lastly add items to their shopping cart and buy the items in that cart. 

### Purpose
This website has an admin which is able to modify the stock of jerseys that are for sale by adding jerseys, removing jerseys, and editing existing jerseys. 
The website also has users which have a username and a shopping cart which is persistent after logout. Users are able to add and remove items from their shopping cart. 

### Glossary and Acronyms
> _Provide a table of terms and acronyms._

| Term | Definition |
|------|------------|
| Admin | Website owner with special privlidges related to the inventory of jerseys. |
| User | A customer that interacts with the website which logs into the website and has a username and shopping cart. |
| Jersey | The type of product that is being sold at the E-Store |
| SPA | Single Page |


## Requirements

This section describes the features of the application.

The application allows for the admin and user to log in and for new users to register for their account. 
It allows for the admin to preform the CRUD operations on a stock of jerseys to which the users will able to browse and view and add to their shopping cart. 
It allows for the user to add and delete items from their shopping cart and 'buy' the items once they are ready. 
The website also allows quick access of every product via a search bar. 

### Definition of MVP
The admin and users must be able to login with the users also able to register new accounts
The admin must be able to preform CRUD operations on the stock of jerseys which is persistently stored.
The user must be able to add, delete, and checkout their shopping cart which also must be persistently stored. 
The website must allow quick and easy access to the products with a postive user experince. 

### MVP Features and the names of their stories
Login Admin and its users
Register new users
Admin Add jerseys
Admin Delete jerseys
Admin Update jerseys
User Add to cart
User Delete from cart
User Checkout cart
Search jerseys and view


### Roadmap of Enhancements
Login Admin and its users
Register new users
Admin Add jerseys
Admin Delete jerseys
Admin Update jerseys
User Add to cart
User Delete from cart
User Checkout cart
Search jerseys and view


## Application Domain

This section describes the application domain.

![Domain Model](Domain_Analysis.png)


This is a Noun/Verb analysis relating to our domain model which is now represented in code through various classes. 

Nouns: 
  Jerseys
    Number
    Name
    Home/away
    Cost
    Size
      XS
      Small 
      Medium
      Large
      XL

  Store

  User
    Admin
    Customer

  Shopping Carts
    Number of items
    Total cost

  Sales/discounts

Verbs:
  Adding (Jerseys to store inventory and shopping cart)
  Editing (Jerseys to store inventory and shopping cart)
  Deleting (Jerseys to store inventory and shopping cart)
  Searching for jerseys
  Browsing the site/jerseys
  Checkout the shopping cart




## Architecture and Design

Using Spring boot, the backend has persistent storage and a consistent model of objects using the controller, model, and persistence setup. 
Jerseys and Users have their respective controller, model, and persistence files which allow for operations and storage through JSON files. 
The frontend is built using angular and through the jersey.service.ts and user.service.ts files are able to access the backend data and functions
in order to deliver a UI that can support persistent storage. 

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

The e-store web application, is built using the Model–View–ViewModel (MVVM) architecture pattern. 

The Model stores the application data objects including any functionality to provide persistance. 

The View is the client-side SPA built with Angular utilizing HTML, CSS and TypeScript. The ViewModel provides RESTful APIs to the client (View) as well as any logic required to manipulate the data objects from the Model.

Both the ViewModel and Model are built using Java and Spring Framework. Details of the components within these tiers are supplied below.


### Overview of User Interface

This section describes the web interface flow; this is how the user views and interacts
with the e-store application.

The user and admin have almost identical flows through the application. The user is only different than that of the admin by the fact that they are unable to see the CRUD operation buttons when viewing a jersey
Both land on a login page when visiting the site. 
Upon login after registration if neccessary, they are directed to a page that displays the stock of jerseys that can be filtered using the search bar, a shopping cart icon, and the username of whoever is currently logged in. 
From here they are able to view jerseys and preform their authorized actions upon them. 


### View Tier
> _Provide a summary of the View Tier UI of your architecture.
> Describe the types of components in the tier and describe their
> responsibilities.  This should be a narrative description, i.e. it has
> a flow or "story line" that the reader can follow._

> _You must also provide sequence diagrams as is relevant to a particular aspects 
> of the design that you are describing.  For example, in e-store you might create a 
> sequence diagram of a customer searching for an item and adding to their cart. 
> Be sure to include an relevant HTTP reuqests from the client-side to the server-side 
> to help illustrate the end-to-end flow._


### ViewModel Tier
> _Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._

> _At appropriate places as part of this narrative provide one or more
> static models (UML class diagrams) with some details such as critical attributes and methods._


### Model Tier
> _Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._

> _At appropriate places as part of this narrative provide one or more
> static models (UML class diagrams) with some details such as critical attributes and methods._

### Static Code Analysis/Design Improvements
> _Discuss design improvements that you would make if the project were
> to continue. These improvement should be based on your direct
> analysis of where there are problems in the code base which could be
> addressed with design changes, and describe those suggested design
> improvements._

> _With the results from the Static Code Analysis exercise, 
> discuss the resulting issues/metrics measurements along with your analysis
> and recommendations for further improvements. Where relevant, include 
> screenshots from the tool and/or corresponding source code that was flagged._

## Testing
> _This section will provide information about the testing performed
> and the results of the testing._

### Acceptance Testing
> _Report on the number of user stories that have passed all their
> acceptance criteria tests, the number that have some acceptance
> criteria tests failing, and the number of user stories that
> have not had any testing yet. Highlight the issues found during
> acceptance testing and if there are any concerns._

### Unit Testing and Code Coverage
> _Discuss your unit testing strategy. Report on the code coverage
> achieved from unit testing of the code base. Discuss the team's
> coverage targets, why you selected those values, and how well your
> code coverage met your targets. If there are any anomalies, discuss
> those._
