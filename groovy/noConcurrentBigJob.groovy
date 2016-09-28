// check if big job is already running and if put the others one in sleep
import hudson.model.*
import hudson.tasks.*
import hudson.console.*

def hi = hudson.model.Hudson.instance
def AntTaskToFound = ['hudson_robotframework','fortify_php']
def delayed = false
// GET LIST OF BUILDS CURRENTLY RUNNING
def buildingList = hi.items.findAll{it->it.isBuilding()}
// GET CURRENT BUILD (THE ONE WHICH EXECUTE THIS SCRIPT)
def curJob = Thread.currentThread()?.executable

println '----------------------------------------------------------------'
println 'detecting if another robotframework or fortify job is already running'
if(curJob instanceof hudson.model.FreeStyleProject ){
        curJob.getBuilders().each{builder->
                if( builder instanceof hudson.tasks.Ant && builder.getTargets() in AntTaskToFound ) {
                        buildingList.each{ build ->
                                build.getBuilders().each{buildTask->
                                        if(buildTask instanceof hudson.tasks.Ant && build.getDisplayName()!=curJob.getDisplayName() && buildTask.getTargets() in AntTaskToFound) {
                                                println 'your job was delaying in reason there is another robotframework/fortify job in execution taking all resources'
                                                delayed = true
                                                //STOP THE CURRENTLY JOB AND MAKE HIM WAIT
                                                curJob.sleep(1*1000*3600) //delay the job for 1 hour
                                        }
                                }
                        }
                }
        }
}
if(delayed==false){
        println 'no job detected in queue'
}
println '----------------------------------------------------------------'
