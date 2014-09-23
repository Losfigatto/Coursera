library(data.table)
library(plyr)
labelFeatures <- read.table("./project/features.txt",col.names=c("seq","label"))


##to load train set measurement and I set like name column the "label Feature" cleaned
XtrainFile <- read.table("./project/train/X_train.txt",col.names=gsub("-",".",gsub(",",".",gsub("\\(|\\)","",labelFeatures[order(labelFeatures$seq, decreasing = FALSE),"label"]))))
## load type of activity for each measurement
YtrainFile <- read.table("./project/train/y_train.txt",col.names=c("activityIndex"))

## load subjects
subjTrain <- read.table("./project/train/subject_train.txt",col.names=c("subj"))


## merged in one data table
trainTable <- data.table(subjTrain,YtrainFile,XtrainFile)

##exec the same step for test set
XtestFile <- read.table("./project/test/X_test.txt",col.names=gsub("-",".",gsub(",",".",gsub("\\(|\\)","",labelFeatures[order(labelFeatures$seq, decreasing = FALSE),"label"]))))
YtestFile <- read.table("./project/test/y_test.txt",col.names=c("activityIndex"))
subjTest <- read.table("./project/test/subject_test.txt",col.names=c("subj"))

testTable <- data.table(subjTest,YtestFile,XtestFile)
## *** Point 1 ***
## Merge in one dataset 
DS <- rbind.fill(trainTable,testTable)

## *** Point 2 ***
## extract only the measurements on the mean and standard deviation for each measurement
## I get Activity Label which contains mean() and std() and I clean them
DSMeanStd <- subset(DS,select=c("subj","activityIndex",gsub("-",".",gsub(",",".",gsub("\\(|\\)","",labelFeatures[grepl("mean\\(\\)|std\\(\\)",labelFeatures$label),"label"])))))

file <- "./project/firstTidyDataForProject.txt"
write.table(DSMeanStd, file=file, row.names=FALSE, col.names=TRUE, sep="\t", quote=FALSE)

## *** Point 3/4 ***
activityLabels <- read.table("./project/activity_labels.txt",col.names=c("seq","activityLabel"))
DSMeanStd$activityLabel <- factor(DSMeanStd$activityIndex,labels=activityLabels[order(activityLabels$seq, decreasing = FALSE),"activityLabel"])

## *** Point 5 ***
library(reshape2)
tidydataMelt<-melt(DSMeanStd,id=c("subj","activityIndex","activityLabel"))
tidydataNew<-dcast(tidydataMelt,subj+activityIndex+activityLabel~variable,fun.aggregate=mean)

## save tidydata into a file 
file <- "./project/SecondTidyDataForProject.txt"
write.table(tidydataNew, file=file, row.names=FALSE, col.names=TRUE, sep="\t", quote=FALSE)

