# GymMembersApp
Assignment Instructions: Create an application used to register new gym members.
The application should allow the user to add GymMembers(Name, Id, Plan) stored in the app using external storage.
The application should display each user in a RecyclerView.
When the user clicks on a member the application should open a new activity displaying the particular members' information in the activity.
-Application consists of 3 activities
Activity 1 displays the members and has a button which leads to a Activity 2 to insert members
Activity 3 is used to display a single users' information

Functionality: A list of members is diplayed on the main activity. When one of the members are clicked detailed information about that member is shown in a new activity. The button at the bottom of the main activity is used to create new members in another activity. These new members are then populated on the main activty view.

Implementation: A recycler view is used to display all the members of the gym. Using onClick listener on each item of the RecyclerView. The application then takes the user to another acticity where detailed information is shown for each of the users. On the main page a button is located at the bottom that taked the user to a registration page. Using intents the id of the member is passed to the registration activty and then all the data is passed back with a single string with ',' delimters. When passed back the data is stored to an external storage and then read to update the main RecyclerView.
