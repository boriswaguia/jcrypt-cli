package com.jcryptcli.shasum.util;

import com.jcryptcli.shasum.args.processor.ArgumentProcessor;
import com.jcryptcli.shasum.args.processor.sha.ShaArgumentProcessorFactory;
import com.jcryptcli.shasum.args.reader.SupportedArgumentParser;
import com.jcryptcli.shasum.args.shared.Arguments;

import java.util.Map;

public class SHaUtil {

    public SHaUtil () {}

    /**
     * Parse command line arguments and compute the SHA value of the provided text.
     * @param args
     * @param parser
     * @return
     */
    public Map<String, String> parseAndCreateSha(String[] args, SupportedArgumentParser parser) {
        Arguments arguments = new ArgumentBuilder(args, parser).getAsArguments();
        ArgumentProcessor processor = ShaArgumentProcessorFactory.getInstance();
        return processor.process(arguments);
    }
}
