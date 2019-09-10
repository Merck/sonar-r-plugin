#!/usr/bin/env bash

set -euo pipefail

echo "Setting release version"

git checkout master
# set release version in POM
./mvnw build-helper:parse-version \
   versions:set '-DnewVersion=${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.incrementalVersion}' \
   versions:commit
# read version back into bash variable
RELEASE_VERSION=$(./mvnw help:evaluate -N -Dexpression=project.version|grep -v '\[')

echo "Current release: ${RELEASE_VERSION}"
# release branch
git checkout -b release/v${RELEASE_VERSION}
#
git commit -a -m "Update release ${RELEASE_VERSION} in POM"
# release tag on the same commit
git tag "${RELEASE_VERSION}"

git push origin release/v${RELEASE_VERSION} --tags -n

echo "Setting new development version"

git checkout master
# set next SNAPSHOT in POM
./mvnw build-helper:parse-version \
   versions:set '-DnewVersion=${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.nextIncrementalVersion}-SNAPSHOT' \
   versions:commit
# read version back into bash variable
DEVELOPMENT_VERSION=$(./mvnw help:evaluate -N -Dexpression=project.version|grep -v '\[')

echo "New development: ${DEVELOPMENT_VERSION}"
#
git commit -a -m "Set new development version to ${DEVELOPMENT_VERSION}"

#
git push -n
