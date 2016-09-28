/* 
 * This script extract the module/branch parameters from the build in which is running
 * and update the branch/module of the job(s) following it
 * This script need to be launched in the job, not in admin console
 */
println "------ START GROOVY SCRIPT ------"

import hudson.model.*
import hudson.console.*
import hudson.scm.*

def backUpDestJob = null
def debug = []
//def currentBuild = build // this work only in "execute system groovy script"
def currentBuild = Thread.currentThread().executable // this work in scriptler
def currentJob = currentBuild.getParent() // get the job from the current build 

println "Current Job Detected:"+currentJob.getDisplayName()
println "This Jobs SCM will be changed:"
println currentJob.getDownstreamProjects()

//getting name of the following job(s)
currentJob.getDownstreamProjects().each{ followingJob ->

  //updating following job scm by current scm
  followingJob.scm = currentJob.getScm()
  followingJob.save()  
}
println "------ END GROOVY SCRIPT ------"
