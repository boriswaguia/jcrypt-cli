package com.jcryptcli.shasum.util;

import com.jcryptcli.shasum.args.reader.ArgumentParserFactory;
import com.jcryptcli.shasum.args.reader.SupportedArgumentParser;
import com.jcryptcli.shasum.args.shared.Arguments;

import java.util.Objects;

public class ArgumentBuilder {

    private final String[] args;
    private final SupportedArgumentParser parser;

    /**
     * Create a default ArgumentBuilder with the default SupportedArgumentParser.COMMON_CLI.
     * @param args
     */
    public ArgumentBuilder(String[] args) {
        this(args, SupportedArgumentParser.COMMON_CLI);
    }

    public ArgumentBuilder(String[] args, SupportedArgumentParser parser) {
        validate(args);
        this.args = args;
        this.parser = parser;
    }

    private void validate(String[] args) {
        if(Objects.isNull(args) || args.length == 0) throw new IllegalArgumentException("Arguments should not be null or null");
    }

    public Arguments getAsArguments() {
        return ArgumentParserFactory.getParser(parser).parse(this.args);
    }

}
