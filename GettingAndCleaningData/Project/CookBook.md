Project Getting and Cleaning Data
================
##Human Activity Recognition
###  DESCRIPTION HOW I WORKED FOR CLEAN DATA 
In script file, I commented the single step.
I loaded the file the corresponding to testing and training data in different data table, then I merged in "DS" data set
with this columns :

	subj ( identify loaded from "subject_(train|test).txt" file)
	activityIndex (numeric code to identify the activity loaded from "y_(train|test).txt" file )
	[...] (loaded each value contained into X_(train|test).txt file which to marking single column 
	       with the name contained into Y_(train|test).txt file after cleaned from illegal characters)
							
###  DATA DICTIONARY
1. subj	
	Record Type
		01..30 An identifier of the subject who carried out the experiment

2. activityIndex	
	Record Type 
		A numeric code which identify type of activity
		1. WALKING
		2. WALKING_UPSTAIRS
		3. WALKING_DOWNSTAIRS
		4. SITTING
		5. STANDING
		6. LAYING

3. tBodyAcc.mean.X	
   tBodyAcc.mean.Y
   tBodyAcc.mean.Z
	Record Type
		-1..1	Mean value of Triaxal Body acceleration signal raw in time domain
			
4. tBodyAcc.std.X
   tBodyAcc.std.Y
   tBodyAcc.std.Z
	Record Type
			-1..1 Standard deviation value of Triaxal Body acceleration signal raw in time domain

			
5. tGravityAcc.mean.X
   tGravityAcc.mean.Y
   tGravityAcc.mean.Z
	Record Type
		-1..1	Mean value of Triaxal gravity acceleration signal raw in time domain
		
6. tGravityAcc.std.X
   tGravityAcc.std.Y
   tGravityAcc.std.Z
	Record Type
			-1..1 Standard deviation value of Triaxal gravity acceleration signal raw in time domain
			
7. tBodyAccJerk.mean.X
   tBodyAccJerk.mean.Y
   tBodyAccJerk.mean.Z	
	Record Type
		-1..1	Mean value of Triaxal Body acceleration Jerk signals in time domain
		
8. tBodyAccJerk.std.X
   tBodyAccJerk.std.Y
   tBodyAccJerk.std.Z
   	Record Type
   			-1..1 Standard deviation value of Triaxal Body acceleration Jerk signals in time domain
   			
9. tBodyGyro.mean.X
   tBodyGyro.mean.Y
   tBodyGyro.mean.Z
    Record Type
   		-1..1	Mean value of Triaxal Body gyroscope signal raw in time domain
   
10. tBodyGyro.std.X
    tBodyGyro.std.Y
    tBodyGyro.std.Z		
   	Record Type
   			-1..1 Standard deviation value of Triaxal Body gyroscope signal raw in time domain	
   			
11. tBodyGyroJerk.mean.X
    tBodyGyroJerk.mean.Y
    tBodyGyroJerk.mean.Z
   	Record Type
   		-1..1	Mean value of Triaxal Body gyroscope Jerk signal raw in time domain	
   
12. tBodyGyroJerk.std.X
    tBodyGyroJerk.std.Y
    tBodyGyroJerk.std.Z		
	Record Type
			-1..1 Standard deviation value of Triaxal Body gyroscope Jerk signal raw in time domain	

13. tBodyAccMag.mean
	Record Type
		-1..1	Mean value of signal the magnitude of Body linear acceleration three-dimensional signals calculated using the Euclidean norm in time domain	
		
14. tBodyAccMag.std		
	Record Type
			-1..1 Standard deviation value of signal the magnitude of Body linear acceleration three-dimensional signals calculated using the Euclidean norm in time domain
			
15. tGravityAccMag.mean
	Record Type
		-1..1	Mean value of signal the magnitude of gravity linear acceleration three-dimensional signals calculated using the Euclidean norm in time domain

16. tGravityAccMag.std		
	Record Type
			-1..1 Standard deviation value of signal the magnitude of gravity linear acceleration three-dimensional signals calculated using the Euclidean norm in time domain

17. tBodyAccJerkMag.mean
	Record Type
		-1..1	Mean value of signal the magnitude of Body linear acceleration three-dimensional Jerk signals calculated using the Euclidean norm in time domain	

18. tBodyAccJerkMag.std		
	Record Type
			-1..1 Standard deviation value of signal the magnitude of Body linear acceleration three-dimensional Jerk signals calculated using the Euclidean norm in time domain	

19. tBodyGyroMag.mean
	Record Type
		-1..1	Mean value of signal the magnitude of Body gyroscope three-dimensional signals calculated using the Euclidean norm in time domain	

20. tBodyGyroMag.std		
	Record Type
			-1..1 Standard deviation value of signal the magnitude of Body gyroscope three-dimensional signals calculated using the Euclidean norm in time domain	
			
21. tBodyGyroJerkMag.mean
	Record Type
		-1..1	Mean value of signal the magnitude of Body gyroscope three-dimensional Jerk signals calculated using the Euclidean norm in time domain	

22. tBodyGyroJerkMag.std		
	Record Type
			-1..1 Standard deviation value of signal the magnitude of Body gyroscope three-dimensional Jerk signals calculated using the Euclidean norm in time domain	

The same signals describe above are been calculated in domain of frequency by Fast Fourier Transform.

23. fBodyAcc.mean.X
24. fBodyAcc.mean.Y
25. fBodyAcc.mean.Z
26. fBodyAcc.std.X
27. fBodyAcc.std.Y
28. fBodyAcc.std.Z
29. fBodyAccJerk.mean.X
30. fBodyAccJerk.mean.Y
31. fBodyAccJerk.mean.Z
32. fBodyAccJerk.std.X
33. fBodyAccJerk.std.Y
34. fBodyAccJerk.std.Z
35. fBodyGyro.mean.X
36. fBodyGyro.mean.Y
37. fBodyGyro.mean.Z
38. fBodyGyro.std.X
39. fBodyGyro.std.Y
40. fBodyGyro.std.Z
41. fBodyAccMag.mean
42. fBodyAccMag.std
43. fBodyBodyAccJerkMag.mean
44. fBodyBodyAccJerkMag.std
45. fBodyBodyGyroMag.mean
46. fBodyBodyGyroMag.std
47. fBodyBodyGyroJerkMag.mean
48. fBodyBodyGyroJerkMag.std



