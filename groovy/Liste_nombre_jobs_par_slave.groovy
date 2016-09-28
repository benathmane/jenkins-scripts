import jenkins.model.Jenkins

Short i=0
jobs = Jenkins.instance.getAllItems()
def nbr = 1
jobs.each { j ->
    if (j?.getClass()?.getName() != 'hudson.model.ExternalJob') {
      // A décommenter si t'as besoin de la liste des jobs désactivés d'un slave
      if (j.getAssignedLabelString()== slave /*&& j.disabled == true*/) {
        i++
          println "${i}) " + j.fullName
        lastbuild = j.builds[0]
       	if (lastbuild != null) {
        	println '  -> URL: ' + j.getAbsoluteUrl()
       	 	println '  -> lastbuild: ' + lastbuild.displayName + ' = ' + lastbuild.result + ', time: ' + lastbuild.getTime().format("YYYY-MM-dd HH:mm:ss")
        	println '\n'				
      	}
      }
    }
}
println "--------------------------"
println("Found ${i} jobs")
