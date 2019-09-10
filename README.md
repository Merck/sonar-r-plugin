Sonar R Plugin
==============
[![Build Status](https://travis-ci.org/Merck/sonar-r-plugin.svg?branch=master)](https://travis-ci.org/Merck/sonar-r-plugin)
[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=com.msd.gin.common.sonar:sonar-r-plugin&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.msd.gin.common.sonar:sonar-r-plugin)

Adds support for [R language](https://www.r-project.org/) into SonarQube. Currently, it uses output from [lintr tool](https://github.com/jimhester/lintr)
which is processed by the plugin and uploaded into SonarQube server.

Extending SonarQube
-------------------
[https://docs.sonarqube.org/display/DEV/Extension+Guide](https://docs.sonarqube.org/display/DEV/Extension+Guide)

Run SonarQube Locally with the Plugin
-------------------------------------
Installed Java 8 is required.

```bash
# build plugin and put it into SonarQube instance
./mvnw clean package
# run SonarQube server
./sonar-local.sh console
# wait for message: SonarQube is up
# stop it by Ctrl-C
```
Repeat previous steps for any changes made in the plugin:
```bash
./mvnw clean package && ./sonar-local.sh console
```

Check logs in different terminal session:
```bash
tail -f -n 0 ./.sonar/sonarqube-*/logs/*
```

[Web UI is running here](http://localhost:9000) (admin access defaults: `admin/admin`)

Sample Project
--------------
[sample-project/README.md](sample-project/README.md)

Add SonarQube into a Project
----------------------------
Follow standard procedure to add SonarQube analysis to existing project:
[https://docs.sonarqube.org/display/SONAR/Analyzing+Source+Code](https://docs.sonarqube.org/display/SONAR/Analyzing+Source+Code)

In case the local SonarQube instance should be used, just update SonarQube server URL to `http://localhost:9000`.

For example in `sonar-project.properties`:
```properties
sonar.host.url=http://localhost:9000
```

Release Process
---------------

TBD
