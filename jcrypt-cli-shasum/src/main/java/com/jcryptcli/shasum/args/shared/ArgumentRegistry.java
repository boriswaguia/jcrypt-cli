/*******************************************************************************
 * Copyright 2018 Adorsys GmbH & Co KG
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

package com.jcryptcli.shasum.args.shared;

import java.util.Collection;
import java.util.HashSet;

/**
 *
 * ArgumentRegistry
 *
 * @author bwa
 *
 */
public class ArgumentRegistry {
  
  
  private ArgumentRegistry() {}
  
  public static Collection<Argument> getJcriptSupportedArguments() {
    Collection<Argument> hashSet = new HashSet<>();

    // Add arguments
    
    addArgument(hashSet, helpArgument());
    addArgument(hashSet, algArgument());
    addArgument(hashSet, fileArgument());
    
    return hashSet;
  }

  private static DefaultArgument fileArgument() {
    return new DefaultArgument("f", "file", "A comma separated list of files, whose SHA values have to be computed.");
  }

  private static DefaultArgument algArgument() {
    return new DefaultArgument("a", "alg", "1 (default), 224, 256, 384, 512, 512224, 512256");
  }


  private static void addArgument(Collection<Argument> hashSet, Argument arg) {
    hashSet.add(arg);
  }
  

  private static DefaultArgument helpArgument() {
    return new DefaultArgument("h", "help", "\"Usage: shasum [OPTION]... [ARGS]... [FILE]...\\n\" + \n" + 
        "                        \"Print or check SHA checksums.\\n\" + \n" + 
        "                        \"With no FILE, or when FILE is -, read standard input.\\n\" + \n" + 
        "                        \"\\n\" + \n" + 
        "                        \"  -a, --algorithm   1 (default), 224, 256, 384, 512, 512224, 512256\\n\" + \n" + 
        "                        \"  -b, --binary      read in binary mode\\n\" + \n" + 
        "                        \"  -c, --check       read SHA sums from the FILEs and check them\\n\" + \n" + 
        "                        \"  -t, --text        read in text mode (default)\\n\" + \n" + 
        "                        \"  -p, --portable    read in portable mode\\n\" + \n" + 
        "                        \"                        produces same digest on Windows/Unix/Mac\\n\" + \n" + 
        "                        \"  -0, --01          read in BITS mode\\n\" + \n" + 
        "                        \"                        ASCII '0' interpreted as 0-bit,\\n\" + \n" + 
        "                        \"                        ASCII '1' interpreted as 1-bit,\\n\" + \n" + 
        "                        \"                        all other characters ignored\\n\" + \n" + 
        "                        \"\\n\" + \n" + 
        "                        \"The following two options are useful only when verifying checksums:\\n\" + \n" + 
        "                        \"  -s, --status      don't output anything, status code shows success\\n\" + \n" + 
        "                        \"  -w, --warn        warn about improperly formatted checksum lines\\n\" + \n" + 
        "                        \"\\n\" + \n" + 
        "                        \"  -h, --help        display this help and exit\\n\" + \n" + 
        "                        \"  -v, --version     output version information and exit\\n\" + \n" + 
        "                        \"\\n\" + \n" + 
        "                        \"When verifying SHA-512/224 or SHA-512/256 checksums, indicate the\\n\" + \n" + 
        "                        \"algorithm explicitly using the -a option, e.g.\\n\" + \n" + 
        "                        \"\\n\" + \n" + 
        "                        \"  shasum -a 512224 -c checksumfile\\n\" + \n" + 
        "                        \"\\n\" + \n" + 
        "                        \"The sums are computed as described in FIPS-180-4.  When checking, the\\n\" + \n" + 
        "                        \"input should be a former output of this program.  The default mode is to\\n\" + \n" + 
        "                        \"print a line with checksum, a character indicating type (`*' for binary,\\n\" + \n" + 
        "                        \"` ' for text, `?' for portable, `^' for BITS), and name for each FILE.\\n\" + \n" + 
        "                        \"\\n\" + \n" + 
        "                        \"Report shasum bugs to bwa@adorsys.de\"", false, 0);
  }

}
