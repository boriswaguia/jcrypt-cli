package com.jcryptcli.shasum.args.processor.sha;

import com.jcryptcli.shasum.args.shared.Arguments;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * 
 * ShaArgumentProcessor
 * 
 * 
 * @author bwa
 *
 */
class DefaultShaArgumentProcessor implements ShaArgumentProcessor{

  private static final Logger log = Logger.getLogger(DefaultShaArgumentProcessor.class.getName());

  @Override
  public ProcessResult process(Arguments arguments) {
    // If params has -h, return help
    if(arguments.hasArgument("h")) {
      ProcessResult res = new ProcessResult();
      res.put("help", "help");
      return res;
    }
    // If param has -a and -f arguments
    if(arguments.hasArgument("a") && arguments.hasArgument("f")) {
      return fileShasFromArg(arguments);
    }
    throw new IllegalStateException("Unsupported action. Use -h or -a alg -f /path/file_name.ext");
  }

  private ProcessResult fileShasFromArg(Arguments arguments) {
    String alg = arguments.getArgument("a").trim();
    String fileArg = arguments.getArgument("f");
    String[] fileNames = fileArg.split(",");

    ProcessResult result = new ProcessResult();

    Map<String, byte[]> files = mapInputParamsToMaps(fileNames);

    files.forEach((key, value) -> {
      try {
        final MessageDigest messageDigest = MessageDigest.getInstance("SHA-"+alg);
        messageDigest.reset();
        final byte[] digest = messageDigest.digest(value);
        final String shaDigest = DatatypeConverter.printHexBinary(digest);
        result.put(key, shaDigest);
      } catch (NoSuchAlgorithmException e) {
        throw new IllegalStateException(String.format("Unsupported operation %s", alg));
      }
    });
    return result;
  }

  private Map<String, byte[]> mapInputParamsToMaps(final String[] argList) {
    return Arrays.stream(argList)
            .map(arg -> arg.trim())
            .collect(Collectors.toMap(arg -> arg, arg -> {
              if(Files.exists(Paths.get(arg))) {
                try {
                  return Files.readAllBytes(Paths.get(arg));
                } catch (IOException e) {
                  throw new IllegalStateException(String.format("Unable to load file %s",arg), e);
                }
              } else {
                // return arg.getBytes();
                throw new IllegalStateException(String.format("File with path %s doesn't exit.", arg));
              }
            }));
  }

}
