package com.jcryptcli.shasum.args.processor.sha;

import java.util.HashMap;
import java.util.Map;
import com.jcryptcli.shasum.args.processor.ArgumentProcessor;

/**
 * 
 * ShaArgumentProcessorFactory
 * 
 * 
 * @author bwa
 *
 */
public class ShaArgumentProcessorFactory {
  
  private static final String DEFAULT_SHA_ARGUMENT_PROCESSOR_NAME = DefaultShaArgumentProcessor.class.getName();

  private ShaArgumentProcessorFactory() {}
  
  
  public final static ArgumentProcessor getInstance() {
    return factory().get(DEFAULT_SHA_ARGUMENT_PROCESSOR_NAME);
  }
  
  private static Map<String, ShaArgumentProcessor> factory () {
    Map<String, ShaArgumentProcessor> factory = new HashMap<>();
    
    // Feed Data in the map
    
    factory.put(DEFAULT_SHA_ARGUMENT_PROCESSOR_NAME, new DefaultShaArgumentProcessor());
    
    // return the factory
    return factory;
  }
}
