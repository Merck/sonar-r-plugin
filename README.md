Sonar R Plugin
==============
Adds support for [R language](https://www.r-project.org/) into SonarQube. Currently, it uses output from [lintr tool](https://github.com/jimhester/lintr)
which is processed by the plugin and uploaded into SonarQube server.

Extending SonarQube
-------------------
[https://docs.sonarqube.org/display/DEV/Extension+Guide](https://docs.sonarqube.org/display/DEV/Extension+Guide)

Run SonarQube Locally with the Plugin
-------------------------------------

```bash
./sonar-local.sh console
# check logs for issues
tail -f -n 0 ./.sonar/sonarqube-*/logs/*
```

[Web UI is running here](http://localhost:9000) (admin access defaults: `admin/admin`)

Sample Project
--------------
[sample-project/README.md](sample-project/README.md)

Add SonarQube into a Project
----------------------------
Follow SonarQube best practices to get existing project configured for SonarQube:
[https://share.merck.com/display/BP/SonarQube](https://share.merck.com/display/BP/SonarQube)

In case the local SonarQube instance should be used, just update URL to:
`http://localhost:9000`

