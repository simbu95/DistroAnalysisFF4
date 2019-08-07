# DistroAnalysisFF4

I used java for my coding, and its kinda sloppy, so sorry, but it should be decently readable. 

First, I recommend you look at the excel document, or the image for the Logic. It will help visualize what each part of the code does, and what the numbers mean.

The basic steps is the FeSimu file creates a an array of 28 integers, and assigns KIs to each spot, representing a location in the game. Then, this array is processed thru a BFS alogirthim, that keeps running until no more progress is made. If every spot in the game has been checked, meeting the criteria for the logic, then the seed passes, and is stored in data. If not, the seed data is discarded. 

The data file, Data.txt (currently ziped) contained the KI location array (refer to the logic picture for what each spot is, and what each number represents), then on the next line has the location of Dmist (also noted in the logic picture). I have removed all the brackets from this file, but you will have to remove brackets if you generate a new data set (sorry I am lazy). 

DataAna.java reads the Data.txt file in the same directory, and performs various counts of the data. Most of these currently are simple, such as if Dmist is in spot 34 (CPU), if D Mist had a certain KI (KI[27]==some number), or I did use the same logic as in the FESimu file, slightly modified to see if Dmist was required to beat the seed. 

Some notes, the logic is fairly sound, but could be flawed.

Moon/odin safety checks are on, Boss safety such as Golbez is not, so imagine the B! flag is on. Yes... I am lazy. Feel free to fix that if you want to, but I am not that interested. 

This simulation does not cover K, Kq, Km, or any Kt variants. That said, any variation of the Kqm flags can be achieved just by changing the random number generation, to make sure if only lands in certain spots. For Kt, that is supposed to be weighted, so yeah, have fun with that. 

I am also counting only 13 KIs right now, feel free to add in the other ones. I personally don't care where sandruby is, its not required for my logic, and thus is the same as nothing, but feel free to knock yourself out. If you care about non V1, you could probably use legend or adamant as a standin for your stats, or just add in the stuff, I don't care. 

