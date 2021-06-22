#!/usr/bin/env/python
import requests, json

webhook_url = '<your_url>'
ping = True

response = requests.get('https://api.ipify.org/?format=json')
ip = response.json()['ip']

data = {
	"content": "{}{}".format('@everyone\n' if ping else '', ip)
}

requests.post(webhook_url, json=data)
