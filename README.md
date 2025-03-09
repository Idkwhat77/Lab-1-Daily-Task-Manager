## Introduction

Another assignment, another report. For this one, we basically have to create some sort of task manager (not to be confused with CMD) sort of like a to do list. We have to use arrays, stacks, and linked lists as data structures for the tasks.

## Environment Setup

- JDK (Java Development Kit) Version 23.0.2 
- Visual Studio Code as my IDE (Integrated Development Environment) because my laptop is a potato and can't handle any other ones.
- Git as a way to push files to Github

## Challenges

No assignment is free of challenges and errors. Here are some I encountered:

- There are so many places where an error can occure. It could be either an out of bounds index or a wrong input mismatch. I asked **ChatGPT** for improvements on the index out of bounds side and it was just comparing it by structure size and 0, which didn't occure to me since I was really worn out or sick when writing this code. Wrong input mismatch is fixed by me just recycling the Check_Int for the scanner code in my previous assignment, but turned it into a method instead
- Trying to complete a task that has the same name as another task in the array or linked list will say (Finished) on both of them, even if I wanted to only complete 1.
