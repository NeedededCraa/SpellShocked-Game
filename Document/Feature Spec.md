# Feature Specification

### Feature Information
|||
|---|---|
|Feature Name|Spellshocked|
|Area|Game|
|Related Features|LibGdx|
|Requirement Specs|
|Document Location|https://github.com/SpellShocked/SpellShocked-Game|
|Spec Status|Draft|

### Contact Information
|Role|Name|
|---|---|
|Manager|Chad Magendanz|
|Developer|Lucy Stewart|
|Developer|Roy Pan|
|Developer|Jack Scott|
|Developer|Alex Stedman|
|Developer|David Nicolle|

### Revision Summary
|Author|Date|Version|Comment|
|---|---|---|---|
|-|11/23/2021|Initial Draft|Created this page|

## Functional Specification
spellshocked is a game developed with libGDX is java. The target audience for this game is people who enjoy fanatsy games. This game includes two main modes, Shock Wave and Pumpkin Rush. Pumpkin Rush is when a player must collect all the pumpkins that are guarded by a skelton, trying to do so in the quickest time as possible. The other mode, ShockWave, is a wave-based game where the player must beat certain amount of monsters that generate next to his location. Thge objective is to survive for as long as possible. 

## Scenario Description

![image](https://user-images.githubusercontent.com/65467897/150017323-0228ec19-cf98-4d15-be79-0749826cc481.png)
Photo of Loading Screen
![image](https://user-images.githubusercontent.com/86680163/150017990-1fcaa019-2bde-4d9e-b95d-7b8351598469.png)
Photo of Shock Wave
![image](https://user-images.githubusercontent.com/86680163/150018090-ada963c6-67f4-4933-9569-231c231675f3.png)
Photo of Pumpkin Rush



#### Title Screen
#### Setting
Includes volume
#### Gamemode
Select one of the three game mode

## Feature Description
Sections of the project:

1. Engine Selection:
    For our engine, we wanted a compromise of an active community, ability to code features largely on your own unlike Unity, and low levelness to essnetially be able to practice our coding skills as much as possible. As a result, we agreed upon LibGx and set up an enviornment and repo to began development.
2. Project Breakdown: Our next step was to break down our project into multiple packages, so each person could work on something else at a time and for better file management. This packages were GUI, entitities, world, and input/util. 
3. GUI: Our gui imported a skin found from a skin database and the bult in button and graphs features from LibGdx. We used this to create a master parent GUI, that then had child classes to create different screens for the corresponding screen needed, for example Game Over, Game Choosing Menu, Pause Menu, Title Screen, and more. To make switching between screens possible and effcient, we decided to organize our game with a Scene Manager, making the parent psellShocked class a stage chooser and game mode classes a seperate entitiy rather than combining the two.
4. Entitities
![image](https://user-images.githubusercontent.com/86680163/150418151-ae220600-dfe6-47cd-a8a6-85e8ba23d494.png)
5. World
![image](https://user-images.githubusercontent.com/86680163/150418317-64be532c-2db5-4c36-9bea-b60aa4dbe803.png)
6. Input/Util
![image](https://user-images.githubusercontent.com/86680163/150418694-3e33dc9f-681f-4403-bfd1-1e759f48942a.png)
7. Images/Artwork/Sounds: This section was done mainly by David. All character and world art was handmade in paint.Net. For sprites, he would use a sprite sheet of the character in a different art 

### gamemode
#### Pumpkin Rush: 
- random and set seed
- time based
- craft as many as spell you want
- enemies to stop you

#### Shockwave (main): 
- PvE 
- map level 
- last as long as you want


## Boneyard
#### Multiplayer
Due to some special circumstances we unable to host the game locally (LAN) and cloud server have too much latency
