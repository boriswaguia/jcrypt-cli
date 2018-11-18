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

package com.jcryptcli.shasum.args;

import org.apache.commons.cli.Option;

/**
 *
 * HelpArgProvider
 *
 * @author bwa
 *
 */
public class HelpArgProvider implements HasCmdLineArg{

	/* (non-Javadoc)
	 * @see com.jcryptcli.shasum.args.HasCmdLineArg#getArg()
	 */
	@Override
	public Option getArg() {
		return helpOption();
	}
	

	private Option helpOption() {
		return Option.builder("h").argName("help")
        		.hasArg(false)
        		.longOpt("help")
        		.numberOfArgs(0)
        		.desc("Usage: shasum [OPTION]... [ARGS]... [FILE]...\n" + 
        				"Print or check SHA checksums.\n" + 
        				"With no FILE, or when FILE is -, read standard input.\n" + 
        				"\n" + 
        				"  -a, --algorithm   1 (default), 224, 256, 384, 512, 512224, 512256\n" + 
        				"  -b, --binary      read in binary mode\n" + 
        				"  -c, --check       read SHA sums from the FILEs and check them\n" + 
        				"  -t, --text        read in text mode (default)\n" + 
        				"  -p, --portable    read in portable mode\n" + 
        				"                        produces same digest on Windows/Unix/Mac\n" + 
        				"  -0, --01          read in BITS mode\n" + 
        				"                        ASCII '0' interpreted as 0-bit,\n" + 
        				"                        ASCII '1' interpreted as 1-bit,\n" + 
        				"                        all other characters ignored\n" + 
        				"\n" + 
        				"The following two options are useful only when verifying checksums:\n" + 
        				"  -s, --status      don't output anything, status code shows success\n" + 
        				"  -w, --warn        warn about improperly formatted checksum lines\n" + 
        				"\n" + 
        				"  -h, --help        display this help and exit\n" + 
        				"  -v, --version     output version information and exit\n" + 
        				"\n" + 
        				"When verifying SHA-512/224 or SHA-512/256 checksums, indicate the\n" + 
        				"algorithm explicitly using the -a option, e.g.\n" + 
        				"\n" + 
        				"  shasum -a 512224 -c checksumfile\n" + 
        				"\n" + 
        				"The sums are computed as described in FIPS-180-4.  When checking, the\n" + 
        				"input should be a former output of this program.  The default mode is to\n" + 
        				"print a line with checksum, a character indicating type (`*' for binary,\n" + 
        				"` ' for text, `?' for portable, `^' for BITS), and name for each FILE.\n" + 
        				"\n" + 
        				"Report shasum bugs to bwa@adorsys.de")
        		.build();
	}


}
