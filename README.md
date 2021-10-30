# Overview

For this project I wanted to learn how to use cloud databases, and how to integrate them with an Android application. Almost all apps use databases and I felt it was important for me to learn how to use them.

I wrote an app where a user can insert members into Firebase, they can also delete members, or edit them. They can also insert available timeslots for a given member. Then the user can view the members stored in Firebase, and they can display the availabilities for any of the members.

My purpose for writing this software was to provide users who work in groups to find available times for their group members. This would make coordinating meeting times much easier. I felt that an app using Firebase would be a user friendly platform.

{Provide a link to your YouTube demonstration.  It should be a 4-5 minute demo of the software running, a walkthrough of the code, and a view of the cloud database.}

[Software Demo Video](http://youtube.link.goes.here)

# Cloud Database

I am using Firebase with Realtime Database.

I have a table for members which stores a member's first and last names along with their group numbers. I also have a table storing available times for members.

# Development Environment

* Android Studio
* Firebase (Realtime Database)
* Kotlin

# Useful Websites

* [Firebase Documentation](https://firebase.google.com/docs/firestore/)

# Future Work

* Add a page/feature to find common times for a team.
* Add error handling for incorrect input.
* I would also like to add signup and signin pages with login verification.
* I would like to adjust the style of the app.
