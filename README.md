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
![image](https://user-images.githubusercontent.com/94236541/213663849-ec23057e-1ce7-44b9-88c1-4fc18af178da.png)


 
