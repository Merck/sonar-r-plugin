Sample Project for R Integration into SonarQube
===============================================
The goal of this project is to get sample project for locally running SonarQube to be able to test R plugin easily.

Installing R on MacOS
---------------------
A few hints for non-R developers to get environment for LintR up-and-running easily.

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

Content for `~/.Renviron` if an HTTP proxy is in place:
```
http_proxy=http://proxy.company.com:8080
https_proxy=http://proxy.company.com:8080
```

```bash
Rscript -e 'install.packages(c("lintr","dplyr","devtools","roxygen2","covr","rcmdcheck","testthat"))'
```

Run LintR
---------
Get LintR output into supported JSON format:
```bash
Rscript run_lintr.R
```

`lintr_out.json` is created by the script.

Run Code Coverage
-----------------
```bash
Rscript run_cov.R
```

`covr_out.json` is created by the script.


Run Sonar Scanner
-----------------
Run `sample-project` analysis to push it into locally running instance:
```bash
cd sample-project
# -X for getting debugging information
./sonar-scanner-run.sh -X
```

Results will be available here:
[http://localhost:9000/dashboard/index/com.msd.gin.common.sonar.r:sample-project](http://localhost:9000/dashboard/index/com.msd.gin.common.sonar.r:sample-project)
