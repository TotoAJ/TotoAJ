#!/usr/bin/env/python
import requests
import json

webhook_url = '<your_url>'
ping = True

response = requests.get('https://api.ipify.org/?format=json')
ip = response.json()['ip']

data = {
	"content": "@everyone" if ping else "",
	"embeds": [{
		"title": ip
	}]
}

requests.post(webhook_url, json=data)

# import requests, json; requests.post('<your_url>', json={"content": "@everyone" if True else "", "embeds": [{"title": requests.get('https://api.ipify.org/?format=json').json()['ip']}]})
# One liner
