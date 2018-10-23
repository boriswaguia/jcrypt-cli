package com.jcryptcli.shasum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = Logger.getLogger(App.class.getName());
	
    public static void main( String[] args )
    {
    	logger.setLevel(Level.FINE);
    	
        Option help = helpOption();
        Option algorithm = createAlgorithm();
        
        
        // Build options
        Options options = buildOptions(help, algorithm);
        
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmdLine = parser.parse(options, args);
            
            if(cmdLine.hasOption("h") && cmdLine.getArgList().size() == 1) {
                System.out.println(options.getOption("help").getDescription());
                return;
            }
            
            String a = null;
            if(cmdLine.hasOption("a")) {
            	System.out.println(cmdLine.getOptionValue("a"));
            	a = cmdLine.getOptionValue("a");
            }
            List<String> argList = cmdLine.getArgList();
            
            Map<String, byte[]> inputs = argList.stream().collect(Collectors.toMap(arg -> arg, arg -> {
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
            
            if (a == null) a = "1";
            
            final String alg = a;
            inputs.forEach((key, value) -> {
            	try {
					MessageDigest messageDigest = MessageDigest.getInstance("SHA-"+alg);
					messageDigest.reset();
					byte[] digest = messageDigest.digest(value);
					String shaDigest = DatatypeConverter.printHexBinary(digest);
					System.out.println(shaDigest+"\t\t"+key);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
            });
            System.out.println(argList);
        } catch (ParseException e) {
            e.printStackTrace();
		}
    }

	private static Options buildOptions(Option ... opts) {
		if(opts == null || opts.length > 10) throw new IllegalArgumentException("The option size in invlaid");
		Options options = new Options();
		Arrays.asList(opts).stream().forEach(opt -> {
			options.addOption(opt);
		});
		return options;
	}

	private static Option createAlgorithm() {
		
		return Option.builder("a")
				.argName("algorithm")
				.hasArg()
				.longOpt("algorithm")
				.numberOfArgs(1)
				.required(false)
				.desc("1 (default), 224, 256, 384, 512, 512224, 512256")
				.build();
	}

	private static Option helpOption() {
		return Option.builder("h").argName("help")
        		.hasArg(false)
        		.longOpt("help")
        		.numberOfArgs(0)
        		.desc("Usage: shasum [OPTION]... [ARGS]... [FILE]...\n" + 
        				"Print or check SHA checksums.\n" + 
        				"With no FILE, or when FILE is -, read standard input.\n" + 
        				"\n" + 
        				"  -a, --algorithm   1 (default), 224, 256, 384, 512, 512224, 512256\n" + 
        				"  -b, --binary      read in binary mode\n" + 
        				"  -c, --check       read SHA sums from the FILEs and check them\n" + 
        				"  -t, --text        read in text mode (default)\n" + 
        				"  -p, --portable    read in portable mode\n" + 
        				"                        produces same digest on Windows/Unix/Mac\n" + 
        				"  -0, --01          read in BITS mode\n" + 
        				"                        ASCII '0' interpreted as 0-bit,\n" + 
        				"                        ASCII '1' interpreted as 1-bit,\n" + 
        				"                        all other characters ignored\n" + 
        				"\n" + 
        				"The following two options are useful only when verifying checksums:\n" + 
        				"  -s, --status      don't output anything, status code shows success\n" + 
        				"  -w, --warn        warn about improperly formatted checksum lines\n" + 
        				"\n" + 
        				"  -h, --help        display this help and exit\n" + 
        				"  -v, --version     output version information and exit\n" + 
        				"\n" + 
        				"When verifying SHA-512/224 or SHA-512/256 checksums, indicate the\n" + 
        				"algorithm explicitly using the -a option, e.g.\n" + 
        				"\n" + 
        				"  shasum -a 512224 -c checksumfile\n" + 
        				"\n" + 
        				"The sums are computed as described in FIPS-180-4.  When checking, the\n" + 
        				"input should be a former output of this program.  The default mode is to\n" + 
        				"print a line with checksum, a character indicating type (`*' for binary,\n" + 
        				"` ' for text, `?' for portable, `^' for BITS), and name for each FILE.\n" + 
        				"\n" + 
        				"Report shasum bugs to bwa@adorsys.de")
        		.build();
	}

}
