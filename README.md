# Red

<img align="right" src="https://vignette.wikia.nocookie.net/muppet/images/3/39/Red_Fraggle.jpg/revision/latest/scale-to-width-down/280?cb=20121231163106">

Red is a service that provides status for Routes. External network solutions can ask Red if a route is available in the OpenShift clusterj

The component is named after the Red Fraggle (http://muppet.wikia.com/wiki/Red_Fraggle). Logo is sourced from Fraggle wiki

## Setup
 
 In order to use this project you must set repositories in your `~/.gradle/init.gradle` file
 
     allprojects {
         ext.repos= {
             mavenCentral()
             jcenter()
         }
         repositories repos
         buildscript {
          repositories repos
         }
     }

## How to start
Run the main class in your Editor. The application run as the currently logged in user to your OpenShift cluster.

If you have several clusters make sure that the cluster url you are log into matches the one in the application.yaml file

## How to run on OpenShift
Make sure that the openshift profile is active, start the Main class
