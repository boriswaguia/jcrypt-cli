package com.jcryptcli.shasum.args.processor;

import com.jcryptcli.shasum.args.reader.SupportedArgumentParser;
import com.jcryptcli.shasum.util.SHaUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;

public class ArgumentProcessorTest {
    private final String tmpFolder = System.getProperty("java.io.tmpdir")+File.pathSeparator;
    @Test
    public void testProcessSha256TestFileWithHelpArgumentShouldReturnHelpText() {
        String[] args = {"-a 512 ", "-f /tmp/letter_to_the_moon.txt", "-h"};

        Map<String, String> result = new SHaUtil().parseAndCreateSha(args, SupportedArgumentParser.COMMON_CLI);

        final String helpKey = "help";
        Assert.assertTrue(result.containsKey(helpKey));
        Assert.assertEquals("help", result.get(helpKey));
    }

    @Test
    public void testProcessSha256TestFileShouldReturnShaValue() throws IOException {
        String testFilePath = tmpFolder+"letter_to_the_moon.txt";
        String[] args = {"-a 512 ", "-f "+testFilePath};

        copyTo("letter_to_the_moon.txt", testFilePath);

        Map<String, String> result = new SHaUtil().parseAndCreateSha(args, SupportedArgumentParser.COMMON_CLI);
        // Assert SHA
        String expectedResult = "268db317f9818ed34c21b1aa76960e2e0bf90becdfdafa6d65dbff982ee8a5a04045c646e53103c3a921d9fb164a83f1b3f4cc5e5dbe13910c3ce893de9c0165";
        Assert.assertTrue(result.containsKey(testFilePath));
        Assert.assertNotNull(result.get(testFilePath));
        Assert.assertEquals(expectedResult.toUpperCase(), result.get(testFilePath));
    }

    private void copyTo(String fileName,String dest) {
        try {
            Files.copy(ArgumentProcessorTest.class.getClassLoader().getResourceAsStream(fileName)
                    , (new File(dest)).toPath()
                    , StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Unable to copy %s to %s", fileName, dest), e);
        }
    }
}
