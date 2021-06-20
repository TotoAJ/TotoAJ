#!/usr/bin/env/python
import requests, json
from urllib.request import *

webhook_url = 'WEBHOOK URL HERE'
ping = True

response = requests.get('https://api.ipify.org/?format=json')
dt = response.json()
headers = {
        'Content-Type': 'application/json',
        'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11'
}

message = '@everyone\n' if ping else ''
message += '```'
message += dt['ip']
message += '```'
payload = json.dumps({'content': message})

req = Request(webhook_url, data=payload.encode(), headers=headers)
urlopen(req)
