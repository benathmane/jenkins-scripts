import hudson.model.*
import hudson.tasks.*
import hudson.scm.*
  
for(item in Hudson.instance.items) {
  for(location in item.scm.locations) {
    println("\n@@@@@@@@@@@@@@@@@")
    println("\njob $item.name")
    println("\n@@@@@@@@@@@@@@@@@")
        
    println("\nlocation $location.remote")
    credid = location.credentialsId
         
    if (credid != null)
      println("\ncredid $credid")
    }
  }
}
