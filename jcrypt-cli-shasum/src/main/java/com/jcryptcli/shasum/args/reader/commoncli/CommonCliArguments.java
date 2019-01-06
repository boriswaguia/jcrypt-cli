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

package com.jcryptcli.shasum.args.reader.commoncli;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import com.jcryptcli.shasum.args.shared.ArgumentRegistry;
import com.jcryptcli.shasum.args.shared.Arguments;

/**
 *
 * CommonCliArguments
 *
 * @author bwa
 *
 */
class CommonCliArguments implements Arguments {
  
  private final CommandLine commandLine;
  /**
   * @param args
   */
  public CommonCliArguments(String[] args) {
    final Options options = new Options();
    // Create possible options that can be used in command line
    ArgumentRegistry
                .getJcriptSupportedArguments()
                .stream()
                  .map( (arg) -> {
                   
                    Option option = Option
                      .builder(arg.getShortText())
                      .argName(arg.getLongText())
                      .hasArg(arg.isRequired())
                      .longOpt(arg.getLongText())
                      .numberOfArgs(arg.getNumberOfArgs())
                      .required(arg.isRequired())
                      .desc(arg.getDescription())
                      .build();
                    
                    return option;
                  })
                  .forEach(opt -> {
                    options.addOption(opt);
                   });
    
    // Create a command line parser
    final CommandLineParser parser = new DefaultParser();
    // Create a representation of arguments passed in the command line
    try {
      this.commandLine = parser.parse(options, args);
    } catch (ParseException e) {
      throw new IllegalStateException("Could not build the command line argument", e);
    } finally {}
  }

  /* (non-Javadoc)
   * @see com.jcryptcli.shasum.args.reader.Argumemts#hasArgument(java.lang.String)
   */
  @Override
  public boolean hasArgument(String arg) {
    return this.commandLine.hasOption(arg);
  }

  /* (non-Javadoc)
   * @see com.jcryptcli.shasum.args.reader.Argumemts#getArgument(java.lang.String)
   */
  @Override
  public String getArgument(String arg) {
    return this.commandLine.getOptionValue(arg);
  }

  @Override
  public Map<String, String> getAllArguments() {
    return Arrays.asList(this.commandLine.getOptions())
              .stream()
              .collect(
                  Collectors.toMap(
                                Option::getOpt,
                                opt -> opt.getValue() == null ? "": opt.getValue().trim()));
  }

}
