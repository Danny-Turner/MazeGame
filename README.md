# MazeGame

Puzzle Maze Game Use Case
Dan Turner

PURPOSE
Goal: Develop a timed, single player, puzzle maze ball game using the Android OS

Scope:
In Scope	Out of Scope
Single player game	Multi-player version
High score table	3D graphics

Actors and Goals:
1.	Player (Primary Actor): Wants to play a maze puzzle game on their mobile device
2.	Program: Allows player to play maze puzzle game using the accelerometer


USE CASES

Use Case 1 – Start the App
Precondition: App installed on the device
Trigger: Player starts the App 
Minimal Guarantee: Program closes and exits on failure. Game progress is not saved on failure.
Success Guarantee: Program starts, shows the high scores, and allows Player to Select Maze.
Main Success Scenario:
1)	Player: Starts the app
2)	Program:  Starts on main screen which displays:
•	Top 10 times/names sorted in increasing order
•	Select Maze

Use Case 2 – Select Maze
Precondition: App is running and at main screen (Start the App)
Trigger: Player selects Select Maze 
Minimal Guarantee: Program closes and exits on failure. Game progress is not saved on failure.
Success Guarantee: Player selects a Maze and the Program proceeds to Begin Game.
Main Success Scenario:
1)	Player: Selects a maze
2)	Program: Proceeds to Begin Game with the selected maze


Use Case 3 – Begin Game
Precondition: App is running and at main screen (Start the App)
Trigger: Player selects Begin Game 
Minimal Guarantee: Program closes and exits on failure. Game progress is not saved on failure.
Success Guarantee: Program draws maze, places the ball, and starts a timer.
Main Success Scenario:
3)	Player: Selects Begin Game
4)	Program:  Displays game screen:
•	Draws a maze
•	Places the ball on the left edge of the maze
•	Places an exit on the right edge of the screen
5)	Program: Starts timer




Use Case 4 – Move Ball
Precondition: App running and game in progress
Trigger: Player tilts the device 
Minimal Guarantee: Program closes and exits on failure. Game progress is not saved on failure.
Success Guarantee: Ball moves in direction the device is tilted
Main Success Scenario:
1)	Player: Tilts the device
2)	Program:  Validates move will not cause ball to collide with maze, and then moves the ball
•	Ball moves in direction of tilt
•	Distance moved increases proportional to amount the device is tilted
Extensions:
	2a) Move would result in collision with maze wall
		.1 The ball does not move in direction perpendicular to maze wall
		.2 The ball will move in direction parallel to maze wall


Use Case 5 – End Game
Precondition: App running and game in progress
Trigger: Player maneuvers the ball to the exit
Minimal Guarantee: Program closes and exits on failure. Game progress is not saved on failure.
Success Guarantee: Game ends and displays main screen (Start the App)
Main Success Scenario:
1)	Player: Maneuvers the ball to the exit 
2)	Program: Validates ball is at exit, stops timer, and validates that time is not a high score
3)	Program: Displays main screen  (Start the App)
Extensions:
	2a) Time is a high score (One of 10 fastest times)
		.1 Program prompts player to Enter High Score


Use Case 6 – Enter High Score
Precondition: The app is running and the Player has completed maze
Trigger: Player has completed a maze (End Game) and has one of the 10 fastest times 
Minimal Guarantee: Program closes and exits on failure. Game progress is not saved on failure.
Success Guarantee: Player enters name to display on high score table
Main Success Scenario:
1)	Player: Enters name
2)	Program: Saves name and time to high scores
3)	Program: Displays main screen  (Start the App)
