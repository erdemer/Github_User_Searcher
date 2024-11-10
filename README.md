# GitHub User Search Android App

This Android app is a small-scale project utilizing the GitHub API to search, display, and manage user profiles. It demonstrates a clean architecture and uses essential Android development libraries and tools for building scalable applications.

## Features

	•	User Search Page: Users can search for GitHub users by username.
	•	User Listing Page: Displays a list of users from the search results, including an option to mark users as favorites.
	•	User Detail Page: Shows detailed information about the selected user, with an option to mark them as a favorite.
	•	Favorite Synchronization: When a user is marked as a favorite, the favorite status reflects across both the list and detail pages.

## Requirements

	•	GitHub API Services: The app uses GitHub API for fetching user data.
	•	Search Endpoint: GET search/users
	•	Detail Endpoint: GET users/{login}

 ## Technologies Used

This project utilizes the following technologies and libraries:

	•	MVVM Clean Architecture: For separating concerns and ensuring scalability.
	•	ViewBinding: For safer and easier access to view elements.
	•	Hilt: For dependency injection.
	•	Navigation Component: To handle in-app navigation between screens.
	•	Coroutines: For asynchronous operations.
	•	Room Database: Used to cache user list and detail data for offline support.
	•	LiveData/Flow: For reactive data handling.

 ## Usage

	•	Search for Users: Enter a GitHub username to search for users.
	•	View User Details: Select a user from the list to see more details.
	•	Mark as Favorite: Use the favorite icon to mark/unmark users. This status will persist between the list and detail pages.
