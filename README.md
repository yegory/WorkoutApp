# Workout Tracker Application

---

Implementation of save/load using Json, JsonWriteTests and JsonReadTests inspired by: 
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

---

## What will the app do?

The general idea of this application is to hopefully help people of different physique goals and experience to create
their *ideal** workout routine. This application will have:

* Some pre-made exercises (a glossary of these exercises will be made, where a description of each exercise is
  presented)
* Some pre-made workouts (for example, a full-body workout, 3 day split workout [chest day + back day + leg day],
  stretches, endurance workout, strength workout, etc.)
* the functionality of adding a custom exercise not included with the pre-made exercises, adding a description for it.
* the ability to rate the workouts (perhaps different categories like difficulty, fat burn, does it require a lot of
  equipment?)

> Disclaimer:
> *Although my ambition with this application is to give the person enough room to customize their routine, ultimately the range of customization will be limited and due to this being worked on solely by myself, thus some may find it useful while others may not. Nevertheless, this is something I thought would be fun to make.

---

## Who will use it?

The final application **could potentially be used by anyone** (assuming enough pre-made workout programs are made),
however, since the time is limited for this project, the design/features will be limited. Furthermore
I need to use a different data structure to store information depending on what type of exercise, e.g.: you cannot define
a 30 minute on an elliptical machine in terms of reps, sets, rest time. The reps/sets/rest time would mainly only fit for gym splits,
and even then this is quite limiting. A better application would address this issue.

That being said, given that an individual has previous gym experience, they can definitely find this application useful
to some extent in managing their workout planning.
---

## Why is this project of interest to me?

In the summer, when I was 16, I went to the gym for the first time ever. I had little to no knowledge about any
exercises, I was overwhelmed. I learned my way through educating myself to YouTube channels like ATHLEAN-X, watching a
bunch of bodybuilder gym-vlogs, vids of powerlifting meets, and science based videos on nutrition. Gym was almost like a
passion of mine for 2 years. I haven't been to a gym since COVID-19 started :(, and I really miss the day when I hit 120
kg on a bench press at 75 kg bodyweight.

> Either way, I decided to make this app because:
>1. I have some experience in this area; and
>2. It seems like a challenging project from which I can learn a lot because in terms of programming as well as 
    facilitating my understanding of a good gym program.
---

# User Stories

## First phase

Here is a list of the goals of my app for the first phase:

- As a user, I want to be able to **view the list of all exercises and favorite exercises**.
- As a user, I want to be able to **view the list of all routines and favorite routines**.
- As a user, I want to be able to **add, delete and edit** my **exercises**.
- As a user, I want to be able to **add, delete and edit** my **routines**.
- As a user, I want to be able to **add any number of existing exercises to a routine**.
- As a user, I want to be able to **see the total time a routine would take to complete**.

## Second phase

Here is a list of the goals for the second phase:

- As a user, when I start the application, I want to start with a fresh start where there are still no exercises or routines.
- As a user, I want to be able to save all the exercises and routines that exist inside the app to the save file.
- As a user, I want to be able to load all the exercises and routines that exist inside the save file.

