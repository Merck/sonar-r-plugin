
cat(jsonlite::toJSON(as.data.frame(lintr::lint_package()), pretty = TRUE), file = "lintr_out.json")
