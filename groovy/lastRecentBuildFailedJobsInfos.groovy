/*
 * display list filtered by SCM and sorted by lastDateBuild of all failed job in last 24h
 */
def cvsList = [:]
def gitList = [:]
def bzrList = [:]
def allList = [:]
  
def hi = hudson.model.Hudson.instance
hi.getItems(hudson.model.Project).each {project ->
	String lastBuildResult = project?.lastBuild?.result
	
	if(lastBuildResult == "FAILURE" ){
		nowDate = Calendar.instance
		lastBuildDate = Calendar.instance
	  
		tempDate = new Date(project.lastBuild.getTimeInMillis())
		lastBuildDate.time = tempDate
		  
		if ( lastBuildDate.get(Calendar.YEAR) == 2013 ){
			Long diffMillis = nowDate.time.time - lastBuildDate.time.time
			Long diffSecond = (diffMillis - (diffMillis %1000)) /1000
			diffHours = (diffSecond - (diffSecond %3600)) /3600
						
			if(diffHours<=24){
				switch ( project.getScm()){
					case ~/(.*)CVSSCM(.*)/:
						cvsList.put(project.name,lastBuildDate)
						break;
					case ~/(.*)GitSCM(.*)/:
						gitList.put(project.name,lastBuildDate)
						break
					case ~/(.*)bazaar(.*)/:
						bzrList.put(project.name,lastBuildDate)
						break
					default:
						println(project.name + 'is unknow: '+project.getScm())
				}
                          	allList.put(project.name,lastBuildDate)
			}
		}
	}
}
allList = allList.sort{ it.value.time.time }
allList.each{ println it.key + " - " + it.value.time  }
println allList.size() + 'job failed'   
  
println "------------------------------------------------"
