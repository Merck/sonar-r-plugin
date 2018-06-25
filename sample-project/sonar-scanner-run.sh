#!/usr/bin/env bash

set -euo pipefail

SONAR_SCANNER_VERSION=3.2.0.1227
URL="https://sonarsource.bintray.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-${SONAR_SCANNER_VERSION}-macosx.zip"

# Resolve where script is stored (symlinks too)
SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
SCRIPT_DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"

SONAR_WORK="${SCRIPT_DIR}/.sonar"

mkdir -p "${SONAR_WORK}"

SONAR_SCANNER_ZIP="${SONAR_WORK}/sonar-scanner-cli-${SONAR_SCANNER_VERSION}-macosx.zip"

[ -f "${SONAR_SCANNER_ZIP}" ] || wget -O "${SONAR_SCANNER_ZIP}" "${URL}"

SONAR_SCANNER_INSTANCE="${SONAR_WORK}/sonar-scanner-${SONAR_SCANNER_VERSION}-macosx"

[ -d "${SONAR_SCANNER_INSTANCE}" ] || unzip "${SONAR_SCANNER_ZIP}" -d "${SONAR_WORK}"

"${SONAR_SCANNER_INSTANCE}/bin/sonar-scanner" "$@"
