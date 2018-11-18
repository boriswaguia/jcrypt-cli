/*******************************************************************************
 * Copyright 2018 Adorsys GmbH & Co KG
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/

package com.jcryptcli.shasum.args;

import java.util.Arrays;
import org.apache.commons.cli.Options;

/**
 *
 * CmdLineArgumentFactory - Provide callers with the list of registered {@link Options}
 *
 * @author bwa
 *
 */
public final class CmdLineArgumentFactory {

  private CmdLineArgumentFactory() {}


  /**
   * @return commandLineArgs - The list of command-line argument supported by this cli tool.
   */
  public static Options getArgs() {
    return createAppOptions();
  }


  private static Options createAppOptions() {
    // Build options
    return buildOptions(new HelpArgProvider(), new AlgorithmArgProvider());
  }


  private static Options buildOptions(final HasCmdLineArg... opts) {
    if (opts == null || opts.length > 10) {
      throw new IllegalArgumentException("The option size in invlaid. Expected a range of [1-10] elements");
    }
    final Options options = new Options();
    Arrays.asList(opts).stream().forEach(opt -> {
      options.addOption(opt.getArg());
    });

    return options;
  }

}
