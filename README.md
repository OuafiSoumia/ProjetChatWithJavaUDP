# ProjetChatWithJavaUDP
pour démarrer l'application il faut procéder comme suit:
1-run classe server.
2-run classe login .
À propos du Chat:
  Ce projet avait pour objectif de construire une application de chat en utilisant les datagrammes en java et de réaliser une communication entre plusieurs utilisateurs    soit de choisir de parler avec une personne précis, a un groupe ou avec plusieurs en mêmes temps.
  
Version SDK utilisé: 1.8
Pour Tester:
1)démarrer la page Login 
2)entrer le login et le MDP comme suit(login:soumia123 ,password:123)//vous trouvez les autres utilisateurs dans la base de données.
(faire  connecter les 4 utilisateurs pour tester l'envoi du groupe ...)
3)démarrer le serveur.
4)dans l'interface de chaque utilisateurs cliquer sur connecter.
5)tester l'envoi du message à tous les utilisateurs connecter (taper le message puis laisser ALL dans la zone texte pour envoyer à tous).
6)tester l'envoi a un utilisateur en remplacent le ALL avec "u_nomUser" exemple taper u_soumia ou u_yassine 
7)tester l'envoi a un groupe en remplacent le ALL avec "g_nomGroup" exemple taper g_sisters.

NB: l’utilisateur abdo123 n'appartient au groupe g_sisters(qui inclut les utilisateurs soumia,sara,yassine) donc il si il a essayé d'envoyer un message a ce groupe ,un message d'erreur va l'interdit.
+l'utilisation de g_ et u_ est fait pour distinguer le nom du groupe du nom de l'utilisateur dans le cas où ils ont le même nom
pour ce deconnecter clicker sur quitter (pour fermer sa session).
Pour ce connecter:
![image](https://user-images.githubusercontent.com/94236541/213663849-ec23057e-1ce7-44b9-88c1-4fc18af178da.png)
![image](https://user-images.githubusercontent.com/94236541/213664077-2456d9b9-6237-4f98-9ecd-e431f176ebe0.png)
![image](https://user-images.githubusercontent.com/94236541/213664444-0e566125-5148-49cc-8247-d70f46eb9794.png)
Pour la voir la liste des amis connecter :
![image](https://user-images.githubusercontent.com/94236541/213664663-af869aa6-9eaa-42a2-b043-22878129e24f.png)
Pour fair un Brodcast "ALL":
![image](https://user-images.githubusercontent.com/94236541/213665097-6981b28e-36dc-4666-98ca-b63d6a744892.png)
Pour envoyer un message a un seul utilisateur:
![image](https://user-images.githubusercontent.com/94236541/213665303-828d6cfe-e26f-40b2-b703-e7b46b1ccb34.png)
Pour envoyer a un message a un groupe:
![image](https://user-images.githubusercontent.com/94236541/213665450-cf00a07f-db1b-405c-b426-582507297fc0.png)
acces interdit au group ,l'utilisateur n'appartient pas au group:
![image](https://user-images.githubusercontent.com/94236541/213665869-a6f50682-b9ba-4d3c-aa80-2da7d4484d1a.png)



 
