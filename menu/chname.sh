#!/bin/bash
#set -x
sDay=`date -d'next-monday-1 days' +%Y%m%d`
eDay=`date -d'next-monday+5 days' +%Y%m%d`

inFileName=$1
fileName=$(echo $sDay"-"$eDay".csv")


if [[ ! -f $inFileName ]]; then
	echo "target Name: $fileName"
elif [[ -f $inFileName ]] && [[ ! -f $fileName ]]; then
	echo "$1 will be renamed to $fileName"
	mv $inFileName $fileName
else 
	echo "$fileName already Exists! abort."
fi

