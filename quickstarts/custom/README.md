<!--

     Copyright 2005-2017 Red Hat, Inc.

     Red Hat licenses this file to you under the Apache License, version
     2.0 (the "License"); you may not use this file except in compliance
     with the License.  You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
     implied.  See the License for the specific language governing
     permissions and limitations under the License.

-->
custom: Demonstrates how to create a custom assembly
====================================================
Author: Fuse Team  
Level: Intermediate  
Technologies: JBoss Fuse, Maven  
Summary: This quickstart demonstrates to use Maven to create a custom assembly of JBoss Fuse  
Target Product: Fuse  
Source: <https://github.com/jboss-fuse/fuse-karaf/tree/master/quickstarts>  



What is it?
-----------

This quick start shows how to use Apache Maven to update and repackage JBoss Fuse to create a custom assembly.

This quick start demonstrates how to create a small, custom assembly. Contrary to the full JBoss Fuse assembly, the custom assembly created will have a much smaller footprint with only a limited set of features that are installed by default.

In studying this quick start you will learn:

* how to use Maven to unpack the existing assembly
* how to use Karaf's Features Maven plugin to create a new system folder
* how to use Maven to package the custom assembly

For more information see:

* https://access.redhat.com/documentation/red-hat-jboss-fuse for more information about using JBoss Fuse

System requirements
-------------------

Before building and running this quick start you need:

* Maven 3.1.1 or higher
* JDK 1.8
* JBoss Fuse 7

You will also need to install the JBoss Fuse distribution into your local Maven repository. You can use a command like:

mvn install:install-file -Dfile=/path/to/fuse/jboss-fuse-karaf-6.2.1.redhat-084.zip \
                         -DgroupId=org.jboss.fuse \
                         -DartifactId=jboss-fuse-karaf \
                         -Dversion=6.2.1.redhat-084 \
                         -Dpackaging=zip \
                         -DgeneratePom=false

Build the custom assembly
-------------------------

* Run `mvn clean install` to build the quickstart.
* After the build has finished, you will find the `target/custom-distro-${project.version}-bin.zip` file with the custom assembly.

Customizing the assembly
------------------------

The quick start shows a custom assembly with just a few features enabled. Typically, that list of features needs to be modified to match
your own environment or requirement.

Those features are configured in two locations:
* The configuration for the `features-maven-plugin` in the `pom.xml` (see `Step 3`) file controls which bundles will be available in the custom assembly's `system` folder
* `src/main/filtered-resources/etc/org.apache.karaf.features.cfg` defines which features will be installed automatically when the container first starts

If there are any other configurations files you need to add or modify in the custom assembly, those can be added to the `src/main/filtered-resources` directory as well.

