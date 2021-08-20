#covr::azure(quiet = FALSE)
cov <- covr::package_coverage(quiet=FALSE)
covr::to_sonarqube(cov, filename = "covr_sonarqube.xml")

# remove module names from the file paths to make it working in SonarQube

x <- xml2::read_xml("covr_sonarqube.xml")
files <- xml2::xml_find_all(x, "/coverage/file")

for (f in files) {
  v <- xml2::xml_attr(f, "path")
  xml2::xml_set_attr(f, "path", sub("^[^/]*/(.*)", "\\1", v))
}

xml2::write_xml(x, file = "covr_sonarqube.xml")
