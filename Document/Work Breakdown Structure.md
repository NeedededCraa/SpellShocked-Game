|Date|Task|Description|Priority|Time per Task|Progress|Comment|
|----|----|-----------|--------|-------------|--------|-------|
|12 Sept 2021| class hierarchies|Drawing out and organzing where classes/packages should be. everyone involved| max|1 day| a bit of debate on what is and isnt an entitiy|
|13 Sept| Installation|download Libgdx--everyone and get github up| Max| 50 mins|Completed| Jack and Lucy need more familiarity with Github|
|14 Sept| GitHub|Learned about github/going over how to pus and pull| Max| 50 mins| done| Intellij works nicely with GitHub|
|16 Sept|Story/game idea|Get story board and design idea done, make pitch to Mr. M| Max| 90 mins| Pitch approved|
|18 Sept|Tiles| Work on getting tiles to render, David will do drawings| High| 50 mins| Not yet, basic grass is done| Talk to Jonah about perlin map?|
|22 Sept|Player| Cont. of yesterday. Roy will start player/player movement| High| 50 mins| No but grass is somehwat working| Need to figure out how to randomize terrain|
|21 Sept|Player design| Cont. of yesterday. David has finished main player's look| High| 50 mins| No but player is now on screen, can't work| How to get player to move?|
|23 Sept|Tile generation| Alex Jack working on tiles. Lucy and Roy working on player| Medium| 50 mins| No| Player can now be moved with WASD|
|24 Sept|Layering| Cont. of yesterdat| Medium| 50 mins| No| Kayering looks wrong but player can walk on grass tiles. Map boundaries that change created|
|27 Sept| Perlin Gen| Working on radmon generation for noise with Perlin| low| 50 mins| Yes||
|28 Sept| Layering part 2|Trying to understand how layering should work properly| High| 50 mins| Almost?||
|30 Sept|Layering part 3| Deploy layering| High| 50 mins| No|Looks right but treated like it is flat|
|2 Oct|Cont.|Cont of yesterday|High| 50 mins|NO||
|3 Oct| Cont of yesterday|High|50 mins|Yes|Time to random generate and split up more work|
|4 Oct| Sprites/layers|David is making more spirtes. Jack and Alex working on perlin for random generation of tiles, Lucy and Roy on player being able to move up layers|Med|50 mins|No|
|5 Oct|Player Movement|Cont. of yesterday|Med|50 mins|PLayer can now move up|
|8 Oct|Sheep and GUI| David making sheep sprite. Alex Jack working on map. Lucy working on GUI/finding texts|Med|5 days|Yes|Sheep can move around now but cannot detect collision well, walks through people|
|15 Oct - 16 Nov| Server|Alex is beginning to work on server. Says it will take a while and is trying to bypass BSD policies. David helps him with simpler tasks like pinging| Max| 1 month|No although worked very hard| "Cool idea but not feasible with schooll network restrictions. Offloading too much work on one person, want to prevent burnout and running out of time. Switch to mode based game"|
|15 - 19 Oct| Input|Lucy works on functional inputs to use to open up menus and settings/pause. Creates buttons and trigger scene clickboxes |Med| 3 days| Yes|
|15 Oct - 1 Dec|Sprite Making/Video Game Art| David works on making sprites| High| Never ending| great progress| Uses paint.net to create pixel art with frames, over all going very smoothly and has generated lots of art|
|15 - 19 Oct|Inventory| Jack is working on inventory class for player entitity and making it so you can reach different slots| High| 4 days| Yes| Now needs to transfer code to make a workable hot bar|
|15 - 26 Oct|Debugging| Roy has a list of bugs to fix and is working on them, including: zoom in causing flipping, moving through sheep, randomly dying when you hit the gui screen|Med|2 weeks| Yes| New bugs are always generating, he is best person to fix them|
|21 - 25 Oct|Game Manager| Lucy discovers that game needs to us egame manager scenes to fix issues with scene switching. She begins to work on that bug, changing structure of code| High|3 days| Yes| Caused a weird bug of dying randomly, which Roy fixed|
|25 Oct - 2 Nov|JSON parsing| Lucy makes the jsons for the different tiles/skins and works on making reading possible|Med|1 week|Done|Active reader is working odd, best to just user super of GUI for skin creation|
|26 Oct - 9 Nov| Obstacles| Roy makes obstacles and random generation for them|Med|2 weeks|Yes|weird bug where you can walk through corners. Also, made it so you can enter through obstacles one time at spawn in case you are entrapped|
|Oct 19 - 29| Hotbar| Jack works on making a player hotbar that you can go through and that you can go through different items and scales with the screen|Med|1 week|Yes|scales but positioning doesnt really work well when zoomed out or in by a lot. will have to work on picking up items|
|29 Oct - 5 Nov| Item pickup| Jack works on making it so you can pick up items and change their positioning in the hotbar itself. |Low| 1 week| Yes| weird bug whre you can duplicate so there is infinite buckets|
|18 - 23 Oct|Make seperate World class & seed|each gamemode will have their own World.java; generate everything in the World with designated seed|Highest|4 Days|Finished|caused big merge issue|
|30 Oct - 9 Nov|Memory Optimization & Fix Tile generation|Low|Test out how large the world can our engine handle, find possible way to reduce memory usage|1 Week|Finished|Still have to improve (still having lots of unused array) but need to change along with the Tile rendering engine (which I don't know how that work)| 
|9 - 11 Nov|Quest Interface|Make a GUI that showing player some quests todo and current progree|High|2 Day|Framework Finished - Need make actual story|It only has one page some currently couldn't display lots of quests|
|12 - 16 Nov|Basic Leaderboard Integration|Send score to the leaderboard after the game end, having the leaderboard viewable online |Medium|3 days|Gave up|Just want a very basic one that showing some score (not expect to have anti-cheat mechanism), tried several game platform and none of them works, decided to male our own backend later|
|17 - 19 Nov|File/Gamemode Restructure and Menu|Gamemodes should be moved to the World file and restructured also the Menu screens for them|Medium|3 days|Finished|Menu screens for gamemodes work and there is a barebones setting menu as well|
|17 - 22 Nov|Enemy Following| Trying to make a start attacking and following componet to monsters, so they follow player|Medium|3 days|Done|Trying to make it so only works in certain range of player|
|22 - 28 Nov|Block Inventories and Sheep entity movement|Blocks have their own inventories with items in them|Medium|5 days|Finished|You are able to click on rocks, you are also able to click on them to open and close the inventory, the sheep can now follow the player, still need to fix some issues with it|
|29 Nov - 2 Dec|General fixes and Block Inventory|Fixes within code, being able to click on individual items in the block inventories, making the sheep into an enemy|Medium|4 days|Finished|Work Breakdown Structure was also updated, and a skeleton sprite sheet added to replace sheep, chests added|
|3 Dec|Sprint review|Need to merge all code and make it work|High|1 day|Finished|Sprint review was good, everything mostly worked, need to fix lava textures|
|5 - 10 Dec|Healthbar and inventory/item reworking|Entities have healthbars (including the player), click registration and some items need to be added|Medium|5 days|Finished|Pumpkins were added which are able to be consumed|
|12 - 16 Dec|Improvements and being able to move items between inventories|Moving items from player's inventory to a chest inventory|Medium|4 days|Finished|Pumpkins are able to be placed, healthbars are also being fixed as well as leaderboard testing|
|17 Dec|Sprint review|Merging code and making sure it all works, final sprint review before winter break|High|1 day|Finished|Issue with healthbars going past borders|
|4 - 5 Jan 2022|Scoring|How to score/include score count for new modifcsations of wave mode|Low|1 day|Finished|
|6 Jan|Game Over Screen|Make correct game voer screens depending on manner of death of player|Low-Med|1 day| Finished|Floating point error with scores, should fix|
|6 - 13 Jan|Pumpkin Rush|Lucy and Roy worked on making a new game mode, pumpkin rush. iteration of Shockwave where players are guarded by skeletons. Needs to create new monster class, work on range, as well as functional inputs|Medium|1 week|Finished|Works well overall, however skeletons stack when reassigned to nearest pumpkin|
|10 - 13 Jan|Shooting|Jack and Alex worked on particle movement, so smoke and magic spell could travel where you aimed and deal damage to the enemy|Med|3 days|finished| David is working on making different drawings to create more spells|
|17 - 21 Jan|Roll out beta versions for testers|Make a playable version able to be tested|Max|5 days|Started||
|21 - 24 Jan|Receive and incorporate feedback|Implement fixes and suggestions from beta testing|High|5 days|Not started||
|24 - 28 Jan|Public Version 1.0|Finishing touches, make sure everything works|High|5 days|Not started||
