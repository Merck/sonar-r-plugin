library(lintr)
library(jsonlite)

res <- lint("bad.R")
x <- toJSON(as.data.frame(res))

fileConn<-file("lintr_out.json")
writeLines(x,fileConn)
close(fileConn)
