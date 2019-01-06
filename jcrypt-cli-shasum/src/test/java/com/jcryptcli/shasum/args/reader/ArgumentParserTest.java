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

package com.jcryptcli.shasum.args.reader;

import com.jcryptcli.shasum.args.shared.Arguments;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 *
 * ArgumentReaderTest
 *
 * @author bwa
 *
 */
public class ArgumentParserTest {
  
  
  @Test
  public void getCommonCliParser() {
    ArgumentParser argumentParser = ArgumentParserFactory.getParser(SupportedArgumentParser.COMMON_CLI);
    assertNotNull(argumentParser);
  }
  
  @Test
  public void getArguments() {
    Arguments arguments = createHelpArgument();
    assertNotNull(arguments);
    assertTrue(arguments.hasArgument("h"));
  }
  
  @Test
  public void getAllArgumentsShouldReturnOneArgument() {
    Arguments arguments = createHelpArgument();
    assertTrue(arguments.getAllArguments().size() == 1);
  }
  

  @Test
  public void getAllArgumentsShouldReturnOneArgumentWithEmptyStringValue() {
    Arguments arguments = createHelpArgument();
    Map<String, String> argMap = arguments.getAllArguments();
    assertTrue(argMap.size() == 1);
    assertEquals("", argMap.get("h").trim());
  }

  private Arguments createHelpArgument() {
    String[] args = {"-h"};
    ArgumentParser argumentParser = ArgumentParserFactory.getParser(SupportedArgumentParser.COMMON_CLI);
    return argumentParser.parse(args);
  }
  

  @Test
  public void hasArgument() {
    Arguments arguments = createHelpArgument();
    
    boolean hasArgument = arguments.hasArgument("h");
    assertTrue(hasArgument);
    
  }
}
