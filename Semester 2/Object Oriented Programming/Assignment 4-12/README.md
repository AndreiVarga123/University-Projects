****************************************************3-4
# Assignment 02-03

## Requirements
- Each student will be given one of the problems below.
- The solution must use the **C language**.
- The problem should be solved over 2 iterations, due `Week 3` and `Week 4`:

### Week 3
- Solve at least requirements **a** and **b**.
- The vector used in the repository can be statically allocated.

### Week 4
- Solve all problem requirements.
- Define a vector structure with specific operations using a dynamically allocated array.
- Use modular programming.
- Source code must be specified and include tests for all non-UI functions
- The program must not leak memory!
- Use a layered architecture for your application (domain, repository, controller, UI). User interface, domain and data access elements will be stored in different modules. The user interface module will only contain the user interface part.
- Have at least 10 entries available at application startup.
- Handle user input errors gracefully (replace program crashes with nice error messages :blush:).

## Bonus possibilities
1. Implement the following requirements using function pointers **[deadline: week 4, bonus: 0.1p]**:
    - For requirement **b**, add a different type of filtering (of your choice).
    - For requirement **c**, add descending sorting. The user should choose the type of sort and the program will show the list of entities accordingly.
2. Provide 2 different implementations for the undo/redo functionality: one using a list of operations (this approach is a precursor of the [Command design pattern](https://en.wikipedia.org/wiki/Command_pattern)) and one where each state of the repository is recorded (this approach is not unlike the [Memento design pattern](https://en.wikipedia.org/wiki/Memento_pattern)). Implement your dynamic array generically, such that it can contain any type of elements (use void*). Use this structure for your repository, as well as to implement both undo/redo functionalities **[deadline: week 5, bonus: 0.1p]**.

## Problem Statements

### Pharmacy
John is the administrator of the *“Smiles”* Pharmacy. He needs a software application to help him manage his pharmacy's medicine stocks. Each **Medicine** has the following attributes: `name`, `concentration`, `quantity` and `price`. John wants the application to help him in the following ways:\
**(a)** Add, delete or update a medicine. A medicine is uniquely identified by its name and concentration. If a product that already exists is added, its quantity will be updated (the new quantity is added to the existing one).\
**(b)** See all available medicines containing a given string (if the string is empty, all the available medicines will be considered), sorted ascending by medicine name.\
**(c)** See only those medicines that are in short supply (quantity less than `X` items, where the value of `X` is user-provided).\
**(d)** Provide multiple undo and redo functionality. Each step will undo/redo the previous operation performed by the user.

### Bakery
Mary runs her family's bakery, *“Bread'n Bagel”*. Every day she struggles with keeping up to date with available stocks of raw materials and would like a program to help her manage the business more effectively. Each **Material** used in the bakery must have: a `name`, a `supplier`, a `quantity` and the `expiration date`. Mary wants a software application that helps her in the following ways:\
**(a)** Add, delete and update a material. A raw material is uniquely identified by its name, supplier and expiration date. If a material that already exists is added, its quantity will be modified (the new quantity is added to the existing one).\
**(b)** See all available materials past their expiration date, containing a given string (if the string is empty, all materials past their expiration date will be considered).\
**(c)** Display all materials from a given supplier, which are in short supply (quantity less than a user-provided value), sorted ascending by their quantities.\
**(d)** Provide multiple undo and redo functionality. Each step will undo/redo the previous operation performed by the user.

### Tourism Agency
The employees of *“Happy Holidays”* need an application to manage the offers that the agency has. Each **Offer** has a `type` (one of `seaside, mountain or city break`), a `destination`, a `departure date` and a `price`. The employees need the application to help them in the following ways:\
**(a)** Add, delete and update an offer. An offer is uniquely identified by its destination and departure dates.\
**(b)** Display all tourism offers whose destinations contain a given string (if the string is empty, all destinations are considered), and show them sorted ascending by price.\
**(c)** Display all offers of a given type, having their departure after a given date.\
**(d)** Provide multiple undo and redo functionality. Each step will undo/redo the previous operation performed by the user.

### Real Estate Agency
Evelyn owns a real estate agency. Being also the only employee, she needs an application to help her manage all the real estates of her clients. Each **Estate** has a type (one of `house, apartment or penthouse`), an `address`, a `surface` and a `price`. Evelyn needs the application to help her in the following ways:\
**(a)** Add, delete or update an estate. An estate is uniquely identified by its address.\
**(b)** Display all estates whose address contains a given string (if the string is empty, all estates will be considered), sorted ascending by their price.\
**(c)** See all estates of a given type, having the surface greater than a user provided value.\
**(d)** Provide multiple undo and redo functionality. Each step will undo/redo the previous operation performed by the user.

### Intelligent Refrigerator
The company *“Home SmartApps”* have decided to design a new intelligent refrigerator. Besides the hardware, they need a software application to manage the refrigerator. Each **Product** that the fridge can store has a `name`, a `category` (one of `dairy, sweets, meat or fruit`), a `quantity` and an `expiration date`. The software application will provide the following functionalities:\
**(a)** Add, delete or update a product. A product is uniquely identified by name and category. If a product that already exists is added, its quantity will be updated (the new quantity is added to the existing one).\
**(b)** Display all products whose name contains a given string (if the string is empty, all products from the refrigerator are considered), and show them sorted ascending by the existing quantity.\
**(c)** Display all products of a given category (if the category is empty, all types of food will be considered) whose expiration dates are close (expire in the following `X` days, where the value of `X` is user-provided).\
**(d)** Provide multiple undo and redo functionality. Each step will undo/redo the previous operation performed by the user.

### World Population Monitoring
The World Population Monitoring Organisation needs an application to help keep track of countries’ populations. Each **Country** has a unique `name`, the `continent` it belongs to (one of `Europe, America, Africa, Australia or Asia`), and a population (stored in millions).  The employees of the organisation need the application to help them in the following ways:\
**(a)** Add, delete or update a country. Updating must also consider the case of migration: a given number of people leave one country to migrate to another.\
**(b)** Display all countries whose name contains a given string (if the string is empty, all the countries should be considered).\
**(c)** Display all countries on a given continent (if the continent is empty, all countries will be considered), whose populations are greater than a given value, sorted ascending by population.\
**(d)** Provide multiple undo and redo functionality. Each step will undo/redo the previous operation performed by the user.


******************************************************5-6
# Assignment 04-05
## Requirements
- You will be given one of the problems below to solve
- The application must be implemented in C++ and use layered architecture.
- Provide tests and specifications for non-trivial functions outside the UI. Test coverage must be at least 98% for all layers, except the UI
- Have at least 10 entities in your memory repository
- Validate all input data
- Handle the following situations:
    - If an entity that already exists is added, a message is shown and the entity is not stored. You must decide what makes an entity unique.
    - If the user tries to update/delete an entity that does not exist, a message will be shown and there will be no effect on the list of entities.

## Week 5
- Solve the requirements related to the administrator mode
- Define the `DynamicVector` class which provides the specific operations: `add`, `remove`, `length`, etc. The array of elements must be dynamically allocated

## Week 6
- Solve all problem requirements
- `DynamicVector` must be a templated class

## Problem Statements

### Keep calm and adopt a pet
The *“Keep calm and adopt a pet”* shelter needs a software application to help them find adoptive parents for the dogs  they are taking care of. The application can be used in two modes: `administrator` and `user`. When the application is started, it will offer the option to select the mode.\
**Administrator mode:** The application will have a database that holds all the dogs in the shelter at a given moment. The shelter employees must be able to update the database, meaning: add a new dog, delete a dog (when the dog is adopted) and update the information of a dog. Each **Dog** has a `breed`, a `name`, an `age` and a `photograph`. The photograph is memorised as a link towards an online resource (the photograph on the presentation site of the centre). The administrators will also have the option to see all the dogs in the shelter.\
**User mode:** A user can access the application and choose one or more dogs to adopt. The application will allow the user to:
- See the dogs in the database, one by one. When the user chooses this option, the data of the first dog (breed, name, age) is displayed, along with its photograph.
- Choose to adopt the dog, in which case the dog is added to the user’s adoption list.
- Choose not to adopt the dog and to continue to the next. In this case, the information corresponding to the next dog is shown and the user is again offered the possibility to adopt it. This can continue as long as the user wants, as when arriving to the end of the list, if the user chooses next, the application will again show the first dog.
- See all the dogs of a given breed, having an age less than a given number. If no breed is provided, then all the dogs will be considered. The functionalities outlined above apply again in this case.
- See the adoption list.

## Local Movie Database
So many movies, so little time. To make sure you do not miss any good movies, you absolutely need a software application to help you manage your films and create watch lists. The application can be used in two modes: administrator and user. When the application is started, it will offer the option to choose the mode.\
**Administrator mode:** The application will have a database which holds all the movies. You must be able to update the database, meaning: add a new movie, delete a movie and update the information of a movie. Each **Movie** has a `title`, a `genre`, a `year of release`, a `number of likes` and a `trailer`. The trailer is memorised as a link towards an online resource. The administrators will also have the option to see all the movies in the database.\
**User mode:** A user can create a watch list with the movies that she wants to watch. The application will allow the user to:
- See the movies in the database having a given genre (if the genre is empty, see all the movies), one by one. When the user chooses this option, the data of the first movie (title, genre, year of release, number of likes) is displayed and the trailer is played in the browser.
- If the user likes the trailer, she can choose to add the movie to her watch list.
- If the trailer is not satisfactory, the user can choose not to add the movie to the watch list and to continue to the next. In this case, the information corresponding to the next movie is shown and the user is again offered the possibility to add it to the watch list. This can continue as long as the user wants, as when arriving to the end of the list of movies with the given genre, if the user chooses next, the application will again show the first movie.
- Delete a movie from the watch list, after the user watched the movie. When deleting a movie from the watch list, the user can also rate the movie (with a like), and in this case, the number of likes the movie has in the repository will be increased.
- See the watch list.

## Proper Trench Coats
Trench coats are cool. Everyone should own a trench coat. The *“Proper Trench Coats”* store sells fashionable, elegant trench coats and the store needs software to allow their customers to order online. The application can be used in two modes: administrator and user. When the application is started, it will offer the option to choose the mode.\
**Administrator mode:** The application will have a database which holds available trench coats at a given moment. The store employees must be able to update the database, meaning: add a new trench coat, delete a trench coat (when it is sold out) and update the information of a trench coat. Each **Trench Coat** has a `size`, a `colour`, a `price`, a `quantity` and a `photograph`. The photograph is memorised as a link towards an online resource (the photograph on the presentation site of the store). The administrators will also have the option to see all the trench coats in the store.\
**User mode:** A user can access the application and choose one or more trench coats to buy. The application will allow the user to:
- See the trench coats in the database, having a given size, one by one. If the size is empty, then all the trench coats will be considered. When the user chooses this option, the data of the first trench coat (size, colour, price, quantity) is displayed, along with its photograph.
- Choose to add the trench to the shopping basket. In this case, the price is added to the total sum the user has to pay. The total sum will be shown after each purchase.
- Choose not to add the trench coat to the basket and to continue to the next. In this case, the information corresponding to the next trench coat is shown and the user is again offered the possibility to buy it. This can continue as long as the user wants, as when arriving to the end of the list, if the user chooses next, the application will again show the first trench coat.
- See the shopping basket and the total price of the items.

## Master C++
You are very passionate about programing (otherwise you wouldn't be reading this) and C++ is a language close to your heart. On your way to becoming a guru, you study a lot and watch many tutorials. To make sure you do not miss any good tutorials, you absolutely need a software application to help you manage your tutorials and create watch lists. The application can be used in two modes: administrator and user. When the application is started, it will offer the option to choose the mode.\
**Administrator mode:** The application will have a database , which holds all the tutorials. You must be able to update the database, meaning: add a new tutorial, delete a tutorial and update the information of a tutorial. Each **Tutorial** has a `title`, a `presenter` (name of the presenter person), a `duration` (minutes and seconds), a number of `likes` and a `link` towards the online resource containing the tutorial. The administrators will also have the option to see all the tutorials in the database.\
**User mode:** A user can create a watch list with the tutorials that he/she wants to watch. The application will allow the user to:
- See the tutorials in the database having a given presenter (if the presenter name is empty, see all the tutorials), one by one. When the user chooses this option, the data of the first tutorial (title, presenter, duration, number of likes) is displayed and the tutorial is played in the browser.
- If the user likes the tutorial, he/she can choose to add it to his/her tutorial watch list.
- If the tutorial seems uninteresting, the user can choose not to add it to the watch list and continue to the next. In this case, the information corresponding to the next tutorial is shown and the user is again offered the possibility to add it to the watch list. This can continue as long as the user wants, as when arriving to the end of the list of tutorials with the given presenter, if the user chooses next, the application will again show the first tutorial.
- Delete a tutorial from the watch list, after the user watched the tutorial. When deleting a tutorial from the watch list, the user can also rate the tutorial (with a like), and in this case, the number of likes for the tutorial will be increased.
- See the watch list.

## Life After School
Lectures, seminars and labs ... school in general must be taken seriously; BUT so must be your social life and leisure time. To manage the latter and be always informed about the interesting events happening in your city you will implement a software application. The application can be used in two modes: administrator and user. When the application is started, it will offer the option to choose the mode.\
**Administrator mode:** The application will have a database which holds all the interesting events in your area. You must be able to update the database, meaning: add a new event, delete an event and update the information of an event. Each **Event** has a `title`, a `description`, a `date and time`, a `number of people` who are going and a `link` towards the online resource containing the event. The administrators will also have the option to see all the events in the database.\
**User mode:** A user can create a list with the events that he/she is interested in. The application will allow the user to:
- See the events in the database for a given month (if there is no month given, see all the events, ordered chronologically), one by one. When the user chooses this option, the data of the first event (title, description, date and time, number of people who are going) is displayed, and the event is opened in the browser (e.g. Facebook event).
- If the user wants to participate in the event he/she can choose to add it to his/her events list. When this happens, the number of people who are going to the event increases (for the event in the repository).
- If the event seems uninteresting, the user can choose not to add it to the list and continue to the next. In this case, the information corresponding to the next event is shown, and the user is again offered the possibility to add it to his/her list. This can continue as long as the user wants, as when arriving to the end of the list of events for the given month, if the user chooses next, the application will again show the first event.
- Delete an event from the list. When the user deletes an event from his/her list, the number of people who are going to the event decreases.
- See the list of events the user wants to attend.




***************************************8-9

# Assignment 08-09
## Week 8
* Implement at least requirements **1**, **2** and **3**.
## Week 9
* Implement all requirements
* Requirements **4** and **5** must be implemented using inheritance and polymorphism.

## Bonus possibility (0.2p, deadline week 9)
In addition to the file-based implementation for the repository, implement a true database-backed repository. For this, use inheritance and polymorphism. You are free to choose any type of database management system (e.g. `MySQL`, `SQLite`, `PostgreSQL`, `Couchbase` etc.).

## Problem Statement
For your solution to the previous assignment (Assignment 05-06), add the following features:
1. Replace the templated DynamicVector with the STL vector. Use STL algorithms wherever possible in your application (e.g. in your filter function you could use `copy_if`, `count_if`). Replace all your for loops either with STL algorithms, or with C++11's ranged-based for loop.

2. Store data in a text file. When the program starts, entities are read from the file. Modifications made during program execution are stored in the file. Implement this using the `iostream` library. Create insertion and extraction operators for your entities and use these when reading/writing to files or the console.

3. Use exceptions to signal errors:
    - from the repository;
    - validation errors – validate your entities using Validator classes;
    - create your own exception classes.
    - validate program input.

4.	Depending on your assignment, store your (adoption list, movie watch list, shopping basket or tutorial watch list) in a file. When the application starts, the user should choose the type of file between `CSV` or `HTML`. Depending on the type, the application will save the list in the correct format.

    **Indications**\
    The CSV file will contain each entity on one line and the attributes will be separated by comma \
    The HTML file will contain a table, in which each row holds the data of one entity. The columns of the table will contain the names of the data attributes.\
    These are exemplified in the [example.csv](example.csv) and [example.html](example.html) files.
    `CSV` and `HTML` files are used to save and display data to the user; they act as program outputs, so data should not be read from them!

5. Add a new command, allowing the user to see the:
    * adoption list
    * movie watch list
    * shopping basket
    * tutorial watch list\
Displaying the list means opening the saved file (`CSV` or `HTML`) with the correct application (`CSV` files using Notepad, Notepad++, Microsoft Excel etc. and `HTML` files using a browser)

6. Create a UML class diagram for your entire application. For this, you can use any tool that you like ([StarUML](https://staruml.io/) or [LucidChart](https://www.lucidchart.com/) are only some examples. Many other options exist.

****************************11-12

# Assignment 11-12

## Requirements
- Create a graphical user interface using the Qt framework for the problem you have been working on (A5-6, A8-9).

## Week 11
- Implement the interface design (location and size of GUI widgets, without attached functionalities), without using the Qt Designer.
- The list or table displaying the repository entities in administrator mode should be populated using an input file. 

## Week 12 
-	All functionalities must be available through the GUI. You may use Qt Designer, if you want to change the initial design of your GUI.
-	The functionality of the application must be the same (including the one-by-one iteration of objects for the user mode).

## Bonus possibility (0.2p, deadline week 12)
Create a graphical representation of the data in your application. You have an example below: a bar chart representing the number of songs for each artist. Your representations can be a bar chart, a pie chart or another type of chart. You can even use circles or rectangles or any other geometric shapes to represent the data.

Hint: You can use QPainter (https://doc.qt.io/qt-6/qpainter.html), QGraphicsScene (https://doc.qt.io/qt-6/qgraphicsscene.html) or a special widget designed for plotting and data visualisation – QCustomPlot (http://www.qcustomplot.com/).

<img width="704" alt="Screenshot 2021-04-17 at 20 24 04" src="https://user-images.githubusercontent.com/25611695/115121335-df0f7e00-9fba-11eb-8839-40cd55da1d69.png">



***************************************************14

# Assignment 14

## Requirements
1. Add multiple *undo* and *redo* functionality for the `add`, `remove`, and `update` operations. Implement this functionality using inheritance and polymorphism. You will have **Undo** and **Redo** buttons on the GUI, as well as a key combination to undo and redo the operations (e.g. `Ctrl+Z`, `Ctrl+Y`).

2. Show the contents of the `adoption list` / `movie watch list` / `shopping basket` / `tutorial watch list` using a table view. You must use the [Qt View/Model](https://doc.qt.io/qt-5/modelview.html) components (`QTableView`). Create your own model – a class which inherits from [`QAbstractTableModel`](https://doc.qt.io/qt-5/qabstracttablemodel.html). This window will be opened from your GUI main window.

## Bonus Possibility [0.1p]
Add multiple *undo* and *redo* functionality for the `adoption list` / `movie watch list` / `shopping basket` / `tutorial watch list`. This will be tested through the application's GUI.

## Bonus Possibility [0.1p]
Use custom Qt delegates. In one of the columns of the Qt table view that shows the elements of the `adoption list` / **etc...**, display an image of the dog, trench coat or a play button that plays the movie trailer or the tutorial - depending on the problem statement. See the example images below.

![image](https://user-images.githubusercontent.com/25611695/119180503-0bfef700-ba79-11eb-86ae-3a42d41bb437.png)
![image](https://user-images.githubusercontent.com/25611695/119180582-2507a800-ba79-11eb-921c-22f64a05522b.png)
