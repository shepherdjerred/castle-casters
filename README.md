# engine
[![Build Status](https://travis-ci.com/harding-capstone/engine.svg?branch=master)](https://travis-ci.com/harding-capstone/engine)
[![Coverage Status](https://coveralls.io/repos/github/harding-capstone/engine/badge.svg?branch=master)](https://coveralls.io/github/harding-capstone/engine?branch=master)

The game of Quoridor with a medevial fantasy theme. Created for the Harding University department of computer science capstone class. Cross-platform, tested on macOS and Windows.

# Foreward
This is pretty bad. Don't judge me too harshly. Over the course of ten weeks I wrote this project. For the first time ever I dealt with graphics rendering, low-level networking, audio playback, game engine design, practical AI programming, and project management.

The core game logic is absolutely solid and very extensible. It's by far the best module of this project. The AI had great potential, but priority was shifted away from it to work on the UI. With a little more work we could've greatly improved its performance, or even worked on an approach using AlphaZero. Networking is absolute trash and should've been architected better (I've spent a lot of time since releasing this thinking about better solutions).

The engine itself is pretty rough, but I'm pretty proud of it considering that it was written from scratch in about a month. The font, map, and sprite rendering were all written in the course of about a week, and it was my first time doing something like this.

One huge caveat of the engine is that it doesn't (easily) support adding game objects to scenes. That means that every button, sprite, tile, etc. must be defined in the game scene before the scene can be rendered. This was a huge hurdle when implementing some of the scenes, and a shortcoming of how I designed the engine.

The maps are really cool though! By using the Tiled application you can create your own maps and easily import them into the game.

This game would be a lot better if I had another month or two to spend on it. Then again the huge push of effort came due to the deadline, so maybe it'd only be a marginal improvement.

I learned an incredible amount despite the years of my life that I lost stressing over this project. Maybe one day I'll revisit it, or perhaps get into game engine design.

- Jerred Shepherd, Nov 30, 2019

# Technologies Used
* Java 11
* lwjgl
  * OpenGL 3.2
  * OpenAL
  * GLFW
  * stb
* Netty 4
* Jenetics
* GSON
* Lombok

# Building
This project uses maven to manage dependencies and the build process. When this project was actively developed there was a dedicated maven repository that served the dependencies written for this project, but it is now down. You must compile all of these projects yourself before compiling the engine. The source for these projects are located in this organization.

![Main Menu](https://i.imgur.com/1UupO6R.png)

![Game Screen - Desert](https://i.imgur.com/c0heRpI.png)

![Game Screen - Grass](https://i.imgur.com/xLbDBcI.png)