## Phase 4: Task 2 (adding event logging)
### A representative sample of the events that occur when I use my program:
    Mon Nov 22 18:26:20 PST 2021
    Workout created

    Mon Nov 22 18:26:24 PST 2021
    Added new exercise "Knee push ups"
    
    Mon Nov 22 18:26:24 PST 2021
    Added new exercise "Normal push ups"
    
    Mon Nov 22 18:26:24 PST 2021
    Added new exercise "Inclined push ups"
    
    Mon Nov 22 18:26:24 PST 2021
    Added new exercise "Bench press hypertrophy"
    
    Mon Nov 22 18:26:24 PST 2021
    Added new exercise "Bench press for strength"
    
    Mon Nov 22 18:26:24 PST 2021
    Added new exercise "Bodyweight squats"
    
    Mon Nov 22 18:26:24 PST 2021
    Added new exercise "Barbell squats"
    
    Mon Nov 22 18:26:24 PST 2021
    Added new exercise "Stiff leg deadlift"
    
    Mon Nov 22 18:26:24 PST 2021
    Added new exercise "Calf raises"
    
    Mon Nov 22 18:26:24 PST 2021
    Added new routine "Chest Workout Hypertrophy"
    
    Mon Nov 22 18:26:24 PST 2021
    Added new routine "Leg day"
    
    Mon Nov 22 18:26:24 PST 2021
    Added new routine "Chest/Legs mix"
    
    Mon Nov 22 18:26:24 PST 2021
    Added new routine "1"
    
    Mon Nov 22 18:30:32 PST 2021
    Added new exercise "Lat Pulldown"
    
    Mon Nov 22 18:32:38 PST 2021
    Changed exercise name from "Bench press hypertrophy to "Normal bench press"
    
    Mon Nov 22 18:32:38 PST 2021
    Changed reps for "Normal bench press" from 15 to 12.
    
    Mon Nov 22 18:32:38 PST 2021
    Changed sets for "Normal bench press" from 2 to 3.
    
    Mon Nov 22 18:32:38 PST 2021
    Changed rest time for "Normal bench press" from 60s to 45s.
    
    Mon Nov 22 18:32:38 PST 2021
    Changed rating for "Normal bench press" from "B - Great" to "A - Amazing".
    
    Mon Nov 22 18:34:08 PST 2021
    Added new exercise "Shrugs"
    
    Mon Nov 22 18:35:13 PST 2021
    Added new exercise "Farmer's Walk"
    
    Mon Nov 22 18:36:33 PST 2021
    Added new exercise "Pull-up"
    
    Mon Nov 22 18:39:49 PST 2021
    Added new exercise "Jumping Jacks"
    
    Mon Nov 22 18:40:45 PST 2021
    Added new exercise "Arm circles"
    
    Mon Nov 22 18:41:42 PST 2021
    Added new exercise "Jump Rope"
    
    Mon Nov 22 18:44:17 PST 2021
    Added new exercise "Lunges"
    
    Mon Nov 22 18:46:44 PST 2021
    Added new routine "Warm up"
    
    Mon Nov 22 18:49:41 PST 2021
    Added new routine "Back day"
    
    Mon Nov 22 18:51:41 PST 2021
    Added new exercise "Hyperextensions"
    
    Mon Nov 22 18:53:38 PST 2021
    Added new exercise "Deadlift"
    
    Mon Nov 22 18:56:21 PST 2021
    Changed included exercises for "Back day" from [(1 exercises) Lat Pulldown] to [(8 exercises) Hyperextensions, Lat Pulldown, Deadlift, Shrugs, Farmer's Walk, Pull-up, Hyperextensions, Hyperextensions].
    
    Mon Nov 22 18:56:21 PST 2021
    Changed total time for "Back day" routine from "6 min, 21 sec" to "54 min, 33 sec".
    
    Mon Nov 22 18:56:28 PST 2021
    Workout saved

## Phase 4: Task 3 (reflection of app design as presented in the UML diagram)

![UML_Design_Diagram](https://media.github.students.cs.ubc.ca/user/13164/files/e0e2cf00-4e30-11ec-8f62-2dc4261750d4)

### prompt:
    If you had more time to work on the project, is there any refactoring that you would do to improve your design?
    If so, describe the changes you would make in point form in this section of your README.md file.
    Note that we do not want you to actually perform the refactoring - just identify the changes you would make if you had more time.
    Keep in mind that refactoring does not mean adding more features to your application.
Things that I would refactor:
1) My ExercisePanel and RoutinePanel are really similar, they both have:
   - a non-editable tables
   - both contain a scroll panel 
   - both have a grid panel
   - both have 3 flow panels in the same orders

`To solve this, I would refactor these common parts out into one abstract class, and extend ExercisePanel and RoutinePanel using it`

2) My ExercisePanel and ExerciseTable both have the same exact table pattern. 

`To reduce the code duplication, I could try to leave the ExerciseTable as it is, and somehow incorporate this class into the
Exercise Panel, by adding an ExerciseTable with all exercises into my ExercisePanel class. This would make my code easier to understand,
because it makes more sense to have update table methods inside the ExerciseTable class instead of ExercisePanel class`

3) I can reduce coupling between the ChoiceList class and the Exercise class by utilizing the observer pattern.
   Furthermore, I believe that my RoutinePanel and Exercise panel would benefit from the observer patter because both 
have to constantly be updated when new routines and exercises are added. 

`I am not sure how to implement the observer pattern, but I believe that an implementation would have the Workout class 
as the Subject of the observer pattern, and the ExercisePanel and RoutinePanel would be the concrete observers 
(I am not sure if WorkoutAppUI is a concrete observer).`

4) The thing that confuses me the most in my program is the ButtonRenderer and ButtonEditor, as well as the way they 
work with favorite view. In my current implementation, I had to verify whether the RoutinePanel is in favorite 
view or not is by checking the text on the button. This is obviously wrong, but I could not figure out how to use 
Object-Oriented Programming with UI elements. That is, I tried to call public methods on a RoutinePanel object (routinePanel),
but this does not work because JPanel does not have those elements. 

`My only guess why I could not call custom methods on my InternalPanels is probably because I did not extend InternalFrame 
inside my RoutinePanel class, which would mean that in my WorkoutAppUI I could instantiate RoutinePanel as a user-defined 
class that extends a java package, which would allow me to inherit the methods (probably). This is just a thought, 
but I would try to do this to improve my code (refactor).`