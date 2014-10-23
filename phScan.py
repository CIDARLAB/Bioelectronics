#!/usr/bin/python

import serial

#print "Welcome to TweeColi!"

usbport='/dev/ttyAMA0'
ser=serial.Serial(usbport,38400)

#Turn on the LEDs
#ser.write(b'\rL,1\r')

# Read one word from the serial connection, where word ends are denoted by carriage returns.
def read_word():
    word = ""
    reading = True
    while reading:
        data = ser.read()
        if(data == "\r"):
            reading = False
        else:
            line.append(data)
    return line

# Send a command to the pH sensor to trigger a read, then return the next word it transmits.
def get_value():
	cmd = b'\bR\b'
	ser.write(cmd)
	return read_word()

# Get the next value reported by the pH sensor when in continous sampling mode.
def get_next_value():
	throwaway = read_word() # Throw away the first value read, since it might be garbled.
	value = read_word() 




