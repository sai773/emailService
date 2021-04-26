import requests
import json
url ='http://localhost:8887/api/v1/ashield/service/email'

payload={'to_addr':'saikumar.ms@ashield.co','subject':'welcome to ashield','htmlBody':'thank you for choosing us,we are happy to provide our services','button_text':'COnform your email','button_link':'https://google.com'}
headers={'Content-type':'application/json', 'Accept':'application/json'}
r = requests.post(url, data=json.dumps(payload),headers=headers)
print ('send email response', r.status_code, r.text)
