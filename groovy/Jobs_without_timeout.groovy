//Source : https://janmaterne.wordpress.com/2010/07/11/how-to-check-if-all-hudson-jobs-have-a-timeout/

hudsonInstance = hudson.model.Hudson.instance
allItems = hudsonInstance.items
activeJobs = allItems.findAll{job -> job.isBuildable()}
wrappableJobs = activeJobs.findAll{job -> job instanceof hudson.model.BuildableItemWithBuildWrappers}
 
jobsWithoutTimeout = wrappableJobs.findAll { job ->
 job.getBuildWrappersList().findAll{it instanceof hudson.plugins.build_timeout.BuildTimeoutWrapper }[0] == null
}
 
println "There are $jobsWithoutTimeout.size jobs without timeout:"
jobsWithoutTimeout.each { println "- $it.name" }
 
x = ""
