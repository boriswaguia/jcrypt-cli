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

import java.util.Map;

/**
 *
 * Argumemts
 *
 * @author bwa
 *
 */
public interface Arguments {
  
  boolean hasArgument(String arg);
  String getArgument(String arg);
  Map<String, String> getAllArguments();

}
