package edu.cnm.deepdive;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Parser {

  private Options options;

  public Parser() {
    options = new Options();
    Option option = Option.builder("l")
                          .longOpt("length")
                          .hasArg(true)
                          .argName("length")
                          .numberOfArgs(1)
                          .optionalArg(false)
                          .required(false)
                          .type(Number.class)
                          .desc("number of words in passphrase")
                          .build();
    options.addOption(option);
    option = Option.builder("?")
                   .longOpt("help")
                   .hasArg(false)
                   .required(false)
                   .desc("display usage")
                   .build();
    options.addOption(option);
  }

  public Map<String, Object> parse(String[] args) throws ParseException {
    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = parser.parse(options, args);
    if (cmd.hasOption("?")) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("java edu.cnm.deepdive.Main [options]", options);
      return null;
    }
    HashMap<String, Object> optionsSpecified = new HashMap<>();
    for (Option option : cmd.getOptions()) {
      Object value = cmd.getParsedOptionValue(option.getOpt());
      optionsSpecified.put(option.getOpt(), value);
    }
    return optionsSpecified;
  }

}
