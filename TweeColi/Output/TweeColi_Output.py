import ast
import Parse_Tweet
from pump_control import Pump
from time import sleep
import RPi.GPIO as gpio
from twython import TwythonStreamer

# Keys for @BUBacteria
apiKey = 'hbkblS9wvLUroL7tRI6MIO3Hj'
apiSecret = 'EwVdnrn1DJzuxuyRDbzgoy1eh6it7x3PMesUoqIaDGstob3FPI'
accessToken = '2860939569-oVLMyzz7TeQ3A5ji5WNPROIYQlZZTQ42dThvq7I'
accessTokenSecret = 'Op5kNa6MCOTNtSHsoLoXZ4lT5He5JwMvBQOrdJpEqH4je'

# Keys for @TweeColi
#apiKey = 'RJSlPyCo084MJKX63tdygPNqZ'
#apiSecret = 'E5i83RLt9VisK4zCngIyHBYxIJx46GD89oowaAiSq4BZeqLXiT'
#accessToken = '2798012371-BYjTww8SShM4lUoh2RNpoAB4TJ1aZLcCQRGZUBc'
#accessTokenSecret = 'neGup8dLJbdlezQb2FrEKNHsxYdQUJCZbDxJ10Iinch7x'

DIR = 11
STEP = 13

gpio.setmode(gpio.BOARD)
gpio.setup(STEP, gpio.OUT, initial = gpio.HIGH)
gpio.setup(DIR, gpio.OUT, initial = gpio.HIGH)

pitch = 0.8
stepAngle = 1.8
microsteps = 1
syringeID = 14.8
p = Pump(pitch, stepAngle, microsteps, syringeID)

# Initialize variables
try:
	class MyStreamer(TwythonStreamer):
		Lac_state = 0
		Ara_state = 0
		print "Lac_state = %s" % Lac_state
		print "Ara_state = %s" % Ara_state
		def on_success(self, data):
			if 'text' in data:
				# Assumed tweet format: chemical state (ex. aTc True)
				message = Parse_Tweet.get_message(data)
				username = Parse_Tweet.get_username(data)
				if message['chemical'] == "aTc":
					if message['state'] == "True":
						self.Lac_state = 1
					else:
						self.Lac_state = 0
					print self.Lac_state
				else:
					print "no aTc update"
				if message['chemical'] == "Ara":
					if message['state'] == "True":
						self.Ara_state = 1
					else:
						self.Ara_state = 0
					print self.Ara_state
				else:
					print "No Ara update"
				if (self.Lac_state ^ self.Ara_state):
					p.dispense_slow(1, DIR, STEP)
					print "Dispensing"
				else:
					print (self.Lac_state ^ self.Ara_state)
					
		def on_error(self, status_code, data):
			print status_code
	
	stream = MyStreamer(apiKey, apiSecret,
		    	accessToken, accessTokenSecret)
	# User id below is @ryanjaysilva
	#stream.statuses.filter(follow='606389094,2798012371')
	# User id below is @TweeColi
	stream.statuses.filter(follow=2798012371)
	# User id below is @bubacteria
	#stream.statuses.filter(follow=2860939569)
except KeyboardInterrupt:
	gpio.cleanup()
