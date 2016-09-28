// to know which jobs running on which master/slave
//slaveName = '';
def hi = hudson.model.Hudson.instance;

if ( slaveName!='' ){
  println 'Jobs for the slave '+slaveName;
  if (slaveName=~'master'){
    println('====================');
        println('Name: Master');
        i=0;j=0
        hi.getItems(hudson.model.Project).each {
          j++
          node = it.getAssignedLabelString()
          if( node == null || node == 'master' ) {
            i++
            println '\t'+it.getDisplayName() + '\t' + '' + it.getAbsoluteUrl()
          }
        }
        println(i + '/' + j + 'jobs executed on master')
  }else{
    if(hi.slaves.name.contains(slaveName)){
      indexOfSlave = hi.slaves.name.indexOf(slaveName)
      theSlave = hi.slaves.get(indexOfSlave)
      theSlave.getComputer().getTiedJobs().each{
        println ('\t'+it.getDisplayName());
      }
    }else{
      println 'slave not found'
    }
  }
}else{
  println 'Jobs list for slave : '
	for (aSlave in hi.slaves) {
	  println('====================');
	  println('Name: ' + aSlave.name);
	  aSlave.getComputer().getTiedJobs().each{
	    println ('\t'+it.getDisplayName());
	  }
	}
  	println('====================');
        println('Name: Master');
        i=0;j=0
        hi.getItems(hudson.model.Project).each {
          j++
          node = it.getAssignedLabelString()
          if( node == null || node == 'master' ) {
            i++
            println '\t'+it.getDisplayName() + '\t' + '' + it.getAbsoluteUrl()
          }
        }
        println(i + '/' + j + 'jobs executed on master')
}
println ''
