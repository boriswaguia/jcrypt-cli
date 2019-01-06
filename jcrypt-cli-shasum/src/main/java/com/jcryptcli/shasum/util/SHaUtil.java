package com.jcryptcli.shasum.util;

import com.jcryptcli.shasum.args.processor.ArgumentProcessor;
import com.jcryptcli.shasum.args.processor.sha.ShaArgumentProcessorFactory;
import com.jcryptcli.shasum.args.reader.SupportedArgumentParser;
import com.jcryptcli.shasum.args.shared.Arguments;

import java.util.Map;

public class SHaUtil {

    public SHaUtil () {}

    public Map<String, String> parseAndCreateSha(String[] args, SupportedArgumentParser parser) {
        Arguments arguments = new ArgumentBuilder(args, parser).getAsArguments();
        ArgumentProcessor processor = ShaArgumentProcessorFactory.getInstance();
        return processor.process(arguments);
    }
}
