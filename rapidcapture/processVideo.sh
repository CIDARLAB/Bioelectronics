#!/bin/bash

python rapidcapture.py
MP4Box -fps 1 -add ~/rapidcapture/spinTest.h264 ~/rapidcapture/spinTest.mp4 
rm ~/rapidcapture/spinTest.h264
cp ~/rapidcapture/spinTest.mp4 $1
rm ~/rapidcapture/spinTest.mp4
omxplayer $1 
