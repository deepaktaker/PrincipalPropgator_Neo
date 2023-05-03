# PrincipalPropgator_Neo
1) Deploy the web App in Java EE 7 Web Profile TomEE 7
![Provider Account App](https://user-images.githubusercontent.com/17820394/235899797-5fcc5362-5d04-4f7c-9e00-ccc90679f0c2.JPG)

2) Subscribe the app in the Subscriber account using the rest endpoint
Method: PUT
Cred: Basic cred with admin rights in provider account
https://services.int.sap.eu2.hana.ondemand.com/services/v1/instances/[subaccount technical Name]/subscriptions/v1/provider/[provideraccount technical Name]/application/[web app name in provider account]
3) In the subaccount go to subscription and select the web app in the list. click on Roles on the left panel. create a Role ESBMessaging.send to the web application in the sub account. assign thiis role to the user mcuper371@gmail.com
![SubAccount 9](https://user-images.githubusercontent.com/17820394/235899896-2b828b92-4711-4e22-98f0-8c5c2c901a3f.JPG)
4) Do the Trust configuration between the IDP and Neo subaccount
![SubAccount 1](https://user-images.githubusercontent.com/17820394/235900079-a32f80f1-ca7f-48e3-a3f3-31f253e548d0.JPG)
![SubAccount 2](https://user-images.githubusercontent.com/17820394/235900097-9b06f154-21bc-4cb3-8628-c91b04870f56.JPG)
![SubAccount 3](https://user-images.githubusercontent.com/17820394/235900115-72bb0914-0a96-4ec4-abe2-56ea852ecf8c.JPG)
![SubAccount 4](https://user-images.githubusercontent.com/17820394/235900123-386ab58a-dcca-4ec3-9b30-00d466a8350d.JPG)
![SubAccount 5](https://user-images.githubusercontent.com/17820394/235900133-01e2966c-fc27-4545-9fe0-52c7c2bb0b56.JPG)
![SubAccount 6](https://user-images.githubusercontent.com/17820394/235900145-0d1e975e-366d-4afe-9e4b-b73c0df88c5c.JPG)
![SubAccount 7](https://user-images.githubusercontent.com/17820394/235900157-081e9654-5153-4154-a6be-53b10da2df96.JPG)
![SubAccount 8](https://user-images.githubusercontent.com/17820394/235900165-6efc3a7e-7587-44cf-8377-30c78b420f57.JPG)
5) Deploy the iflow that trigger the Principal propagation in the Neo subaccount
![Iflow with Odava v2](https://user-images.githubusercontent.com/17820394/235901144-646346c9-8e4c-4a25-9213-7425dcb1c379.JPG)
![Iflow Connection](https://user-images.githubusercontent.com/17820394/235901159-7e5984a1-bad2-4672-bcb0-9f68911f0767.JPG)
![Iflow Processing](https://user-images.githubusercontent.com/17820394/235901171-e880acd0-f8fc-4b33-a3d7-9553d0825011.JPG)
6) Register Oauth client in Successfactors. then copy the client id from Oauth registration and use it in next step
6) Deploy Oauth Saml Bearer Credential in Neo subaccount
![SubAccount 10](https://user-images.githubusercontent.com/17820394/235901301-ab0f3e84-1365-4771-a985-8a1c05677e37.JPG)

