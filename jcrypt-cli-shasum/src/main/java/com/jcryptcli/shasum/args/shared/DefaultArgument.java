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

import com.jcryptcli.shasum.util.Contract;

/**
 *
 * DefaultArgument
 *
 * @author bwa
 *
 */
class DefaultArgument implements Argument{
  
  private final String shortText;
  private final String longText;
  private final String description;
  private final boolean isRequired;
  private final int numberOfArgs;
  
  
  /**
   * @param shortText
   * @param longText
   * @param description
   */
  public DefaultArgument(String shortText, String longText, String description) {
    this(shortText, longText, description, false, 1);
  }
  
  public DefaultArgument(String shortText, String longText, String description, boolean isRequired, int numberOfArgs) {

    Contract.requireNotEmpty(shortText);
    Contract.requireNotEmpty(longText);
    Contract.requireNotEmpty(description);
    
    this.shortText = shortText;
    this.longText = longText;
    this.description = description;
    this.isRequired = isRequired;
    this.numberOfArgs = numberOfArgs;
  }
  /* (non-Javadoc)
   * @see com.jcryptcli.shasum.args.Argument#getShortRepresentation()
   */
  @Override
  public String getShortText() {
    return this.shortText;
  }

  /* (non-Javadoc)
   * @see com.jcryptcli.shasum.args.Argument#getLongRepresentation()
   */
  @Override
  public String getLongText() {
    return this.longText;
  }

  /* (non-Javadoc)
   * @see com.jcryptcli.shasum.args.Argument#getDescription()
   */
  @Override
  public String getDescription() {
    return this.description;
  }

  /* (non-Javadoc)
   * @see com.jcryptcli.shasum.args.shared.Argument#isRequired()
   */
  @Override
  public boolean isRequired() {
    return this.isRequired;
  }

  /* (non-Javadoc)
   * @see com.jcryptcli.shasum.args.shared.Argument#getNumberOfArgs()
   */
  @Override
  public int getNumberOfArgs() {
    return this.numberOfArgs;
  }

}
