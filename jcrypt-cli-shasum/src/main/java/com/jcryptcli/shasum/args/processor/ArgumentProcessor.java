package com.jcryptcli.shasum.args.processor;

import com.jcryptcli.shasum.args.shared.Arguments;

import java.util.Map;

/**
 * 
 * ArgumentProcessor
 * 
 * @author bwa
 *
 */
public interface ArgumentProcessor {
  
  /**
   * @param arguments
   * @return 
   */
  Map<String, String> process(Arguments arguments);
}
