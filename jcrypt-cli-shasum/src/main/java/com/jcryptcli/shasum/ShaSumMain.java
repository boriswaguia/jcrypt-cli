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

import com.jcryptcli.shasum.args.reader.SupportedArgumentParser;
import com.jcryptcli.shasum.util.SHaUtil;

import java.util.Map;
import java.util.logging.Logger;

/**
 * ShaSumMain !
 *
 */
public final class ShaSumMain 
{
	private static final Logger log = Logger.getLogger(ShaSumMain.class.getName());

	private ShaSumMain() {}
	
	/** Main method */
    public static void main( final String[] args ) {
		Map<String, String> result = new SHaUtil().parseAndCreateSha(args, SupportedArgumentParser.COMMON_CLI);
		result.forEach((key, value) -> {
			System.out.println(String.format("%s \t %s", key, value));
		});
    }

}
