import pyshark as ps

cap = ps.LiveCapture()
cap.sniff(timeout=50)
cap
cap[3]
for packet in cap.sniff_continuously(packet_count=5):
    print('Just arrived:', packet)
