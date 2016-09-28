/**
 * Found list of jobs with Mail notifier which send mail to an adress
 * useful for find all jobs which send email to bad recipients
 */
import hudson.plugins.emailext.*
import hudson.model.*
import hudson.maven.*
import hudson.maven.reporters.*
import hudson.tasks.*
  
//def textToFound = 'dbuteau' //parameter to change 

def hi = hudson.model.Hudson.instance
hi.getItems(hudson.model.Project).each {project ->

		//depending of object we get the recipients
	if(project instanceof MavenModuleSet ){
		publishers = project.reporters
	}else {
		publishers = project.publishersList
	}
		
	//then we search for the @mail parameter in the recipientsList
	for(publisher in publishers ){
		if( publisher instanceof ExtendedEmailPublisher){
			recipient = publisher.recipientList
		}else 
	  	if( publisher instanceof Mailer || publisher instanceof MavenMailer){
			recipient = publisher.recipients
		}
		if(binding.variables.containsKey("recipient") && recipient =~ textToFound){
			println("JOB : "+project.name );
		}
	}
}
