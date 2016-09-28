/*
 * List all Disabled Jobs
 */
import jenkins.model.Jenkins

Short i=0
jobs = Jenkins.instance.getAllItems()
jobs.each { j ->
  def typeJob = j?.getClass()?.getName()
  if (typeJob != 'hudson.model.ExternalJob') {
    if(j.disabled == true){
      lastbuild = j.builds[0]
      if (lastbuild != null) {
        i++
        println i + ' : JOB: ' + j.fullName
        println '  -> URL: ' + j.getAbsoluteUrl()
        println '  -> Slave: ' + j.getAssignedLabelString()
        println '  -> lastbuild: ' + lastbuild.displayName + ' = ' + lastbuild.result + ', time: ' + lastbuild.getTime().format("YYYY-MM-dd HH:mm:ss")
        println '\n'				
      }
    }
  }
}
println "--------------------------"
println("Found ${i} disabled jobs")
