# What is this?

**JobsLastBuildDate** : est un script groovy permettant de lister les jobs qui ne sont pas lancés durant une période donnée (ces jobs sont groupés par vue 'view' dans JobsLastBuildDateGroupbyView.groovy)
* Paramétres :
    * tag : c'est le nom du slave où la recherche sera effectuée
    * dateMax : c'est la date max (ex: 2014-12-31 23:59:59)
    * dateMin : c'est la date min (ex: 2010-01-01 01:00:00)
    * afficherInfo : prend deux valeurs (avec ou sans), permet d'afficher ou non les informations liées au job (ex: Url du job, n° dernier build, statut et la date/heure)

**ListPlugins** : est un script groovy permettant de lister les plugins installés dans Jenkins
