
import time
import picamera
import serial

ser = serial.Serial('/dev/ttyACM0', 9600)

frames = 15

def filenames():
	frame = 0
	while frame < frames:
		yield 'image%02d.jpg' % frame
		frame +=1 
#shutterspeed = 2500

while True:
	s = ser.readline()
	print s
	ser.write("0")
	if s.startswith("1"):
		with picamera.PiCamera() as camera:
			#camera.resolution = (1024, 768)
			camera.resolution = (640, 480)
			camera.framerate = 90
			camera.shutter_speed = 100
			#camera.ISO = 10
			camera.ISO = 0
			#camera.image_effects = 'solarize'
			camera.brightness = 75 
			camera.exposure_mode = 'sports'
			#camera.awb_mode = 'auto'
			camera.start_preview()
			#camera.led = False
			#Give the camera some warm-up time
			#while 1==1:
			time.sleep(1)
			#camera.start_recording('spinTest.h264')
			#camera.wait_recording(5)
			#camera.stop_recording()
			start = time.time()
			camera.capture_sequence(filenames())
			camera.capture_sequence(filenames(), use_video_port=True)
			finish = time.time()
			#s = "0"
			ser.write("1")
		
	
	#print ('Captured %d frames at %.2ffps' % (
		#frames,
		#frames / (finish-start)))
	
	
