/* 
 * This script extract the list of project containing the search value in scm
 * if search var not given or empty, it list all project with the name of its scm
 */
println "------ START GROOVY SCRIPT ------"

import hudson.model.*
import hudson.console.*
import hudson.scm.*
 
  if(search!='' && search!=null){
    println '====== list projects using '+search+' SCM ======'
  }else{
    println '====== list all projects using an SCM ====='
  }      
     
def hi = hudson.model.Hudson.instance
hi.getItems().each{ project ->
  //updating following job scm by current scm
  tmp=project.getScm().getType().tokenize('.') //trunk all the class name part 
  scmName=tmp.last()[0..-4] //trunc the 'SCM' part from name

  if ( (search!=null && search!='' && scmName.toLowerCase()=~search.toLowerCase()) || search==null){
      println project.getName()
  }else{
    if((search==null || search=='')  && scmName!='Null' ){
      println scmName +' - '+project.getName()
    }
  }
}


println "------ END GROOVY SCRIPT ------"
