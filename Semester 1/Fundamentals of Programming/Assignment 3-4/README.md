## Requirements
- You will be given one of the problems below to solve
- Use procedural programing and the simple feature-driven software development process
- Provide a command-based console user interface that accepts given commands **exactly** as stated
- Handle the case of incorrect user input by displaying error messages. The program must not crash!
- Use built-in compound types to represent entities in the problem domain and access/modify them using *getter* and *setter* functions
- Have at least 10 items in your application at startup
- Provide **specification** and **tests** for all non-UI functions related to every functionality

## Problem Statements
### 1. Numerical List
A math teacher needs a program to help students test different properties of complex numbers, provided in the `a+bi` form (we assume `a` and `b` are integers for simplicity). Write a program that implements the functionalities exemplified below:

**(A) Add a number**\
`add <number>`\
`insert <number> at <position>`\
e.g.\
`add 4+2i` – appends `4+2i` to the list\
`insert 1+1i at 1` – insert number `1+i` at position `1` in the list (positions are numbered starting from `0`)

**(B) Modify numbers**\
`remove <position>`\
`remove <start position> to <end position>`\
`replace <old number> with <new number>`\
e.g.\
`remove 1` – removes the number at position `1`\
`remove 1 to 3` – removes the numbers at positions `1`,`2`, and `3`\
`replace 1+3i with 5-3i` – replaces all occurrences of number `1+3i` with the number `5-3i`

**(C) Display numbers having different properties**\
`list`\
`list real <start position> to <end position>`\
`list modulo [ < | = | > ] <number>`\
e.g.\
`list` – display the list of numbers\
`list real 1 to 5` – display the real numbers (imaginary part `=0`) between positions `1` and `5`\
`list modulo < 10` – display all numbers having modulo `<10` \
`list modulo = 5` – display all numbers having modulo `=5`


# 💻 Assignment 4
## New Requirements
- Continue the problem statement started at **A3** by adding its code to this repository
- Implement **modular programming** by having a **UI** module, a **Functions** module and a **Start** module
## Common with A3
- Use procedural programing and the simple feature-driven software development process
- Provide a console-based user interface that accepts given commands **exactly** as required, and works when additional whitespaces are included
- Use built-in compound types to represent entities in the problem domain and access them using *getter* and *setter* functions
- Have at least 10 items in your application at startup
- Provide **specification** and **tests** for all non-UI functions related to every functionality

## Problem Statements
### 1. Numerical List
A math teacher needs a program to help students test different properties of complex numbers, provided in the `a+bi` form (we assume `a` and `b` are integers for simplicity). Write a program that implements the functionalities exemplified below:

**(A) Add a number**\
`add <number>`\
`insert <number> at <position>`\
e.g.\
`add 4+2i` – appends `4+2i` to the list\
`insert 1+1i at 1` – insert number `1+i` at position `1` in the list (positions are numbered starting from `0`)

**(B) Modify numbers**\
`remove <position>`\
`remove <start position> to <end position>`\
`replace <old number> with <new number>`\
e.g.\
`remove 1` – removes the number at position `1`\
`remove 1 to 3` – removes the numbers at positions `1`,`2`, and `3`\
`replace 1+3i with 5-3i` – replaces all occurrences of number `1+3i` with the number `5-3i`

**(C) Display numbers having different properties**\
`list`\
`list real <start position> to <end position>`\
`list modulo [ < | = | > ] <number>`\
e.g.\
`list` – display all numbers\
`list real 1 to 5` – display the real numbers (imaginary part `=0`) between positions `1` and `5`\
`list modulo < 10` – display all numbers with modulo `<10`\
`list modulo = 5` – display all numbers with modulo `=5`

**(D) Obtain different characteristics of sub-lists**\
`sum <start position> to <end position>`\
`product <start position> to <end position>`\
e.g.\
`sum 1 to 5` – display the sum of the numbers between positions `1` and `5`\
`product 1 to 5` - display the product of numbers between positions `1` and `5`

**(E) Filter the list**\
`filter real`\
`filter modulo [ < | = | > ] <number>`\
e.g.\
`filter real` – keep only numbers having imaginary part `=0`\
`filter modulo < 10` – keep only numbers having modulo `<10`\
`filter modulo > 6` – keep only those numbers having modulo `>6`

**(F) Undo**\
`undo` – the last operation that modified program data is reversed. The user can undo all operations performed since program start by repeatedly calling this function.
