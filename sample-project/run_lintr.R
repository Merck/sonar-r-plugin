library(lintr)
library(jsonlite)

res <- lint("bad.R")
x <- toJSON(as.data.frame(res), pretty=TRUE)

cat(x, file="lintr_out.json")
