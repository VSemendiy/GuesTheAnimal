# GuesTheAnimal
In this project, I've created a simple interactive game where the computer will try to guess the animal that the person has in mind with the help of yes or no questions. During the game, the computer will extend its knowledge base by learning new facts about animals and using this information in the next game (all knowledges are supposed to be stored in a form of a binary tree and kept on HDD in one of the following formats: JSON, XML, YAML).

The computer greets the user based on the time of day.    

After greeting the user, if there is no data from the previews sessions on teh disk, the program asks user about his favorite animal. This animal will become the basis of the newly created knowledge base. The program should then offer the user to play. If a knowledge tree already exists, the computer loads it, greets the user as an expert system in animals, and offers the user a menu. The menu has to include at least these five items:

- Play the guessing game
- List of all animals
- Search for an animal
- Calculate statistics
- Print the Knowledge Tree.    
The game is multilingual. If your locale is Esperanto, the program will ask and answer in a language you understand.    
Example (note that greater-than symbol is not part of the input and only represents the user input):
```
Good evening!

I want to learn about animals.
Which animal do you like most?
> cat

Welcome to the animal expert system!

What do you want to do:

1. Play the guessing game
2. List of all animals
3. Search for an animal
4. Calculate statistics
5. Print the Knowledge Tree
0. Exit
> 1
You think of an animal, and I guess it.
Press enter when you're ready.
>
Is it a cat?
> No
I give up. What animal do you have in mind?
> a shark
Specify a fact that distinguishes a cat from a shark.
The sentence should be of the format: 'It can/has/is ...'.
> It is a mammal
Is this fact correct for the shark?
> No
Wonderful! I've learned so much about animals!
Do you like to play again?
> No
What do you want to do:

1. Play the guessing game
2. List of all animals
3. Search for an animal
4. Calculate statistics
5. Print the Knowledge Tree
0. Exit
> 0
Thank you and goodbye!
```
