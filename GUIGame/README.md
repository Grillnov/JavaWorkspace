#Created by Bowen
#See the comments of the code for further information

#WTF is this game
A simulation of the famous game http://www.4399.com/flash/140894_3.htm
One of the local Huskies just broke out of his home! We gotta stop that silly dog before he gets away!

#How to start the game
Load the project with Eclipse® Java Mars® and hit the run button, or simply launch it with console command lines:(./is the project's root directory.)

cd ./bin
java GUIMainbody

Two copies of the icons are present, for Eclipse's using a strange strategy to determine the relative paths.

#How to play it
Press the buttons to place a roadblock at a desired location.
Don't let the Husky reach the border or you'll fail!
If you make it to round him up with roadblocks, he'll find himself nowhere to go and have to surrender. He's a good dog though.
Note that the Husky has only four directions to go at a time, less than the original game(6 directions) so it should be hella easier to beat this game.

#The gaming AI and GUI logics
Since the Husky's from siberia, he knows exactly how to choose optimal path to save his energy. So he'll just traverse the entire map using BFS search to find a shortest path to the border of the map.
See the comments embedded in the code for further information about AI and GUI design.

#What project file is this
Created with Eclipse®, Java Mars®