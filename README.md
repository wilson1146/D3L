# D3L
## This project was created for my Mobile App Developement Class. It is a basic clone of the course's website (D2L). It uses the Bouquet library (https://github.com/GRizzi91/bouquet) to handle PDF rendering. The project uses Firebase for user authentication.



Features: 
Nested dynamic page generation implemented inside of a LazyScroll,
User Registration and Authentication through Firebase,
In app local PDF viewing

Instructions for use:
Either setup your own firebase authentication service in firebases console or change the startDestination (located on line 29 of NavGraph.kt) from "login_screen" to "home_screen" if you would like to bypass the login and signup functionality.
