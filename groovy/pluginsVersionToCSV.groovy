// produce a CSV file which contain list of all plugins installed, it's version and the version backuped if exist (null else)

f = new File('/WOO/hudson/main1/workspace/GROOVY/plugins.csv')
f.append("Short Name, Installed Version, Backuped Version\r\n")
def plugins = jenkins.model.Jenkins.instance?.getPluginManager()?.getPlugins()
plugins.each {
  println "${it.getShortName()},${it.getVersion()},${it.getBackupVersion()}"
    f.append("\r\n${it.getShortName()},${it.getVersion()},${it.getBackupVersion()}")
}
