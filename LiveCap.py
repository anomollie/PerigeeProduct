import shlex
import subprocess as sp
from subprocess import Popen

#Live Capture Data
terminal = r'sudo tshark -l -i wlan0mon -o wlan.enable_decryption:TRUE -o "uat:80211_keys:\"wpa-pwd\",\"Passphrase:sreemed2022:R2W\"" -T json "ether host 10:52:1c:cf:bb:74"'
split = shlex.split(terminal)
with open("live_cap.json", "w") as f:
	Popen((split), stdout=f)
