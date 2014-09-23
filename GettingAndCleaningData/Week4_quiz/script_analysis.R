library(plyr)
library(data.table)

dir.create("esercizio4")

##  Question 1
fileUrl<-"http://d396qusza40orc.cloudfront.net/getdata%2Fdata%2Fss06hid.csv"
download.file(fileUrl,destfile="./esercizio4/dataurl.csv")
DSOrigin<-read.csv("./esercizio4/dataurl.csv")


DSsplitted <- strsplit(names(DSOrigin),"wgtp")
DSsplitted[[123]]   
## SOLUZIONE

## [1] ""   "15"

## Question 2
fileUrl<-"http://d396qusza40orc.cloudfront.net/getdata%2Fdata%2FGDP.csv"
download.file(fileUrl,destfile="./esercizio4/dataurl2.csv")
DSOrigin2<-read.csv("./esercizio4/dataurl2.csv",skip=4,col.names=c("COD","RNK","V1","LBLNAT","QUANT","V2","V3","V4","V5","V6"))

GDFFile<-DSOrigin2[c(1:190),]

removeComma=function(s) {as.numeric(gsub(",", "", s[5], fixed = TRUE))}

GDFFileWithoutCommas <- apply(GDFFile, 1 , removeComma) 

mean(GDFFileWithoutCommas)

## SOLUZIONE

## [1] 377652.4

## Question 3

lenght(grep("^United",GDFFile$LBLNAT))

##SOLUZIONE
## 3

## Question 4
fileUrl<-"http://d396qusza40orc.cloudfront.net/getdata%2Fdata%2FGDP.csv"
download.file(fileUrl,destfile="./esercizio4/dataurl3.csv")
DSOrigin3<-read.csv("./esercizio4/dataurl3.csv",skip=4,col.names=c("COD","RNK","V1","LBLNAT","QUANT","V2","V3","V4","V5","V6"))

GDFFile3<-DSOrigin3[c(1:190),]

fileUrl<-"http://d396qusza40orc.cloudfront.net/getdata%2Fdata%2FEDSTATS_Country.csv"
download.file(fileUrl,destfile="./esercizio4/dataurl31.csv")
FEDFile<-read.csv("./esercizio4/dataurl31.csv")

mergedData<- merge(FEDFile,GDFFile3,by.x="CountryCode",by.y="COD",all=TRUE)

length(grep("Fiscal year end: June",mergedData$Special.Notes))

##SOLUZIONE

## [1] 13

##Question 5

library(quantmod)
amzn=getSymbols("AMZN",auto.assign=FALSE)
sampleTime = index(amzn)

length(grep("2012-",sampleTime))
##SOLUZIONE 5.1
## 250

sum(ifelse(weekdays(as.Date(sampleTime[grep("2012-",sampleTime)],"%Y-%m-%d"))=="lunedì",1,0))

## SOLUZIONE 5.2
## 47