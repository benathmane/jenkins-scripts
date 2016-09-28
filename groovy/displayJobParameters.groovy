// This scripts displays the parameters for all the jobs along with their default values (if applicable).Script found at <a href="http://wiki.hudson-ci.org/display/HUDSON/Display+job+parameters">Hudson</a>.

import hudson.model.*

for(item in Hudson.instance.items) {
  prop = item.getProperty(ParametersDefinitionProperty.class)
  if(prop != null) {
    println("--- Parameters for " + item.name + " ---")
    for(param in prop.getParameterDefinitions()) {
      try {
        println(param.name + " " + param.defaultValue)
      }
      catch(Exception e) {
        println(param.name)
      }
    }
    println()
  }
}

