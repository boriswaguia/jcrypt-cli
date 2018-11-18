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
package com.jcryptcli.shasum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import com.jcryptcli.shasum.args.CmdLineArgumentFactory;
/**
 * ShaSumMain !
 *
 */
public final class ShaSumMain 
{
	/** Logger */
	private static final Logger LOG = Logger.getLogger(ShaSumMain.class.getName());
	
	private ShaSumMain() {}
	
	/** Main method */
    public static void main( final String[] args ) {

        try {
        	// Create possible options that can be used in command line
        	final Options cliArgs = CmdLineArgumentFactory.getArgs();
        	
        	// Create a command line parser
        	final CommandLineParser parser = new DefaultParser();
        	
        	// Create a representation of arguments passed in the command line
            final CommandLine cmdLine = parser.parse(cliArgs, args);
            
            // If the command has a help argument
            if(cmdHasHelpArgument(cmdLine)) {
            	LOG.info(extractHelpFromOptions(cliArgs));
                return;
            }
            
            // Extract input algorithm
            final String alg = extractAlgorithm(cmdLine);
            // Read user provided inputs
            final List<String> argList = getCmdLineArgList(cmdLine);
            
            // Maps values to be encrypted into a value and value's byte map 
            final Map<String, byte[]> inputs = mapInputParamsToMaps(argList);
            
            // Print every value in the map its hash
            printHashValuesUsingAlg(alg, inputs);
        } catch (ParseException e) {
        	LOG.severe(e.getMessage());
		}
    }

	private static void printHashValuesUsingAlg(final String alg, final Map<String, byte[]> inputs) {
		inputs.forEach(printHashValuesUsingAlg(alg));
	}

	private static List<String> getCmdLineArgList(final CommandLine cmdLine) {
		return cmdLine.getArgList();
	}

	private static String extractHelpFromOptions(final Options options) {
		final Option option = options.getOption("help");
		return getOptionDescription(option);
	}

	private static String getOptionDescription(final Option option) {
		return option.getDescription();
	}

	private static BiConsumer<? super String, ? super byte[]> printHashValuesUsingAlg(final String alg) {
		return (key, value) -> {
			try {
				final MessageDigest messageDigest = MessageDigest.getInstance("SHA-"+alg);
				messageDigest.reset();
				final byte[] digest = messageDigest.digest(value);
				final String shaDigest = DatatypeConverter.printHexBinary(digest);
				LOG.info(shaDigest+"\t\t"+key);
			} catch (NoSuchAlgorithmException e) {
				LOG.severe(e.getMessage());
			}
		};
	}

	private static Map<String, byte[]> mapInputParamsToMaps(final List<String> argList) {
		return argList.stream().collect(Collectors.toMap(arg -> arg, arg -> {
			if(Files.exists(Paths.get(arg))) {
				try {
					return Files.readAllBytes(Paths.get(arg));
				} catch (IOException e) {
					System.out.println("Error reading - "+arg+" : "+e.getMessage());
					return new byte[0];
				}
			} else {
				return arg.getBytes();
			}
		}));
	}

	private static boolean cmdHasHelpArgument(final CommandLine cmdLine) {
		return cmdLine.hasOption("h") && cmdLine.getArgList().size() == 1;
	}

	private static String extractAlgorithm(final CommandLine cmdLine) {
		if(cmdLine.hasOption("a")) {
			LOG.info(cmdLine.getOptionValue("a"));
			return cmdLine.getOptionValue("a");
		} else {
			return "1";
		}
	}

}
