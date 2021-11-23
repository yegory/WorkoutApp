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
however, since the time is limited for this project, the design/features will be biased towards the average male/woman (
need to define this better later).

However, technically speaking, it is **impossible** to create a program that would be able to produce the 'ideal'
workout routine for everyone, which is why, personally, I would recommend anyone who wants to see the best results in
the fastest time to get a personal trainer.

That being said, with enough background knowledge, I believe that one could definitely find this application useful in
managing their workout planning.

---

## Why is this project of interest to me?

In the summer, when I was 16, I went to the gym for the first time ever. I had little to no knowledge about any
exercises, I was overwhelmed. I learned my way through educating myself to YouTube channels like ATHLEAN-X, watching a
bunch of bodybuilder gym-vlogs, vids of powerlifting meets, and science based videos on nutrition. Gym was almost like a
passion of mine for 2 years. I haven't been to a gym since COVID-19 started :(, and I really miss the day when I hit 120
kg on a bench press at 75 kg bodyweight.

> Either way, I decided to make this app because:
>1. I have some experience in this area; and
>2. It seems like a challenging project from which I can learn a lot because in terms of programming as well as facilitating my understanding of a good gym program.
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
    
    Mon Nov 22 18:26:24 PST 2021
    Loaded program from ./data/WorkoutAppData.json
    
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
    Saved program to ./data/WorkoutAppData.json

## Phase 4: Task 3 (reflection of app design as presented in the UML diagram)
### prompt:
    If you had more time to work on the project, is there any refactoring that you would do to improve your design?
    If so, describe the changes you would make in point form in this section of your README.md file.
    Note that we do not want you to actually perform the refactoring - just identify the changes you would make if you had more time.
    Keep in mind that refactoring does not mean adding more features to your application.    