def views = jenkins.model.Jenkins.instance.getViews()
def nbr = 1

views.each { v ->
  if (v.name != 'ALL'
     && v.name != 'tests'){
    println 'View : ' + v.getViewName()
    def jobs = v.getAllItems()
    jobs.each { j ->
      def typeJob = j?.getClass()?.getName()
      if (typeJob != 'hudson.model.ExternalJob') {
          if (j.getAssignedLabel() != null) {
              node = j.getAssignedLabelString()
              if (tag != "") {
                      if (node == tag) {
                          numbuilds = j.builds.size()
                          lastbuild = j.builds[0]
                          if (lastbuild != null) {
                              java.sql.Timestamp dd1 = java.sql.Timestamp.valueOf(dateMin);
                              long dateMin = dd1.getTime();
  
                              java.sql.Timestamp dd2 = java.sql.Timestamp.valueOf(dateMax);
                              long dateMax = dd2.getTime();
  
                              java.sql.Timestamp dd3 = java.sql.Timestamp.valueOf(lastbuild.getTime().format("YYYY-MM-dd HH:mm:ss"));
                              long dateLastBuild = dd3.getTime();
                              if (dateLastBuild < dateMax && dateLastBuild > dateMin) {
                                  //if (dateLastBuild < dateMax){
                                  if (afficherInfo == 'non') {
                                      println '\t' + j.fullName
                                      nbr++
                                  } else if (afficherInfo == 'oui') {
                                      println nbr + ' : JOB: ' + j.fullName
                                      println '  -> URL: ' + j.getAbsoluteUrl()
                                      println '  -> lastbuild: ' + lastbuild.displayName + ' = ' + lastbuild.result + ', time: ' + lastbuild.getTime().format("YYYY-MM-dd HH:mm:ss")
                                      println '\n'
                                      nbr++
                                  }
                              }
                          }
                      }
              } else {
                  numbuilds = j.builds.size()
                  lastbuild = j.builds[0]
                  if (lastbuild != null) {
                      java.sql.Timestamp dd1 = java.sql.Timestamp.valueOf(dateMin);
                      long dateMin = dd1.getTime();
  
                      java.sql.Timestamp dd2 = java.sql.Timestamp.valueOf(dateMax);
                      long dateMax = dd2.getTime();
  
                      java.sql.Timestamp dd3 = java.sql.Timestamp.valueOf(lastbuild.getTime().format("YYYY-MM-dd HH:mm:ss"));
                      long dateLastBuild = dd3.getTime();
                      if (dateLastBuild < dateMax && dateLastBuild > dateMin) {
                          //if (dateLastBuild < dateMax){
                          if (afficherInfo == 'non') {
                              println j.fullName
                              nbr++
                          } else if (afficherInfo == 'oui') {
                              println nbr + ' : JOB: ' + j.fullName
                              println '  -> URL: ' + j.getAbsoluteUrl()
                              println '  -> lastbuild: ' + lastbuild.displayName + ' = ' + lastbuild.result + ', time: ' + lastbuild.getTime().format("YYYY-MM-dd HH:mm:ss")
                              println '\n'
                              nbr++
                          }
                      }
                  }
              }
          }
      }
    }
  	println '\n'
  }
}
