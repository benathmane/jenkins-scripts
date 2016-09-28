// If you have a lot of job configured on your Hudson installation, you maybe want to have a list of job which last run fails. This script is taken from an article by <a href="http://janmaterne.wordpress.com/2009/07/03/hudson-how-to-
// get-a-list-of-all-failed-jobs/">'Jan Materne'</a>

hudsonInstance = hudson.model.Hudson.instance allItems = hudsonInstance.items activeJobs = allItems.findAll{job -> job.isBuildable()} failedRuns = activeJobs.findAll{job -> job.lastBuild && job.lastBuild.result == hudson.model.Result.FAILURE} failedRuns.each{run -> println(run.name)}
