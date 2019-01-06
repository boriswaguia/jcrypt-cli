#-------------------------------------------------------------------------------
# Copyright 2018 Adorsys GmbH & Co KG
# 
# Licensed under the Apache License, Version 2.0 (the "License"); you may not
# use this file except in compliance with the License.  You may obtain a copy
# of the License at
# 
#   http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
# WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
# License for the specific language governing permissions and limitations under
# the License.
#-------------------------------------------------------------------------------
# jcrypt-cli-shasum

## Description
Java command line utility so compute SHAs messages digest for a given set of parameters.

## How to use

1. Clone The project:
`jcrypt-cli-shasum$ git clone https://github.com/boriswaguia/jcrypt-cli.git`

2. Build the project:
`jcrypt-cli-shasum$ mvn clean package`

3. Run the script loaded with the project:
`jcrypt-cli-shasum$ ./jcryptcli-shasum.sh` or `jcrypt-cli-shasum$java -jar jcrypt-cli-shasum-1.0-SNAPSHOT.jar`


## Description

This command line utility use the Java Cryptographic Api to compute digests. The cli support two parameters

- `-a, --algorithm : 1 (default), 224, 256, 384, 512, 512224, 512256`
- `-f : comma separated list of files`
- `ARGS: A list of strings or files paths (/Users/bwa/dev/opensource/bwa/jcrypt-cli/jcrypt-cli-shasum/jcryptcli-shasum.sh abd)`

## Example

`jcrypt-cli-shasum$ ./jcryptcli-shasum.sh -a 256 ${YOUR_DEV_HOME}/jcrypt-cli/jcrypt-cli-shasum/jcryptcli-shasum.sh abd`
