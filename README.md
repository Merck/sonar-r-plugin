Sonar R Plugin
==============

TBD

Installing R on MacOS
---------------------

```bash
brew install r
```

Content for `~/.Rprofile`:
```
local({r <- getOption("repos")
   r["CRAN"] <- "https://cloud.r-project.org"
   options(repos=r)
})
```

Content for: `~/.Renviron`:
```
http_proxy=http://uswhsc88.merck.com:8080
https_proxy=http://uswhsc88.merck.com:8080
```

```bash
Rscript -e 'install.packages("lintr")'
Rscript -e 'install.packages("devtools")'
Rscript -e 'install.packages("roxygen2")'
```

Run LintR
---------
```bash
Rscript -e 'lintr::lint_package()'
```

Extending SonarQube
-------------------
[https://docs.sonarqube.org/display/DEV/Extension+Guide]

Run SonarQube Locally
---------------------

```bash
./.sonar/sonar.sh console
tail -f -n 0 ./.sonar/sonarqube-6.7.4/logs/*
```

```bash
cd sample-project
./sonar-scanner-run.sh
```
