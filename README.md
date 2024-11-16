# ELWAController
Controller that connect ECHONET Lite devices to ECHONET Lite Web API server


# Install echowand library
All device classes are created base on the ECHONET Lite Specification (release J). 
All mandatory and observerable attributes are supported (feel free to add more attribute on your own).

The echowand lirary has been used as a local maven dependency (Make it public? Not sure! contact the author).
Jar library of the echowand is included. Install it into your local maven by running the following command:

  mvn install:install-file -Dfile=echowand-1.1.jar -DgroupId=jaist.tanlab -DartifactId=echowand -Dpackaging=jar -Dversion=1.1