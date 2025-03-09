## Introduction

Another assignment, another report. For this one, we basically have to create some sort of task manager (not to be confused with CMD), sort of like a to do list. We have to use arrays, stacks, and linked lists as data structures for the tasks. Arrays and Linked Lists are where the tasks are stored, and stacks are where each task are marked as either unfinished or finished. 

## Environment Setup

- JDK (Java Development Kit) Version 23.0.2 
- Visual Studio Code as my IDE (Integrated Development Environment) because my laptop is a potato and can't handle any other ones.
- Git as a way to push files to Github

## My approach to each data structure

**- Arrays**
This is the simplest once. Since array sizes are fixed, we can just have a pre-determined list of n tasks that can be updated where each task can be updated whenever we want by just changing the values in n index. It gets a bit complicated though when stacks are involved, which are used for applying and reversing completion status.

**-Stacks**
Stacks are where we mark a task as complete. If a task is in the stack, it is finished. This is made easier by using Java's java.util.Stack import. At first, I tried storing the task's name itself in the stack, but it quickly backfired by applying a finished status to all tasks with that name (including duplicate tasks), rather than one specific task. I changed this by storing the task's index since all indexes are unique.

**-Linked List**
Linked List wasn't all that hard either since all I did was copy the code for the methods in Arrays and change the variables a bit and added a remove feature + extra error handling.

## Challenges

No assignment is free of challenges and errors. Here are some I encountered:

- There are so many places where an error can occure. It could be either an out of bounds index or a wrong input mismatch. I asked **ChatGPT** for improvements on the index out of bounds side and it was just comparing it by structure size and 0, which didn't occure to me since I was really worn out or sick when writing this code. Wrong input mismatch is fixed by me just recycling the Check_Int for the scanner code in my previous assignment, but turned it into a method instead

- Trying to complete a task that has the same name as another task in the array or linked list will say (Finished) on both of them, even if I wanted to only complete 1. This is fixed by rather copying the task to the stack, we used the index itself since the index will always be unique.

- Using indexes as the completion status breaks the previous LinkedList code that previously checks if a task is in the stack when removing it. Using indexes mean that all tasks after the removed task has their index shifted to the left, which doesn't correspond with the index inside the stack. I just asked ChatGPT to fix this since I'm too tired to code.
