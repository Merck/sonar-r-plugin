#!/usr/bin/env bash

SONAR_NAME=sonarqube-6.7.4
URL="https://sonarsource.bintray.com/Distribution/sonarqube/${SONAR_NAME}.zip"

set -euo pipefail

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

SONAR_ZIP="${SONAR_WORK}/${SONAR_NAME}.zip"

[ -f "${SONAR_ZIP}" ] || wget -O "${SONAR_ZIP}" "${URL}"

SONAR_INSTANCE="${SONAR_WORK}/${SONAR_NAME}"

[ -d "${SONAR_INSTANCE}" ] || unzip "${SONAR_ZIP}" -d "${SONAR_WORK}"


ln -fs "${SONAR_INSTANCE}/bin/macosx-universal-64/sonar.sh" "${SONAR_WORK}/sonar.sh"

echo "SonarQube Instance: ${SONAR_INSTANCE}"
