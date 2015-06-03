#!/usr/bin/python

# Copyright 2013 Michigan Technological University
# Author: Bas Wijnen <bwijnen@mtu.edu>
# This design was developed as part of a project with
# the Michigan Tech Open Sustainability Technology Research Group
# http://www.appropedia.org/Category:MOST
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Affero General Public License as
# published by the Free Software Foundation, either version 3 of the
# License, or(at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU Affero General Public License for more details.
#
# You should have received a copy of the GNU Affero General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.

# Original file modified by arolfe@bu.edu to edit out the web interface 
# with the intention of using it as a module for python scripts

import sys
import argparse
import time
import math

try:
    import RPi.GPIO as gpio
    gpio.setwarnings(False)
    gpio.setmode(gpio.BOARD)
except:
    sys.stderr.write('Warning: using emulation because RPi.GPIO could not be used\n')
    class gpio:
        LOW = 0
        HIGH = 1
        IN = 0
        OUT = 1
        @classmethod
        def output(cls, pin, state):
            pass
        @classmethod
        def setup(cls, pin, type, initial):
            pass
DIR = 11
STEP = 13
gpio.setup(STEP, gpio.OUT, initial = gpio.HIGH)
gpio.setup(DIR, gpio.OUT, initial = gpio.HIGH)

class Pump:
    def __init__(self, pitch, stepAngle, microsteps, syringeID):
	self.steps_per_rev = 360 / float(stepAngle)
	print "steps_per_rev = %s" % self.steps_per_rev
	self.pulse_per_rev = self.steps_per_rev * microsteps
	print "pulse_per_rev = %s" % self.pulse_per_rev
	self.mm_per_pulse = float(pitch) / self.pulse_per_rev
	print "mm_per_pulse = %s" % self.mm_per_pulse
	self.ml_per_pulse = math.pi * math.pow(float(syringeID) / 2, 2) * float(self.mm_per_pulse) / 1000 # <- converts mm^3 to mL
	print "ml_per_pulse = %s" % self.ml_per_pulse
    def flow(self, amount, seconds):
	pulses = abs(int(float(amount) / self.ml_per_pulse))
	print "pulses = %s" % pulses 
	print "seconds = %s" % seconds
	wait_time = seconds / float(pulses) / 2.
	print "wait_time = %s" % wait_time 
	if (wait_time < 0.0003):
		suggest = 0.0003 * pulses * 2
		print "WARNING: Too fast! Suggest increasing time to %s" % suggest
        gpio.output(DIR, gpio.HIGH if amount > 0 else gpio.LOW)
    	time.sleep(wait_time)
        for t in range(pulses):
            gpio.output(STEP, gpio.HIGH)
    	    time.sleep(wait_time)
            gpio.output(STEP, gpio.LOW)
    	    time.sleep(wait_time)
    def dispense_slow(self, amount):
	pulses = abs(int(float(amount) / self.ml_per_pulse))
	print "pulses = %s" % pulses 
	wait_time = 0.0005
	print "wait_time = %s" % wait_time 
	gpio.output(DIR, gpio.HIGH if amount > 0 else gpio.LOW)
    	time.sleep(wait_time)
        for t in range(pulses):
            gpio.output(STEP, gpio.HIGH)
    	    time.sleep(wait_time)
            gpio.output(STEP, gpio.LOW)
    	    time.sleep(wait_time)

if __name__ == "__main__":
    a = argparse.ArgumentParser()
    a.add_argument('--pitch', help = 'lead screw pitch(.8 mm; M5)', default = .8, type = float)
    a.add_argument('--stepAngle', help = 'angle, in degrees, of each motor step', default = 1.8, type = float)
    a.add_argument('--microsteps', help = 'microstep settings (use 1 for full step)', default = 1, type = float)
    a.add_argument('--syringeID', help = 'inner diameter of syringe in mm', default = 14.8, type = float)
    args = a.parse_args()

    p = Pump(args.pitch, args.stepAngle, args.microsteps, args.syringeID)
try:
    p.flow(-1,1)
finally:
    gpio.cleanup()
