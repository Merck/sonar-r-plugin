#!/usr/bin/env bash
# ----------------------------------------------------------------------------
# Copyright 2018 Merck Sharp & Dohme Corp. a subsidiary of Merck & Co.,
# Inc., Kenilworth, NJ, USA.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# ----------------------------------------------------------------------------

set -euo pipefail

SONAR_SCANNER_VERSION=4.7.0.2747
URL="https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-${SONAR_SCANNER_VERSION}-macosx.zip"

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

[ -s "${SONAR_SCANNER_ZIP}" ] || wget --no-check-certificate -O "${SONAR_SCANNER_ZIP}" "${URL}"

SONAR_SCANNER_INSTANCE="${SONAR_WORK}/sonar-scanner-${SONAR_SCANNER_VERSION}-macosx"

[ -d "${SONAR_SCANNER_INSTANCE}" ] || unzip "${SONAR_SCANNER_ZIP}" -d "${SONAR_WORK}"

"${SONAR_SCANNER_INSTANCE}/bin/sonar-scanner" "$@"
