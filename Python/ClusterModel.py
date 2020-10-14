import json
import subprocess as sp
from subprocess import Popen
import shlex
import pandas as pd
import numpy as np
import time
import schedule
import matplotlib.pyplot as plt
import os

from sklearn.cluster import KMeans

allFrameLengths = np.array([])
deltas = np.array([])
lastTime = 0
UDPCount = 0
protocol = ''

with open('/home/kali/Desktop/LightSwitch.json', 'r') as f:
  capture = json.load(f)

#Model Training
for packet in capture:
    #print(packet, ":")
    for identification, layers in packet.items():
        if identification=='_source':
            for key, value in layers.items():
                if key=='layers':
                    for key2, val2 in value.items():
                        if key2=='frame':
                            for key3, val3 in val2.items():
                                if key3=='frame.len':
                                    allFrameLengths = np.append(allFrameLengths, int(val3))
                                if key3 == 'frame.coloring_rule.name' and val3 == 'UDP':
                                    protocol = 'UDP'
                                    UDPCount = UDPCount + 1
                                if protocol == 'UDP' and key3 == 'frame.time_epoch':
                                    protocol = ''
                                    timeDelta = float(val3) - lastTime
                                    if 7 > timeDelta > 3:
                                        if timeDelta > 6:
                                            deltas = np.append(deltas, timeDelta-1)
                                            print('UDP Packet Delta T: ', timeDelta-1)
                                        if timeDelta < 4:
                                            deltas = np.append(deltas, timeDelta+1)
                                            print('UDP Packet Delta T: ', timeDelta+1)
                                    lastTime = float(val3)

print('Total number of packets in Capture File: ', allFrameLengths.size)
print('Total number of UDP packets in Capture File: ', UDPCount)
plottable = deltas.reshape(-1, 1)
X = np.zeros(deltas.size)
plt.scatter(deltas, X, s = 50, c='b')
plt.show()
from sklearn.cluster import KMeans
Kmean = KMeans(n_clusters=2)
t0=time.time()
Kmean.fit(plottable)
t_batch = time.time()-t0
centers = Kmean.cluster_centers_
print('Cluster Centers: \n', centers)
print('Model Training Time: ', t_batch, ' seconds\n\n')
plt.scatter(deltas, X, s =50, c='b')
for center in centers:
    plt.scatter(center, 0, s=200, c='g', marker='s')
plt.show()
groupings = Kmean.labels_


def genData():
    with open('/home/kali/PycharmProjects/Capture/live_cap.json', 'r') as f:
        raw_string = f.read() + ']'
        testCapture = json.loads(raw_string)

    allFrameTest = np.array([])
    deltaTest = np.array([])
    lastTimeTest = 0
    protocolTest = ''
    UDPCountTest = 0
    malicious_packet_ip = 'ALLCLEAR'
    breached = False
    for packet in testCapture:
        #print(packet, ":")
        for identification, layers in packet.items():
            if identification=='_source':
                for key, value in layers.items():
                    if key=='layers':
                        for key2, val2 in value.items():
                            if key2=='frame':
                                for key3, val3 in val2.items():
                                    if key3=='frame.len':
                                        framelen = int(val3)
                                        allFrameTest = np.append(allFrameTest, framelen)
                                        if framelen > 500:
                                            breached = True
                                    if key3=='frame.protocols' and ('udp' in val3):
                                        protocolTest = 'UDP'
                                        UDPCountTest = UDPCountTest + 1
                                    if protocolTest == 'UDP' and key3 == 'frame.time_epoch':
                                        protocolTest = ''
                                        timeDeltaTest = float(val3) - lastTimeTest
                                        if 6 > timeDeltaTest > 4:
                                            outfile = open('/home/kali/PycharmProjects/Capture/abj.txt', 'a')
                                            outfile.write(str(timeDeltaTest) + '\n')
                                            deltaTest = np.append(deltaTest, timeDeltaTest)
                                            print('UDP Packet Delta T (Test): ', timeDeltaTest)
                                        lastTimeTest = float(val3)
                            if key2=='ip':
                                for key3, val3 in val2.items():
                                    if key3=='ip.src' and breached == True:
                                        malicious_packet_ip = val3

    print('Total number of packets in Test File: ', allFrameTest.size)
    print('Total number of UDP packets in Capture File: ', UDPCountTest)
    TestDataSet = deltaTest.reshape(-1, 1)
    TestClusterPrediction = Kmean.predict(TestDataSet)
    TestScore = Kmean.score(TestDataSet)
    Distance = np.sqrt(np.abs(TestScore))
    print('Your data points have been matched to the following cluster indices\n',
          TestClusterPrediction, '\nand is on average', Distance, 'units away from the nearest cluster\n')

    plottable = deltaTest.reshape(-1, 1)
    X = np.zeros(deltaTest.size)
    plt.scatter(deltaTest, X, s = 50, c='b')
    for center in centers:
        plt.scatter(center, 0, s=200, c='g', marker='s')
    #plt.show()

schedule.every(5).seconds.do(genData)

while(1):
    schedule.run_pending()
    time.sleep(1)

f = open('C:\\Users\\Varun\\Desktop\\demo.rules', 'a')

if(Distance>100): #arbitrary, can make algortihm that is more accurate/ device specific
    print('MALICIOUS PACKET DETECTED....WRITING SURICATA RULES FILE TO BLOCK IP @', malicious_packet_ip)
    rule = 'drop ip ' + malicious_packet_ip + ' any -> any any (msg:"THIS MALICIOUS PACKET WILL BE DROPPED"; sid:1;)'
    f.write(rule)
    f.close()
    #RuleReload
    #os.system('sudo kill -USR2 $(pidof suricata)')
