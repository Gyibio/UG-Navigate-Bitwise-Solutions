# UG Navigate: Advanced Campus Route Optimization System

## Overview
UG Navigate is a Java application for intelligent route optimization on the University of Ghana campus. It demonstrates advanced algorithms, real-world problem solving, and modern features.

## Features

- **Multi-Algorithm Pathfinding**
	- Dijkstra’s Algorithm: Shortest path by distance
	- Fastest Route: Time-based pathfinding with simulated traffic
	- A* Search: Heuristic-based optimal routing
	- Minimum Spanning Tree (MST): Campus-wide connectivity

- **Traffic Simulation**
	- Time-based congestion factors (peak hours increase travel time)

- **Accessibility Support**
	- Each building/landmark has an accessibility rating (1-5)
	- Ratings displayed in building lists

- **Landmark Search**
	- Fast binary search for landmark lookup by name

- **Input Validation & Output Formatting**
	- Robust input checks for user entries
	- Tabular display of buildings and results

## How to Run

1. Compile:
	 ```
	 javac -d bin src/app/UGNavigate.java src/model/*.java
	 ```
2. Run:
	 ```
	 java -cp bin app.UGNavigate
	 ```

## Example Usage

- View all buildings and their accessibility ratings
- Find shortest or fastest route between locations
- See MST for campus connectivity
- Search for a landmark by name

## Extensibility

- Easily add new algorithms or features
- Adaptable for file input, weather, or accessibility enhancements
