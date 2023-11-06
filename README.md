Sonar R Plugin
==============
[![Main branch publish](https://github.com/kmoco2am/sonar-r-plugin/actions/workflows/main.yaml/badge.svg)](https://github.com/kmoco2am/sonar-r-plugin/actions/workflows/main.yaml)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=kmoco2am_sonar-r-plugin&metric=bugs)](https://sonarcloud.io/summary/new_code?id=kmoco2am_sonar-r-plugin)

Adds support for [R language](https://www.r-project.org/) into SonarQube. Currently, it uses output from [lintr tool](https://github.com/jimhester/lintr)
which is processed by the plugin and uploaded into SonarQube server.

![Sample Screenshot](images/sample-screen1.png)

Features
--------
- reporting issues found by LintR (by processing its output)

Planned Features
----------------
- syntax highlighting
- code coverage
- code statistics (e.g. number of lines of code)

Extending SonarQube
-------------------
[Developing a plugin on SonarQube official documentation](https://docs.sonarqube.org/latest/extend/developing-plugin/).

Run SonarQube Locally with the Plugin
-------------------------------------
Installed **Java 17** is required to run SonarQube server.

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
Script for the release steps:
```
./release.sh
```

Travis CI build does the release process in Github.

What it does:
- derive release version from current SNAPSHOT in POM
- create new release branch
  - commit release version into POM
  - create tag
  - push
- generate new development version by increasing last number
- checkout master
  - commit new development version
  - push



